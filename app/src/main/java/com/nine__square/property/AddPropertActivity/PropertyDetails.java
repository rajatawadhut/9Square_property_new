package com.nine__square.property.AddPropertActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nine__square.property.Adapter.FlatTypeAdapter;
import com.nine__square.property.Models.FlatTypeModel;
import com.nine__square.property.R;
import com.nine__square.property.Utils.ListingData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PropertyDetails extends AppCompatActivity {

    LinearLayout llDataBuilder, built9, carpet9, bedroom9, bathroom9, balconies9, addrooms9, furnisheditem9, totalfloor9, reserver9,
            availability9, ageofproperty9, furnishedspin9,availabilityfromdate9, possessionby9, noofroom9, qualityrating9, floorallowed9, roomavailabe9, flattype9;
    EditText builtuparea, plotarea, carpetarea, edtFlatTypeNo, edtStudioAprt, edtPentHouse,edtRowHouse, edtDuplex,edtShop, edtCommercialSpace;
    RadioButton rbNationalized;
    Spinner unit1, unit2, unit3, bedroomspin, bathroomspin, balconiesspin, totalfloorspin, availabilityspin, ageofpropertyspin, furnishedspin, possessionbyspin,
            noofroomspin, qualityratingspin, floorallowedspin, roomavailabespin, flattypespin, spiFlatType;
    String unit1get = "", unit2get = "", unit3get = "", spiFlatTypeNew ="", edtStudioAprtNo = "", edtPentHouseNo = "",edtRowHouseNo= "", edtDuplexNo = "",edtShopNo = "", edtCommercialSpaceNo = "", tvBankLoan = "";
    TextView availabilityfromdate;
    String availabilitydate = "", bedget = "", bathget = "", balconieget = "", totalfloorget = "", reservedpark = "Yes", availabilityspinget = "", getrera ="",
            ageofpropertyspinget = "", furnishedspinget = "",
            possessionbyspinget = "", noofroomspinget="", qualityratingspinget = "", floorallowedspinget = "", roomavailabespinget = "", flattypespinget = "";
    DatePickerDialog.OnDateSetListener date;
    ArrayList<String> homethings = new ArrayList<>();
    ArrayList<String> otherrooms = new ArrayList<>();
    String homethingsget = "", otherroomsget = "";
    Button btnnext, btnback, btnAddFlatType;
    RadioButton yespark, nopark,yesrera, norera;

    List<FlatTypeModel> flatTypeModels;
    RecyclerView rvFlatType;
    boolean validFlatType = false;

    CheckBox wardrobe, kitchen,fridge,ac,stove,geyser,dinning,sofa,washing,water,microwave,curtains, chimney,exhaust, poojaroom,studyroom,serventroom,otherroom;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_details);


        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Property Details");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });






        unit1 = findViewById(R.id.unit1);
        unit2 = findViewById(R.id.unit2);
        unit3 = findViewById(R.id.unit3);

        built9 = findViewById(R.id.built9);
        carpet9 = findViewById(R.id.carpet9);


        btnnext = findViewById(R.id.btnnext);
        btnback = findViewById(R.id.btnback);

        bedroom9 = findViewById(R.id.bedroom9);
        bathroom9 = findViewById(R.id.bathromm9);
        balconies9 = findViewById(R.id.balconies9);
        bedroomspin = findViewById(R.id.bedroomspin);
        bathroomspin = findViewById(R.id.bathroomspin);
        balconiesspin = findViewById(R.id.balconiesspin);
        addrooms9 = findViewById(R.id.addrooms9);
        furnisheditem9 = findViewById(R.id.furnisheditem9);
        totalfloor9 = findViewById(R.id.totalfloor9);
        reserver9 = findViewById(R.id.reserver9);
        availability9 = findViewById(R.id.availability9);
        ageofproperty9 = findViewById(R.id.ageofproperty9);
        availabilityfromdate9 = findViewById(R.id.availabilityfromdate9);
        furnishedspin9 = findViewById(R.id.furnishedspin9);
        possessionby9 = findViewById(R.id.possessionby9);
        noofroom9 = findViewById(R.id.noofroom9);
        qualityrating9 = findViewById(R.id.qualityrating9);
        floorallowed9 = findViewById(R.id.floorallowed9);
        roomavailabe9 = findViewById(R.id.roomavailabe9);
        flattype9 = findViewById(R.id.flattype9);

        llDataBuilder = findViewById(R.id.ll_data_builder);

        yespark = findViewById(R.id.yespark);
        nopark = findViewById(R.id.nopark);


        yesrera = findViewById(R.id.yesrera);
        norera = findViewById(R.id.norera);

        spiFlatType = findViewById(R.id.spi_flat_type);
        edtFlatTypeNo = findViewById(R.id.edt_flat_type_no);
        btnAddFlatType = findViewById(R.id.btn_add_flat_type);
        rvFlatType = findViewById(R.id.rv_flat_type);

        edtStudioAprt = findViewById(R.id.edt_studio_app_no);
        edtPentHouse = findViewById(R.id.edt_pent_house_no);
        edtRowHouse = findViewById(R.id.edt_row_house_no);
        edtDuplex = findViewById(R.id.edt_duplex_no);
        edtShop = findViewById(R.id.edt_shop_no);
        edtCommercialSpace = findViewById(R.id.edt_commercial_space_no);
        rbNationalized = findViewById(R.id.rb_nationalized);



        totalfloorspin = findViewById(R.id.totalfloorspin);
        availabilityspin = findViewById(R.id.availabilityspin);
        ageofpropertyspin = findViewById(R.id.ageofpropertyspin);
        possessionbyspin = findViewById(R.id.possessionbyspin);
        furnishedspin = findViewById(R.id.furnishedspin);
        noofroomspin = findViewById(R.id.noofroomspin);
        qualityratingspin = findViewById(R.id.qualityratingspin);
        floorallowedspin = findViewById(R.id.floorallowedspin);
        roomavailabespin = findViewById(R.id.roomavailabespin);
        flattypespin = findViewById(R.id.flattypespin);



        built9.setVisibility(View.GONE);
        carpet9.setVisibility(View.GONE);
        bedroom9.setVisibility(View.GONE);
        bathroom9.setVisibility(View.GONE);
        balconies9.setVisibility(View.GONE);
        addrooms9.setVisibility(View.GONE);
        furnisheditem9.setVisibility(View.GONE);
        totalfloor9.setVisibility(View.GONE);
        reserver9.setVisibility(View.GONE);
        availability9.setVisibility(View.GONE);
        ageofproperty9.setVisibility(View.GONE);
        availabilityfromdate9.setVisibility(View.GONE);
        furnishedspin9.setVisibility(View.GONE);
        possessionby9.setVisibility(View.GONE);
        noofroom9.setVisibility(View.GONE);
        qualityrating9.setVisibility(View.GONE);
        floorallowed9.setVisibility(View.GONE);
        roomavailabe9.setVisibility(View.GONE);
        flattype9.setVisibility(View.GONE);

        //checkboxs

        wardrobe = findViewById(R.id.wardrobe);
        kitchen = findViewById(R.id.kitchen);
        fridge = findViewById(R.id.fridge);
        ac = findViewById(R.id.ac);
        stove = findViewById(R.id.stove);
        geyser = findViewById(R.id.geyser);
        dinning = findViewById(R.id.dinning);
        sofa = findViewById(R.id.sofa);
        washing = findViewById(R.id.washing);
        water = findViewById(R.id.water);
        microwave = findViewById(R.id.microwave);
        curtains = findViewById(R.id.curtains);
        chimney = findViewById(R.id.chimney);
        exhaust = findViewById(R.id.exhaust);
        poojaroom = findViewById(R.id.poojaroom);
        studyroom = findViewById(R.id.studyroom);
        serventroom = findViewById(R.id.servantroom);
        otherroom = findViewById(R.id.otherroom);

        furnisheditem9.setVisibility(View.GONE);
        flatTypeModels = new ArrayList<>();



        if(ListingData.getInstance(getApplicationContext()).usertype().equals("Builder")) {
//            llDataBuilder.setVisibility(View.VISIBLE);
        }else {
            llDataBuilder.setVisibility(View.GONE);
        }


        if(ListingData.getInstance(getApplicationContext()).category().equals("Offices")){
            built9.setVisibility(View.VISIBLE);
            carpet9.setVisibility(View.VISIBLE);
            bedroom9.setVisibility(View.GONE);
            bathroom9.setVisibility(View.VISIBLE);
            balconies9.setVisibility(View.GONE);
            addrooms9.setVisibility(View.GONE);
            totalfloor9.setVisibility(View.GONE);
            reserver9.setVisibility(View.GONE);
            availability9.setVisibility(View.VISIBLE);
            ageofproperty9.setVisibility(View.GONE);
            availabilityfromdate9.setVisibility(View.GONE);
            furnishedspin9.setVisibility(View.GONE);
            possessionby9.setVisibility(View.VISIBLE);
        }

        if(ListingData.getInstance(getApplicationContext()).category().equals("Retail")){
            built9.setVisibility(View.VISIBLE);
            carpet9.setVisibility(View.VISIBLE);
            bedroom9.setVisibility(View.GONE);
            bathroom9.setVisibility(View.VISIBLE);
            balconies9.setVisibility(View.VISIBLE);
            addrooms9.setVisibility(View.GONE);
            totalfloor9.setVisibility(View.GONE);
            reserver9.setVisibility(View.GONE);
            availability9.setVisibility(View.VISIBLE);
            ageofproperty9.setVisibility(View.GONE);
            availabilityfromdate9.setVisibility(View.GONE);
            furnishedspin9.setVisibility(View.GONE);
            possessionby9.setVisibility(View.VISIBLE);
        }


        if(ListingData.getInstance(getApplicationContext()).category().equals("Land") && ListingData.getInstance(getApplicationContext()).type().equals("Commercial")){
            built9.setVisibility(View.VISIBLE);
            carpet9.setVisibility(View.VISIBLE);
            bedroom9.setVisibility(View.GONE);
            bathroom9.setVisibility(View.GONE);
            balconies9.setVisibility(View.GONE);
            addrooms9.setVisibility(View.GONE);
            totalfloor9.setVisibility(View.GONE);
            reserver9.setVisibility(View.GONE);
            availability9.setVisibility(View.GONE);
            ageofproperty9.setVisibility(View.GONE);
            availabilityfromdate9.setVisibility(View.GONE);
            furnishedspin9.setVisibility(View.GONE);
            possessionby9.setVisibility(View.VISIBLE);
        }
        if(ListingData.getInstance(getApplicationContext()).category().equals("Others") && ListingData.getInstance(getApplicationContext()).type().equals("Commercial")){
            built9.setVisibility(View.VISIBLE);
            carpet9.setVisibility(View.VISIBLE);
            bedroom9.setVisibility(View.GONE);
            bathroom9.setVisibility(View.GONE);
            balconies9.setVisibility(View.GONE);
            addrooms9.setVisibility(View.GONE);
            totalfloor9.setVisibility(View.GONE);
            reserver9.setVisibility(View.GONE);
            availability9.setVisibility(View.GONE);
            ageofproperty9.setVisibility(View.GONE);
            availabilityfromdate9.setVisibility(View.GONE);
            furnishedspin9.setVisibility(View.GONE);
            possessionby9.setVisibility(View.VISIBLE);
        }


        if(ListingData.getInstance(getApplicationContext()).category().equals("Industry") || ListingData.getInstance(getApplicationContext()).category().equals("Storage")){
            built9.setVisibility(View.VISIBLE);
            carpet9.setVisibility(View.VISIBLE);
            bedroom9.setVisibility(View.GONE);
            bathroom9.setVisibility(View.VISIBLE);
            balconies9.setVisibility(View.GONE);
            addrooms9.setVisibility(View.GONE);
            totalfloor9.setVisibility(View.GONE);
            reserver9.setVisibility(View.GONE);
            availability9.setVisibility(View.VISIBLE);
            ageofproperty9.setVisibility(View.GONE);
            availabilityfromdate9.setVisibility(View.GONE);
            furnishedspin9.setVisibility(View.GONE);
            possessionby9.setVisibility(View.VISIBLE);
        }


        if(ListingData.getInstance(getApplicationContext()).category().equals("Hospitality")){
            built9.setVisibility(View.VISIBLE);
            carpet9.setVisibility(View.VISIBLE);
            bedroom9.setVisibility(View.GONE);
            bathroom9.setVisibility(View.VISIBLE);
            balconies9.setVisibility(View.VISIBLE);
            addrooms9.setVisibility(View.VISIBLE);
            totalfloor9.setVisibility(View.GONE);
            reserver9.setVisibility(View.GONE);
            availability9.setVisibility(View.VISIBLE);
            ageofproperty9.setVisibility(View.GONE);
            availabilityfromdate9.setVisibility(View.GONE);
            furnishedspin9.setVisibility(View.VISIBLE);
            possessionby9.setVisibility(View.VISIBLE);
            noofroom9.setVisibility(View.VISIBLE);
            qualityrating9.setVisibility(View.VISIBLE);
        }


        if(ListingData.getInstance(getApplicationContext()).category().equals("Apartment/Flat/Builder Floor") && ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Sell")){
            built9.setVisibility(View.VISIBLE);
            carpet9.setVisibility(View.VISIBLE);
            bedroom9.setVisibility(View.VISIBLE);
            bathroom9.setVisibility(View.VISIBLE);
            balconies9.setVisibility(View.VISIBLE);
            addrooms9.setVisibility(View.VISIBLE);
            totalfloor9.setVisibility(View.VISIBLE);
            flattype9.setVisibility(View.VISIBLE);
            reserver9.setVisibility(View.VISIBLE);
            availability9.setVisibility(View.VISIBLE);
            ageofproperty9.setVisibility(View.GONE);
            availabilityfromdate9.setVisibility(View.GONE);
            furnishedspin9.setVisibility(View.VISIBLE);
            possessionby9.setVisibility(View.VISIBLE);
        }

        if(ListingData.getInstance(getApplicationContext()).category().equals("House/Villa") && ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Sell")){
            System.out.println("print   showw 1");
            built9.setVisibility(View.VISIBLE);
            carpet9.setVisibility(View.VISIBLE);
            bedroom9.setVisibility(View.VISIBLE);
            bathroom9.setVisibility(View.VISIBLE);
            balconies9.setVisibility(View.VISIBLE);
            addrooms9.setVisibility(View.VISIBLE);
            totalfloor9.setVisibility(View.VISIBLE);
            reserver9.setVisibility(View.VISIBLE);
            availability9.setVisibility(View.VISIBLE);
            ageofproperty9.setVisibility(View.GONE);
            flattype9.setVisibility(View.GONE);
            availabilityfromdate9.setVisibility(View.GONE);
            furnishedspin9.setVisibility(View.VISIBLE);
            possessionby9.setVisibility(View.VISIBLE);
        }
        if(ListingData.getInstance(getApplicationContext()).category().equals("Others") && ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Sell") && ListingData.getInstance(getApplicationContext()).type().equals("Residential")){
            built9.setVisibility(View.GONE);
            carpet9.setVisibility(View.GONE);
            bedroom9.setVisibility(View.GONE);
            bathroom9.setVisibility(View.GONE);
            balconies9.setVisibility(View.GONE);
            addrooms9.setVisibility(View.VISIBLE);
            totalfloor9.setVisibility(View.VISIBLE);
            reserver9.setVisibility(View.VISIBLE);
            flattype9.setVisibility(View.GONE);
            availability9.setVisibility(View.VISIBLE);
            ageofproperty9.setVisibility(View.GONE);
            availabilityfromdate9.setVisibility(View.GONE);
            furnishedspin9.setVisibility(View.GONE);
            possessionby9.setVisibility(View.VISIBLE);
        }

        if(ListingData.getInstance(getApplicationContext()).category().equals("Apartment/Flat/Builder Floor") && ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Rent")){
            built9.setVisibility(View.VISIBLE);
            carpet9.setVisibility(View.VISIBLE);
            bedroom9.setVisibility(View.VISIBLE);
            bathroom9.setVisibility(View.VISIBLE);
            balconies9.setVisibility(View.VISIBLE);
            addrooms9.setVisibility(View.VISIBLE);
            totalfloor9.setVisibility(View.VISIBLE);
            reserver9.setVisibility(View.VISIBLE);
            flattype9.setVisibility(View.VISIBLE);
            availability9.setVisibility(View.VISIBLE);
            ageofproperty9.setVisibility(View.VISIBLE);
            availabilityfromdate9.setVisibility(View.VISIBLE);
            furnishedspin9.setVisibility(View.VISIBLE);
            possessionby9.setVisibility(View.VISIBLE);
        }

        if(ListingData.getInstance(getApplicationContext()).category().equals("House/Villa") && ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Rent")){
            System.out.println("print   showw 2");

            built9.setVisibility(View.VISIBLE);
            carpet9.setVisibility(View.VISIBLE);
            bedroom9.setVisibility(View.VISIBLE);
            bathroom9.setVisibility(View.VISIBLE);
            balconies9.setVisibility(View.VISIBLE);
            addrooms9.setVisibility(View.VISIBLE);
            totalfloor9.setVisibility(View.VISIBLE);
            flattype9.setVisibility(View.GONE);
            reserver9.setVisibility(View.VISIBLE);
            availability9.setVisibility(View.VISIBLE);
            ageofproperty9.setVisibility(View.VISIBLE);
            availabilityfromdate9.setVisibility(View.VISIBLE);
            furnishedspin9.setVisibility(View.VISIBLE);
            possessionby9.setVisibility(View.VISIBLE);
        }

        if(ListingData.getInstance(getApplicationContext()).category().equals("Others") && ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Rent") && ListingData.getInstance(getApplicationContext()).type().equals("Residential")){
            built9.setVisibility(View.GONE);
            carpet9.setVisibility(View.GONE);
            bedroom9.setVisibility(View.GONE);
            bathroom9.setVisibility(View.GONE);
            balconies9.setVisibility(View.GONE);
            addrooms9.setVisibility(View.VISIBLE);
            totalfloor9.setVisibility(View.VISIBLE);
            reserver9.setVisibility(View.GONE);
            flattype9.setVisibility(View.GONE);
            availability9.setVisibility(View.GONE);
            ageofproperty9.setVisibility(View.VISIBLE);
            availabilityfromdate9.setVisibility(View.VISIBLE);
            furnishedspin9.setVisibility(View.GONE);
            possessionby9.setVisibility(View.GONE);
        }



        if(ListingData.getInstance(getApplicationContext()).category().equals("Residential Land") && ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Sell")){
            built9.setVisibility(View.VISIBLE);
            carpet9.setVisibility(View.VISIBLE);
            bedroom9.setVisibility(View.GONE);
            bathroom9.setVisibility(View.GONE);
            balconies9.setVisibility(View.GONE);
            addrooms9.setVisibility(View.GONE);
            totalfloor9.setVisibility(View.GONE);
            reserver9.setVisibility(View.GONE);
            flattype9.setVisibility(View.GONE);
            availability9.setVisibility(View.GONE);
            ageofproperty9.setVisibility(View.GONE);
            availabilityfromdate9.setVisibility(View.GONE);
            furnishedspin9.setVisibility(View.GONE);
            possessionby9.setVisibility(View.VISIBLE);
            floorallowed9.setVisibility(View.VISIBLE);
        }


        if(ListingData.getInstance(getApplicationContext()).category().equals("Apartment/Flat/Builder Floor") && ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Paying Guest")){
            built9.setVisibility(View.VISIBLE);
            carpet9.setVisibility(View.VISIBLE);
            bedroom9.setVisibility(View.VISIBLE);
            bathroom9.setVisibility(View.VISIBLE);
            balconies9.setVisibility(View.VISIBLE);
            addrooms9.setVisibility(View.VISIBLE);
            flattype9.setVisibility(View.VISIBLE);
            totalfloor9.setVisibility(View.VISIBLE);
            reserver9.setVisibility(View.VISIBLE);
            availability9.setVisibility(View.VISIBLE);
            ageofproperty9.setVisibility(View.GONE);
            availabilityfromdate9.setVisibility(View.VISIBLE);
            furnishedspin9.setVisibility(View.VISIBLE);
            possessionby9.setVisibility(View.VISIBLE);
        }

        if(ListingData.getInstance(getApplicationContext()).category().equals("House/Villa")&& ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Paying Guest")){
            System.out.println("print   showw ");

            built9.setVisibility(View.VISIBLE);
            carpet9.setVisibility(View.VISIBLE);
            bedroom9.setVisibility(View.VISIBLE);
            bathroom9.setVisibility(View.VISIBLE);
            balconies9.setVisibility(View.VISIBLE);
            addrooms9.setVisibility(View.VISIBLE);
            totalfloor9.setVisibility(View.VISIBLE);
            flattype9.setVisibility(View.GONE);
            reserver9.setVisibility(View.VISIBLE);
            availability9.setVisibility(View.VISIBLE);
            ageofproperty9.setVisibility(View.GONE);
            availabilityfromdate9.setVisibility(View.VISIBLE);
            furnishedspin9.setVisibility(View.VISIBLE);
            possessionby9.setVisibility(View.VISIBLE);
        }



        builtuparea = findViewById(R.id.builtuparea);
        plotarea = findViewById(R.id.plotarea);
        carpetarea = findViewById(R.id.carpetarea);


        availabilityfromdate= findViewById(R.id.availabilityfromdate);



        if(ListingData.getInstance(getApplicationContext()).availabilitydate() != null){
            availabilitydate = ListingData.getInstance(getApplicationContext()).availabilitydate();
        }


        ArrayAdapter<CharSequence> adapter25 = ArrayAdapter.createFromResource(this, R.array.bhk, android.R.layout.simple_spinner_item);
        adapter25.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiFlatType.setAdapter(adapter25);


        spiFlatType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spiFlatTypeNew = spiFlatType.getSelectedItem().toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnAddFlatType.setOnClickListener(v -> {
            if(spiFlatTypeNew.isEmpty()){
                showmessage("Please select flat type!");
            }else if(spiFlatType.getSelectedItem().toString().equals("Select")){
                showmessage("Please select flats type!");
            }else if(edtFlatTypeNo.getText().toString().isEmpty()){
                showmessage("Please enter flats nos!");
            }else{

                FlatTypeModel model = new FlatTypeModel();
                model.setFlatType(spiFlatType.getSelectedItem().toString());
                model.setFlatTypeNo(edtFlatTypeNo.getText().toString());
                if(flatTypeModels.size() > 0){
                    for (FlatTypeModel flatTypeModel : flatTypeModels){
                        if(flatTypeModel.getFlatType().equals(spiFlatType.getSelectedItem().toString())){
                            showmessage("Add valid flat type!");
                            validFlatType = false;
                            break;
                        }else{
                            validFlatType = true;
                        }
                    }
                    if(validFlatType){
                        flatTypeModels.add(model);
                        rvFlatType.getAdapter().notifyDataSetChanged();
                    }
                }else{
                    flatTypeModels.add(model);
                }
                rvFlatType.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
                FlatTypeAdapter adapter = new FlatTypeAdapter(flatTypeModels,this);
                rvFlatType.setAdapter(adapter);


            }
        });


        availabilityfromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        PropertyDetails.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        date,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });


        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
