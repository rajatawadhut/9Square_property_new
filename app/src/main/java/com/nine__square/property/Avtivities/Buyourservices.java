package com.nine__square.property.Avtivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.nine__square.property.Utils.StorageSome;
import com.nine__square.property.Utils.VolleySingleton;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Buyourservices extends AppCompatActivity implements PaymentResultListener {


    Button payment;
    ImageView back;
    private ProgressDialog progressDialog;
    CardView cvBalance;
    TextView tvBalance;

    Button btn700,btn1200,btn2000;
    String getsubscription= "";
    String amountValue= "";
    int valueInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_our_services);


        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Buy Services");
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


        btn700 = findViewById(R.id.btn_700);
        btn1200 = findViewById(R.id.btn_1200);
        btn2000 = findViewById(R.id.btn_2000);
        cvBalance = findViewById(R.id.cv_balance);
        tvBalance = findViewById(R.id.tv_balance);


        if(VolleySingleton.getInstance(getApplicationContext()).isLogin()){
            getsubscription();
        }


        btn700.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(VolleySingleton.getInstance(getApplicationContext()).isLogin()){
                    startpayment("700");
                    valueInt = 4;
                    amountValue = "700";
                    
                }else{
                    showmessage("Please login first!!");
                    startActivity(new Intent(Buyourservices.this, LoginActivity.class));
                    finish();
                }

            }
        });

        btn1200.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(VolleySingleton.getInstance(getApplicationContext()).isLogin()){
                    startpayment("1200");
                    valueInt = 8;
                    amountValue = "1200";
                    
                }else{
                    showmessage("Please login first!!");
                    startActivity(new Intent(Buyourservices.this, LoginActivity.class));
                    finish();
                }

            }
        });

        btn2000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(VolleySingleton.getInstance(getApplicationContext()).isLogin()){
                    startpayment("2000");
                    valueInt = 12;
                    amountValue = "2000";
                    
                }else{
                    showmessage("Please login first!!");
                    startActivity(new Intent(Buyourservices.this, LoginActivity.class));
                    finish();
                }
            }
        });


    }

    public void startpayment(String value){


        Checkout checkout = new Checkout();
        try {
            JSONObject orderRequest = new JSONObject();
            orderRequest.put("name", "9 Square Property"); // amount in the smallest currency unit
            orderRequest.put("description", "App Payment"); // amount in the smallest currency unit
            orderRequest.put("currency", "INR");
//            String amountValue = amount.getText().toString().trim();

            double total = Double.parseDouble(value);
            total = total * 100;
            orderRequest.put("theme.color","#008B8B");
            orderRequest.put("amount", total);
            orderRequest.put("image", Endpoints.homeicon);
            orderRequest.put("prefill.email", VolleySingleton.getInstance(getApplicationContext()).email());
            orderRequest.put("prefill.contact",VolleySingleton.getInstance(getApplicationContext()).mobile());

            checkout.open(this, orderRequest);




        }
        catch (Exception e){

        }
    }


    private void getsubscription() {
        final String userid = VolleySingleton.getInstance(getApplicationContext()).id();
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.getsubscription, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                System.out.println("response :: " +response);
                progressDialog.dismiss();
                try {
                    JSONObject obj = new JSONObject(response);
                    if(obj.getBoolean("error")){
                        showmessage(obj.getString("message"));
                    }else{
                        String result = obj.getString("subscriptionplan");
                        cvBalance.setVisibility(View.VISIBLE);
                        tvBalance.setText(result);
                        StorageSome.getInstance(getApplicationContext()).setsubsription(result);


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(Buyourservices.this,"Something Went Wrong",Toast.LENGTH_SHORT).show();
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


    private void showmessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    private void sendsubscription(String tansId) {

        int getsub = Integer.parseInt(StorageSome.getInstance(getApplicationContext()).getsubsription());
        int a = getsub + valueInt;

        getsubscription = String.valueOf(a);

        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.sendsubscription, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                System.out.println("response ::::: "+ response);
                progressDialog.dismiss();
                try {
                    JSONObject obj = new JSONObject(response);
                    if(obj.getBoolean("error")){
                        showmessage(obj.getString("message"));
                    }else{
                        getsubscription();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Buyourservices.this,"Something Went Wrong",Toast.LENGTH_SHORT).show();
                Log.d("VOLLEY", String.valueOf(error));
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                SmsData smsData = new SmsData();
                Map<String, String> params = new HashMap<>();
                params.put("header", smsData.token);
                params.put("userid", VolleySingleton.getInstance(getApplicationContext()).id());
                params.put("subscription", getsubscription);

                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(Buyourservices.this).addToRequestQueue(stringRequest);


    }



    private void sendPaymentDetail(String transId, String amount) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.payment_detail, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                System.out.println("response ::::: "+ response);
                try {
                    JSONObject obj = new JSONObject(response);
                    progressDialog.dismiss();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Buyourservices.this,"Something Went Wrong",Toast.LENGTH_SHORT).show();
                Log.d("VOLLEY", String.valueOf(error));
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                SmsData smsData = new SmsData();
                Map<String, String> params = new HashMap<>();
                params.put("header", smsData.token);
                params.put("userid", VolleySingleton.getInstance(getApplicationContext()).id());
                params.put("amount", amount);
                params.put("transactionid", transId);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_TIMEOUT_MS));
        VolleySingleton.getInstance(Buyourservices.this).addToRequestQueue(stringRequest);

    }


    @Override
    public void onPaymentSuccess(String s) {
        sendsubscription(s);
        sendPaymentDetail(s, amountValue);
        Toast.makeText(this,"Payment Successful", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onPaymentError(int i, String s) {

        Toast.makeText(this, "Payment Failed!!", Toast.LENGTH_LONG).show();

    }
}

