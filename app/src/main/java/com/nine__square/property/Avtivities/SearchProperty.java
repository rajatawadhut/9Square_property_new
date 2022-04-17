package com.nine__square.property.Avtivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
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
import com.nine__square.property.Utils.SmsData;
import com.nine__square.property.Utils.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class SearchProperty extends AppCompatActivity {

    TextView apartmentt, residentiallandtl, housevillat, officet, retailt,
            landt, industryt, storaget, hospitalityt, othert;

    LinearLayout sharinglayout9, usertype9, budget9,category9, statelayout, localitylayout, citylayout, projectlayout;

    ImageView apartment, residentialland, housevilla, office, retail, land, industry, storage, hospitality, other;
    Button residentialtype, commercialtype;
    EditText city, locality, state, project, otherlocality;
    RadioButton buyr, rentr, pgr, privater, sharer, buyers;
    Button search;
    private ProgressDialog progressDialog;
    ArrayList<String> allcity = new ArrayList<String>();



    LinearLayout apartment9, residentialland9, housevilla9, office9, retail9, land9,
            industry9, storage9, hospitality9, other9;

    String category = "", type = "", listfor ="", sharing = "", instance = "";
    String min = "", max = "";
    String otherlocalityG;

    Spinner usertypespin, minpricespin, maxpricespin;
    String usertype = "", subscriptionplan ="";


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Search Property");
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
        progressDialog.setCancelable(true);




        instance = getIntent().getStringExtra("instance");



        statelayout = findViewById(R.id.statelayout);
        citylayout = findViewById(R.id.citylayout);
        projectlayout = findViewById(R.id.projectlayout);
        localitylayout = findViewById(R.id.localitylayout);
        category9 = findViewById(R.id.category9);
        sharinglayout9 = findViewById(R.id.sharinglayout9);
        budget9 = findViewById(R.id.budget9);
        usertype9 = findViewById(R.id.usertype9);
        otherlocality = findViewById(R.id.otherlocality);

        minpricespin = findViewById(R.id.minprice);
        maxpricespin = findViewById(R.id.maxprice);



        buyers = findViewById(R.id.buyers);



        if(instance.equals("lead")){
            getsubscription();
            category9.setVisibility(View.GONE);
            sharinglayout9.setVisibility(View.GONE);
            budget9.setVisibility(View.GONE);
            usertype9.setVisibility(View.GONE);
            localitylayout.setVisibility(View.GONE);
            projectlayout.setVisibility(View.GONE);
            statelayout.setVisibility(View.VISIBLE);
            buyers.setVisibility(View.VISIBLE);

            toolbar.setTitle("Search Lead");



            Calendar cal = Calendar.getInstance();
            SimpleDateFormat dateOnly = new SimpleDateFormat("yyyy-MM-dd");
            String date2;
            date2 = dateOnly.format(cal.getTime());

            VolleySingleton.getInstance(getApplicationContext()).setcounterdate(date2);


        }else{


            ArrayAdapter<CharSequence> minpricespinadapter = ArrayAdapter.createFromResource(SearchProperty.this, R.array.select, android.R.layout.simple_spinner_item);
            minpricespinadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            minpricespin.setAdapter(minpricespinadapter);

            ArrayAdapter<CharSequence> maxpricespinadapter = ArrayAdapter.createFromResource(SearchProperty.this, R.array.select, android.R.layout.simple_spinner_item);
            maxpricespinadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            maxpricespin.setAdapter(maxpricespinadapter);


            category9.setVisibility(View.VISIBLE);
            sharinglayout9.setVisibility(View.VISIBLE);
            budget9.setVisibility(View.VISIBLE);
            usertype9.setVisibility(View.VISIBLE);
            localitylayout.setVisibility(View.VISIBLE);
            statelayout.setVisibility(View.VISIBLE);
            projectlayout.setVisibility(View.VISIBLE);
        }












        usertypespin = findViewById(R.id.usertypespin);




        apartmentt = findViewById(R.id.appartmentt);
        residentiallandtl = findViewById(R.id.residentiallandt);
        housevillat = findViewById(R.id.housevillat);
        officet = findViewById(R.id.officet);
        retailt = findViewById(R.id.retailt);
        landt = findViewById(R.id.landt);
        industryt = findViewById(R.id.industryt);
        storaget = findViewById(R.id.storaget);
        hospitalityt = findViewById(R.id.hospitalityt);
        othert = findViewById(R.id.othert);



        apartment = findViewById(R.id.appartment);
        residentialland = findViewById(R.id.residentialland);
        housevilla = findViewById(R.id.housevilla);
        office = findViewById(R.id.office);
        retail = findViewById(R.id.retail);
        land = findViewById(R.id.land);
        industry = findViewById(R.id.industry);
        storage = findViewById(R.id.storage);
        hospitality = findViewById(R.id.hospitality);
        other = findViewById(R.id.other);




        residentialtype = findViewById(R.id.residential);
        commercialtype = findViewById(R.id.comercial);


        city = findViewById(R.id.city);
        locality = findViewById(R.id.locality);
        state = findViewById(R.id.state);
        project = findViewById(R.id.project);

        buyr = findViewById(R.id.buyr);
        rentr = findViewById(R.id.rentr);
        pgr = findViewById(R.id.pgr);
        privater = findViewById(R.id.privater);
        sharer = findViewById(R.id.sharer);
        buyers = findViewById(R.id.buyers);

        if(instance.equals("lead")){
            buyr.setText("Sell");
        }else{
            buyr.setText("Buy");
        }


        apartment9 = findViewById(R.id.appartment9);
        residentialland9 = findViewById(R.id.residentialland9);
        housevilla9 = findViewById(R.id.housevilla9);
        office9 = findViewById(R.id.office9);
        retail9 = findViewById(R.id.retail9);
        land9 = findViewById(R.id.land9);
        industry9 = findViewById(R.id.industry9);
        storage9 = findViewById(R.id.storage9);
        hospitality9 = findViewById(R.id.hospitality9);
        other9 = findViewById(R.id.other9);


        search = findViewById(R.id.search);



        sharinglayout9.setVisibility(View.GONE);

        apartment9.setVisibility(View.VISIBLE);
        residentialland9.setVisibility(View.VISIBLE);
        housevilla9.setVisibility(View.VISIBLE);
        office9.setVisibility(View.GONE);
        retail9.setVisibility(View.GONE);
        land9.setVisibility(View.GONE);
        industry9.setVisibility(View.GONE);
        storage9.setVisibility(View.GONE);
        hospitality9.setVisibility(View.GONE);
        other9.setVisibility(View.VISIBLE);


        locality.setText("");
        VolleySingleton.getInstance(getApplicationContext()).setsearchlocality("");

        state.setText("Maharashtra");
        VolleySingleton.getInstance(getApplicationContext()).setsearchstate("Maharashtra");


        project.setText("");
        VolleySingleton.getInstance(getApplicationContext()).setsearchproject("");



//        privater.setChecked(true);
//        sharing = "Private";

        city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locality.setText("");
                VolleySingleton.getInstance(getApplicationContext()).setsearchlocality("");
                startActivity(new Intent(SearchProperty.this, SearchCity.class));
            }
        });

        state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                city.setText("");
                VolleySingleton.getInstance(getApplicationContext()).setsearchcity("");
                locality.setText("");
                VolleySingleton.getInstance(getApplicationContext()).setsearchlocality("");
                startActivity(new Intent(SearchProperty.this, SearchState.class));
            }
        });

        locality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchProperty.this, SearchLocality.class));
            }
        });

        project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchProperty.this, SearchProject.class));
            }
        });

        privater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharing = "Private";
            }
        });

        sharer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharing = "Sharing";
            }
        });

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.usertype, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        usertypespin.setAdapter(adapter2);



        usertypespin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                usertype = usertypespin.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        apartmentt.setTextColor(getResources().getColor(R.color.black));
        residentiallandtl.setTextColor(getResources().getColor(R.color.black));
        housevillat.setTextColor(getResources().getColor(R.color.black));
        officet.setTextColor(getResources().getColor(R.color.black));
        retailt.setTextColor(getResources().getColor(R.color.black));
        landt.setTextColor(getResources().getColor(R.color.black));
        industryt.setTextColor(getResources().getColor(R.color.black));
        storaget.setTextColor(getResources().getColor(R.color.black));
        hospitalityt.setTextColor(getResources().getColor(R.color.black));
        othert.setTextColor(getResources().getColor(R.color.black));



        buyr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    category = "";
                    commercialtype.setVisibility(View.VISIBLE);
                    residentialland9.setVisibility(View.VISIBLE);
                    listfor = "Sell";


                    sharinglayout9.setVisibility(View.GONE);
                    sharing = "";


                    apartment.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                    residentialland.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                    housevilla.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                    office.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                    retail.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                    land.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                    industry.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                    storage.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                    hospitality.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                    other.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);


                    apartmentt.setTextColor(getResources().getColor(R.color.black));
                    residentiallandtl.setTextColor(getResources().getColor(R.color.black));
                    housevillat.setTextColor(getResources().getColor(R.color.black));
                    officet.setTextColor(getResources().getColor(R.color.black));
                    retailt.setTextColor(getResources().getColor(R.color.black));
                    landt.setTextColor(getResources().getColor(R.color.black));
                    industryt.setTextColor(getResources().getColor(R.color.black));
                    storaget.setTextColor(getResources().getColor(R.color.black));
                    hospitalityt.setTextColor(getResources().getColor(R.color.black));
                    othert.setTextColor(getResources().getColor(R.color.black));



                ArrayAdapter<CharSequence> minpricespinadapter = ArrayAdapter.createFromResource(SearchProperty.this, R.array.minpricebuy, android.R.layout.simple_spinner_item);
                minpricespinadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                minpricespin.setAdapter(minpricespinadapter);

                ArrayAdapter<CharSequence> maxpricespinadapter = ArrayAdapter.createFromResource(SearchProperty.this, R.array.maxpricebuy, android.R.layout.simple_spinner_item);
                maxpricespinadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                maxpricespin.setAdapter(maxpricespinadapter);




            }
        });

        buyers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commercialtype.setVisibility(View.VISIBLE);
                listfor = "Buyers";
            }
        });

        rentr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    category = "";
                    residentialland9.setVisibility(View.GONE);
                    other9.setVisibility(View.VISIBLE);
                    commercialtype.setVisibility(View.VISIBLE);
                    listfor = "Rent";
                    sharinglayout9.setVisibility(View.GONE);
                    sharing = "";


                apartment.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                    residentialland.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                    housevilla.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                    office.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                    retail.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                    land.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                    industry.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                    storage.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                    hospitality.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                    other.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);


                    apartmentt.setTextColor(getResources().getColor(R.color.black));
                    residentiallandtl.setTextColor(getResources().getColor(R.color.black));
                    housevillat.setTextColor(getResources().getColor(R.color.black));
                    officet.setTextColor(getResources().getColor(R.color.black));
                    retailt.setTextColor(getResources().getColor(R.color.black));
                    landt.setTextColor(getResources().getColor(R.color.black));
                    industryt.setTextColor(getResources().getColor(R.color.black));
                    storaget.setTextColor(getResources().getColor(R.color.black));
                    hospitalityt.setTextColor(getResources().getColor(R.color.black));
                    othert.setTextColor(getResources().getColor(R.color.black));


                ArrayAdapter<CharSequence> minpricespinadapter = ArrayAdapter.createFromResource(SearchProperty.this, R.array.minpricerent, android.R.layout.simple_spinner_item);
                minpricespinadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                minpricespin.setAdapter(minpricespinadapter);

                ArrayAdapter<CharSequence> maxpricespinadapter = ArrayAdapter.createFromResource(SearchProperty.this, R.array.maxpricerent, android.R.layout.simple_spinner_item);
                maxpricespinadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                maxpricespin.setAdapter(maxpricespinadapter);


                }
        });

        pgr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = "";
                commercialtype.setVisibility(View.GONE);
                other9.setVisibility(View.GONE);
                residentialland9.setVisibility(View.GONE);

                listfor = "Paying Guest";

                sharing = "";

                if(instance.equals("lead")){
                    sharinglayout9.setVisibility(View.GONE);
                }else{
                    sharinglayout9.setVisibility(View.VISIBLE);

                }

                apartment.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                residentialland.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                housevilla.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                office.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                retail.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                land.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                industry.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                storage.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                hospitality.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                other.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);


                apartmentt.setTextColor(getResources().getColor(R.color.black));
                residentiallandtl.setTextColor(getResources().getColor(R.color.black));
                housevillat.setTextColor(getResources().getColor(R.color.black));
                officet.setTextColor(getResources().getColor(R.color.black));
                retailt.setTextColor(getResources().getColor(R.color.black));
                landt.setTextColor(getResources().getColor(R.color.black));
                industryt.setTextColor(getResources().getColor(R.color.black));
                storaget.setTextColor(getResources().getColor(R.color.black));
                hospitalityt.setTextColor(getResources().getColor(R.color.black));
                othert.setTextColor(getResources().getColor(R.color.black));


                ArrayAdapter<CharSequence> minpricespinadapter = ArrayAdapter.createFromResource(SearchProperty.this, R.array.minpricepg, android.R.layout.simple_spinner_item);
                minpricespinadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                minpricespin.setAdapter(minpricespinadapter);

                ArrayAdapter<CharSequence> maxpricespinadapter = ArrayAdapter.createFromResource(SearchProperty.this, R.array.maxpricepg, android.R.layout.simple_spinner_item);
                maxpricespinadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                maxpricespin.setAdapter(maxpricespinadapter);
            }
        });


        residentialtype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                residentialtype.setTextColor(Color.GREEN);
                commercialtype.setTextColor(Color.WHITE);
                type = "Residential";

                category = "";

                apartment.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                residentialland.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                housevilla.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                office.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                retail.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                land.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                industry.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                storage.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                hospitality.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                other.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);


                apartmentt.setTextColor(getResources().getColor(R.color.black));
                residentiallandtl.setTextColor(getResources().getColor(R.color.black));
                housevillat.setTextColor(getResources().getColor(R.color.black));
                officet.setTextColor(getResources().getColor(R.color.black));
                retailt.setTextColor(getResources().getColor(R.color.black));
                landt.setTextColor(getResources().getColor(R.color.black));
                industryt.setTextColor(getResources().getColor(R.color.black));
                storaget.setTextColor(getResources().getColor(R.color.black));
                hospitalityt.setTextColor(getResources().getColor(R.color.black));
                othert.setTextColor(getResources().getColor(R.color.black));


                if(listfor.equals("Sell") || listfor.isEmpty()){
                    apartment9.setVisibility(View.VISIBLE);
                    residentialland9.setVisibility(View.VISIBLE);
                    housevilla9.setVisibility(View.VISIBLE);
                    office9.setVisibility(View.GONE);
                    retail9.setVisibility(View.GONE);
                    land9.setVisibility(View.GONE);
                    industry9.setVisibility(View.GONE);
                    storage9.setVisibility(View.GONE);
                    hospitality9.setVisibility(View.GONE);
                    other9.setVisibility(View.VISIBLE);
                }

                if(listfor.equals("Rent")){
                    apartment9.setVisibility(View.VISIBLE);
                    residentialland9.setVisibility(View.GONE);
                    housevilla9.setVisibility(View.VISIBLE);
                    office9.setVisibility(View.GONE);
                    retail9.setVisibility(View.GONE);
                    land9.setVisibility(View.GONE);
                    industry9.setVisibility(View.GONE);
                    storage9.setVisibility(View.GONE);
                    hospitality9.setVisibility(View.GONE);
                    other9.setVisibility(View.VISIBLE);
                }

                if(listfor.equals("Paying Guest")){
                    apartment9.setVisibility(View.VISIBLE);
                    residentialland9.setVisibility(View.GONE);
                    housevilla9.setVisibility(View.VISIBLE);
                    office9.setVisibility(View.GONE);
                    retail9.setVisibility(View.GONE);
                    land9.setVisibility(View.GONE);
                    industry9.setVisibility(View.GONE);
                    storage9.setVisibility(View.GONE);
                    hospitality9.setVisibility(View.GONE);
                    other9.setVisibility(View.GONE);
                }






            }
        });

        commercialtype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                residentialtype.setTextColor(Color.WHITE);
                commercialtype.setTextColor(Color.GREEN);
                type = "Commercial";

                category = "";

                apartment.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                residentialland.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                housevilla.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                office.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                retail.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                land.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                industry.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                storage.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                hospitality.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                other.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);


                apartmentt.setTextColor(getResources().getColor(R.color.black));
                residentiallandtl.setTextColor(getResources().getColor(R.color.black));
                housevillat.setTextColor(getResources().getColor(R.color.black));
                officet.setTextColor(getResources().getColor(R.color.black));
                retailt.setTextColor(getResources().getColor(R.color.black));
                landt.setTextColor(getResources().getColor(R.color.black));
                industryt.setTextColor(getResources().getColor(R.color.black));
                storaget.setTextColor(getResources().getColor(R.color.black));
                hospitalityt.setTextColor(getResources().getColor(R.color.black));
                othert.setTextColor(getResources().getColor(R.color.black));



                apartment9.setVisibility(View.GONE);
                residentialland9.setVisibility(View.GONE);
                housevilla9.setVisibility(View.GONE);
                office9.setVisibility(View.VISIBLE);
                retail9.setVisibility(View.VISIBLE);
                land9.setVisibility(View.VISIBLE);
                industry9.setVisibility(View.VISIBLE);
                storage9.setVisibility(View.VISIBLE);
                hospitality9.setVisibility(View.VISIBLE);
                other9.setVisibility(View.VISIBLE);



            }
        });

        minpricespin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                min = minpricespin.getSelectedItem().toString();
                if(min.equals("4000")){
                    min = "4000";
                }

                else if(min.equals("5000")){
                    min = "5000";
                }

                else if(min.equals("6000")){
                    min = "6000";
                }

                else if(min.equals("8000")){
                    min = "8000";
                }

                else if(min.equals("10000")){
                    min = "10000";
                }

                else if(min.equals("12000")){
                    min = "12000";
                }

                else if(min.equals("15000")){
                    min = "15000";
                }

                else if(min.equals("20000")){
                    min = "20000";
                }

                else if(min.equals("25000")){
                    min = "25000";
                }

                else if(min.equals("30000")){
                    min = "30000";
                }

                else if(min.equals("35000")){
                    min = "35000";
                }

                else if(min.equals("40000")){
                    min = "40000";
                }

                else if(min.equals("50000")){
                    min = "50000";
                }

                else if(min.equals("60000")){
                    min = "60000";
                }


                else if(min.equals("70000")){
                    min = "70000";
                }

                else if(min.equals("80000")){
                    min = "80000";
                }

                else if(min.equals("90000")){
                    min = "90000";
                }

                else if(min.equals("1 Lac")){
                    min = "100000";
                }

                else if(min.equals("1.5 Lac")){
                    min = "150000";
                }


                else if(min.equals("2 Lac")){
                    min = "200000";
                }

                else if(min.equals("4 Lac")){
                    min = "400000";
                }

                else if(min.equals("5 Lac")){
                    min = "500000";
                }

                else if(min.equals("7 Lac")){
                    min = "700000";
                }

                else if(min.equals("10 Lac")){
                    min = "1000000";
                }



                else if(min.equals("20 Lac")){
                    min = "2000000";
                }



                else if(min.equals("30 Lac")){
                    min = "3000000";
                }



                else if(min.equals("40 Lac")){
                    min = "4000000";
                }



                else if(min.equals("50 Lac")){
                    min = "5000000";
                }



                else if(min.equals("60 Lac")){
                    min = "6000000";
                }



                else if(min.equals("70 Lac")){
                    min = "7000000";
                }



                else if(min.equals("80 Lac")){
                    min = "8000000";
                }



                else if(min.equals("90 Lac")){
                    min = "9000000";
                }



                else if(min.equals("1 Cr")){
                    min = "10000000";
                }



                else if(min.equals("1.2 Cr")){
                    min = "12000000";
                }



                else if(min.equals("1.5 Cr")){
                    min = "15000000";
                }



                else if(min.equals("1.6 Cr")){
                    min = "16000000";
                }



                else if(min.equals("1.8 Cr")){
                    min = "18000000";
                }



                else if(min.equals("2 Cr")){
                    min = "20000000";
                }



                else if(min.equals("2.3 Cr")){
                    min = "23000000";
                }


                else if(min.equals("2.6 Cr")){
                    min = "26000000";
                }
                else if(min.equals("3 Cr")){
                    min = "30000000";
                }
                else if(min.equals("3.5 Cr")){
                    min = "35000000";
                }
                else if(min.equals("4 Cr")){
                    min = "40000000";
                }
                else if(min.equals("4.5 Cr")){
                    min = "45000000";
                }
                else if(min.equals("5 Cr")){
                    min = "50000000";
                }
                else if(min.equals("10 Cr")){
                    min = "100000000";
                }
                else if(min.equals("15 Cr")){
                    min = "150000000";
                }




            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        maxpricespin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                max = maxpricespin.getSelectedItem().toString();

                if(max.equals("4000")){
                    max = "4000";
                }

                else if(max.equals("5000")){
                    max = "5000";
                }

                else if(max.equals("6000")){
                    max = "6000";
                }

                else if(max.equals("8000")){
                    max = "8000";
                }

                else if(max.equals("10000")){
                    max = "10000";
                }

                else if(max.equals("12000")){
                    max = "12000";
                }

                else if(max.equals("15000")){
                    max = "15000";
                }

                else if(max.equals("20000")){
                    max = "20000";
                }

                else if(max.equals("25000")){
                    max = "25000";
                }

                else if(max.equals("30000")){
                    max = "30000";
                }

                else if(max.equals("35000")){
                    max = "35000";
                }

                else if(max.equals("40000")){
                    max = "40000";
                }

                else if(max.equals("50000")){
                    max = "50000";
                }

                else if(max.equals("60000")){
                    max = "60000";
                }

                else if(max.equals("70000")){
                    max = "70000";
                }

                else if(max.equals("80000")){
                    max = "80000";
                }

                else if(max.equals("90000")){
                    max = "90000";
                }

                else if(max.equals("1 Lac")){
                    max = "100000";
                }

                else if(max.equals("1.5 Lac")){
                    max = "150000";
                }


                else if(max.equals("2 Lac")){
                    max = "200000";
                }

                else if(max.equals("4 Lac")){
                    max = "400000";
                }


                else if(max.equals("5 Lac")){
                    max = "500000";
                }

                else if(max.equals("7 Lac")){
                    max = "700000";
                }

                else if(max.equals("10 Lac")){
                    max = "1000000";
                }



                else if(max.equals("20 Lac")){
                    max = "2000000";
                }



                else if(max.equals("30 Lac")){
                    max = "3000000";
                }



                else if(max.equals("40 Lac")){
                    max = "4000000";
                }



                else if(max.equals("50 Lac")){
                    max = "5000000";
                }



                else if(max.equals("60 Lac")){
                    max = "6000000";
                }



                else if(max.equals("70 Lac")){
                    max = "7000000";
                }



                else if(max.equals("80 Lac")){
                    max = "8000000";
                }



                else if(max.equals("90 Lac")){
                    max = "9000000";
                }



                else if(max.equals("1 Cr")){
                    max = "10000000";
                }



                else if(max.equals("1.2 Cr")){
                    max = "12000000";
                }



                else if(max.equals("1.5 Cr")){
                    max = "15000000";
                }



                else if(max.equals("1.6 Cr")){
                    max = "16000000";
                }



                else if(max.equals("1.8 Cr")){
                    max = "18000000";
                }



                else if(max.equals("2 Cr")){
                    max = "20000000";
                }



                else if(max.equals("2.3 Cr")){
                    max = "23000000";
                }


                else if(max.equals("2.6 Cr")){
                    max = "26000000";
                }
                else if(max.equals("3 Cr")){
                    max = "30000000";
                }
                else if(max.equals("3.5 Cr")){
                    max = "35000000";
                }
                else if(max.equals("4 Cr")){
                    max = "40000000";
                }
                else if(max.equals("4.5 Cr")){
                    max = "45000000";
                }
                else if(max.equals("5 Cr")){
                    max = "50000000";
                }
                else if(max.equals("10 Cr")){
                    max = "100000000";
                }
                else if(max.equals("15 Cr")){
                    max = "150000000";
                }


            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });





        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(instance.equals("search")) {


                    String getcity, getlocality, getstate, getproject;
                    getcity = city.getText().toString().trim();
                    getstate = state.getText().toString().trim();
                    getlocality = locality.getText().toString().trim();
                    getproject = project.getText().toString().trim();

                    if(getproject.isEmpty()){
                        getproject = "";
                    }
                    if(getlocality.isEmpty()){
                        getlocality = "";
                    }

                    if(getstate.isEmpty()){
                        getstate = "";
                    }
                    if(listfor.isEmpty()){
                        listfor = "";
                    }
                    if(usertype.equals("Select")){
                        usertype = "";
                    }
                    if(category.equals("Select")){
                        category = "";
                    }
                    if(min.equals("Min")){
                        min = "";
                    }
                    if(max.equals("Max")){
                        max = "";
                    }

                    if(getlocality.equals("Add Location")){
                        otherlocalityG = otherlocality.getText().toString().trim();

                        getlocality= otherlocalityG;
                    }

                    if (valid(listfor, getcity, type, category)) {
                        Intent intent = new Intent(SearchProperty.this, ShowSearchProperty.class);
                        intent.putExtra("listfor", listfor);
                        intent.putExtra("getcity", getcity);
                        intent.putExtra("type", type);
                        intent.putExtra("category", category);
                        intent.putExtra("minprice", min);
                        intent.putExtra("maxprice", max);
                        intent.putExtra("sharing", sharing);
                        intent.putExtra("usertype", usertype);
                        intent.putExtra("state", getstate);
                        intent.putExtra("locality", getlocality);
                        intent.putExtra("project", getproject);
                        startActivity(intent);
//                    showsearch(listfor, getcity, type, category);
                    }
                }else if(instance.equals("lead")){


                    String getcity;
                    getcity = city.getText().toString().trim();

                    if (valid1(listfor, getcity, type)) {
                        Intent intent = new Intent(SearchProperty.this, LeadSearch.class);
                        intent.putExtra("propertylistfor", listfor);
                        intent.putExtra("city", getcity);
                        intent.putExtra("type", type);
                        intent.putExtra("subscriptionplan", subscriptionplan);
                        startActivity(intent);
//                    showsearch(listfor, getcity, type, category);
                    }



                }


            }
        });

        apartment9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                category = "Apartment/Flat/Builder Floor";


                apartment.setColorFilter(getResources().getColor(R.color.selected), PorterDuff.Mode.SRC_IN);
                residentialland.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                housevilla.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                office.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                retail.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                land.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                industry.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                storage.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                hospitality.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                other.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);


                apartmentt.setTextColor(getResources().getColor(R.color.selected));
                residentiallandtl.setTextColor(getResources().getColor(R.color.black));
                housevillat.setTextColor(getResources().getColor(R.color.black));
                officet.setTextColor(getResources().getColor(R.color.black));
                retailt.setTextColor(getResources().getColor(R.color.black));
                landt.setTextColor(getResources().getColor(R.color.black));
                industryt.setTextColor(getResources().getColor(R.color.black));
                storaget.setTextColor(getResources().getColor(R.color.black));
                hospitalityt.setTextColor(getResources().getColor(R.color.black));
                othert.setTextColor(getResources().getColor(R.color.black));


            }
        });

        residentialland9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = "Residential Land";


                apartment.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                residentialland.setColorFilter(getResources().getColor(R.color.selected), PorterDuff.Mode.SRC_IN);
                housevilla.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                office.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                retail.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                land.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                industry.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                storage.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                hospitality.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                other.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);


                apartmentt.setTextColor(getResources().getColor(R.color.black));
                residentiallandtl.setTextColor(getResources().getColor(R.color.selected));
                housevillat.setTextColor(getResources().getColor(R.color.black));
                officet.setTextColor(getResources().getColor(R.color.black));
                retailt.setTextColor(getResources().getColor(R.color.black));
                landt.setTextColor(getResources().getColor(R.color.black));
                industryt.setTextColor(getResources().getColor(R.color.black));
                storaget.setTextColor(getResources().getColor(R.color.black));
                hospitalityt.setTextColor(getResources().getColor(R.color.black));
                othert.setTextColor(getResources().getColor(R.color.black));

            }
        });

        housevilla9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                category = "House/Villa";




                apartment.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                residentialland.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                housevilla.setColorFilter(getResources().getColor(R.color.selected), PorterDuff.Mode.SRC_IN);
                office.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                retail.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                land.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                industry.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                storage.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                hospitality.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                other.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);


                apartmentt.setTextColor(getResources().getColor(R.color.black));
                residentiallandtl.setTextColor(getResources().getColor(R.color.black));
                housevillat.setTextColor(getResources().getColor(R.color.selected));
                officet.setTextColor(getResources().getColor(R.color.black));
                retailt.setTextColor(getResources().getColor(R.color.black));
                landt.setTextColor(getResources().getColor(R.color.black));
                industryt.setTextColor(getResources().getColor(R.color.black));
                storaget.setTextColor(getResources().getColor(R.color.black));
                hospitalityt.setTextColor(getResources().getColor(R.color.black));
                othert.setTextColor(getResources().getColor(R.color.black));

            }
        });

        office9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                category = "Offices";



                apartment.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                residentialland.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                housevilla.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                office.setColorFilter(getResources().getColor(R.color.selected), PorterDuff.Mode.SRC_IN);
                retail.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                land.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                industry.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                storage.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                hospitality.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                other.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);


                apartmentt.setTextColor(getResources().getColor(R.color.black));
                residentiallandtl.setTextColor(getResources().getColor(R.color.black));
                housevillat.setTextColor(getResources().getColor(R.color.black));
                officet.setTextColor(getResources().getColor(R.color.selected));
                retailt.setTextColor(getResources().getColor(R.color.black));
                landt.setTextColor(getResources().getColor(R.color.black));
                industryt.setTextColor(getResources().getColor(R.color.black));
                storaget.setTextColor(getResources().getColor(R.color.black));
                hospitalityt.setTextColor(getResources().getColor(R.color.black));
                othert.setTextColor(getResources().getColor(R.color.black));

            }
        });

        retail9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                category = "Retail";



                apartment.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                residentialland.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                housevilla.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                office.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                retail.setColorFilter(getResources().getColor(R.color.selected), PorterDuff.Mode.SRC_IN);
                land.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                industry.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                storage.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                hospitality.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                other.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);


                apartmentt.setTextColor(getResources().getColor(R.color.black));
                residentiallandtl.setTextColor(getResources().getColor(R.color.black));
                housevillat.setTextColor(getResources().getColor(R.color.black));
                officet.setTextColor(getResources().getColor(R.color.black));
                retailt.setTextColor(getResources().getColor(R.color.selected));
                landt.setTextColor(getResources().getColor(R.color.black));
                industryt.setTextColor(getResources().getColor(R.color.black));
                storaget.setTextColor(getResources().getColor(R.color.black));
                hospitalityt.setTextColor(getResources().getColor(R.color.black));
                othert.setTextColor(getResources().getColor(R.color.black));

            }
        });

        land9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                category = "Land";




                apartment.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                residentialland.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                housevilla.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                office.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                retail.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                land.setColorFilter(getResources().getColor(R.color.selected), PorterDuff.Mode.SRC_IN);
                industry.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                storage.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                hospitality.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                other.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);


                apartmentt.setTextColor(getResources().getColor(R.color.black));
                residentiallandtl.setTextColor(getResources().getColor(R.color.black));
                housevillat.setTextColor(getResources().getColor(R.color.black));
                officet.setTextColor(getResources().getColor(R.color.black));
                retailt.setTextColor(getResources().getColor(R.color.black));
                landt.setTextColor(getResources().getColor(R.color.selected));
                industryt.setTextColor(getResources().getColor(R.color.black));
                storaget.setTextColor(getResources().getColor(R.color.black));
                hospitalityt.setTextColor(getResources().getColor(R.color.black));
                othert.setTextColor(getResources().getColor(R.color.black));

            }
        });
        industry9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                category = "Industry";



                apartment.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                residentialland.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                housevilla.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                office.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                retail.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                land.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                industry.setColorFilter(getResources().getColor(R.color.selected), PorterDuff.Mode.SRC_IN);
                storage.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                hospitality.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                other.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);

                apartmentt.setTextColor(getResources().getColor(R.color.black));
                residentiallandtl.setTextColor(getResources().getColor(R.color.black));
                housevillat.setTextColor(getResources().getColor(R.color.black));
                officet.setTextColor(getResources().getColor(R.color.black));
                retailt.setTextColor(getResources().getColor(R.color.black));
                landt.setTextColor(getResources().getColor(R.color.black));
                industryt.setTextColor(getResources().getColor(R.color.selected));
                storaget.setTextColor(getResources().getColor(R.color.black));
                hospitalityt.setTextColor(getResources().getColor(R.color.black));
                othert.setTextColor(getResources().getColor(R.color.black));

            }
        });

        storage9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                category = "Storage";


                apartment.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                residentialland.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                housevilla.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                office.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                retail.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                land.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                industry.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                storage.setColorFilter(getResources().getColor(R.color.selected), PorterDuff.Mode.SRC_IN);
                hospitality.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                other.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);

                apartmentt.setTextColor(getResources().getColor(R.color.black));
                residentiallandtl.setTextColor(getResources().getColor(R.color.black));
                housevillat.setTextColor(getResources().getColor(R.color.black));
                officet.setTextColor(getResources().getColor(R.color.black));
                retailt.setTextColor(getResources().getColor(R.color.black));
                landt.setTextColor(getResources().getColor(R.color.black));
                industryt.setTextColor(getResources().getColor(R.color.black));
                storaget.setTextColor(getResources().getColor(R.color.selected));
                hospitalityt.setTextColor(getResources().getColor(R.color.black));
                othert.setTextColor(getResources().getColor(R.color.black));

            }
        });

        hospitality9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                category = "Hospitality";



                apartment.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                residentialland.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                housevilla.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                office.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                retail.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                land.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                industry.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                storage.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                hospitality.setColorFilter(getResources().getColor(R.color.selected), PorterDuff.Mode.SRC_IN);
                other.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);

                apartmentt.setTextColor(getResources().getColor(R.color.black));
                residentiallandtl.setTextColor(getResources().getColor(R.color.black));
                housevillat.setTextColor(getResources().getColor(R.color.black));
                officet.setTextColor(getResources().getColor(R.color.black));
                retailt.setTextColor(getResources().getColor(R.color.black));
                landt.setTextColor(getResources().getColor(R.color.black));
                industryt.setTextColor(getResources().getColor(R.color.black));
                storaget.setTextColor(getResources().getColor(R.color.black));
                hospitalityt.setTextColor(getResources().getColor(R.color.selected));
                othert.setTextColor(getResources().getColor(R.color.black));

            }
        });

        other9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                category = "Others";



                apartment.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                residentialland.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                housevilla.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                office.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                retail.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                land.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                industry.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                storage.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                hospitality.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                other.setColorFilter(getResources().getColor(R.color.selected), PorterDuff.Mode.SRC_IN);

                apartmentt.setTextColor(getResources().getColor(R.color.black));
                residentiallandtl.setTextColor(getResources().getColor(R.color.black));
                housevillat.setTextColor(getResources().getColor(R.color.black));
                officet.setTextColor(getResources().getColor(R.color.black));
                retailt.setTextColor(getResources().getColor(R.color.black));
                landt.setTextColor(getResources().getColor(R.color.black));
                industryt.setTextColor(getResources().getColor(R.color.black));
                storaget.setTextColor(getResources().getColor(R.color.black));
                hospitalityt.setTextColor(getResources().getColor(R.color.black));
                othert.setTextColor(getResources().getColor(R.color.selected));

            }
        });





    }

    private boolean valid1(String listfor, String getcity, String type) {

        if(listfor.isEmpty()){
            showmessage("Please Select Property Looking for...");
            return false;
        }
        else if(getcity.isEmpty()){
            city.setError("Please Enter The City");
            showmessage("Please Enter The City");
            return false;
        }

        else if(!listfor.equals("Buyers")){
              if(type.isEmpty()){
                showmessage("Please Select Type of Property...");
                return false;
            }
        }


        return true;
    }

    private boolean valid(String listfor, String getcity, String type, String category) {

        if(getcity.isEmpty()){
            showmessage("Please Enter The City");
            return false;
        }


        return true;
    }