//                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                availabilityfromdate.setText(date);
                availabilitydate = date.toString();
            }
        };

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.units, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unit1.setAdapter(adapter1);


        unit1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                unit1get = unit1.getSelectedItem().toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.units, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unit2.setAdapter(adapter2);


        unit2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                unit2get = unit2.getSelectedItem().toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.units, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unit3.setAdapter(adapter3);


        unit3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                unit3get = unit3.getSelectedItem().toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this, R.array.pgno, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bathroomspin.setAdapter(adapter4);


        bathroomspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bathget = bathroomspin.getSelectedItem().toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this, R.array.pgno, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        balconiesspin.setAdapter(adapter5);


        balconiesspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                balconieget = balconiesspin.getSelectedItem().toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        ArrayAdapter<CharSequence> adapter6 = ArrayAdapter.createFromResource(this, R.array.pgno, android.R.layout.simple_spinner_item);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bedroomspin.setAdapter(adapter6);


        bedroomspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bedget = bedroomspin.getSelectedItem().toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ArrayAdapter<CharSequence> adapter7 = ArrayAdapter.createFromResource(this, R.array.pgno, android.R.layout.simple_spinner_item);
        adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        totalfloorspin.setAdapter(adapter7);


        totalfloorspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                totalfloorget = totalfloorspin.getSelectedItem().toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ArrayAdapter<CharSequence> adapter8 = ArrayAdapter.createFromResource(this, R.array.availability, android.R.layout.simple_spinner_item);
        adapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        availabilityspin.setAdapter(adapter8);


        availabilityspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                availabilityspinget = availabilityspin.getSelectedItem().toString();
                if(availabilityspinget.equals("Ready To Move")){
                    ageofproperty9.setVisibility(View.VISIBLE);
                    possessionby9.setVisibility(View.GONE);
                }else{
                    ageofproperty9.setVisibility(View.GONE);
                    possessionby9.setVisibility(View.VISIBLE);
                }
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ArrayAdapter<CharSequence> adapter9 = ArrayAdapter.createFromResource(this, R.array.ageofproperty, android.R.layout.simple_spinner_item);
        adapter9.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ageofpropertyspin.setAdapter(adapter9);


        ageofpropertyspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ageofpropertyspinget = ageofpropertyspin.getSelectedItem().toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        ArrayAdapter<CharSequence> adapter10 = ArrayAdapter.createFromResource(this, R.array.furnish, android.R.layout.simple_spinner_item);
        adapter10.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        furnishedspin.setAdapter(adapter10);


        furnishedspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                furnishedspinget = furnishedspin.getSelectedItem().toString();

                if(furnishedspinget.equals("Unfurnished")){
                    furnisheditem9.setVisibility(View.GONE);
                }
                if(furnishedspinget.equals("Semifurnished")){
                    furnisheditem9.setVisibility(View.VISIBLE);
                }
                if(furnishedspinget.equals("Furnished")){
                    furnisheditem9.setVisibility(View.VISIBLE);
                }
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ArrayAdapter<CharSequence> adapter11 = ArrayAdapter.createFromResource(this, R.array.possession, android.R.layout.simple_spinner_item);
        adapter11.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        possessionbyspin.setAdapter(adapter11);


        possessionbyspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                possessionbyspinget = possessionbyspin.getSelectedItem().toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ArrayAdapter<CharSequence> adapter12 = ArrayAdapter.createFromResource(this, R.array.pgno, android.R.layout.simple_spinner_item);
        adapter12.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        noofroomspin.setAdapter(adapter12);


        noofroomspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                noofroomspinget = noofroomspin.getSelectedItem().toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ArrayAdapter<CharSequence> adapter13 = ArrayAdapter.createFromResource(this, R.array.rating, android.R.layout.simple_spinner_item);
        adapter13.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        qualityratingspin.setAdapter(adapter13);


        qualityratingspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                qualityratingspinget = qualityratingspin.getSelectedItem().toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ArrayAdapter<CharSequence> adapter14 = ArrayAdapter.createFromResource(this, R.array.pgno, android.R.layout.simple_spinner_item);
        adapter14.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        floorallowedspin.setAdapter(adapter14);


        floorallowedspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                floorallowedspinget = floorallowedspin.getSelectedItem().toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        ArrayAdapter<CharSequence> adapter15 = ArrayAdapter.createFromResource(this, R.array.pgno, android.R.layout.simple_spinner_item);
        adapter15.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roomavailabespin.setAdapter(adapter15);


        roomavailabespin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                roomavailabespinget = roomavailabespin.getSelectedItem().toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ArrayAdapter<CharSequence> adapter16 = ArrayAdapter.createFromResource(this, R.array.bhk, android.R.layout.simple_spinner_item);
        adapter16.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        flattypespin.setAdapter(adapter16);


        flattypespin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                flattypespinget = flattypespin.getSelectedItem().toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        yespark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reservedpark = "Yes";
            }
        });

        nopark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reservedpark = "No";
            }
        });

        yesrera.setChecked(true);
        getrera = "Yes";

        yesrera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getrera = "Yes";
            }
        });

        norera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getrera = "No";
            }
        });

        builtuparea.setText(ListingData.getInstance(getApplicationContext()).builtup());
        plotarea.setText(ListingData.getInstance(getApplicationContext()).plota());
        carpetarea.setText(ListingData.getInstance(getApplicationContext()).cartpeta());

        if(ListingData.getInstance(getApplicationContext()).availabilitydate() == null){
            System.out.println("findddd:  null worked");
        }else{
        availabilityfromdate.setText(ListingData.getInstance(getApplicationContext()).availabilitydate());
        }


        int position1 = adapter1.getPosition(ListingData.getInstance(getApplicationContext()).unit1());
        unit1.setSelection(position1);

        int position2 = adapter2.getPosition(ListingData.getInstance(getApplicationContext()).unit2());
        unit2.setSelection(position2);

        int position3 = adapter3.getPosition(ListingData.getInstance(getApplicationContext()).unit3());
        unit3.setSelection(position3);

        int position4 = adapter4.getPosition(ListingData.getInstance(getApplicationContext()).bath());
        bathroomspin.setSelection(position4);

        int position5 = adapter5.getPosition(ListingData.getInstance(getApplicationContext()).balcoine());
        balconiesspin.setSelection(position5);

        int position6 = adapter6.getPosition(ListingData.getInstance(getApplicationContext()).bed());
        bedroomspin.setSelection(position6);

        int position7 = adapter7.getPosition(ListingData.getInstance(getApplicationContext()).totalfloorget());
        totalfloorspin.setSelection(position7);

        int position8 = adapter8.getPosition(ListingData.getInstance(getApplicationContext()).availabilityspinget());
        availabilityspin.setSelection(position8);

        int position9 = adapter9.getPosition(ListingData.getInstance(getApplicationContext()).ageofpropertyspinget());
        ageofpropertyspin.setSelection(position9);

        int position10 = adapter10.getPosition(ListingData.getInstance(getApplicationContext()).furnishedspinget());
        furnishedspin.setSelection(position10);

        int position11 = adapter11.getPosition(ListingData.getInstance(getApplicationContext()).possessionbyspinget());
        possessionbyspin.setSelection(position11);

        int position12 = adapter12.getPosition(ListingData.getInstance(getApplicationContext()).noofroomspinget());
        noofroomspin.setSelection(position12);

        int position13 = adapter13.getPosition(ListingData.getInstance(getApplicationContext()).qualityratingspinget());
        qualityratingspin.setSelection(position13);

        int position14 = adapter14.getPosition(ListingData.getInstance(getApplicationContext()).floorallowedspinget());
        floorallowedspin.setSelection(position14);

        int position15 = adapter15.getPosition(ListingData.getInstance(getApplicationContext()).roomavailabespinget());
        roomavailabespin.setSelection(position15);

        int position16 = adapter16.getPosition(ListingData.getInstance(getApplicationContext()).flattypespinget());
        flattypespin.setSelection(position16);

        if(ListingData.getInstance(getApplicationContext()).reservedpark() != null){
            if(ListingData.getInstance(getApplicationContext()).reservedpark().equals("Yes")){
                yespark.setChecked(true);
            }else{
                nopark.setChecked(true);
            }
        }
        if(ListingData.getInstance(getApplicationContext()).getrera() != null){
            if(ListingData.getInstance(getApplicationContext()).getrera().equals("Yes")){
                yesrera.setChecked(true);
            }else{
                norera.setChecked(true);
            }
        }


        otherroomsget = ListingData.getInstance(getApplicationContext()).otherroom();
        homethingsget = ListingData.getInstance(getApplicationContext()).homethings();



