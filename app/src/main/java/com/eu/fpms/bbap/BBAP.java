package com.eu.fpms.bbap;

import android.app.Application;
import android.os.SystemClock;

public class BBAP extends Application {

    @Override
    public void onCreate() {

        super.onCreate();
        SystemClock.sleep(2000);

    }
}
