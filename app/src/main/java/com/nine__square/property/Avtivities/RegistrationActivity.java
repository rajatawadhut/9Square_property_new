package com.nine__square.property.Avtivities;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputLayout;
import com.nine__square.property.R;
import com.nine__square.property.Utils.Endpoints;
import com.nine__square.property.Utils.JavaMailRegisterOtp;
import com.nine__square.property.Utils.SmsData;
import com.nine__square.property.Utils.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity {

    private EditText fnameEt, lnameEt, passwordEt, mobileEt, emailEt, addressEt, designationEt, experienceEt, firmEt, cityEt, countryEt,stateEt;
    private Button registration, button_hide;
    private AutoCompleteTextView districtEt;
    private ProgressDialog progressDialog;
    private RadioButton rcustomer, rdeveloper, rbroker;
    private ImageView profile_image;
    private Bitmap bitmap;
    private String encodedImage;
    private String imageName;
    private String radio_selecton;
    private String firm, expe, otp;
    SmsData smsData;
    TextInputLayout citylay, statelay;




    @SuppressLint("DefaultLocale")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
//        progressDialog.setCancelable(false);

        smsData = new SmsData();



//        if(VolleySingleton.getInstance(getApplicationContext()).isLogin()){
//            startActivity(new Intent(this,HomeActivity.class));
//            RegistrationActivity.this.finish();
//        }

        fnameEt = findViewById(R.id.fname);
        lnameEt = findViewById(R.id.lname);
        passwordEt = findViewById(R.id.password);
        mobileEt = findViewById(R.id.number);
        emailEt = findViewById(R.id.email);
        cityEt =  findViewById(R.id.city);
        countryEt =  findViewById(R.id.country);
        districtEt =  findViewById(R.id.district);
        stateEt =  findViewById(R.id.state);
        addressEt = findViewById(R.id.address);
//        profile_image = findViewById(R.id.profile_image);
        registration = findViewById(R.id.register);
        citylay = findViewById(R.id.citylay);
        statelay = findViewById(R.id.statelay);


        countryEt.setText("India");
        stateEt.setText("Maharashtra");
        districtEt.setText("Nagpur");
        cityEt.setText("Nagpur");





        citylay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this, SearchCity.class));
            }
        });



        statelay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cityEt.setText("");
                VolleySingleton.getInstance(getApplicationContext()).setsearchcity("");
                startActivity(new Intent(RegistrationActivity.this, SearchState.class));
            }
        });

        cityEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this, SearchCity.class));
            }
        });



        stateEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cityEt.setText("");
                VolleySingleton.getInstance(getApplicationContext()).setsearchcity("");
                startActivity(new Intent(RegistrationActivity.this, SearchState.class));
            }
        });









        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname, lname, phone, password, email, district, state, city, address;
                fname = fnameEt.getText().toString().trim();
                lname = lnameEt.getText().toString().trim();
                email = emailEt.getText().toString().trim();
                email.toLowerCase();
                password = passwordEt.getText().toString().trim();
                phone = mobileEt.getText().toString().trim();
                district = districtEt.getText().toString().trim();
                state = stateEt.getText().toString().trim();
                city = cityEt.getText().toString().trim();
                address = addressEt.getText().toString().trim();





                if(isValid(fname, lname, email, password, phone, district, state, city, address)){
                    register(fname, lname, email, password, phone, district, state, city, address);
                }

            }
        });


        Random random = new Random();
        otp = String.format("%04d", random.nextInt(10000));








//        String[] cities = getResources().getStringArray(R.array.city_name);
//        ArrayAdapter<String> adapterCITY = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_list_item_1, cities);
//        cityEt.setAdapter(adapterCITY);
//
//
//        String[] bloodgroup = getResources().getStringArray(R.array.blood_gp);
//        ArrayAdapter<String> adapterBG = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_list_item_1, bloodgroup);
//        bloodgpEt.setAdapter(adapterBG);
//
//        if(VolleySingleton.getInstance(getApplicationContext()).isLogin()){
//            startActivity(new Intent(this,ProfileActivity.class));
//            RegisterActivity.this.finish();
//        }





