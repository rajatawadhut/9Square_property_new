package com.nine__square.property.Avtivities;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.nine__square.property.R;
import com.nine__square.property.Utils.Endpoints;
import com.nine__square.property.Utils.JavaMailRegisterOtp;
import com.nine__square.property.Utils.SmsData;
import com.nine__square.property.Utils.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RegistrationOTP extends AppCompatActivity {

    EditText otp;
    Button register;
    String getemail, getmobile, otp1, getfname, getlname;
    TextView resendotp, timer;
    private ProgressDialog progressDialog;
    SmsData smsData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_otp);

        if(VolleySingleton.getInstance(getApplicationContext()).isLogin()){
            startActivity(new Intent(this,MainActivity.class));
            RegistrationOTP.this.finish();
        }

        smsData = new SmsData();

        register = findViewById(R.id.register);
        otp = findViewById(R.id.otp);
        resendotp = findViewById(R.id.resendotp);
        timer = findViewById(R.id.timer);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);

        starttimer();

        getemail =  getIntent().getStringExtra("email");
        getmobile =  getIntent().getStringExtra("mobile");
        getfname =  getIntent().getStringExtra("fname");
        getlname =  getIntent().getStringExtra("lname");







        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String otpv;
                otpv = otp.getText().toString().trim();

                if(valid(otpv)){
                    sendotp(otpv);

                }
            }
        });


        resendotp.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View v) {
                Random random = new Random();
                otp1 = String.format("%04d", random.nextInt(10000));
                sentotpserver(otp1);
                sendotptomobile(otp1);
            }
        });


    }


    private void sendotptomobile(String otp2) {


        String subject = "Otp Of 99Acres";
        String name = getfname + " " + getlname;
        final String message = smsData.message + otp2 + smsData.message2;
        String url = "https://www.txtguru.in/imobile/api.php?";


        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                System.out.println("Response Sms : "+ response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegistrationOTP.this,"Something Went Wrong",Toast.LENGTH_SHORT).show();
                Log.d("VOLLEY", String.valueOf(error));
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                SmsData smsData = new SmsData();
                Map<String, String> params = new HashMap<>();
                params.put("header", smsData.token);
                params.put("username", smsData.Username);
                params.put("password", smsData.Password);
                params.put("source", smsData.source);
                params.put("dltentityid", smsData.dltentityid);
                params.put("dltheaderid", smsData.dltheaderid);
                params.put("dlttempid", smsData.dlttempid);
                params.put("dmobile", getmobile);
                params.put("message", message);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }


    private void sentotpserver(final String otp12) {
        starttimer();
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.resendotp, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                System.out.println("response :: " +response);
                progressDialog.dismiss();
                try {
                    JSONObject obj = new JSONObject(response);
//                    System.out.println("response :: " +obj.getString("message"));
                    if(obj.getBoolean("error")){
                        showmessage(obj.getString("message"));
                    }else{
                        sendMail(otp12);
                        showmessage(obj.getString("message"));


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(RegistrationOTP.this,"Something Went Wrong",Toast.LENGTH_SHORT).show();
//                Log.d("VOLLEY",error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                SmsData smsData = new SmsData();
                Map<String, String> params = new HashMap<>();
                params.put("header", smsData.token);
                params.put("otp", otp12);
                params.put("email", getemail);
                params.put("mobile", getmobile);
                return params;
            }
        };
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);


    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Are you sure?")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        startActivity(intent);
                        finishAffinity();
                        finish();
                    }
                }).setNegativeButton("no", null).show();
    }

    private boolean valid(String otpv) {
        if(otpv.isEmpty()){
            otp.setError("Please Enter OTP");
            return false;
        }
        return true;
    }

    private void sendotp(final String otpv) {
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.registrationOtp, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                System.out.println("response :: " +response);
                progressDialog.dismiss();
                try {
                    JSONObject obj = new JSONObject(response);
//                    System.out.println("response :: " +obj.getString("message"));
                    if(obj.getBoolean("error")){
                        showmessage(obj.getString("message"));
                    }else{
                        VolleySingleton.getInstance(getApplicationContext()).user(

                                obj.getString("id"),
                                obj.getString("fname"),
                                obj.getString("lname"),
                                obj.getString("mobile"),
                                obj.getString("email"),
                                obj.getString("address"),
                                obj.getString("city"),
                                "",
                                obj.getString("state"),
                                Endpoints.base_url+obj.getString("profilepic"),
                                obj.getString("date")
                        );

                        showmessage(obj.getString("message"));
                        startActivity(new Intent(RegistrationOTP.this, MainActivity.class));
                        RegistrationOTP.this.finish();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(RegistrationOTP.this,"Something Went Wrong",Toast.LENGTH_SHORT).show();
//                Log.d("VOLLEY",error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                SmsData smsData = new SmsData();
                Map<String, String> params = new HashMap<>();
                params.put("header", smsData.token);
                params.put("otp", otpv);
                params.put("email", getemail);
                params.put("mobile", getmobile);
                return params;
            }
        };
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }

    private void sendMail(String otp12) {

        String mail = getemail;

        String subject = smsData.subject;
        String name = getfname + " " + getlname;
        String message = smsData.message + otp12 + smsData.message2;
        String phone = getmobile;

        JavaMailRegisterOtp javaMailRegisterOtp = new JavaMailRegisterOtp(this, mail, subject,message);
        javaMailRegisterOtp.execute();


    }

    public void starttimer(){
        timer.setVisibility(View.VISIBLE);
        resendotp.setVisibility(View.GONE);
        new CountDownTimer(150000, 1000) {

            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                timer.setText("TIME : " + String.format("%02d", minutes)
                        + ":" + String.format("%02d", seconds));
            }


            public void onFinish() {
                resendotp.setVisibility(View.VISIBLE);
                timer.setVisibility(View.GONE);

            }
        }.start();
    }






    private void showmessage(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

}
