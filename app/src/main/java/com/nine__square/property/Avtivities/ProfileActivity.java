package com.nine__square.property.Avtivities;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nine__square.property.R;
import com.nine__square.property.Utils.Endpoints;
import com.nine__square.property.Utils.SmsData;
import com.nine__square.property.Utils.VolleySingleton;

import com.google.android.material.textfield.TextInputLayout;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import de.hdodenhof.circleimageview.CircleImageView;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

public class ProfileActivity extends AppCompatActivity {
    private EditText fnameEt, lnameEt, passwordEt, mobileEt, emailEt, addressEt, designationEt, experienceEt, firmEt, docname, cityEt, countryEt,stateEt,districtEt;
    TextView imageno, images, selectimg, gotodoc;
    private Button registration, button_hide;
    private ProgressDialog progressDialog;
    private RadioButton rcustomer, rdeveloper, rbroker;
    private RadioGroup radio_btn;
    private CircleImageView profile_image;
    private Bitmap bitmap;
    private String encodedImage;
    private String encodedimage2;
    private String imageName;
    private String radio_selecton;
    private String firm, expe;
    private TextInputLayout firmlayout;
    TextInputLayout citylay, statelay;




    private Bitmap bitmapD;

    ArrayList<Uri>  bitmaps = new ArrayList<Uri>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("User Profile");
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


        fnameEt = findViewById(R.id.fname);
        lnameEt = findViewById(R.id.lname);
        mobileEt = findViewById(R.id.number);
        emailEt = findViewById(R.id.email);
        cityEt =  findViewById(R.id.city);
        countryEt =  findViewById(R.id.country);
        districtEt =  findViewById(R.id.district);
        stateEt =  findViewById(R.id.state);
        addressEt = findViewById(R.id.address);
//        gotodoc = findViewById(R.id.gotodoc);


        profile_image = findViewById(R.id.profile_image);
        registration = findViewById(R.id.register);
        citylay = findViewById(R.id.citylay);
        statelay = findViewById(R.id.statelay);



        fnameEt.setText(VolleySingleton.getInstance(getApplicationContext()).fname());
        lnameEt.setText(VolleySingleton.getInstance(getApplicationContext()).lname());
        mobileEt.setText(VolleySingleton.getInstance(getApplicationContext()).mobile());
        emailEt.setText(VolleySingleton.getInstance(getApplicationContext()).email());
        cityEt.setText(VolleySingleton.getInstance(getApplicationContext()).city());
        countryEt.setText("India");
        stateEt.setText(VolleySingleton.getInstance(getApplicationContext()).state());
        addressEt.setText(VolleySingleton.getInstance(getApplicationContext()).address());
//            experienceEt.setText(VolleySingleton.getInstance(getApplicationContext()).yrofexperience());
//        Glide.with(ProfileActivity.this).load(VolleySingleton.getInstance(this).profilepic()).into(profile_image);

        Glide.with(ProfileActivity.this)
                .load(VolleySingleton.getInstance(this).profilepic())
                .apply(RequestOptions
                        .placeholderOf(R.drawable.profileicon)
                        .dontAnimate()
                        .error(R.drawable.profileicon))
                .into(profile_image);



        citylay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, SearchCity.class));
            }
        });



        statelay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cityEt.setText("");
                VolleySingleton.getInstance(getApplicationContext()).setsearchcity("");
                startActivity(new Intent(ProfileActivity.this, SearchState.class));
            }
        });

        cityEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, SearchCity.class));
            }
        });



        stateEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cityEt.setText("");
                VolleySingleton.getInstance(getApplicationContext()).setsearchcity("");
                startActivity(new Intent(ProfileActivity.this, SearchState.class));
            }
        });






        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname, lname, phone, email, district, state, city, address;
                fname = fnameEt.getText().toString().trim();
                lname = lnameEt.getText().toString().trim();
                email = emailEt.getText().toString().trim();
                phone = mobileEt.getText().toString().trim();
                state = stateEt.getText().toString().trim();
                city = cityEt.getText().toString().trim();
                address = addressEt.getText().toString().trim();






                if(isValidDonor(fname, lname, email, phone, state, city, address)){
                    update(fname, lname, email, phone, state, city, address);
                }

            }
        });











        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withActivity(ProfileActivity.this).withPermission(READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                        intent.setType("image/*");
                        startActivityForResult(Intent.createChooser(intent, "select image"),1);
                    }
                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                    }
                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Uri filepath = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(filepath);
                bitmap = BitmapFactory.decodeStream(inputStream);
