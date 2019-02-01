package com.desafiozup;


import android.app.Application;
import android.content.Context;


public class App extends Application {

    static App instance;

    public Context getContext() {
        return getApplicationContext();
    }

    public static App getInstance() {
        if (instance == null) {
            instance = new App();
        }

        return instance;
    }

}

