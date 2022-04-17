package com.nine__square.property.AddPropertActivity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.nine__square.property.Avtivities.Buyourservices;
import com.nine__square.property.R;
import com.nine__square.property.Utils.Endpoints;
import com.nine__square.property.Utils.ListingData;
import com.nine__square.property.Utils.SmsData;
import com.nine__square.property.Utils.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BasicDetails extends AppCompatActivity {

    RadioGroup radioGroup;
    ImageView rowner , rdealer, rbuilder;
    String usertype = "", type = "", category = "",
            categorytype = "", agrement = "",pgavailablefor = "",  pgshareprivate ="",  sharingspinnumber = "", subscription = "";
    Spinner listpropertyspinner, categorytypespin, sharingnopspin;
    String propertylistfor, getsubcription = "";
    Button residentialtype, commercialtype;
    LinearLayout pgchange9,sharingnopg9,willing9 ,apartment9, residentialland9, housevilla9, office9, retail9, land9,
            industry9, storage9, hospitality9, other9, categorytypespin9;
    ImageView apartment, residentialland, housevilla, office, retail, land, industry, storage, hospitality, other;
    CheckBox checkfamily, checkmen, checkwomen, studentpg, workingpropg;
    RadioButton agremmentradio, anyradio, girlpgradio, boypgradio, anypgradio, shareradiopg, privateradiopg;
    TextView ownert , dealert, buildert, apartmentt, residentiallandtl, housevillat, officet, retailt,
            landt, industryt, storaget, hospitalityt, othert;



    ArrayList<String> getsubcription1 = new ArrayList<>();





    ArrayList<String> willing = new ArrayList<>();
    ArrayList<String> pgsutailbefor  = new ArrayList<>();
    Button btnnext;
    private ProgressDialog progressDialog;





    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_details);



        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(" Basic Details");
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


        if(VolleySingleton.getInstance(getApplicationContext()).isLogin()){
            getsubscription();
        }







        //type of user
        rowner = findViewById(R.id.rowner);
        rdealer = findViewById(R.id.rdealer);
        rbuilder = findViewById(R.id.rbuilder);

        //type

        commercialtype = findViewById(R.id.comercial);
        residentialtype = findViewById(R.id.residential);


        // categor
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


        //textview
//        rownert , dealert, buildert, apartmentt, residentiallandtl, housevillat, officet, ,
//                landt, industryt, storaget, hospitalityt, othert;

        ownert = findViewById(R.id.ownert);
        dealert = findViewById(R.id.dealert);
        buildert = findViewById(R.id.buildert);
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




        // color of category
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



        //hide category
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



        //next btn
        btnnext = findViewById(R.id.btnnext);




        //spiner listing for
        listpropertyspinner = findViewById(R.id.listpropertyspinner);


        //spinner categorytype
         categorytypespin = findViewById(R.id.categorytypespin);
         categorytypespin9 = findViewById(R.id.categorytypespin9);


         //willing
        willing9 = findViewById(R.id.willing9);
        checkfamily = findViewById(R.id.checkfamily);
        checkmen = findViewById(R.id.checkmen);
        checkwomen = findViewById(R.id.checkwomen);
        agremmentradio =findViewById(R.id.agremmentradio);
        anyradio = findViewById(R.id.anyradio);

        //pgchange
        sharingnopg9 = findViewById(R.id.sharingnopg9);
        pgchange9 = findViewById(R.id.pgchange9);
        sharingnopspin = findViewById(R.id.sharingnopgspin);
        shareradiopg = findViewById(R.id.shareradiopg);
        privateradiopg = findViewById(R.id.privateradiopg);
        studentpg = findViewById(R.id.studentpg);
        workingpropg = findViewById(R.id.workingpropg);
        girlpgradio = findViewById(R.id.girlpgradio);
        boypgradio = findViewById(R.id.boypgradio);
        anypgradio = findViewById(R.id.anypgradio);





