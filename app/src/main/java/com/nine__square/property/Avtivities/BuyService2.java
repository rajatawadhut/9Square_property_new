package com.nine__square.property.Avtivities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
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

public class BuyService2 extends AppCompatActivity implements PaymentResultListener {


    Button payment;
    ImageView back;
    private ProgressDialog progressDialog;


    TextView platinum, gold, silver, propertylistno;
    Button platinumbtn, goldbtn, silverbtn;
    String getsubscription= "";
    int valueInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_service2);




        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);


//        payment = v.findViewById(R.id.payment);
            platinum = findViewById(R.id.platinum);
            gold = findViewById(R.id.gold);
            silver = findViewById(R.id.silver);
            back = findViewById(R.id.back);
        propertylistno = findViewById(R.id.propertylistno);

            platinumbtn = findViewById(R.id.platinumbtn);
            goldbtn = findViewById(R.id.goldbtn);
            silverbtn = findViewById(R.id.silverbtm);

            getsubscription();




            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });


            platinumbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    startpayment("1500");
                    valueInt = 100;
                }
            });

            goldbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    startpayment("1000");
                    valueInt = 50;


                }
            });

            silverbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startpayment("500");
                    valueInt = 20;

                }
            });


            blink();

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
                orderRequest.put("amount", total);
                orderRequest.put("image", "https://i.ibb.co/Yhwy9Qw/ico.png");
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
                        propertylistno.setText(result);
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
                Toast.makeText(BuyService2.this,"Something Went Wrong",Toast.LENGTH_SHORT).show();
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


    private void sendsubscription() {

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
                Toast.makeText(BuyService2.this,"Something Went Wrong",Toast.LENGTH_SHORT).show();
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
        VolleySingleton.getInstance(BuyService2.this).addToRequestQueue(stringRequest);


    }


    private void blink(){
            final Handler handler = new Handler();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int timeToBlink = 800;    //in milissegunds
                    try{Thread.sleep(timeToBlink);}catch (Exception e) {}
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if(silver.getVisibility() == View.VISIBLE){
                                silver.setVisibility(View.INVISIBLE);
                            }else{
                                silver.setVisibility(View.VISIBLE);
                            }

                            if(gold.getVisibility() == View.VISIBLE){
                                gold.setVisibility(View.INVISIBLE);
                            }else{
                                gold.setVisibility(View.VISIBLE);
                            }


                            if(platinum.getVisibility() == View.VISIBLE){
                                platinum.setVisibility(View.INVISIBLE);
                            }else{
                                platinum.setVisibility(View.VISIBLE);
                            }




                            blink();
                        }
                    });
                }
            }).start();
        }


        @Override
        public void onPaymentSuccess(String s) {
            sendsubscription();
            Toast.makeText(this, s, Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onPaymentError(int i, String s) {

            Toast.makeText(this, s, Toast.LENGTH_SHORT).show();

        }
    }

