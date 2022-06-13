package com.nine__square.property.AddPropertActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.nine__square.property.R;
import com.nine__square.property.Utils.ListingData;

import java.util.ArrayList;

public class Pricing extends AppCompatActivity {

    EditText price, maintenance, bookingamount, durationofrentag, monthofnotice, earlylivingcharges,
            contractduration, annualduespayable, depositea;
    LinearLayout price9,pricenegotiate9, securitydeposit9,maintenance9, bookingamount9, durationofrentag9, monthofnotice9, ownership9, facilityinprice9,
            fooddetails9, cotractduration9, earlylivingcharges9, annualduespayable9;


    CheckBox electricCharge, negotiable,food, laundry,fridge, electricity,none,housekeeping,dth,water,wifi, breakfast,lunch,dinner,breakfast1,lunch1,dinner1;
    Spinner securitydepositspin, maintenancemonthlyspin, ownweshipspin;
    String securitydepositspinget = "", maintenancemonthlyspinget = "", ownweshipspinget = "";
    Button btnnext, btnback;
    RadioButton veg, nonveg;
    String typeoffood="", typeoffoodget;

    ArrayList<String> pricestep = new ArrayList<>();
    ArrayList<String> facilityincluded = new ArrayList<>();
    ArrayList<String> weekdays = new ArrayList<>();
    ArrayList<String> weekends = new ArrayList<>();