//    private void showsearch(final String listfor, final String getcity, final String type, final String category) {
//        progressDialog.show();
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.searchproperty, new Response.Listener<String>() {
//
//            @Override
//            public void onResponse(String response) {
//                System.out.println("response ::::: "+ response);
//                progressDialog.dismiss();
//                try {
//                    JSONObject obj = new JSONObject(response);
//                    if(obj.getBoolean("error")){
//                        showmessage(obj.getString("message"));
//                    }else{
//                        showmessage(obj.getString("message"));
//                        ListingData.getInstance(getApplicationContext()).Logout();
//                        startActivity(new Intent(SearchProperty.this, MainActivity.class));
//
//
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(SearchProperty.this,"Something Went Wrong",Toast.LENGTH_SHORT).show();
//                Log.d("VOLLEY", String.valueOf(error));
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                SmsData smsData = new SmsData();
//                Map<String, String> params = new HashMap<>();
//                params.put("header", smsData.token);
//
//                params.put("listfor", listfor);
//                params.put("minprice", min);
//                params.put("maxprice", max);
//                params.put("type", type);
//                params.put("category", category);
//                params.put("city", getcity);
//
//
//                return params;
//            }
//        };
//        stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        VolleySingleton.getInstance(SearchProperty.this).addToRequestQueue(stringRequest);
//
//    }


    private void getsubscription() {
        progressDialog.show();
        final String userid = VolleySingleton.getInstance(getApplicationContext()).id();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.getsubscription, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                System.out.println("response :: " +response);
                try {
                    JSONObject obj = new JSONObject(response);
                    if(obj.getBoolean("error")){
                        showmessage(obj.getString("message"));
                    }else{
                        subscriptionplan = obj.getString("subscriptionplan");

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SearchProperty.this,"Something Went Wrong",Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                SmsData smsData = new SmsData();
                Map<String, String> params = new HashMap<>();
                params.put("header", smsData.token);
                params.put("userid", userid);
                return params;
            }
        };


        stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }



    @Override
    protected void onResume() {

        if(VolleySingleton.getInstance(getApplicationContext()).getsearchproject() != null){
            String citysearch = VolleySingleton.getInstance(getApplicationContext()).getsearchproject();
            project.setText(citysearch);
        }

        if(VolleySingleton.getInstance(getApplicationContext()).getsearchcity() != null){
            String citysearch = VolleySingleton.getInstance(getApplicationContext()).getsearchcity();
            city.setText(citysearch);
        }

        if(VolleySingleton.getInstance(getApplicationContext()).getsearchstate() != null){
            String citysearch = VolleySingleton.getInstance(getApplicationContext()).getsearchstate();
            state.setText(citysearch);
        }

        if(VolleySingleton.getInstance(getApplicationContext()).getsearchlocality() != null){
            String citysearch = VolleySingleton.getInstance(getApplicationContext()).getsearchlocality();
            locality.setText(citysearch);
        }

        if(locality.getText().toString().trim().equals("Add Location")){
            otherlocality.setVisibility(View.VISIBLE);
        }else{
            otherlocality.setVisibility(View.GONE);
        }


        super.onResume();
    }



//    private void getcity() {
//        allcity.clear();
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.getcity, new Response.Listener<String>() {
//
//            @Override
//            public void onResponse(String response) {
//
//                System.out.println("responseec:: "+ response);
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
//                        for(int i=0; i<jsonArray.length();i++){
//                            JSONObject object = jsonArray.getJSONObject(i);
//                            String city =object.getString("city");
//                            allcity.add(city);
//
//                        }
//
//                    }
//
//
//
//
//                } catch (JSONException e) {
//                    showmessage("Something wrong please check network connection"+e);
//                    System.out.println("problem ::"+e);
//                    e.printStackTrace();
//                }
//                city.setAdapter(new ArrayAdapter<String>(SearchProperty.this, android.R.layout.simple_spinner_dropdown_item, allcity));
//
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(SearchProperty.this,"Something Went Wrong",Toast.LENGTH_SHORT).show();
//                Log.d("VOLLEY",error.getMessage());
//            }
//        });
//        stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
//    }


    private void showmessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
