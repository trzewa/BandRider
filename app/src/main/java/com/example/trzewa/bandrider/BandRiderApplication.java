package com.example.trzewa.bandrider;

import android.app.Application;

import com.parse.Parse;


public class BandRiderApplication extends Application{

    private static BandRiderApplication sInstance;

    @Override
    public void onCreate() {
        sInstance = this;
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "7dCgOIoRAUAdUhbz9JL4t1slM3nb7qjMSxHKAJlk", "jNNxaRX1LG8Khoxj8hbGbOogveERWn4BANC2iAcI");

    }

    public static BandRiderApplication getInstance() {
        return sInstance;
    }
}
