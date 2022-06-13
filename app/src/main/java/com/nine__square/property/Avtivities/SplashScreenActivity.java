package com.nine__square.property.Avtivities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.nine__square.property.BuildConfig;
import com.nine__square.property.R;
import com.nine__square.property.Utils.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class SplashScreenActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    TextView retry, textview;
    ImageView img;
    LinearLayout nodata;


    String API= "https://9squareproperty.com/9squareandroid/getV2Property.php";
//    String API= "https://9squareproperty.com/9squareandroid/getVProperty.php";
//    String API= "https://unprevented-marches.000webhostapp.com/apanagharDB/getversionupdate2.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Waiting For Internet...");
        progressDialog.setCancelable(false);

        retry = findViewById(R.id.retry);
/*        img = findViewById(R.id.img);
        textview = findViewById(R.id.textview);
        nodata = findViewById(R.id.nodata);*/

        retry.setVisibility(View.GONE);


   /*     Animation animation = AnimationUtils.loadAnimation(this, R.anim.slidup);
        nodata.startAnimation(animation);
*/

        if(!isNetworkAvailable()){
            Toast.makeText(this, "Please Check Your Internet Connection...", Toast.LENGTH_SHORT).show();
        }






        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ConnectivityManager manager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activenetwork = manager.getActiveNetworkInfo();

                if (activenetwork == null)
                {
                    retry.setVisibility(View.VISIBLE);
//                    Toast.makeText(SplashScreenActivity.this, "Please Start Internet", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(activenetwork.isConnected())
                    {
                        retry.setVisibility(View.GONE);

                        showupdate();
                        /*Intent splash = new Intent(SplashScreenActivity.this, MainActivity.class);
                        splash.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(splash);
                        finish();*/
                    }
                    else
                    {
                        retry.setVisibility(View.VISIBLE);
//                        Toast.makeText(SplashScreenActivity.this, "Please Start Internet", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        },250);


            retry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recreate();

                }
            });







    }




    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }



    public void showupdate(){

//        Intent splash = new Intent(SplashScreenActivity.this, MainActivity.class);
//        splash.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//        startActivity(splash);
//        finish();


        final String userid1;
        userid1 = "1";


        StringRequest stringRequest = new StringRequest(Request.Method.GET, API, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                System.out.println("response :: " +response);
//                progressDialog.dismiss();
                try {
                    JSONObject obj = new JSONObject(response);

                    System.out.println(" dataataataaVersion:; "+ obj.getString("number"));

                    String counter = obj.getString("number");

                    if(counter.equals(BuildConfig.VERSION_NAME)){
                        Intent splash = new Intent(SplashScreenActivity.this, MainActivity.class);
                        splash.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(splash);
                        finish();
                    }






                } catch (JSONException e) {
                    System.out.println(" dataataataaVersion:; "+ e.getMessage());

                    e.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(" dataataataaVersion:; "+ error.getMessage());

//                progressDialog.dismiss();
//                Toast.makeText(SplashScreenActivity.this,"Something Went Wrong",Toast.LENGTH_SHORT).show();
//                Log.d("VOLLEY",error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("userid", userid1);
                return params;
            }
        };
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }


}