//        if(homethingsget.contains("Iron")){
//            homethings.add("Iron");
//            wardrobe.setChecked(true);
//        }else{
//            homethings.remove("Iron");
//            wardrobe.setChecked(false);
//        }

        if(ListingData.getInstance(getApplicationContext()).homethings() != null) {


            if (homethingsget.contains("Wardrobe")) {
                homethings.add("Wardrobe");
                wardrobe.setChecked(true);
            } else {
                homethings.remove("Wardrobe");
                wardrobe.setChecked(false);
            }

            if (homethingsget.contains("Modular Kitchen")) {
                homethings.add("Modular Kitchen");
                kitchen.setChecked(true);

            } else {
                homethings.remove("Modular Kitchen");
                kitchen.setChecked(false);

            }

            if (homethingsget.contains("Fridge")) {
                homethings.add("Fridge");
                fridge.setChecked(true);

            } else {
                homethings.remove("Fridge");
                fridge.setChecked(false);

            }

            if (homethingsget.contains("Ac")) {
                homethings.add("Ac");
                ac.setChecked(true);

            } else {
                homethings.remove("Ac");
                ac.setChecked(false);

            }

            if (homethingsget.contains("Stove")) {
                homethings.add("Stove");
                stove.setChecked(true);

            } else {
                homethings.remove("Stove");
                stove.setChecked(false);

            }

            if (homethingsget.contains("Geyser")) {
                homethings.add("Geyser");
                geyser.setChecked(true);

            } else {
                homethings.remove("Geyser");
                geyser.setChecked(false);

            }
            if (homethingsget.contains("Dinning Table")) {
                homethings.add("Dinning Table");
                dinning.setChecked(true);

            } else {
                homethings.remove("Dinning Table");
                dinning.setChecked(false);

            }

            if (homethingsget.contains("Sofa")) {
                homethings.add("Sofa");
                sofa.setChecked(true);

            } else {
                homethings.remove("Sofa");
                sofa.setChecked(false);

            }

            if (homethingsget.contains("Washing Machine")) {
                homethings.add("Washing Machine");
                washing.setChecked(true);

            } else {
                homethings.remove("Washing Machine");
                washing.setChecked(false);

            }

            if (homethingsget.contains("Water Purifier")) {
                homethings.add("Water Purifier");
                water.setChecked(true);

            } else {
                homethings.remove("Water Purifier");
                water.setChecked(false);

            }

            if (homethingsget.contains("Microwave")) {
                homethings.add("Microwave");
                microwave.setChecked(true);

            } else {
                homethings.remove("Microwave");
                microwave.setChecked(false);

            }

            if (homethingsget.contains("Curtains")) {
                homethings.add("Curtains");
                curtains.setChecked(true);

            } else {
                homethings.remove("Curtains");
                curtains.setChecked(false);

            }

            if (homethingsget.contains("Chimney")) {
                homethings.add("Chimney");
                chimney.setChecked(true);

            } else {
                homethings.remove("Chimney");
                chimney.setChecked(false);

            }

            if (homethingsget.contains("Exhaust Fan")) {
                homethings.add("Exhaust Fan");
                exhaust.setChecked(true);


            } else {
                homethings.remove("Exhaust Fan");
                exhaust.setChecked(false);

            }
        }







            if(ListingData.getInstance(getApplicationContext()).otherroom() != null) {


                if (otherroomsget.contains("Pooja Room")) {
                    otherrooms.add("Pooja Room");
                    poojaroom.setChecked(true);

                } else {
                    otherrooms.remove("Pooja Room");
                    poojaroom.setChecked(false);

                }

                if (otherroomsget.contains("Servant Room")) {
                    otherrooms.add("Servant Room");
                    serventroom.setChecked(true);

                } else {
                    otherrooms.remove("Servant Room");
                    serventroom.setChecked(false);

                }

                if (otherroomsget.contains("Others")) {
                    otherrooms.add("Others");
                    otherroom.setChecked(true);

                } else {
                    otherrooms.remove("Others");
                    otherroom.setChecked(false);

                }


                if (otherroomsget.contains("Study Room")) {
                    otherrooms.add("Study Room");
                    studyroom.setChecked(true);

                } else {
                    otherrooms.remove("Study Room");
                    studyroom.setChecked(false);

                }
            }







        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String builtupa, carpeta, plota;
                builtupa = builtuparea.getText().toString().trim();
                carpeta = carpetarea.getText().toString().trim();
                plota = plotarea.getText().toString().trim();

                if(ListingData.getInstance(getApplicationContext()).usertype().equals("Builder")) {
                    Gson gson = new Gson();
                    String flatType = gson.toJson(flatTypeModels);

                    edtStudioAprtNo = edtStudioAprt.getText().toString();
                    edtPentHouseNo = edtPentHouse.getText().toString();
                    edtRowHouseNo = edtRowHouse.getText().toString();
                    edtDuplexNo = edtDuplex.getText().toString();
                    edtShopNo = edtShop.getText().toString();
                    edtCommercialSpaceNo = edtCommercialSpace.getText().toString();
                    if(rbNationalized.isChecked()){
                        tvBankLoan = "1";
                    }else{
                        tvBankLoan = "2";
                    }
                    ListingData.getInstance(getApplicationContext()).addpropertydetailsChanges(flatType, edtStudioAprtNo, edtPentHouseNo,edtRowHouseNo,edtDuplexNo,edtShopNo,edtCommercialSpaceNo,tvBankLoan);
                }

                if(ListingData.getInstance(getApplicationContext()).category().equals("Offices")){

                    System.out.println("print   1");

                    if(plota.isEmpty()){
                        showmessage("Please Enter Area");
                        plotarea.setError("Please Enter Area");
                    }
                    else if(unit1get.equals("Select")){
                        showmessage("Please Select The Area Unit");
                    }

                    else{
                        ListingData.getInstance(getApplicationContext()).addpropertydetails(unit1get, unit2get, unit3get, availabilitydate,
                                String.valueOf(homethings), String.valueOf(otherrooms), builtupa, carpeta, plota, bedget, bathget, balconieget,
                                totalfloorget,reservedpark,availabilityspinget, ageofpropertyspinget, furnishedspinget, possessionbyspinget, noofroomspinget, qualityratingspinget,
                                floorallowedspinget, roomavailabespinget, flattypespinget, getrera);

                        startActivity(new Intent(PropertyDetails.this, Pricing.class));
                    }


                }


                if(ListingData.getInstance(getApplicationContext()).category().equals("Retail")){
                    System.out.println("print   2");

                    if(plota.isEmpty()){
                        showmessage("Please Enter Area");
                        plotarea.setError("Please Enter Area");
                    }
                    else if(unit1get.equals("Select")){
                        showmessage("Please Select The Area Unit");
                    }

                    else{
                        ListingData.getInstance(getApplicationContext()).addpropertydetails(unit1get, unit2get, unit3get, availabilitydate,
                                String.valueOf(homethings), String.valueOf(otherrooms), builtupa, carpeta, plota, bedget, bathget, balconieget,
                                totalfloorget,reservedpark,availabilityspinget, ageofpropertyspinget, furnishedspinget, possessionbyspinget, noofroomspinget, qualityratingspinget,
                                floorallowedspinget, roomavailabespinget, flattypespinget, getrera);

                        startActivity(new Intent(PropertyDetails.this, Pricing.class));
                    }
                }


                if(ListingData.getInstance(getApplicationContext()).category().equals("Land") && ListingData.getInstance(getApplicationContext()).type().equals("Commercial")){
                    System.out.println("print   3");

                    if(plota.isEmpty()){
                        showmessage("Please Enter Area");
                        plotarea.setError("Please Enter Area");
                    }
                    else if(unit1get.equals("Select")){
                        showmessage("Please Select The Area Unit");
                    }

                    else{
                        ListingData.getInstance(getApplicationContext()).addpropertydetails(unit1get, unit2get, unit3get, availabilitydate,
                                String.valueOf(homethings), String.valueOf(otherrooms), builtupa, carpeta, plota, bedget, bathget, balconieget,
                                totalfloorget,reservedpark,availabilityspinget, ageofpropertyspinget, furnishedspinget, possessionbyspinget, noofroomspinget, qualityratingspinget,
                                floorallowedspinget, roomavailabespinget, flattypespinget, getrera);

                        startActivity(new Intent(PropertyDetails.this, Pricing.class));
                    }
                }

                if(ListingData.getInstance(getApplicationContext()).category().equals("Others") && ListingData.getInstance(getApplicationContext()).type().equals("Commercial")){
                    System.out.println("print   4");

                    if(plota.isEmpty()){
                        showmessage("Please Enter Area");
                        plotarea.setError("Please Enter Area");
                    }
                    else if(unit1get.equals("Select")){
                        showmessage("Please Select The Area Unit");
                    }

                    else{
                        ListingData.getInstance(getApplicationContext()).addpropertydetails(unit1get, unit2get, unit3get, availabilitydate,
                                String.valueOf(homethings), String.valueOf(otherrooms), builtupa, carpeta, plota, bedget, bathget, balconieget,
                                totalfloorget,reservedpark,availabilityspinget, ageofpropertyspinget, furnishedspinget, possessionbyspinget, noofroomspinget, qualityratingspinget,
                                floorallowedspinget, roomavailabespinget, flattypespinget, getrera);

                        startActivity(new Intent(PropertyDetails.this, Pricing.class));
                    }
                }


                if(ListingData.getInstance(getApplicationContext()).category().equals("Industry") || ListingData.getInstance(getApplicationContext()).category().equals("Storage")) {
                    System.out.println("print   5");


                    if (plota.isEmpty()) {
                        showmessage("Please Enter Area");
                        plotarea.setError("Please Enter Area");
                    } else if (unit1get.equals("Select")) {
                        showmessage("Please Select The Area Unit");
                    }
                    else {
                        ListingData.getInstance(getApplicationContext()).addpropertydetails(unit1get, unit2get, unit3get, availabilitydate,
                                String.valueOf(homethings), String.valueOf(otherrooms), builtupa, carpeta, plota, bedget, bathget, balconieget,
                                totalfloorget, reservedpark, availabilityspinget, ageofpropertyspinget, furnishedspinget, possessionbyspinget, noofroomspinget, qualityratingspinget,
                                floorallowedspinget, roomavailabespinget, flattypespinget, getrera);

                        startActivity(new Intent(PropertyDetails.this, Pricing.class));
                    }

                }


                    if(ListingData.getInstance(getApplicationContext()).category().equals("Hospitality")){
                        System.out.println("print   6");


                        if(plota.isEmpty()){
                            showmessage("Please Enter Area");
                            plotarea.setError("Please Enter Area");
                        }
                        else if(unit1get.equals("Select")){
                            showmessage("Please Select The Area Unit");
                        }


                        else{
                            ListingData.getInstance(getApplicationContext()).addpropertydetails(unit1get, unit2get, unit3get, availabilitydate,
                                    String.valueOf(homethings), String.valueOf(otherrooms), builtupa, carpeta, plota, bedget, bathget, balconieget,
                                    totalfloorget,reservedpark,availabilityspinget, ageofpropertyspinget, furnishedspinget, possessionbyspinget, noofroomspinget, qualityratingspinget,
                                    floorallowedspinget, roomavailabespinget, flattypespinget, getrera);

                            startActivity(new Intent(PropertyDetails.this, Pricing.class));
                        }
                    }

                    if(ListingData.getInstance(getApplicationContext()).category().equals("House/Villa") && ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Sell")){
                        System.out.println("print   7");

                        if(plota.isEmpty()){
                            showmessage("Please Enter Area");
                            plotarea.setError("Please Enter Area");
                        }
                        else if(unit1get.equals("Select")){
                            showmessage("Please Select The Area Unit");
                        }
                        else{
                            ListingData.getInstance(getApplicationContext()).addpropertydetails(unit1get, unit2get, unit3get, availabilitydate,
                                    String.valueOf(homethings), String.valueOf(otherrooms), builtupa, carpeta, plota, bedget, bathget, balconieget,
                                    totalfloorget,reservedpark,availabilityspinget, ageofpropertyspinget, furnishedspinget, possessionbyspinget, noofroomspinget, qualityratingspinget,
                                    floorallowedspinget, roomavailabespinget, flattypespinget, getrera);

                            startActivity(new Intent(PropertyDetails.this, Pricing.class));
                        }
                    }





                if(ListingData.getInstance(getApplicationContext()).category().equals("Apartment/Flat/Builder Floor") && ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Sell")){
                    System.out.println("print   8");

                    if(plota.isEmpty()){
                        showmessage("Please Enter Area");
                        plotarea.setError("Please Enter Area");
                    }
                    else if(unit1get.equals("Select")){
                        showmessage("Please Select The Area Unit");
                    }


                    else{
                        ListingData.getInstance(getApplicationContext()).addpropertydetails(unit1get, unit2get, unit3get, availabilitydate,
                                String.valueOf(homethings), String.valueOf(otherrooms), builtupa, carpeta, plota, bedget, bathget, balconieget,
                                totalfloorget,reservedpark,availabilityspinget, ageofpropertyspinget, furnishedspinget, possessionbyspinget, noofroomspinget, qualityratingspinget,
                                floorallowedspinget, roomavailabespinget, flattypespinget, getrera);

                        startActivity(new Intent(PropertyDetails.this, Pricing.class));
                    }

                }
                if(ListingData.getInstance(getApplicationContext()).category().equals("Others") && ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Sell") && ListingData.getInstance(getApplicationContext()).type().equals("Residential")){
                    System.out.println("print   9");

                    if(plota.isEmpty()){
                        showmessage("Please Enter Area");
                        plotarea.setError("Please Enter Area");
                    }
                    else if(unit1get.equals("Select")){
                        showmessage("Please Select The Area Unit");
                    }


                    else{
                        ListingData.getInstance(getApplicationContext()).addpropertydetails(unit1get, unit2get, unit3get, availabilitydate,
                                String.valueOf(homethings), String.valueOf(otherrooms), builtupa, carpeta, plota, bedget, bathget, balconieget,
                                totalfloorget,reservedpark,availabilityspinget, ageofpropertyspinget, furnishedspinget, possessionbyspinget, noofroomspinget, qualityratingspinget,
                                floorallowedspinget, roomavailabespinget, flattypespinget, getrera);

                        startActivity(new Intent(PropertyDetails.this, Pricing.class));
                    }
                }

                if(ListingData.getInstance(getApplicationContext()).category().equals("Apartment/Flat/Builder Floor") && ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Rent")){
                    System.out.println("print   10");

                    if(plota.isEmpty()){
                        showmessage("Please Enter Area");
                        plotarea.setError("Please Enter Area");
                    }
                    else if(unit1get.equals("Select")){
                        showmessage("Please Select The Area Unit");
                    }


                    else{
                        ListingData.getInstance(getApplicationContext()).addpropertydetails(unit1get, unit2get, unit3get, availabilitydate,
                                String.valueOf(homethings), String.valueOf(otherrooms), builtupa, carpeta, plota, bedget, bathget, balconieget,
                                totalfloorget,reservedpark,availabilityspinget, ageofpropertyspinget, furnishedspinget, possessionbyspinget, noofroomspinget, qualityratingspinget,
                                floorallowedspinget, roomavailabespinget, flattypespinget, getrera);

                        startActivity(new Intent(PropertyDetails.this, Pricing.class));
                    }
                }

                if(ListingData.getInstance(getApplicationContext()).category().equals("House/Villa") && ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Rent")){
                    System.out.println("print   11");

                    if(plota.isEmpty()){
                        showmessage("Please Enter Area");
                        plotarea.setError("Please Enter Area");
                    }
                    else if(unit1get.equals("Select")){
                        showmessage("Please Select The Area Unit");
                    }

                    else{
                        ListingData.getInstance(getApplicationContext()).addpropertydetails(unit1get, unit2get, unit3get, availabilitydate,
                                String.valueOf(homethings), String.valueOf(otherrooms), builtupa, carpeta, plota, bedget, bathget, balconieget,
                                totalfloorget,reservedpark,availabilityspinget, ageofpropertyspinget, furnishedspinget, possessionbyspinget, noofroomspinget, qualityratingspinget,
                                floorallowedspinget, roomavailabespinget, flattypespinget, getrera);

                        startActivity(new Intent(PropertyDetails.this, Pricing.class));
                    }
                }

                if(ListingData.getInstance(getApplicationContext()).category().equals("Others") && ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Rent") && ListingData.getInstance(getApplicationContext()).type().equals("Residential")){
                    System.out.println("print   12");

                    if(plota.isEmpty()){
                        showmessage("Please Enter Area");
                        plotarea.setError("Please Enter Area");
                    }
                    else if(unit1get.equals("Select")){
                        showmessage("Please Select The Area Unit");
                    }



                    else{
                        ListingData.getInstance(getApplicationContext()).addpropertydetails(unit1get, unit2get, unit3get, availabilitydate,
                                String.valueOf(homethings), String.valueOf(otherrooms), builtupa, carpeta, plota, bedget, bathget, balconieget,
                                totalfloorget,reservedpark,availabilityspinget, ageofpropertyspinget, furnishedspinget, possessionbyspinget, noofroomspinget, qualityratingspinget,
                                floorallowedspinget, roomavailabespinget, flattypespinget, getrera);

                        startActivity(new Intent(PropertyDetails.this, Pricing.class));
                    }
                }



                if(ListingData.getInstance(getApplicationContext()).category().equals("Residential Land") && ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Sell")){
                    System.out.println("print   13");

                    if(plota.isEmpty()){
                        showmessage("Please Enter Area");
                        plotarea.setError("Please Enter Area");
                    }
                    else if(unit1get.equals("Select")){
                        showmessage("Please Select The Area Unit");
                    }


                    else{
                        ListingData.getInstance(getApplicationContext()).addpropertydetails(unit1get, unit2get, unit3get, availabilitydate,
                                String.valueOf(homethings), String.valueOf(otherrooms), builtupa, carpeta, plota, bedget, bathget, balconieget,
                                totalfloorget,reservedpark,availabilityspinget, ageofpropertyspinget, furnishedspinget, possessionbyspinget, noofroomspinget, qualityratingspinget,
                                floorallowedspinget, roomavailabespinget, flattypespinget, getrera);

                        startActivity(new Intent(PropertyDetails.this, Pricing.class));
                    }
                }


                if(ListingData.getInstance(getApplicationContext()).category().equals("Apartment/Flat/Builder Floor") && ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Paying Guest")){
                    System.out.println("print   14");

                    if(plota.isEmpty()){
                        showmessage("Please Enter Area");
                        plotarea.setError("Please Enter Area");
                    }
                    else if(unit1get.equals("Select")){
                        showmessage("Please Select The Area Unit");
                    }

                    else{
                        ListingData.getInstance(getApplicationContext()).addpropertydetails(unit1get, unit2get, unit3get, availabilitydate,
                                String.valueOf(homethings), String.valueOf(otherrooms), builtupa, carpeta, plota, bedget, bathget, balconieget,
                                totalfloorget,reservedpark,availabilityspinget, ageofpropertyspinget, furnishedspinget, possessionbyspinget, noofroomspinget, qualityratingspinget,
                                floorallowedspinget, roomavailabespinget, flattypespinget, getrera);

                        startActivity(new Intent(PropertyDetails.this, Pricing.class));
                    }

                }

                if(ListingData.getInstance(getApplicationContext()).category().equals("House/Villa")&& ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Paying Guest")){
                    System.out.println("print   15");

                    if(plota.isEmpty()){
                        showmessage("Please Enter Area");
                        plotarea.setError("Please Enter Area");
                    }
                    else if(unit1get.equals("Select")){
                        showmessage("Please Select The Area Unit");
                    }

                    else{
                        ListingData.getInstance(getApplicationContext()).addpropertydetails(unit1get, unit2get, unit3get, availabilitydate,
                                String.valueOf(homethings), String.valueOf(otherrooms), builtupa, carpeta, plota, bedget, bathget, balconieget,
                                totalfloorget,reservedpark,availabilityspinget, ageofpropertyspinget, furnishedspinget, possessionbyspinget, noofroomspinget, qualityratingspinget,
                                floorallowedspinget, roomavailabespinget, flattypespinget, getrera);

                        startActivity(new Intent(PropertyDetails.this, Pricing.class));
                    }
                }
            }
        });


        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });





    }

    private void showmessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void selectItem(View view) {

        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()) {
            case R.id.wardrobe:
                if (checked) {
                    homethings.add("Wardrobe");
                } else {
                    homethings.remove("Wardrobe");
                }
                break;

            case R.id.kitchen:
                if (checked) {
                    homethings.add("Modular Kitchen");
                } else {
                    homethings.remove("Modular Kitchen");
                }
                break;

            case R.id.fridge:
                if (checked) {
                    homethings.add("Fridge");
                } else {
                    homethings.remove("Fridge");
                }
                break;

            case R.id.ac:
                if (checked) {
                    homethings.add("Ac");
                } else {
                    homethings.remove("Ac");
                }
                break;

            case R.id.geyser:
                if (checked) {
                    homethings.add("Geyser");
                } else {
                    homethings.remove("Geyser");
                }
                break;
            case R.id.dinning:
                if (checked) {
                    homethings.add("Dinning Table");
                } else {
                    homethings.remove("Dinning Table");
                }
                break;
            case R.id.sofa:
                if (checked) {
                    homethings.add("Sofa");
                } else {
                    homethings.remove("Sofa");
                }
                break;

            case R.id.stove:
                if (checked) {
                    homethings.add("Stove");
                } else {
                    homethings.remove("Stove");
                }
                break;

            case R.id.washing:
                if (checked) {
                    homethings.add("Washing Machine");
                } else {
                    homethings.remove("Washing Machine");
                }
                break;

            case R.id.water:
                if (checked) {
                    homethings.add("Water Purifier");
                } else {
                    homethings.remove("Water Purifier");
                }
                break;

            case R.id.microwave:
                if (checked) {
                    homethings.add("Microwave");
                } else {
                    homethings.remove("Microwave");
                }
                break;

            case R.id.curtains:
                if (checked) {
                    homethings.add("Curtains");
                } else {
                    homethings.remove("Curtains");
                }
                break;

            case R.id.chimney:
                if (checked) {
                    homethings.add("Chimney");
                } else {
                    homethings.remove("Chimney");
                }
                break;

            case R.id.exhaust:
                if (checked) {
                    homethings.add("Exhaust Fan");
                } else {
                    homethings.remove("Exhaust Fan");
                }
                break;

            case R.id.poojaroom:
                if (checked) {
                    otherrooms.add("Pooja Room");
                } else {
                    otherrooms.remove("Pooja Room");
                }
                break;

            case R.id.servantroom:
                if (checked) {
                    otherrooms.add("Servant Room");
                } else {
                    otherrooms.remove("Servant Room");
                }
                break;

            case R.id.otherroom:
                if (checked) {
                    otherrooms.add("Others");
                } else {
                    otherrooms.remove("Others");
                }
                break;

            case R.id.studyroom:
                if (checked) {
                    otherrooms.add("Study Room");
                } else {
                    otherrooms.remove("Study Room");
                }
                break;


        }

    }


}
