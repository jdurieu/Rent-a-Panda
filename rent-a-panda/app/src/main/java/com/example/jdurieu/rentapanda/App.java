package com.example.jdurieu.rentapanda;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by JonathanDurieu
 * on 10/06/16.
 */

public class App extends Application {

    private RequestQueue mRequestQueue;

    private static App mSharedInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mSharedInstance = this;

        mRequestQueue = Volley.newRequestQueue(this);
        mRequestQueue.start();

    }

    public static App getInstance() {
        return mSharedInstance;
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

}
