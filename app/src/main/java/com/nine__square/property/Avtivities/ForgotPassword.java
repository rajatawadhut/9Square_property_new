package com.nine__square.property.Avtivities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
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

import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ForgotPassword extends AppCompatActivity {
    String otp, otp1;
    EditText otpget, password, email, password2, number;
    Button chpassword, sendotpv;
    private ProgressDialog progressDialog;
    TextInputLayout passwordlay, otplay, emaillay, passwordlay2,numberlay;
    TextView resendotp, timer;
    RadioButton mobiler, emailr;
    LinearLayout radio;
    String selected;
    String emailp="", mobilep="";
    SmsData smsData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        if(VolleySingleton.getInstance(getApplicationContext()).isLogin()){
            startActivity(new Intent(this,MainActivity.class));
            ForgotPassword.this.finish();
        }

        smsData = new SmsData();
        chpassword = findViewById(R.id.chpassword);
        email = findViewById(R.id.email);
        resendotp = findViewById(R.id.resendotp);
        timer = findViewById(R.id.timer);


        otpget = findViewById(R.id.otp);
        sendotpv = findViewById(R.id.sendotpv);
        password = findViewById(R.id.password);
        password2 = findViewById(R.id.password2);
        mobiler = findViewById(R.id.mobiler);
        emailr = findViewById(R.id.emailr);
        radio = findViewById(R.id.radio);
        number = findViewById(R.id.number);


        emaillay = findViewById(R.id.emaillay);
        otplay = findViewById(R.id.otplay);
        passwordlay = findViewById(R.id.passwordlay);
        passwordlay2 = findViewById(R.id.passwordlay2);
        numberlay = findViewById(R.id.numberlay);


        passwordlay.setVisibility(View.GONE);
        passwordlay2.setVisibility(View.GONE);
        timer.setVisibility(View.GONE);
        otplay.setVisibility(View.GONE);
        chpassword.setVisibility(View.GONE);
        resendotp.setVisibility(View.GONE);



        emailr.setChecked(true);
        selected = "email";
        numberlay.setVisibility(View.GONE);


        emailr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected = "email";
                emaillay.setVisibility(View.VISIBLE);
                numberlay.setVisibility(View.GONE);

            }
        });

        mobiler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected = "mobile";
                numberlay.setVisibility(View.VISIBLE);
                emaillay.setVisibility(View.GONE);
            }
        });








        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);

        Random random = new Random();
        otp = String.format("%04d", random.nextInt(10000));


        sendotpv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selected.equals("email")){
                    emailp = email.getText().toString().trim();
                    emailp.toLowerCase();
                    if(isvalid(emailp)){
//                        Toast.makeText(ForgotPassword.this, "OTP has Been Send To your Email Please Wait Few Minutes.....", Toast.LENGTH_LONG).show();
                        sendotp(emailp,otp);

                    }
                }else{
                    mobilep = number.getText().toString().trim();
                    if(mobilep.isEmpty()){
                        showmessage("Please Enter the number");
                    }else {
//                        Toast.makeText(ForgotPassword.this, "OTP has Been Send To your Email Please Wait Few Minutes.....", Toast.LENGTH_LONG).show();
                        sendotpmobile(mobilep,otp);
                    }

                }

            }
        });





        chpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String otpv, passwordv, emailv, passwordv2;
                emailp = email.getText().toString().trim();
                emailp.toLowerCase();
                otpv = otpget.getText().toString().trim();
                passwordv = password.getText().toString().trim();
                passwordv2 = password2.getText().toString().trim();

                if(valid(otpv, passwordv, passwordv2)){

                    sendpassword(otpv,passwordv);
                }
            }
        });

        resendotp.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View v) {
                starttimer();
                if(selected.equals("email")){
                    Random random = new Random();
                    otp1 = String.format("%04d", random.nextInt(10000));
                    sentotpserver(otp1);
                }else{
                    mobilep = number.getText().toString().trim();
                    Random random = new Random();
                    otp1 =  String.format("%04d", random.nextInt(10000));
                    sentotpservermobile(otp1);
                    sendotptomobile2(mobilep,otp1);
                }

            }
        });

    }

    private void sentotpservermobile(final String otp155) {
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.resendotpmobile, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                System.out.println("response :: " +response);
                progressDialog.dismiss();
                try {
                    JSONObject obj = new JSONObject(response);
                    if(obj.getBoolean("error")){
                        showmessage(obj.getString("message"));
                    }else{
                        showmessage(obj.getString("message"));
                        mobilep = obj.getString("mobile");
                        emailp = obj.getString("email");
                        sendMail1(otp155,obj.getString("email") );
                        sendotptomobile2(mobilep,otp155);



                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(ForgotPassword.this,"Something Went Wrong",Toast.LENGTH_SHORT).show();
//                Log.d("VOLLEY",error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                SmsData smsData = new SmsData();
                Map<String, String> params = new HashMap<>();
                params.put("header", smsData.token);
                params.put("otp", otp155);
                params.put("mobile", number.getText().toString().trim());
                params.put("selected", selected);
                return params;
            }
        };
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);


    }

    private void sendotptomobile2(final String mobilep, String otp2) {


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
                Toast.makeText(ForgotPassword.this,"Something Went Wrong",Toast.LENGTH_SHORT).show();
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
                params.put("dmobile", mobilep);
                params.put("message", message);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }


    private void sendotpmobile(final String mobilev, final String otpm) {
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.forgotpasswordotp, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                System.out.println("response :: " +response);
                progressDialog.dismiss();
                try {
                    JSONObject obj = new JSONObject(response);

                    if(obj.getBoolean("error")){
                        showmessage(obj.getString("message"));
                    }else{
                        passwordlay.setVisibility(View.VISIBLE);
                        passwordlay2.setVisibility(View.VISIBLE);
                        otplay.setVisibility(View.VISIBLE);
                        chpassword.setVisibility(View.VISIBLE);
//                        resendotp.setVisibility(View.VISIBLE);
                        radio.setVisibility(View.GONE);
                        emaillay.setVisibility(View.GONE);
                        numberlay.setVisibility(View.GONE);
                        sendotpv.setVisibility(View.GONE);
                        showmessage(obj.getString("message"));





                        sendMailmobile(obj.getString("email"),otpm);
                        sendotptomobile2(mobilev,otpm);



                        starttimer();





                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(ForgotPassword.this,"Something Went Wrong",Toast.LENGTH_SHORT).show();
//                Log.d("VOLLEY",error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                SmsData smsData = new SmsData();
                Map<String, String> params = new HashMap<>();
                params.put("header", smsData.token);
                params.put("otp", otpm);
                params.put("mobile", mobilev);
                params.put("selected", selected);
                return params;
            }
        };
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }


    private void sentotpserver(final String otp12) {
        final String email1 = email.getText().toString().trim();
        email1.toLowerCase();

        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.resendotpemail, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                System.out.println("response :: " +response);
                progressDialog.dismiss();
                try {
                    JSONObject obj = new JSONObject(response);
                    if(obj.getBoolean("error")){
                        showmessage(obj.getString("message"));
                    }else{

                        showmessage(obj.getString("message"));
                        emailp = obj.getString("email");
                        mobilep = obj.getString("mobile");

                        sendMail1(otp12,obj.getString("email") );
                        sendotptomobile2(mobilep,otp12);



                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(ForgotPassword.this,"Something Went Wrong",Toast.LENGTH_SHORT).show();
//                Log.d("VOLLEY",error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                SmsData smsData = new SmsData();
                Map<String, String> params = new HashMap<>();
                params.put("header", smsData.token);
                params.put("otp", otp12);
                params.put("email", email1);
                return params;
            }
        };
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);


    }

    private void sendpassword(final String otpv, final String passwordv) {
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.forgotpassword, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                System.out.println("response :: " +response);
                progressDialog.dismiss();
                try {
                    JSONObject obj = new JSONObject(response);
                    if(obj.getBoolean("error")){
                        showmessage(obj.getString("message"));
                    }else{

                        showmessage(obj.getString("message"));
                        startActivity(new Intent(ForgotPassword.this, LoginActivity.class));
                        ForgotPassword.this.finish();


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(ForgotPassword.this,"Something Went Wrong",Toast.LENGTH_SHORT).show();
//                Log.d("VOLLEY",error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                SmsData smsData = new SmsData();
                Map<String, String> params = new HashMap<>();
                params.put("header", smsData.token);
                params.put("otp", otpv);
                params.put("email", emailp);
                params.put("mobile", mobilep);
                params.put("password", passwordv);
                params.put("selected", selected);
                System.out.println("paramsssss: "+ params);
                return params;
            }
        };
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);


    }

    private boolean isvalid(String emailv) {
        Pattern emailPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
        Matcher emailMatcher = emailPattern.matcher(emailv);
        if(emailv.isEmpty()){
            email.setError("Please enter Email");
            return false;
        }
        else if(!emailMatcher.matches()){
            email.setError("Please enter valid Email");
            return false;
        }
        return true;
    }

    private boolean valid(String otpv, String passwordv , String passwordv2) {

//        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";
//
//        Pattern passpattern = Pattern.compile(PASSWORD_PATTERN);
//        Matcher passmatcher1 = passpattern.matcher(passwordv);
//        Matcher passmatcher2 = passpattern.matcher(passwordv2);

        if(otpv.isEmpty()){
            otpget.setError("Please Enter OTP");
            return false;
        }
        else if(passwordv.isEmpty()){
            password.setError("Please Enter Password");
            return false;
        }
        else if(passwordv.length() < 8){
            password.setError("The password must be at least 8 characters long and include a number and letter without spaces");
//            showmessage("The password must be at least 8 characters long and include a number," +
//                    " lowercase letter, uppercase letter and special character (e.g. @, &amp;, #, ?)");
            return false;
        }
        else if(passwordv2.isEmpty()){
            password2.setError("Please Enter Confirm Password");
            return false;
        }
        else if(passwordv2.length() < 8){
            password2.setError("The password must be at least 8 characters long and include a number and letter without spaces");
//            showmessage("The password must be at least 8 characters long and include a number," +
//                    " lowercase letter, uppercase letter and special character (e.g. @, &amp;, #, ?)");
            return false;
        }
        else if(!passwordv.equals(passwordv2)){
            password2.setError("Password Mismatch");
            return false;
        }
        return true;
    }

    private void sendotp(final String email, final String otpg) {
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.forgotpasswordotp, new Response.Listener<String>() {

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

                        mobilep = obj.getString("mobile");

                        sendMail(otp);
                        sendotptomobile2(mobilep,otp);


                        passwordlay.setVisibility(View.VISIBLE);
                        passwordlay2.setVisibility(View.VISIBLE);
                        otplay.setVisibility(View.VISIBLE);
                        chpassword.setVisibility(View.VISIBLE);

//                        resendotp.setVisibility(View.VISIBLE);
                        radio.setVisibility(View.GONE);
                        emaillay.setVisibility(View.GONE);
                        numberlay.setVisibility(View.GONE);
                        sendotpv.setVisibility(View.GONE);

                        starttimer();




                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(ForgotPassword.this,"Something Went Wrong",Toast.LENGTH_SHORT).show();
//                Log.d("VOLLEY",error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                SmsData smsData = new SmsData();
                Map<String, String> params = new HashMap<>();
                params.put("header", smsData.token);
                params.put("otp", otpg);
                params.put("email", email);
                params.put("selected", selected);
                return params;
            }
        };
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }

    private void sendMail(String otp) {

        String mail = email.getText().toString().trim();
        mail.toLowerCase();
        String subject = smsData.subject;
        String message = smsData.message + otp + smsData.message2;

        JavaMailRegisterOtp javaMailRegisterOtp = new JavaMailRegisterOtp(this, mail, subject,message);
        javaMailRegisterOtp.execute();
    }


    private void sendMailmobile(String email, String otpm) {
        String mail = email;

        String subject = smsData.subject;
        String message = smsData.message + otpm + smsData.message2;

        JavaMailRegisterOtp javaMailRegisterOtp = new JavaMailRegisterOtp(this, mail, subject,message);
        javaMailRegisterOtp.execute();
    }


    private void sendMail1(String otp12, String emaily) {

        String mail = emaily;

        String subject = smsData.subject;
        String message = smsData.message + otp12 + smsData.message2;

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

