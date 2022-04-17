package com.nine__square.property.Avtivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.nine__square.property.Utils.JavaMailLead;
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

public class CallBackForm extends AppCompatActivity {

    private ProgressDialog progressDialog;
    EditText fullname, mobile, email, city, otpedit;
    String getpropertyid ,getfname, getlname, getmobile, getemail, getcity, getinstance, getinstancemobile,getinstanceemail,getinstancefullname, otp;
    Button callback, verification;
    TextInputLayout emaillayout , mobilelayout, citylayout, fullnamelayout, otplayout;
    TextView textview;

    SmsData smsData;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_back_form);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Contact Details");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

        smsData = new SmsData();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);



        fullname = findViewById(R.id.fullname);
        email = findViewById(R.id.email);
        city = findViewById(R.id.city);
        mobile = findViewById(R.id.mobile);
        callback = findViewById(R.id.callback);


        verification = findViewById(R.id.verification);
        otpedit = findViewById(R.id.otp);
        otplayout = findViewById(R.id.otplayout);
        textview = findViewById(R.id.textview);


        emaillayout = findViewById(R.id.emaillayout);
        mobilelayout = findViewById(R.id.mobilelayout);
        citylayout = findViewById(R.id.citylayout);
        fullnamelayout = findViewById(R.id.fullnamelayout);

