package com.nine__square.property.Avtivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
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
import com.nine__square.property.Adapter.UserListAdapter;
import com.nine__square.property.Models.LatestDataModel;
import com.nine__square.property.Models.UserListDataModel;
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

public class OwnerBuilderDeveloperList extends AppCompatActivity {

    private Button builder, developer, owner, search;
    private View vbuilder, vdeveloper, vowner;
    private EditText name, state;
    private RecyclerView recyclerView;
    LinearLayout linear3;

    String usertype = "", city = "", propertytype =  "", stateget = "";
    LinearLayoutManager layoutManager;
    UserListAdapter userListAdapter;
    private List<UserListDataModel> userListDataModels;

    private ProgressBar progresscustom;
    private ProgressDialog progressDialog;

    LinearLayout nodata;

    private List<LatestDataModel> latestDataModels;
    private LatestAdapter latestAdapter;


    String fullname, email, mobile, subscriptionplan = "", image = "";


    Boolean isScrolling = false;
    int currentItems, totalItems, scrollOutItems;
    int offset = 0;

//    RadioButton property, project;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_builder_developer_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("User List");
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
        progressDialog.setCancelable(false);

        userListDataModels = new ArrayList<>();
        latestDataModels = new ArrayList<>();



        getsubscription();



        builder = findViewById(R.id.builder);
        developer = findViewById(R.id.developer);
        owner = findViewById(R.id.owner);
        name = findViewById(R.id.name);
        state = findViewById(R.id.state);
        recyclerView = findViewById(R.id.recyclerview);
        search = findViewById(R.id.search);

        vbuilder = findViewById(R.id.vbuilder);
        vdeveloper = findViewById(R.id.vdeveloper);
        vowner = findViewById(R.id.vowner);
        linear3 = findViewById(R.id.linear3);



        progresscustom = findViewById(R.id.progresscustom);

        progresscustom.setVisibility(View.GONE);


        nodata = findViewById(R.id.nodata);

        nodata.setVisibility(View.GONE);




        vbuilder.setVisibility(View.GONE);
        vdeveloper.setVisibility(View.GONE);
        vowner.setVisibility(View.GONE);



//        propertytype = "Property";

        state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name.setText("");
                VolleySingleton.getInstance(getApplicationContext()).setsearchcity("");
                startActivity(new Intent(OwnerBuilderDeveloperList.this, SearchState.class));
            }
        });

        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OwnerBuilderDeveloperList.this, SearchCity.class));
            }
        });






        layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        userListAdapter = new UserListAdapter(userListDataModels,this);
        recyclerView.setAdapter(userListAdapter);












        linear3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nodata.setVisibility(View.GONE);
            }
        });




        builder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                builder.setTextColor(Color.GREEN);
                developer.setTextColor(Color.WHITE);
                owner.setTextColor(Color.WHITE);


                vbuilder.setVisibility(View.VISIBLE);
                vdeveloper.setVisibility(View.GONE);
                vowner.setVisibility(View.GONE);

                usertype = "3";


            }
        });

        developer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                builder.setTextColor(Color.WHITE);
                developer.setTextColor(Color.GREEN);
                owner.setTextColor(Color.WHITE);

                vbuilder.setVisibility(View.GONE);
                vdeveloper.setVisibility(View.VISIBLE);
                vowner.setVisibility(View.GONE);
                usertype = "2";



            }
        });

        owner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                builder.setTextColor(Color.WHITE);
                developer.setTextColor(Color.WHITE);
                owner.setTextColor(Color.GREEN);

                vbuilder.setVisibility(View.GONE);
                vdeveloper.setVisibility(View.GONE);
                vowner.setVisibility(View.VISIBLE);
                usertype = "1";

            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stateget = state.getText().toString().trim();
                city = name.getText().toString().trim();


                if(isvalid()){
                    searchuser(city);
                    userListDataModels.clear();
                    userListAdapter.notifyDataSetChanged();
                    nodata.setVisibility(View.GONE);
                    closeKeyboard();
                    offset = 0;

                }

            }
        });


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                    searchuser(city);
                }
            }
        });





    }


    private void getsubscription() {
        final String userid = VolleySingleton.getInstance(getApplicationContext()).id();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.getsubscription, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                System.out.println("response :: " +response);
                try {
                    JSONObject obj = new JSONObject(response);
                    if(obj.getBoolean("error")){
                        showmessage(obj.getString("message"));
                    }else{
                        subscriptionplan = obj.getString("subscriptionplan");

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(OwnerBuilderDeveloperList.this,"Something Went Wrong",Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                SmsData smsData = new SmsData();
                Map<String, String> params = new HashMap<>();
                params.put("header", smsData.token);
                params.put("userid", userid);
                return params;
            }
        };


        stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }



    public void searchuser(final String name1){
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



        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.getuserlistsearch, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    progresscustom.setVisibility(View.GONE);
                    System.out.println("response :: "+ response);
                    closeKeyboard();

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
                                String fname, lname, userid1, usertype1;
                                fname= object.getString("fname");
                                lname= object.getString("lname");
                                fullname = fname +" "+lname;
                                email= object.getString("email");
                                mobile= object.getString("mobile");
                                city= object.getString("city");
                                userid1 = object.getString("userid");
                                usertype1 = object.getString("usertype");
                                image = Endpoints.base_url + object.getString("image");



                                UserListDataModel userListDataModel = new UserListDataModel(fullname,email,mobile,city,userid1, subscriptionplan, image, usertype1);
                                userListDataModels.add(userListDataModel);
                                userListAdapter.notifyDataSetChanged();
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
                    params.put("city", name1);
                    params.put("usertype", usertype);
                    params.put("offset", String.valueOf(offset));

                    return params;
                }
            };
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
        }






    private boolean isvalid() {

        if(usertype.isEmpty()){
            showmessage("Please Select User Type...");
            return false;
        }
        else if(stateget.isEmpty()){
            showmessage("Please Type State....");
            return false;
        }
        else if(city.isEmpty()){
            showmessage("Please Type City....");
            return false;
        }

        return true;
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    public void  showmessage(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }


    @Override
    protected void onResume() {

        if(VolleySingleton.getInstance(getApplicationContext()).getsearchstate() != null){
            String citysearch = VolleySingleton.getInstance(getApplicationContext()).getsearchstate();
            state.setText(citysearch);
        }

        if(VolleySingleton.getInstance(getApplicationContext()).getsearchcity() != null){
            String citysearch = VolleySingleton.getInstance(getApplicationContext()).getsearchcity();
            name.setText(citysearch);
        }

        super.onResume();
    }

}
