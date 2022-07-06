package com.example.uberclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class MainActivity extends AppCompatActivity {

    public void redirectActivity(){

    if(ParseUser.getCurrentUser().get("UserOrDriver").equals("user")) {
        Intent intent = new Intent(getApplicationContext(), RiderActivity.class);
        startActivity(intent);

    }
    else{
        Intent intent = new Intent(getApplicationContext(),ViewRequestActivity.class);
        startActivity(intent);
    }

    }

    public void getStarted(View view){

        Switch switch1 = (Switch) findViewById(R.id.switch1);

        Log.i("switch value", String.valueOf(switch1.isChecked()));

        String userType =  "user";
        if(switch1.isChecked()){
            userType="driver";
        }

        ParseUser.getCurrentUser().put("UserOrDriver",userType);
        ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {

                redirectActivity();
            }
        });

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ParseUser.getCurrentUser()==null) {
            ParseAnonymousUtils.logIn(new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if(e==null){
                        Log.i("info","anonnaymous login success");
                    }
                    else{
                        Log.i("info","anonnaymous login failed");
                    }
                }
            });
        }
        else{
            if(ParseUser.getCurrentUser().get("UserOrDriver")!=null){
                Log.i("info","redirecing as "+ParseUser.getCurrentUser().get("UserOrDriver"));
                redirectActivity();
            }
        }




        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }
}