//                post_img.setImageBitmap(bitmap);
                Glide.with(ProfileActivity.this).load(bitmap).into(profile_image);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 10, stream);
                byte[] imageBytes = stream.toByteArray();
                encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                File f = new File(String.valueOf(filepath));
                imageName = f.getName();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {


            ClipData clipData = data.getClipData();
            if (clipData != null) {
                for (int i = 0; i < clipData.getItemCount(); i++) {
                    Uri Imageuri = clipData.getItemAt(i).getUri();

                    try {
                        bitmaps.add(Imageuri);
                    } catch (Exception e) {
                        Log.e("Error", "File select error", e);
                    }

                }

                selectimg.setVisibility(View.GONE);
                imageno.setVisibility(View.VISIBLE);
                images.setVisibility(View.VISIBLE);
                imageno.setText(String.valueOf(bitmaps.size()));
            } else {
                Uri imageuri = data.getData();

                try {
                    bitmaps.add(imageuri);
                } catch (Exception e) {
                    Log.e("Error", "File select error", e);
                }


                selectimg.setVisibility(View.GONE);
                imageno.setVisibility(View.VISIBLE);
                images.setVisibility(View.VISIBLE);
                imageno.setText(String.valueOf(bitmaps.size()));

            }

            super.onActivityResult(requestCode, resultCode, data);
        }}



    private void update (final String fname, final String lname, final String email,
                         final String phone, final String state, final String city,
                         final String address){
        progressDialog.show();

        if (encodedImage == null) {
            encodedimage2 = "1";
        } else {
            encodedimage2 = encodedImage;
        }
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.updateprofile, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.getBoolean("error")) {
                        showmessage(obj.getString("message"));
                    } else {
                        showmessage(obj.getString("message"));
//                        (String id, String fname, String lname, String mobile,
//                                String email, String address, String city,
//                                String district, String state,
//                                String profilepic, String date) {
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
                                Endpoints.base_url + obj.getString("profilepic"),
                                obj.getString("date")
                        );
//                            startActivity(new Intent(ProfileActivity.this, HomeActivity.class));
//                            ProfileActivity.this.finish();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ProfileActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                Log.d("VOLLEY", String.valueOf(error));
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                SmsData smsData = new SmsData();
                Map<String, String> params = new HashMap<>();
                params.put("header", smsData.token);
                params.put("fname", fname);
                params.put("lname", lname);
                params.put("email", email);
                params.put("mobile", phone);
                params.put("state", state);
                params.put("city", city);
                params.put("address", address);
                params.put("image", encodedimage2);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
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



