package com.nine__square.property.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.nine__square.property.R;
import com.nine__square.property.Utils.Endpoints;
import com.nine__square.property.Utils.SmsData;
import com.nine__square.property.Utils.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class DeleteFragment extends Fragment {
    Button deleteimg;
    ImageView showimg;
    String getimg, id;
    private ProgressDialog progressDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_delete, container, false);


        Toolbar toolbar = v.findViewById(R.id.toolbar);
        toolbar.setTitle("Delete Image");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);


        deleteimg = v.findViewById(R.id.deleteimg);
        showimg = v.findViewById(R.id.showimg);




        Bundle bundle = this.getArguments();

        if(bundle != null){
            getimg = bundle.getString("img");
            id = bundle.getString("id");
        }

        Glide.with(DeleteFragment.this).load(getimg).into(showimg);


        deleteimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
//                new AlertDialog.Builder(getContext()).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Delete Image")
//                        .setMessage("Are you sure?")
//                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                Delete();
//
//                            }
//                        }).setNegativeButton("no", null).show();

            }
        });





        return v;
    }

    public void delete(){

        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.deleteimg, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                System.out.println("response :: "+ response);




                try {
                    JSONObject obj = new JSONObject(response);

                    Toast.makeText(getContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                    getActivity().onBackPressed();






                } catch (JSONException e) {
                    System.out.println("problem ::"+e);
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                System.out.println("response error : " +error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                SmsData smsData = new SmsData();
                Map<String, String> params = new HashMap<>();
                params.put("header", smsData.token);
                params.put("id", id);
                System.out.println("response params :; "+ params);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);

    }

}