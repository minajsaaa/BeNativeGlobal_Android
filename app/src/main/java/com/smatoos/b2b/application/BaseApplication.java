package com.smatoos.b2b.application;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.smatoos.b2b.R;

public class BaseApplication extends Application {

    private Tracker mTracker;

    //  ====================================================================================

    @Override
    public void onCreate() {
        super.onCreate();

        //  Todo
        Stetho.initializeWithDefaults(this);
   }

    synchronized public Tracker getDefaultTracker() {
        if (mTracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
            mTracker = analytics.newTracker(R.xml.global_tracker);
        }
        return mTracker;
    }
}
