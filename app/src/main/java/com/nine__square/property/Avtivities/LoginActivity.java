package com.nine__square.property.Avtivities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import com.nine__square.property.Utils.JavaMailRegisterOtp;
import com.nine__square.property.Utils.SmsData;
import com.nine__square.property.Utils.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private EditText passwordEt, emailEt, mobile ;
    private TextView register, skip, forgot;
    private Button login, loginotp;
    private ProgressDialog progressDialog;
    String otp;
    SmsData smsData;
    TextView otherWay;
    LinearLayout ll_number,ll_email;
    boolean selected = true;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        smsData = new SmsData();

        emailEt = findViewById(R.id.email);
        mobile= findViewById(R.id.mobile);
        loginotp = findViewById(R.id.loginotp);
        passwordEt = findViewById(R.id.password);
        register = findViewById(R.id.register);
        ll_number = findViewById(R.id.ll_login_number);
        ll_email = findViewById(R.id.ll_login_email);
        otherWay = findViewById(R.id.other_way);

        if(VolleySingleton.getInstance(getApplicationContext()).isLogin()){
            startActivity(new Intent(this,MainActivity.class));
            LoginActivity.this.finish();
        }
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        login = findViewById(R.id.login);
        skip = findViewById(R.id.skip);
        forgot = findViewById(R.id.forgot);


        Random random = new Random();
        otp = String.format("%04d", random.nextInt(10000));





        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password, email;
                email = emailEt.getText().toString().trim();
                password = passwordEt.getText().toString().trim();


                if(isValid(email, password)){
                    Login(email, password);
                }
            }
        });

        otherWay.setOnClickListener(v -> {

            if(selected){
                ll_email.setVisibility(View.GONE);
                ll_number.setVisibility(View.VISIBLE);
                selected = false;
            }else{
                ll_email.setVisibility(View.VISIBLE);
                ll_number.setVisibility(View.GONE);
                selected = true;
            }
        });


        loginotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile1;
                mobile1 = mobile.getText().toString().trim();

                if(mobile1.isEmpty() || mobile1.length() < 10){
                    mobile.setError("Please Enter Mobile Number");
                    showmessage("Please Enter Mobile Number");
                }else{


                    checkmobile(mobile1, otp);
                }
            }
        });


        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ForgotPassword.class));
            }
        });



        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });




    }

    private void checkmobile(final String mobile1, final String otp1) {

        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.loginotpinsert, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                System.out.println("response :: " +response);
                progressDialog.dismiss();
                try {
                    JSONObject obj = new JSONObject(response);

                    if(obj.getBoolean("error")){
                        showmessage(obj.getString("message"));
                        register.setTextColor(Color.rgb(4,86,197));

                    }else{

                        String fname1, lname1, email1;

                        fname1 =  obj.getString("fname");
                        lname1 =  obj.getString("lname");
                        email1 =  obj.getString("email");





                        sendMail(fname1, lname1, email1, otp1);
                        sendotptomobile(fname1, lname1, mobile1, otp1);


                        Intent intent = new Intent(LoginActivity.this, LoginThroughOtpActivity.class);
                        intent.putExtra("fname", fname1);
                        intent.putExtra("lname", lname1);
                        intent.putExtra("mobile", mobile1);
                        intent.putExtra("email", email1);
                        startActivity(intent);



                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this,"Something Went Wrong",Toast.LENGTH_SHORT).show();
//                Log.d("VOLLEY",error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                SmsData smsData = new SmsData();
                Map<String, String> params = new HashMap<>();
                params.put("header", smsData.token);
                params.put("otp", otp1);
                params.put("mobile", mobile1);
                return params;
            }
        };
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);




    }

    // On backpress closed application
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

    private void Login(final String email, final String password){
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.login, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                System.out.println("response :: " +response);
                progressDialog.dismiss();
                try {
                    JSONObject obj = new JSONObject(response);
//                    System.out.println("response :: " +obj.getString("message"));
                    if(obj.getBoolean("error")){
                        showmessage(obj.getString("message"));
                        register.setTextColor(Color.rgb(4,86,197));
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
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        LoginActivity.this.finish();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this,"Something Went Wrong",Toast.LENGTH_SHORT).show();
//                Log.d("VOLLEY",error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                SmsData smsData = new SmsData();
                Map<String, String> params = new HashMap<>();
                params.put("header", smsData.token);
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }

    private void sendotptomobile(String fname1, String lname1, final String mobile2, String otp3) {
        final String phone = mobile.getText().toString().trim();


        String subject = "Otp Of 99Acres";
        final String message = smsData.message + otp3 + smsData.message2;
        String url = "https://www.txtguru.in/imobile/api.php?";


        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                System.out.println("Response Sms : "+ response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this,"Something Went Wrong",Toast.LENGTH_SHORT).show();
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
                params.put("dmobile", phone);
                params.put("message", message);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }


    private void sendMail(String fname1, String lname1, String email1, String otp1) {


        String subject = smsData.subject;
        String name = fname1 + " " + lname1;
        String message = smsData.message + otp1 + smsData.message2;
        String phone = mobile.getText().toString().trim();

        JavaMailRegisterOtp javaMailRegisterOtp = new JavaMailRegisterOtp(this, email1, subject,message);
        javaMailRegisterOtp.execute();


    }





    private boolean isValid(String email, String password){
        Pattern emailPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
        Matcher emailMatcher = emailPattern.matcher(email);
        if(email.isEmpty()){
            showmessage("Email is empty");
            return false;
        }
        else if(!emailMatcher.matches()){
            showmessage("Please enter valid Email");
            return false;
        }
        else if(password.isEmpty()){
            showmessage("Password is empty");
            return false;
        }

        return true;
    }

    private void showmessage(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }


    public void register(View view) {
        startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
    }

    public void forgot(View view) {
//        startActivity(new Intent(LoginActivity.this, ForgotPassword.class));
    }
}


