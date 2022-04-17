package com.nine__square.property.Avtivities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nine__square.property.Adapter.ReviewAdapter;
import com.nine__square.property.AddPropertActivity.BasicDetails;
import com.nine__square.property.Helper.DateForMsgCounterNotification;
import com.nine__square.property.Models.ReviewDataModel;

import com.nine__square.property.R;
import com.nine__square.property.Utils.Endpoints;
import com.nine__square.property.Utils.ListingData;
import com.nine__square.property.Utils.SmsData;
import com.nine__square.property.Utils.VolleySingleton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestimonialsActivity extends AppCompatActivity {
    NavigationView nav;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    RatingBar ratingBar;
    EditText fname, lname, email,mobile, message;
    Button sendreview;
    TextInputLayout fnamelay, lnamelay,emaillay,phonelay, messagelay;
    RecyclerView recylerview;
    ImageView review;
    private List<ReviewDataModel> reviewDataModels;
    private ReviewAdapter reviewAdapter;
    private ProgressDialog progressDialog;
    ImageView footerh,footerl, footershort, footers;

    private LinearLayout homeefooter, latestfooter, searchfooter, shortlistedfooter, footer, addfooter;
    private TextView homeefooter1, latestfooter1, searchfooter1, shortlistedfooter1;
    String userreviewid="", userreview="", userrating="", valueid="";
    int a = 1;

    ArrayList<String> getmsgcounter;
    TextView tv_counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testimonials);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);





        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        email = findViewById(R.id.email);
        mobile = findViewById(R.id.mobile);
        message = findViewById(R.id.message);
        ratingBar = findViewById(R.id.ratingBar);
        sendreview = findViewById(R.id.sendreview);



        fnamelay = findViewById(R.id.fnamelay);
        lnamelay = findViewById(R.id.lnamelay);
        emaillay = findViewById(R.id.emaillay);
        phonelay = findViewById(R.id.phonelay);
        messagelay = findViewById(R.id.messagelay);
        recylerview = findViewById(R.id.recylerview);
        review = findViewById(R.id.reviewbtn);
        footer = findViewById(R.id.footer);

        fnamelay.setVisibility(View.GONE);
        lnamelay.setVisibility(View.GONE);
        emaillay.setVisibility(View.GONE);
        phonelay.setVisibility(View.GONE);
        messagelay.setVisibility(View.GONE);
        ratingBar.setVisibility(View.GONE);
        sendreview.setVisibility(View.GONE);

        reviewDataModels = new ArrayList<>();


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recylerview.setLayoutManager(layoutManager);
        reviewAdapter = new ReviewAdapter(reviewDataModels,this);
        recylerview.setAdapter(reviewAdapter);
        getrivew();



        sendreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                fnamelay.setVisibility(View.GONE);
//                lnamelay.setVisibility(View.GONE);
//                emaillay.setVisibility(View.GONE);
//                phonelay.setVisibility(View.GONE);
//                messagelay.setVisibility(View.GONE);
//                ratingBar.setVisibility(View.GONE);
//                sendreview.setVisibility(View.GONE);
//                review.setVisibility(View.VISIBLE);

                String fnameb,lnameb,emailb,mobileb,messageb,rating;
                rating = String.valueOf(ratingBar.getRating());
                fnameb = fname.getText().toString().trim();
                lnameb = lname.getText().toString().trim();
                emailb = email.getText().toString().trim();
                mobileb = mobile.getText().toString().trim();
                messageb = message.getText().toString().trim();
