package com.nine__square.property.AddPropertActivity;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.nine__square.property.R;
import com.nine__square.property.Utils.ListingData;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

public class Features extends AppCompatActivity {

    LinearLayout anemities9, moreanemities9, watersource9, overlooking9, facing9,
            typeofflooring9, powerbackup9, byers9, description9, timesuitable9 , rule1, rule2, somefeatures9, facingwidth9, boundrywall9, moreanemitiesitem91;
    TextView somefeaturestext, overlookingtext, moreanemities;
    Spinner facingspin, unitspin, typeofflooringspin, powerbackupspin, lasttimespin;
    String facingspinget = "", unitspinget = "", typeofflooringspinget = "", powerbackupspinget = "", lasttimespinget = "";
    EditText widthfacing, description;
    Button btnback, btnnext, pickimage;
    RadioButton pety, petn, visitery, visitern, smokingy, smokingn, alcoholy, alcoholn, eventy,eventn, boundryyes, boundryno;
    String widthfacingget, descriptionget;
    ImageView img;

    String encodedImage="";
    String imagepresent;
    Bitmap bitmap;
    int a = 0;

    ImageView uparrow, downarrow;

    ArrayList<String> anemitiesitem = new ArrayList<>();
    ArrayList<String> moreanemitiesitem = new ArrayList<>();
    ArrayList<String> watersourceitem = new ArrayList<>();
    ArrayList<String> overlookingitem = new ArrayList<>();
    ArrayList<String> somefeatureitem = new ArrayList<>();
    ArrayList<String> byersitem = new ArrayList<>();
    ArrayList<String> timeitems = new ArrayList<>();


    String anemitiesitemget,moreanemitiesitemget, watersourceitemget, overlookingitemget, somefeatureitemget, byersitemget, timeitemsget;

    String pet = "", visiter = "", smoking = "", alcohol = "",event = "", boundrywall= "";


    CheckBox lift, park, maintenancestaff, visiterparking, fengshui, intercomefacility, waterstorage, alaram, swimmingpool, ac, garden,wifi,pipedgas,waterpurifier,
    clubhouse, shoppingcenter,waterdissposal, rainwaterharvesting, bankedattachedproperty,gym, municiplecorporation,borewell,parkgarden,
    mainroad,club, lakefacing, seafacing, others,gatedsociety, cornerproperty, petfriendly, wheelchairfriendly, water24,
    visiterparkingavailable, closetoschool,closetobank,naturallight,closetohospital,closetomarket,airyrooms,
    eight12, twele3, three6, six9,nine11, none;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_features);


        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Features");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });


        //linear layout
        anemities9 = findViewById(R.id.anemities9);
        moreanemities9 = findViewById(R.id.moreanemitiesitem9);
        watersource9 = findViewById(R.id.watersource9);
        overlooking9 = findViewById(R.id.overlooking9);
        facing9 = findViewById(R.id.facing9);
        pickimage = findViewById(R.id.pickimage);
        typeofflooring9 = findViewById(R.id.typeofflooring9);
        powerbackup9 = findViewById(R.id.powerbackup9);
        byers9 = findViewById(R.id.byers9);
        description9 = findViewById(R.id.descrioption9);
        timesuitable9 = findViewById(R.id.timesuitable9);
        rule1 = findViewById(R.id.rules1);
        rule2 = findViewById(R.id.rule2);
        somefeatures9 = findViewById(R.id.somefeatures9);
        facingwidth9 = findViewById(R.id.facingwidth9);
        boundrywall9 = findViewById(R.id.boundrywall9);
        moreanemitiesitem91 = findViewById(R.id.moreanemitiesitem91);

        // textview
        somefeaturestext = findViewById(R.id.somefeaturestext);
        overlookingtext = findViewById(R.id.overlookingtext);
        moreanemities = findViewById(R.id.moreanemities);

        //Spinner

        facingspin = findViewById(R.id.facingspin);
        unitspin = findViewById(R.id.unitspin);
        typeofflooringspin = findViewById(R.id.typeofflooringspin);
        powerbackupspin = findViewById(R.id.powerbackupspin);
        lasttimespin = findViewById(R.id.lasttimespin);

        // Edittext

        widthfacing = findViewById(R.id.widthfaceing);
        description = findViewById(R.id.description);

        // button

        btnnext = findViewById(R.id.btnnext);
        btnback = findViewById(R.id.btnback);

        img = findViewById(R.id.img);


        //arrows

        uparrow = findViewById(R.id.uparrow);
        downarrow = findViewById(R.id.downarrow);


        uparrow.setVisibility(View.GONE);

        //radiobtn

