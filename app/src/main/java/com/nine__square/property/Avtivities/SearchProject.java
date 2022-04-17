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
import com.nine__square.property.Adapter.SearchProjectAdapter;
import com.nine__square.property.Models.SearchProjectDataModel;
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

public class SearchProject extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private ProgressDialog progressDialog;
    private List<SearchProjectDataModel> searchProjectDataModels;
    private SearchProjectAdapter searchProjectAdapter;

    LinearLayoutManager layoutManager;

    SearchView citysearch;
    RecyclerView serachrecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_project);

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




        searchProjectDataModels = new ArrayList<>();


        layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        serachrecyclerview.setLayoutManager(layoutManager);
        searchProjectAdapter = new SearchProjectAdapter(searchProjectDataModels,this);
        serachrecyclerview.setAdapter(searchProjectAdapter);


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




    private void getcity(final String city) {





        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.getprojectname, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                System.out.println("responseec:: "+ response);
                try {
                    JSONObject obj = new JSONObject(response);

                    System.out.println("response :: "+ response);

                    String succes = obj.getString("success");
                    JSONArray jsonArray = obj.getJSONArray("data");


                    if(succes.equals("1")){

                        searchProjectDataModels.clear();
                        searchProjectAdapter.notifyDataSetChanged();

                        for(int i=0; i<jsonArray.length();i++){
                            JSONObject object = jsonArray.getJSONObject(i);
                            String id =object.getString("id");
                            String project =object.getString("project");

                            SearchProjectDataModel searchCityDataModel = new SearchProjectDataModel(id,project);
                            searchProjectDataModels.add(searchCityDataModel);
                            searchProjectAdapter.notifyDataSetChanged();

                        }

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
                Toast.makeText(SearchProject.this,"Something Went Wrong",Toast.LENGTH_SHORT).show();
                Log.d("VOLLEY",error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                SmsData smsData = new SmsData();
                Map<String, String> params = new HashMap<>();
                params.put("header", smsData.token);
                params.put("project", city);
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