//                Toast.makeText(TestimonialsActivity.this, rating, Toast.LENGTH_SHORT).show();

                if(valid(rating,fnameb,lnameb,emailb,mobileb,messageb)){
                    sendreviewtoserver(rating,fnameb,lnameb,emailb,mobileb,messageb);
                }
            }
        });

        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fnamelay.setVisibility(View.VISIBLE);
                lnamelay.setVisibility(View.VISIBLE);
                emaillay.setVisibility(View.VISIBLE);
                phonelay.setVisibility(View.VISIBLE);
                messagelay.setVisibility(View.VISIBLE);
                ratingBar.setVisibility(View.VISIBLE);
                sendreview.setVisibility(View.VISIBLE);
                recylerview.setVisibility(View.GONE);
                review.setVisibility(View.GONE);
                footer.setVisibility(View.GONE);
                a = 0;

                fname.setText(VolleySingleton.getInstance(getApplicationContext()).fname());
                lname.setText(VolleySingleton.getInstance(getApplicationContext()).lname());
                email.setText(VolleySingleton.getInstance(getApplicationContext()).email());
                mobile.setText(VolleySingleton.getInstance(getApplicationContext()).mobile());
            }
        });
    Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("FeedBack");

    drawer = findViewById(R.id.drawer_layout);
    nav = findViewById(R.id.nav_view);

        if(!VolleySingleton.getInstance(getApplicationContext()).isLogin()){
            nav.getMenu().clear();
            nav.inflateMenu(R.menu.activity_main_drawer2);
            nav.setItemIconTintList(null);
        }else {
            nav.getMenu().clear();
            nav.inflateMenu(R.menu.activity_main_drawer);
            nav.setItemIconTintList(null);
            showmsgcounter();
        }



        View headerView = nav.getHeaderView(0);
        nav.setItemIconTintList(ColorStateList.valueOf(getResources().getColor(R.color.icon_color)));

        ImageView profilepic = headerView.findViewById(R.id.profile_image);
        TextView tvUserName = headerView.findViewById(R.id.tv_user_name);
        RelativeLayout llSignin = headerView.findViewById(R.id.ll_signin);
        RelativeLayout llProfile = headerView.findViewById(R.id.ll_profile);

        if(!VolleySingleton.getInstance(getApplicationContext()).isLogin()){
            llProfile.setVisibility(View.GONE);
            llSignin.setVisibility(View.VISIBLE);

        }else {
            llProfile.setVisibility(View.VISIBLE);
            llSignin.setVisibility(View.GONE);

            tvUserName.setText(VolleySingleton.getInstance(getApplicationContext()).fname());


            Glide.with(this)
                    .load(VolleySingleton.getInstance(getApplicationContext()).profilepic())
                    .apply(RequestOptions
                            .placeholderOf(R.drawable.profile)
                            .dontAnimate()
                            .error(R.drawable.profile))
                    .into(profilepic);



        }

        llSignin.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
        });


        llProfile.setOnClickListener(v -> {
            startActivity(new Intent(this, ProfileActivity.class));
        });


        homeefooter = findViewById(R.id.homefooter);
        latestfooter = findViewById(R.id.latestfooter);
        shortlistedfooter = findViewById(R.id.shortlistedfooter);
        searchfooter = findViewById(R.id.searchfooter);
        addfooter = findViewById(R.id.addfooter);

        homeefooter1 = findViewById(R.id.homefooter1);
        latestfooter1 = findViewById(R.id.latestfooter1);
        searchfooter1 = findViewById(R.id.searchfooter1);
        shortlistedfooter1 = findViewById(R.id.shortlistedfooter1);



        footerh = findViewById(R.id.footerh);
        footerl = findViewById(R.id.footerl);
        footershort = findViewById(R.id.footershort);
        footers = findViewById(R.id.footers);





        homeefooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent footorhome = new Intent(TestimonialsActivity.this, MainActivity.class);
                footorhome.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(footorhome);            }
        });
        latestfooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent footorhome = new Intent(TestimonialsActivity.this, LatestActivity.class);
                footorhome.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(footorhome);
            }
        });

        addfooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(VolleySingleton.getInstance(getApplicationContext()).isLogin()){
                    ListingData.getInstance(getApplicationContext()).Logout();
                    ListingData.getInstance(getApplicationContext()).setinstrance("Add");
                    startActivity(new Intent(TestimonialsActivity.this, BasicDetails.class));
                }else{
                    startActivity(new Intent(TestimonialsActivity.this, LoginActivity.class));
                    finish();

                }

            }
        });


        shortlistedfooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent footorprofile = new Intent(TestimonialsActivity.this, ShortlistedActivity.class);
                footorprofile.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(footorprofile);
            }
        });
        searchfooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intsl = new Intent(TestimonialsActivity.this, SearchProperty.class);
                intsl.putExtra("instance", "search");
                startActivity(intsl);
            }
        });


        toggle = new ActionBarDrawerToggle(TestimonialsActivity.this,drawer,toolbar,R.string.open,R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.nav_home:
                    Intent intelp = new Intent(TestimonialsActivity.this, MainActivity.class);
                    intelp.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intelp);
                    drawer.closeDrawer(GravityCompat.START);
                    break;

                case R.id.testimonials:
                    drawer.closeDrawer(GravityCompat.START);
                    break;

                case R.id.addproperty:
                    ListingData.getInstance(getApplicationContext()).Logout();
                    ListingData.getInstance(getApplicationContext()).setinstrance("Add");
                    drawer.closeDrawer(GravityCompat.START);
                    Intent inte = new Intent(TestimonialsActivity.this, BasicDetails.class);
                    inte.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(inte);

                    break;

                case R.id.setting:
                    Intent intel = new Intent(TestimonialsActivity.this, SettingActivity.class);
                    intel.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intel);
                    drawer.closeDrawer(GravityCompat.START);
                    break;

                case R.id.profile:
                    Intent intep = new Intent(TestimonialsActivity.this, ProfileActivity.class);
                    intep.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intep);
                    drawer.closeDrawer(GravityCompat.START);
                    break;


                case R.id.buyproperty:
                    Intent intebuy = new Intent(TestimonialsActivity.this, SearchProperty.class);
                    intebuy.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    intebuy.putExtra("instance", "search");
                    startActivity(intebuy);
                    drawer.closeDrawer(GravityCompat.START);
                    break;

                case R.id.userlist:
                    Intent intepu = new Intent(TestimonialsActivity.this, OwnerBuilderDeveloperList.class);
                    intepu.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intepu);
                    drawer.closeDrawer(GravityCompat.START);
                    break;

                case R.id.shortlisted:
                    Intent intes = new Intent(TestimonialsActivity.this, ShortlistedActivity.class);
                    intes.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intes);
                    drawer.closeDrawer(GravityCompat.START);
                    break;


                case R.id.latest:
                    Intent intess = new Intent(TestimonialsActivity.this, LatestActivity.class);
                    intess.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intess);
                    break;

                case R.id.service:
                    Intent intemys = new Intent(TestimonialsActivity.this, Buyourservices.class);
                    intemys.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intemys);
                    drawer.closeDrawer(GravityCompat.START);
                    break;


                case R.id.myproperty:
                    Intent intemy = new Intent(TestimonialsActivity.this, MyPropertyActivity.class);
                    intemy.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intemy);
                    drawer.closeDrawer(GravityCompat.START);
                    break;


                case R.id.lead:
                    Intent intsl = new Intent(TestimonialsActivity.this, SearchProperty.class);
                    intsl.putExtra("instance", "lead");
                    intsl.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intsl);
                    drawer.closeDrawer(GravityCompat.START);
                    break;



                case R.id.navabout:
                    Intent inta = new Intent(TestimonialsActivity.this, AboutActivity.class);
                    inta.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(inta);
                    drawer.closeDrawer(GravityCompat.START);
                    break;

                case R.id.contact:
                    Intent intc = new Intent(TestimonialsActivity.this, ContactActivity.class);
                    intc.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intc);
                    drawer.closeDrawer(GravityCompat.START);
                    break;

                case R.id.logout:
                    new AlertDialog.Builder(TestimonialsActivity.this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Logout")
                            .setMessage("Are you sure?")
                            .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    VolleySingleton.getInstance(getApplicationContext()).Logout();
                                    Intent intl = new Intent(TestimonialsActivity.this, LoginActivity.class);
                                    intl.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                    startActivity(intl);
                                    drawer.closeDrawer(GravityCompat.START);
                                }
                            }).setNegativeButton("no", null).show();
                    break;



            }

            return true;
        }
    });




}



    public void showmsgcounter(){

        final String userid1, date, date2;
        userid1 = VolleySingleton.getInstance(getApplicationContext()).id();

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateOnly = new SimpleDateFormat("yyyy-MM-dd");

        DateForMsgCounterNotification dateForMsgCounterNotification = new DateForMsgCounterNotification(dateOnly.format(cal.getTime()));
        date = dateForMsgCounterNotification.getdateforcounter();


        if(VolleySingleton.getInstance(getApplicationContext()).getmsgcounterdate() != null){
            date2 = VolleySingleton.getInstance(getApplicationContext()).getmsgcounterdate();
        }else{
            date2 = date;
        }




        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.getquestionsnotification, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                System.out.println("getparaaaaams response  :: " +response);
                //progressDialog.dismiss();
                try {
                    JSONObject obj = new JSONObject(response);


                    String counter = obj.getString("notification");


//                System.out.println(" dataataataa :;  "+ counter );


                    VolleySingleton.getInstance(getApplicationContext()).setmsglist(obj.getString("notification"));






//                    counter = counter.toString().replace("[", "").replace("]", "");
//                    String resultflatamore = counter.replaceAll("^[\"']+|[\"']+$", "");

                    getmsgcounter = new ArrayList<String>(Arrays.asList(counter.split(",")));


//
//                    if(getmsgcounter.contains()){
//                        System.out.println(" dataataataa :;  "+ counter + "    "+ getmsgcounter.size() );
//                    }


                    LayoutInflater li = LayoutInflater.from(TestimonialsActivity.this);
                    tv_counter = (TextView)li.inflate(R.layout.counter_layout, null);
                    nav.getMenu().findItem(R.id.myproperty).setActionView(tv_counter);


                    if(getmsgcounter.get(0).length() != 2){
                        tv_counter.setText(String.valueOf(getmsgcounter.size()));
                    }else {
                        tv_counter.setText("");
                    }





                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //progressDialog.dismiss();
                Toast.makeText(TestimonialsActivity.this,"Something Went Wrong",Toast.LENGTH_SHORT).show();
//                Log.d("VOLLEY",error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                SmsData smsData = new SmsData();
                Map<String, String> params = new HashMap<>();
                params.put("header", smsData.token);
                params.put("userid", userid1);
                params.put("date", date);
                params.put("date2", date2);

                System.out.println("getparaaaaams :; " + params);
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

        if(a == 0){
            fnamelay.setVisibility(View.GONE);
            lnamelay.setVisibility(View.GONE);
            emaillay.setVisibility(View.GONE);
            phonelay.setVisibility(View.GONE);
            messagelay.setVisibility(View.GONE);
            ratingBar.setVisibility(View.GONE);
            sendreview.setVisibility(View.GONE);
            recylerview.setVisibility(View.VISIBLE);
            review.setVisibility(View.VISIBLE);
            footer.setVisibility(View.VISIBLE);
            a = 1;
        }else{
            super.onBackPressed();
        }
    }

    private void getrivew() {
        final String id = VolleySingleton.getInstance(getApplicationContext()).id();
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.getreview, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                progressDialog.dismiss();
                System.out.println("response :: "+ response);


                try {
                    JSONObject obj = new JSONObject(response);


                    String succes = obj.getString("success");
                    JSONArray jsonArray = obj.getJSONArray("data");


                    if(succes.equals("1")){
                        reviewDataModels.clear();
                        for(int i=0; i<jsonArray.length();i++){
                            JSONObject object = jsonArray.getJSONObject(i);
                            String fname =object.getString("fname");
                            String lname =object.getString("lname");
                            String review1 =object.getString("review");
                            String rating =object.getString("rating");
                            valueid =object.getString("valueid");

                            if(valueid.equals("1")){
                                userreviewid =object.getString("userreviewid");
                                userreview =object.getString("userreview");
                                userrating =object.getString("userrating");

                                message.setText(userreview);
                                ratingBar.setRating(Float.parseFloat(userrating));
                            }else {
                            }

                            ReviewDataModel reviewDataModel = new ReviewDataModel(fname,lname,rating,review1, valueid, userreviewid, userreview, userrating);
                            reviewDataModels.add(reviewDataModel);
                            reviewAdapter.notifyDataSetChanged();
                        }
                    }

                } catch (JSONException e) {
                    progressDialog.dismiss();
                    showmessage("Something wrong please check network connection"+e);
                    System.out.println("problem ::"+e);
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
                params.put("userid", id);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }


    private boolean valid(String rating, String fnameb, String lnameb, String emailb, String mobileb, String messageb1) {

        Pattern emailPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
        Matcher emailMatcher = emailPattern.matcher(emailb);
        if(fnameb.isEmpty()){
            fname.setError("Please Enter Your First Name");
            return false;
        }
        if(lnameb.isEmpty()){
            lname.setError("Please Enter Your Last Name");
            return false;
        }
        if(emailb.isEmpty()){
            email.setError("Please Enter Your Email");
            return false;
        }
        else if(!emailMatcher.matches()){
            email.setError("Please enter valid Email");
            return false;
        }
        if(mobileb.isEmpty()){
            mobile.setError("Please Enter Your Phone Number");
            return false;
        }
        else if(mobileb.length() != 10){
            mobile.setError("Invalid Mobile Number, Number should be of 10 Digits");
            return false;
        }
        if(messageb1.isEmpty()){
            fname.setError("Please Enter Message");
            return false;
        }

        if(rating.equals("0.0")){
            showmessage("Please Give Rating");
            return false;
        }


        return true;
    }

    private void sendreviewtoserver(final String rating, final String fnameb, final String lnameb, final String emailb, final String mobileb, final String messageb) {
        final String userid = VolleySingleton.getInstance(getApplicationContext()).id();
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.reviewpost, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject obj = new JSONObject(response);
                    if(obj.getBoolean("error")){
                        showmessage(obj.getString("message"));
                    }else{
                        showmessage(obj.getString("message"));
                        finish();
                        startActivity(getIntent());
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TestimonialsActivity.this,"Something Went Wrong",Toast.LENGTH_SHORT).show();
                Log.d("VOLLEY", String.valueOf(error));
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                SmsData smsData = new SmsData();
                Map<String, String> params = new HashMap<>();
                params.put("header", smsData.token);
                params.put("fname", fnameb);
                params.put("lname", lnameb);
                params.put("email", emailb);
                params.put("mobile", mobileb);
                params.put("review", messageb);
                params.put("rating", rating);
                params.put("userid", userid);
                params.put("valueid", valueid);
                params.put("userreviewid", userreviewid);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }


    @Override
    protected void onResume() {
        super.onResume();
        // to check current activity in the navigation drawer
        nav.getMenu().getItem(10).setChecked(true);


//        footers.setColorFilter(getResources().getColor(R.color.gray), PorterDuff.Mode.SRC_IN);
//        searchfooter1.setTextColor(Color.GRAY);
//
//
//
//        footerh.setColorFilter(getResources().getColor(R.color.gray), PorterDuff.Mode.SRC_IN);
//        footerl.setColorFilter(getResources().getColor(R.color.gray), PorterDuff.Mode.SRC_IN);
//        footershort.setColorFilter(getResources().getColor(R.color.gray), PorterDuff.Mode.SRC_IN);
//        homeefooter1.setTextColor(Color.GRAY);
//        latestfooter1.setTextColor(Color.GRAY);
//        shortlistedfooter1.setTextColor(Color.GRAY);



    }
    private void showmessage(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

}
