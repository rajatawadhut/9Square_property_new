package com.nine__square.property.Helper;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.nine__square.property.Utils.Endpoints;
import com.nine__square.property.Utils.SmsData;
import com.nine__square.property.Utils.VolleySingleton;

import java.util.HashMap;
import java.util.Map;

public class ShortlistedHelper extends AppCompatActivity {

    final String userid;
    final String propertyid;
    final String shortlistedvalue;

    public ShortlistedHelper(String userid,
                             String propertyid,
                             String shortlistedvalue){

        this.userid = userid;
        this.propertyid = propertyid;
        this.shortlistedvalue = shortlistedvalue;

    }

    public void addshortlisted(){

        System.out.println("getvalueee ::"+shortlistedvalue + "   " +propertyid+ "   " + userid);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.shortlisted, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("response :: "+ response);

//                try {
//                    JSONObject obj = new JSONObject(response);
//
//
//
//                } catch (JSONException e) {
//                    System.out.println("problem ::"+e);
//                    e.printStackTrace();
//                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                SmsData smsData = new SmsData();
                Map<String, String> params = new HashMap<>();
                params.put("header", smsData.token);
                params.put("userid", userid);
                params.put("propertyid", propertyid);
                params.put("shortlistedvalue", shortlistedvalue);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(ShortlistedHelper.this).addToRequestQueue(stringRequest);

    }

    public void deleteshortlisted(){

    }
}
