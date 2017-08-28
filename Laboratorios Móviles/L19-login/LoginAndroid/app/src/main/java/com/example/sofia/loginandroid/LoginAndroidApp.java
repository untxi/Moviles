package com.example.sofia.loginandroid;

import android.app.Application;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;


public class LoginAndroidApp extends Application{
    @Override
    public void onCreate (){
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
    }
}

