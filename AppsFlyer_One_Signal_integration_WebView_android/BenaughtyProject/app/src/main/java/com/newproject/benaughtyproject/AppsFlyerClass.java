package com.newproject.benaughtyproject;

import android.app.Application;

import com.appsflyer.AppsFlyerLib;

public class AppsFlyerClass extends Application {

    public static final String LOG_TAG = "AppsFlyerFeedMeApp";
    @Override
    public void onCreate() {
        super.onCreate();
        //noinspection SpellCheckingInspection
        String afDevKey = "QkKWZNtWzpmmNV3QaQxkKg";

        AppsFlyerLib appsflyer = AppsFlyerLib.getInstance();
        appsflyer.setMinTimeBetweenSessions(0);
        appsflyer.init(afDevKey, null, this);
        appsflyer.start(this, afDevKey);
        appsflyer.setDebugLog(true);
    }

}
