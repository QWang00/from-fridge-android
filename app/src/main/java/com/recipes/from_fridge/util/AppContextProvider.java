package com.recipes.from_fridge.util;

import android.app.Application;
import android.content.Context;

public class AppContextProvider extends Application {

    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
    }

    public static Context getContext() {
        return appContext;
    }
}
