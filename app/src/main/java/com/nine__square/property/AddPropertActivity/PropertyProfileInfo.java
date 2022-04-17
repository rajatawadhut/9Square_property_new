package com.nine__square.property.AddPropertActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.nine__square.property.Avtivities.MainActivity;
import com.nine__square.property.Avtivities.MyPropertyActivity;
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

import static com.nine__square.property.Utils.Endpoints.updateproperty;

public class PropertyProfileInfo extends AppCompatActivity {
    EditText city1, number1;
    Button create;
    private ProgressDialog progressDialog;

    ArrayList<String> willing1 = new ArrayList<>();
    ArrayList<String> pgsuitablefor1 = new ArrayList<>();
    ArrayList<String> otherroom1 = new ArrayList<>();
    ArrayList<String> homethings1 = new ArrayList<>();
    ArrayList<String> facilityincluded1 = new ArrayList<>();
    ArrayList<String> weekdays1 = new ArrayList<>();
    ArrayList<String> weekend1 = new ArrayList<>();
    ArrayList<String> anemitiesitem1 = new ArrayList<>();
    ArrayList<String> moreanemitiesitem1 = new ArrayList<>();
    ArrayList<String> watersourceitem1 = new ArrayList<>();
    ArrayList<String> overlookingitem1 = new ArrayList<>();
    ArrayList<String> somefeatureitem1 = new ArrayList<>();
    ArrayList<String> byersitem1 = new ArrayList<>();
    ArrayList<String> timeitems1 = new ArrayList<>();

    String usertype = "", type = "", category = "", categorytype = "", agrement = "", pgavaliablefor = "", pgshareprivate = "",
            sharingspinnumber = "", propertylistfor = "", willing = "", pgsuitablefor = "",state = "", city = "", locality = "", projectname = "", address = "",
            unit1 = "",unit2 = "",unit3 = "",availabilitydate = "", otherroom = "", homethings = "", builtupa = "", carpeta = "", plota = "", bed = "",
            bath = "", balconie = "", totalfloorget = "", reservedpark = "", availabilityspinget = "", ageofpropertyspinget = "",
            furnishedspinget = "",possessionbyspinget = "",
            noofroomspinget = "", qualityratingspinget = "", floorallowedspinget = "", roomavailabespinget = "", priceget = "", maintenanceget = "",
            bookingamountget = "",
            durationofrentagget = "", monthofnoticeget = "", securitydepositspinget = "", maintenancemonthlyspinget = "",
            ownweshipspinget = "",
            typeoffood = "", pricestep = "", facilityincluded = "", weekdays = "", weeends = "", earlylivingchargesget = "", contractdurationget = "",
            annualduespayableget = "",
            depositeaget = "", facingspinget = "", unitspinget = "", typeofflooringspinget = "", powerbackupspinget = "", lasttimespinget = "",
            pet = "", visiter = "", smoking = "",
            alcohol = "", event = "", anemitiesitem = "", moreanemitiesitem = "", watersourceitem = "", overlookingitem = "",
            somefeatureitem = "", byersitem = "", timeitems = "",
            widthfacingget = "", descriptionget = "", boundrywall = "",flattype = "", user_id = "", image="", imagepresent="", getrera="", getlatlong = "";

