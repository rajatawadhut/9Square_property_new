package com.nine__square.property.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class StorageProperty {

    private Context mContext;



    private final String SHARED_NAME = "PostProperty";


    //Bank
    private final String ID = "id";
    private final String FIRMNAME = "firmname";
    private final String FNAME = "fname";
    private final String LNAME = "lname";
    private final String MOBILE = "mobile";



    public boolean user(String id) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ID, id);

        editor.apply();
        editor.commit();
        return true;
    }
    public String id(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(ID, null);
    }

}
