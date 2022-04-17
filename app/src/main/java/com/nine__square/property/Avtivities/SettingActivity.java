package com.nine__square.property.Avtivities;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.nine__square.property.AddPropertActivity.BasicDetails;
import com.nine__square.property.Helper.DateForMsgCounterNotification;
import com.nine__square.property.R;
import com.nine__square.property.Utils.Endpoints;
import com.nine__square.property.Utils.ListingData;
import com.nine__square.property.Utils.SmsData;
import com.nine__square.property.Utils.VolleySingleton;

import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class SettingActivity extends AppCompatActivity {

    NavigationView nav;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;

    ImageView footerh,footerl, footershort, footers;


    private Uri imageUri;
    private Intent intent;


    private LinearLayout homeefooter, latestfooter, searchfooter, shortlistedfooter, addfooter;
    private TextView homeefooter1, latestfooter1, searchfooter1, shortlistedfooter1;

    CardView about,contact,privacy,moreapp,share,terms;

    ArrayList<String> getmsgcounter;
    TextView tv_counter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        about = findViewById(R.id.about);
        contact = findViewById(R.id.contact);
        privacy = findViewById(R.id.privacy);
        terms = findViewById(R.id.terms);
//        moreapp = findViewById(R.id.moreapp);
        share = findViewById(R.id.share);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Setting");

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

//
//        if(!VolleySingleton.getInstance(getApplicationContext()).isLogin()){
//            nav.getMenu().clear();
////            nav.inflateMenu(R.menu.activity_home_drawer3);
//        }else {
//            if (VolleySingleton.getInstance(getApplicationContext()).typeofuser().equals("Customer")) {
//                nav.getMenu().clear();
//                nav.inflateMenu(R.menu.activity_home_drawer2);
//            } else if (VolleySingleton.getInstance(getApplicationContext()).typeofuser().equals("Broker")) {
//                nav.getMenu().clear();
//                nav.inflateMenu(R.menu.activity_home_drawer);
//            } else if (VolleySingleton.getInstance(getApplicationContext()).typeofuser().equals("Developer")) {
//                nav.getMenu().clear();
//                nav.inflateMenu(R.menu.activity_home_drawer);
//            }
//        }



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


        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent footorhome = new Intent(SettingActivity.this, AboutActivity.class);
                footorhome.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(footorhome);
            }
        });

        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent footorhome = new Intent(SettingActivity.this, PrivacyPolicyActivity.class);
                footorhome.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(footorhome);
            }
        });

        terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent footorhome = new Intent(SettingActivity.this, TermsAndConditionActivity.class);
                footorhome.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(footorhome);
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                imageUri = Uri.parse(String.valueOf(R.drawable.splaahome));
//                intent = new Intent();
//                intent.setAction(Intent.ACTION_SEND);
//                intent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.nestingindia.nesting_india");
//                intent.putExtra(Intent.EXTRA_STREAM, imageUri);
//                intent.setType("image/*");
//                startActivity(intent);

                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.9 Square Property.nesting_india");
                sharingIntent.setType("text/plain");
                startActivity(Intent.createChooser(sharingIntent, "Share Request Post"));
            }
        });

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(VolleySingleton.getInstance(getApplicationContext()).isLogin()){
                    Intent footorhome = new Intent(SettingActivity.this, ContactActivity.class);
                    footorhome.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(footorhome);
                }else{
                    Intent footorhome = new Intent(SettingActivity.this, LoginActivity.class);
                    footorhome.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(footorhome);
                }

            }
        });


        homeefooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent footorhome = new Intent(SettingActivity.this, MainActivity.class);
                footorhome.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(footorhome);            }
        });
        latestfooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent footorhome = new Intent(SettingActivity.this, LatestActivity.class);
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
                    startActivity(new Intent(SettingActivity.this, BasicDetails.class));
                }else{
                    startActivity(new Intent(SettingActivity.this, LoginActivity.class));
                    finish();

                }

            }
        });


        shortlistedfooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(VolleySingleton.getInstance(getApplicationContext()).isLogin()){
                    Intent footorprofile = new Intent(SettingActivity.this, ShortlistedActivity.class);
                    footorprofile.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(footorprofile);
                }else{
                    Intent footorprofile = new Intent(SettingActivity.this, LoginActivity.class);
                    footorprofile.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(footorprofile);
                }


            }
        });

        searchfooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intsl = new Intent(SettingActivity.this, SearchProperty.class);
                intsl.putExtra("instance", "search");
                startActivity(intsl);
            }
        });

        toggle = new ActionBarDrawerToggle(SettingActivity.this,drawer,toolbar,R.string.open,R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        Intent inteh = new Intent(SettingActivity.this, MainActivity.class);
                        inteh.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(inteh);
                        drawer.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.latest:
                        Intent inteh5 = new Intent(SettingActivity.this, LatestActivity.class);
                        inteh5.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(inteh5);
                        drawer.closeDrawer(GravityCompat.START);
                        break;


                    case R.id.testimonials:
                        Intent intel5 = new Intent(SettingActivity.this, TestimonialsActivity.class);
                        intel5.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intel5);
                        drawer.closeDrawer(GravityCompat.START);
                        break;



                    case R.id.buyproperty:
                        Intent intebuy = new Intent(SettingActivity.this, SearchProperty.class);
                        intebuy.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        intebuy.putExtra("instance", "search");
                        startActivity(intebuy);
                        drawer.closeDrawer(GravityCompat.START);
                        break;