    String getsubscription = "",  spiFlatTypeNew ="", edtStudioAprtNo = "", edtPentHouseNo = "",edtRowHouseNo= "", edtDuplexNo = "",edtShopNo = "", edtCommercialSpaceNo = "", tvBankLoan = "";







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_profile_info);



        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Profile Details");
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
//        progressDialog.setCancelable(false);

        city1 = findViewById(R.id.city);
        number1 = findViewById(R.id.number1);
        create = findViewById(R.id.create);



        city1.setText(VolleySingleton.getInstance(getApplicationContext()).city());
        number1.setText(VolleySingleton.getInstance(getApplicationContext()).mobile());
        user_id = VolleySingleton.getInstance(getApplicationContext()).id();



        if(ListingData.getInstance(getApplicationContext()).getinstance().equals("Add")) {
            create.setText("Create");
        }else{
            create.setText("Update");
        }




        usertype = ListingData.getInstance(getApplicationContext()).usertype();
        type = ListingData.getInstance(getApplicationContext()).type();
        category = ListingData.getInstance(getApplicationContext()).category();
        categorytype = ListingData.getInstance(getApplicationContext()).categorytype();
        agrement = ListingData.getInstance(getApplicationContext()).agrement();
        pgavaliablefor = ListingData.getInstance(getApplicationContext()).pavailabefor();
        pgshareprivate = ListingData.getInstance(getApplicationContext()).pgshareprivate();
        sharingspinnumber = ListingData.getInstance(getApplicationContext()).sharingspinnumber();
        propertylistfor = ListingData.getInstance(getApplicationContext()).propertylistfor();
        willing = ListingData.getInstance(getApplicationContext()).willing();
        pgsuitablefor = ListingData.getInstance(getApplicationContext()).pgsuitablefor();


        state = ListingData.getInstance(getApplicationContext()).state();
        city = ListingData.getInstance(getApplicationContext()).city();
        locality = ListingData.getInstance(getApplicationContext()).locality();
        projectname = ListingData.getInstance(getApplicationContext()).projectname();
        address = ListingData.getInstance(getApplicationContext()).address();



        unit1 = ListingData.getInstance(getApplicationContext()).unit1();
        unit2 = ListingData.getInstance(getApplicationContext()).unit2();
        unit3 = ListingData.getInstance(getApplicationContext()).unit3();
        availabilitydate = ListingData.getInstance(getApplicationContext()).availabilitydate();
        if(availabilitydate == null){
            availabilitydate = "";
        }
        otherroom = ListingData.getInstance(getApplicationContext()).otherroom();
        homethings = ListingData.getInstance(getApplicationContext()).homethings();
        builtupa = ListingData.getInstance(getApplicationContext()).builtup();
        carpeta = ListingData.getInstance(getApplicationContext()).cartpeta();
        plota = ListingData.getInstance(getApplicationContext()).plota();
        bed = ListingData.getInstance(getApplicationContext()).bed();
        bath = ListingData.getInstance(getApplicationContext()).bath();
        balconie = ListingData.getInstance(getApplicationContext()).balcoine();
        flattype = ListingData.getInstance(getApplicationContext()).flattypespinget();
        totalfloorget = ListingData.getInstance(getApplicationContext()).totalfloorget();
        reservedpark = ListingData.getInstance(getApplicationContext()).reservedpark();
        availabilityspinget = ListingData.getInstance(getApplicationContext()).availabilityspinget();
        ageofpropertyspinget = ListingData.getInstance(getApplicationContext()).ageofpropertyspinget();
        furnishedspinget = ListingData.getInstance(getApplicationContext()).furnishedspinget();
        possessionbyspinget = ListingData.getInstance(getApplicationContext()).possessionbyspinget();
        noofroomspinget = ListingData.getInstance(getApplicationContext()).noofroomspinget();
        qualityratingspinget = ListingData.getInstance(getApplicationContext()).qualityratingspinget();
        floorallowedspinget = ListingData.getInstance(getApplicationContext()).floorallowedspinget();



        roomavailabespinget = ListingData.getInstance(getApplicationContext()).roomavailabespinget();
        priceget = ListingData.getInstance(getApplicationContext()).priceget();
        maintenanceget = ListingData.getInstance(getApplicationContext()).maintenanceget();
        bookingamountget = ListingData.getInstance(getApplicationContext()).bookingamountget();
        durationofrentagget = ListingData.getInstance(getApplicationContext()).durationofrentagget();
        monthofnoticeget = ListingData.getInstance(getApplicationContext()).monthofnoticeget();
        securitydepositspinget = ListingData.getInstance(getApplicationContext()).securitydepositspinget();
        maintenancemonthlyspinget = ListingData.getInstance(getApplicationContext()).maintenancemonthlyspinget();
        ownweshipspinget = ListingData.getInstance(getApplicationContext()).ownweshipspinget();
        typeoffood = ListingData.getInstance(getApplicationContext()).typeoffood();
        pricestep = ListingData.getInstance(getApplicationContext()).pricestep();
        facilityincluded = ListingData.getInstance(getApplicationContext()).facilityincluded();
        weekdays = ListingData.getInstance(getApplicationContext()).weekdays();
        weeends = ListingData.getInstance(getApplicationContext()).weeends();
        earlylivingchargesget = ListingData.getInstance(getApplicationContext()).earlylivingchargesget();


        contractdurationget = ListingData.getInstance(getApplicationContext()).contractdurationget();
        annualduespayableget = ListingData.getInstance(getApplicationContext()).annualduespayableget();
        depositeaget = ListingData.getInstance(getApplicationContext()).depositeaget();
        facingspinget = ListingData.getInstance(getApplicationContext()).facingspinget();
        unitspinget = ListingData.getInstance(getApplicationContext()).unitspinget();
        typeofflooringspinget = ListingData.getInstance(getApplicationContext()).typeofflooringspinget();
        powerbackupspinget = ListingData.getInstance(getApplicationContext()).powerbackupspinget();
        lasttimespinget = ListingData.getInstance(getApplicationContext()).lasttimespinget();
        pet = ListingData.getInstance(getApplicationContext()).pet();
        visiter = ListingData.getInstance(getApplicationContext()).visiter();
        smoking = ListingData.getInstance(getApplicationContext()).smoking();
        alcohol = ListingData.getInstance(getApplicationContext()).alcohol();
        event = ListingData.getInstance(getApplicationContext()).event();
        anemitiesitem = ListingData.getInstance(getApplicationContext()).anemitiesitem();
        moreanemitiesitem = ListingData.getInstance(getApplicationContext()).moreanemitiesitem();
        watersourceitem = ListingData.getInstance(getApplicationContext()).watersourceitem();
        overlookingitem = ListingData.getInstance(getApplicationContext()).overlookingitem();
        somefeatureitem = ListingData.getInstance(getApplicationContext()).somefeatureitem();
        byersitem = ListingData.getInstance(getApplicationContext()).byersitem();
        timeitems = ListingData.getInstance(getApplicationContext()).timeitems();
        widthfacingget = ListingData.getInstance(getApplicationContext()).widthfacingget();
        descriptionget = ListingData.getInstance(getApplicationContext()).descriptionget();
        boundrywall = ListingData.getInstance(getApplicationContext()).boundrywall();
        image = ListingData.getInstance(getApplicationContext()).getimage();
        imagepresent = ListingData.getInstance(getApplicationContext()).getimagepresent();
        getrera = ListingData.getInstance(getApplicationContext()).getrera();
        getlatlong = "";