//        emaillayout.setVisibility(View.GONE);
//        mobilelayout.setVisibility(View.GONE);
//        citylayout.setVisibility(View.GONE);
//        fullnamelayout.setVisibility(View.GONE);


        Random random = new Random();
        otp = String.format("%04d", random.nextInt(10000));

        verification.setVisibility(View.GONE);
        otplayout.setVisibility(View.GONE);
        textview.setVisibility(View.GONE);

        getpropertyid = getIntent().getStringExtra("propertyid");
        getinstance = getIntent().getStringExtra("instance");
        getinstancemobile = getIntent().getStringExtra("mobile");
        getinstanceemail = getIntent().getStringExtra("email");
        getinstancefullname = getIntent().getStringExtra("fullname");


        System.out.println("get emailll :: " + getinstanceemail+ "  "+getpropertyid +"   "+  getinstancefullname+ " "+getinstancemobile);



        getfname = VolleySingleton.getInstance(getApplicationContext()).fname();
        getlname = VolleySingleton.getInstance(getApplicationContext()).lname();
        getmobile = VolleySingleton.getInstance(getApplicationContext()).mobile();
        getemail = VolleySingleton.getInstance(getApplicationContext()).email();
        getcity = VolleySingleton.getInstance(getApplicationContext()).city();

        if(VolleySingleton.getInstance(getApplicationContext()).isLogin()){

            fullname.setFocusable(false);
            fullname.setFocusableInTouchMode(false);
            fullname.setClickable(false);


            email.setFocusable(false);
            email.setFocusableInTouchMode(false);
            email.setClickable(false);


            city.setFocusable(false);
            city.setFocusableInTouchMode(false);
            city.setClickable(false);


            mobile.setFocusable(false);
            mobile.setFocusableInTouchMode(false);
            mobile.setClickable(false);
        }

        if(getinstance.equals("share")){
            callback.setText("Share Property");
        }

        if(getfname != null){
            fullname.setText(getfname+" "+getlname);
            mobile.setText(getmobile);
            email.setText(getemail);
            city.setText(getcity);
        }




        callback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String city1, fullname1, mobile1, email1;

                city1 = city.getText().toString().trim();
                fullname1 = fullname.getText().toString().trim();
                mobile1 = mobile.getText().toString().trim();
                email1 = email.getText().toString().trim();

                if(isValid(fullname1, mobile1, email1, city1)){
                    sendcallback(city1, fullname1, mobile1, email1, otp);
                }

            }
        });



        verification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String city1, fullname1, mobile1, email1, otpget;

                otpget = otpedit.getText().toString().trim();
                city1 = city.getText().toString().trim();
                fullname1 = fullname.getText().toString().trim();
                mobile1 = mobile.getText().toString().trim();
                email1 = email.getText().toString().trim();


                if(otpget.isEmpty()){
                    showmessage("please enter otp...");
                }else{
                    verifcationsend(otpget, city1, fullname1, mobile1, email1);
                }
            }
        });

















    }


    private void contactpropertystatus(final String mobile1, final String email1) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.contactpropertystatus, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("response :: "+ response);
                try {
                    JSONObject obj = new JSONObject(response);
                    if(obj.getBoolean("error")){
                        System.out.println("");
                    }else{

                    }

                } catch (JSONException e) {
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
                params.put("propertyid", getpropertyid);
                params.put("email", email1);
                params.put("mobile", mobile1);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }


    private void verifcationsend(final String otpget, final String city1, final String fullname1, final String mobile1, final String email1) {

        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.contactpropertyverify, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                System.out.println("response :: "+ response);
                try {
                    JSONObject obj = new JSONObject(response);
                    if(obj.getBoolean("error")){
                        showmessage("Incorrect OTP");
                    }else{

                        contactpropertystatus(mobile1,email1);

                            showmessage(obj.getString("message"));

                            sendpropertyDeatailsToowner(email1,fullname1, mobile1, city1, getinstanceemail);
                            sendpropertyDeatailsToUser(getinstanceemail,getinstancefullname, getinstancemobile, "",email1);

                            if(getinstance.equals("view")){
                                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + getinstancemobile));
                                startActivity(intent);
                                finish();
                            }

                            if(getinstance.equals("call")){
                                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + getinstancemobile));
                                startActivity(intent);
                                finish();

                            }
                            if(getinstance.equals("share")){
                                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                                sharingIntent.putExtra(Intent.EXTRA_TEXT, " http://www.9squareproperty.com/property/"+ getpropertyid);
                                sharingIntent.setType("text/plain");
                                startActivity(Intent.createChooser(sharingIntent, "Share Request Post"));
                                finish();
                            }


                    }

                } catch (JSONException e) {
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
                params.put("propertyid", getpropertyid);
                params.put("email", email1);
                params.put("mobile", mobile1);
                params.put("city", city1);
                params.put("fullname", fullname1);
                params.put("otp", otpget);

                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }


    private void sendcallback(final String city1, final String fullname1, final String mobile1, final String email1, final String otp1) {
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.contactproperty, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                System.out.println("response :: "+ response);
                try {
                    JSONObject obj = new JSONObject(response);
                    if(obj.getBoolean("error")){
                        showmessage(obj.getString("message"));
                    }else{

                        if(VolleySingleton.getInstance(getApplicationContext()).isLogin()){
                            showmessage(obj.getString("message"));
                            contactpropertystatus(mobile1,email1);

                            sendpropertyDeatailsToowner(email1,fullname1, mobile1, city1,getinstanceemail);
                            sendpropertyDeatailsToUser(getinstanceemail,getinstancefullname, getinstancemobile, "",email1);

                            if(getinstance.equals("view")){
                                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + getinstancemobile));
                                startActivity(intent);
                                finish();


                            }

                            if(getinstance.equals("call")){
                                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + getinstancemobile));
                                startActivity(intent);
                                finish();

                            }
                            if(getinstance.equals("share")){
                                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                                sharingIntent.putExtra(Intent.EXTRA_TEXT, " http://www.9squareproperty.com/property/"+ getpropertyid);
                                sharingIntent.setType("text/plain");
                                startActivity(Intent.createChooser(sharingIntent, "Share Request Post"));
                                finish();

                            }
                        }else{

                            showmessage("Otp hs been sent to your mail");

                            sendMail(otp1);
                            sendotptomobile(otp1);
                            emaillayout.setVisibility(View.GONE);
                            mobilelayout.setVisibility(View.GONE);
                            citylayout.setVisibility(View.GONE);
                            fullnamelayout.setVisibility(View.GONE);
                            callback.setVisibility(View.GONE);

                            verification.setVisibility(View.VISIBLE);
                            otplayout.setVisibility(View.VISIBLE);
                            textview.setVisibility(View.VISIBLE);

                        }





                    }

                } catch (JSONException e) {
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
                params.put("propertyid", getpropertyid);
                params.put("email", email1);
                params.put("mobile", mobile1);
                params.put("city", city1);
                params.put("fullname", fullname1);
                params.put("otp", otp1);

                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    private void sendMail(String otp1) {

        String mail = email.getText().toString().trim();

        String subject = smsData.subject;

        String name = fullname.getText().toString().trim();
        String message = smsData.message + otp1 + smsData.message2;
        String phone = mobile.getText().toString().trim();

        JavaMailRegisterOtp javaMailRegisterOtp = new JavaMailRegisterOtp(this, mail, subject,message);
        javaMailRegisterOtp.execute();


    }


    private void sendpropertyDeatailsToUser(String useremail, String fullname, String mobile, String city, String sendingmail) {

        String name = fullname;
        String phone = mobile;
        String subject = "9 Square Property Notification";
        String message = "[#] You got this email because you have viewed detail of property holder\n" +
                "\n" +
                "[#] Person name is: "+fullname+"\n" +
                "\n" +
                "[#] Person email id is: "+useremail+"\n" +
                "\n" +
                "[#] Person phone no is: "+mobile+"\n" +
                "\n" +
                "[#] Contact for any query @www.9squareproperty.com/contact";

        JavaMailLead javaMailLead = new JavaMailLead(this, sendingmail, subject,message);
        javaMailLead.execute();


    }



    private void sendpropertyDeatailsToowner(String useremail, String fullname, String mobile, String city, String sendingmail) {

        String name = fullname;
        String phone = mobile;
        String subject = "9 Square Property Notification";

        String message = "[#] You got this email because someone viewed your detail\n" +
                "\n" +
                "[#] Person name is: "+fullname+"\n" +
                "\n" +
                "[#] Person email id is: "+useremail+"\n" +
                "\n" +
                "[#] Person phone no is: "+mobile+"\n" +
                "\n" +
                "[#] Person city: "+city+"\n" +
                "\n" +
                "[#] Contact for any query @www.9squareproperty.com/contact";


        JavaMailLead javaMailLead = new JavaMailLead(this, sendingmail, subject,message);
        javaMailLead.execute();


    }


    private void sendotptomobile(String otp2) {

        final String phone = mobile.getText().toString().trim();

        String subject = smsData.subject;
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
                Toast.makeText(CallBackForm.this,"Something Went Wrong",Toast.LENGTH_SHORT).show();
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




    private boolean isValid(String fullname1, String mobile1, String email1, String city1){
        Pattern emailPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
        Matcher emailMatcher = emailPattern.matcher(email1);


        if(fullname1.isEmpty()){
            fullname.setError("First name is empty");
            return false;
        }
        else if(email1.isEmpty()){
            email.setError("Please select Your City");
            return false;
        }
        else if(!emailMatcher.matches()){
            email.setError("Please enter valid Email");
            return false;
        }
        else if(mobile1.isEmpty()){
            mobile.setError("Phone number is empty");
            return false;
        }
        else if(mobile1.length() != 10){
            mobile.setError("Invalid Mobile Number, Number should be of 10 Digits");
            return false;
        }

        else if(city1.isEmpty()){
            city.setError("Please select Your City");
            return false;
        }


        return true;

    }





    public void showmessage(String m){
        Toast.makeText(this, m, Toast.LENGTH_SHORT).show();
    }

}