    String pricestepget = "", facilityincludedget = "", weekdaysget = "", weekendsget = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pricing);


        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Price");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

        price = findViewById(R.id.price);
        maintenance = findViewById(R.id.maintenance);
        bookingamount = findViewById(R.id.bookingamount);
        durationofrentag = findViewById(R.id.durationofrentag);
        monthofnotice = findViewById(R.id.monthsofnotice);



        price9 = findViewById(R.id.price9);
        pricenegotiate9 = findViewById(R.id.pricenegotiate9);
        securitydeposit9 = findViewById(R.id.securitydeposit9);
        maintenance9 = findViewById(R.id.maintenance9);
        bookingamount9 = findViewById(R.id.bookingamount9);
        durationofrentag9 = findViewById(R.id.durationofrentag9);
        monthofnotice9 = findViewById(R.id.monthsofnotice9);
        ownership9 = findViewById(R.id.ownership9);
        facilityinprice9 = findViewById(R.id.facilityinprice9);
        fooddetails9 = findViewById(R.id.fooddetail9);
        cotractduration9 = findViewById(R.id.cotractduration9);
        earlylivingcharges9 = findViewById(R.id.earlylivingcharges9);
        annualduespayable9 = findViewById(R.id.annualduespayable9);



        price9.setVisibility(View.GONE);
        pricenegotiate9.setVisibility(View.GONE);
        securitydeposit9.setVisibility(View.GONE);
        maintenance9.setVisibility(View.GONE);
        bookingamount9.setVisibility(View.GONE);
        durationofrentag9.setVisibility(View.GONE);
        monthofnotice9.setVisibility(View.GONE);
        ownership9.setVisibility(View.GONE);
        
        facilityinprice9.setVisibility(View.GONE);
        fooddetails9.setVisibility(View.GONE);
        cotractduration9.setVisibility(View.GONE);
        earlylivingcharges9.setVisibility(View.GONE);
        annualduespayable9.setVisibility(View.GONE);


        //sell

        if(ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Sell") && ListingData.getInstance(getApplicationContext()).category().equals("Apartment/Flat/Builder Floor")){
            price9.setVisibility(View.VISIBLE);
            pricenegotiate9.setVisibility(View.VISIBLE);
            ownership9.setVisibility(View.VISIBLE);
            maintenance9.setVisibility(View.VISIBLE);
            bookingamount9.setVisibility(View.VISIBLE);
            
        }
        if(ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Sell") && ListingData.getInstance(getApplicationContext()).category().equals("Residential Land")){
            price9.setVisibility(View.VISIBLE);
            pricenegotiate9.setVisibility(View.VISIBLE);
            ownership9.setVisibility(View.VISIBLE);
            maintenance9.setVisibility(View.VISIBLE);
            bookingamount9.setVisibility(View.VISIBLE);
            
        }
        if(ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Sell") && ListingData.getInstance(getApplicationContext()).category().equals("House/Villa")){
            price9.setVisibility(View.VISIBLE);
            pricenegotiate9.setVisibility(View.VISIBLE);
            ownership9.setVisibility(View.VISIBLE);
            maintenance9.setVisibility(View.VISIBLE);
            bookingamount9.setVisibility(View.VISIBLE);
            
        }
        if(ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Sell") && ListingData.getInstance(getApplicationContext()).category().equals("Others")){
            price9.setVisibility(View.VISIBLE);
            pricenegotiate9.setVisibility(View.VISIBLE);
            ownership9.setVisibility(View.VISIBLE);
            maintenance9.setVisibility(View.VISIBLE);
            bookingamount9.setVisibility(View.VISIBLE);
            
        }


        //rent

        if(ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Rent") && ListingData.getInstance(getApplicationContext()).category().equals("Apartment/Flat/Builder Floor")){
            price9.setVisibility(View.VISIBLE);
            pricenegotiate9.setVisibility(View.VISIBLE);
            securitydeposit9.setVisibility(View.VISIBLE);
            maintenance9.setVisibility(View.VISIBLE);
            bookingamount9.setVisibility(View.VISIBLE);
            durationofrentag9.setVisibility(View.VISIBLE);
            monthofnotice9.setVisibility(View.VISIBLE);
        }

        if(ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Rent") && ListingData.getInstance(getApplicationContext()).category().equals("House/Villa")){
            price9.setVisibility(View.VISIBLE);
            pricenegotiate9.setVisibility(View.VISIBLE);
            securitydeposit9.setVisibility(View.VISIBLE);
            maintenance9.setVisibility(View.VISIBLE);
            bookingamount9.setVisibility(View.VISIBLE);
            durationofrentag9.setVisibility(View.VISIBLE);
            monthofnotice9.setVisibility(View.VISIBLE);
        }


        if(ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Rent") && ListingData.getInstance(getApplicationContext()).category().equals("Others")){
            price9.setVisibility(View.VISIBLE);
            pricenegotiate9.setVisibility(View.VISIBLE);
            securitydeposit9.setVisibility(View.VISIBLE);
            maintenance9.setVisibility(View.VISIBLE);
            bookingamount9.setVisibility(View.VISIBLE);
            durationofrentag9.setVisibility(View.VISIBLE);
            monthofnotice9.setVisibility(View.VISIBLE);
        }


        //Paying Geust

        if(ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Paying Guest") && ListingData.getInstance(getApplicationContext()).category().equals("Apartment/Flat/Builder Floor")){

            price9.setVisibility(View.VISIBLE);
            securitydeposit9.setVisibility(View.VISIBLE);
            maintenance9.setVisibility(View.GONE);
            bookingamount9.setVisibility(View.GONE);
            durationofrentag9.setVisibility(View.GONE);
            monthofnotice9.setVisibility(View.GONE);
            ownership9.setVisibility(View.GONE);
            
            facilityinprice9.setVisibility(View.VISIBLE);
            fooddetails9.setVisibility(View.VISIBLE);
            cotractduration9.setVisibility(View.VISIBLE);
            earlylivingcharges9.setVisibility(View.VISIBLE);
        }
        if(ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Paying Guest") && ListingData.getInstance(getApplicationContext()).category().equals("House/Villa")){
            price9.setVisibility(View.VISIBLE);
            securitydeposit9.setVisibility(View.VISIBLE);
            maintenance9.setVisibility(View.GONE);
            bookingamount9.setVisibility(View.GONE);
            durationofrentag9.setVisibility(View.GONE);
            monthofnotice9.setVisibility(View.GONE);
            ownership9.setVisibility(View.GONE);
            
            facilityinprice9.setVisibility(View.VISIBLE);
            fooddetails9.setVisibility(View.VISIBLE);
            cotractduration9.setVisibility(View.VISIBLE);
            earlylivingcharges9.setVisibility(View.VISIBLE);
        }


        // commersial


        if(ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Sell") && (ListingData.getInstance(getApplicationContext()).category().equals("Offices")
                || ListingData.getInstance(getApplicationContext()).category().equals("Retail")
        || ListingData.getInstance(getApplicationContext()).category().equals("Land") || ListingData.getInstance(getApplicationContext()).category().equals("Industry")
        || ListingData.getInstance(getApplicationContext()).category().equals("Storage") || ListingData.getInstance(getApplicationContext()).category().equals("Hospitality"))){
            price9.setVisibility(View.VISIBLE);
            pricenegotiate9.setVisibility(View.VISIBLE);
            ownership9.setVisibility(View.VISIBLE);
            maintenance9.setVisibility(View.VISIBLE);
            bookingamount9.setVisibility(View.VISIBLE);
            
            annualduespayable9.setVisibility(View.VISIBLE);
        }

        if(ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Rent") && (ListingData.getInstance(getApplicationContext()).category().equals("Offices")
                || ListingData.getInstance(getApplicationContext()).category().equals("Retail")
                || ListingData.getInstance(getApplicationContext()).category().equals("Land") || ListingData.getInstance(getApplicationContext()).category().equals("Industry")
                || ListingData.getInstance(getApplicationContext()).category().equals("Storage") || ListingData.getInstance(getApplicationContext()).category().equals("Hospitality"))){
            price9.setVisibility(View.VISIBLE);
            pricenegotiate9.setVisibility(View.VISIBLE);
            maintenance9.setVisibility(View.VISIBLE);
            bookingamount9.setVisibility(View.VISIBLE);
            
            annualduespayable9.setVisibility(View.VISIBLE);
        }







        veg = findViewById(R.id.veg);
        nonveg = findViewById(R.id.nonveg);



