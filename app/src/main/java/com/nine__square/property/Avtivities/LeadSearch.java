package com.nine__square.property.Avtivities;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.nine__square.property.Adapter.LeadAdapter;
import com.nine__square.property.Models.LeadDataModel;
import com.nine__square.property.R;
import com.nine__square.property.Utils.Endpoints;
import com.nine__square.property.Utils.SmsData;
import com.nine__square.property.Utils.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeadSearch extends AppCompatActivity {

    RecyclerView  recyclerviewlead;

    private List<LeadDataModel> leadDataModels;
    private LeadAdapter leadAdapter;
    private ProgressDialog progressDialog;
    private ProgressBar progresscustom;
    Boolean isScrolling = false;
    int currentItems, totalItems, scrollOutItems;
    int offset = 0;

    LinearLayoutManager layoutManager;
    String fullname, email, mobile, city, date, time, propertyid, type, category,locality, subscriptionplan;
    String type1, propertylistfor, city1 , type2="";

    LinearLayout nodata;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead_search);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(true);



        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Lead Search");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });



        type1 =  getIntent().getStringExtra("type");
        propertylistfor =  getIntent().getStringExtra("propertylistfor");
        city1 =  getIntent().getStringExtra("city");
        subscriptionplan =  getIntent().getStringExtra("subscriptionplan");


        progresscustom = findViewById(R.id.progresscustom);

        System.out.println("gettttypesss ::: "+ type1);


        recyclerviewlead = findViewById(R.id.recyclerviewlead);
        leadDataModels = new ArrayList<>();


        nodata = findViewById(R.id.nodata);
        nodata.setVisibility(View.GONE);





        layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerviewlead.setLayoutManager(layoutManager);
        leadAdapter = new LeadAdapter(leadDataModels,this);
        recyclerviewlead.setAdapter(leadAdapter);




        recyclerviewlead.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
                {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItems = layoutManager.getChildCount();
                totalItems = layoutManager.getItemCount();
                scrollOutItems = layoutManager.findFirstVisibleItemPosition();

                if(isScrolling && (currentItems + scrollOutItems == totalItems))
                {
                    offset = offset + 10;
                    isScrolling = false;

                    if(getIntent().getStringExtra("propertylistfor").equals("Buyers")){
                        System.out.println("get  Buyers");
                        LatestBuyres();
                    }else{
                        System.out.println("get  Buyers2");
                        LatestProperty();
                    }
                }
            }
        });

        if(propertylistfor.equals("Buyers")){
            LatestBuyres();
        }else{
            LatestProperty();
        }





        if(type1.equals("Residential")){
            type1 = "1";
            type2 = "Residential";
        }else if(type1.equals("Commercial")){
            type1 = "2";
            type2 = "Commercial";
        }else{
            type2 = "";
        }



        if(propertylistfor.equals("Sell")){
            propertylistfor = "1";
        }else if(propertylistfor.equals("Rent")){
            propertylistfor = "2";
        }else{
            propertylistfor = "3";
        }





    }

    private void LatestProperty() {
        if(offset == 0){
            progressDialog.show();
            progresscustom.setVisibility(View.GONE);

        }else{
            progresscustom.setVisibility(View.VISIBLE);
        }
        final String userid;
        if(VolleySingleton.getInstance(getApplicationContext()).isLogin()){
            userid = VolleySingleton.getInstance(getApplicationContext()).id();
        }else{
            userid = "no";
        }


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.getlead, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                progresscustom.setVisibility(View.GONE);
                System.out.println("response :: "+ response);

                try {
                    JSONObject obj = new JSONObject(response);


                    String succes = obj.getString("success");
                    JSONArray jsonArray = obj.getJSONArray("data");
                    if(jsonArray.isNull(0)){
                        nodata.setVisibility(View.VISIBLE);
                    }
                    if(offset > 0){
                        nodata.setVisibility(View.GONE);

                    }

                    if(succes.equals("1")){
                        for(int i=0; i<jsonArray.length();i++){

                            JSONObject object = jsonArray.getJSONObject(i);


                            fullname= object.getString("fullname");
                            email= object.getString("email");
                            mobile= object.getString("mobile");
                            city= object.getString("city");
                            date= object.getString("date");
                            time= object.getString("time");
                            propertyid= object.getString("propertyid");
                            type= object.getString("type");


                            if(type.equals("1")){
                                type = "Residential";
                            }else{
                                type = "Commercial";
                            }


                            category = object.getString("category");

                            if(category.equals("1")){
                                category = "Apartment/Flat/Builder Floor";
                            }
                            else if(category.equals("2")){
                                category = "Residential Land";
                            }
                            else if(category.equals("3")){
                                category = "House/Villa";
                            }
                            else if(category.equals("4")){
                                category = "Offices";
                            }
                            else if(category.equals("5")){
                                category = "Retail";
                            }
                            else if(category.equals("6")){
                                category = "Land";
                            }
                            else if(category.equals("7")){
                                category = "Industry";
                            }
                            else if(category.equals("8")){
                                category = "Storage";
                            }
                            else if(category.equals("9")){
                                category = "Hospitality";
                            }
                            else if(category.equals("10")){
                                category = "Others";
                            }







                            LeadDataModel leadDataModel = new LeadDataModel(fullname,email,mobile,city,date,time,propertyid,type,category, "",subscriptionplan);
                            leadDataModels.add(leadDataModel);
                            leadAdapter.notifyDataSetChanged();
                        }
                    }

                } catch (JSONException e) {
                    progressDialog.dismiss();
                    progresscustom.setVisibility(View.GONE);
                    showmessage("Something wrong please check network connection"+e);
                    System.out.println("problem ::"+e);
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                showmessage(error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                SmsData smsData = new SmsData();
                Map<String, String> params = new HashMap<>();
                params.put("header", smsData.token);
                params.put("userid", userid);
                params.put("propertylistfor", propertylistfor);
                params.put("type", type1);
                params.put("city", city1);
                params.put("offset", String.valueOf(offset));

                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }




    private void LatestBuyres() {



        if(offset == 0){
            progressDialog.show();
            progresscustom.setVisibility(View.GONE);

        }else{
            progresscustom.setVisibility(View.VISIBLE);
        }
        final String userid;
        if(VolleySingleton.getInstance(getApplicationContext()).isLogin()){
            userid = VolleySingleton.getInstance(getApplicationContext()).id();
        }else{
            userid = "no";
        }


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.getleadusersearch, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                progresscustom.setVisibility(View.GONE);
                System.out.println("response :: "+ response);

                try {
                    JSONObject obj = new JSONObject(response);


                    String succes = obj.getString("success");
                    JSONArray jsonArray = obj.getJSONArray("data");
                    if(jsonArray.isNull(0)){
                        nodata.setVisibility(View.VISIBLE);
                    }
                    if(offset > 0){
                        nodata.setVisibility(View.GONE);

                    }

                    if(succes.equals("1")){
                        for(int i=0; i<jsonArray.length();i++){

                            JSONObject object = jsonArray.getJSONObject(i);


                            fullname= object.getString("fullname");
                            email= object.getString("email");
                            mobile= object.getString("mobile");
                            city= object.getString("city");
                            date= object.getString("date");
                            time= object.getString("time");
                            propertyid= object.getString("propertyid");
                            type= object.getString("type");
                            locality= object.getString("locality");


//                            if(type.equals("Residential")){
//                                type = "Residential";
//                            }else{
//                                type = "Commercial";
//                            }


                            category = object.getString("category");

//                            if(category.equals("1")){
//                                category = "Apartment/Flat/Builder Floor";
//                            }
//                            else if(category.equals("2")){
//                                category = "Residential Land";
//                            }
//                            else if(category.equals("3")){
//                                category = "House/Villa";
//                            }
//                            else if(category.equals("4")){
//                                category = "Offices";
//                            }
//                            else if(category.equals("5")){
//                                category = "Retail";
//                            }
//                            else if(category.equals("6")){
//                                category = "Land";
//                            }
//                            else if(category.equals("7")){
//                                category = "Industry";
//                            }
//                            else if(category.equals("8")){
//                                category = "Storage";
//                            }
//                            else if(category.equals("9")){
//                                category = "Hospitality";
//                            }
//                            else if(category.equals("10")){
//                                category = "Others";
//                            }







                            LeadDataModel leadDataModel = new LeadDataModel(fullname,email,mobile,city,date,time,propertyid,type,category, locality,subscriptionplan);
                            leadDataModels.add(leadDataModel);
                            leadAdapter.notifyDataSetChanged();
                        }
                    }

                } catch (JSONException e) {
                    progressDialog.dismiss();
                    progresscustom.setVisibility(View.GONE);
                    showmessage("Something wrong please check network connection"+e);
                    System.out.println("problem ::"+e);
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                showmessage(error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                SmsData smsData = new SmsData();
                Map<String, String> params = new HashMap<>();
                params.put("header", smsData.token);
                params.put("userid", userid);
                params.put("propertylistfor", "Buyers");
                params.put("type", type2);
                params.put("city", city1);
                params.put("offset", String.valueOf(offset));

                System.out.println("response :::; params :; + "+ params);

                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }


    public void showmessage(String m){
        Toast.makeText(this, m, Toast.LENGTH_SHORT).show();
    }


}
