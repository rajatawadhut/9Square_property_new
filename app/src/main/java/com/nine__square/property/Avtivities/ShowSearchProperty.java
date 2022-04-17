package com.nine__square.property.Avtivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
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
import com.nine__square.property.Adapter.LatestAdapter;
import com.nine__square.property.Models.LatestDataModel;
import com.nine__square.property.R;
import com.nine__square.property.Utils.Endpoints;
import com.nine__square.property.Utils.SmsData;
import com.nine__square.property.Utils.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowSearchProperty extends AppCompatActivity {

    String listfor1 = "", getcity1 = "", type1 = "", category1 = "", min = "", max = "", sharing = "", usertype1 = "", getstate1 = "", getlocality1 = "", getproject1 = "";
    RecyclerView recyclerviewsearch;

    private List<LatestDataModel> latestDataModels;
    private LatestAdapter latestAdapter;
    private ProgressDialog progressDialog;
    LinearLayout nodata;



    private ProgressBar progresscustom;
    Boolean isScrolling = false;
    int currentItems, totalItems, scrollOutItems;
    int offset = 0;

    LinearLayoutManager layoutManager;

    ArrayList<String> willing1 = new ArrayList<>();
    ArrayList<String> pgsuitablefor1 = new ArrayList<>();
    ArrayList<String> otherroom1 = new ArrayList<>();
    ArrayList<String> homethings1 = new ArrayList<>();
    ArrayList<String> facilityincluded1 = new ArrayList<>();
    ArrayList<String> weekdays1 = new ArrayList<>();
    ArrayList<String> weekend1 = new ArrayList<>();
    ArrayList<String> anemitiesitem1 = new ArrayList<>();
    ArrayList<String> moreanemitiesitem1 = new ArrayList<>();
    ArrayList<String> watersourceitem1 = new ArrayList<>();
    ArrayList<String> overlookingitem1 = new ArrayList<>();
    ArrayList<String> somefeatureitem1 = new ArrayList<>();
    ArrayList<String> byersitem1 = new ArrayList<>();
    ArrayList<String> timeitems1 = new ArrayList<>();


    ArrayList<String> moreanemitiesitem2 = new ArrayList<>();
    ArrayList<String> moreanemitiesitem3;
    ArrayList<String> homethings2 = new ArrayList<>();
    ArrayList<String> homethings3;










    String id = "",usertype = "", type = "", category = "", categorytype = "", agrement = "", pgavaliablefor = "", pgshareprivate = "",
            sharingspinnumber = "", propertylistfor = "", willing = "", pgsuitablefor = "",state= "", city = "", locality = "", projectname = "", address = "",
            unit1 = "",unit2 = "",unit3 = "",availabilitydate = "", otherroom = "", homethings = "", builtupa = "", carpeta = "", plota = "", bed = "",
            bath = "", balconie = "", totalfloorget = "", reservedpark = "", availabilityspinget = "", ageofpropertyspinget = "",
            furnishedspinget = "",possessionbyspinget = "",
            noofroomspinget = "", qualityratingspinget = "", floorallowedspinget = "", roomavailabespinget = "", priceget = "", maintenanceget = "",
            bookingamountget = "",
            durationofrentagget = "", monthofnoticeget  = "", securitydepositspinget = "", maintenancemonthlyspinget = "",
            ownweshipspinget = "",
            typeoffood = "", pricestep = "", facilityincluded = "", weekdays = "", weeends = "", earlylivingchargesget = "", contractdurationget = "",
            annualduespayableget = "",
            depositeaget = "", facingspinget = "", unitspinget = "", typeofflooringspinget = "", powerbackupspinget = "", lasttimespinget = "",
            pet = "", visiter = "", smoking = "",
            alcohol = "", event = "", anemitiesitem = "", moreanemitiesitem = "", watersourceitem = "", overlookingitem = "",
            somefeatureitem = "", byersitem = "", timeitems = "",
            widthfacingget = "", descriptionget = "", boundrywall = "",flattype = "", user_id = "", newprice ="", image = "",
            shortlistedvalue="", getrera="", mobile= "", reg_date = "", fname = "", lname = "", latlong = "", email="";






    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_search_property);


        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Search Property");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });






        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(true);


        nodata = findViewById(R.id.nodata);
        nodata.setVisibility(View.GONE);

        progresscustom = findViewById(R.id.progresscustom);



        listfor1 =  getIntent().getStringExtra("listfor");
        getcity1 =  getIntent().getStringExtra("getcity");
        sharing =  getIntent().getStringExtra("sharing");
        type1 =  getIntent().getStringExtra("type");
        category1 =  getIntent().getStringExtra("category");
        min =  getIntent().getStringExtra("minprice");
        max =  getIntent().getStringExtra("maxprice");
        usertype1 =  getIntent().getStringExtra("usertype");
        getstate1 =  getIntent().getStringExtra("state");
        getlocality1 =  getIntent().getStringExtra("locality");
        getproject1 =  getIntent().getStringExtra("project");




//        System.out.println("getdetails :: "+ " listing for ::"+listfor1+"  city:: "+ getcity1+" sharing ::"+ sharing+" type :;"+ type1+" category :;"+ category1+" Min :;"+ min+" max ::"+ max+" usertype ::"+ usertype1);



        recyclerviewsearch = findViewById(R.id.recyclerviewsearch);
        latestDataModels = new ArrayList<>();


        layoutManager = new GridLayoutManager(this, 2);
        recyclerviewsearch.setLayoutManager(layoutManager);
        latestAdapter = new LatestAdapter(latestDataModels,this);
        recyclerviewsearch.setAdapter(latestAdapter);

        recyclerviewsearch.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
//                    showsearch();
                }
            }
        });
        showsearch();




