package com.pixelatedleaft.kore;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

/**
 * @author Julian Cardona on 9/5/17.
 */

public class KoreApp extends Application{

    @SuppressLint("StaticFieldLeak")
    private static Context globalContext;

    @Override
    public void onCreate() {
        super.onCreate();
        globalContext = this;
    }

    public static Context getGlobalContext() {
        return globalContext;
    }

}
