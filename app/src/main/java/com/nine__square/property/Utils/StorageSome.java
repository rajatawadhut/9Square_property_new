package com.nine__square.property.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class StorageSome {
    private static StorageSome mInstance;
    private RequestQueue mRequestQueue;
    private static Context mContext;

    private static final String SHARED_NAME = "Some";



    private static final String ID = "id";
    private static final String RELOAD = "reload";
    private static final String CALL = "call";
    private static final String SUBSCRIPTION = "subscription";



    private StorageSome(Context context) {
        // Specify the application context
        mContext = context;
        // Get the request queue
        mRequestQueue = getRequestQueue();
    }

    public static synchronized StorageSome getInstance(Context context) {
        // If Instance is null then initialize new Instance
        if (mInstance == null) {
            mInstance = new StorageSome(context);
        }
        // Return MySingleton new Instance
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        // If RequestQueue is null the initialize new RequestQueue
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }

        // Return RequestQueue
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request) {
        // Add the specified request to the request queue
        getRequestQueue().add(request);
    }


    public boolean setreload(String reload) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(RELOAD, reload);


        editor.apply();
        editor.commit();
        return true;
    }

    public String getreload(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(RELOAD, null);
    }


    public boolean setcall(String call) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(CALL, call);


        editor.apply();
        editor.commit();
        return true;
    }


    public String getcall() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(CALL, null);

    }

    public boolean setsubsription(String result) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SUBSCRIPTION, result);


        editor.apply();
        editor.commit();
        return true;
    }


    public String getsubsription() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(SUBSCRIPTION, null);

    }
}
