package com.nine__square.property.Fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.nine__square.property.Avtivities.MyPropertyActivity;
import com.nine__square.property.R;
import com.nine__square.property.Utils.Endpoints;
import com.nine__square.property.Utils.ListingData;
import com.nine__square.property.Utils.SmsData;
import com.nine__square.property.Utils.VolleySingleton;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.app.Activity.RESULT_OK;


public class AddImages extends Fragment {

    ImageView propertyimage;
    Button pickimage, uploadimage, next, previous;
    ArrayList<String> bitmaps = new ArrayList<String>();
    ArrayList<Uri> imageadd = new ArrayList<Uri>();
    String encodedImage;
    int position =0;
    private ProgressDialog progressDialog;
    String propertyid;

    Spinner imgtypespin;
    String imgtype;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_add_images, container, false);


        Toolbar toolbar = v.findViewById(R.id.toolbar);
        toolbar.setTitle("Add Images");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        propertyid = ListingData.getInstance(getContext()).getpropertyid();




        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);


        propertyimage = v.findViewById(R.id.propertyimage);
        pickimage = v.findViewById(R.id.pickimage);
        uploadimage = v.findViewById(R.id.uploadimage);
        next = v.findViewById(R.id.next);
        previous = v.findViewById(R.id.previous);
        imgtypespin = v.findViewById(R.id.imgtypespin);

        String[] arraySpinner = new String[] {
                "Front Image","More Images"
        };


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        imgtypespin.setAdapter(adapter);


        imgtypespin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                // TODO Auto-generated method stub
                imgtype = imgtypespin.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });



        pickimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    bitmaps.clear();
                    imageadd.clear();
                    Dexter.withActivity(getActivity()).withPermission(READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
                        @Override
                        public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                            if(imgtype.equals("Front Image")){
                                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                                intent.setType("image/*");
                                startActivityForResult(Intent.createChooser(intent, "select image"),1);
                            }else {
                                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                                intent.setType("image/*");
                                startActivityForResult(Intent.createChooser(intent, "select image"), 1);
                            }

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

            }
        });

        pickimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    bitmaps.clear();
                    imageadd.clear();
                    Dexter.withActivity((Activity) getContext()).withPermission(READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
                        @Override
                        public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                            if(imgtype.equals("Front Image")){
                                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                                intent.setType("image/*");
                                startActivityForResult(Intent.createChooser(intent, "select image"),1);
                            }else {
                                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                                intent.setType("image/*");
                                startActivityForResult(Intent.createChooser(intent, "select image"), 1);
                            }

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

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position < imageadd.size() -1 ){
                    position++;
                    propertyimage.setImageURI(imageadd.get(position));
                }else{
                    Toast.makeText(getContext(), "No More Images....", Toast.LENGTH_SHORT).show();
                }
            }
        });


        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(position > 0){
                    position--;
                    propertyimage.setImageURI(imageadd.get(position));
                }else{
                    Toast.makeText(getContext(), "No Previous Images", Toast.LENGTH_SHORT).show();
                }

            }
        });

        uploadimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bitmaps.size() == 0){
                    showmessage("Please Select Property Images ");
                }else{
                    if(imgtype.equals("Front Image")){
                        uploadFrontImg();
                    }else{
                        postimage();
                    }                }
            }
        });




        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 1 && resultCode == RESULT_OK && data != null){


            ClipData clipData = data.getClipData();
            if(clipData != null){
                for(int i = 0; i < clipData.getItemCount(); i++){
                    Uri Imageuri = clipData.getItemAt(i).getUri();
                    try {
                        InputStream is = getContext().getContentResolver().openInputStream(Imageuri);
                        Bitmap bitmap = BitmapFactory.decodeStream(is);
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG,19,stream);
                        byte[] imageBytes = stream.toByteArray();
                        encodedImage = String.valueOf(Base64.encodeToString(imageBytes, Base64.DEFAULT));
                        bitmaps.add(encodedImage);
                        imageadd.add(Imageuri);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    propertyimage.setImageURI(imageadd.get(0));
                    position = 0;
                }
            }else {
                Uri imageuri = data.getData();
                try {
                    InputStream is = getContext().getContentResolver().openInputStream(imageuri);
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG,19,stream);
                    byte[] imageBytes = stream.toByteArray();
                    encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                    bitmaps.add(encodedImage);
                    imageadd.add(imageuri);


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                propertyimage.setImageURI(imageadd.get(0));
                position = 0;


            }


        }

        super.onActivityResult(requestCode, resultCode, data);

    }


    private void uploadFrontImg() {

        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.postfrontimage, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                System.out.println("params123 response :: "+ response);
                progressDialog.dismiss();



                try {
                    JSONObject obj = new JSONObject(response);
                    if(obj.getBoolean("error")){
                        showmessage(obj.getString("message"));
                    }else{
                        showmessage(obj.getString("message"));
//                        startActivity(getActivity().getIntent());
                        startActivity(new Intent(getContext(), MyPropertyActivity.class));
                        getActivity().finish();
//
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getContext(),"Something Went Wrong",Toast.LENGTH_SHORT).show();
                Log.d("VOLLEY", String.valueOf(error));
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                SmsData smsData = new SmsData();
                Map<String, String> params = new HashMap<>();
                params.put("header", smsData.token);

                for(int i = 0; i < bitmaps.size(); i++){
                    params.put("size", String.valueOf(bitmaps.size()));
                    params.put("userid", VolleySingleton.getInstance(getContext()).id());
                    params.put("propertyid", propertyid);
                    params.put("image"+i, bitmaps.get(i));

                }

                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
    }

    private void postimage(){

        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.postmultipleimage, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                System.out.println("params123 response :: "+ response);
                progressDialog.dismiss();



                try {
                    JSONObject obj = new JSONObject(response);
                    if(obj.getBoolean("error")){
                        showmessage(obj.getString("message"));
                    }else{
                        showmessage(obj.getString("message"));
//                        startActivity(getActivity().getIntent());
                        getActivity().getSupportFragmentManager().popBackStack();
//
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getContext(),"Something Went Wrong",Toast.LENGTH_SHORT).show();
                Log.d("VOLLEY", String.valueOf(error));
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                SmsData smsData = new SmsData();
                Map<String, String> params = new HashMap<>();
                params.put("header", smsData.token);

                for(int i = 0; i < bitmaps.size(); i++){
                    params.put("size", String.valueOf(bitmaps.size()));
                    params.put("userid", VolleySingleton.getInstance(getContext()).id());
                    params.put("propertyid", propertyid);
                    params.put("image"+i, bitmaps.get(i));

                }

                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
    }

    private void showmessage(String msg){
        Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
    }


}