//                    case R.id.logout:
//                        Toast.makeText(SettingActivity.this, "Logout", Toast.LENGTH_SHORT).show();
//                        VolleySingleton.getInstance(getApplicationContext()).Logout();
//                        startActivity(new Intent(SettingActivity.this, LoginActivity.class));
//                        SettingActivity.this.finish();
//                        drawer.closeDrawer(GravityCompat.START);
//                        break;

                    case R.id.addproperty:
                        ListingData.getInstance(getApplicationContext()).Logout();
                        ListingData.getInstance(getApplicationContext()).setinstrance("Add");

                        if (VolleySingleton.getInstance(getApplicationContext()).isLogin()) {
                            Intent integ = new Intent(SettingActivity.this, BasicDetails.class);
                            integ.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            startActivity(integ);

                        }else{
                            Intent integ = new Intent(SettingActivity.this, LoginActivity.class);
                            integ.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            startActivity(integ);
                        }

                        drawer.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.profile:
                        Intent inte = new Intent(SettingActivity.this, ProfileActivity.class);
                        inte.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(inte);
                        drawer.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.navabout:
                        Intent intea = new Intent(SettingActivity.this, AboutActivity.class);
                        intea.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intea);
                        drawer.closeDrawer(GravityCompat.START);
                        break;



                    case R.id.shortlisted:
                        Intent intes = new Intent(SettingActivity.this, ShortlistedActivity.class);
                        intes.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intes);
                        drawer.closeDrawer(GravityCompat.START);
                        break;


                    case R.id.contact:
                        if (VolleySingleton.getInstance(getApplicationContext()).isLogin()) {
                            Intent inten = new Intent(SettingActivity.this, ContactActivity.class);
                            inten.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            startActivity(inten);
                        }else{
                            Intent inten = new Intent(SettingActivity.this, LoginActivity.class);
                            inten.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            startActivity(inten);
                        }
                        drawer.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.setting:
                        drawer.closeDrawer(GravityCompat.START);
                        break;


                    case R.id.myproperty:
                        Intent intemy = new Intent(SettingActivity.this, MyPropertyActivity.class);
                        intemy.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intemy);
                        drawer.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.service:
                        Intent intemys = new Intent(SettingActivity.this, Buyourservices.class);
                        intemys.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intemys);
                        drawer.closeDrawer(GravityCompat.START);
                        break;



                    case R.id.login:
                        Intent intenl = new Intent(SettingActivity.this, LoginActivity.class);
                        intenl.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intenl);
                        drawer.closeDrawer(GravityCompat.START);
                        break;


                    case R.id.userlist:
                        Intent intepu = new Intent(SettingActivity.this, OwnerBuilderDeveloperList.class);
                        intepu.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intepu);
                        drawer.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.lead:
                        Intent intsl = new Intent(SettingActivity.this, SearchProperty.class);
                        intsl.putExtra("instance", "lead");
                        intsl.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intsl);
                        drawer.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.logout:
                        new AlertDialog.Builder(SettingActivity.this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Logout")
                                .setMessage("Are you sure?")
                                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        VolleySingleton.getInstance(getApplicationContext()).Logout();
                                        Intent intl = new Intent(SettingActivity.this, LoginActivity.class);
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


                    LayoutInflater li = LayoutInflater.from(SettingActivity.this);
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
                Toast.makeText(SettingActivity.this,"Something Went Wrong",Toast.LENGTH_SHORT).show();
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
    protected void onResume() {
        super.onResume();
        // to check current activity in the navigation drawer
        if(VolleySingleton.getInstance(getApplicationContext()).isLogin()){
            nav.getMenu().getItem(13).setChecked(true);
        }else{
            nav.getMenu().getItem(7).setChecked(true);
        }
        footers.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
        searchfooter1.setTextColor(Color.GREEN);



//        footerh.setColorFilter(getResources().getColor(R.color.gray), PorterDuff.Mode.SRC_IN);
//        footerl.setColorFilter(getResources().getColor(R.color.gray), PorterDuff.Mode.SRC_IN);
//        footershort.setColorFilter(getResources().getColor(R.color.gray), PorterDuff.Mode.SRC_IN);
//        homeefooter1.setTextColor(Color.GRAY);
//        latestfooter1.setTextColor(Color.GRAY);
//        shortlistedfooter1.setTextColor(Color.GRAY);



    }


}