//        CheckBox electricCharge, negotiable,food, laundry,fridge,
//        electricity,none,housekeeping,dth,water,wifi, breakfast,lunch,dinner,breakfast1,lunch1,dinner1;



        electricCharge = findViewById(R.id.electricCharge);
        negotiable = findViewById(R.id.negotiable);
        food = findViewById(R.id.food);
        laundry = findViewById(R.id.laundry);
        fridge = findViewById(R.id.fridge);
        electricity = findViewById(R.id.electricity);
        none = findViewById(R.id.none);
        housekeeping = findViewById(R.id.housekeeping);
        dth = findViewById(R.id.dth);
        water = findViewById(R.id.water);
        wifi = findViewById(R.id.wifi);
        breakfast = findViewById(R.id.breakfast);
        lunch = findViewById(R.id.lunch);
        dinner = findViewById(R.id.dinner);
        breakfast1 = findViewById(R.id.breakfast1);
        lunch1 = findViewById(R.id.lunch1);
        dinner1 = findViewById(R.id.dinner1);


        securitydepositspin = findViewById(R.id.securitydepositspin);
        maintenancemonthlyspin = findViewById(R.id.maintenancemonthlyspin);
        ownweshipspin = findViewById(R.id.ownweshipspin);
        earlylivingcharges = findViewById(R.id.earlylivingcharges);
        contractduration = findViewById(R.id.contractduration);
        annualduespayable = findViewById(R.id.annualduespayable);
        depositea = findViewById(R.id.depositea);


        btnnext = findViewById(R.id.btnnext);
        btnback = findViewById(R.id.btnback);



        veg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                typeoffood = "Veg";

            }
        });

        nonveg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                typeoffood = "Veg and Non-Veg";

            }
        });

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(Pricing.this, R.array.securitydeposite, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        securitydepositspin.setAdapter(adapter1);


        securitydepositspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                securitydepositspinget = securitydepositspin.getSelectedItem().toString();
                if(securitydepositspinget.equals("Fixed")){
                    depositea.setHint("Price");
                }
                if(securitydepositspinget.equals("Multiple Of Rent")){
                    depositea.setHint("Months");
                }
                if(securitydepositspinget.equals("Select")){
                    depositea.setHint("");
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(Pricing.this, R.array.maintenance, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        maintenancemonthlyspin.setAdapter(adapter2);


        maintenancemonthlyspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maintenancemonthlyspinget = maintenancemonthlyspin.getSelectedItem().toString();
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(Pricing.this, R.array.ownership, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ownweshipspin.setAdapter(adapter3);


        ownweshipspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ownweshipspinget = ownweshipspin.getSelectedItem().toString();
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });





        price.setText(ListingData.getInstance(getApplicationContext()).priceget());
        maintenance.setText(ListingData.getInstance(getApplicationContext()).maintenanceget());
        bookingamount.setText(ListingData.getInstance(getApplicationContext()).bookingamountget());
        durationofrentag.setText(ListingData.getInstance(getApplicationContext()).durationofrentagget());
        monthofnotice.setText(ListingData.getInstance(getApplicationContext()).monthofnoticeget());
        earlylivingcharges.setText(ListingData.getInstance(getApplicationContext()).earlylivingchargesget());
        annualduespayable.setText(ListingData.getInstance(getApplicationContext()).annualduespayableget());
        contractduration.setText(ListingData.getInstance(getApplicationContext()).contractdurationget());
        depositea.setText(ListingData.getInstance(getApplicationContext()).depositeaget());


        pricestepget = ListingData.getInstance(getApplicationContext()).pricestep();
        facilityincludedget = ListingData.getInstance(getApplicationContext()).facilityincluded();
        weekdaysget = ListingData.getInstance(getApplicationContext()).weekdays();
        weekendsget = ListingData.getInstance(getApplicationContext()).weeends();




        if(ListingData.getInstance(getApplicationContext()).typeoffood() != null){
            if(ListingData.getInstance(getApplicationContext()).typeoffood().equals("Veg")){
                veg.setChecked(true);
                typeoffood = "Veg";
            }else{
                nonveg.setChecked(true);
                typeoffood = "Ved and Non-Veg";

            }
        }



        int position1 = adapter1.getPosition(ListingData.getInstance(getApplicationContext()).securitydepositspinget());
        securitydepositspin.setSelection(position1);

        int position2 = adapter2.getPosition(ListingData.getInstance(getApplicationContext()).maintenancemonthlyspinget());
        maintenancemonthlyspin.setSelection(position2);

        int position3 = adapter3.getPosition(ListingData.getInstance(getApplicationContext()).ownweshipspinget());
        ownweshipspin.setSelection(position3);


        if(ListingData.getInstance(getApplicationContext()).pricestep() != null) {


            if (pricestepget.contains("Electricity And Water Charges Excluded")) {
                pricestep.add("Electricity And Water Charges Excluded");
                electricCharge.setChecked(true);

            } else {
                pricestep.remove("Electricity And Water Charges Excluded");
                electricCharge.setChecked(false);

            }

            if (pricestepget.contains("Price Negotiable")) {
                pricestep.add("Price Negotiable");
                negotiable.setChecked(true);
            } else {
                pricestep.remove("Price Negotiable");
                negotiable.setChecked(false);

            }
        }


        if(ListingData.getInstance(getApplicationContext()).facilityincluded() != null) {


            if (facilityincludedget.contains("Food")) {
                facilityincluded.add("Food");
                food.setChecked(true);
            } else {
                facilityincluded.remove("Food");
                food.setChecked(false);

            }

            if (facilityincludedget.contains("Laundry")) {
                facilityincluded.add("Laundry");
                laundry.setChecked(true);

            } else {
                facilityincluded.remove("Laundry");
                laundry.setChecked(false);

            }

            if (facilityincludedget.contains("Fridge")) {
                facilityincluded.add("Fridge");
                fridge.setChecked(true);
            } else {
                facilityincluded.remove("Fridge");
                fridge.setChecked(false);

            }

            if (facilityincludedget.contains("Electricity")) {
                facilityincluded.add("Electricity");
                electricity.setChecked(true);
            } else {
                facilityincluded.remove("Electricity");
                electricity.setChecked(false);

            }
            if (facilityincludedget.contains("None Of the Above")) {
                facilityincluded.add("None Of the Above");
                none.setChecked(true);
            } else {
                facilityincluded.remove("None Of the Above");
                none.setChecked(false);

            }
            if (facilityincludedget.contains("HouseKeeping")) {
                facilityincluded.add("HouseKeeping");
                housekeeping.setChecked(true);
            } else {
                facilityincluded.remove("HouseKeeping");
                housekeeping.setChecked(false);

            }
            if (facilityincludedget.contains("DTH")) {
                facilityincluded.add("DTH");
                dth.setChecked(true);
            } else {
                facilityincluded.remove("DTH");
                dth.setChecked(false);

            }
            if (facilityincludedget.contains("Water")) {
                facilityincluded.add("Water");
                water.setChecked(true);
            } else {
                facilityincluded.remove("Water");
                water.setChecked(false);

            }
            if (facilityincludedget.contains("Wifi")) {
                facilityincluded.add("Wifi");
                wifi.setChecked(true);
            } else {
                facilityincluded.remove("Wifi");
                wifi.setChecked(false);

            }
        }

        if(ListingData.getInstance(getApplicationContext()).weekdays() != null) {


            if (weekdaysget.contains("Breakfast")) {
                weekdays.add("Breakfast");
                breakfast.setChecked(true);
            } else {
                weekdays.remove("Breakfast");
                breakfast.setChecked(false);

            }

            if (weekdaysget.contains("Lunch")) {
                weekdays.add("Lunch");
                lunch.setChecked(true);
            } else {
                weekdays.remove("Lunch");
                lunch.setChecked(false);

            }


            if (weekdaysget.contains("Dinner")) {
                weekdays.add("Dinner");
                dinner.setChecked(true);
            } else {
                weekdays.remove("Dinner");
                dinner.setChecked(false);

            }
        }

        if(ListingData.getInstance(getApplicationContext()).weeends() != null) {


            if (weekendsget.contains("Breakfast")) {
                weekends.add("Breakfast");
                breakfast1.setChecked(true);
            } else {
                weekends.remove("Breakfast");
                breakfast1.setChecked(false);

            }


            if (weekendsget.contains("Lunch")) {
                weekends.add("Lunch");
                lunch1.setChecked(true);
            } else {
                weekends.remove("Lunch");
                lunch1.setChecked(false);

            }


            if (weekendsget.contains("Dinner")) {
                weekends.add("Dinner");
                dinner1.setChecked(true);
            } else {
                weekends.remove("Dinner");
                dinner1.setChecked(false);

            }

        }






        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String priceget, maintenanceget, bookingamountget, durationofrentagget,
                        monthofnoticeget, earlylivingchargesget, contractdurationget, annualduespayableget, depositeaget="";

                priceget = price.getText().toString().trim();
                maintenanceget = maintenance.getText().toString().trim();
                bookingamountget = bookingamount.getText().toString().trim();
                durationofrentagget = durationofrentag.getText().toString().trim();
                monthofnoticeget = monthofnotice.getText().toString().trim();
                earlylivingchargesget = earlylivingcharges.getText().toString().trim();
                contractdurationget = contractduration.getText().toString().trim();
                annualduespayableget = annualduespayable.getText().toString().trim();
                depositeaget = depositea.getText().toString().trim();


                if(ListingData.getInstance(getApplicationContext()).getinstance().equals("Update") && !ListingData.getInstance(getApplicationContext()).priceget().isEmpty() && !priceget.isEmpty()){
                    if(Integer.parseInt(ListingData.getInstance(getApplicationContext()).priceget()) > Integer.parseInt(price.getText().toString())) {
                        ListingData.getInstance(getApplicationContext()).setOfferPrice("1");
                    }else{
                        ListingData.getInstance(getApplicationContext()).setOfferPrice("0");
                    }
                }else {
                    ListingData.getInstance(getApplicationContext()).setOfferPrice("0");
                }


                if(ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Sell") && ListingData.getInstance(getApplicationContext()).category().equals("Apartment/Flat/Builder Floor")){

                    if(priceget.isEmpty()){
                        showmessage("Please Enter Expected Price");
                        price.setError("Please Enter Expected Price");
                    }

                    else{
                        ListingData.getInstance(getApplicationContext()).addpricing(priceget,maintenanceget,bookingamountget,durationofrentagget,monthofnoticeget,
                                securitydepositspinget,maintenancemonthlyspinget, ownweshipspinget,typeoffood,String.valueOf(pricestep),String.valueOf(facilityincluded), String.valueOf(weekdays),
                                String.valueOf(weekends), earlylivingchargesget,contractdurationget, annualduespayableget, depositeaget);

                        startActivity(new Intent(Pricing.this, Features.class));
                    }

                }
                if(ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Sell") && ListingData.getInstance(getApplicationContext()).category().equals("Residential Land")){
                    if(priceget.isEmpty()){
                        showmessage("Please Enter Expected Price");
                        price.setError("Please Enter Expected Price");
                    }

                    else{
                        ListingData.getInstance(getApplicationContext()).addpricing(priceget,maintenanceget,bookingamountget,durationofrentagget,monthofnoticeget,
                                securitydepositspinget,maintenancemonthlyspinget, ownweshipspinget,typeoffood,String.valueOf(pricestep),String.valueOf(facilityincluded), String.valueOf(weekdays),
                                String.valueOf(weekends), earlylivingchargesget,contractdurationget, annualduespayableget, depositeaget);

                        startActivity(new Intent(Pricing.this, Features.class));
                    }
                }
                if(ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Sell") && ListingData.getInstance(getApplicationContext()).category().equals("House/Villa")){
                    if(priceget.isEmpty()){
                        showmessage("Please Enter Expected Price");
                        price.setError("Please Enter Expected Price");
                    }

                    else{
                        ListingData.getInstance(getApplicationContext()).addpricing(priceget,maintenanceget,bookingamountget,durationofrentagget,monthofnoticeget,
                                securitydepositspinget,maintenancemonthlyspinget, ownweshipspinget,typeoffood,String.valueOf(pricestep),String.valueOf(facilityincluded), String.valueOf(weekdays),
                                String.valueOf(weekends), earlylivingchargesget,contractdurationget, annualduespayableget, depositeaget);

                        startActivity(new Intent(Pricing.this, Features.class));
                    }
                }
                if(ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Sell") && ListingData.getInstance(getApplicationContext()).category().equals("Others")){
                    if(priceget.isEmpty()){
                        showmessage("Please Enter Expected Price");
                        price.setError("Please Enter Expected Price");
                    }

                    else{
                        ListingData.getInstance(getApplicationContext()).addpricing(priceget,maintenanceget,bookingamountget,durationofrentagget,monthofnoticeget,
                                securitydepositspinget,maintenancemonthlyspinget, ownweshipspinget,typeoffood,String.valueOf(pricestep),String.valueOf(facilityincluded), String.valueOf(weekdays),
                                String.valueOf(weekends), earlylivingchargesget,contractdurationget, annualduespayableget, depositeaget);

                        startActivity(new Intent(Pricing.this, Features.class));
                    }
                }


                //rent

                if(ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Rent") && ListingData.getInstance(getApplicationContext()).category().equals("Apartment/Flat/Builder Floor")){

                    if(priceget.isEmpty()){
                        showmessage("Please Enter Expected Price");
                        price.setError("Please Enter Expected Price");
                    }


                    else{
                        ListingData.getInstance(getApplicationContext()).addpricing(priceget,maintenanceget,bookingamountget,durationofrentagget,monthofnoticeget,
                                securitydepositspinget,maintenancemonthlyspinget, ownweshipspinget,typeoffood,String.valueOf(pricestep),String.valueOf(facilityincluded), String.valueOf(weekdays),
                                String.valueOf(weekends), earlylivingchargesget,contractdurationget, annualduespayableget, depositeaget);

                        startActivity(new Intent(Pricing.this, Features.class));
                    }
                }

                if(ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Rent") && ListingData.getInstance(getApplicationContext()).category().equals("House/Villa")){
                    if(priceget.isEmpty()){
                        showmessage("Please Enter Expected Price");
                        price.setError("Please Enter Expected Price");
                    }



                    else{
                        ListingData.getInstance(getApplicationContext()).addpricing(priceget,maintenanceget,bookingamountget,durationofrentagget,monthofnoticeget,
                                securitydepositspinget,maintenancemonthlyspinget, ownweshipspinget,typeoffood,String.valueOf(pricestep),String.valueOf(facilityincluded), String.valueOf(weekdays),
                                String.valueOf(weekends), earlylivingchargesget,contractdurationget, annualduespayableget, depositeaget);

                        startActivity(new Intent(Pricing.this, Features.class));
                    }
                }


                if(ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Rent") && ListingData.getInstance(getApplicationContext()).category().equals("Others")){
                    if(priceget.isEmpty()){
                        showmessage("Please Enter Expected Price");
                        price.setError("Please Enter Expected Price");
                    }


                    else{
                        ListingData.getInstance(getApplicationContext()).addpricing(priceget,maintenanceget,bookingamountget,durationofrentagget,monthofnoticeget,
                                securitydepositspinget,maintenancemonthlyspinget, ownweshipspinget,typeoffood,String.valueOf(pricestep),String.valueOf(facilityincluded), String.valueOf(weekdays),
                                String.valueOf(weekends), earlylivingchargesget,contractdurationget, annualduespayableget, depositeaget);

                        startActivity(new Intent(Pricing.this, Features.class));
                    }
                }


                //Paying Geust

                if(ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Paying Guest") && ListingData.getInstance(getApplicationContext()).category().equals("Apartment/Flat/Builder Floor")){


                    if(priceget.isEmpty()){
                        showmessage("Please Enter Expected Price");
                        price.setError("Please Enter Expected Price");
                    }

                    else{
                        ListingData.getInstance(getApplicationContext()).addpricing(priceget,maintenanceget,bookingamountget,durationofrentagget,monthofnoticeget,
                                securitydepositspinget,maintenancemonthlyspinget, ownweshipspinget,typeoffood,String.valueOf(pricestep),String.valueOf(facilityincluded), String.valueOf(weekdays),
                                String.valueOf(weekends), earlylivingchargesget,contractdurationget, annualduespayableget, depositeaget);

                        startActivity(new Intent(Pricing.this, Features.class));
                    }






                }
                if(ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Paying Guest") && ListingData.getInstance(getApplicationContext()).category().equals("House/Villa")){


                    if(priceget.isEmpty()){
                        showmessage("Please Enter Expected Price");
                        price.setError("Please Enter Expected Price");
                    }

                    else{
                        ListingData.getInstance(getApplicationContext()).addpricing(priceget,maintenanceget,bookingamountget,durationofrentagget,monthofnoticeget,
                                securitydepositspinget,maintenancemonthlyspinget, ownweshipspinget,typeoffood,String.valueOf(pricestep),String.valueOf(facilityincluded), String.valueOf(weekdays),
                                String.valueOf(weekends), earlylivingchargesget,contractdurationget, annualduespayableget, depositeaget);

                        startActivity(new Intent(Pricing.this, Features.class));
                    }

                }


                // commersial


                if(ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Sell") && (ListingData.getInstance(getApplicationContext()).category().equals("Offices")
                        || ListingData.getInstance(getApplicationContext()).category().equals("Retail")
                        || ListingData.getInstance(getApplicationContext()).category().equals("Land") || ListingData.getInstance(getApplicationContext()).category().equals("Industry")
                        || ListingData.getInstance(getApplicationContext()).category().equals("Storage") || ListingData.getInstance(getApplicationContext()).category().equals("Hospitality"))){




                    if(priceget.isEmpty()){
                        showmessage("Please Enter Expected Price");
                        price.setError("Please Enter Expected Price");
                    }

                    else{
                        ListingData.getInstance(getApplicationContext()).addpricing(priceget,maintenanceget,bookingamountget,durationofrentagget,monthofnoticeget,
                                securitydepositspinget,maintenancemonthlyspinget, ownweshipspinget,typeoffood,String.valueOf(pricestep),String.valueOf(facilityincluded), String.valueOf(weekdays),
                                String.valueOf(weekends), earlylivingchargesget,contractdurationget, annualduespayableget, depositeaget);

                        startActivity(new Intent(Pricing.this, Features.class));
                    }
                }

                if(ListingData.getInstance(getApplicationContext()).propertylistfor().equals("Rent") && (ListingData.getInstance(getApplicationContext()).category().equals("Offices")
                        || ListingData.getInstance(getApplicationContext()).category().equals("Retail")
                        || ListingData.getInstance(getApplicationContext()).category().equals("Land") || ListingData.getInstance(getApplicationContext()).category().equals("Industry")
                        || ListingData.getInstance(getApplicationContext()).category().equals("Storage") || ListingData.getInstance(getApplicationContext()).category().equals("Hospitality"))){
                    if(priceget.isEmpty()){
                        showmessage("Please Enter Expected Price");
                        price.setError("Please Enter Expected Price");
                    }


                    else{
                        ListingData.getInstance(getApplicationContext()).addpricing(priceget,maintenanceget,bookingamountget,durationofrentagget,monthofnoticeget,
                                securitydepositspinget,maintenancemonthlyspinget, ownweshipspinget,typeoffood,String.valueOf(pricestep),String.valueOf(facilityincluded), String.valueOf(weekdays),
                                String.valueOf(weekends), earlylivingchargesget,contractdurationget, annualduespayableget, depositeaget);

                        startActivity(new Intent(Pricing.this, Features.class));
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
            case R.id.electricCharge:
                if (checked) {
                    pricestep.add("Electricity And Water Charges Excluded");
                } else {
                    pricestep.remove("Electricity And Water Charges Excluded");
                }
                break;

            case R.id.negotiable:
                if (checked) {
                    pricestep.add("Price Negotiable");
                } else {
                    pricestep.remove("Price Negotiable");
                }
                break;


            case R.id.food:
                if (checked) {
                    facilityincluded.add("Food");
                } else {
                    facilityincluded.remove("Food");
                }
                break;

            case R.id.laundry:
                if (checked) {
                    facilityincluded.add("Laundry");
                } else {
                    facilityincluded.remove("Laundry");
                }
                break;

            case R.id.fridge:
                if (checked) {
                    facilityincluded.add("Fridge");
                } else {
                    facilityincluded.remove("Fridge");
                }
                break;

            case R.id.electricity:
                if (checked) {
                    facilityincluded.add("Electricity");
                } else {
                    facilityincluded.remove("Electricity");
                }
                break;
            case R.id.none:
                if (checked) {
                    facilityincluded.add("None Of the Above");
                } else {
                    facilityincluded.remove("None Of the Above");
                }
                break;
            case R.id.housekeeping:
                if (checked) {
                    facilityincluded.add("HouseKeeping");
                } else {
                    facilityincluded.remove("HouseKeeping");
                }
                break;
            case R.id.dth:
                if (checked) {
                    facilityincluded.add("DTH");
                } else {
                    facilityincluded.remove("DTH");
                }
                break;
            case R.id.water:
                if (checked) {
                    facilityincluded.add("Water");
                } else {
                    facilityincluded.remove("Water");
                }
                break;
            case R.id.wifi:
                if (checked) {
                    facilityincluded.add("Wifi");
                } else {
                    facilityincluded.remove("Wifi");
                }
                break;

            case R.id.breakfast:
                if (checked) {
                    weekdays.add("Breakfast");
                } else {
                    weekdays.remove("Breakfast");
                }
                break;

            case R.id.lunch:
                if (checked) {
                    weekdays.add("Lunch");
                } else {
                    weekdays.remove("Lunch");
                }
                break;


            case R.id.dinner:
                if (checked) {
                    weekdays.add("Dinner");
                } else {
                    weekdays.remove("Dinner");
                }
                break;


            case R.id.breakfast1:
                if (checked) {
                    weekends.add("Breakfast");
                } else {
                    weekends.remove("Breakfast");
                }
                break;


            case R.id.lunch1:
                if (checked) {
                    weekends.add("Lunch");
                } else {
                    weekends.remove("Lunch");
                }
                break;


            case R.id.dinner1:
                if (checked) {
                    weekends.add("Dinner");
                } else {
                    weekends.remove("Dinner");
                }
                break;





        }
    }
}