//        getlatlong = ListingData.getInstance(getApplicationContext()).getLatLog();
        getsubscription = ListingData.getInstance(getApplicationContext()).getsubscription();

        spiFlatTypeNew = ListingData.getInstance(getApplicationContext()).getSpiFlatTypeNew();
        edtStudioAprtNo = ListingData.getInstance(getApplicationContext()).getEdtStudioAprtNo();
        edtPentHouseNo = ListingData.getInstance(getApplicationContext()).getEdtPentHouseNo();
        edtRowHouseNo = ListingData.getInstance(getApplicationContext()).getEdtRowHouseNo();
        edtDuplexNo = ListingData.getInstance(getApplicationContext()).getEdtDuplexNo();
        edtShopNo = ListingData.getInstance(getApplicationContext()).getEdtShopNo();
        edtCommercialSpaceNo = ListingData.getInstance(getApplicationContext()).getEdtCommercialSpaceNo();
        tvBankLoan = ListingData.getInstance(getApplicationContext()).getTvBankLoan();



        if(ListingData.getInstance(getApplicationContext()).getinstance().equals("Add")){
            System.out.println("subcription ::::::"+ getsubscription);
            if (getsubscription != "0") {
                int i = Integer.parseInt(getsubscription);
                int ans = i-1;
                getsubscription = String.valueOf(ans);
            }else{
                getsubscription = "0";

            }
        }


            //usertype

            if (usertype.equals("Owner")) {
                usertype = "1";
            } else if (usertype.equals("Dealer")) {
                usertype = "2";
            } else if (usertype.equals("Builder")) {
                usertype = "3";
            }


            if (bed.equals("Select")){
                bed = "";
            }

            if (bath.equals("Select")){
                bath = "";
            }

            if (balconie.equals("Select")){
                balconie = "";
            }

            if (totalfloorget.equals("Select")){
                totalfloorget = "";
            }


            if (noofroomspinget.equals("Select")){
                noofroomspinget = "";
            }
            if (floorallowedspinget.equals("Select")){
                floorallowedspinget = "";
            }


            if (roomavailabespinget.equals("Select")){
                roomavailabespinget = "";
            }


                //type

                if(type.equals("Residential")){
                    type = "1";
                }else{
                    type = "2";
                }

                // category

                if(category.equals("Apartment/Flat/Builder Floor")){
                    category = "1";
                }
                else if(category.equals("Residential Land")){
                    category = "2";
                }
                else if(category.equals("House/Villa")){
                    category = "3";
                }
                else if(category.equals("Offices")){
                    category = "4";
                }
                else if(category.equals("Retail")){
                    category = "5";
                }
                else if(category.equals("Land")){
                    category = "6";
                }
                else if(category.equals("Industry")){
                    category = "7";
                }
                else if(category.equals("Storage")){
                    category = "8";
                }
                else if(category.equals("Hospitality")){
                    category = "9";
                }
                else if(category.equals("Others")){
                    category = "10";
                }

                //categorytype

                if (categorytype.equals("Residential Apartment")){
                    categorytype = "1";
                }

                else if (categorytype.equals("Independent/Builder Floor")){
                    categorytype = "2";
                }
                else if (categorytype.equals("Studio Apartment")){
                    categorytype = "3";
                }
                else if (categorytype.equals("Serviced Apartments")){
                    categorytype = "4";
                }
                else if (categorytype.equals("Independent House/Villa")){
                    categorytype = "5";
                }
                else if (categorytype.equals("Farm House")){
                    categorytype = "6";
                }
                else if (categorytype.equals("Ready to move office space")){
                    categorytype = "7";
                }
                else if (categorytype.equals("Bare shell office space")){
                    categorytype = "8";
                }
                else if (categorytype.equals("Co-working office space")){
                    categorytype = "9";
                }
                else if (categorytype.equals("Commercial Shops")){
                    categorytype = "10";
                }
                else if (categorytype.equals("Commercial Showrooms")){
                    categorytype = "11";
                }
                else if (categorytype.equals("Space in Retail Mall")){
                    categorytype = "12";
                }
                else if (categorytype.equals("Commercial Land/Inst. Land")){
                    categorytype = "13";
                }
                else if (categorytype.equals("Agricultural/Farm Land")){
                    categorytype = "14";
                }
                else if (categorytype.equals("Industrial Lands/Plots")){
                    categorytype = "15";
                }
                else if (categorytype.equals("Factory")){
                    categorytype = "16";
                }
                else if (categorytype.equals("Manufacturing")){
                    categorytype = "17";
                }
                else if (categorytype.equals("Ware House")){
                    categorytype = "18";
                }
                else if (categorytype.equals("Cold Storage")){
                    categorytype = "19";
                }
                else if (categorytype.equals("Hotel/Resorts")){
                    categorytype = "20";
                }
                else if (categorytype.equals("Guest-House/Banquet-Hallsage")){
                    categorytype = "21";
                }



                // aggrement

                if(agrement.equals("Company Lease Agreement")){
                    agrement = "1";
                }else if(agrement.equals("Any")){
                    agrement = "2";
                }


                //pgavaliablefor

                if(pgavaliablefor.equals("Girls")){
                    pgavaliablefor = "1";
                }else if (pgavaliablefor.equals("Boys")){
                    pgavaliablefor ="2";
                }else if (pgavaliablefor.equals("Any")){
                    pgavaliablefor = "3";
                }

                // pgshareprivate

                if (pgshareprivate.equals("Private")){
                    pgshareprivate = "1";
                }else if (pgshareprivate.equals("Sharing")){
                    pgshareprivate = "2";
                }

                // propertylistfor
                if(propertylistfor.equals("Sell")){
                    propertylistfor = "1";
                }else if(propertylistfor.equals("Rent")){
                    propertylistfor = "2";
                }else if(propertylistfor.equals("Paying Guest")){
                    propertylistfor = "3";
                }

                //willing

                if(willing.contains("Family")){
                    willing1.add("1");
                }
                if (willing.contains("Single Men")){
                    willing1.add("2");
                }
                if(willing.contains("Single Women")){
                    willing1.add("3");
                }

                willing = String.valueOf(willing1);


                //pgsuitablefor

                if(pgsuitablefor.contains("Students")){
                    pgsuitablefor1.add("1");
                }
                if(pgsuitablefor.contains("Working Professionals")){
                    pgsuitablefor1.add("2");
                }

                pgsuitablefor = String.valueOf(pgsuitablefor1);

                // unit1

                 if(unit1.equals("Sq.Ft")){
                     unit1 = "1";
                 }
                 else if(unit1.equals("Sq.yards")){
                     unit1 = "2";
                 }else if(unit1.equals("Sq.m")){
                     unit1 = "3";
                 }
                 else if(unit1.equals("Acres")){
                     unit1 = "4";
                 }
                 else if(unit1.equals("Marla")){
                     unit1 = "5";
                 }else if(unit1.equals("Cents")){
                     unit1 = "6";
                 }
                 else if(unit1.equals("Bigha")){
                     unit1 = "7";
                 }else if(unit1.equals("Kottah")){
                     unit1 = "8";
                 }
                 else if(unit1.equals("Grounds")){
                     unit1 = "9";
                 }
                 else if(unit1.equals("Ares")){
                         unit1 = "10";
                     }
                 else if(unit1.equals("Biswa")){
                         unit1 = "11";
                     }
                 else if(unit1.equals("Guntha")){
                         unit1 = "12";
                     }
                 else if(unit1.equals("Aankadam")){
                         unit1 = "13";
                     }

                else if(unit1.equals("Hectares")){
                         unit1 = "14";
                }

                else if(unit1.equals("Rood")){
                    unit1 = "15";
                }

                else if(unit1.equals("Chataks")){
                    unit1 = "16";
                }

                else if(unit1.equals("Perch")){
                    unit1 = "17";
                }

                //unit2

                if(unit2.equals("Sq.Ft")){
                    unit2 = "1";
                }
                else if(unit2.equals("Sq.yards")){
                    unit2 = "2";
                }else if(unit2.equals("Sq.m")){
                    unit2 = "3";
                }
                else if(unit2.equals("Acres")){
                    unit2 = "4";
                }
                else if(unit2.equals("Marla")){
                    unit2 = "5";
                }else if(unit2.equals("Cents")){
                    unit2 = "6";
                }
                else if(unit2.equals("Bigha")){
                    unit2 = "7";
                }else if(unit2.equals("Kottah")){
                    unit2 = "8";
                }
                else if(unit2.equals("Grounds")){
                    unit2 = "9";
                }
                else if(unit2.equals("Ares")){
                    unit2 = "10";
                }
                else if(unit2.equals("Biswa")){
                    unit2 = "11";
                }
                else if(unit2.equals("Guntha")){
                    unit2 = "12";
                }
                else if(unit2.equals("Aankadam")){
                    unit2 = "13";
                }

                else if(unit2.equals("Hectares")){
                    unit2 = "14";
                }

                else if(unit2.equals("Rood")){
                    unit2 = "15";
                }

                else if(unit2.equals("Chataks")){
                    unit2 = "16";
                }

                else if(unit2.equals("Perch")){
                    unit2 = "17";
                }


                //unit 3

                if(unit3.equals("Sq.Ft")){
                    unit3 = "1";
                }
                else if(unit3.equals("Sq.yards")){
                    unit3 = "2";
                }else if(unit3.equals("Sq.m")){
                    unit3 = "3";
                }
                else if(unit3.equals("Acres")){
                    unit3 = "4";
                }
                else if(unit3.equals("Marla")){
                    unit3 = "5";
                }else if(unit3.equals("Cents")){
                    unit3 = "6";
                }
                else if(unit3.equals("Bigha")){
                    unit3 = "7";
                }else if(unit3.equals("Kottah")){
                    unit3 = "8";
                }
                else if(unit3.equals("Grounds")){
                    unit3 = "9";
                }
                else if(unit3.equals("Ares")){
                    unit3 = "10";
                }
                else if(unit3.equals("Biswa")){
                    unit3 = "11";
                }
                else if(unit3.equals("Guntha")){
                    unit3 = "12";
                }
                else if(unit3.equals("Aankadam")){
                    unit3 = "13";
                }

                else if(unit3.equals("Hectares")){
                    unit3 = "14";
                }

                else if(unit3.equals("Rood")){
                    unit3 = "15";
                }

                else if(unit3.equals("Chataks")){
                    unit3 = "16";
                }

                else if(unit3.equals("Perch")){
                    unit3 = "17";
                }

                //othersroom


                if(otherroom.contains("Pooja Room")){
                    otherroom1.add("1");
                }
                if(otherroom.contains("Study Room")){
                    otherroom1.add("2");
                }

                if(otherroom.contains("Servant Room")){
                    otherroom1.add("3");
                }

                if(otherroom.contains("Others")){
                    otherroom1.add("4");
                }

                otherroom = String.valueOf(otherroom1);

                //homethings

                if(homethings.contains("Wardrobe")){
                    homethings1.add("1");
                }


                if(homethings.contains("Modular Kitchen")){
                    homethings1.add("2");
                }


                if(homethings.contains("Fridge")){
                    homethings1.add("3");
                }


                if(homethings.contains("Ac")){
                    homethings1.add("4");
                }


                if(homethings.contains("Stove")){
                    homethings1.add("5");
                }


                if(homethings.contains("Geyser")){
                    homethings1.add("6");
                }


                if(homethings.contains("Dinning Table")){
                    homethings1.add("7");
                }


                if(homethings.contains("Sofa")){
                    homethings1.add("8");
                }


                if(homethings.contains("Washing Machine")){
                    homethings1.add("9");
                }


                if(homethings.contains("Water Purifier")){
                    homethings1.add("10");
                }


                if(homethings.contains("Microwave")){
                    homethings1.add("11");
                }


                if(homethings.contains("Curtains")){
                    homethings1.add("12");
                }

                if(homethings.contains("Chimney")){
                    homethings1.add("13");
                }

                if(homethings.contains("Exhaust Fan")){
                    homethings1.add("14");
                }


                homethings = String.valueOf(homethings1);

                // reservedpark

                if(reservedpark.equals("Yes")){
                    reservedpark = "1";
                }else{
                    reservedpark = "0";
                }

                //availabilityspinget
                if(availabilityspinget.equals("Under Construction")){
                    availabilityspinget = "1";
                }
                else if(availabilityspinget.equals("Ready To Move")){
                    availabilityspinget = "2";

                }

                //ageofpropertyspinget

                if(ageofpropertyspinget.equals("0-1 Year Old Property")){
                    ageofpropertyspinget = "1";
                }
                else if(ageofpropertyspinget.equals("1-5 Year Old Property")){
                    ageofpropertyspinget = "2";
                }
                else if(ageofpropertyspinget.equals("5-10 Year Old Property")){
                    ageofpropertyspinget = "3";
                }
                else if(ageofpropertyspinget.equals("10+ Year Old Property")){
                    ageofpropertyspinget = "4";
                }
                else if(ageofpropertyspinget.equals("Ready To Move")){
                    ageofpropertyspinget = "5";
                }

                //furnishedspinget
                if(furnishedspinget.equals("Furnished")){
                    furnishedspinget = "1";
                }
                else if(furnishedspinget.equals("Semifurnished")){
                    furnishedspinget = "2";
                }
                else if(furnishedspinget.equals("Unfurnished")){
                    furnishedspinget = "3";
                }

                // possessionbyspinget
                if(possessionbyspinget.equals("Within 3 Months")){
                    possessionbyspinget = "1";
                }
               else if(possessionbyspinget.equals("By 2021")){
                   possessionbyspinget = "2";
               }
               else if(possessionbyspinget.equals("By 2022")){
                   possessionbyspinget = "3";
               }
               else if(possessionbyspinget.equals("By 2023")){
                   possessionbyspinget = "4";
               }
               else if(possessionbyspinget.equals("By 2024")){
                   possessionbyspinget = "5";
               }
               else if(possessionbyspinget.equals("By 2025")){
                   possessionbyspinget = "6";
               }
               else if(possessionbyspinget.equals("By 2026")){
                   possessionbyspinget = "7";
               }
               else if(possessionbyspinget.equals("By 2027")){
                   possessionbyspinget = "8";
               }
               else if(possessionbyspinget.equals("By 2028")){
                   possessionbyspinget = "9";
               }
               else if(possessionbyspinget.equals("By 2029")){
                   possessionbyspinget = "10";
               }
               else if(possessionbyspinget.equals("By 2030")){
                   possessionbyspinget = "11";
               }
               else if(possessionbyspinget.equals("By 2031")){
                   possessionbyspinget = "12";
               }

               //securitydepositspinget

               if(securitydepositspinget.equals("Select")){
                   securitydepositspinget = "";
               }
               else if(securitydepositspinget.equals("Fixed")){
                   securitydepositspinget = "1";
               }
               else if(securitydepositspinget.equals("Multiple Of Rent")){
                   securitydepositspinget = "2";
               }
                //maintenancemonthlyspinget

                if(maintenancemonthlyspinget.equals("Monthly")){
                    maintenancemonthlyspinget = "1";
                }
                else if(maintenancemonthlyspinget.equals("Annually")){
                    maintenancemonthlyspinget = "2";
                }
                else if(maintenancemonthlyspinget.equals("One Time")){
                    maintenancemonthlyspinget = "3";
                }
                else if(maintenancemonthlyspinget.equals("Per Unit/Monthly")){
                    maintenancemonthlyspinget = "4";
                }
                else if(maintenancemonthlyspinget.equals("Select")){
                    maintenancemonthlyspinget = "";
                }

                //ownweshipspinget

                if(ownweshipspinget.equals("Freehold")){
                    ownweshipspinget = "1";
                }
                else if(ownweshipspinget.equals("Leasehold")){
                    ownweshipspinget = "2";
                }
                else if(ownweshipspinget.equals("Co-Operative Society")){
                    ownweshipspinget = "3";
                }
                else if(ownweshipspinget.equals("Power Of Attorney")){
                    ownweshipspinget = "4";
                }

                //typeoffood
                if(typeoffood.equals("Veg")){
                    typeoffood = "1";
                }else if(typeoffood.equals("Veg and Non-Veg")){
                    typeoffood = "2";
                }

                //pricestep

                if(pricestep.equals("Price Negotiable")){
                    pricestep = "1";
                }else if(pricestep.equals("Electricity And Water Charges Excluded")){
                    pricestep = "2";
                }

                // facilityincluded
                if(facilityincluded.contains("Food")){
                    facilityincluded1.add("1");
                }

                if(facilityincluded.contains("Laundry")){
                    facilityincluded1.add("2");
                }

                if(facilityincluded.contains("Fridge")){
                    facilityincluded1.add("3");
                }

                if(facilityincluded.contains("Electricity")){
                    facilityincluded1.add("4");
                }

                if(facilityincluded.contains("None Of the Above")){
                    facilityincluded1.add("5");
                }

                if(facilityincluded.contains("HouseKeeping")){
                    facilityincluded1.add("6");
                }

                if(facilityincluded.contains("DTH")){
                    facilityincluded1.add("7");
                }

                if(facilityincluded.contains("Water")){
                    facilityincluded1.add("8");
                }

                if(facilityincluded.contains("Wifi")){
                    facilityincluded1.add("9");
                }

                facilityincluded = String.valueOf(facilityincluded1);

                // weekdays
                if(weekdays.contains("Breakfast")){
                    weekdays1.add("1");
                }
                if(weekdays.contains("Lunch")){
                    weekdays1.add("2");
                }
                if(weekdays.contains("Dinner")){
                    weekdays1.add("3");
                }

                weekdays = String.valueOf(weekdays1);

                //weekend
                if(weeends.contains("Breakfast")){
                    weekend1.add("1");
                }
                if(weeends.contains("Lunch")){
                    weekend1.add("2");
                }
                if(weeends.contains("Dinner")){
                    weekend1.add("3");
                }

                weeends = String.valueOf(weekend1);

                //facing

                if(facingspinget.equals("East")){
                    facingspinget = "1";
                }
                else if(facingspinget.equals("North-East")){
                    facingspinget = "2";
                }
                else if(facingspinget.equals("North")){
                    facingspinget = "3";
                }
                else if(facingspinget.equals("West")){
                    facingspinget = "4";
                }
                else if(facingspinget.equals("South")){
                    facingspinget = "5";
                }
                else if(facingspinget.equals("South-East")){
                    facingspinget = "6";
                }
                else if(facingspinget.equals("North-West")){
                    facingspinget = "7";
                }
                else if(facingspinget.equals("South-West")){
                    facingspinget = "8";
                }
                else if(facingspinget.equals("Select")){
                    facingspinget = "";
                }

                // unitspinget


                if(unitspinget.equals("Meter")){
                    unitspinget = "1";
                }


                //typeofflooringspinget

                if(typeofflooringspinget.equals("Vitrified")){
                    typeofflooringspinget = "1";
                }
                else if(typeofflooringspinget.equals("Marble")){
                    typeofflooringspinget = "2";
                }
                else if(typeofflooringspinget.equals("Concrete")){
                    typeofflooringspinget = "3";
                }
                else if(typeofflooringspinget.equals("Ceramic")){
                    typeofflooringspinget = "4";
                }
                else if(typeofflooringspinget.equals("Polished Concrete")){
                    typeofflooringspinget = "5";
                }
                else if(typeofflooringspinget.equals("Mosaic")){
                    typeofflooringspinget = "6";
                }
                else if(typeofflooringspinget.equals("Wood")){
                    typeofflooringspinget = "7";
                }
                else if(typeofflooringspinget.equals("Granite")){
                    typeofflooringspinget = "8";
                }
                else if(typeofflooringspinget.equals("Spartex")){
                    typeofflooringspinget = "9";
                }
                else if(typeofflooringspinget.equals("Cement")){
                    typeofflooringspinget = "10";
                }
                else if(typeofflooringspinget.equals("Stone")){
                    typeofflooringspinget = "11";
                }
                else if(typeofflooringspinget.equals("Vinyl")){
                    typeofflooringspinget = "12";
                }
                else if(typeofflooringspinget.equals("IPSFinish")){
                    typeofflooringspinget = "13";
                }
                else if(typeofflooringspinget.equals("Others")){
                    typeofflooringspinget = "14";
                }
                else if(typeofflooringspinget.equals("Select")){
                    typeofflooringspinget = "";
                }

                //powerbackupspinget


                if(powerbackupspinget.equals("None")){
                    powerbackupspinget = "1";
                }
                else if(powerbackupspinget.equals("Partial")){
                    powerbackupspinget = "2";
                }
                else if(powerbackupspinget.equals("Full")){
                    powerbackupspinget = "3";
                }
                else if(powerbackupspinget.equals("Select")){
                    powerbackupspinget = "";
                }

                //pet
                if(pet.equals("Yes")){
                    pet = "1";
                }else {
                    pet = "0";
                }

                //visiter
                if(visiter.equals("Yes")){
                    visiter = "1";
                }else {
                    visiter = "0";
                }

                //smoking
                if(smoking.equals("Yes")){
                    smoking = "1";
                }else {
                    smoking = "0";
                }

                // alcohol
               if(alcohol.equals("Yes")){
                   alcohol = "1";
               }else {
                   alcohol = "0";
               }

               //event

               if(event.equals("Yes")){
                   event = "1";
               }else {
                   event = "0";
               }

               //boundrywall
               if(boundrywall.equals("Yes")){
                   boundrywall = "1";
               }else {
                   boundrywall = "0";
               }
               //rera
               if(getrera.equals("Yes")){
                   getrera = "1";
               }else {
                   getrera = "0";
               }

               //anemitiesitem

                if(anemitiesitem.contains("Lift")){
                    anemitiesitem1.add("1");
                }

                if(anemitiesitem.contains("Park")){
                    anemitiesitem1.add("2");
                }

                if(anemitiesitem.contains("Maintenance Staff")){
                    anemitiesitem1.add("3");
                }

                if(anemitiesitem.contains("Visiter Parking")){
                    anemitiesitem1.add("4");
                }

                if(anemitiesitem.contains("FengShui/Vaastu Compliant")){
                    anemitiesitem1.add("5");
                }

                if(anemitiesitem.contains("Intercome Facility")){
                    anemitiesitem1.add("6");
                }

                if(anemitiesitem.contains("Water Storage")){
                    anemitiesitem1.add("7");
                }

                if(anemitiesitem.contains("Security/Fire Alaram")){
                    anemitiesitem1.add("8");
                }

                anemitiesitem = String.valueOf(anemitiesitem1);

                //moreanemitiesitem


        System.out.println("moreanemeties :::"+ moreanemitiesitem);

                if(moreanemitiesitem.contains("Swimming Pool")){
                    moreanemitiesitem1.add("1");
                }

                if(moreanemitiesitem.contains("Centrally Air Conditioned")){
                    moreanemitiesitem1.add("2");
                }

                if(moreanemitiesitem.contains("Garden")){
                    moreanemitiesitem1.add("3");
                }

                if(moreanemitiesitem.contains("Wifi")){
                    moreanemitiesitem1.add("4");
                }

                if(moreanemitiesitem.contains("Piped-Gas")){
                    moreanemitiesitem1.add("5");
                }

                if(moreanemitiesitem.contains("Water Purifier")){
                    moreanemitiesitem1.add("6");
                }

                if(moreanemitiesitem.contains("Club House")){
                    moreanemitiesitem1.add("7");
                }

                if(moreanemitiesitem.contains("Shopping Center")){
                    moreanemitiesitem1.add("8");
                }

                if(moreanemitiesitem.contains("Water Disposal")){
                    moreanemitiesitem1.add("9");
                }

                if(moreanemitiesitem.contains("RainWater Harvesting")){
                    moreanemitiesitem1.add("10");
                }

                if(moreanemitiesitem.contains("Bank Attached Property")){
                    moreanemitiesitem1.add("11");
                }

                if(moreanemitiesitem.contains("Gym")){
                    moreanemitiesitem1.add("12");
                }

                moreanemitiesitem = String.valueOf(moreanemitiesitem1);


                //watersourceitem
                if(watersourceitem.contains("Municipal Corporation")){
                    watersourceitem1.add("1");
                }
               if(watersourceitem.contains("Borewell")){
                    watersourceitem1.add("2");
                }

               watersourceitem = String.valueOf(watersourceitem1);

               //overlookingitem

                if(overlookingitem.contains("Park/Garden")){
                    overlookingitem1.add("1");
                }

                if(overlookingitem.contains("Main Road")){
                    overlookingitem1.add("2");
                }

                if(overlookingitem.contains("Club")){
                    overlookingitem1.add("3");
                }

                if(overlookingitem.contains("Lake Facing")){
                    overlookingitem1.add("4");
                }

                if(overlookingitem.contains("Sea Facing")){
                    overlookingitem1.add("5");
                }

                if(overlookingitem.contains("Others")){
                    overlookingitem1.add("6");
                }

             overlookingitem = String.valueOf(overlookingitem1);

                //somefeatureitem
                if(somefeatureitem.contains("In a Gated Society")){
                    somefeatureitem1.add("1");
                }

                if(somefeatureitem.contains("Corner Property")){
                    somefeatureitem1.add("2");
                }
                if(somefeatureitem.contains("Pet Friendly")){
                    somefeatureitem1.add("3");
                }

                if(somefeatureitem.contains("Wheelchair Friendly")){
                    somefeatureitem1.add("4");
                }

                somefeatureitem = String.valueOf(somefeatureitem1);


                //byersitem

                if (byersitem.contains("24 x 7 Water")) {
                    byersitem1.add("1");
                }
                if (byersitem.contains("Visiter Parking Available")) {
                    byersitem1.add("2");
                }
                if (byersitem.contains("Close To School")) {
                    byersitem1.add("3");
                }
                if (byersitem.contains("Close To Bank")) {
                    byersitem1.add("4");
                }
                if (byersitem.contains("Natural Light")) {
                    byersitem1.add("5");
                }
                if (byersitem.contains("Close To Hospital")) {
                    byersitem1.add("6");
                }
                if (byersitem.contains("Close To Market")) {
                    byersitem1.add("7");
                }
                if (byersitem.contains("Airy Rooms")) {
                    byersitem1.add("8");
                }

                byersitem = String.valueOf(byersitem1);

                //timeitems
                if(timeitems.contains("8am - 12pm")){
                    timeitems1.add("1");
                }

                if(timeitems.contains("12pm - 3pm")){
                    timeitems1.add("2");
                }
                if(timeitems.contains("3pm - 6pm")){
                    timeitems1.add("3");
                }

                if(timeitems.contains("6pm - 9pm")){
                    timeitems1.add("4");
                }

                if(timeitems.contains("9pm - 11pm")){
                    timeitems1.add("5");
                }

                if(timeitems.contains("None")){
                    timeitems1.add("6");
                }

                timeitems = String.valueOf(timeitems1);

                //lasttimespinget
                if(lasttimespinget.contains("7 PM")){
                    lasttimespinget = "1";
                }
                if(lasttimespinget.contains("8 PM")){
                    lasttimespinget = "2";
                }
                if(lasttimespinget.contains("9 PM")){
                    lasttimespinget = "3";
                }
                if(lasttimespinget.contains("10 PM")){
                    lasttimespinget = "4";
                }
                if(lasttimespinget.contains("11 PM")){
                    lasttimespinget = "5";
                }
                if(lasttimespinget.contains("12 PM")){
                    lasttimespinget = "6";
                }
                if(lasttimespinget.contains("1 AM")){
                    lasttimespinget = "7";
                }
                if(lasttimespinget.contains("2 AM")){
                    lasttimespinget = "8";
                }
                if(lasttimespinget.contains("3 AM")){
                    lasttimespinget = "9";
                }

                if(lasttimespinget.contains("24 x 7")){
                    lasttimespinget = "10";
                }
                if(lasttimespinget.contains("Select")){
                    lasttimespinget = "";
                }


                //flattype

                if(flattype.equals("1 BHK")){
                    flattype = "1";
                }
                else if(flattype.equals("2 BHK")){
                    flattype = "2";
                }
                else if(flattype.equals("3 BHK")){
                    flattype = "3";
                }
                else if(flattype.equals("4 BHK")){
                    flattype = "4";
                }
                else if(flattype.equals("5 BHK")){
                    flattype = "5";
                }
                else if(flattype.equals("6 BHK")){
                    flattype = "6";
                }
                else if(flattype.equals("7 BHK")){
                    flattype = "7";
                }
                else if(flattype.equals("8 BHK")){
                    flattype = "8";
                }
                else if(flattype.equals("9 BHK")){
                    flattype = "9";
                }
                else if(flattype.equals("10 BHK")){
                    flattype = "10";
                }
                else if(flattype.equals("11 BHK")){
                    flattype = "11";
                }
                else if(flattype.equals("Select")){
                    flattype = "";
                }


                //qualityrating
                if(qualityratingspinget.equals("1 Star")){
                    qualityratingspinget = "1";
                }
                else if(qualityratingspinget.equals("2 Star")){
                    qualityratingspinget = "2";
                }
                else if(qualityratingspinget.equals("3 Star")){
                    qualityratingspinget = "3";
                }
                else if(qualityratingspinget.equals("4 Star")){
                    qualityratingspinget = "4";
                }
                else if(qualityratingspinget.equals("5 Star")){
                    qualityratingspinget = "5";
                }
                else if(qualityratingspinget.equals("6 Star")){
                    qualityratingspinget = "6";
                }
                else if(qualityratingspinget.equals("7 Star")){
                    qualityratingspinget = "7";
                }
                else if(qualityratingspinget.equals("8 Star")){
                    qualityratingspinget = "8";
                }
                else if(qualityratingspinget.equals("9 Star")){
                    qualityratingspinget = "9";
                }
                else if(qualityratingspinget.equals("10 Star")){
                    qualityratingspinget = "10";
                }

                else if(qualityratingspinget.equals("Select")){
                    qualityratingspinget = "";
                }




        //
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ListingData.getInstance(getApplicationContext()).getinstance().equals("Add")){
                    addproperty();
                }else{
                    updateproperty();
                }

            }
        });









    }

    private void updateproperty() {
        final String propertyid = ListingData.getInstance(getApplicationContext()).getpropertyid();

        System.out.println("Property id :::"+  ListingData.getInstance(getApplicationContext()).getimagepresent());
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, updateproperty, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                System.out.println("response ::::: "+ response);
                progressDialog.dismiss();
                try {
                    JSONObject obj = new JSONObject(response);
                    if(obj.getBoolean("error")){
                        showmessage(obj.getString("message"));
                    }else{
                        showmessage(obj.getString("message"));
                        ListingData.getInstance(getApplicationContext()).Logout();
                        startActivity(new Intent(PropertyProfileInfo.this, MyPropertyActivity.class));


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PropertyProfileInfo.this,"Something Went Wrong",Toast.LENGTH_SHORT).show();
                Log.d("VOLLEY", String.valueOf(error));
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                SmsData smsData = new SmsData();
                Map<String, String> params = new HashMap<>();
                params.put("header", smsData.token);

                params.put("usertype", usertype);
                params.put("type", type);
                params.put("category", category);
                params.put("categorytype", categorytype);
                params.put("agrement", agrement);
                params.put("pgavaliablefor", pgavaliablefor);
                params.put("pgshareprivate", pgshareprivate);
                params.put("sharingspinnumber", sharingspinnumber);
                params.put("propertylistfor", propertylistfor);
                params.put("willing", willing);
                params.put("pgsuitablefor", pgsuitablefor);
                params.put("state", state);
                params.put("city", city);
                params.put("locality", locality);
                params.put("projectname", projectname);
                params.put("address", address);
                params.put("unit1", unit1);
                params.put("unit2", unit2);
                params.put("unit3", unit3);
                params.put("availabilitydate", availabilitydate);
                params.put("otherroom", otherroom);
                params.put("homethings", homethings);
                params.put("builtupa", builtupa);
                params.put("carpeta", carpeta);
                params.put("plota", plota);
                params.put("bed", bed);
                params.put("bath", bath);
                params.put("balconie", balconie);
                params.put("totalfloorget", totalfloorget);
                params.put("reservedpark", reservedpark);
                params.put("availabilityspinget", availabilityspinget);
                params.put("ageofpropertyspinget", ageofpropertyspinget);
                params.put("furnishedspinget", furnishedspinget);
                params.put("possessionbyspinget", possessionbyspinget);
                params.put("noofroomspinget", noofroomspinget);
                params.put("qualityratingspinget", qualityratingspinget);
                params.put("floorallowedspinget", floorallowedspinget);
                params.put("roomavailabespinget", roomavailabespinget);
                params.put("priceget", priceget);
                params.put("maintenanceget", maintenanceget);
                params.put("bookingamountget", bookingamountget);
                params.put("durationofrentagget", durationofrentagget);
                params.put("monthofnoticeget", monthofnoticeget);
                params.put("securitydepositspinget", securitydepositspinget);
                params.put("maintenancemonthlyspinget", maintenancemonthlyspinget);
                params.put("ownweshipspinget", ownweshipspinget);
                params.put("typeoffood", typeoffood);
                params.put("pricestep", pricestep);
                params.put("facilityincluded", facilityincluded);
                params.put("weekdays", weekdays);
                params.put("weeends", weeends);
                params.put("earlylivingchargesget", earlylivingchargesget);
                params.put("contractdurationget", contractdurationget);
                params.put("annualduespayableget", annualduespayableget);
                params.put("depositeaget", depositeaget);
                params.put("facingspinget", facingspinget);
                params.put("unitspinget", unitspinget);
                params.put("typeofflooringspinget", typeofflooringspinget);
                params.put("powerbackupspinget", powerbackupspinget);
                params.put("lasttimespinget", lasttimespinget);
                params.put("pet", pet);
                params.put("visiter", visiter);
                params.put("smoking", smoking);
                params.put("alcohol", alcohol);
                params.put("event", event);
                params.put("anemitiesitem", anemitiesitem);
                params.put("moreanemitiesitem", moreanemitiesitem);
                params.put("watersourceitem", watersourceitem);
                params.put("overlookingitem", overlookingitem);
                params.put("somefeatureitem", somefeatureitem);
                params.put("byersitem", byersitem);
                params.put("timeitems", timeitems);
                params.put("widthfacingget", widthfacingget);
                params.put("descriptionget", descriptionget);
                params.put("boundrywall", boundrywall);
                params.put("flattype", flattype);
                params.put("user_id", user_id);
                params.put("propertyid", propertyid);
                params.put("image", image );
                params.put("imagepresent", imagepresent );
                params.put("rera", getrera );
                params.put("mobile", number1.getText().toString().trim());
                params.put("latlong", getlatlong);


                params.put("flattypenew", spiFlatTypeNew);
                params.put("studioaprtnos", edtStudioAprtNo);
                params.put("penthousenos", edtPentHouseNo);
                params.put("rowhousenos", edtRowHouseNo);
                params.put("duplexnos", edtDuplexNo);
                params.put("shopnos", edtShopNo);
                params.put("commercialspacenos", edtCommercialSpaceNo);
                params.put("bankloan", tvBankLoan);



                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(PropertyProfileInfo.this).addToRequestQueue(stringRequest);


    }


    public void addproperty(){

//                String num2="";
//                num2 = number2.getText().toString().trim();


            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.propertyadd, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    System.out.println("response ::::: "+ response);
                    progressDialog.dismiss();
                    try {
                        JSONObject obj = new JSONObject(response);
                        if(obj.getBoolean("error")){
                            showmessage(obj.getString("message"));
                        }else{
                            showmessage(obj.getString("message"));
                            sendsubscription();
                            ListingData.getInstance(getApplicationContext()).Logout();
                            startActivity(new Intent(PropertyProfileInfo.this, MainActivity.class));



                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(PropertyProfileInfo.this,"Something Went Wrong",Toast.LENGTH_SHORT).show();
                    Log.d("VOLLEY", String.valueOf(error));
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    SmsData smsData = new SmsData();
                    Map<String, String> params = new HashMap<>();
                    params.put("header", smsData.token);

                    params.put("usertype", usertype);
                    params.put("type", type);
                    params.put("category", category);
                    params.put("categorytype", categorytype);
                    params.put("agrement", agrement);
                    params.put("pgavaliablefor", pgavaliablefor);
                    params.put("pgshareprivate", pgshareprivate);
                    params.put("sharingspinnumber", sharingspinnumber);
                    params.put("propertylistfor", propertylistfor);
                    params.put("willing", willing);
                    params.put("pgsuitablefor", pgsuitablefor);
                    params.put("state", state);
                    params.put("city", city);
                    params.put("locality", locality);
                    params.put("projectname", projectname);
                    params.put("address", address);
                    params.put("unit1", unit1);
                    params.put("unit2", unit2);
                    params.put("unit3", unit3);
                    params.put("availabilitydate", availabilitydate);
                    params.put("otherroom", otherroom);
                    params.put("homethings", homethings);
                    params.put("builtupa", builtupa);
                    params.put("carpeta", carpeta);
                    params.put("plota", plota);
                    params.put("bed", bed);
                    params.put("bath", bath);
                    params.put("balconie", balconie);
                    params.put("totalfloorget", totalfloorget);
                    params.put("reservedpark", reservedpark);
                    params.put("availabilityspinget", availabilityspinget);
                    params.put("ageofpropertyspinget", ageofpropertyspinget);
                    params.put("furnishedspinget", furnishedspinget);
                    params.put("possessionbyspinget", possessionbyspinget);
                    params.put("noofroomspinget", noofroomspinget);
                    params.put("qualityratingspinget", qualityratingspinget);
                    params.put("floorallowedspinget", floorallowedspinget);
                    params.put("roomavailabespinget", roomavailabespinget);
                    params.put("priceget", priceget);
                    params.put("maintenanceget", maintenanceget);
                    params.put("bookingamountget", bookingamountget);
                    params.put("durationofrentagget", durationofrentagget);
                    params.put("monthofnoticeget", monthofnoticeget);
                    params.put("securitydepositspinget", securitydepositspinget);
                    params.put("maintenancemonthlyspinget", maintenancemonthlyspinget);
                    params.put("ownweshipspinget", ownweshipspinget);
                    params.put("typeoffood", typeoffood);
                    params.put("pricestep", pricestep);
                    params.put("facilityincluded", facilityincluded);
                    params.put("weekdays", weekdays);
                    params.put("weeends", weeends);
                    params.put("earlylivingchargesget", earlylivingchargesget);
                    params.put("contractdurationget", contractdurationget);
                    params.put("annualduespayableget", annualduespayableget);
                    params.put("depositeaget", depositeaget);
                    params.put("facingspinget", facingspinget);
                    params.put("unitspinget", unitspinget);
                    params.put("typeofflooringspinget", typeofflooringspinget);
                    params.put("powerbackupspinget", powerbackupspinget);
                    params.put("lasttimespinget", lasttimespinget);
                    params.put("pet", pet);
                    params.put("visiter", visiter);
                    params.put("smoking", smoking);
                    params.put("alcohol", alcohol);
                    params.put("event", event);
                    params.put("anemitiesitem", anemitiesitem);
                    params.put("moreanemitiesitem", moreanemitiesitem);
                    params.put("watersourceitem", watersourceitem);
                    params.put("overlookingitem", overlookingitem);
                    params.put("somefeatureitem", somefeatureitem);
                    params.put("byersitem", byersitem);
                    params.put("timeitems", timeitems);
                    params.put("widthfacingget", widthfacingget);
                    params.put("descriptionget", descriptionget);
                    params.put("boundrywall", boundrywall);
                    params.put("flattype", flattype);
                    params.put("user_id", user_id);
                    params.put("image", image );
                    params.put("imagepresent", imagepresent );
                    params.put("rera", getrera );
                    params.put("mobile", number1.getText().toString().trim());
                    params.put("latlong", getlatlong);

                    params.put("flattypenew", spiFlatTypeNew);
                    params.put("studioaprtnos", edtStudioAprtNo);
                    params.put("penthousenos", edtPentHouseNo);
                    params.put("rowhousenos", edtRowHouseNo);
                    params.put("duplexnos", edtDuplexNo);
                    params.put("shopnos", edtShopNo);
                    params.put("commercialspacenos", edtCommercialSpaceNo);
                    params.put("bankloan", tvBankLoan);


                    return params;
                }
            };
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            VolleySingleton.getInstance(PropertyProfileInfo.this).addToRequestQueue(stringRequest);


        }

    private void sendsubscription() {
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.sendsubscription, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                System.out.println("response ::::: "+ response);
                progressDialog.dismiss();
                try {
                    JSONObject obj = new JSONObject(response);
                    if(obj.getBoolean("error")){
                        showmessage(obj.getString("message"));
                    }else{

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PropertyProfileInfo.this,"Something Went Wrong",Toast.LENGTH_SHORT).show();
                Log.d("VOLLEY", String.valueOf(error));
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                SmsData smsData = new SmsData();
                Map<String, String> params = new HashMap<>();
                params.put("header", smsData.token);
                params.put("userid", VolleySingleton.getInstance(getApplicationContext()).id());
                params.put("subscription", getsubscription);

                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(PropertyProfileInfo.this).addToRequestQueue(stringRequest);


    }

    private void showmessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