//        RadioButton pety, petn, visitery, visitern, smokingy, smokingn, alcoholy, alcoholn, eventy,eventn;
        pety = findViewById(R.id.pety);
        petn = findViewById(R.id.petn);
        visitery = findViewById(R.id.visitery);
        visitern = findViewById(R.id.visitern);
        smokingy = findViewById(R.id.smokingy);
        smokingn = findViewById(R.id.smokingn);
        alcoholy = findViewById(R.id.alcoholy);
        alcoholn = findViewById(R.id.alcoholn);
        eventy = findViewById(R.id.eventy);
        eventn = findViewById(R.id.eventn);
        boundryyes = findViewById(R.id.boundryyes);
        boundryno = findViewById(R.id.boundryno);





        anemities9.setVisibility(View.GONE);
        moreanemities9.setVisibility(View.GONE);
        watersource9.setVisibility(View.GONE);
        overlooking9.setVisibility(View.GONE);
        facing9.setVisibility(View.GONE);
        typeofflooring9.setVisibility(View.GONE);
        powerbackup9.setVisibility(View.GONE);
        byers9.setVisibility(View.GONE);
        description9.setVisibility(View.GONE);
        timesuitable9.setVisibility(View.GONE);
        rule1.setVisibility(View.GONE);
        rule2.setVisibility(View.GONE);
        somefeatures9.setVisibility(View.GONE);
        facingwidth9.setVisibility(View.GONE);
        boundrywall9.setVisibility(View.GONE);
        moreanemitiesitem91.setVisibility(View.GONE);





        lift = findViewById(R.id.lift);
        park = findViewById(R.id.park);
        maintenancestaff = findViewById(R.id.maintenancestaff);
        visiterparking = findViewById(R.id.visiterparking);
        fengshui = findViewById(R.id.fengshui);
        intercomefacility = findViewById(R.id.intercomefacility);
        waterstorage = findViewById(R.id.waterstorage);
        alaram = findViewById(R.id.alarm);
        swimmingpool = findViewById(R.id.swimmingpool);
        ac = findViewById(R.id.ac);
        garden = findViewById(R.id.garden);
        wifi = findViewById(R.id.wifi);
        pipedgas = findViewById(R.id.pipedgas);
        waterpurifier = findViewById(R.id.waterpurifier);



        clubhouse = findViewById(R.id.clubhouse);
        shoppingcenter = findViewById(R.id.shoppingcenter);
        waterdissposal = findViewById(R.id.waterdisposal);
        rainwaterharvesting = findViewById(R.id.rainwaterharvesting);
        bankedattachedproperty = findViewById(R.id.bankattachedproperty);
        gym = findViewById(R.id.gym);
        municiplecorporation = findViewById(R.id.muncipalcorporation);
        borewell = findViewById(R.id.borewell);
        parkgarden = findViewById(R.id.parkgarden);



        mainroad = findViewById(R.id.mainrood);
        club = findViewById(R.id.club);
        lakefacing = findViewById(R.id.lakefacing);
        seafacing = findViewById(R.id.seafacing);
        others = findViewById(R.id.others);
        gatedsociety = findViewById(R.id.gatedsociety);
        cornerproperty = findViewById(R.id.cornerproperty);
        petfriendly = findViewById(R.id.petfriendly);
        wheelchairfriendly = findViewById(R.id.wheelchairfriendly);
        water24 = findViewById(R.id.water24);




        visiterparkingavailable = findViewById(R.id.visiterparkingavailable);
        closetoschool = findViewById(R.id.closetoschool);
        closetobank = findViewById(R.id.closetobank);
        naturallight = findViewById(R.id.naturallight);
        closetohospital = findViewById(R.id.closetohospital);
        closetomarket = findViewById(R.id.closetomarket);
        airyrooms = findViewById(R.id.airyrooms);
        eight12 = findViewById(R.id.eight12);
        twele3 = findViewById(R.id.twele3);
        three6 = findViewById(R.id.three6);
        six9 = findViewById(R.id.six9);
        nine11 = findViewById(R.id.nine11);
        none = findViewById(R.id.none);

        //Sell

        if(ListingData.getInstance(getApplicationContext()).category().equals("Apartment/Flat/Builder Floor") && ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Sell")){
            anemities9.setVisibility(View.VISIBLE);
            moreanemities9.setVisibility(View.VISIBLE);
            watersource9.setVisibility(View.VISIBLE);
            overlooking9.setVisibility(View.VISIBLE);
            somefeatures9.setVisibility(View.VISIBLE);
            facing9.setVisibility(View.VISIBLE);
            facingwidth9.setVisibility(View.VISIBLE);
            typeofflooring9.setVisibility(View.VISIBLE);
            powerbackup9.setVisibility(View.VISIBLE);
            byers9.setVisibility(View.VISIBLE);
            description9.setVisibility(View.VISIBLE);
            timesuitable9.setVisibility(View.VISIBLE);
            rule1.setVisibility(View.GONE);
            rule2.setVisibility(View.GONE);

        }
        if(ListingData.getInstance(getApplicationContext()).category().equals("Residential Land") && ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Sell")){
            anemities9.setVisibility(View.VISIBLE);
            moreanemities9.setVisibility(View.GONE);
            boundrywall9.setVisibility(View.VISIBLE);
            watersource9.setVisibility(View.GONE);
            overlooking9.setVisibility(View.VISIBLE);
            somefeatures9.setVisibility(View.VISIBLE);
            facing9.setVisibility(View.VISIBLE);
            facingwidth9.setVisibility(View.VISIBLE);
            typeofflooring9.setVisibility(View.GONE);
            powerbackup9.setVisibility(View.GONE);
            byers9.setVisibility(View.VISIBLE);
            description9.setVisibility(View.VISIBLE);
            timesuitable9.setVisibility(View.VISIBLE);
            rule1.setVisibility(View.GONE);
            rule2.setVisibility(View.GONE);

        }

        if(ListingData.getInstance(getApplicationContext()).category().equals("House/Villa") && ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Sell")){
            anemities9.setVisibility(View.VISIBLE);
            moreanemities9.setVisibility(View.VISIBLE);
            boundrywall9.setVisibility(View.GONE);
            watersource9.setVisibility(View.VISIBLE);
            overlooking9.setVisibility(View.VISIBLE);
            somefeatures9.setVisibility(View.VISIBLE);
            facing9.setVisibility(View.VISIBLE);
            facingwidth9.setVisibility(View.VISIBLE);
            typeofflooring9.setVisibility(View.VISIBLE);
            powerbackup9.setVisibility(View.VISIBLE);
            byers9.setVisibility(View.VISIBLE);
            description9.setVisibility(View.VISIBLE);
            timesuitable9.setVisibility(View.VISIBLE);
            rule1.setVisibility(View.GONE);
            rule2.setVisibility(View.GONE);

        }

        if(ListingData.getInstance(getApplicationContext()).category().equals("Others") && ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Sell") && ListingData.getInstance(getApplicationContext()).type().equals("Residential")){
            anemities9.setVisibility(View.VISIBLE);
            moreanemities9.setVisibility(View.VISIBLE);
            boundrywall9.setVisibility(View.GONE);
            watersource9.setVisibility(View.GONE);
            overlooking9.setVisibility(View.GONE);
            somefeatures9.setVisibility(View.VISIBLE);
            facing9.setVisibility(View.VISIBLE);
            facingwidth9.setVisibility(View.VISIBLE);
            typeofflooring9.setVisibility(View.VISIBLE);
            powerbackup9.setVisibility(View.VISIBLE);
            byers9.setVisibility(View.VISIBLE);
            description9.setVisibility(View.VISIBLE);
            timesuitable9.setVisibility(View.VISIBLE);
            rule1.setVisibility(View.GONE);
            rule2.setVisibility(View.GONE);

        }

        //Rent

        if((ListingData.getInstance(getApplicationContext()).category().equals("Apartment/Flat/Builder Floor") && ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Rent")) || (ListingData.getInstance(getApplicationContext()).category().equals("House/Villa") && ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Rent")) || (ListingData.getInstance(getApplicationContext()).category().equals("Others") && ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Rent") && ListingData.getInstance(getApplicationContext()).type().equals("Residential"))){
            anemities9.setVisibility(View.VISIBLE);
            moreanemities9.setVisibility(View.VISIBLE);
            watersource9.setVisibility(View.GONE);
            overlooking9.setVisibility(View.GONE);
            somefeatures9.setVisibility(View.VISIBLE);
            facing9.setVisibility(View.VISIBLE);
            facingwidth9.setVisibility(View.VISIBLE);
            typeofflooring9.setVisibility(View.VISIBLE);
            powerbackup9.setVisibility(View.VISIBLE);
            byers9.setVisibility(View.VISIBLE);
            description9.setVisibility(View.VISIBLE);
            timesuitable9.setVisibility(View.VISIBLE);
            rule1.setVisibility(View.GONE);
            rule2.setVisibility(View.GONE);

        }


        //Paying Guest


        if((ListingData.getInstance(getApplicationContext()).category().equals("Apartment/Flat/Builder Floor") && ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Paying Guest")) || ListingData.getInstance(getApplicationContext()).category().equals("House/Villa") && ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Paying Guest")){
             anemities9.setVisibility(View.VISIBLE);
            moreanemities9.setVisibility(View.VISIBLE);
            watersource9.setVisibility(View.GONE);
            overlooking9.setVisibility(View.GONE);
            somefeatures9.setVisibility(View.VISIBLE);
            facing9.setVisibility(View.VISIBLE);
            facingwidth9.setVisibility(View.VISIBLE);
            typeofflooring9.setVisibility(View.VISIBLE);
            powerbackup9.setVisibility(View.VISIBLE);
            byers9.setVisibility(View.VISIBLE);
            description9.setVisibility(View.VISIBLE);
            timesuitable9.setVisibility(View.VISIBLE);
            rule1.setVisibility(View.VISIBLE);
            rule2.setVisibility(View.VISIBLE);
        }


         // sell commersial

        if(ListingData.getInstance(getApplicationContext()).category().equals("Offices")){
            anemities9.setVisibility(View.VISIBLE);
            moreanemities9.setVisibility(View.VISIBLE);
            watersource9.setVisibility(View.GONE);
            overlooking9.setVisibility(View.GONE);
            somefeatures9.setVisibility(View.VISIBLE);
            gatedsociety.setVisibility(View.GONE);
            cornerproperty.setVisibility(View.GONE);
            petfriendly.setVisibility(View.GONE);
            facing9.setVisibility(View.GONE);
            facingwidth9.setVisibility(View.GONE);
            typeofflooring9.setVisibility(View.GONE);
            description9.setVisibility(View.VISIBLE);
            timesuitable9.setVisibility(View.VISIBLE);
        }

        if(ListingData.getInstance(getApplicationContext()).category().equals("Retail")){
            anemities9.setVisibility(View.VISIBLE);
            moreanemities9.setVisibility(View.VISIBLE);
            watersource9.setVisibility(View.GONE);
            overlooking9.setVisibility(View.GONE);
            somefeatures9.setVisibility(View.VISIBLE);
            gatedsociety.setVisibility(View.GONE);
            cornerproperty.setVisibility(View.GONE);
            petfriendly.setVisibility(View.GONE);
            facing9.setVisibility(View.VISIBLE);
            facingwidth9.setVisibility(View.GONE);
            typeofflooring9.setVisibility(View.VISIBLE);
            description9.setVisibility(View.VISIBLE);
            timesuitable9.setVisibility(View.VISIBLE);
        }

        if(ListingData.getInstance(getApplicationContext()).category().equals("Land")){
            anemities9.setVisibility(View.VISIBLE);
            moreanemities9.setVisibility(View.VISIBLE);
            watersource9.setVisibility(View.GONE);
            overlooking9.setVisibility(View.GONE);
            somefeatures9.setVisibility(View.GONE);
            gatedsociety.setVisibility(View.GONE);
            cornerproperty.setVisibility(View.GONE);
            petfriendly.setVisibility(View.GONE);
            facing9.setVisibility(View.VISIBLE);
            facingwidth9.setVisibility(View.GONE);
            typeofflooring9.setVisibility(View.GONE);
            description9.setVisibility(View.VISIBLE);
            timesuitable9.setVisibility(View.VISIBLE);
        }

        if(ListingData.getInstance(getApplicationContext()).category().equals("Industry") || ListingData.getInstance(getApplicationContext()).category().equals("Storage") || ListingData.getInstance(getApplicationContext()).category().equals("Hospitality")){
            anemities9.setVisibility(View.VISIBLE);
            moreanemities9.setVisibility(View.VISIBLE);
            watersource9.setVisibility(View.GONE);
            overlooking9.setVisibility(View.GONE);
            somefeatures9.setVisibility(View.VISIBLE);
            gatedsociety.setVisibility(View.GONE);
            cornerproperty.setVisibility(View.GONE);
            petfriendly.setVisibility(View.GONE);
            facing9.setVisibility(View.VISIBLE);
            facingwidth9.setVisibility(View.GONE);
            typeofflooring9.setVisibility(View.VISIBLE);
            description9.setVisibility(View.VISIBLE);
            timesuitable9.setVisibility(View.VISIBLE);
        }


        if(ListingData.getInstance(getApplicationContext()).category().equals("Others") && ListingData.getInstance(getApplicationContext()).type().equals("Commercial")){
            anemities9.setVisibility(View.VISIBLE);
            moreanemities9.setVisibility(View.VISIBLE);
            boundrywall9.setVisibility(View.GONE);
            watersource9.setVisibility(View.GONE);
            overlooking9.setVisibility(View.GONE);
            somefeatures9.setVisibility(View.GONE);
            facing9.setVisibility(View.VISIBLE);
            facingwidth9.setVisibility(View.GONE);
            typeofflooring9.setVisibility(View.GONE);
            powerbackup9.setVisibility(View.GONE);
            byers9.setVisibility(View.GONE);
            description9.setVisibility(View.VISIBLE);
            timesuitable9.setVisibility(View.VISIBLE);
            rule1.setVisibility(View.GONE);
            rule2.setVisibility(View.GONE);

        }


















        pety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pet = "Yes";
            }
        });
        petn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pet = "No";
            }
        });

        visitery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                visiter = "Yes";
            }
        });

        visitern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                visiter = "No";
            }
        });


        smokingy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                smoking = "Yes";
            }
        });

        smokingn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                smoking = "No";
            }
        });

        alcoholy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alcohol = "Yes";
            }
        });

        alcoholn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alcohol = "No";
            }
        });


        eventy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                event = "Yes";
            }
        });

        eventn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                event = "No";
            }
        });

        boundryno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boundrywall = "No";
            }
        });
        boundryyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boundrywall = "Yes";
            }
        });




        moreanemities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(a==0){
                    moreanemitiesitem91.setVisibility(View.VISIBLE);
                    uparrow.setVisibility(View.VISIBLE);
                    downarrow.setVisibility(View.GONE);
                    a = 1;
                }else {
                    moreanemitiesitem91.setVisibility(View.GONE);
                    uparrow.setVisibility(View.GONE);
                    downarrow.setVisibility(View.VISIBLE);
                    a = 0;

                }
            }
        });



        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(Features.this, R.array.facing, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        facingspin.setAdapter(adapter1);

        facingspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                facingspinget = facingspin.getSelectedItem().toString();

            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(Features.this, R.array.widthunits, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitspin.setAdapter(adapter2);

        unitspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                unitspinget = unitspin.getSelectedItem().toString();

            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(Features.this, R.array.flooring, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeofflooringspin.setAdapter(adapter3);

        typeofflooringspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                typeofflooringspinget = typeofflooringspin.getSelectedItem().toString();

            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(Features.this, R.array.powerbackup, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        powerbackupspin.setAdapter(adapter4);

        powerbackupspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                powerbackupspinget = powerbackupspin.getSelectedItem().toString();

            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(Features.this, R.array.lasttime, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lasttimespin.setAdapter(adapter5);

        lasttimespin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lasttimespinget = lasttimespin.getSelectedItem().toString();

            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


//        TextView somefeaturestext, overlookingtext, moreanemities;

//        ArrayList<String> anemitiesitem = new ArrayList<>();
//        ArrayList<String> moreanemitiesitem = new ArrayList<>();
//        ArrayList<String> watersourceitem = new ArrayList<>();
//        ArrayList<String> overlookingitem = new ArrayList<>();
//        ArrayList<String> somefeatureitem = new ArrayList<>();
//        ArrayList<String> byersitem = new ArrayList<>();
//        ArrayList<String> timeitems = new ArrayList<>();
//
//        String pet, visiter, smoking, alcohol,event;
//        int a = 0;


        //edittext
        widthfacing.setText(ListingData.getInstance(getApplicationContext()).widthfacingget());
        description.setText(ListingData.getInstance(getApplicationContext()).descriptionget());

        //checkbox;
        anemitiesitemget = ListingData.getInstance(getApplicationContext()).anemitiesitem();
        moreanemitiesitemget = ListingData.getInstance(getApplicationContext()).moreanemitiesitem();
        watersourceitemget = ListingData.getInstance(getApplicationContext()).watersourceitem();
        overlookingitemget = ListingData.getInstance(getApplicationContext()).overlookingitem();
        somefeatureitemget = ListingData.getInstance(getApplicationContext()).somefeatureitem();
        byersitemget = ListingData.getInstance(getApplicationContext()).byersitem();
        timeitemsget = ListingData.getInstance(getApplicationContext()).timeitems();





        //radio

        if(ListingData.getInstance(getApplicationContext()).pet() != null){
            if(ListingData.getInstance(getApplicationContext()).pet().equals("Yes")){
                pety.setChecked(true);
                pet = "Yes";
            }else{
                petn.setChecked(true);
                pet = "No";
            }
        }
        if(ListingData.getInstance(getApplicationContext()).visiter() != null){
            if(ListingData.getInstance(getApplicationContext()).visiter().equals("Yes")){
                visitery.setChecked(true);
                visiter = "Yes";
            }else{
                visitern.setChecked(true);
                visiter = "No";
            }
        }


        if(ListingData.getInstance(getApplicationContext()).smoking() != null){
            if(ListingData.getInstance(getApplicationContext()).smoking().equals("Yes")){
                smokingy.setChecked(true);
            }else{
                smokingn.setChecked(true);
            }
        }
        if(ListingData.getInstance(getApplicationContext()).alcohol() != null){
            if(ListingData.getInstance(getApplicationContext()).alcohol().equals("Yes")){
                alcoholy.setChecked(true);
                alcohol = "Yes";
            }else{
                alcoholn.setChecked(true);
                alcohol = "No";
            }
        }

        if(ListingData.getInstance(getApplicationContext()).event() != null){
            if(ListingData.getInstance(getApplicationContext()).event().equals("Yes")){
                eventy.setChecked(true);
                event = "Yes";
            }else{
                eventn.setChecked(true);
                event = "No";
            }
        }

        if(ListingData.getInstance(getApplicationContext()).boundrywall() != null){
            if(ListingData.getInstance(getApplicationContext()).boundrywall().equals("Yes")){
                boundryyes.setChecked(true);
                boundrywall = "Yes";
            }else{
                boundryno.setChecked(true);
                boundrywall = "No";
            }
        }

        int position1 = adapter1.getPosition(ListingData.getInstance(getApplicationContext()).facingspinget());
        facingspin.setSelection(position1);

        int position2 = adapter2.getPosition(ListingData.getInstance(getApplicationContext()).unitspinget());
        unitspin.setSelection(position2);

        int position3 = adapter3.getPosition(ListingData.getInstance(getApplicationContext()).typeofflooringspinget());
        typeofflooringspin.setSelection(position3);

        int position4 = adapter4.getPosition(ListingData.getInstance(getApplicationContext()).powerbackupspinget());
        powerbackupspin.setSelection(position4);

        int position5 = adapter5.getPosition(ListingData.getInstance(getApplicationContext()).lasttimespinget());
        lasttimespin.setSelection(position5);


        // String anemitiesitemget,moreanemitiesitemget, watersourceitemget, overlookingitemget, somefeatureitemget, byersitemget, timeitemsget;
        if(ListingData.getInstance(getApplicationContext()).getimage() != null) {


            System.out.println("imageeeee ::;" +ListingData.getInstance(getApplicationContext()).getimage());
            String propertyimg= ListingData.getInstance(getApplicationContext()).getimage();

            if(ListingData.getInstance(getApplicationContext()).getinstance().equals("Add")){
                byte[] decodedString = Base64.decode(propertyimg, Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                Glide.with(Features.this).load(decodedByte).into(img);
            }else {
                if (propertyimg.length() > 95){
                    Glide.with(Features.this).load(propertyimg).into(img);
                }else{
                    Glide.with(Features.this).load(R.drawable.nopropertyimage).into(img);
                }
            }
            encodedImage = propertyimg;
        }
        if(ListingData.getInstance(getApplicationContext()).anemitiesitem() != null) {


            if (anemitiesitemget.contains("Lift")) {
                anemitiesitem.add("Lift");
                lift.setChecked(true);

            } else {
                anemitiesitem.remove("Lift");
                lift.setChecked(false);
            }

            if (anemitiesitemget.contains("Park")) {
                anemitiesitem.add("Park");
                park.setChecked(true);

            } else {
                anemitiesitem.remove("Park");
                park.setChecked(false);

            }

            if (anemitiesitemget.contains("Maintenance Staff")) {
                anemitiesitem.add("Maintenance Staff");
                maintenancestaff.setChecked(true);

            } else {
                anemitiesitem.remove("Maintenance Staff");
                maintenancestaff.setChecked(false);

            }

            if (anemitiesitemget.contains("Visiter Parking")) {
                anemitiesitem.add("Visiter Parking");
                visiterparking.setChecked(true);

            } else {
                anemitiesitem.remove("Visiter Parking");
                visiterparking.setChecked(false);

            }

            if (anemitiesitemget.contains("FengShui/Vaastu Compliant")) {
                anemitiesitem.add("FengShui/Vaastu Compliant");
                fengshui.setChecked(true);

            } else {
                anemitiesitem.remove("FengShui/Vaastu Compliant");
                fengshui.setChecked(false);

            }

            if (anemitiesitemget.contains("Intercome Facility")) {
                anemitiesitem.add("Intercome Facility");
                intercomefacility.setChecked(true);

            } else {
                anemitiesitem.remove("Intercome Facility");
                intercomefacility.setChecked(false);

            }

            if (anemitiesitemget.contains("Water Storage")) {
                anemitiesitem.add("Water Storage");
                waterstorage.setChecked(true);

            } else {
                anemitiesitem.remove("Water Storage");
                waterstorage.setChecked(false);

            }

            if (anemitiesitemget.contains("Security/Fire Alaram")) {
                anemitiesitem.add("Security/Fire Alaram");
                alaram.setChecked(true);

            } else {
                anemitiesitem.remove("Security/Fire Alaram");
                alaram.setChecked(false);

            }


        }

        if(ListingData.getInstance(getApplicationContext()).moreanemitiesitem() != null) {


            // moreanemitiies

            if (moreanemitiesitemget.contains("Swimming Pool")) {
                moreanemitiesitem.add("Swimming Pool");
                swimmingpool.setChecked(true);

            } else {
                moreanemitiesitem.remove("Swimming Pool");
                swimmingpool.setChecked(false);

            }

            if (moreanemitiesitemget.contains("Centrally Air Conditioned")) {
                moreanemitiesitem.add("Centrally Air Conditioned");
                ac.setChecked(true);

            } else {
                moreanemitiesitem.remove("Centrally Air Conditioned");
                ac.setChecked(false);

            }

            if (moreanemitiesitemget.contains("Garden")) {
                moreanemitiesitem.add("Garden");
                garden.setChecked(true);

            } else {
                moreanemitiesitem.remove("Garden");
                garden.setChecked(false);

            }

            if (moreanemitiesitemget.contains("Wifi")) {
                moreanemitiesitem.add("Wifi");
                wifi.setChecked(true);

            } else {
                moreanemitiesitem.remove("Wifi");
                wifi.setChecked(false);

            }

            if (moreanemitiesitemget.contains("Piped-Gas")) {
                moreanemitiesitem.add("Piped-Gas");
                pipedgas.setChecked(true);

            } else {
                moreanemitiesitem.remove("Piped-Gas");
                pipedgas.setChecked(false);

            }

            if (moreanemitiesitemget.contains("Water Purifier")) {
                moreanemitiesitem.add("Water Purifier");
                waterpurifier.setChecked(true);

            } else {
                moreanemitiesitem.remove("Water Purifier");
                waterpurifier.setChecked(false);

            }

            if (moreanemitiesitemget.contains("Club House")) {
                moreanemitiesitem.add("Club House");
                clubhouse.setChecked(true);

            } else {
                moreanemitiesitem.remove("Club House");
                clubhouse.setChecked(false);

            }

            if (moreanemitiesitemget.contains("Shopping Center")) {
                moreanemitiesitem.add("Shopping Center");
                shoppingcenter.setChecked(true);

            } else {
                moreanemitiesitem.remove("Shopping Center");
                shoppingcenter.setChecked(false);

            }

            if (moreanemitiesitemget.contains("Water Disposal")) {
                moreanemitiesitem.add("Water Disposal");
                waterdissposal.setChecked(true);

            } else {
                moreanemitiesitem.remove("Water Disposal");
                waterdissposal.setChecked(false);

            }

            if (moreanemitiesitemget.contains("RainWater Harvesting")) {
                moreanemitiesitem.add("RainWater Harvesting");
                rainwaterharvesting.setChecked(true);

            } else {
                moreanemitiesitem.remove("RainWater Harvesting");
                rainwaterharvesting.setChecked(false);

            }

            if (moreanemitiesitemget.contains("Bank Attached Property")) {
                moreanemitiesitem.add("Bank Attached Property");
                bankedattachedproperty.setChecked(true);

            } else {
                moreanemitiesitem.remove("Bank Attached Property");
                bankedattachedproperty.setChecked(false);

            }

            if (moreanemitiesitemget.contains("Gym")) {
                moreanemitiesitem.add("Gym");
                gym.setChecked(true);

            } else {
                moreanemitiesitem.remove("Gym");
                gym.setChecked(false);

            }

        }

            //watersource

        if(ListingData.getInstance(getApplicationContext()).watersourceitem() != null) {

            if (watersourceitemget.contains("Municipal Corporation")) {
                watersourceitem.add("Municipal Corporation");
                municiplecorporation.setChecked(true);

            } else {
                watersourceitem.remove("Municipal Corporation");
                municiplecorporation.setChecked(false);

            }

            if (watersourceitemget.contains("Borewell")) {
                watersourceitem.add("Borewell");
                borewell.setChecked(true);

            } else {
                watersourceitem.remove("Borewell");
                borewell.setChecked(false);

            }
        }
            //overlooking

        if(ListingData.getInstance(getApplicationContext()).overlookingitem() != null) {

            if (overlookingitemget.contains("Park/Garden")) {
                overlookingitem.add("Park/Garden");
                parkgarden.setChecked(true);

            } else {
                overlookingitem.remove("Park/Garden");
                parkgarden.setChecked(false);

            }

            if (overlookingitemget.contains("Main Road")) {
                overlookingitem.add("Main Road");
                mainroad.setChecked(true);

            } else {
                overlookingitem.remove("Main Road");
                mainroad.setChecked(false);

            }

            if (overlookingitemget.contains("Club")) {
                overlookingitem.add("Club");
                club.setChecked(true);

            } else {
                overlookingitem.remove("Club");
                club.setChecked(false);

            }

            if (overlookingitemget.contains("Lake Facing")) {
                overlookingitem.add("Lake Facing");
                lakefacing.setChecked(true);

            } else {
                overlookingitem.remove("Lake Facing");
                lakefacing.setChecked(false);

            }

            if (overlookingitemget.contains("Sea Facing")) {
                overlookingitem.add("Sea Facing");
                seafacing.setChecked(true);

            } else {
                overlookingitem.remove("Sea Facing");
                seafacing.setChecked(false);

            }

            if (overlookingitemget.contains("Others")) {
                overlookingitem.add("Others");
                others.setChecked(true);

            } else {
                overlookingitem.remove("Others");
                others.setChecked(false);

            }
        }

            //somefetures


        if(ListingData.getInstance(getApplicationContext()).somefeatureitem() != null) {

            if (somefeatureitemget.contains("In a Gated Society")) {
                somefeatureitem.add("In a Gated Society");
                gatedsociety.setChecked(true);

            } else {
                somefeatureitem.remove("In a Gated Society");
                gatedsociety.setChecked(false);

            }

            if (somefeatureitemget.contains("Corner Property")) {
                somefeatureitem.add("Corner Property");
                cornerproperty.setChecked(true);

            } else {
                somefeatureitem.remove("Corner Property");
                cornerproperty.setChecked(false);

            }

            if (somefeatureitemget.contains("Pet Friendly")) {
                somefeatureitem.add("Pet Friendly");
                petfriendly.setChecked(true);

            } else {
                somefeatureitem.remove("Pet Friendly");
                petfriendly.setChecked(false);

            }

            if (somefeatureitemget.contains("Wheelchair Friendly")) {
                somefeatureitem.add("Wheelchair Friendly");
                wheelchairfriendly.setChecked(true);

            } else {
                somefeatureitem.remove("Wheelchair Friendly");
                wheelchairfriendly.setChecked(false);

            }


        }

            // byres
        if(ListingData.getInstance(getApplicationContext()).byersitem() != null) {


            if (byersitemget.contains("24 x 7 Water")) {
                byersitem.add("24 x 7 Water");
                water24.setChecked(true);

            } else {
                byersitem.remove("24 x 7 Water");
                water24.setChecked(false);

            }

            if (byersitemget.contains("Visiter Parking Available")) {
                byersitem.add("Visiter Parking Available");
                visiterparkingavailable.setChecked(true);

            } else {
                byersitem.remove("Visiter Parking Available");
                visiterparkingavailable.setChecked(false);

            }

            if (byersitemget.contains("Close To School")) {
                byersitem.add("Close To School");
                closetoschool.setChecked(true);

            } else {
                byersitem.remove("Close To School");
                closetoschool.setChecked(false);

            }

            if (byersitemget.contains("Close To Bank")) {
                byersitem.add("Close To Bank");
                closetobank.setChecked(true);

            } else {
                byersitem.remove("Close To Bank");
                closetobank.setChecked(false);

            }

            if (byersitemget.contains("Natural Light")) {
                byersitem.add("Natural Light");
                naturallight.setChecked(true);

            } else {
                byersitem.remove("Natural Light");
                naturallight.setChecked(false);

            }

            if (byersitemget.contains("Close To Hospital")) {
                byersitem.add("Close To Hospital");
                closetohospital.setChecked(true);

            } else {
                byersitem.remove("Close To Hospital");
                closetohospital.setChecked(false);

            }

            if (byersitemget.contains("Close To Market")) {
                byersitem.add("Close To Market");
                closetomarket.setChecked(true);

            } else {
                byersitem.remove("Close To Market");
                closetomarket.setChecked(false);

            }
            if (byersitemget.contains("Airy Rooms")) {
                byersitem.add("Airy Rooms");
                airyrooms.setChecked(true);

            } else {
                byersitem.remove("Airy Rooms");
                airyrooms.setChecked(false);

            }
        }

            //. time
        if(ListingData.getInstance(getApplicationContext()).timeitems() != null) {

            if (timeitemsget.contains("8am - 12pm")) {
                timeitems.add("8am - 12pm");
                eight12.setChecked(true);

            } else {
                timeitems.remove("8am - 12pm");
                eight12.setChecked(false);

            }


            if (timeitemsget.contains("12pm - 3pm")) {
                timeitems.add("12pm - 3pm");
                twele3.setChecked(true);

            } else {
                timeitems.remove("12pm - 3pm");
                twele3.setChecked(false);

            }

            if (timeitemsget.contains("3pm - 6pm")) {
                timeitems.add("3pm - 6pm");
                three6.setChecked(true);

            } else {
                timeitems.remove("3pm - 6pm");
                three6.setChecked(false);

            }

            if (timeitemsget.contains("6pm - 9pm")) {
                timeitems.add("6pm - 9pm");
                six9.setChecked(true);

            } else {
                timeitems.remove("6pm - 9pm");
                six9.setChecked(false);

            }


            if (timeitemsget.contains("9pm - 11pm")) {
                timeitems.add("9pm - 11pm");
                nine11.setChecked(true);

            } else {
                timeitems.remove("9pm - 11pm");
                nine11.setChecked(false);

            }

            if (timeitemsget.contains("None")) {
                timeitems.add("None");
                none.setChecked(true);

            } else {
                timeitems.remove("None");
                none.setChecked(false);

            }


        }














        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                widthfacingget = widthfacing.getText().toString().trim();
                descriptionget = description.getText().toString();

                System.out.println("local ::  Start");

                if(ListingData.getInstance(getApplicationContext()).category().equals("Apartment/Flat/Builder Floor") && ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Sell")){




                    if(descriptionget.isEmpty()){
                        showmessage("Please Enter Description");
                        description.setError("Please Enter Description");
                    }

                    else{
                        ListingData.getInstance(getApplicationContext()).addfeatures(
                                facingspinget, unitspinget, typeofflooringspinget,
                                powerbackupspinget, lasttimespinget,
                                pet, visiter, smoking, alcohol,event,
                                String.valueOf(anemitiesitem), String.valueOf(moreanemitiesitem), String.valueOf(watersourceitem), String.valueOf(overlookingitem),
                                String.valueOf(somefeatureitem), String.valueOf(byersitem),String.valueOf(timeitems), widthfacingget, descriptionget, boundrywall, encodedImage);
                        startActivity(new Intent(Features.this, PropertyProfileInfo.class));
                    }
                }
                if(ListingData.getInstance(getApplicationContext()).category().equals("Residential Land") && ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Sell")){


                    if(descriptionget.isEmpty()){
                        showmessage("Please Enter Description");
                        description.setError("Please Enter Description");
                    }

                    else{
                        ListingData.getInstance(getApplicationContext()).addfeatures(
                                facingspinget, unitspinget, typeofflooringspinget,
                                powerbackupspinget, lasttimespinget,
                                pet, visiter, smoking, alcohol,event,
                                String.valueOf(anemitiesitem), String.valueOf(moreanemitiesitem), String.valueOf(watersourceitem), String.valueOf(overlookingitem),
                                String.valueOf(somefeatureitem), String.valueOf(byersitem),String.valueOf(timeitems), widthfacingget, descriptionget, boundrywall, encodedImage);
                        startActivity(new Intent(Features.this, PropertyProfileInfo.class));

                    }

                }

                if(ListingData.getInstance(getApplicationContext()).category().equals("House/Villa") && ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Sell")){



                    if(descriptionget.isEmpty()){
                        showmessage("Please Enter Description");
                        description.setError("Please Enter Description");
                    }

                    else{
                        ListingData.getInstance(getApplicationContext()).addfeatures(
                                facingspinget, unitspinget, typeofflooringspinget,
                                powerbackupspinget, lasttimespinget,
                                pet, visiter, smoking, alcohol,event,
                                String.valueOf(anemitiesitem), String.valueOf(moreanemitiesitem), String.valueOf(watersourceitem), String.valueOf(overlookingitem),
                                String.valueOf(somefeatureitem), String.valueOf(byersitem),String.valueOf(timeitems),
                                widthfacingget, descriptionget, boundrywall, encodedImage);
                        startActivity(new Intent(Features.this, PropertyProfileInfo.class));

                    }


                }

                if(ListingData.getInstance(getApplicationContext()).category().equals("Others") && ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Sell") && ListingData.getInstance(getApplicationContext()).type().equals("Residential")){


                    if(descriptionget.isEmpty()){
                        showmessage("Please Enter Description");
                        description.setError("Please Enter Description");
                    }

                    else{
                        ListingData.getInstance(getApplicationContext()).addfeatures(
                                facingspinget, unitspinget, typeofflooringspinget,
                                powerbackupspinget, lasttimespinget,
                                pet, visiter, smoking, alcohol,event,
                                String.valueOf(anemitiesitem), String.valueOf(moreanemitiesitem), String.valueOf(watersourceitem), String.valueOf(overlookingitem),
                                String.valueOf(somefeatureitem), String.valueOf(byersitem),String.valueOf(timeitems),
                                widthfacingget, descriptionget, boundrywall, encodedImage);
                        startActivity(new Intent(Features.this, PropertyProfileInfo.class));

                    }

                }

                //Rent

                if((ListingData.getInstance(getApplicationContext()).category().equals("Apartment/Flat/Builder Floor") && ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Rent")) || (ListingData.getInstance(getApplicationContext()).category().equals("House/Villa") && ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Rent")) || (ListingData.getInstance(getApplicationContext()).category().equals("Others") && ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Rent") && ListingData.getInstance(getApplicationContext()).type().equals("Residential"))){


                    if(descriptionget.isEmpty()){
                        showmessage("Please Enter Description");
                        description.setError("Please Enter Description");
                    }

                    else{
                        ListingData.getInstance(getApplicationContext()).addfeatures(
                                facingspinget, unitspinget, typeofflooringspinget,
                                powerbackupspinget, lasttimespinget,
                                pet, visiter, smoking, alcohol,event,
                                String.valueOf(anemitiesitem), String.valueOf(moreanemitiesitem), String.valueOf(watersourceitem), String.valueOf(overlookingitem),
                                String.valueOf(somefeatureitem), String.valueOf(byersitem),String.valueOf(timeitems),
                                widthfacingget, descriptionget, boundrywall, encodedImage);
                        startActivity(new Intent(Features.this, PropertyProfileInfo.class));

                    }

                }


                //Paying Guest


                if((ListingData.getInstance(getApplicationContext()).category().equals("Apartment/Flat/Builder Floor") && ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Paying Guest")) || ListingData.getInstance(getApplicationContext()).category().equals("House/Villa") && ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Paying Guest")){


                    if(descriptionget.isEmpty()){
                        showmessage("Please Enter Description");
                        description.setError("Please Enter Description");
                    }


                    else{
                        ListingData.getInstance(getApplicationContext()).addfeatures(
                                facingspinget, unitspinget, typeofflooringspinget,
                                powerbackupspinget, lasttimespinget,
                                pet, visiter, smoking, alcohol,event,
                                String.valueOf(anemitiesitem), String.valueOf(moreanemitiesitem), String.valueOf(watersourceitem), String.valueOf(overlookingitem),
                                String.valueOf(somefeatureitem), String.valueOf(byersitem),String.valueOf(timeitems),
                                widthfacingget, descriptionget, boundrywall, encodedImage);
                        startActivity(new Intent(Features.this, PropertyProfileInfo.class));

                    }
                }


                // sell commersial

                if(ListingData.getInstance(getApplicationContext()).category().equals("Offices")){



                    if(descriptionget.isEmpty()){
                        showmessage("Please Enter Description");
                        description.setError("Please Enter Description");
                    }

                    else{
                        ListingData.getInstance(getApplicationContext()).addfeatures(
                                facingspinget, unitspinget, typeofflooringspinget,
                                powerbackupspinget, lasttimespinget,
                                pet, visiter, smoking, alcohol,event,
                                String.valueOf(anemitiesitem), String.valueOf(moreanemitiesitem), String.valueOf(watersourceitem), String.valueOf(overlookingitem),
                                String.valueOf(somefeatureitem), String.valueOf(byersitem),String.valueOf(timeitems),
                                widthfacingget, descriptionget, boundrywall, encodedImage);
                        startActivity(new Intent(Features.this, PropertyProfileInfo.class));

                    }
                }

                if(ListingData.getInstance(getApplicationContext()).category().equals("Retail")){




                    if(descriptionget.isEmpty()){
                        showmessage("Please Enter Description");
                        description.setError("Please Enter Description");
                    }

                    else{
                        ListingData.getInstance(getApplicationContext()).addfeatures(
                                facingspinget, unitspinget, typeofflooringspinget,
                                powerbackupspinget, lasttimespinget,
                                pet, visiter, smoking, alcohol,event,
                                String.valueOf(anemitiesitem), String.valueOf(moreanemitiesitem), String.valueOf(watersourceitem), String.valueOf(overlookingitem),
                                String.valueOf(somefeatureitem), String.valueOf(byersitem),String.valueOf(timeitems),
                                widthfacingget, descriptionget, boundrywall, encodedImage);
                        startActivity(new Intent(Features.this, PropertyProfileInfo.class));

                    }
                }

                if(ListingData.getInstance(getApplicationContext()).category().equals("Land")){


                    if(descriptionget.isEmpty()){
                        showmessage("Please Enter Description");
                        description.setError("Please Enter Description");
                    }

                    else{
                        ListingData.getInstance(getApplicationContext()).addfeatures(
                                facingspinget, unitspinget, typeofflooringspinget,
                                powerbackupspinget, lasttimespinget,
                                pet, visiter, smoking, alcohol,event,
                                String.valueOf(anemitiesitem), String.valueOf(moreanemitiesitem), String.valueOf(watersourceitem), String.valueOf(overlookingitem),
                                String.valueOf(somefeatureitem), String.valueOf(byersitem),String.valueOf(timeitems),
                                widthfacingget, descriptionget, boundrywall, encodedImage);
                        startActivity(new Intent(Features.this, PropertyProfileInfo.class));

                    }
                }

                if(ListingData.getInstance(getApplicationContext()).category().equals("Industry") || ListingData.getInstance(getApplicationContext()).category().equals("Storage") || ListingData.getInstance(getApplicationContext()).category().equals("Hospitality")){

                    if(descriptionget.isEmpty()){
                        showmessage("Please Enter Description");
                        description.setError("Please Enter Description");
                    }

                    else{
                        ListingData.getInstance(getApplicationContext()).addfeatures(
                                facingspinget, unitspinget, typeofflooringspinget,
                                powerbackupspinget, lasttimespinget,
                                pet, visiter, smoking, alcohol,event,
                                String.valueOf(anemitiesitem), String.valueOf(moreanemitiesitem), String.valueOf(watersourceitem), String.valueOf(overlookingitem),
                                String.valueOf(somefeatureitem), String.valueOf(byersitem),String.valueOf(timeitems),
                                widthfacingget, descriptionget, boundrywall, encodedImage);
                        startActivity(new Intent(Features.this, PropertyProfileInfo.class));

                    }
                }


                if(ListingData.getInstance(getApplicationContext()).category().equals("Others") && ListingData.getInstance(getApplicationContext()).type().equals("Commercial")){




                    if(descriptionget.isEmpty()){
                        showmessage("Please Enter Description");
                        description.setError("Please Enter Description");
                    }

                    else{
                        ListingData.getInstance(getApplicationContext()).addfeatures(
                                facingspinget, unitspinget, typeofflooringspinget,
                                powerbackupspinget, lasttimespinget,
                                pet, visiter, smoking, alcohol,event,
                                String.valueOf(anemitiesitem), String.valueOf(moreanemitiesitem), String.valueOf(watersourceitem), String.valueOf(overlookingitem),
                                String.valueOf(somefeatureitem), String.valueOf(byersitem),String.valueOf(timeitems),
                                widthfacingget, descriptionget, boundrywall, encodedImage);
                        startActivity(new Intent(Features.this, PropertyProfileInfo.class));

                    }

                }

                System.out.println("local ::  Start");



            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        if(encodedImage.length() > 250){
            imagepresent = "0";
            ListingData.getInstance(getApplicationContext()).imagepresent("0");
        }else {
            imagepresent = "1";
            ListingData.getInstance(getApplicationContext()).imagepresent("1");

        }




        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                imagepresent = "0";
//                ListingData.getInstance(getApplicationContext()).imagepresent("0");

                System.out.println("encodededdd :: " + encodedImage.length());

                Dexter.withActivity(Features.this).withPermission(READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
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


        pickimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                imagepresent = "0";
//                ListingData.getInstance(getApplicationContext()).imagepresent("0");

                System.out.println("encodededdd :: " + encodedImage.length());

                Dexter.withActivity(Features.this).withPermission(READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
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


//
//        if(encodedImage.length() > 150){
//            imagepresent = "0";
//            ListingData.getInstance(getApplicationContext()).imagepresent("0");
//        }else {
//            imagepresent = "1";
//            ListingData.getInstance(getApplicationContext()).imagepresent("1");
//
//        }



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 1 && resultCode == RESULT_OK && data != null){
            Uri filepath = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(filepath);
                bitmap = BitmapFactory.decodeStream(inputStream);
//                post_img.setImageBitmap(bitmap);
                Glide.with(Features.this).load(bitmap).into(img);
                imageStore(bitmap);
//                File f = new File(String.valueOf(filepath));
//                imageName = f.getName();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }



    private void imageStore(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,45,stream);
        byte[] imageBytes = stream.toByteArray();
        encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        System.out.println("Encodedimage ::"+ encodedImage.length());
        if(encodedImage.length() > 250){
            imagepresent = "0";
            ListingData.getInstance(getApplicationContext()).imagepresent("0");
        }else {
            imagepresent = "1";
            ListingData.getInstance(getApplicationContext()).imagepresent("1");

        }
    }

    private void showmessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    public void selectItem(View view) {

        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()) {

            case R.id.lift:
                if (checked) {
                    anemitiesitem.add("Lift");
                } else {
                    anemitiesitem.remove("Lift");
                }
                break;

            case R.id.park:
                if (checked) {
                    anemitiesitem.add("Park");
                } else {
                    anemitiesitem.remove("Park");
                }
                break;

            case R.id.maintenancestaff:
                if (checked) {
                    anemitiesitem.add("Maintenance Staff");
                } else {
                    anemitiesitem.remove("Maintenance Staff");
                }
                break;

            case R.id.visiterparking:
                if (checked) {
                    anemitiesitem.add("Visiter Parking");
                } else {
                    anemitiesitem.remove("Visiter Parking");
                }
                break;
            case R.id.fengshui:
                if (checked) {
                    anemitiesitem.add("FengShui/Vaastu Compliant");
                } else {
                    anemitiesitem.remove("FengShui/Vaastu Compliant");
                }
                break;
            case R.id.intercomefacility:
                if (checked) {
                    anemitiesitem.add("Intercome Facility");
                } else {
                    anemitiesitem.remove("Intercome Facility");
                }
                break;
            case R.id.waterstorage:
                if (checked) {
                    anemitiesitem.add("Water Storage");
                } else {
                    anemitiesitem.remove("Water Storage");
                }
                break;
            case R.id.alarm:
                if (checked) {
                    anemitiesitem.add("Security/Fire Alaram");
                } else {
                    anemitiesitem.remove("Security/Fire Alaram");
                }
                break;



                // moreanemitiies

            case R.id.swimmingpool:
                if (checked) {
                    moreanemitiesitem.add("Swimming Pool");
                } else {
                    moreanemitiesitem.remove("Swimming Pool");
                }
                break;

            case R.id.ac:
                if (checked) {
                    moreanemitiesitem.add("Centrally Air Conditioned");
                } else {
                    moreanemitiesitem.remove("Centrally Air Conditioned");
                }
                break;

            case R.id.garden:
                if (checked) {
                    moreanemitiesitem.add("Garden");
                } else {
                    moreanemitiesitem.remove("Garden");
                }
                break;
            case R.id.wifi:
                if (checked) {
                    moreanemitiesitem.add("Wifi");
                } else {
                    moreanemitiesitem.remove("Wifi");
                }
                break;
            case R.id.pipedgas:
                if (checked) {
                    moreanemitiesitem.add("Piped-Gas");
                } else {
                    moreanemitiesitem.remove("Piped-Gas");
                }
                break;
            case R.id.waterpurifier:
                if (checked) {
                    moreanemitiesitem.add("Water Purifier");
                } else {
                    moreanemitiesitem.remove("Water Purifier");
                }
                break;

                case R.id.clubhouse:
                if (checked) {
                    moreanemitiesitem.add("Club House");
                } else {
                    moreanemitiesitem.remove("Club House");
                }
                break;
            case R.id.shoppingcenter:
                if (checked) {
                    moreanemitiesitem.add("Shopping Center");
                } else {
                    moreanemitiesitem.remove("Shopping Center");
                }
                break;
            case R.id.waterdisposal:
                if (checked) {
                    moreanemitiesitem.add("Water Disposal");
                } else {
                    moreanemitiesitem.remove("Water Disposal");
                }
                break;
            case R.id.rainwaterharvesting:
                if (checked) {
                    moreanemitiesitem.add("RainWater Harvesting");
                } else {
                    moreanemitiesitem.remove("RainWater Harvesting");
                }
                break;
            case R.id.bankattachedproperty:
                if (checked) {
                    moreanemitiesitem.add("Bank Attached Property");
                } else {
                    moreanemitiesitem.remove("Bank Attached Property");
                }
                break;
            case R.id.gym:
                if (checked) {
                    moreanemitiesitem.add("Gym");
                } else {
                    moreanemitiesitem.remove("Gym");
                }
                break;


                //watersource


            case R.id.muncipalcorporation:
                if (checked) {
                    watersourceitem.add("Municipal Corporation");
                } else {
                    watersourceitem.remove("Municipal Corporation");
                }
                break;

            case R.id.borewell:
                if (checked) {
                    watersourceitem.add("Borewell");
                } else {
                    watersourceitem.remove("Borewell");
                }
                break;

                //overlooking


            case R.id.parkgarden:
                if (checked) {
                    overlookingitem.add("Park/Garden");
                } else {
                    overlookingitem.remove("Park/Garden");
                }
                break;


            case R.id.mainrood:
                if (checked) {
                    overlookingitem.add("Main Road");
                } else {
                    overlookingitem.remove("Main Road");
                }
                break;

            case R.id.club:
                if (checked) {
                    overlookingitem.add("Club");
                } else {
                    overlookingitem.remove("Club");
                }
                break;

            case R.id.lakefacing:
                if (checked) {
                    overlookingitem.add("Lake Facing");
                } else {
                    overlookingitem.remove("Lake Facing");
                }
                break;

            case R.id.seafacing:
                if (checked) {
                    overlookingitem.add("Sea Facing");
                } else {
                    overlookingitem.remove("Sea Facing");
                }
                break;

            case R.id.others:
                if (checked) {
                    overlookingitem.add("Others");
                } else {
                    overlookingitem.remove("Others");
                }
                break;


                //somefetures


            case R.id.gatedsociety:
                if (checked) {
                    somefeatureitem.add("In a Gated Society");
                } else {
                    somefeatureitem.remove("In a Gated Society");
                }
                break;

            case R.id.cornerproperty:
                if (checked) {
                    somefeatureitem.add("Corner Property");
                } else {
                    somefeatureitem.remove("Corner Property");
                }
                break;

            case R.id.petfriendly:
                if (checked) {
                    somefeatureitem.add("Pet Friendly");
                } else {
                    somefeatureitem.remove("Pet Friendly");
                }
                break;
            case R.id.wheelchairfriendly:
                if (checked) {
                    somefeatureitem.add("Wheelchair Friendly");
                } else {
                    somefeatureitem.remove("Wheelchair Friendly");
                }
                break;



                // byres

            case R.id.water24:
                if (checked) {
                    byersitem.add("24 x 7 Water");
                } else {
                    byersitem.remove("24 x 7 Water");
                }
                break;

            case R.id.visiterparkingavailable:
                if (checked) {
                    byersitem.add("Visiter Parking Available");
                } else {
                    byersitem.remove("Visiter Parking Available");
                }
                break;

            case R.id.closetoschool:
                if (checked) {
                    byersitem.add("Close To School");
                } else {
                    byersitem.remove("Close To School");
                }
                break;
            case R.id.closetobank:
                if (checked) {
                    byersitem.add("Close To Bank");
                } else {
                    byersitem.remove("Close To Bank");
                }
                break;
            case R.id.naturallight:
                if (checked) {
                    byersitem.add("Natural Light");
                } else {
                    byersitem.remove("Natural Light");
                }
                break;
            case R.id.closetohospital:
                if (checked) {
                    byersitem.add("Close To Hospital");
                } else {
                    byersitem.remove("Close To Hospital");
                }
                break;
            case R.id.closetomarket:
                if (checked) {
                    byersitem.add("Close To Market");
                } else {
                    byersitem.remove("Close To Market");
                }
                break;
            case R.id.airyrooms:
                if (checked) {
                    byersitem.add("Airy Rooms");
                } else {
                    byersitem.remove("Airy Rooms");
                }
                break;


                //. time

            case R.id.eight12:
                if (checked) {
                    timeitems.add("8am - 12pm");
                    timeitems.remove("None");
                    none.setChecked(false);

                } else {
                    timeitems.remove("8am - 12pm");
                }
                break;


            case R.id.twele3:
                if (checked) {
                    timeitems.add("12pm - 3pm");
                    timeitems.remove("None");
                    none.setChecked(false);
                } else {
                    timeitems.remove("12pm - 3pm");
                }
                break;

            case R.id.three6:
                if (checked) {
                    timeitems.add("3pm - 6pm");
                    timeitems.remove("None");
                    none.setChecked(false);
                } else {
                    timeitems.remove("3pm - 6pm");
                }
                break;

            case R.id.six9:
                if (checked) {
                    timeitems.add("6pm - 9pm");
                    timeitems.remove("None");
                    none.setChecked(false);
                } else {
                    timeitems.remove("6pm - 9pm");
                }
                break;

            case R.id.nine11:
                if (checked) {
                    timeitems.add("9pm - 11pm");
                    timeitems.remove("None");
                    none.setChecked(false);
                } else {
                    timeitems.remove("9pm - 11pm");
                }
                break;

            case R.id.none:
                if (checked) {
                    timeitems.clear();
                    timeitems.add("None");
                    eight12.setChecked(false);
                    twele3.setChecked(false);
                    three6.setChecked(false);
                    six9.setChecked(false);
                    nine11.setChecked(false);
                } else {


                    timeitems.remove("None");
                }
                break;


















        }
    }
}