//
//
//        ArrayAdapter<String> adapterSubcription = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, getsubcriptionspin);
//        adapterSubcription.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
//        subscriptionspin.setAdapter(adapterSubcription);
//
//        int positionsubscription = adapterSubcription.getPosition(ListingData.getInstance(getApplicationContext()).propertylistfor());
//        subscriptionspin.setSelection(positionsubscription);
//
//        subscriptionspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                subscription = subscriptionspin.getSelectedItem().toString();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });




        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.propertylistfor, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listpropertyspinner.setAdapter(adapter2);

        int position1 = adapter2.getPosition(ListingData.getInstance(getApplicationContext()).propertylistfor());
        listpropertyspinner.setSelection(position1);


        listpropertyspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                propertylistfor = listpropertyspinner.getSelectedItem().toString();

        // Owner , Dealer, Builde

                if(propertylistfor.equals("Sell")){
                    residentialtype.setTextColor(Color.WHITE);
                    commercialtype.setTextColor(Color.WHITE);
                    type = "";
                    category = "";
                    commercialtype.setVisibility(View.VISIBLE);
                    residentialland9.setVisibility(View.VISIBLE);
                    pgchange9.setVisibility(View.GONE);


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



                }

                if (propertylistfor.equals("Rent")){
                    residentialtype.setTextColor(Color.WHITE);
                    type = "";
                    category = "";
                    residentialland9.setVisibility(View.GONE);
                    other9.setVisibility(View.VISIBLE);
                    commercialtype.setVisibility(View.VISIBLE);
                    pgchange9.setVisibility(View.GONE);


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


                }
                if (propertylistfor.equals("Paying Guest")){
                    category = "";
                    residentialtype.setTextColor(Color.GREEN);
                    type = "Residential";
                    commercialtype.setTextColor(Color.WHITE);
                    commercialtype.setVisibility(View.GONE);
                    other9.setVisibility(View.GONE);
                    residentialland9.setVisibility(View.GONE);
                    pgchange9.setVisibility(View.VISIBLE);

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

                }

                rowner.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                rdealer.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                rbuilder.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);


                if(ListingData.getInstance(getApplicationContext()).usertype() != null) {
            if (ListingData.getInstance(getApplicationContext()).usertype().equals("Owner")) {
                usertype = "Owner";
                rowner.setColorFilter(getResources().getColor(R.color.selected), PorterDuff.Mode.SRC_IN);
                ownert.setTextColor(getResources().getColor(R.color.selected));
            } else if (ListingData.getInstance(getApplicationContext()).usertype().equals("Dealer")) {
                usertype = "Dealer";
                rdealer.setColorFilter(getResources().getColor(R.color.selected), PorterDuff.Mode.SRC_IN);
                dealert.setTextColor(getResources().getColor(R.color.selected));
            } else {
                usertype = "Builder";
                rbuilder.setColorFilter(getResources().getColor(R.color.selected), PorterDuff.Mode.SRC_IN);
                buildert.setTextColor(getResources().getColor(R.color.selected));

            }
        }











        // type Residential commersial
                if(ListingData.getInstance(getApplicationContext()).usertype() != null) {
                    if(ListingData.getInstance(getApplicationContext()).type().equals("Residential")){
                        type = "Residential";
                        residentialtype.setTextColor(Color.GREEN);
                        commercialtype.setTextColor(Color.WHITE);

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


                        if(propertylistfor.equals("Sell")){
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

                        if(propertylistfor.equals("Rent")){
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

                        if(propertylistfor.equals("Paying Guest")){
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
                    }else{

//                        Toast.makeText(BasicDetails.this, ListingData.getInstance(getApplicationContext()).type(), Toast.LENGTH_SHORT).show();

                        type = "Commercial";
                        commercialtype.setTextColor(Color.GREEN);
                        residentialtype.setTextColor(Color.WHITE);

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
                }


        // Category
                categorytypespin9.setVisibility(View.GONE);


    if(ListingData.getInstance(getApplicationContext()).category() != null) {

        if (ListingData.getInstance(getApplicationContext()).category().equals("Apartment/Flat/Builder Floor")) {
            category = "Apartment/Flat/Builder Floor";
            categorytypespin9.setVisibility(View.VISIBLE);
            apartment.setColorFilter(getResources().getColor(R.color.selected), PorterDuff.Mode.SRC_IN);
            apartmentt.setTextColor(getResources().getColor(R.color.selected));

            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(BasicDetails.this, R.array.categorytypeapartment, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            categorytypespin.setAdapter(adapter);

            int position00 = adapter.getPosition(ListingData.getInstance(getApplicationContext()).categorytype());
            categorytypespin.setSelection(position00);
        }

        if (ListingData.getInstance(getApplicationContext()).category().equals("Residential Land")) {
            category = "Residential Land";
            residentialland.setColorFilter(getResources().getColor(R.color.selected), PorterDuff.Mode.SRC_IN);
            residentiallandtl.setTextColor(getResources().getColor(R.color.selected));
            categorytypespin9.setVisibility(View.GONE);
        }

        if (ListingData.getInstance(getApplicationContext()).category().equals("House/Villa")) {
            category = "House/Villa";
            categorytypespin9.setVisibility(View.VISIBLE);
            housevilla.setColorFilter(getResources().getColor(R.color.selected), PorterDuff.Mode.SRC_IN);
            housevillat.setTextColor(getResources().getColor(R.color.selected));

            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(BasicDetails.this, R.array.housevilla, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            categorytypespin.setAdapter(adapter);

            int position22 = adapter.getPosition(ListingData.getInstance(getApplicationContext()).categorytype());
            categorytypespin.setSelection(position22);

        }

        if (ListingData.getInstance(getApplicationContext()).category().equals("Offices")) {
            category = "Offices";
            categorytypespin9.setVisibility(View.VISIBLE);
            office.setColorFilter(getResources().getColor(R.color.selected), PorterDuff.Mode.SRC_IN);
            officet.setTextColor(getResources().getColor(R.color.selected));


            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(BasicDetails.this, R.array.office, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            categorytypespin.setAdapter(adapter);

            int position5 = adapter.getPosition(ListingData.getInstance(getApplicationContext()).categorytype());
            categorytypespin.setSelection(position5);

        }

        if (ListingData.getInstance(getApplicationContext()).category().equals("Retail")) {
            category = "Retail";
            categorytypespin9.setVisibility(View.VISIBLE);
            retail.setColorFilter(getResources().getColor(R.color.selected), PorterDuff.Mode.SRC_IN);
            retailt.setTextColor(getResources().getColor(R.color.selected));

            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(BasicDetails.this, R.array.retail, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            categorytypespin.setAdapter(adapter);

            int position6 = adapter.getPosition(ListingData.getInstance(getApplicationContext()).categorytype());
            categorytypespin.setSelection(position6);

        }

        if (ListingData.getInstance(getApplicationContext()).category().equals("Land")) {
            category = "Land";
            categorytypespin9.setVisibility(View.VISIBLE);
            land.setColorFilter(getResources().getColor(R.color.selected), PorterDuff.Mode.SRC_IN);
            landt.setTextColor(getResources().getColor(R.color.selected));

            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(BasicDetails.this, R.array.land, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            categorytypespin.setAdapter(adapter);

            int position7 = adapter.getPosition(ListingData.getInstance(getApplicationContext()).categorytype());
            categorytypespin.setSelection(position7);

        }

        if (ListingData.getInstance(getApplicationContext()).category().equals("Industry")) {
            category = "Industry";
            categorytypespin9.setVisibility(View.VISIBLE);
            industry.setColorFilter(getResources().getColor(R.color.selected), PorterDuff.Mode.SRC_IN);
            industryt.setTextColor(getResources().getColor(R.color.selected));

            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(BasicDetails.this, R.array.industry, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            categorytypespin.setAdapter(adapter);

            int position8 = adapter.getPosition(ListingData.getInstance(getApplicationContext()).categorytype());
            categorytypespin.setSelection(position8);

        }

        if (ListingData.getInstance(getApplicationContext()).category().equals("Storage")) {
            category = "Storage";
            categorytypespin9.setVisibility(View.VISIBLE);
            storage.setColorFilter(getResources().getColor(R.color.selected), PorterDuff.Mode.SRC_IN);
            storaget.setTextColor(getResources().getColor(R.color.selected));

            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(BasicDetails.this, R.array.storage, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            categorytypespin.setAdapter(adapter);

            int position9 = adapter.getPosition(ListingData.getInstance(getApplicationContext()).categorytype());
            categorytypespin.setSelection(position9);

        }

        if (ListingData.getInstance(getApplicationContext()).category().equals("Hospitality")) {
            category = "Hospitality";
            categorytypespin9.setVisibility(View.VISIBLE);
            hospitality.setColorFilter(getResources().getColor(R.color.selected), PorterDuff.Mode.SRC_IN);
            hospitalityt.setTextColor(getResources().getColor(R.color.selected));

            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(BasicDetails.this, R.array.hospital, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            categorytypespin.setAdapter(adapter);

            int position0 = adapter.getPosition(ListingData.getInstance(getApplicationContext()).categorytype());
            categorytypespin.setSelection(position0);

        }

        if(ListingData.getInstance(getApplicationContext()).categorytype() != null ) {


            if((ListingData.getInstance(getApplicationContext()).category().equals("Residential Land"))) {
                categorytypespin9.setVisibility(View.GONE);
            }else {
                categorytypespin9.setVisibility(View.VISIBLE);

            }
        }

        if (ListingData.getInstance(getApplicationContext()).category().equals("Others")) {
            category = "Others";
            categorytypespin9.setVisibility(View.GONE);
            other.setColorFilter(getResources().getColor(R.color.selected), PorterDuff.Mode.SRC_IN);
            othert.setTextColor(getResources().getColor(R.color.selected));

        }

    }

                if(ListingData.getInstance(getApplicationContext()).type() != null) {

                    if (ListingData.getInstance(getApplicationContext()).type().equals("Residential") && ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Rent")) {
                        willing9.setVisibility(View.VISIBLE);
                    } else {
                        willing9.setVisibility(View.GONE);
                    }

                }
        if(ListingData.getInstance(getApplicationContext()).willing() != null) {

            if (ListingData.getInstance(getApplicationContext()).willing().contains("Family")) {
                willing.add("Family");
                checkfamily.setChecked(true);
            } else {
                willing.remove("Family");
                checkfamily.setChecked(false);
            }

            if (ListingData.getInstance(getApplicationContext()).willing().contains("Single Men")) {
                willing.add("Single Men");
                checkmen.setChecked(true);
            } else {
                willing.remove("Single Men");
                checkmen.setChecked(false);
            }

            if (ListingData.getInstance(getApplicationContext()).willing().contains("Single Women")) {
                willing.add("Single Women");
                checkwomen.setChecked(true);
            } else {
                willing.remove("Single Women");
                checkwomen.setChecked(false);
            }

            if (ListingData.getInstance(getApplicationContext()).pgsuitablefor().contains("Students")) {
                pgsutailbefor.add("Students");
                studentpg.setChecked(true);
            } else {
                pgsutailbefor.remove("Students");
                studentpg.setChecked(false);
            }
            if (ListingData.getInstance(getApplicationContext()).pgsuitablefor().contains("Working Professionals")) {
                pgsutailbefor.add("Working Professionals");
                workingpropg.setChecked(true);
            } else {
                pgsutailbefor.remove("Working Professionals");
                workingpropg.setChecked(false);
            }


        }
//                if(ListingData.getInstance(getApplicationContext()).categorytype() != null ) {
//
//
//                if((ListingData.getInstance(getApplicationContext()).category().equals("Residential Land"))) {
//                    categorytypespin9.setVisibility(View.GONE);
//                }else {
//                    categorytypespin9.setVisibility(View.VISIBLE);
//
//                }
//                }



                    if(ListingData.getInstance(getApplicationContext()).agrement() != null) {


          if (ListingData.getInstance(getApplicationContext()).agrement().equals("Company Lease Agreement")) {
              agrement = "Company Lease Agreement";
              agremmentradio.setChecked(true);
          } else {
              agrement = "Any";
              anypgradio.setChecked(true);
          }
      }

                if(ListingData.getInstance(getApplicationContext()).pavailabefor() != null) {

                    if (ListingData.getInstance(getApplicationContext()).pavailabefor().equals("Girls")) {
                        pgavailablefor = "Girls";
                        girlpgradio.setChecked(true);
                    } else if (ListingData.getInstance(getApplicationContext()).pavailabefor().equals("Boys")) {
                        pgavailablefor = "Boys";
                        boypgradio.setChecked(true);
                    } else {
                        pgavailablefor = "Any";
                        anypgradio.setChecked(true);
                    }
                }

                if(ListingData.getInstance(getApplicationContext()).pgshareprivate() != null) {

                    if (ListingData.getInstance(getApplicationContext()).pgshareprivate().equals("Private")) {
                        pgshareprivate = "Private";
                        privateradiopg.setChecked(true);
                        sharingnopg9.setVisibility(View.GONE);


                    } else {
                        pgshareprivate = "Sharing";
                        shareradiopg.setChecked(true);
                    }
                }







        // categorytype













//        residentialland9.setVisibility(View.GONE);



        //color for user

//
//        //color for type
//        residentialtype.setTextColor(Color.black);
//        commercialtype.setTextColor(Color.black);



        //user clicklistner color change
        rowner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rowner.setColorFilter(getResources().getColor(R.color.selected), PorterDuff.Mode.SRC_IN);
                rdealer.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                rbuilder.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);

                ownert.setTextColor(getResources().getColor(R.color.selected));
                dealert.setTextColor(getResources().getColor(R.color.black));
                buildert.setTextColor(getResources().getColor(R.color.black));


                usertype = "Owner";

            }
        });


        rdealer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rowner.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                rdealer.setColorFilter(getResources().getColor(R.color.selected), PorterDuff.Mode.SRC_IN);
                rbuilder.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);

                ownert.setTextColor(getResources().getColor(R.color.black));
                dealert.setTextColor(getResources().getColor(R.color.selected));
                buildert.setTextColor(getResources().getColor(R.color.black));

                usertype = "Dealer";

            }
        });


        rbuilder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rowner.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                rdealer.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                rbuilder.setColorFilter(getResources().getColor(R.color.selected), PorterDuff.Mode.SRC_IN);

                ownert.setTextColor(getResources().getColor(R.color.black));
                dealert.setTextColor(getResources().getColor(R.color.black));
                buildert.setTextColor(getResources().getColor(R.color.selected));

                usertype = "Builder";

            }
        });


        //listing for



//                Toast.makeText(BasicDetails.this, imageselected+"  "+  propertylistfor, Toast.LENGTH_SHORT).show();





                if (type.equals("Residential") && propertylistfor.equals("Rent")) {
                    willing9.setVisibility(View.VISIBLE);
                }else{
                    willing9.setVisibility(View.GONE);
                }
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });



        // type click listener
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


                if(propertylistfor.equals("Sell")){
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

                if(propertylistfor.equals("Rent")){
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

                if(propertylistfor.equals("Paying Guest")){
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


//                apartment9.setVisibility(View.VISIBLE);
//                residentialland9.setVisibility(View.VISIBLE);
//                housevilla9.setVisibility(View.VISIBLE);
//                office9.setVisibility(View.GONE);
//                retail9.setVisibility(View.GONE);
//                land9.setVisibility(View.GONE);
//                industry9.setVisibility(View.GONE);
//                storage9.setVisibility(View.GONE);
//                hospitality9.setVisibility(View.GONE);
//                other9.setVisibility(View.VISIBLE);


//                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(BasicDetails.this, R.array.propertylistfor, android.R.layout.simple_spinner_item);
//                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                listpropertyspinner.setAdapter(adapter);





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


//
//                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(BasicDetails.this, R.array.propertylistfor, android.R.layout.simple_spinner_item);
//                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                listpropertyspinner.setAdapter(adapter);
            }
        });






        apartment9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                category = "Apartment/Flat/Builder Floor";
                categorytype = "";

                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(BasicDetails.this, R.array.categorytypeapartment, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                categorytypespin.setAdapter(adapter);

                categorytypespin9.setVisibility(View.VISIBLE);

                if (type.equals("Residential") && propertylistfor.equals("Rent")) {
                    willing9.setVisibility(View.VISIBLE);
                }else{
                    willing9.setVisibility(View.GONE);
                }

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
                willing9.setVisibility(View.GONE);
                category = "Residential Land";
                categorytype = "";

                categorytypespin9.setVisibility(View.GONE);

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
                categorytype = "";

                if (type.equals("Residential") && propertylistfor.equals("Rent")) {
                    willing9.setVisibility(View.VISIBLE);
                }else{
                    willing9.setVisibility(View.GONE);
                }



                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(BasicDetails.this, R.array.housevilla, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                categorytypespin.setAdapter(adapter);

                categorytypespin9.setVisibility(View.VISIBLE);

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
                categorytype = "";

                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(BasicDetails.this, R.array.office, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                categorytypespin.setAdapter(adapter);
                willing9.setVisibility(View.GONE);
                categorytypespin9.setVisibility(View.VISIBLE);

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
                categorytype = "";


                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(BasicDetails.this, R.array.retail, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                categorytypespin.setAdapter(adapter);
                willing9.setVisibility(View.GONE);
                categorytypespin9.setVisibility(View.VISIBLE);

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
                categorytype = "";


                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(BasicDetails.this, R.array.land, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                categorytypespin.setAdapter(adapter);

                categorytypespin9.setVisibility(View.VISIBLE);
                willing9.setVisibility(View.GONE);

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
                categorytype = "";


                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(BasicDetails.this, R.array.industry, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                categorytypespin.setAdapter(adapter);

                categorytypespin9.setVisibility(View.VISIBLE);

                willing9.setVisibility(View.GONE);

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
                categorytype = "";

                willing9.setVisibility(View.GONE);

                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(BasicDetails.this, R.array.storage, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                categorytypespin.setAdapter(adapter);

                categorytypespin9.setVisibility(View.VISIBLE);

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
                categorytype = "";

                willing9.setVisibility(View.GONE);



                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(BasicDetails.this, R.array.hospital, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                categorytypespin.setAdapter(adapter);

                categorytypespin9.setVisibility(View.VISIBLE);

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
                categorytype = "";

                if (type.equals("Residential") && propertylistfor.equals("Rent")) {
                    willing9.setVisibility(View.VISIBLE);
                }else{
                    willing9.setVisibility(View.GONE);
                }


                categorytypespin9.setVisibility(View.GONE);

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





        // categorytype
        categorytypespin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categorytype = categorytypespin.getSelectedItem().toString();

                if (type.equals("Residential") && propertylistfor.equals("Rent")) {
                    willing9.setVisibility(View.VISIBLE);
                }else{
                    willing9.setVisibility(View.GONE);
                }






//                if (!propertylistfor.equals("Select City")){
//                    getlocality(cityvalue);
//                }
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });





        anyradio.setChecked(true);
        agrement = "Any";


        agremmentradio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agrement = "Company Lease Agreement";
            }
        });

        anyradio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agrement = "Any";
            }
        });




        girlpgradio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pgavailablefor = "Girls";
            }
        });

        boypgradio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pgavailablefor = "Boys";
            }
        });

        anypgradio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pgavailablefor = "Any";
            }
        });



        shareradiopg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pgshareprivate = "Sharing";
                sharingnopg9.setVisibility(View.VISIBLE);
            }
        });
        privateradiopg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pgshareprivate = "Private";
                sharingnopg9.setVisibility(View.GONE);
            }
        });
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(BasicDetails.this, R.array.pgno, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sharingnopspin.setAdapter(adapter1);

        int position2 = adapter1.getPosition(ListingData.getInstance(getApplicationContext()).sharingspinnumber());
        sharingnopspin.setSelection(position2);

        sharingnopspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sharingspinnumber = sharingnopspin.getSelectedItem().toString();




//                if (!propertylistfor.equals("Select City")){
//                    getlocality(cityvalue);
//                }
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

//        int position = adapter.getPosition("Rent");
//        listpropertyspinner.setSelection(position);



        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                System.out.println("subcription :::"+ getsubcription + " size:"+ getsubcription1.size()+ " ::::::"+ getsubcription1.get(0));

//                String usertype = "", type = "", category = "", categorytype = "", agrement = "",pgavailablefor = "",  pgshareprivate ="",  sharingspinnumber = "";

                if(usertype.isEmpty()){
                    showmessage("Please Select User Type");
                }
                else if(type.isEmpty()){
                    showmessage("Please Select Property Type");
                }
                else if(category.isEmpty()){
                    showmessage("Please Select Category of Property");
                }
                else if(category.equals("Others") && propertylistfor.equals("Sell")){

                        if(getsubcription1.get(0).equals("0")){
                            startActivity(new Intent(BasicDetails.this, Buyourservices.class));
                        }else{
                            if(sharingspinnumber.equals("Select")){
                                sharingspinnumber = "";
                            }
                            if(ListingData.getInstance(getApplicationContext()).getinstance().equals("Add")){
                                ListingData.getInstance(getApplicationContext()).setsubscription(getsubcription1.get(0));
                            }else{
                                ListingData.getInstance(getApplicationContext()).setsubscription("");
                            }

                            ListingData.getInstance(getApplicationContext()).basicdetails(usertype,type,category,categorytype,agrement,pgavailablefor,
                                    pgshareprivate,sharingspinnumber,propertylistfor, String.valueOf(willing), String.valueOf(pgsutailbefor));
                            startActivity(new Intent(BasicDetails.this, Location.class));
                        }



                }
                else if(category.equals("Residential Land") && propertylistfor.equals("Sell")){

                    if(getsubcription1.get(0).equals("0")){
                        startActivity(new Intent(BasicDetails.this, Buyourservices.class));
                    }else{
                        if(sharingspinnumber.equals("Select")){
                            sharingspinnumber = "";
                        }
                        if(ListingData.getInstance(getApplicationContext()).getinstance().equals("Add")){
                            ListingData.getInstance(getApplicationContext()).setsubscription(getsubcription1.get(0));
                        }else{
                            ListingData.getInstance(getApplicationContext()).setsubscription("");
                        }
                        ListingData.getInstance(getApplicationContext()).basicdetails(usertype,type,category,categorytype,agrement,pgavailablefor,
                                pgshareprivate,sharingspinnumber,propertylistfor, String.valueOf(willing), String.valueOf(pgsutailbefor));
                        startActivity(new Intent(BasicDetails.this, Location.class));
                    }


                }

                else if(propertylistfor.equals("Sell") && categorytype.equals("Select")){
                    showmessage("Please Select The Category Type");
                }

                else if(propertylistfor.equals("Rent") && categorytype.equals("Select") && !category.equals("Others")){
                    showmessage("Please Select The Category Type");
                }
                else if(propertylistfor.equals("Rent") && type.equals("Residential") && willing.isEmpty()){
                    showmessage("Please Select The Willing To Rent Out");
                }

                else if(propertylistfor.equals("Paying Guest") && categorytype.equals("Select") && !category.equals("Others")){
                    showmessage("Please Select The Category Type");
                }
                else if(propertylistfor.equals("Paying Guest") && pgavailablefor.isEmpty()){
                    showmessage("Please Select The Pg Available For");
                }

                else if(propertylistfor.equals("Paying Guest") && pgsutailbefor.isEmpty()){
                    showmessage("Please Select The Pg Suitable For");
                }

                else if(propertylistfor.equals("Paying Guest") && pgshareprivate.isEmpty()){
                    showmessage("Please Select The Pg Private Or Share");
                }

                else if(propertylistfor.equals("Paying Guest") && pgshareprivate.equals("Sharing") && sharingspinnumber.equals("Select")){
                    showmessage("Please Select The Room Sharing Person Number");
                }
                else if(category.equals("Others") && propertylistfor.equals("Rent") && willing.isEmpty() && type.equals("Residential")){
                    showmessage("Please Select the Willing To Rent Out");
                }

                else {

                    if(getsubcription1.get(0).equals("0")){
                        startActivity(new Intent(BasicDetails.this, Buyourservices.class));
                    } else {
                        if (sharingspinnumber.equals("Select")) {
                            sharingspinnumber = "";
                        }
                        if(ListingData.getInstance(getApplicationContext()).getinstance().equals("Add")){
                            ListingData.getInstance(getApplicationContext()).setsubscription(getsubcription1.get(0));
                        }else{
                            ListingData.getInstance(getApplicationContext()).setsubscription("");
                        }
                        ListingData.getInstance(getApplicationContext()).basicdetails(usertype, type, category, categorytype, agrement, pgavailablefor,
                                pgshareprivate, sharingspinnumber, propertylistfor, String.valueOf(willing), String.valueOf(pgsutailbefor));
                        startActivity(new Intent(BasicDetails.this, Location.class));
                    }
                }


                }
        });
    }

    private void   getsubscription() {
        final String userid = VolleySingleton.getInstance(getApplicationContext()).id();
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.getsubscription, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                System.out.println("response :: " +response);
                progressDialog.dismiss();
                try {
                    JSONObject obj = new JSONObject(response);
                    if(obj.getBoolean("error")){
                        showmessage(obj.getString("message"));
                    }else{
                        String result = obj.getString("subscriptionplan");
                        getsubcription1.add(result);


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(BasicDetails.this,"Something Went Wrong",Toast.LENGTH_SHORT).show();
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

    private void showmessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void seletedItem(View view) {

        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()) {
            case R.id.studentpg:
                if (checked) {
                    pgsutailbefor.add("Students");
                } else {
                    pgsutailbefor.remove("Students");
                }
                break;

            case R.id.workingpropg:
                if (checked) {
                    pgsutailbefor.add("Working Professionals");
                } else {
                    pgsutailbefor.remove("Working Professionals");
                }
                break;

            case R.id.checkfamily:
                if(checked){
                    willing.add("Family");
                }else{
                    willing.remove("Family");
                }
                break;

            case R.id.checkmen:
                if(checked){
                    willing.add("Single Men");
                }else{
                    willing.remove("Single Men");
                }
                break;

            case R.id.checkwomen:
                if(checked){
                    willing.add("Single Women");
                }else{
                    willing.remove("Single Women");
                }
                break;
        }
    }
}
