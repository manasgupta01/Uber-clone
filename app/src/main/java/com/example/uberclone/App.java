package com.example.uberclone;

import android.app.Application;
import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("0lhZFVQg41nHdRXNMqf81Lps3hyAUR5pBNcFxp8k")
                .clientKey("WSzp0Ip5PNHXC0ApjIhdx9ua344jSxH8P0PfMhKc")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }

}
