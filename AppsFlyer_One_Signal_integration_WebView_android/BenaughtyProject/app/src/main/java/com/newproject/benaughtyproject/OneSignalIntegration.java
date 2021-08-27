package com.newproject.benaughtyproject;

import android.app.Application;

import com.onesignal.OneSignal;

public class OneSignalIntegration extends Application {

    private static final String ONESIGNAL_APP_ID = "41db8615-1ef4-456a-acdb-a881baf9fc78";

    @Override
    public void onCreate() {
        super.onCreate();



        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);


    }

}
