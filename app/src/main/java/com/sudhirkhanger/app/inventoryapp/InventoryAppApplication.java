package com.sudhirkhanger.app.inventoryapp;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class InventoryAppApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