//    private void getdoc() {
//        final String dealerid = VolleySingleton.getInstance(getApplicationContext()).id();
//        progressDialog.show();
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.getdoc, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                progressDialog.dismiss();
//                try {
//                    JSONObject obj = new JSONObject(response);
//
//                    System.out.println("response :: "+ response);
//
//                    String succes = obj.getString("success");
//                    JSONArray jsonArray = obj.getJSONArray("data");
//
//
//                    if(succes.equals("1")){
//                        multiDocDataModels.clear();
//                        for(int i=0; i<jsonArray.length();i++){
//                            JSONObject object = jsonArray.getJSONObject(i);
//                            String id =object.getString("id");
//                            String img =Endpoints.base_url + object.getString("img");
//                            String documentname = object.getString("docname");
//
//
//                            MultiDocDataModel multiDocDataModel = new MultiDocDataModel(id,img, documentname);
//                            multiDocDataModels.add(multiDocDataModel);
//                            multiDocAdapter.notifyDataSetChanged();
//                        }
//                    }
//
//                } catch (JSONException e) {
//                    progressDialog.dismiss();
//                    showmessage("Something wrong please check network connection"+e);
//                    System.out.println("problem ::"+e);
//                    e.printStackTrace();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                showmessage(error.getMessage());
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//
//                params.put("dealerid", dealerid);
//
//                return params;
//            }
//        };
//        stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
//    }


    private boolean isValidDonor (String fname, String lname, String email, String phone, String state, String city, String address){
        Pattern emailPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
        Matcher emailMatcher = emailPattern.matcher(email);


        if (fname.isEmpty()) {
            fnameEt.setError("First name is empty");
            return false;
        } else if (lname.isEmpty()) {
            lnameEt.setError("Last name is empty");
            return false;
        } else if (email.isEmpty()) {
            emailEt.setError("Please enter Email");
            return false;
        } else if (!emailMatcher.matches()) {
            emailEt.setError("Please enter valid Email");
            return false;
        } else if (phone.isEmpty()) {
            mobileEt.setError("Phone number is empty");
            return false;
        } else if (phone.length() != 10) {
            mobileEt.setError("Invalid Mobile Number, Number should be of 10 Digits");
            return false;
        } else if (state.isEmpty()) {
            stateEt.setError("Please select Your State");
            return false;
        } else if (city.isEmpty()) {
            cityEt.setError("Please select Your City");
            return false;
        } else if (address.isEmpty()) {
            addressEt.setError("Please select Your Address");
            return false;
        }

        return true;

    }


    private void showmessage (String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    public void login (View view){
        startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
        ProfileActivity.this.finish();
    }
//    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//    private void uploadImagesToServer() {
//
//
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(ApiService.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        progressDialog.show();
//
//
//
//        // create list of file parts (photo, video, ...)
//        List<MultipartBody.Part> parts = new ArrayList<>();
//
//        // create upload service client
//        ApiService service = retrofit.create(ApiService.class);
//
//        if (bitmaps != null) {
//            // create part for file (photo, video, ...)
//            for (int i = 0; i < bitmaps.size(); i++) {
//                parts.add(prepareFilePart("image"+i, bitmaps.get(i)));
//                System.out.println("imagefile :: "+ prepareFilePart("image"+i, bitmaps.get(i)));
//            }
//        }
//
//        // create a map of data to pass along
////        RequestBody description = createPartFromString("www.androidlearning.com");
//        RequestBody size = createPartFromString(""+parts.size());
//
//        // finally, execute the request
//        String docname1 = docname.getText().toString().trim();
//        RequestBody delerid = createPartFromString(VolleySingleton.getInstance(getApplicationContext()).id());
//        RequestBody docname = createPartFromString(docname1);
//        Call<ResponseBody> call = service.uploadMultiple(size, delerid ,docname,parts);
//
//        call.enqueue(new Callback<ResponseBody>() {
//
//            @Override
//            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
//                progressDialog.dismiss();
//
//                if(response.isSuccessful()) {
//                    Toast.makeText(ProfileActivity.this,
//                            "Document successfully uploaded!", Toast.LENGTH_SHORT).show();
//                    finish();
//                    startActivity(getIntent());
//                } else {
//                    Snackbar.make(findViewById(android.R.id.content),
//                            "Something wrong", Snackbar.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
//                progressDialog.dismiss();
//
//                Log.e("error", "Image upload failed!", t);
//                Snackbar.make(findViewById(android.R.id.content),
//                        "Image upload failed!", Snackbar.LENGTH_LONG).show();
//            }
//        });
//
//
//    }
//
//    @NonNull
//    private RequestBody createPartFromString(String descriptionString) {
//        return RequestBody.create(MediaType.parse(FileUtils.MIME_TYPE_TEXT), descriptionString);
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//    @NonNull
//    private MultipartBody.Part prepareFilePart(String partName, Uri fileUri) {
//        // use the FileUtils to get the actual file by uri
//        File file = FileUtils.getFile(this, fileUri);
//
//        // create RequestBody instance from file
//        okhttp3.RequestBody requestFile = RequestBody.create (MediaType.parse(FileUtils.MIME_TYPE_IMAGE), file);
//
//        // MultipartBody.Part is used to send also the actual file name
//        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
//    }


}