//        profile_image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Dexter.withActivity(RegistrationActivity.this).withPermission(READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
//                    @Override
//                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
//                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//                        intent.setType("image/*");
//                        startActivityForResult(Intent.createChooser(intent, "select image"),1);
//                    }
//                    @Override
//                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
//                    }
//
//                    @Override
//                    public void onPermissionRationaleShouldBeShown(com.karumi.dexter.listener.PermissionRequest permissionRequest, PermissionToken permissionToken) {
//                        permissionToken.continuePermissionRequest();
//
//                    }
//
//                }).check();
//            }
//        }); profile_image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Dexter.withActivity(RegistrationActivity.this).withPermission(READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
//                    @Override
//                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
//                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//                        intent.setType("image/*");
//                        startActivityForResult(Intent.createChooser(intent, "select image"),1);
//                    }
//                    @Override
//                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
//                    }
//
//                    @Override
//                    public void onPermissionRationaleShouldBeShown(com.karumi.dexter.listener.PermissionRequest permissionRequest, PermissionToken permissionToken) {
//                        permissionToken.continuePermissionRequest();
//
//                    }
//
//                }).check();
//            }
//        });
    }

    //    private void register()
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 1 && resultCode == RESULT_OK && data != null){
            Uri filepath = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(filepath);
                bitmap = BitmapFactory.decodeStream(inputStream);
//                post_img.setImageBitmap(bitmap);
                Glide.with(RegistrationActivity.this).load(bitmap).into(profile_image);
                imageStore(bitmap);
                File f = new File(String.valueOf(filepath));
                imageName = f.getName();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void imageStore(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,10,stream);
        byte[] imageBytes = stream.toByteArray();
        encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        System.out.println("Encodedimage ::"+ encodedImage.length());

    }

    @Override
    protected void onResume() {

            if(VolleySingleton.getInstance(getApplicationContext()).getsearchcity() != null){
                String citysearch = VolleySingleton.getInstance(getApplicationContext()).getsearchcity();
                cityEt.setText(citysearch);
            }

            if(VolleySingleton.getInstance(getApplicationContext()).getsearchstate() != null){
                String statesearch = VolleySingleton.getInstance(getApplicationContext()).getsearchstate();
                stateEt.setText(statesearch);
            }
            super.onResume();
        }


    private void register(final String fname, final String lname, final String email, final String password, final String phone, final String district, final String state, final String city, final String address){
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.registration, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                System.out.println("response ::::: "+ response);
                progressDialog.dismiss();
                try {
                    JSONObject obj = new JSONObject(response);
                    if(obj.getBoolean("error")){
                        showmessage(obj.getString("message"));
                    }else{
                        showmessage(obj.getString("message"));
                        sendMail();

                        sendotptomobile();
                        Intent intent = new Intent(RegistrationActivity.this, RegistrationOTP.class);
                        intent.putExtra("email", email);
                        intent.putExtra("mobile",phone);
                        intent.putExtra("fname",fname);
                        intent.putExtra("lname",lname);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        finish();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegistrationActivity.this,"Something Went Wrong",Toast.LENGTH_SHORT).show();
                Log.d("VOLLEY", String.valueOf(error));
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                SmsData smsData = new SmsData();
                Map<String, String> params = new HashMap<>();
                params.put("header", smsData.token);
                params.put("fname", fname);
                params.put("lname", lname);
                params.put("email", email);
                params.put("password", password);
                params.put("mobile", phone);
                params.put("district", district);
                params.put("state", state);
                params.put("city", city);
                params.put("address", address);
                params.put("otp", otp);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }


    private void sendMail() {

        String mail = emailEt.getText().toString().trim();

        String subject = smsData.subject;

        String name = fnameEt.getText().toString().trim() + " " + lnameEt.getText().toString().trim();
        String message = smsData.message + otp + smsData.message2;
        String phone = mobileEt.getText().toString().trim();

        JavaMailRegisterOtp javaMailRegisterOtp = new JavaMailRegisterOtp(this, mail, subject,message);
        javaMailRegisterOtp.execute();


    }

    private void sendotptomobile() {

        String mail = emailEt.getText().toString().trim();

        String subject = "Otp Of 99Acres";
        String name = fnameEt.getText().toString().trim() + " " + lnameEt.getText().toString().trim();
        final String message = smsData.message + otp + smsData.message2;
        final String phone = mobileEt.getText().toString().trim();
        String url = "https://www.txtguru.in/imobile/api.php?";


        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                System.out.println("Response Sms : "+ response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegistrationActivity.this,"Something Went Wrong",Toast.LENGTH_SHORT).show();
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
                params.put("dmobile", mobileEt.getText().toString().trim());
                params.put("message", message);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }




    private boolean isValid(String fname, String lname,String email, String password, String phone, String district, String state, String city, String address){
        Pattern emailPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
        Matcher emailMatcher = emailPattern.matcher(email);

//        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";

//        Pattern passpattern = Pattern.compile(PASSWORD_PATTERN);
//        Matcher passmatcher = passpattern.matcher(password);

        if(fname.isEmpty()){
            fnameEt.setError("First name is empty");
            return false;
        }
        else if(lname.isEmpty()){
            lnameEt.setError("Last name is empty");
            return false;
        }
        else if(email.isEmpty()){
            emailEt.setError("Please enter Email");
            return false;
        }
        else if(!emailMatcher.matches()){
            emailEt.setError("Please enter valid Email");
            return false;
        }
        else if(phone.isEmpty()){
            mobileEt.setError("Phone number is empty");
            return false;
        }
        else if(phone.length() != 10){
            mobileEt.setError("Invalid Mobile Number, Number should be of 10 Digits");
            return false;
        }
        else if(state.isEmpty()){
            stateEt.setError("Please select Your State");
            return false;
        }
        else if(city.isEmpty()){
            cityEt.setError("Please select Your City");
            return false;
        }

        else if(address.isEmpty()){
            addressEt.setError("Please select Your Address");
            return false;
        }
        else if(password.isEmpty()){
            passwordEt.setError("Please select Your Password");
            showmessage("Please select Your Password");

            return false;
        }
        else if(password.length() < 8){
            passwordEt.setError("The password must be at least 8 characters long and include a number and letter without spaces");
//            showmessage("The password must be at least 8 characters long and include a number," +
//                    " lowercase letter, uppercase letter and special character (e.g. @, &amp;, #, ?)");
            return false;
        }

        return true;

    }


    private void showmessage(String msg){
        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show();
    }

    public void login(View view) {
        startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
        finish();
    }


//    public void login(View view) {
//        startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
//        RegistrationActivity.this.finish();
//    }


}



