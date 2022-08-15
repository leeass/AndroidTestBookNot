package com.lee.bookkeep;

import android.app.Application;

import com.lee.bookkeep.db.DBManager;

public class UnitApp extends Application {
    public  void onCreate(){
        super.onCreate();
        DBManager.initDB(getApplicationContext());
    }
}
