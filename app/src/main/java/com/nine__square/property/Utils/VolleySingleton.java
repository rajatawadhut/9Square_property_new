package com.nine__square.property.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {
    private static VolleySingleton mInstance;
    private RequestQueue mRequestQueue;
    private static Context mContext;

    private static final String SHARED_NAME = "99acresProfile";



    private static final String ID = "id";
    private static final String FNAME = "fname";
    private static final String LNAME = "lname";
    private static final String MOBILE = "mobile";
    private static final String EMAIL = "email";
    private static final String ADDRESS = "address";
    private static final String CITY = "city";
    private static final String DISTRICT = "district";
    private static final String STATE = "state";
    private static final String PROFILEPIC = "profilepic";
    private static final String DATE = "date";
    private static final String DATE2 = "date2";
    private static final String MSGDATE2 = "MSGDATE2";
    private static final String MSGLIST = "MSGLIST";
    private static final String SETSEARCHCITY = "setsearchcity";
    private static final String SETSEARCHLOCALITY = "setsearchlocality";
    private static final String SETSEARCHSTATE = "setsearchstate";
    private static final String SETSEARCHPROJECT = "searchproject";
    private static final String SETSEARCHADDRESS = "searchaddress";



    private VolleySingleton(Context context) {
        // Specify the application context
        mContext = context;
        // Get the request queue
        mRequestQueue = getRequestQueue();
    }

    public static synchronized VolleySingleton getInstance(Context context) {
        // If Instance is null then initialize new Instance
        if (mInstance == null) {
            mInstance = new VolleySingleton(context);
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


    public boolean user(String id, String fname, String lname, String mobile,
                        String email, String address, String city,
                        String district, String state,
                        String profilepic, String date) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ID, id);
        editor.putString(FNAME, fname);
        editor.putString(LNAME, lname);
        editor.putString(MOBILE, mobile);
        editor.putString(EMAIL, email);
        editor.putString(ADDRESS, address);
        editor.putString(CITY, city);
        editor.putString(DISTRICT, district);
        editor.putString(STATE, state);
        editor.putString(PROFILEPIC, profilepic);
        editor.putString(DATE, date);

        editor.apply();
        editor.commit();
        return true;
    }

    public String id(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(ID, null);
    }


    public String fname(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(FNAME, null);
    }


    public String lname(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(LNAME, null);
    }


    public String mobile(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(MOBILE, null);
    }



    public String email(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(EMAIL, null);
    }
    public String address(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(ADDRESS, null);
    }

    public String city(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(CITY, null);
    }

    public String district(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(DISTRICT, null);
    }

    public String state(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(STATE, null);
    }
    public String profilepic(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(PROFILEPIC, null);
    }


    public boolean isLogin(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        if(sharedPreferences.getString(FNAME, null) == null){
            return false;
        }
        return true;
    }




    public boolean Logout(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        editor.commit();
        return true;
    }


    public boolean setmsglist(String list) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(MSGLIST, list);

        editor.apply();
        editor.commit();
        return true;
    }
    public String getmsglist(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(MSGLIST, null);
    }

    public boolean setcounterdate(String date2) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(DATE2, date2);

        editor.apply();
        editor.commit();
        return true;
    }

    public boolean setmsgcounterdate(String date2) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(MSGDATE2, date2);

        editor.apply();
        editor.commit();
        return true;
    }


    public String getcounterdate(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(DATE2, null);
    }

    public String getmsgcounterdate(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(MSGDATE2, null);
    }

    public boolean setsearchcity(String searchcity) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SETSEARCHCITY, searchcity);

        editor.apply();
        editor.commit();
        return true;
    }


    public String getsearchcity(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(SETSEARCHCITY, null);
    }

    public boolean setsearchstate(String searchstate) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SETSEARCHSTATE, searchstate);

        editor.apply();
        editor.commit();
        return true;
    }


    public String getsearchstate(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(SETSEARCHSTATE, null);
    }


    public boolean setsearchlocality(String searchlocality) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SETSEARCHLOCALITY, searchlocality);

        editor.apply();
        editor.commit();
        return true;
    }


    public String getsearchlocality(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(SETSEARCHLOCALITY, null);
    }


    public boolean setsearchproject(String searchproject) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SETSEARCHPROJECT, searchproject);

        editor.apply();
        editor.commit();
        return true;
    }


    public String getsearchproject(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(SETSEARCHPROJECT, null);
    }


    public boolean setsearchaddress(String searchaddress) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SETSEARCHADDRESS, searchaddress);

        editor.apply();
        editor.commit();
        return true;
    }


    public String getsearchaddress(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(SETSEARCHADDRESS, null);
    }




}
