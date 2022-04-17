package com.nine__square.property.Avtivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.nine__square.property.Adapter.SearchLocalityAdapter;
import com.nine__square.property.Models.SearchLocalityDataModel;
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

public class SearchLocality extends AppCompatActivity implements SearchView.OnQueryTextListener {


    private ProgressDialog progressDialog;
    private List<SearchLocalityDataModel> searchLocalityDataModels;
    private SearchLocalityAdapter searchLocalityAdapter;

    LinearLayoutManager layoutManager;

    SearchView citysearch;
    RecyclerView serachrecyclerview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_locality);


        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Search");
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


        serachrecyclerview = findViewById(R.id.serachrecyclerview);
        citysearch = findViewById(R.id.citysearch);




        searchLocalityDataModels = new ArrayList<>();


        layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        serachrecyclerview.setLayoutManager(layoutManager);
        searchLocalityAdapter = new SearchLocalityAdapter(searchLocalityDataModels,this);
        serachrecyclerview.setAdapter(searchLocalityAdapter);


        citysearch.setOnQueryTextListener(this);


    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        getcity(newText);
        return false;
    }




    private void getcity(final String locality) {


        final String state, city;

        city = VolleySingleton.getInstance(getApplicationContext()).getsearchcity();
        state = VolleySingleton.getInstance(getApplicationContext()).getsearchstate();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.getlocality, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                System.out.println("responseec:: "+ response);
                try {
                    JSONObject obj = new JSONObject(response);

                    System.out.println("response :: "+ response);

                    String succes = obj.getString("success");
                    JSONArray jsonArray = obj.getJSONArray("data");


                    if(succes.equals("1")){

                        searchLocalityDataModels.clear();
                        searchLocalityAdapter.notifyDataSetChanged();

                        for(int i=0; i<jsonArray.length();i++){
                            JSONObject object = jsonArray.getJSONObject(i);
                            String id =object.getString("id");
                            String state =object.getString("state");
                            String city =object.getString("city");
                            String locality =object.getString("locality");

                            SearchLocalityDataModel searchStateDataModel = new SearchLocalityDataModel(id,state,city,locality);
                            searchLocalityDataModels.add(searchStateDataModel);
                            searchLocalityAdapter.notifyDataSetChanged();

                        }
                        SearchLocalityDataModel searchStateDataModel2 = new SearchLocalityDataModel("0","0","0","Add Location");
                        searchLocalityDataModels.add(searchStateDataModel2);
                        searchLocalityAdapter.notifyDataSetChanged();


                        System.out.println("finddd :::: "+searchLocalityDataModels);


                    }




                } catch (JSONException e) {
                    showmessage("Something wrong please check network connection"+e);
                    System.out.println("problem ::"+e);
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SearchLocality.this,"Something Went Wrong",Toast.LENGTH_SHORT).show();
                Log.d("VOLLEY",error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                SmsData smsData = new SmsData();
                Map<String, String> params = new HashMap<>();
                params.put("header", smsData.token);
                params.put("locality", locality);
                params.put("state", state);
                params.put("city", city);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }


    public void showmessage(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
