package com.nine__square.property.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.nine__square.property.R;
import com.nine__square.property.Utils.Endpoints;
import com.nine__square.property.Utils.SmsData;
import com.nine__square.property.Utils.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class PostAnswer extends Fragment {

    EditText question,answer;
    Button sendanswer;
    private ProgressDialog progressDialog;
    String getpropertyid, getquestion,getqueid, getuserid;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_post_answer, container, false);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);


        Toolbar toolbar = v.findViewById(R.id.toolbar);
        toolbar.setTitle("Send Answer");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();

            }
        });

        question =v.findViewById(R.id.question);
        answer = v.findViewById(R.id.answer);
        sendanswer = v.findViewById(R.id.sendanswer);

        Bundle bundle = this.getArguments();

        if(bundle != null){
            getquestion =  bundle.getString("question");
            getpropertyid =  bundle.getString("propertyid");
            getqueid =  bundle.getString("queid");
            getuserid =  bundle.getString("userid");        }






        question.setText(getquestion);


        sendanswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String answera;

                answera = answer.getText().toString().trim();


                if(questionvalid(answera)){
                    sendques(answera);
                }
            }
        });

        return v;

    }

    private void sendques(final String answera) {
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.sendanswer, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject obj = new JSONObject(response);
                    if(obj.getBoolean("error")){
                        showmessage(obj.getString("message"));
                    }else{
                        showmessage(obj.getString("message"));
                        getActivity().onBackPressed();
//                        Intent intent = new Intent(PostAnswer.this, MyAllProjectActivity.class);
//                        startActivity(intent);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"Something Went Wrong",Toast.LENGTH_SHORT).show();
                Log.d("VOLLEY", String.valueOf(error));
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                SmsData smsData = new SmsData();
                Map<String, String> params = new HashMap<>();
                params.put("header", smsData.token);
                params.put("answer", answera);
                params.put("queid", getqueid);
                params.put("propertyid", getpropertyid);
                params.put("userid", getuserid);

                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
    }

    private boolean questionvalid(String answera) {
        if(answera.isEmpty()){
            answer.setError("Please Enter the Answer");
            return false;
        }

        return true;
    }

    private void showmessage(String msg){
        Toast.makeText(getContext(),msg,Toast.LENGTH_LONG).show();
    }

}