//        String listfor1, getcity1, type1, category1, min, max, sharing, usertype1;
        if(listfor1.equals("Sell")){
            listfor1 = "1";
        }else if(listfor1.equals("Rent")){
            listfor1 = "2";
        }else if(listfor1.equals("Paying Guest")){
            listfor1 = "3";
        }



        if(type1.equals("Residential")){
            type1 = "1";
        }
        else if(type1.equals("Commercial")){
            type1 = "2";
        }

        if(category1.equals("Apartment/Flat/Builder Floor")){
            category1 = "1";
        }
        else if(category1.equals("Residential Land")){
            category1 = "2";
        }
        else if(category1.equals("House/Villa")){
            category1 = "3";
        }
        else if(category1.equals("Offices")){
            category1 = "4";
        }
        else if(category1.equals("Retail")){
            category1 = "5";
        }
        else if(category1.equals("Land")){
            category1 = "6";
        }
        else if(category1.equals("Industry")){
            category1 = "7";
        }
        else if(category1.equals("Storage")){
            category1 = "8";
        }
        else if(category1.equals("Hospitality")){
            category1 = "9";
        }
        else if(category1.equals("Others")){
            category1 = "10";
        }


        if(usertype1.equals("Owner")){
            usertype1 = "1";
        }else if (usertype1.equals("Dealer")){
            usertype1 = "2";
        }
        else if (usertype1.equals("Builder")){
            usertype1 = "3";
        }


        if(sharing.equals("Private")){
            sharing = "1";
        }
        else if(sharing.equals("Sharing")){
            sharing = "2";
        }







    }

    private void addUserSearchData(final String userid, final String listfor1, final String getcity1, final String type1, final String category1, final String min, final String max, final String sharing, final String usertype1, final String getstate1, final String getlocality1, final String getproject1, final String name, final String mobile, final String email, final String status) {
            final StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.addUserSearchData, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    System.out.println("response123 ::::: "+ response);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
//                    Toast.makeText(ShowSearchProperty.this,"Something Went Wrong",Toast.LENGTH_SHORT).show();
                    System.out.println("response321 ::::: "+ error.getMessage().toString());

                    Log.d("VOLLEY", String.valueOf(error));
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    SmsData smsData = new SmsData();
                Map<String, String> params = new HashMap<>();
                params.put("header", smsData.token);
//                    String userid, String listfor1, String getcity1, String type1,
//                            String category1, String min, String max, String sharing, String usertype1,
//                            String getstate1, String getlocality1, String getproject1, String name,
//                            String mobile, String email) {

                    params.put("userid", userid);
                    params.put("listfor", listfor1);
                    params.put("minprice", min);
                    params.put("maxprice", max);
                    params.put("type", type1);
                    params.put("category", category1);
                    params.put("city", getcity1);
                    params.put("sharing", sharing);
                    params.put("usertype", usertype1);
                    params.put("state", getstate1);
                    params.put("locality", getlocality1);
                    params.put("project", getproject1);
                    params.put("name", name);
                    params.put("mobile", mobile);
                    params.put("email", email);
                    params.put("status", status);
                    return params;
                }
            };
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }

    private void showsearch() {
        if(offset == 0){
            progressDialog.show();
            progresscustom.setVisibility(View.GONE);

        }else{
            progresscustom.setVisibility(View.VISIBLE);
        }
        final String userid;
        if(VolleySingleton.getInstance(getApplicationContext()).isLogin()){
            userid =  VolleySingleton.getInstance(getApplicationContext()).id();
        }else{
            userid = "no";
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.searchproperty, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                System.out.println("response ::::: "+ response);
                progressDialog.dismiss();
                progresscustom.setVisibility(View.GONE);

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

                    if(VolleySingleton.getInstance(getApplicationContext()).isLogin()){
                        String status = String.valueOf(jsonArray.length());

                        String userid, name, mobile, email;
                        userid = VolleySingleton.getInstance(getApplicationContext()).id();
                        name = VolleySingleton.getInstance(getApplicationContext()).fname()+ " "+ VolleySingleton.getInstance(getApplicationContext()).lname();
                        mobile = VolleySingleton.getInstance(getApplicationContext()).mobile();
                        email = VolleySingleton.getInstance(getApplicationContext()).email();

                        if(type1.equals("1")){
                            type1 = "Residential";
                        }else if(type1.equals("2")){
                            type1 = "Commercial";
                        }



                        if(listfor1.equals("1")){
                            listfor1 = "Sell";
                        }else if(listfor1.equals("2")){
                            listfor1 = "Rent";
                        }else if(listfor1.equals("3")){
                            listfor1 = "Paying Guest";
                        }


                        if(usertype1.equals("1")){
                            usertype1 = "Owner";
                        }else if (usertype1.equals("2")){
                            usertype1 = "Dealer";
                        }
                        else if (usertype1.equals("3")){
                            usertype1 = "Builder";
                        }

                        if(category1.equals("1")){
                            category1 = "Apartment/Flat/Builder Floor";
                        }
                        else if(category1.equals("2")){
                            category1 = "Residential Land";
                        }
                        else if(category1.equals("3")){
                            category1 = "House/Villa";
                        }
                        else if(category1.equals("4")){
                            category1 = "Offices";
                        }
                        else if(category1.equals("5")){
                            category1 = "Retail";
                        }
                        else if(category1.equals("6")){
                            category1 = "Land";
                        }
                        else if(category1.equals("7")){
                            category1 = "Industry";
                        }
                        else if(category1.equals("8")){
                            category1 = "Storage";
                        }
                        else if(category1.equals("9")){
                            category1 = "Hospitality";
                        }
                        else if(category1.equals("10")){
                            category1 = "Others";
                        }


                        if(sharing.equals("1")){
                            sharing = "Private";
                        }
                        else if(sharing.equals("2")){
                            sharing = "Sharing";
                        }





                        addUserSearchData(userid, listfor1, getcity1, type1, category1, min, max, sharing, usertype1, getstate1, getlocality1, getproject1, name, mobile, email, status);
                    }




                    if(succes.equals("1")){
                        for(int i=0; i<jsonArray.length();i++){


                            willing1.clear();
                            pgsuitablefor1.clear();
                            otherroom1.clear();
                            homethings1.clear();
                            facilityincluded1.clear();
                            weekdays1.clear();
                            weekend1.clear();
                            anemitiesitem1.clear();
                            moreanemitiesitem1.clear();
                            watersourceitem1.clear();
                            overlookingitem1.clear();
                            somefeatureitem1.clear();
                            byersitem1.clear();
                            timeitems1.clear();
                            moreanemitiesitem2.clear();
                            homethings2.clear();



                            JSONObject object = jsonArray.getJSONObject(i);
                            id =object.getString("id");
                            //usertype
                            usertype = object.getString("usertype");

                            if(usertype.equals("1")){
                                usertype = "Owner";
                            }else if (usertype.equals("2")){
                                usertype = "Dealer";
                            }
                            else if (usertype.equals("3")){
                                usertype = "Builder";
                            }



                            // type
                            type = object.getString("type");

                            if(type.equals("1")){
                                type = "Residential";
                            }else if(type.equals("2")){
                                type = "Commercial";
                            }


                            //category
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



                            //categorytype
                            categorytype = object.getString("categorytype");


                            if (categorytype.equals("1")){
                                categorytype = "Residential Apartment";
                            }

                            else if (categorytype.equals("2")){
                                categorytype = "Independent/Builder Floor";
                            }
                            else if (categorytype.equals("3")){
                                categorytype = "Studio Apartment";
                            }
                            else if (categorytype.equals("4")){
                                categorytype = "Serviced Apartments";
                            }
                            else if (categorytype.equals("5")){
                                categorytype = "Independent House/Villa";
                            }
                            else if (categorytype.equals("6")){
                                categorytype = "Farm House";
                            }
                            else if (categorytype.equals("7")){
                                categorytype = "Ready to move office space";
                            }
                            else if (categorytype.equals("8")){
                                categorytype = "Bare shell office space";
                            }
                            else if (categorytype.equals("9")){
                                categorytype = "Co-working office space";
                            }
                            else if (categorytype.equals("10")){
                                categorytype = "Commercial Shops";
                            }
                            else if (categorytype.equals("11")){
                                categorytype = "Commercial Showrooms";
                            }
                            else if (categorytype.equals("12")){
                                categorytype = "Space in Retail Mall";
                            }
                            else if (categorytype.equals("13")){
                                categorytype = "Commercial Land/Inst. Land";
                            }
                            else if (categorytype.equals("14")){
                                categorytype = "Agricultural/Farm Land";
                            }
                            else if (categorytype.equals("15")){
                                categorytype = "Industrial Lands/Plots";
                            }
                            else if (categorytype.equals("16")){
                                categorytype = "Factory";
                            }
                            else if (categorytype.equals("17")){
                                categorytype = "Manufacturing";
                            }
                            else if (categorytype.equals("18")){
                                categorytype = "Ware House";
                            }
                            else if (categorytype.equals("19")){
                                categorytype = "Cold Storage";
                            }
                            else if (categorytype.equals("20")){
                                categorytype = "Hotel/Resorts";
                            }
                            else if (categorytype.equals("21")){
                                categorytype = "Guest-House/Banquet-Hallsage";
                            }




                            //agrement
                            agrement = object.getString("agrement");

                            if(agrement.equals("1")){
                                agrement = "Company Lease Agreement";
                            }else{
                                agrement = "Any";
                            }



                            //pgavailablefor
                            pgavaliablefor = object.getString("pgavaliablefor");

                            if(pgavaliablefor.equals("1")){
                                pgavaliablefor = "Girls";
                            }else if (pgavaliablefor.equals("2")){
                                pgavaliablefor ="Boys";
                            }else{
                                pgavaliablefor = "Any";
                            }




                            //pgshareprivate
                            pgshareprivate = object.getString("pgshareprivate");

                            if (pgshareprivate.equals("1")){
                                pgshareprivate = "Private";
                            }else {
                                pgshareprivate = "Sharing";
                            }




                            //sharingnumber
                            sharingspinnumber = object.getString("sharingspinnumber");

                            //propertylistfor
                            propertylistfor = object.getString("propertylistfor");

                            if(propertylistfor.equals("1")){
                                propertylistfor = "Sell";
                            }else if(propertylistfor.equals("2")){
                                propertylistfor = "Rent";
                            }else{
                                propertylistfor = "Paying Guest";
                            }

                            // willing
                            willing = object.getString("willing");

                            if(willing.contains("1")){
                                willing1.add("Family");
                            }
                            if (willing.contains("2")){
                                willing1.add("Single Men");
                            }
                            if(willing.contains("3")){
                                willing1.add("Single Women");
                            }

                            willing = String.valueOf(willing1);


                            //pgsuitablefor
                            pgsuitablefor = object.getString("pgsuitablefor");

                            if(pgsuitablefor.contains("1")){
                                pgsuitablefor1.add("Students");
                            }
                            if(pgsuitablefor.contains("2")){
                                pgsuitablefor1.add("Working Professionals");
                            }

                            pgsuitablefor = String.valueOf(pgsuitablefor1);

                            state = object.getString("state");
                            city = object.getString("city");
                            locality = object.getString("locality");
                            projectname = object.getString("projectname");
                            address = object.getString("address");


                            //unit1
                            unit1 = object.getString("unit1");

                            if(unit1.equals("1")){
                                unit1 = "Sq.Ft";
                            }
                            else if(unit1.equals("2")){
                                unit1 = "Sq.yards";
                            }else if(unit1.equals("3")){
                                unit1 = "Sq.m";
                            }
                            else if(unit1.equals("4")){
                                unit1 = "Acres";
                            }
                            else if(unit1.equals("5")){
                                unit1 = "Marla";
                            }else if(unit1.equals("6")){
                                unit1 = "Cents";
                            }
                            else if(unit1.equals("7")){
                                unit1 = "Bigha";
                            }else if(unit1.equals("8")){
                                unit1 = "Kottah";
                            }
                            else if(unit1.equals("9")){
                                unit1 = "Grounds";
                            }
                            else if(unit1.equals("10")){
                                unit1 = "Ares";
                            }
                            else if(unit1.equals("11")){
                                unit1 = "Biswa";
                            }
                            else if(unit1.equals("12")){
                                unit1 = "Guntha";
                            }
                            else if(unit1.equals("13")){
                                unit1 = "Aankadam";
                            }

                            else if(unit1.equals("14")){
                                unit1 = "Hectares";
                            }

                            else if(unit1.equals("15")){
                                unit1 = "Rood";
                            }

                            else if(unit1.equals("16")){
                                unit1 = "Chataks";
                            }

                            else if(unit1.equals("17")){
                                unit1 = "Perch";
                            }


                            //unit2
                            unit2 = object.getString("unit2");

                            if(unit2.equals("1")){
                                unit2 = "Sq.Ft";
                            }
                            else if(unit2.equals("2")){
                                unit2 = "Sq.yards";
                            }else if(unit2.equals("3")){
                                unit2 = "Sq.m";
                            }
                            else if(unit2.equals("4")){
                                unit2 = "Acres";
                            }
                            else if(unit2.equals("5")){
                                unit2 = "Marla";
                            }else if(unit2.equals("6")){
                                unit2 = "Cents";
                            }
                            else if(unit2.equals("7")){
                                unit2 = "Bigha";
                            }else if(unit2.equals("8")){
                                unit2 = "Kottah";
                            }
                            else if(unit2.equals("9")){
                                unit2 = "Grounds";
                            }
                            else if(unit2.equals("10")){
                                unit2 = "Ares";
                            }
                            else if(unit2.equals("11")){
                                unit2 = "Biswa";
                            }
                            else if(unit2.equals("12")){
                                unit2 = "Guntha";
                            }
                            else if(unit2.equals("13")){
                                unit2 = "Aankadam";
                            }

                            else if(unit2.equals("14")){
                                unit2 = "Hectares";
                            }

                            else if(unit2.equals("15")){
                                unit2 = "Rood";
                            }

                            else if(unit2.equals("16")){
                                unit2 = "Chataks";
                            }

                            else if(unit2.equals("17")){
                                unit2 = "Perch";
                            }



                            //unit3
                            unit3 = object.getString("unit3");

                            if(unit3.equals("1")){
                                unit3 = "Sq.Ft";
                            }
                            else if(unit3.equals("2")){
                                unit3 = "Sq.yards";
                            }else if(unit3.equals("3")){
                                unit1 = "Sq.m";
                            }
                            else if(unit3.equals("4")){
                                unit3 = "Acres";
                            }
                            else if(unit3.equals("5")){
                                unit3 = "Marla";
                            }else if(unit3.equals("6")){
                                unit3 = "Cents";
                            }
                            else if(unit3.equals("7")){
                                unit3 = "Bigha";
                            }else if(unit3.equals("8")){
                                unit3 = "Kottah";
                            }
                            else if(unit3.equals("9")){
                                unit3 = "Grounds";
                            }
                            else if(unit3.equals("10")){
                                unit3 = "Ares";
                            }
                            else if(unit3.equals("11")){
                                unit3 = "Biswa";
                            }
                            else if(unit3.equals("12")){
                                unit3 = "Guntha";
                            }
                            else if(unit3.equals("13")){
                                unit3 = "Aankadam";
                            }

                            else if(unit3.equals("14")){
                                unit3 = "Hectares";
                            }

                            else if(unit3.equals("15")){
                                unit3 = "Rood";
                            }

                            else if(unit3.equals("16")){
                                unit3 = "Chataks";
                            }

                            else if(unit3.equals("17")){
                                unit3 = "Perch";
                            }




                            availabilitydate = object.getString("availabilitydate");

                            //otherroom
                            otherroom = object.getString("otherroom");

                            if(otherroom.contains("1")){
                                otherroom1.add("Pooja Room");
                            }
                            if(otherroom.contains("2")){
                                otherroom1.add("Study Room");
                            }

                            if(otherroom.contains("3")){
                                otherroom1.add("Servant Room");
                            }

                            if(otherroom.contains("4")){
                                otherroom1.add("Others");
                            }

                            otherroom = String.valueOf(otherroom1);




                            //homethings
                            homethings = object.getString("homethings");


                            homethings = homethings.toString().replace("[", "").replace("]", "");
                            String result123 = homethings.replaceAll("\\s", "");

                            homethings3 = new ArrayList<String>(Arrays.asList(result123.split(",")));


                            homethings2.addAll(homethings3);


                            if(homethings2.contains("1")){
                                homethings1.add("Wardrobe");
                            }


                            if(homethings2.contains("2")){
                                homethings1.add("Modular Kitchen");
                            }


                            if(homethings2.contains("3")){
                                homethings1.add("Fridge");
                            }


                            if(homethings2.contains("4")){
                                homethings1.add("Ac");
                            }


                            if(homethings2.contains("5")){
                                homethings1.add("Stove");
                            }


                            if(homethings2.contains("6")){
                                homethings1.add("Geyser");
                            }


                            if(homethings2.contains("7")){
                                homethings1.add("Dinning Table");
                            }

                            if(homethings2.contains("8")){
                                homethings1.add("Sofa");
                            }


                            if(homethings2.contains("9")){
                                homethings1.add("Washing Machine");
                            }


                            if(homethings2.contains("10")){
                                homethings1.add("Water Purifier");
                            }


                            if(homethings2.contains("11")){
                                homethings1.add("Microwave");
                            }


                            if(homethings2.contains("12")){
                                homethings1.add("Curtains");
                            }

                            if(homethings2.contains("13")){
                                homethings1.add("Chimney");
                            }

                            if(homethings2.contains("14")){
                                homethings1.add("Exhaust Fan");
                            }



                            homethings = String.valueOf(homethings1);





                            builtupa = object.getString("builtupa");
                            carpeta = object.getString("carpeta");
                            plota = object.getString("plota");
                            bed = object.getString("bed");
                            bath = object.getString("bath");
                            balconie = object.getString("balconie");
                            totalfloorget = object.getString("totalfloorget");


                            //reservvedpark
                            reservedpark = object.getString("reservedpark");
                            if(reservedpark.equals("1")){
                                reservedpark = "Yes";
                            }else{
                                reservedpark = "No";
                            }


                            // availability
                            availabilityspinget = object.getString("availabilityspinget");

                            if(availabilityspinget.equals("1")){
                                availabilityspinget = "Under Construction";
                            }
                            else if(availabilityspinget.equals("2")){
                                availabilityspinget = "Ready To Move";

                            }


                            //ageofproperty
                            ageofpropertyspinget = object.getString("ageofpropertyspinget");
                            if(ageofpropertyspinget.equals("1")){
                                ageofpropertyspinget = "0-1 Year Old Property";
                            }
                            else if(ageofpropertyspinget.equals("2")){
                                ageofpropertyspinget = "1-5 Year Old Property";
                            }
                            else if(ageofpropertyspinget.equals("3")){
                                ageofpropertyspinget = "5-10 Year Old Property";
                            }
                            else if(ageofpropertyspinget.equals("4")){
                                ageofpropertyspinget = "10+ Year Old Property";
                            }
                            else if(ageofpropertyspinget.equals("5")){
                                ageofpropertyspinget = "Ready To Move";
                            }



                            //furnished
                            furnishedspinget = object.getString("furnishedspinget");
                            if(furnishedspinget.equals("1")){
                                furnishedspinget = "Furnished";
                            }
                            else if(furnishedspinget.equals("2")){
                                furnishedspinget = "Semifurnished";
                            }
                            else if(furnishedspinget.equals("3")){
                                furnishedspinget = "Unfurnished";
                            }



                            //possessionby
                            possessionbyspinget = object.getString("possessionbyspinget");

                            if(possessionbyspinget.equals("1")){
                                possessionbyspinget = "Within 3 Months";
                            }
                            else if(possessionbyspinget.equals("2")){
                                possessionbyspinget = "By 2021";
                            }
                            else if(possessionbyspinget.equals("3")){
                                possessionbyspinget = "By 2022";
                            }
                            else if(possessionbyspinget.equals("4")){
                                possessionbyspinget = "By 2023";
                            }
                            else if(possessionbyspinget.equals("5")){
                                possessionbyspinget = "By 2024";
                            }
                            else if(possessionbyspinget.equals("6")){
                                possessionbyspinget = "By 2025";
                            }
                            else if(possessionbyspinget.equals("7")){
                                possessionbyspinget = "By 2026";
                            }
                            else if(possessionbyspinget.equals("8")){
                                possessionbyspinget = "By 2027";
                            }
                            else if(possessionbyspinget.equals("9")){
                                possessionbyspinget = "By 2028";
                            }
                            else if(possessionbyspinget.equals("10")){
                                possessionbyspinget = "By 2029";
                            }
                            else if(possessionbyspinget.equals("11")){
                                possessionbyspinget = "By 2030";
                            }
                            else if(possessionbyspinget.equals("12")){
                                possessionbyspinget = "By 2031";
                            }



                            noofroomspinget = object.getString("noofroomspinget");


                            //qualityrating
                            qualityratingspinget = object.getString("qualityratingspinget");

                            if(qualityratingspinget.equals("1")){
                                qualityratingspinget = "1 Star";
                            }
                            else if(qualityratingspinget.equals("2")){
                                qualityratingspinget = "2 Star";
                            }
                            else if(qualityratingspinget.equals("3")){
                                qualityratingspinget = "3 Star";
                            }
                            else if(qualityratingspinget.equals("4")){
                                qualityratingspinget = "4 Star";
                            }
                            else if(qualityratingspinget.equals("5")){
                                qualityratingspinget = "5 Star";
                            }
                            else if(qualityratingspinget.equals("6")){
                                qualityratingspinget = "6 Star";
                            }
                            else if(qualityratingspinget.equals("7")){
                                qualityratingspinget = "7 Star";
                            }
                            else if(qualityratingspinget.equals("8")){
                                qualityratingspinget = "8 Star";
                            }
                            else if(qualityratingspinget.equals("9")){
                                qualityratingspinget = "9 Star";
                            }
                            else if(qualityratingspinget.equals("10")){
                                qualityratingspinget = "10 Star";
                            }

                            floorallowedspinget = object.getString("floorallowedspinget");
                            roomavailabespinget = object.getString("roomavailabespinget");
                            priceget = object.getString("priceget");
                            maintenanceget = object.getString("maintenanceget");
                            bookingamountget = object.getString("bookingamountget");
                            durationofrentagget = object.getString("durationofrentagget");
                            monthofnoticeget = object.getString("monthofnoticeget");


                            // securitydeposite
                            securitydepositspinget = object.getString("securitydepositspinget");


                            if(securitydepositspinget.equals("1")){
                                securitydepositspinget = "Fixed";
                            }
                            else if(securitydepositspinget.equals("2")){
                                securitydepositspinget = "Multiple Of Rent";
                            }



                            //maintenancemonthly
                            maintenancemonthlyspinget = object.getString("maintenancemonthlyspinget");

                            if(maintenancemonthlyspinget.equals("1")){
                                maintenancemonthlyspinget = "Monthly";
                            }
                            else if(maintenancemonthlyspinget.equals("2")){
                                maintenancemonthlyspinget = "Annually";
                            }
                            else if(maintenancemonthlyspinget.equals("3")){
                                maintenancemonthlyspinget = "One Time";
                            }
                            else if(maintenancemonthlyspinget.equals("4")){
                                maintenancemonthlyspinget = "Per Unit/Monthly";
                            }



                            //ownership
                            ownweshipspinget = object.getString("ownweshipspinget");

                            if(ownweshipspinget.equals("1")){
                                ownweshipspinget = "Freehold";
                            }
                            else if(ownweshipspinget.equals("2")){
                                ownweshipspinget = "Leasehold";
                            }
                            else if(ownweshipspinget.equals("3")){
                                ownweshipspinget = "Co-Operative Society";
                            }
                            else if(ownweshipspinget.equals("4")){
                                ownweshipspinget = "Power Of Attorney";
                            }


                            //typeoffood
                            typeoffood = object.getString("typeoffood");

                            if(typeoffood.equals("1")){
                                typeoffood = "Veg";
                            }else{
                                typeoffood = "Ved and Non-Veg";
                            }



                            //pricestep
                            pricestep = object.getString("pricestep");

                            if(pricestep.equals("1")){
                                pricestep = "Price Negotiable";
                            }else{
                                pricestep = "Electricity And Water Charges Excluded";
                            }




                            //facilityinculded
                            facilityincluded = object.getString("facilityincluded");

                            if(facilityincluded.contains("1")){
                                facilityincluded1.add("Food");
                            }

                            if(facilityincluded.contains("2")){
                                facilityincluded1.add("Laundry");
                            }

                            if(facilityincluded.contains("3")){
                                facilityincluded1.add("Fridge");
                            }

                            if(facilityincluded.contains("4")){
                                facilityincluded1.add("Electricity");
                            }

                            if(facilityincluded.contains("5")){
                                facilityincluded1.add("None Of the Above");
                            }

                            if(facilityincluded.contains("6")){
                                facilityincluded1.add("HouseKeeping");
                            }

                            if(facilityincluded.contains("7")){
                                facilityincluded1.add("DTH");
                            }

                            if(facilityincluded.contains("8")){
                                facilityincluded1.add("Water");
                            }

                            if(facilityincluded.contains("9")){
                                facilityincluded1.add("Wifi");
                            }

                            facilityincluded = String.valueOf(facilityincluded1);





                            //weekdays
                            weekdays = object.getString("weekdays");

                            if(weekdays.contains("1")){
                                weekdays1.add("Breakfast");
                            }
                            if(weekdays.contains("2")){
                                weekdays1.add("Lunch");
                            }
                            if(weekdays.contains("3")){
                                weekdays1.add("Dinner");
                            }

                            weekdays = String.valueOf(weekdays1);



                            //weekends
                            weeends = object.getString("weeends");

                            if(weeends.contains("1")){
                                weekend1.add("Breakfast");
                            }
                            if(weeends.contains("2")){
                                weekend1.add("Lunch");
                            }
                            if(weeends.contains("3")){
                                weekend1.add("Dinner");
                            }

                            weeends = String.valueOf(weekend1);


                            earlylivingchargesget = object.getString("earlylivingchargesget");
                            contractdurationget = object.getString("contractdurationget");
                            annualduespayableget = object.getString("annualduespayableget");
                            depositeaget = object.getString("depositeaget");


                            //facing
                            facingspinget = object.getString("facingspinget");

                            if(facingspinget.equals("1")){
                                facingspinget = "East";
                            }
                            else if(facingspinget.equals("2")){
                                facingspinget = "North-East";
                            }
                            else if(facingspinget.equals("3")){
                                facingspinget = "North";
                            }
                            else if(facingspinget.equals("4")){
                                facingspinget = "West";
                            }
                            else if(facingspinget.equals("5")){
                                facingspinget = "South";
                            }
                            else if(facingspinget.equals("6")){
                                facingspinget = "South-East";
                            }
                            else if(facingspinget.equals("7")){
                                facingspinget = "North-West";
                            }
                            else if(facingspinget.equals("8")){
                                facingspinget = "South-West";
                            }




                            //unitspinget
                            unitspinget = object.getString("unitspinget");

                            if(unitspinget.equals("1")){
                                unitspinget = "Sq.Ft";
                            }
                            else if(unitspinget.equals("2")){
                                unitspinget = "Sq.yards";
                            }else if(unitspinget.equals("3")){
                                unitspinget = "Sq.m";
                            }
                            else if(unitspinget.equals("4")){
                                unitspinget = "Acres";
                            }
                            else if(unitspinget.equals("5")){
                                unitspinget = "Marla";
                            }else if(unitspinget.equals("6")){
                                unitspinget = "Cents";
                            }
                            else if(unitspinget.equals("7")){
                                unitspinget = "Bigha";
                            }else if(unitspinget.equals("8")){
                                unitspinget = "Kottah";
                            }
                            else if(unitspinget.equals("9")){
                                unitspinget = "Grounds";
                            }
                            else if(unitspinget.equals("10")){
                                unitspinget = "Ares";
                            }
                            else if(unitspinget.equals("11")){
                                unitspinget = "Biswa";
                            }
                            else if(unitspinget.equals("12")){
                                unitspinget = "Guntha";
                            }
                            else if(unitspinget.equals("13")){
                                unitspinget = "Aankadam";
                            }

                            else if(unitspinget.equals("14")){
                                unitspinget = "Hectares";
                            }

                            else if(unitspinget.equals("15")){
                                unitspinget = "Rood";
                            }

                            else if(unitspinget.equals("16")){
                                unitspinget = "Chataks";
                            }

                            else if(unitspinget.equals("17")){
                                unitspinget = "Perch";
                            }





                            //typeofflooring
                            typeofflooringspinget = object.getString("typeofflooringspinget");

                            if(typeofflooringspinget.equals("1")){
                                typeofflooringspinget = "Vitrified";
                            }
                            else if(typeofflooringspinget.equals("2")){
                                typeofflooringspinget = "Marble";
                            }
                            else if(typeofflooringspinget.equals("3")){
                                typeofflooringspinget = "Concrete";
                            }
                            else if(typeofflooringspinget.equals("4")){
                                typeofflooringspinget = "Ceramic";
                            }
                            else if(typeofflooringspinget.equals("5")){
                                typeofflooringspinget = "Polished Concrete";
                            }
                            else if(typeofflooringspinget.equals("6")){
                                typeofflooringspinget = "Mosaic";
                            }
                            else if(typeofflooringspinget.equals("7")){
                                typeofflooringspinget = "Wood";
                            }
                            else if(typeofflooringspinget.equals("8")){
                                typeofflooringspinget = "Granite";
                            }
                            else if(typeofflooringspinget.equals("9")){
                                typeofflooringspinget = "Spartex";
                            }
                            else if(typeofflooringspinget.equals("10")){
                                typeofflooringspinget = "Cement";
                            }
                            else if(typeofflooringspinget.equals("11")){
                                typeofflooringspinget = "Stone";
                            }
                            else if(typeofflooringspinget.equals("12")){
                                typeofflooringspinget = "Vinyl";
                            }
                            else if(typeofflooringspinget.equals("13")){
                                typeofflooringspinget = "IPSFinish";
                            }
                            else if(typeofflooringspinget.equals("14")){
                                typeofflooringspinget = "Others";
                            }


                            //powerbackup
                            powerbackupspinget = object.getString("powerbackupspinget");

                            if(powerbackupspinget.equals("1")){
                                powerbackupspinget = "None";
                            }
                            else if(powerbackupspinget.equals("2")){
                                powerbackupspinget = "Partial";
                            }
                            else if(powerbackupspinget.equals("3")){
                                powerbackupspinget = "Full";
                            }




                            //lasttime
                            lasttimespinget = object.getString("lasttimespinget");

                            if(lasttimespinget.contains("1")){
                                lasttimespinget = "7 PM";
                            }
                            if(lasttimespinget.contains("2")){
                                lasttimespinget = "8 PM";
                            }
                            if(lasttimespinget.contains("3")){
                                lasttimespinget = "9 PM";
                            }
                            if(lasttimespinget.contains("4")){
                                lasttimespinget = "10 PM";
                            }
                            if(lasttimespinget.contains("5")){
                                lasttimespinget = "11 PM";
                            }
                            if(lasttimespinget.contains("6")){
                                lasttimespinget = "12 AM";
                            }
                            if(lasttimespinget.contains("7")){
                                lasttimespinget = "1 AM";
                            }
                            if(lasttimespinget.contains("8")){
                                lasttimespinget = "2 AM";
                            }
                            if(lasttimespinget.contains("9")){
                                lasttimespinget = "3 AM";
                            }

                            if(lasttimespinget.contains("10")){
                                lasttimespinget = "24 x 7";
                            }










                            //pet
                            pet = object.getString("pet");
                            if(pet.equals("1")){
                                pet = "Yes";
                            }else {
                                pet = "No";
                            }


                            //visiter
                            visiter = object.getString("visiter");
                            if(visiter.equals("1")){
                                visiter = "Yes";
                            }else {
                                visiter = "No";
                            }


                            //smoking
                            smoking = object.getString("smoking");

                            if(smoking.equals("1")){
                                smoking = "Yes";
                            }else {
                                smoking = "No";
                            }


                            //alcohol
                            alcohol = object.getString("alcohol");
                            if(alcohol.equals("1")){
                                alcohol = "Yes";
                            }else {
                                alcohol = "No";
                            }


                            //event
                            event = object.getString("event");
                            if(event.equals("1")){
                                event = "Yes";
                            }else {
                                event = "No";
                            }


                            //anemities
                            anemitiesitem = object.getString("anemitiesitem");


                            if(anemitiesitem.contains("1")){
                                anemitiesitem1.add("Lift");
                            }

                            if(anemitiesitem.contains("2")){
                                anemitiesitem1.add("Park");
                            }

                            if(anemitiesitem.contains("3")){
                                anemitiesitem1.add("Maintenance Staff");
                            }

                            if(anemitiesitem.contains("4")){
                                anemitiesitem1.add("Visiter Parking");
                            }

                            if(anemitiesitem.contains("5")){
                                anemitiesitem1.add("FengShui/Vaastu Compliant");
                            }

                            if(anemitiesitem.contains("6")){
                                anemitiesitem1.add("Intercome Facility");
                            }

                            if(anemitiesitem.contains("7")){
                                anemitiesitem1.add("Water Storage");
                            }

                            if(anemitiesitem.contains("8")){
                                anemitiesitem1.add("Security/Fire Alaram");
                            }

                            anemitiesitem = String.valueOf(anemitiesitem1);






                            //moreanemities
                            moreanemitiesitem = object.getString("moreanemitiesitem");

                            moreanemitiesitem = moreanemitiesitem.toString().replace("[", "").replace("]", "");
                            String resultflatamore = moreanemitiesitem.replaceAll("\\s", "");

                            moreanemitiesitem3 = new ArrayList<String>(Arrays.asList(resultflatamore.split(",")));


                            moreanemitiesitem2.addAll(moreanemitiesitem3);

                            System.out.println("morrrrrrr ::: "+ moreanemitiesitem2);


                            if(moreanemitiesitem2.contains("1")){
                                moreanemitiesitem1.add("Swimming Pool");
                            }

                            if(moreanemitiesitem2.contains("2")){
                                moreanemitiesitem1.add("Centrally Air Conditioned");
                            }

                            if(moreanemitiesitem2.contains("3")){
                                moreanemitiesitem1.add("Garden");
                            }

                            if(moreanemitiesitem2.contains("4")){
                                moreanemitiesitem1.add("Wifi");
                            }

                            if(moreanemitiesitem2.contains("5")){
                                moreanemitiesitem1.add("Piped-Gas");
                            }

                            if(moreanemitiesitem2.contains("6")){
                                moreanemitiesitem1.add("Water Purifier");
                            }

                            if(moreanemitiesitem2.contains("7")){
                                moreanemitiesitem1.add("Club House");
                            }

                            if(moreanemitiesitem2.contains("8")){
                                moreanemitiesitem1.add("Shopping Center");
                            }

                            if(moreanemitiesitem2.contains("9")){
                                moreanemitiesitem1.add("Water Disposal");
                            }

                            if(moreanemitiesitem2.contains("10")){
                                moreanemitiesitem1.add("RainWater Harvesting");
                            }

                            if(moreanemitiesitem2.contains("11")){
                                moreanemitiesitem1.add("Bank Attached Property");
                            }

                            if(moreanemitiesitem2.contains("12")){
                                moreanemitiesitem1.add("Gym");
                            }

                            moreanemitiesitem = String.valueOf(moreanemitiesitem1);

                            System.out.println("morrrr more ::"+ moreanemitiesitem1);



                            //watersource
                            watersourceitem = object.getString("watersourceitem");

                            if(watersourceitem.contains("1")){
                                watersourceitem1.add("Municipal Corporation");
                            }
                            if(watersourceitem.contains("2")){
                                watersourceitem1.add("Borewell");
                            }

                            watersourceitem = String.valueOf(watersourceitem1);



                            //overlooking
                            overlookingitem = object.getString("overlookingitem");

                            if(overlookingitem.contains("1")){
                                overlookingitem1.add("Park/Garden");
                            }

                            if(overlookingitem.contains("2")){
                                overlookingitem1.add("Main Road");
                            }

                            if(overlookingitem.contains("3")){
                                overlookingitem1.add("Club");
                            }

                            if(overlookingitem.contains("4")){
                                overlookingitem1.add("Lake Facing");
                            }

                            if(overlookingitem.contains("5")){
                                overlookingitem1.add("Sea Facing");
                            }

                            if(overlookingitem.contains("6")){
                                overlookingitem1.add("Others");
                            }

                            overlookingitem = String.valueOf(overlookingitem1);



                            //somefeatures
                            somefeatureitem = object.getString("somefeatureitem");
                            if(somefeatureitem.contains("1")){
                                somefeatureitem1.add("In a Gated Society");
                            }

                            if(somefeatureitem.contains("2")){
                                somefeatureitem1.add("Corner Property");
                            }
                            if(somefeatureitem.contains("3")){
                                somefeatureitem1.add("Pet Friendly");
                            }

                            if(somefeatureitem.contains("4")){
                                somefeatureitem1.add("Wheelchair Friendly");
                            }

                            somefeatureitem = String.valueOf(somefeatureitem1);



                            //byres
                            byersitem = object.getString("byersitem");

                            if (byersitem.contains("1")) {
                                byersitem1.add("24 x 7 Water");
                            }
                            if (byersitem.contains("2")) {
                                byersitem1.add("Visiter Parking Available");
                            }
                            if (byersitem.contains("3")) {
                                byersitem1.add("Close To School");
                            }
                            if (byersitem.contains("4")) {
                                byersitem1.add("Close To Bank");
                            }
                            if (byersitem.contains("5")) {
                                byersitem1.add("Natural Light");
                            }
                            if (byersitem.contains("6")) {
                                byersitem1.add("Close To Hospital");
                            }
                            if (byersitem.contains("7")) {
                                byersitem1.add("Close To Market");
                            }
                            if (byersitem.contains("8")) {
                                byersitem1.add("Airy Rooms");
                            }

                            byersitem = String.valueOf(byersitem1);


                            //times
                            timeitems = object.getString("timeitems");

                            if(timeitems.contains("1")){
                                timeitems1.add("8am - 12pm");
                            }

                            if(timeitems.contains("2")){
                                timeitems1.add("12pm - 3pm");
                            }
                            if(timeitems.contains("3")){
                                timeitems1.add("3pm - 6pm");
                            }

                            if(timeitems.contains("4")){
                                timeitems1.add("6pm - 9pm");
                            }

                            if(timeitems.contains("5")){
                                timeitems1.add("9pm - 11pm");
                            }

                            if(timeitems.contains("6")){
                                timeitems1.add("None");
                            }

                            timeitems = String.valueOf(timeitems1);



                            widthfacingget = object.getString("widthfacingget");
                            descriptionget = object.getString("descriptionget");


                            //bowndywall
                            boundrywall = object.getString("boundrywall");
                            if(boundrywall.equals("1")){
                                boundrywall = "Yes";
                            }else {
                                boundrywall = "No";
                            }

                            //flattype
                            flattype = object.getString("flattype");


                            if(flattype.equals("1")){
                                flattype = "1 BHK";
                            }
                            else if(flattype.equals("2")){
                                flattype = "2 BHK";
                            }
                            else if(flattype.equals("3")){
                                flattype = "3 BHK";
                            }
                            else if(flattype.equals("4")){
                                flattype = "4 BHK";
                            }
                            else if(flattype.equals("5")){
                                flattype = "5 BHK";
                            }
                            else if(flattype.equals("6")){
                                flattype = "6 BHK";
                            }
                            else if(flattype.equals("7")){
                                flattype = "7 BHK";
                            }
                            else if(flattype.equals("8")){
                                flattype = "8 BHK";
                            }
                            else if(flattype.equals("9")){
                                flattype = "9 BHK";
                            }
                            else if(flattype.equals("10")){
                                flattype = "10 BHK";
                            }
                            else if(flattype.equals("11")){
                                flattype = "11 BHK";
                            }


                            user_id= object.getString("user_id");
                            newprice= object.getString("newprice");
                            image= Endpoints.base_url+ object.getString("image");
                            shortlistedvalue= object.getString("shortlistedvalue");

                            //rera
                            getrera= object.getString("rera");
                            if(getrera.equals("1")){
                                getrera = "Yes";
                            }else {
                                getrera = "No";
                            }

                            mobile= object.getString("mobile");
                            reg_date= object.getString("reg_date");

                            /*System.out.println("getuserfname :; "+ object.getString("id"));
                            System.out.println("getuserfname :; "+ object.getString("fname"));*/
                            fname= object.getString("fname");
                            lname= object.getString("lname");
                            email= object.getString("email");
                            latlong= object.getString("latlong");






                            LatestDataModel latestDataModel = new LatestDataModel(id ,usertype , type , category , categorytype , agrement , pgavaliablefor , pgshareprivate ,
                                    sharingspinnumber , propertylistfor , willing , pgsuitablefor , state,city , locality , projectname , address ,
                                    unit1 ,unit2 ,unit3 ,availabilitydate , otherroom , homethings , builtupa , carpeta , plota , bed ,
                                    bath , balconie , totalfloorget , reservedpark , availabilityspinget , ageofpropertyspinget ,
                                    furnishedspinget ,possessionbyspinget ,
                                    noofroomspinget , qualityratingspinget , floorallowedspinget , roomavailabespinget , priceget , maintenanceget ,
                                    bookingamountget ,
                                    durationofrentagget , monthofnoticeget  , securitydepositspinget , maintenancemonthlyspinget ,
                                    ownweshipspinget ,
                                    typeoffood , pricestep , facilityincluded , weekdays , weeends , earlylivingchargesget , contractdurationget ,
                                    annualduespayableget ,
                                    depositeaget , facingspinget , unitspinget , typeofflooringspinget , powerbackupspinget , lasttimespinget ,
                                    pet , visiter , smoking ,
                                    alcohol , event , anemitiesitem , moreanemitiesitem , watersourceitem , overlookingitem ,
                                    somefeatureitem , byersitem , timeitems ,
                                    widthfacingget , descriptionget , boundrywall ,flattype, user_id, newprice, image,
                                    shortlistedvalue, getrera, mobile, reg_date, fname, lname , latlong, email);
                            latestDataModels.add(latestDataModel);
                            latestAdapter.notifyDataSetChanged();
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
                Toast.makeText(ShowSearchProperty.this,"Something Went Wrong",Toast.LENGTH_SHORT).show();
                Log.d("VOLLEY", String.valueOf(error));
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                SmsData smsData = new SmsData();
                Map<String, String> params = new HashMap<>();
                params.put("header", smsData.token);

                params.put("listfor", listfor1);
                params.put("minprice", min);
                params.put("maxprice", max);
                params.put("type", type1);
                params.put("category", category1);
                params.put("city", getcity1);
                params.put("sharing", sharing);
                params.put("userid", userid);
                params.put("usertype", usertype1);
                params.put("state", getstate1);
                params.put("locality", getlocality1);
                params.put("project", getproject1);
                params.put("offset", String.valueOf(offset));



                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(ShowSearchProperty.this).addToRequestQueue(stringRequest);

    }

    private void showmessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
