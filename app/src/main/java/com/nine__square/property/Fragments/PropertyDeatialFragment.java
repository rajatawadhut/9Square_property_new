package com.nine__square.property.Fragments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.location.Address;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nine__square.property.Adapter.AnemitiesAdapter;
import com.nine__square.property.Adapter.ByersAdapter;
import com.nine__square.property.Adapter.QuestionAnswerAdapter;
import com.nine__square.property.Adapter.SimilarPropertyAdapter;
import com.nine__square.property.Adapter.ViewPager2Adapter;
import com.nine__square.property.Avtivities.CallBackForm;
import com.nine__square.property.Helper.ShortlistedHelper;
import com.nine__square.property.Models.AnemitiesDatamodel;
import com.nine__square.property.Models.ByersDatamodel;
import com.nine__square.property.Models.QuestionAnswerDataModel;
import com.nine__square.property.Models.SimilarPropertyDataModel;
import com.nine__square.property.R;
import com.nine__square.property.Utils.Endpoints;
import com.nine__square.property.Utils.ListingData;
import com.nine__square.property.Utils.SliderUtils2;
import com.nine__square.property.Utils.SmsData;
import com.nine__square.property.Utils.StorageSome;
import com.nine__square.property.Utils.VolleySingleton;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


public class PropertyDeatialFragment extends Fragment {

    ViewPager propertyimage;
    private ProgressDialog progressDialog;
    EditText question;
    Button sendquestion;
    ImageView call_button, share_button;
    TextView fname1,lname1,ownerpp1,cancel;
    String fname2="", lname2="", ownerpp2="";
    TextView rera,ageofproperty,qualityrating, noofroom,price,listingfor,categoryt, flattypet,
            categorytypet, availability, area, areaunit,bedroom, bathroom, balconies, addresst,
            floor,possessionby, ownership, furnishing, parking,gatedcommunity, taxcharges,
            builtuparea, builtupareaunit, carpetarea, carpetareaunit,avalabilitydate,flooring,reservedparkt,
            wheelchairfriendly, rentaggrement, monthofnotice, facing,widthfaceing, widthfacingunit,
            brokerageprice, brokeragepricers,depositeprice, depositepricers, powerbackup,description, projectnamet;
    RecyclerView  anemities, uniqthings, faq, similarproperty;
    LinearLayout  card523, card52, similar9,rera9,addresslayout9,ageofproperty9,qualityrating9, noofroom9, flattypet9, categorytypet9, availability9, floor9, possessionby9, ownership9, furnishing9,bedroom9, bathroom9, balconies9, parking9, gatedcommunity9, taxcharges9, builtuparea9, carpetarea9, avalabilitydate9, flooring9, reservedparkt9, wheelchairfriendly9, aggrement9, monthofnotice9, facing9, widthfaceing9, brokerageprice9, depositeprice9, powerbackup9, projectname9;
    CardView locationlayout;

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
    ArrayList<String> moreanemitiesitem2 = new ArrayList<>();
    ArrayList<String> moreanemitiesitem3;
    ArrayList<String> homethings2 = new ArrayList<>();
    ArrayList<String> homethings3;

    List<Address> addresslist1 = null;
    LatLng latLng2;


    Dialog settingsDialog;
    ImageView nointernet;







    String id="",usertype = "", type = "", category = "",
            categorytype = "", agrement = "", pgavaliablefor = "",
            pgshareprivate = "",
            sharingspinnumber = "", propertylistfor = "", willing = "", pgsuitablefor = "",state= "", city = "", locality = "", projectname = "", address = "",
            unit1 = "",unit2 = "",unit3 = "",availabilitydate = "", otherroom = "", homethings = "", builtupa = "", carpeta = "", plota = "", bed = "",
            bath = "", balconie = "", totalfloorget = "", reservedpark = "", availabilityspinget = "", ageofpropertyspinget = "",
            furnishedspinget = "",possessionbyspinget = "",
            noofroomspinget = "", qualityratingspinget = "", floorallowedspinget = "", roomavailabespinget = "", priceget = "", maintenanceget = "",
            bookingamountget = "",
            durationofrentagget = "", monthofnoticeget = "",  securitydepositspinget = "", maintenancemonthlyspinget = "",
            ownweshipspinget = "",
            typeoffood = "", pricestep = "", facilityincluded = "", weekdays = "", weeends = "", earlylivingchargesget = "", contractdurationget = "",
            annualduespayableget = "",
            depositeaget = "", facingspinget = "", unitspinget = "", typeofflooringspinget = "", powerbackupspinget = "", lasttimespinget = "",
            pet = "", visiter = "", smoking = "",
            alcohol = "", event = "", anemitiesitem = "", moreanemitiesitem = "", watersourceitem = "", overlookingitem = "",
            somefeatureitem = "", byersitem = "", timeitems = "",
            widthfacingget = "", descriptionget = "", boundrywall = "",flattype = "", user_id = "", propertyid = "", getrera="",
            newprice="", image="", shortlistedvalue="", value ="", mobile ="" , reg_date = "", fname = "", lname = "", latlong = "", email ="";


    private List<SimilarPropertyDataModel> similarPropertyDataModels;
    private SimilarPropertyAdapter similarPropertyAdapter;

    private List<QuestionAnswerDataModel> questionAnswerDataModels;
    private QuestionAnswerAdapter questionAnswerAdapter;



    String datanull;
    LinearLayout faqlay;




    private List<AnemitiesDatamodel> anemitiesDatamodels;
    private List<ByersDatamodel> byersDatamodels;
    private AnemitiesAdapter anemitiesAdapter;
    private ByersAdapter byersAdapter;

    String anemitiesmodify, abc;
    String moreanemitiesmodify;
    ArrayList<String> anemetiesmodi;
    ArrayList<String> moreanemetiesmodi;


    String byersmodify, byersabc;
    String byerssmodify;
    ArrayList<String> byerssmodi;
    ImageView  heartselect, shareselect;

    RequestQueue rq;

    List<SliderUtils2> sliderImg;
    LinearLayout  hidequestion;


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_property_deatial, container, false);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle("Property Details");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
                ListingData.getInstance(getContext()).Logout();
            }
        });

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(true);


        settingsDialog = new Dialog(getContext());
        settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        settingsDialog.setContentView(getLayoutInflater().inflate(R.layout.image_layout
                , null));
        settingsDialog.setCancelable(false);


        cancel = settingsDialog.findViewById(R.id.cancel);
        nointernet = settingsDialog.findViewById(R.id.nointernet);


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingsDialog.dismiss();
            }
        });




        rq = Volley.newRequestQueue(getContext());
        sliderImg = new ArrayList<>();


        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        usertype = ListingData.getInstance(getContext()).usertype();
        type = ListingData.getInstance(getContext()).type();
        category = ListingData.getInstance(getContext()).category();
        categorytype = ListingData.getInstance(getContext()).categorytype();
        agrement = ListingData.getInstance(getContext()).agrement();
        pgavaliablefor = ListingData.getInstance(getContext()).pavailabefor();
        pgshareprivate = ListingData.getInstance(getContext()).pgshareprivate();
        sharingspinnumber = ListingData.getInstance(getContext()).sharingspinnumber();
        propertylistfor = ListingData.getInstance(getContext()).propertylistfor();
        willing = ListingData.getInstance(getContext()).willing();
        pgsuitablefor = ListingData.getInstance(getContext()).pgsuitablefor();
        state = ListingData.getInstance(getContext()).state();
        city = ListingData.getInstance(getContext()).city();
        locality = ListingData.getInstance(getContext()).locality();
        projectname = ListingData.getInstance(getContext()).projectname();
        address = ListingData.getInstance(getContext()).address();
        unit1 = ListingData.getInstance(getContext()).unit1();
        unit2 = ListingData.getInstance(getContext()).unit2();
        unit3 = ListingData.getInstance(getContext()).unit3();
        availabilitydate = ListingData.getInstance(getContext()).availabilitydate();
        otherroom = ListingData.getInstance(getContext()).otherroom();
        homethings = ListingData.getInstance(getContext()).homethings();
        builtupa = ListingData.getInstance(getContext()).builtup();
        carpeta = ListingData.getInstance(getContext()).cartpeta();
        plota = ListingData.getInstance(getContext()).plota();
        bed = ListingData.getInstance(getContext()).bed();
        bath = ListingData.getInstance(getContext()).bath();
        balconie = ListingData.getInstance(getContext()).balcoine();
        totalfloorget = ListingData.getInstance(getContext()).totalfloorget();
        reservedpark = ListingData.getInstance(getContext()).reservedpark();
        availabilityspinget = ListingData.getInstance(getContext()).availabilityspinget();
        ageofpropertyspinget = ListingData.getInstance(getContext()).ageofpropertyspinget();
        furnishedspinget = ListingData.getInstance(getContext()).furnishedspinget();
        possessionbyspinget = ListingData.getInstance(getContext()).possessionbyspinget();
        noofroomspinget = ListingData.getInstance(getContext()).noofroomspinget();
        qualityratingspinget = ListingData.getInstance(getContext()).qualityratingspinget();
        floorallowedspinget = ListingData.getInstance(getContext()).floorallowedspinget();
        roomavailabespinget = ListingData.getInstance(getContext()).roomavailabespinget();
        priceget = ListingData.getInstance(getContext()).newprice();
        maintenanceget = ListingData.getInstance(getContext()).maintenanceget();
        bookingamountget = ListingData.getInstance(getContext()).bookingamountget();
        durationofrentagget = ListingData.getInstance(getContext()).durationofrentagget();
        monthofnoticeget = ListingData.getInstance(getContext()).monthofnoticeget();
        securitydepositspinget = ListingData.getInstance(getContext()).securitydepositspinget();
        maintenancemonthlyspinget = ListingData.getInstance(getContext()).maintenancemonthlyspinget();
        ownweshipspinget = ListingData.getInstance(getContext()).ownweshipspinget();
        typeoffood = ListingData.getInstance(getContext()).typeoffood();
        pricestep = ListingData.getInstance(getContext()).pricestep();
        facilityincluded = ListingData.getInstance(getContext()).facilityincluded();
        weekdays = ListingData.getInstance(getContext()).weekdays();
        weeends = ListingData.getInstance(getContext()).weeends();
        earlylivingchargesget = ListingData.getInstance(getContext()).earlylivingchargesget();
        contractdurationget = ListingData.getInstance(getContext()).contractdurationget();
        annualduespayableget = ListingData.getInstance(getContext()).annualduespayableget();
        depositeaget = ListingData.getInstance(getContext()).depositeaget();
        facingspinget = ListingData.getInstance(getContext()).facingspinget();
        unitspinget = ListingData.getInstance(getContext()).unitspinget();
        typeofflooringspinget = ListingData.getInstance(getContext()).typeofflooringspinget();
        powerbackupspinget = ListingData.getInstance(getContext()).powerbackupspinget();
        lasttimespinget = ListingData.getInstance(getContext()).lasttimespinget();
        pet = ListingData.getInstance(getContext()).pet();
        visiter = ListingData.getInstance(getContext()).visiter();
        smoking = ListingData.getInstance(getContext()).smoking();
        alcohol = ListingData.getInstance(getContext()).alcohol();
        event = ListingData.getInstance(getContext()).event();
        anemitiesitem = ListingData.getInstance(getContext()).anemitiesitem();
        moreanemitiesitem = ListingData.getInstance(getContext()).moreanemitiesitem();
        watersourceitem = ListingData.getInstance(getContext()).watersourceitem();
        overlookingitem = ListingData.getInstance(getContext()).overlookingitem();
        somefeatureitem = ListingData.getInstance(getContext()).somefeatureitem();
        byersitem = ListingData.getInstance(getContext()).byersitem();
        timeitems = ListingData.getInstance(getContext()).timeitems();
        widthfacingget = ListingData.getInstance(getContext()).widthfacingget();
        descriptionget = ListingData.getInstance(getContext()).descriptionget();
        boundrywall = ListingData.getInstance(getContext()).boundrywall();
        flattype = ListingData.getInstance(getContext()).flattypespinget();
        propertyid = ListingData.getInstance(getContext()).getpropertyid();
        value = ListingData.getInstance(getContext()).getshortlistedvalue();
        getrera = ListingData.getInstance(getContext()).getrera();
        image = ListingData.getInstance(getContext()).getimage();
        mobile = ListingData.getInstance(getContext()).getmobile1();

        ownerpp2 = ListingData.getInstance(getContext()).usertype();
        fname2 = ListingData.getInstance(getContext()).getfname();
        lname2 = ListingData.getInstance(getContext()).getlname();

        propertyimage = view.findViewById(R.id.propertyimage);
        getmultipleimages();


        System.out.println("Anemebebene :;" + anemitiesitem +"+++"+ byersitem+ "+++"+category);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimertask(), 5000, 3500);

//        Glide.with(getContext()).load(image).into(propertyimage);

        SliderUtils2 sliderUtils2 = new SliderUtils2(image,"");
        sliderImg.add(sliderUtils2);
        ViewPager2Adapter viewPager2Adapter = new ViewPager2Adapter(sliderImg, getContext());
        propertyimage.setAdapter(viewPager2Adapter);


        heartselect = view.findViewById(R.id.heartselect);
        shareselect = view.findViewById(R.id.shareselect);
        locationlayout = view.findViewById(R.id.locationlayout);
        card523 = view.findViewById(R.id.card523);
        card52 = view.findViewById(R.id.card52);

        share_button = view.findViewById(R.id.share_button);
        call_button = view.findViewById(R.id.call_button);





        share_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CallBackForm.class);
                intent.putExtra("propertyid", ListingData.getInstance(getContext()).getpropertyid());
                intent.putExtra("mobile", ListingData.getInstance(getContext()).getmobile1());
                intent.putExtra("email", ListingData.getInstance(getContext()).getlemail());
                intent.putExtra("fullname", ListingData.getInstance(getContext()).getfname()+" "+ListingData.getInstance(getContext()).getlname());
                intent.putExtra("instance", "share");
                getContext().startActivity(intent);

            }
        });

        call_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CallBackForm.class);
                intent.putExtra("propertyid", ListingData.getInstance(getContext()).getpropertyid());
                intent.putExtra("mobile", ListingData.getInstance(getContext()).getmobile1());
                intent.putExtra("email", ListingData.getInstance(getContext()).getlemail());
                intent.putExtra("fullname", ListingData.getInstance(getContext()).getfname()+" "+ListingData.getInstance(getContext()).getlname());
                intent.putExtra("instance", "view");
                getContext().startActivity(intent);
            }
        });



        if(value != null) {
            if (value.equals("1")) {
                System.out.println("yesssssss");
                heartselect.setColorFilter(Color.parseColor("#FFC107"), PorterDuff.Mode.SRC_IN);
            } else {
                System.out.println("nooooo");

                heartselect.setColorFilter(null);
            }
        }
        heartselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("dshortlitedvalue ::"+ value);

                StorageSome.getInstance(getActivity()).setreload("1");

                if(value.equals("0")){

                    ListingData.getInstance(getContext()).shortlistedvalue("1");
                    value = "1";


                    heartselect.setColorFilter(Color.parseColor("#FFC107"), PorterDuff.Mode.SRC_IN);



                    String userid = VolleySingleton.getInstance(getContext()).id();
                    String propertyid = ListingData.getInstance(getContext()).getpropertyid();
                    String shortlistedvalue = "1";


                    ShortlistedHelper shortlistedHelper = new ShortlistedHelper(userid,propertyid,shortlistedvalue);
                    shortlistedHelper.addshortlisted();

                    final ProgressDialog dialog = new ProgressDialog(getContext());
                    dialog.setCancelable(false);
                    dialog.setMessage("Please wait.....");
                    dialog.show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            Toast.makeText(getContext(),"Shortlisted Successfully",Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }
                    }, 1300);



                }else {
                    heartselect.setColorFilter(null);


                    ListingData.getInstance(getContext()).shortlistedvalue("0");
                    value = "0";
                    String userid = VolleySingleton.getInstance(getContext()).id();
                    String propertyid = ListingData.getInstance(getContext()).getpropertyid();
                    String shortlistedvalue = "0";


                    ShortlistedHelper shortlistedHelper = new ShortlistedHelper(userid,propertyid,shortlistedvalue);
                    shortlistedHelper.addshortlisted();


                    final ProgressDialog dialog = new ProgressDialog(getContext());
                    dialog.setCancelable(false);
                    dialog.setMessage("Please wait.....");
                    dialog.show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            Toast.makeText(getContext(),"Remove Shortlisted Successfully",Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }
                    }, 1300);



                }
            }
        });


//        Glide.with(getActivity()).load(ListingData.getInstance(getContext()).getimage()).into(propertyimage);

        hidequestion = view.findViewById(R.id.hidequestion);

        question = view.findViewById(R.id.question);
        sendquestion = view.findViewById(R.id.sendquestion);
        faqlay = view.findViewById(R.id.faqlay);


        if(VolleySingleton.getInstance(getContext()).isLogin()){
            heartselect.setVisibility(View.VISIBLE);
            hidequestion.setVisibility(View.VISIBLE);

        }else{
            heartselect.setVisibility(View.GONE);
            hidequestion.setVisibility(View.GONE);
        }



        price = view.findViewById(R.id.price);
        listingfor = view.findViewById(R.id.listingfor);
        categoryt = view.findViewById(R.id.category);
        flattypet = view.findViewById(R.id.flattype);
        categorytypet = view.findViewById(R.id.categorytype);
        availability = view.findViewById(R.id.availability);
        area = view.findViewById(R.id.area);
        areaunit = view.findViewById(R.id.areaunit);
        addresst = view.findViewById(R.id.address);
        floor = view.findViewById(R.id.floor);
        possessionby = view.findViewById(R.id.possessionby);
        ownership = view.findViewById(R.id.ownership);
        furnishing = view.findViewById(R.id.furnishing);
        parking = view.findViewById(R.id.parking);
        gatedcommunity = view.findViewById(R.id.gatedcommunity);
        taxcharges = view.findViewById(R.id.taxcharges);
        builtuparea = view.findViewById(R.id.builtuparea);
        builtupareaunit = view.findViewById(R.id.builtupareaunit);
        carpetarea = view.findViewById(R.id.carptarea);
        carpetareaunit = view.findViewById(R.id.carpetareaunit);
        avalabilitydate = view.findViewById(R.id.availabilitydate);
        flooring = view.findViewById(R.id.flooring);
        reservedparkt = view.findViewById(R.id.reserverpark);
        wheelchairfriendly = view.findViewById(R.id.wheelchairfriendly);
        rentaggrement = view.findViewById(R.id.agremment);
        monthofnotice = view.findViewById(R.id.monthofnotice);
        facing = view.findViewById(R.id.facing);
        widthfaceing = view.findViewById(R.id.widthfaceing);
        widthfacingunit = view.findViewById(R.id.widthfaceingunit);
        brokerageprice = view.findViewById(R.id.brokerageprice);
        brokeragepricers = view.findViewById(R.id.brokeragepricers);
        depositeprice = view.findViewById(R.id.depositpeice);
        depositepricers = view.findViewById(R.id.depositepeicers);
        powerbackup = view.findViewById(R.id.powerbackup);
        description = view.findViewById(R.id.description);
        projectnamet = view.findViewById(R.id.projectname);
        bathroom = view.findViewById(R.id.bathroom);
        bedroom = view.findViewById(R.id.bedroom);
        balconies = view.findViewById(R.id.balconie);
        noofroom = view.findViewById(R.id.noofroom);
        qualityrating = view.findViewById(R.id.qualityrating);
        ageofproperty = view.findViewById(R.id.ageofproperty);
        rera = view.findViewById(R.id.rera);

        ownerpp1 = view.findViewById(R.id.usertype);
        fname1 = view.findViewById(R.id.fname);










        flattypet9 = view.findViewById(R.id.flattype9);
        categorytypet9 = view.findViewById(R.id.categorytype9);
        availability9 = view.findViewById(R.id.availability9);
        floor9 = view.findViewById(R.id.floor9);
        possessionby9 = view.findViewById(R.id.possessionby9);
        ownership9 = view.findViewById(R.id.ownership9);
        furnishing9 = view.findViewById(R.id.furnishing9);
        parking9 = view.findViewById(R.id.parking9);
        gatedcommunity9 = view.findViewById(R.id.gatedcommunity9);
        taxcharges9 = view.findViewById(R.id.taxcharges9);
        builtuparea9 = view.findViewById(R.id.builtuparea9);
        carpetarea9 = view.findViewById(R.id.carpetarea9);
        avalabilitydate9 = view.findViewById(R.id.availabilitydate9);
        flooring9 = view.findViewById(R.id.flooring9);
        reservedparkt9 = view.findViewById(R.id.reserverpark9);
        wheelchairfriendly9 = view.findViewById(R.id.wheelchairfriendly9);
        aggrement9 = view.findViewById(R.id.agremment9);
        monthofnotice9 = view.findViewById(R.id.monthofnotice9);
        facing9 = view.findViewById(R.id.facing9);
        widthfaceing9 = view.findViewById(R.id.widthfaceing9);
        brokerageprice9 = view.findViewById(R.id.brokerageprice9);
        depositeprice9 = view.findViewById(R.id.depositpeice9);
        powerbackup9 = view.findViewById(R.id.powerbackup9);
        projectname9 = view.findViewById(R.id.projectname9);
        bedroom9 = view.findViewById(R.id.bedroom9);
        bathroom9 = view.findViewById(R.id.bathromm9);
        balconies9 = view.findViewById(R.id.balconies9);
        noofroom9 = view.findViewById(R.id.noofroom9);
        qualityrating9 = view.findViewById(R.id.qualityrating9);
        ageofproperty9 = view.findViewById(R.id.ageofproperty9);
        addresslayout9 = view.findViewById(R.id.addresslayout9);
        rera9 = view.findViewById(R.id.rera9);
        similar9 = view.findViewById(R.id.similar9);


        flattypet9.setVisibility(View.GONE);
        categorytypet9.setVisibility(View.GONE);
        availability9.setVisibility(View.GONE);
        floor9.setVisibility(View.GONE);
        possessionby9.setVisibility(View.GONE);
        ownership9.setVisibility(View.GONE);
        furnishing9.setVisibility(View.GONE);
        parking9.setVisibility(View.GONE);
        gatedcommunity9.setVisibility(View.GONE);
        taxcharges9.setVisibility(View.GONE);
        builtuparea9.setVisibility(View.GONE);
        carpetarea9.setVisibility(View.GONE);
        avalabilitydate9.setVisibility(View.GONE);
        flooring9.setVisibility(View.GONE);
        reservedparkt9.setVisibility(View.GONE);
        wheelchairfriendly9.setVisibility(View.GONE);
        aggrement9.setVisibility(View.GONE);
        monthofnotice9.setVisibility(View.GONE);
        facing9.setVisibility(View.GONE);
        widthfaceing9.setVisibility(View.GONE);
        brokerageprice9.setVisibility(View.GONE);
        depositeprice9.setVisibility(View.GONE);
        powerbackup9.setVisibility(View.GONE);
        bedroom9.setVisibility(View.GONE);
        bathroom9.setVisibility(View.GONE);
        balconies9.setVisibility(View.GONE);
        noofroom9.setVisibility(View.GONE);
        qualityrating9.setVisibility(View.GONE);
        ageofproperty9.setVisibility(View.GONE);
        rera9.setVisibility(View.GONE);






//        RecyclerView  anemities, uniqthings, faq;

        anemities = view.findViewById(R.id.anemities);
        uniqthings = view.findViewById(R.id.uniqthing);
        faq = view.findViewById(R.id.faq);
        similarproperty = view.findViewById(R.id.similarproperty);

        anemitiesDatamodels = new ArrayList<>();
        byersDatamodels = new ArrayList<>();
        similarPropertyDataModels = new ArrayList<>();
        questionAnswerDataModels = new ArrayList<>();



        if(priceget.isEmpty()){
            price.setText("N/A");
        }else{
            price.setText(getContext().getResources().getString(R.string.rupees)+ " " +priceget);
        }

        if(propertylistfor.isEmpty()){
            listingfor.setText("N/A");
        }else{
            listingfor.setText(propertylistfor);
        }

        if(category.isEmpty()){
            categoryt.setText(category);
        }else{
            categoryt.setText(category);
        }

        if(flattype.isEmpty()){
            flattypet.setText("N/A");
        }else{
            flattypet.setText(flattype);
        }

        if(categorytype.isEmpty()){
            categorytypet.setText("N/A");
        }else{
            categorytypet.setText(categorytype);
        }

        if (availabilityspinget.isEmpty()){
            availability.setText("N/A");
        }else{
            availability.setText(availabilityspinget);
        }

        if(plota.isEmpty()){
            area.setText("N/A");
            areaunit.setText("");
        }else{
            area.setText(plota);
            areaunit.setText(unit1);
        }

        if(address.isEmpty()){
            addresst.setText("N/A");
        }else{
            addresst.setText(address);
        }

        if(totalfloorget.isEmpty()){
            floor.setText("N/A");
        }else{
            floor.setText(totalfloorget);
        }


        if(possessionbyspinget.isEmpty()){
            possessionby.setText("N/A");
        }else{
            possessionby.setText(possessionbyspinget);
        }


        if(ownweshipspinget.isEmpty()){
            ownership.setText("N/A");
        }else{
            ownership.setText(ownweshipspinget);
        }

        if(furnishedspinget.isEmpty()){
            furnishing.setText("N/A");
        }else{
            furnishing.setText(furnishedspinget);
        }

        if(reservedpark.isEmpty()){
            parking.setText("N/A");
        }else{
            parking.setText(reservedpark);
        }

        if(builtupa.isEmpty()){
            builtuparea.setText("N/A");
            builtupareaunit.setText("");

        }else{
            builtuparea.setText(builtupa);
            builtupareaunit.setText(unit2);

        }

        if(carpeta.isEmpty()){
            carpetarea.setText("N/A");
            carpetareaunit.setText("");
        }else{
            carpetarea.setText(carpeta);
            carpetareaunit.setText(unit3);
        }

        if(availabilitydate.isEmpty()){
            avalabilitydate.setText("N/A");
        }else{
            avalabilitydate.setText(availabilitydate);
        }

        if(typeofflooringspinget.isEmpty()){
            flooring.setText("N/A");
        }else{
            flooring.setText(typeofflooringspinget);
        }

        if(reservedpark.isEmpty()){
            reservedparkt.setText("N/A");
        }
        else{
            reservedparkt.setText(reservedpark);
        }

//        wheelchairfriendly.setText();
//        rentaggrement.setText();

        if(monthofnoticeget.isEmpty()){
            monthofnotice.setText("N/A");
        }else{
            monthofnotice.setText(monthofnoticeget);
        }

        if(facingspinget.isEmpty()){
            facing.setText("N/A");
        }else{
            facing.setText(facingspinget);
        }


        if(widthfacingget.isEmpty()){
            widthfaceing.setText("N/A");
            widthfacingunit.setText("");
        }else{
            widthfaceing.setText(widthfacingget);
//            widthfacingunit.setText(unitspinget);
            widthfacingunit.setText("Meter");
        }
//        brokerageprice.setText();

        if(bookingamountget.isEmpty()){
            depositeprice.setText("N/A");
            depositepricers.setVisibility(View.GONE);
        }else{
            depositeprice.setText(bookingamountget);
            depositepricers.setVisibility(View.VISIBLE);

        }

        if(powerbackupspinget.isEmpty()){
            powerbackup.setText("N/A");
        }else{
            powerbackup.setText(powerbackupspinget);
        }

        if(descriptionget.isEmpty()){
            description.setText("N/A");
        }else{
            description.setText(descriptionget);
        }

        if(projectname.isEmpty()){
            projectname9.setVisibility(View.GONE);
        }else{
            projectnamet.setText(projectname);
        }


        if(bed.isEmpty()){
            bedroom.setText("N/A");
        }else{
            bedroom.setText(bed);
        }

        if(bath.isEmpty()){
            bathroom.setText("N/A");
        }else{
            bathroom.setText(bath);
        }

        if(balconie.isEmpty()){
            balconies.setText("N/A");
        }else{
            balconies.setText(balconie);
        }

        if(noofroomspinget.isEmpty()){
            noofroom.setText("N/A");
        }else{
            noofroom.setText(noofroomspinget);
        }

        if(qualityratingspinget.isEmpty()){
            qualityrating.setText("N/A");
        }else{
            qualityrating.setText(qualityratingspinget);
        }

        if(ageofpropertyspinget.isEmpty()){
            ageofproperty.setText("N/A");
        }else{
            ageofproperty.setText(ageofpropertyspinget);
        }

        if(getrera.isEmpty()){
            rera.setText("N/A");
        }else{
            rera.setText(getrera);
        }



        if(ownerpp2.isEmpty()){
            ownerpp1.setText("N/A");
        }else{
            ownerpp1.setText(ownerpp2);
        }

        if(fname2.isEmpty()){
            fname1.setText("N/A");
        }else{
            fname1.setText(fname2+ " "+ lname2);
        }




        if(VolleySingleton.getInstance(getContext()).isLogin()){
            addresslayout9.setVisibility(View.VISIBLE);
        }else{
            addresslayout9.setVisibility(View.GONE);
        }





        anemitiesmodify = anemitiesitem.replace("[", "").replace("]", "");
        String resultflata = anemitiesmodify.replaceAll("^[\"']+|[\"']+$", "");

        moreanemitiesmodify = moreanemitiesitem.toString().replace("[", "").replace("]", "");
        String resultflatamore = moreanemitiesmodify.replaceAll("^[\"']+|[\"']+$", "");

        anemetiesmodi = new ArrayList<String>(Arrays.asList(resultflata.split(",")));
        moreanemetiesmodi = new ArrayList<String>(Arrays.asList(resultflatamore.split(",")));



        if(anemetiesmodi != null){
            if(resultflata.length() == 0){
                card52.setVisibility(View.GONE);
            }
        }



//        propertyimage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Bundle bundle = new Bundle();
//                bundle.putString("img", ListingData.getInstance(getContext()).getimage()); // Put anything what you want
//
//                Fragment fragment = new PicZoomFragment();
//                fragment.setArguments(bundle);
//
//                getFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.containerfragment, fragment)
//                        .commit();
//            }
//        });


        locationlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String a = address.replace(", ", "%20").replace(" ", "%20").trim();




            if(VolleySingleton.getInstance(getContext()).isLogin()){
                if(address!= null || address.equals("")){
//                    Geocoder geocoder = new Geocoder(getContext());
//                    try {
//                        addresslist1 = geocoder.getFromLocationName(address,1);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//                    System.out.println("mappppp::: "+ );
//                    Address address2 = addresslist1.get(0);
//                    latLng2 = new LatLng(address2.getLatitude(), address2.getLongitude());


//                    String i = ListingData.getInstance(getContext()).address().replace(", ", "%20").replace(" ", "%20").trim();
                    String i = ListingData.getInstance(getContext()).getLatLog();


                    String url = "http://maps.google.co.in/maps?q="+ i;
                    System.out.println("mappppp::: "+ url );
                    Intent intent = new Intent(Intent.ACTION_VIEW,  Uri.parse(url));
                    startActivity(intent);




                }

            }else{
                showmessage("Please Login First...");
            }


            }
        });



        sendquestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getquestion, getmobile, getemail, getname;
                getemail = VolleySingleton.getInstance(getContext()).email();
                getmobile = VolleySingleton.getInstance(getContext()).mobile();
                getname = VolleySingleton.getInstance(getContext()).fname()+ " "+ VolleySingleton.getInstance(getContext()).lname();
                getquestion = question.getText().toString().trim();

                if(getquestion.isEmpty()){
                    showmessage("Please Enter Your Question...");
                }else{

                    sendquestionto_server(getquestion, getemail,getmobile,getname);
                }
            }
        });

        if (!resultflatamore.isEmpty()){
            for(int i = 0; i< moreanemetiesmodi.size(); i++){
                anemetiesmodi.add(moreanemetiesmodi.get(i));
            }
        }


        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        anemities.setLayoutManager(layoutManager);
        anemitiesAdapter = new AnemitiesAdapter(anemitiesDatamodels, getContext());
        anemities.setAdapter(anemitiesAdapter);


        for (int i = 0; i < anemetiesmodi.size(); i++) {

            if (i == 0) {
                abc = " " + anemetiesmodi.get(i);
            } else {
                abc = anemetiesmodi.get(i);
            }

            AnemitiesDatamodel anemitiesDatamodel = new AnemitiesDatamodel(abc);
            anemitiesDatamodels.add(anemitiesDatamodel);
            anemitiesAdapter.notifyDataSetChanged();


        }



            byersmodify = byersitem.toString().replace("[", "").replace("]", "");
            String resultbyers = byersmodify.replaceAll("^[\"']+|[\"']+$", "");
            byerssmodi = new ArrayList<String>(Arrays.asList(resultbyers.split(",")));

            RecyclerView.LayoutManager layoutManager2 = new GridLayoutManager(getContext(), 2);
            uniqthings.setLayoutManager(layoutManager2);
            byersAdapter = new ByersAdapter(byersDatamodels, getContext());
            uniqthings.setAdapter(byersAdapter);

        if(byerssmodi != null){
            if(resultbyers.length() == 0){
                card523.setVisibility(View.GONE);
            }
        }


        for (int i = 0; i < byerssmodi.size(); i++) {

                if (i == 0) {
                    byersabc = " " + byerssmodi.get(i);
                } else {
                    byersabc = byerssmodi.get(i);
                }

                System.out.println("Abbccc::; " + byersabc);


                ByersDatamodel byersDatamodel = new ByersDatamodel(byersabc);
                byersDatamodels.add(byersDatamodel);
                byersAdapter.notifyDataSetChanged();

            }



        RecyclerView.LayoutManager layoutManager4 = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        similarproperty.setLayoutManager(layoutManager4);
        similarPropertyAdapter = new SimilarPropertyAdapter(similarPropertyDataModels,getContext());
        similarproperty.setAdapter(similarPropertyAdapter);
        getsimilarproperty();


        RecyclerView.LayoutManager layoutManager3 = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        faq.setLayoutManager(layoutManager3);
        questionAnswerAdapter = new QuestionAnswerAdapter(questionAnswerDataModels,getContext());
        faq.setAdapter(questionAnswerAdapter);

        questionanswer();




        if(ListingData.getInstance(getContext()).category().equals("Offices")){

            flattypet9.setVisibility(View.GONE);
            categorytypet9.setVisibility(View.VISIBLE);
            availability9.setVisibility(View.VISIBLE);
            floor9.setVisibility(View.GONE);
            possessionby9.setVisibility(View.VISIBLE);
            if(ListingData.getInstance(getContext()).propertylistfor().equals("Sell")){
                ownership9.setVisibility(View.VISIBLE);
            }else{
                ownership9.setVisibility(View.GONE);
            }
            card523.setVisibility(View.GONE);
            furnishing9.setVisibility(View.GONE);
            parking9.setVisibility(View.GONE);
            gatedcommunity9.setVisibility(View.GONE);
            taxcharges9.setVisibility(View.GONE);
            builtuparea9.setVisibility(View.VISIBLE);
            carpetarea9.setVisibility(View.VISIBLE);
            avalabilitydate9.setVisibility(View.GONE);
            flooring9.setVisibility(View.GONE);
            reservedparkt9.setVisibility(View.GONE);
            wheelchairfriendly9.setVisibility(View.VISIBLE);
            aggrement9.setVisibility(View.GONE);
            monthofnotice9.setVisibility(View.GONE);
            facing9.setVisibility(View.GONE);
            widthfaceing9.setVisibility(View.GONE);
            brokerageprice9.setVisibility(View.GONE);
            depositeprice9.setVisibility(View.VISIBLE);
            powerbackup9.setVisibility(View.GONE);
            noofroom9.setVisibility(View.GONE);
            qualityrating9.setVisibility(View.GONE);

            bedroom9.setVisibility(View.GONE);
            bathroom9.setVisibility(View.VISIBLE);
            balconies9.setVisibility(View.GONE);
            rera9.setVisibility(View.GONE);




        }

        if(ListingData.getInstance(getContext()).category().equals("Retail")){

            flattypet9.setVisibility(View.GONE);
            categorytypet9.setVisibility(View.VISIBLE);
            availability9.setVisibility(View.VISIBLE);
            floor9.setVisibility(View.GONE);
            possessionby9.setVisibility(View.VISIBLE);

            if(ListingData.getInstance(getContext()).propertylistfor().equals("Sell")){
                ownership9.setVisibility(View.VISIBLE);
            }else{
                ownership9.setVisibility(View.GONE);
            }
            furnishing9.setVisibility(View.GONE);
            parking9.setVisibility(View.GONE);
            gatedcommunity9.setVisibility(View.GONE);
            taxcharges9.setVisibility(View.GONE);
            builtuparea9.setVisibility(View.VISIBLE);
            carpetarea9.setVisibility(View.VISIBLE);
            avalabilitydate9.setVisibility(View.GONE);
            flooring9.setVisibility(View.GONE);
            reservedparkt9.setVisibility(View.GONE);
            wheelchairfriendly9.setVisibility(View.VISIBLE);
            aggrement9.setVisibility(View.GONE);
            monthofnotice9.setVisibility(View.GONE);
            facing9.setVisibility(View.GONE);
            widthfaceing9.setVisibility(View.GONE);
            brokerageprice9.setVisibility(View.GONE);
            depositeprice9.setVisibility(View.VISIBLE);
            powerbackup9.setVisibility(View.GONE);
            bedroom9.setVisibility(View.GONE);
            bathroom9.setVisibility(View.VISIBLE);
            balconies9.setVisibility(View.VISIBLE);
            noofroom9.setVisibility(View.GONE);
            qualityrating9.setVisibility(View.GONE);
            rera9.setVisibility(View.GONE);


        }


        if(ListingData.getInstance(getContext()).category().equals("Land") && ListingData.getInstance(getContext()).type().equals("Commercial")){

            flattypet9.setVisibility(View.GONE);
            categorytypet9.setVisibility(View.VISIBLE);

            availability9.setVisibility(View.GONE);

            floor9.setVisibility(View.GONE);
            possessionby9.setVisibility(View.VISIBLE);
            if(ListingData.getInstance(getContext()).propertylistfor().equals("Sell")){
                ownership9.setVisibility(View.VISIBLE);
            }else{
                ownership9.setVisibility(View.GONE);
            }
            furnishing9.setVisibility(View.GONE);
            parking9.setVisibility(View.GONE);
            gatedcommunity9.setVisibility(View.GONE);
            taxcharges9.setVisibility(View.GONE);
            builtuparea9.setVisibility(View.VISIBLE);
            carpetarea9.setVisibility(View.VISIBLE);
            avalabilitydate9.setVisibility(View.GONE);
            flooring9.setVisibility(View.GONE);
            reservedparkt9.setVisibility(View.GONE);
            wheelchairfriendly9.setVisibility(View.GONE);
            aggrement9.setVisibility(View.GONE);
            monthofnotice9.setVisibility(View.GONE);
            facing9.setVisibility(View.VISIBLE);
            widthfaceing9.setVisibility(View.GONE);
            brokerageprice9.setVisibility(View.GONE);
            depositeprice9.setVisibility(View.VISIBLE);
            powerbackup9.setVisibility(View.GONE);
            bedroom9.setVisibility(View.GONE);
            bathroom9.setVisibility(View.GONE);
            balconies9.setVisibility(View.GONE);
            noofroom9.setVisibility(View.GONE);
            qualityrating9.setVisibility(View.GONE);
            rera9.setVisibility(View.VISIBLE);

        }

        if(ListingData.getInstance(getContext()).category().equals("Others") && ListingData.getInstance(getContext()).type().equals("Commercial")){
            flattypet9.setVisibility(View.GONE);
            categorytypet9.setVisibility(View.VISIBLE);
            availability9.setVisibility(View.VISIBLE);
            floor9.setVisibility(View.GONE);
            possessionby9.setVisibility(View.VISIBLE);
            if(ListingData.getInstance(getContext()).propertylistfor().equals("Sell")){
                ownership9.setVisibility(View.VISIBLE);
            }else{
                ownership9.setVisibility(View.GONE);
            }
            furnishing9.setVisibility(View.GONE);
            parking9.setVisibility(View.GONE);
            gatedcommunity9.setVisibility(View.GONE);
            taxcharges9.setVisibility(View.GONE);
            builtuparea9.setVisibility(View.VISIBLE);
            carpetarea9.setVisibility(View.VISIBLE);
            avalabilitydate9.setVisibility(View.GONE);
            flooring9.setVisibility(View.GONE);
            reservedparkt9.setVisibility(View.GONE);
            wheelchairfriendly9.setVisibility(View.GONE);
            aggrement9.setVisibility(View.GONE);
            monthofnotice9.setVisibility(View.GONE);
            facing9.setVisibility(View.VISIBLE);
            widthfaceing9.setVisibility(View.GONE);
            brokerageprice9.setVisibility(View.GONE);
            depositeprice9.setVisibility(View.VISIBLE);
            powerbackup9.setVisibility(View.GONE);
            bedroom9.setVisibility(View.GONE);
            bathroom9.setVisibility(View.GONE);
            balconies9.setVisibility(View.GONE);
            noofroom9.setVisibility(View.GONE);
            qualityrating9.setVisibility(View.GONE);
            rera9.setVisibility(View.GONE);

        }


        if(ListingData.getInstance(getContext()).category().equals("Industry") || ListingData.getInstance(getContext()).category().equals("Storage")){

            flattypet9.setVisibility(View.GONE);
            categorytypet9.setVisibility(View.VISIBLE);
            availability9.setVisibility(View.VISIBLE);
            floor9.setVisibility(View.GONE);
            possessionby9.setVisibility(View.VISIBLE);
            if(ListingData.getInstance(getContext()).propertylistfor().equals("Sell")){
                ownership9.setVisibility(View.VISIBLE);
            }else{
                ownership9.setVisibility(View.GONE);
            }
            furnishing9.setVisibility(View.GONE);
            parking9.setVisibility(View.GONE);
            gatedcommunity9.setVisibility(View.GONE);
            taxcharges9.setVisibility(View.GONE);
            builtuparea9.setVisibility(View.VISIBLE);
            carpetarea9.setVisibility(View.VISIBLE);
            avalabilitydate9.setVisibility(View.GONE);
            flooring9.setVisibility(View.GONE);
            reservedparkt9.setVisibility(View.GONE);
            wheelchairfriendly9.setVisibility(View.GONE);
            aggrement9.setVisibility(View.GONE);
            monthofnotice9.setVisibility(View.GONE);
            facing9.setVisibility(View.GONE);
            widthfaceing9.setVisibility(View.GONE);
            brokerageprice9.setVisibility(View.GONE);
            depositeprice9.setVisibility(View.VISIBLE);
            powerbackup9.setVisibility(View.GONE);
            bedroom9.setVisibility(View.GONE);
            bathroom9.setVisibility(View.VISIBLE);
            balconies9.setVisibility(View.GONE);
            noofroom9.setVisibility(View.GONE);
            qualityrating9.setVisibility(View.GONE);
            rera9.setVisibility(View.GONE);

        }


        if(ListingData.getInstance(getContext()).category().equals("Hospitality")){

            flattypet9.setVisibility(View.GONE);
            categorytypet9.setVisibility(View.VISIBLE);
            availability9.setVisibility(View.VISIBLE);
            floor9.setVisibility(View.GONE);
            possessionby9.setVisibility(View.VISIBLE);
            if(ListingData.getInstance(getContext()).propertylistfor().equals("Sell")){
                ownership9.setVisibility(View.VISIBLE);
            }else{
                ownership9.setVisibility(View.GONE);
            }
            furnishing9.setVisibility(View.GONE);
            parking9.setVisibility(View.GONE);
            gatedcommunity9.setVisibility(View.GONE);
            taxcharges9.setVisibility(View.GONE);
            builtuparea9.setVisibility(View.VISIBLE);
            carpetarea9.setVisibility(View.VISIBLE);
            avalabilitydate9.setVisibility(View.GONE);
            flooring9.setVisibility(View.GONE);
            reservedparkt9.setVisibility(View.GONE);
            wheelchairfriendly9.setVisibility(View.GONE);
            aggrement9.setVisibility(View.GONE);
            monthofnotice9.setVisibility(View.GONE);
            facing9.setVisibility(View.GONE);
            widthfaceing9.setVisibility(View.GONE);
            brokerageprice9.setVisibility(View.GONE);
            depositeprice9.setVisibility(View.VISIBLE);
            powerbackup9.setVisibility(View.GONE);
            bedroom9.setVisibility(View.GONE);
            bathroom9.setVisibility(View.VISIBLE);
            balconies9.setVisibility(View.VISIBLE);
            noofroom9.setVisibility(View.VISIBLE);
            qualityrating9.setVisibility(View.VISIBLE);
            rera9.setVisibility(View.GONE);

        }


        if(ListingData.getInstance(getContext()).category().equals("Apartment/Flat/Builder Floor") && ListingData.getInstance(getContext()).propertylistfor().equals("Sell")){

            flattypet9.setVisibility(View.VISIBLE);
            categorytypet9.setVisibility(View.VISIBLE);
            availability9.setVisibility(View.VISIBLE);
            floor9.setVisibility(View.VISIBLE);
            possessionby9.setVisibility(View.VISIBLE);
            ownership9.setVisibility(View.VISIBLE);
            furnishing9.setVisibility(View.GONE);
            parking9.setVisibility(View.VISIBLE);
            gatedcommunity9.setVisibility(View.GONE);
            taxcharges9.setVisibility(View.GONE);
            builtuparea9.setVisibility(View.VISIBLE);
            carpetarea9.setVisibility(View.VISIBLE);
            avalabilitydate9.setVisibility(View.GONE);
            flooring9.setVisibility(View.VISIBLE);
            reservedparkt9.setVisibility(View.VISIBLE);
            wheelchairfriendly9.setVisibility(View.GONE);
            aggrement9.setVisibility(View.GONE);
            monthofnotice9.setVisibility(View.GONE);
            facing9.setVisibility(View.VISIBLE);
            widthfaceing9.setVisibility(View.VISIBLE);
            brokerageprice9.setVisibility(View.GONE);
            depositeprice9.setVisibility(View.VISIBLE);
            powerbackup9.setVisibility(View.VISIBLE);
            bedroom9.setVisibility(View.VISIBLE);
            bathroom9.setVisibility(View.VISIBLE);
            balconies9.setVisibility(View.VISIBLE);
            noofroom9.setVisibility(View.GONE);
            qualityrating9.setVisibility(View.GONE);
            rera9.setVisibility(View.GONE);

        }

        if(ListingData.getInstance(getContext()).category().equals("House/Villa") && ListingData.getInstance(getContext()).propertylistfor().equals("Sell")){

            flattypet9.setVisibility(View.GONE);
            categorytypet9.setVisibility(View.VISIBLE);
            availability9.setVisibility(View.VISIBLE);
            floor9.setVisibility(View.VISIBLE);
            possessionby9.setVisibility(View.VISIBLE);
            ownership9.setVisibility(View.VISIBLE);
            furnishing9.setVisibility(View.GONE);
            parking9.setVisibility(View.VISIBLE);
            gatedcommunity9.setVisibility(View.GONE);
            taxcharges9.setVisibility(View.GONE);
            builtuparea9.setVisibility(View.VISIBLE);
            carpetarea9.setVisibility(View.VISIBLE);
            avalabilitydate9.setVisibility(View.GONE);
            flooring9.setVisibility(View.VISIBLE);
            reservedparkt9.setVisibility(View.VISIBLE);
            wheelchairfriendly9.setVisibility(View.GONE);
            aggrement9.setVisibility(View.GONE);
            monthofnotice9.setVisibility(View.GONE);
            facing9.setVisibility(View.VISIBLE);
            widthfaceing9.setVisibility(View.VISIBLE);
            brokerageprice9.setVisibility(View.GONE);
            depositeprice9.setVisibility(View.VISIBLE);
            powerbackup9.setVisibility(View.VISIBLE);
            bedroom9.setVisibility(View.VISIBLE);
            bathroom9.setVisibility(View.VISIBLE);
            balconies9.setVisibility(View.VISIBLE);
            noofroom9.setVisibility(View.GONE);
            qualityrating9.setVisibility(View.GONE);
            rera9.setVisibility(View.GONE);

        }
        if(ListingData.getInstance(getContext()).category().equals("Others") && ListingData.getInstance(getContext()).propertylistfor().equals("Sell") && ListingData.getInstance(getContext()).type().equals("Residential")){


            flattypet9.setVisibility(View.GONE);
            categorytypet9.setVisibility(View.VISIBLE);
            availability9.setVisibility(View.VISIBLE);
            floor9.setVisibility(View.GONE);
            possessionby9.setVisibility(View.VISIBLE);
            ownership9.setVisibility(View.VISIBLE);
            furnishing9.setVisibility(View.GONE);
            parking9.setVisibility(View.GONE);
            gatedcommunity9.setVisibility(View.GONE);
            taxcharges9.setVisibility(View.GONE);
            builtuparea9.setVisibility(View.GONE);
            carpetarea9.setVisibility(View.GONE);
            avalabilitydate9.setVisibility(View.GONE);
            flooring9.setVisibility(View.GONE);
            reservedparkt9.setVisibility(View.GONE);
            wheelchairfriendly9.setVisibility(View.GONE);
            aggrement9.setVisibility(View.GONE);
            monthofnotice9.setVisibility(View.GONE);
            facing9.setVisibility(View.VISIBLE);
            widthfaceing9.setVisibility(View.GONE);
            brokerageprice9.setVisibility(View.GONE);
            depositeprice9.setVisibility(View.VISIBLE);
            powerbackup9.setVisibility(View.VISIBLE);
            bedroom9.setVisibility(View.GONE);
            bathroom9.setVisibility(View.GONE);
            balconies9.setVisibility(View.GONE);
            noofroom9.setVisibility(View.GONE);
            qualityrating9.setVisibility(View.GONE);
        }

        if(ListingData.getInstance(getContext()).category().equals("Apartment/Flat/Builder Floor") && ListingData.getInstance(getContext()).propertylistfor().equals("Rent")){


            flattypet9.setVisibility(View.VISIBLE);
            categorytypet9.setVisibility(View.VISIBLE);
            availability9.setVisibility(View.VISIBLE);
            floor9.setVisibility(View.VISIBLE);
            possessionby9.setVisibility(View.VISIBLE);
            ownership9.setVisibility(View.GONE);
            furnishing9.setVisibility(View.VISIBLE);
            parking9.setVisibility(View.VISIBLE);
            gatedcommunity9.setVisibility(View.GONE);
            taxcharges9.setVisibility(View.GONE);
            builtuparea9.setVisibility(View.VISIBLE);
            carpetarea9.setVisibility(View.VISIBLE);
            avalabilitydate9.setVisibility(View.VISIBLE);
            flooring9.setVisibility(View.VISIBLE);
            reservedparkt9.setVisibility(View.VISIBLE);
            wheelchairfriendly9.setVisibility(View.GONE);
            aggrement9.setVisibility(View.GONE);
            monthofnotice9.setVisibility(View.GONE);
            facing9.setVisibility(View.VISIBLE);
            widthfaceing9.setVisibility(View.VISIBLE);
            brokerageprice9.setVisibility(View.GONE);
            depositeprice9.setVisibility(View.GONE);
            powerbackup9.setVisibility(View.VISIBLE);
            bedroom9.setVisibility(View.VISIBLE);
            bathroom9.setVisibility(View.VISIBLE);
            balconies9.setVisibility(View.VISIBLE);
            noofroom9.setVisibility(View.GONE);
            qualityrating9.setVisibility(View.GONE);
            rera9.setVisibility(View.GONE);

        }

        if(ListingData.getInstance(getContext()).category().equals("House/Villa") && ListingData.getInstance(getContext()).propertylistfor().equals("Rent")){
            System.out.println("print   showw 2");


            flattypet9.setVisibility(View.GONE);
            categorytypet9.setVisibility(View.VISIBLE);
            availability9.setVisibility(View.VISIBLE);
            floor9.setVisibility(View.VISIBLE);
            possessionby9.setVisibility(View.VISIBLE);
            ownership9.setVisibility(View.GONE);
            furnishing9.setVisibility(View.VISIBLE);
            parking9.setVisibility(View.VISIBLE);
            gatedcommunity9.setVisibility(View.GONE);
            taxcharges9.setVisibility(View.GONE);
            builtuparea9.setVisibility(View.VISIBLE);
            carpetarea9.setVisibility(View.VISIBLE);
            avalabilitydate9.setVisibility(View.VISIBLE);
            flooring9.setVisibility(View.VISIBLE);
            reservedparkt9.setVisibility(View.VISIBLE);
            wheelchairfriendly9.setVisibility(View.GONE);
            aggrement9.setVisibility(View.GONE);
            monthofnotice9.setVisibility(View.GONE);
            facing9.setVisibility(View.VISIBLE);
            widthfaceing9.setVisibility(View.VISIBLE);
            brokerageprice9.setVisibility(View.GONE);
            depositeprice9.setVisibility(View.GONE);
            powerbackup9.setVisibility(View.VISIBLE);
            bedroom9.setVisibility(View.VISIBLE);
            bathroom9.setVisibility(View.VISIBLE);
            balconies9.setVisibility(View.VISIBLE);
            noofroom9.setVisibility(View.GONE);
            qualityrating9.setVisibility(View.GONE);
            rera9.setVisibility(View.GONE);

        }

        if(ListingData.getInstance(getContext()).category().equals("Others") && ListingData.getInstance(getContext()).propertylistfor().equals("Rent") && ListingData.getInstance(getContext()).type().equals("Residential")){

            flattypet9.setVisibility(View.GONE);
            categorytypet9.setVisibility(View.GONE);
            availability9.setVisibility(View.VISIBLE);
            floor9.setVisibility(View.VISIBLE);
            possessionby9.setVisibility(View.GONE);
            ownership9.setVisibility(View.VISIBLE);
            furnishing9.setVisibility(View.GONE);
            parking9.setVisibility(View.GONE);
            gatedcommunity9.setVisibility(View.GONE);
            ageofproperty9.setVisibility(View.VISIBLE);
            taxcharges9.setVisibility(View.GONE);
            builtuparea9.setVisibility(View.GONE);
            carpetarea9.setVisibility(View.GONE);
            avalabilitydate9.setVisibility(View.VISIBLE);
            flooring9.setVisibility(View.GONE);
            reservedparkt9.setVisibility(View.GONE);
            wheelchairfriendly9.setVisibility(View.GONE);
            aggrement9.setVisibility(View.GONE);
            monthofnotice9.setVisibility(View.GONE);
            facing9.setVisibility(View.VISIBLE);
            widthfaceing9.setVisibility(View.GONE);
            brokerageprice9.setVisibility(View.GONE);
            depositeprice9.setVisibility(View.GONE);
            powerbackup9.setVisibility(View.GONE);
            bedroom9.setVisibility(View.GONE);
            bathroom9.setVisibility(View.GONE);
            balconies9.setVisibility(View.GONE);
            noofroom9.setVisibility(View.GONE);
            qualityrating9.setVisibility(View.GONE);
            rera9.setVisibility(View.GONE);


        }



        if(ListingData.getInstance(getContext()).category().equals("Residential Land") && ListingData.getInstance(getContext()).propertylistfor().equals("Sell")){

            flattypet9.setVisibility(View.GONE);
            categorytypet9.setVisibility(View.GONE);
            availability9.setVisibility(View.GONE);
            floor9.setVisibility(View.GONE);
            possessionby9.setVisibility(View.VISIBLE);
            ownership9.setVisibility(View.GONE);
            furnishing9.setVisibility(View.GONE);
            parking9.setVisibility(View.GONE);
            gatedcommunity9.setVisibility(View.GONE);
            taxcharges9.setVisibility(View.GONE);
            builtuparea9.setVisibility(View.VISIBLE);
            carpetarea9.setVisibility(View.VISIBLE);
            avalabilitydate9.setVisibility(View.GONE);
            flooring9.setVisibility(View.GONE);
            reservedparkt9.setVisibility(View.GONE);
            wheelchairfriendly9.setVisibility(View.GONE);
            aggrement9.setVisibility(View.GONE);
            monthofnotice9.setVisibility(View.GONE);
            facing9.setVisibility(View.VISIBLE);
            widthfaceing9.setVisibility(View.GONE);
            brokerageprice9.setVisibility(View.GONE);
            depositeprice9.setVisibility(View.VISIBLE);
            powerbackup9.setVisibility(View.GONE);
            bedroom9.setVisibility(View.GONE);
            bathroom9.setVisibility(View.GONE);
            balconies9.setVisibility(View.GONE);
            noofroom9.setVisibility(View.GONE);
            qualityrating9.setVisibility(View.GONE);
            rera9.setVisibility(View.VISIBLE);

        }


        if(ListingData.getInstance(getContext()).category().equals("Apartment/Flat/Builder Floor") && ListingData.getInstance(getContext()).propertylistfor().equals("Paying Guest")){

            flattypet9.setVisibility(View.VISIBLE);
            categorytypet9.setVisibility(View.VISIBLE);
            availability9.setVisibility(View.VISIBLE);
            floor9.setVisibility(View.VISIBLE);
            possessionby9.setVisibility(View.VISIBLE);
            ownership9.setVisibility(View.GONE);
            furnishing9.setVisibility(View.VISIBLE);
            parking9.setVisibility(View.VISIBLE);
            gatedcommunity9.setVisibility(View.GONE);
            taxcharges9.setVisibility(View.GONE);
            builtuparea9.setVisibility(View.VISIBLE);
            carpetarea9.setVisibility(View.VISIBLE);
            avalabilitydate9.setVisibility(View.VISIBLE);
            flooring9.setVisibility(View.VISIBLE);
            reservedparkt9.setVisibility(View.VISIBLE);
            wheelchairfriendly9.setVisibility(View.GONE);
            aggrement9.setVisibility(View.GONE);
            monthofnotice9.setVisibility(View.GONE);
            facing9.setVisibility(View.VISIBLE);
            widthfaceing9.setVisibility(View.VISIBLE);
            brokerageprice9.setVisibility(View.GONE);
            depositeprice9.setVisibility(View.GONE);
            powerbackup9.setVisibility(View.VISIBLE);
            bedroom9.setVisibility(View.VISIBLE);
            bathroom9.setVisibility(View.VISIBLE);
            balconies9.setVisibility(View.VISIBLE);
            noofroom9.setVisibility(View.GONE);
            qualityrating9.setVisibility(View.GONE);


        }

        if(ListingData.getInstance(getContext()).category().equals("House/Villa")&& ListingData.getInstance(getContext()).propertylistfor().equals("Paying Guest")){
            System.out.println("print   showw ");


            flattypet9.setVisibility(View.GONE);
            categorytypet9.setVisibility(View.VISIBLE);
            availability9.setVisibility(View.VISIBLE);
            floor9.setVisibility(View.VISIBLE);
            possessionby9.setVisibility(View.VISIBLE);
            ownership9.setVisibility(View.GONE);
            furnishing9.setVisibility(View.VISIBLE);
            parking9.setVisibility(View.VISIBLE);
            gatedcommunity9.setVisibility(View.GONE);
            taxcharges9.setVisibility(View.GONE);
            builtuparea9.setVisibility(View.VISIBLE);
            carpetarea9.setVisibility(View.VISIBLE);
            avalabilitydate9.setVisibility(View.VISIBLE);
            flooring9.setVisibility(View.VISIBLE);
            reservedparkt9.setVisibility(View.VISIBLE);
            wheelchairfriendly9.setVisibility(View.GONE);
            aggrement9.setVisibility(View.GONE);
            monthofnotice9.setVisibility(View.GONE);
            facing9.setVisibility(View.VISIBLE);
            widthfaceing9.setVisibility(View.VISIBLE);
            brokerageprice9.setVisibility(View.GONE);
            depositeprice9.setVisibility(View.GONE);
            powerbackup9.setVisibility(View.VISIBLE);
            bedroom9.setVisibility(View.VISIBLE);
            bathroom9.setVisibility(View.VISIBLE);
            balconies9.setVisibility(View.VISIBLE);
            noofroom9.setVisibility(View.GONE);
            qualityrating9.setVisibility(View.GONE);
        }




        //category
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


        //propertylistfor
        if(propertylistfor.equals("Sell")){
            propertylistfor = "1";
        }else if(propertylistfor.equals("Rent")){
            propertylistfor = "2";
        }else{
            propertylistfor = "3";
        }




        return view;

        }

    private void questionanswer() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.getquestionanswer, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("response :: "+ response);

                try {
                    JSONObject obj = new JSONObject(response);


                    String succes = obj.getString("success");
                    JSONArray jsonArray = obj.getJSONArray("data");

                    if(jsonArray.isNull(0)){
                        datanull = "0";
                        faqlay.setVisibility(View.GONE);
                    }else{
                        datanull ="1";
                        faqlay.setVisibility(View.VISIBLE);

                    }


                    if(succes.equals("1")){
                        questionAnswerDataModels.clear();
                        for(int i=0; i<jsonArray.length();i++){
                            JSONObject object = jsonArray.getJSONObject(i);
                            String queid =object.getString("queid");
                            String question =object.getString("question");
                            String mobile =object.getString("mobile");
                            String email =object.getString("email");
                            String name =object.getString("name");
                            String status =object.getString("status");
                            String answer =object.getString("answer");
                            String propertyid =object.getString("propertyid");


                            QuestionAnswerDataModel questionAnswerDataModel = new QuestionAnswerDataModel(queid,question,mobile,email,name,status,answer, propertyid, datanull);
                            questionAnswerDataModels.add(questionAnswerDataModel);
                            questionAnswerAdapter.notifyDataSetChanged();
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

                params.put("propertyid", propertyid);

                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
    }


    private void sendquestionto_server(final String getquestion, final String getemail, final String getmobile, final String getname) {
        progressDialog.show();
        final String userid = VolleySingleton.getInstance(getContext()).id();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.sendquestion, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject obj = new JSONObject(response);
                    if(obj.getBoolean("error")){
                        showmessage(obj.getString("message"));
                    }else{
                        showmessage(obj.getString("message"));
                        getActivity().finish();
                        startActivity(getActivity().getIntent());


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
                params.put("propertyid", propertyid);
                params.put("userid", userid);
                params.put("name", getname);
                params.put("email", getemail);
                params.put("mobile", getmobile);
                params.put("question", getquestion);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);

    }

    private void getsimilarproperty() {
        progressDialog.show();
        final String userid;
        if(VolleySingleton.getInstance(getContext()).isLogin()){
            userid =  VolleySingleton.getInstance(getContext()).id();
        }else{
            userid = "no";
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.getsimilarproperty, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();

                System.out.println("response similarproperty :: "+ response);


                try {
                    JSONObject obj = new JSONObject(response);


                    String succes = obj.getString("success");
                    JSONArray jsonArray = obj.getJSONArray("data");

                    if (jsonArray.isNull(0)){
                        similar9.setVisibility(View.GONE);
                    }


                    if(succes.equals("1")){
                        similarPropertyDataModels.clear();
                        for(int i=0; i<jsonArray.length();i++){


                            willing1.clear();
                            pgsuitablefor1.clear();
                            otherroom1.clear();
                            homethings1.clear();
                            facilityincluded1.clear();
                            weekdays1.clear();
                            weekend1.clear();
                            anemitiesitem1.clear();
                            moreanemitiesitem1.clear();
                            watersourceitem1.clear();
                            overlookingitem1.clear();
                            somefeatureitem1.clear();
                            byersitem1.clear();
                            timeitems1.clear();
                            moreanemitiesitem2.clear();
                            homethings2.clear();



                            JSONObject object = jsonArray.getJSONObject(i);
                            id =object.getString("id");
                            //usertype
                            usertype = object.getString("usertype");

                            if(usertype.equals("1")){
                                usertype = "Owner";
                            }else if (usertype.equals("2")){
                                usertype = "Dealer";
                            }
                            else if (usertype.equals("3")){
                                usertype = "Builder";
                            }



                            // type
                            type = object.getString("type");

                            if(type.equals("1")){
                                type = "Residential";
                            }else{
                                type = "Commercial";
                            }


                            //category
                            category = object.getString("category");

                            if(category.equals("1")){
                                category = "Apartment/Flat/Builder Floor";
                            }
                            else if(category.equals("2")){
                                category = "Residential Land";
                            }
                            else if(category.equals("3")){
                                category = "House/Villa";
                            }
                            else if(category.equals("4")){
                                category = "Offices";
                            }
                            else if(category.equals("5")){
                                category = "Retail";
                            }
                            else if(category.equals("6")){
                                category = "Land";
                            }
                            else if(category.equals("7")){
                                category = "Industry";
                            }
                            else if(category.equals("8")){
                                category = "Storage";
                            }
                            else if(category.equals("9")){
                                category = "Hospitality";
                            }
                            else if(category.equals("10")){
                                category = "Others";
                            }



                            //categorytype
                            categorytype = object.getString("categorytype");


                            if (categorytype.equals("1")){
                                categorytype = "Residential Apartment";
                            }

                            else if (categorytype.equals("2")){
                                categorytype = "Independent/Builder Floor";
                            }
                            else if (categorytype.equals("3")){
                                categorytype = "Studio Apartment";
                            }
                            else if (categorytype.equals("4")){
                                categorytype = "Serviced Apartments";
                            }
                            else if (categorytype.equals("5")){
                                categorytype = "Independent House/Villa";
                            }
                            else if (categorytype.equals("6")){
                                categorytype = "Farm House";
                            }
                            else if (categorytype.equals("7")){
                                categorytype = "Ready to move office space";
                            }
                            else if (categorytype.equals("8")){
                                categorytype = "Bare shell office space";
                            }
                            else if (categorytype.equals("9")){
                                categorytype = "Co-working office space";
                            }
                            else if (categorytype.equals("10")){
                                categorytype = "Commercial Shops";
                            }
                            else if (categorytype.equals("11")){
                                categorytype = "Commercial Showrooms";
                            }
                            else if (categorytype.equals("12")){
                                categorytype = "Space in Retail Mall";
                            }
                            else if (categorytype.equals("13")){
                                categorytype = "Commercial Land/Inst. Land";
                            }
                            else if (categorytype.equals("14")){
                                categorytype = "Agricultural/Farm Land";
                            }
                            else if (categorytype.equals("15")){
                                categorytype = "Industrial Lands/Plots";
                            }
                            else if (categorytype.equals("16")){
                                categorytype = "Factory";
                            }
                            else if (categorytype.equals("17")){
                                categorytype = "Manufacturing";
                            }
                            else if (categorytype.equals("18")){
                                categorytype = "Ware House";
                            }
                            else if (categorytype.equals("19")){
                                categorytype = "Cold Storage";
                            }
                            else if (categorytype.equals("20")){
                                categorytype = "Hotel/Resorts";
                            }
                            else if (categorytype.equals("21")){
                                categorytype = "Guest-House/Banquet-Hallsage";
                            }




                            //agrement
                            agrement = object.getString("agrement");

                            if(agrement.equals("1")){
                                agrement = "Company Lease Agreement";
                            }else{
                                agrement = "Any";
                            }



                            //pgavailablefor
                            pgavaliablefor = object.getString("pgavaliablefor");

                            if(pgavaliablefor.equals("1")){
                                pgavaliablefor = "Girls";
                            }else if (pgavaliablefor.equals("2")){
                                pgavaliablefor ="Boys";
                            }else{
                                pgavaliablefor = "Any";
                            }




                            //pgshareprivate
                            pgshareprivate = object.getString("pgshareprivate");

                            if (pgshareprivate.equals("1")){
                                pgshareprivate = "Private";
                            }else {
                                pgshareprivate = "Sharing";
                            }




                            //sharingnumber
                            sharingspinnumber = object.getString("sharingspinnumber");

                            //propertylistfor
                            propertylistfor = object.getString("propertylistfor");

                            if(propertylistfor.equals("1")){
                                propertylistfor = "Sell";
                            }else if(propertylistfor.equals("2")){
                                propertylistfor = "Rent";
                            }else{
                                propertylistfor = "Paying Guest";
                            }

                            // willing
                            willing = object.getString("willing");

                            if(willing.contains("1")){
                                willing1.add("Family");
                            }
                            if (willing.contains("2")){
                                willing1.add("Single Men");
                            }
                            if(willing.contains("3")){
                                willing1.add("Single Women");
                            }

                            willing = String.valueOf(willing1);


                            //pgsuitablefor
                            pgsuitablefor = object.getString("pgsuitablefor");

                            if(pgsuitablefor.contains("1")){
                                pgsuitablefor1.add("Students");
                            }
                            if(pgsuitablefor.contains("2")){
                                pgsuitablefor1.add("Working Professionals");
                            }

                            pgsuitablefor = String.valueOf(pgsuitablefor1);

                            state = object.getString("state");
                            city = object.getString("city");
                            locality = object.getString("locality");
                            projectname = object.getString("projectname");
                            address = object.getString("address");


                            //unit1
                            unit1 = object.getString("unit1");

                            if(unit1.equals("1")){
                                unit1 = "Sq.Ft";
                            }
                            else if(unit1.equals("2")){
                                unit1 = "Sq.yards";
                            }else if(unit1.equals("3")){
                                unit1 = "Sq.m";
                            }
                            else if(unit1.equals("4")){
                                unit1 = "Acres";
                            }
                            else if(unit1.equals("5")){
                                unit1 = "Marla";
                            }else if(unit1.equals("6")){
                                unit1 = "Cents";
                            }
                            else if(unit1.equals("7")){
                                unit1 = "Bigha";
                            }else if(unit1.equals("8")){
                                unit1 = "Kottah";
                            }
                            else if(unit1.equals("9")){
                                unit1 = "Grounds";
                            }
                            else if(unit1.equals("10")){
                                unit1 = "Ares";
                            }
                            else if(unit1.equals("11")){
                                unit1 = "Biswa";
                            }
                            else if(unit1.equals("12")){
                                unit1 = "Guntha";
                            }
                            else if(unit1.equals("13")){
                                unit1 = "Aankadam";
                            }

                            else if(unit1.equals("14")){
                                unit1 = "Hectares";
                            }

                            else if(unit1.equals("15")){
                                unit1 = "Rood";
                            }

                            else if(unit1.equals("16")){
                                unit1 = "Chataks";
                            }

                            else if(unit1.equals("17")){
                                unit1 = "Perch";
                            }


                            //unit2
                            unit2 = object.getString("unit2");

                            if(unit2.equals("1")){
                                unit2 = "Sq.Ft";
                            }
                            else if(unit2.equals("2")){
                                unit2 = "Sq.yards";
                            }else if(unit2.equals("3")){
                                unit2 = "Sq.m";
                            }
                            else if(unit2.equals("4")){
                                unit2 = "Acres";
                            }
                            else if(unit2.equals("5")){
                                unit2 = "Marla";
                            }else if(unit2.equals("6")){
                                unit2 = "Cents";
                            }
                            else if(unit2.equals("7")){
                                unit2 = "Bigha";
                            }else if(unit2.equals("8")){
                                unit2 = "Kottah";
                            }
                            else if(unit2.equals("9")){
                                unit2 = "Grounds";
                            }
                            else if(unit2.equals("10")){
                                unit2 = "Ares";
                            }
                            else if(unit2.equals("11")){
                                unit2 = "Biswa";
                            }
                            else if(unit2.equals("12")){
                                unit2 = "Guntha";
                            }
                            else if(unit2.equals("13")){
                                unit2 = "Aankadam";
                            }

                            else if(unit2.equals("14")){
                                unit2 = "Hectares";
                            }

                            else if(unit2.equals("15")){
                                unit2 = "Rood";
                            }

                            else if(unit2.equals("16")){
                                unit2 = "Chataks";
                            }

                            else if(unit2.equals("17")){
                                unit2 = "Perch";
                            }



                            //unit3
                            unit3 = object.getString("unit3");

                            if(unit3.equals("1")){
                                unit3 = "Sq.Ft";
                            }
                            else if(unit3.equals("2")){
                                unit3 = "Sq.yards";
                            }else if(unit3.equals("3")){
                                unit1 = "Sq.m";
                            }
                            else if(unit3.equals("4")){
                                unit3 = "Acres";
                            }
                            else if(unit3.equals("5")){
                                unit3 = "Marla";
                            }else if(unit3.equals("6")){
                                unit3 = "Cents";
                            }
                            else if(unit3.equals("7")){
                                unit3 = "Bigha";
                            }else if(unit3.equals("8")){
                                unit3 = "Kottah";
                            }
                            else if(unit3.equals("9")){
                                unit3 = "Grounds";
                            }
                            else if(unit3.equals("10")){
                                unit3 = "Ares";
                            }
                            else if(unit3.equals("11")){
                                unit3 = "Biswa";
                            }
                            else if(unit3.equals("12")){
                                unit3 = "Guntha";
                            }
                            else if(unit3.equals("13")){
                                unit3 = "Aankadam";
                            }

                            else if(unit3.equals("14")){
                                unit3 = "Hectares";
                            }

                            else if(unit3.equals("15")){
                                unit3 = "Rood";
                            }

                            else if(unit3.equals("16")){
                                unit3 = "Chataks";
                            }

                            else if(unit3.equals("17")){
                                unit3 = "Perch";
                            }




                            availabilitydate = object.getString("availabilitydate");

                            //otherroom
                            otherroom = object.getString("otherroom");

                            if(otherroom.contains("1")){
                                otherroom1.add("Pooja Room");
                            }
                            if(otherroom.contains("2")){
                                otherroom1.add("Study Room");
                            }

                            if(otherroom.contains("3")){
                                otherroom1.add("Servant Room");
                            }

                            if(otherroom.contains("4")){
                                otherroom1.add("Others");
                            }

                            otherroom = String.valueOf(otherroom1);




                            //homethings
                            homethings = object.getString("homethings");


                            homethings = homethings.toString().replace("[", "").replace("]", "");
                            String result123 = homethings.replaceAll("\\s", "");

                            homethings3 = new ArrayList<String>(Arrays.asList(result123.split(",")));


                            homethings2.addAll(homethings3);


                            if(homethings2.contains("1")){
                                homethings1.add("Wardrobe");
                            }


                            if(homethings2.contains("2")){
                                homethings1.add("Modular Kitchen");
                            }


                            if(homethings2.contains("3")){
                                homethings1.add("Fridge");
                            }


                            if(homethings2.contains("4")){
                                homethings1.add("Ac");
                            }


                            if(homethings2.contains("5")){
                                homethings1.add("Stove");
                            }


                            if(homethings2.contains("6")){
                                homethings1.add("Geyser");
                            }


                            if(homethings2.contains("7")){
                                homethings1.add("Dinning Table");
                            }

                            if(homethings2.contains("8")){
                                homethings1.add("Sofa");
                            }


                            if(homethings2.contains("9")){
                                homethings1.add("Washing Machine");
                            }


                            if(homethings2.contains("10")){
                                homethings1.add("Water Purifier");
                            }


                            if(homethings2.contains("11")){
                                homethings1.add("Microwave");
                            }


                            if(homethings2.contains("12")){
                                homethings1.add("Curtains");
                            }

                            if(homethings2.contains("13")){
                                homethings1.add("Chimney");
                            }

                            if(homethings2.contains("14")){
                                homethings1.add("Exhaust Fan");
                            }



                            homethings = String.valueOf(homethings1);





                            builtupa = object.getString("builtupa");
                            carpeta = object.getString("carpeta");
                            plota = object.getString("plota");
                            bed = object.getString("bed");
                            bath = object.getString("bath");
                            balconie = object.getString("balconie");
                            totalfloorget = object.getString("totalfloorget");


                            //reservvedpark
                            reservedpark = object.getString("reservedpark");
                            if(reservedpark.equals("1")){
                                reservedpark = "Yes";
                            }else{
                                reservedpark = "No";
                            }


                            // availability
                            availabilityspinget = object.getString("availabilityspinget");

                            if(availabilityspinget.equals("1")){
                                availabilityspinget = "Under Construction";
                            }
                            else if(availabilityspinget.equals("2")){
                                availabilityspinget = "Ready To Move";

                            }


                            //ageofproperty
                            ageofpropertyspinget = object.getString("ageofpropertyspinget");
                            if(ageofpropertyspinget.equals("1")){
                                ageofpropertyspinget = "0-1 Year Old Property";
                            }
                            else if(ageofpropertyspinget.equals("2")){
                                ageofpropertyspinget = "1-5 Year Old Property";
                            }
                            else if(ageofpropertyspinget.equals("3")){
                                ageofpropertyspinget = "5-10 Year Old Property";
                            }
                            else if(ageofpropertyspinget.equals("4")){
                                ageofpropertyspinget = "10+ Year Old Property";
                            }
                            else if(ageofpropertyspinget.equals("5")){
                                ageofpropertyspinget = "Ready To Move";
                            }



                            //furnished
                            furnishedspinget = object.getString("furnishedspinget");
                            if(furnishedspinget.equals("1")){
                                furnishedspinget = "Furnished";
                            }
                            else if(furnishedspinget.equals("2")){
                                furnishedspinget = "Semifurnished";
                            }
                            else if(furnishedspinget.equals("3")){
                                furnishedspinget = "Unfurnished";
                            }



                            //possessionby
                            possessionbyspinget = object.getString("possessionbyspinget");

                            if(possessionbyspinget.equals("1")){
                                possessionbyspinget = "Within 3 Months";
                            }
                            else if(possessionbyspinget.equals("2")){
                                possessionbyspinget = "By 2021";
                            }
                            else if(possessionbyspinget.equals("3")){
                                possessionbyspinget = "By 2022";
                            }
                            else if(possessionbyspinget.equals("4")){
                                possessionbyspinget = "By 2023";
                            }
                            else if(possessionbyspinget.equals("5")){
                                possessionbyspinget = "By 2024";
                            }
                            else if(possessionbyspinget.equals("6")){
                                possessionbyspinget = "By 2025";
                            }
                            else if(possessionbyspinget.equals("7")){
                                possessionbyspinget = "By 2026";
                            }
                            else if(possessionbyspinget.equals("8")){
                                possessionbyspinget = "By 2027";
                            }
                            else if(possessionbyspinget.equals("9")){
                                possessionbyspinget = "By 2028";
                            }
                            else if(possessionbyspinget.equals("10")){
                                possessionbyspinget = "By 2029";
                            }
                            else if(possessionbyspinget.equals("11")){
                                possessionbyspinget = "By 2030";
                            }
                            else if(possessionbyspinget.equals("12")){
                                possessionbyspinget = "By 2031";
                            }



                            noofroomspinget = object.getString("noofroomspinget");


                            //qualityrating
                            qualityratingspinget = object.getString("qualityratingspinget");

                            if(qualityratingspinget.equals("1")){
                                qualityratingspinget = "1 Star";
                            }
                            else if(qualityratingspinget.equals("2")){
                                qualityratingspinget = "2 Star";
                            }
                            else if(qualityratingspinget.equals("3")){
                                qualityratingspinget = "3 Star";
                            }
                            else if(qualityratingspinget.equals("4")){
                                qualityratingspinget = "4 Star";
                            }
                            else if(qualityratingspinget.equals("5")){
                                qualityratingspinget = "5 Star";
                            }
                            else if(qualityratingspinget.equals("6")){
                                qualityratingspinget = "6 Star";
                            }
                            else if(qualityratingspinget.equals("7")){
                                qualityratingspinget = "7 Star";
                            }
                            else if(qualityratingspinget.equals("8")){
                                qualityratingspinget = "8 Star";
                            }
                            else if(qualityratingspinget.equals("9")){
                                qualityratingspinget = "9 Star";
                            }
                            else if(qualityratingspinget.equals("10")){
                                qualityratingspinget = "10 Star";
                            }

                            floorallowedspinget = object.getString("floorallowedspinget");
                            roomavailabespinget = object.getString("roomavailabespinget");
                            priceget = object.getString("priceget");
                            maintenanceget = object.getString("maintenanceget");
                            bookingamountget = object.getString("bookingamountget");
                            durationofrentagget = object.getString("durationofrentagget");
                            monthofnoticeget = object.getString("monthofnoticeget");


                            // securitydeposite
                            securitydepositspinget = object.getString("securitydepositspinget");


                            if(securitydepositspinget.equals("1")){
                                securitydepositspinget = "Fixed";
                            }
                            else if(securitydepositspinget.equals("2")){
                                securitydepositspinget = "Multiple Of Rent";
                            }



                            //maintenancemonthly
                            maintenancemonthlyspinget = object.getString("maintenancemonthlyspinget");

                            if(maintenancemonthlyspinget.equals("1")){
                                maintenancemonthlyspinget = "Monthly";
                            }
                            else if(maintenancemonthlyspinget.equals("2")){
                                maintenancemonthlyspinget = "Annually";
                            }
                            else if(maintenancemonthlyspinget.equals("3")){
                                maintenancemonthlyspinget = "One Time";
                            }
                            else if(maintenancemonthlyspinget.equals("4")){
                                maintenancemonthlyspinget = "Per Unit/Monthly";
                            }



                            //ownership
                            ownweshipspinget = object.getString("ownweshipspinget");

                            if(ownweshipspinget.equals("1")){
                                ownweshipspinget = "Freehold";
                            }
                            else if(ownweshipspinget.equals("2")){
                                ownweshipspinget = "Leasehold";
                            }
                            else if(ownweshipspinget.equals("3")){
                                ownweshipspinget = "Co-Operative Society";
                            }
                            else if(ownweshipspinget.equals("4")){
                                ownweshipspinget = "Power Of Attorney";
                            }


                            //typeoffood
                            typeoffood = object.getString("typeoffood");

                            if(typeoffood.equals("1")){
                                typeoffood = "Veg";
                            }else{
                                typeoffood = "Ved and Non-Veg";
                            }



                            //pricestep
                            pricestep = object.getString("pricestep");

                            if(pricestep.equals("1")){
                                pricestep = "Price Negotiable";
                            }else{
                                pricestep = "Electricity And Water Charges Excluded";
                            }




                            //facilityinculded
                            facilityincluded = object.getString("facilityincluded");

                            if(facilityincluded.contains("1")){
                                facilityincluded1.add("Food");
                            }

                            if(facilityincluded.contains("2")){
                                facilityincluded1.add("Laundry");
                            }

                            if(facilityincluded.contains("3")){
                                facilityincluded1.add("Fridge");
                            }

                            if(facilityincluded.contains("4")){
                                facilityincluded1.add("Electricity");
                            }

                            if(facilityincluded.contains("5")){
                                facilityincluded1.add("None Of the Above");
                            }

                            if(facilityincluded.contains("6")){
                                facilityincluded1.add("HouseKeeping");
                            }

                            if(facilityincluded.contains("7")){
                                facilityincluded1.add("DTH");
                            }

                            if(facilityincluded.contains("8")){
                                facilityincluded1.add("Water");
                            }

                            if(facilityincluded.contains("9")){
                                facilityincluded1.add("Wifi");
                            }

                            facilityincluded = String.valueOf(facilityincluded1);





                            //weekdays
                            weekdays = object.getString("weekdays");

                            if(weekdays.contains("1")){
                                weekdays1.add("Breakfast");
                            }
                            if(weekdays.contains("2")){
                                weekdays1.add("Lunch");
                            }
                            if(weekdays.contains("3")){
                                weekdays1.add("Dinner");
                            }

                            weekdays = String.valueOf(weekdays1);



                            //weekends
                            weeends = object.getString("weeends");

                            if(weeends.contains("1")){
                                weekend1.add("Breakfast");
                            }
                            if(weeends.contains("2")){
                                weekend1.add("Lunch");
                            }
                            if(weeends.contains("3")){
                                weekend1.add("Dinner");
                            }

                            weeends = String.valueOf(weekend1);


                            earlylivingchargesget = object.getString("earlylivingchargesget");
                            contractdurationget = object.getString("contractdurationget");
                            annualduespayableget = object.getString("annualduespayableget");
                            depositeaget = object.getString("depositeaget");


                            //facing
                            facingspinget = object.getString("facingspinget");

                            if(facingspinget.equals("1")){
                                facingspinget = "East";
                            }
                            else if(facingspinget.equals("2")){
                                facingspinget = "North-East";
                            }
                            else if(facingspinget.equals("3")){
                                facingspinget = "North";
                            }
                            else if(facingspinget.equals("4")){
                                facingspinget = "West";
                            }
                            else if(facingspinget.equals("5")){
                                facingspinget = "South";
                            }
                            else if(facingspinget.equals("6")){
                                facingspinget = "South-East";
                            }
                            else if(facingspinget.equals("7")){
                                facingspinget = "North-West";
                            }
                            else if(facingspinget.equals("8")){
                                facingspinget = "South-West";
                            }




                            //unitspinget
                            unitspinget = object.getString("unitspinget");

                            if(unitspinget.equals("1")){
                                unitspinget = "Sq.Ft";
                            }
                            else if(unitspinget.equals("2")){
                                unitspinget = "Sq.yards";
                            }else if(unitspinget.equals("3")){
                                unitspinget = "Sq.m";
                            }
                            else if(unitspinget.equals("4")){
                                unitspinget = "Acres";
                            }
                            else if(unitspinget.equals("5")){
                                unitspinget = "Marla";
                            }else if(unitspinget.equals("6")){
                                unitspinget = "Cents";
                            }
                            else if(unitspinget.equals("7")){
                                unitspinget = "Bigha";
                            }else if(unitspinget.equals("8")){
                                unitspinget = "Kottah";
                            }
                            else if(unitspinget.equals("9")){
                                unitspinget = "Grounds";
                            }
                            else if(unitspinget.equals("10")){
                                unitspinget = "Ares";
                            }
                            else if(unitspinget.equals("11")){
                                unitspinget = "Biswa";
                            }
                            else if(unitspinget.equals("12")){
                                unitspinget = "Guntha";
                            }
                            else if(unitspinget.equals("13")){
                                unitspinget = "Aankadam";
                            }

                            else if(unitspinget.equals("14")){
                                unitspinget = "Hectares";
                            }

                            else if(unitspinget.equals("15")){
                                unitspinget = "Rood";
                            }

                            else if(unitspinget.equals("16")){
                                unitspinget = "Chataks";
                            }

                            else if(unitspinget.equals("17")){
                                unitspinget = "Perch";
                            }





                            //typeofflooring
                            typeofflooringspinget = object.getString("typeofflooringspinget");

                            if(typeofflooringspinget.equals("1")){
                                typeofflooringspinget = "Vitrified";
                            }
                            else if(typeofflooringspinget.equals("2")){
                                typeofflooringspinget = "Marble";
                            }
                            else if(typeofflooringspinget.equals("3")){
                                typeofflooringspinget = "Concrete";
                            }
                            else if(typeofflooringspinget.equals("4")){
                                typeofflooringspinget = "Ceramic";
                            }
                            else if(typeofflooringspinget.equals("5")){
                                typeofflooringspinget = "Polished Concrete";
                            }
                            else if(typeofflooringspinget.equals("6")){
                                typeofflooringspinget = "Mosaic";
                            }
                            else if(typeofflooringspinget.equals("7")){
                                typeofflooringspinget = "Wood";
                            }
                            else if(typeofflooringspinget.equals("8")){
                                typeofflooringspinget = "Granite";
                            }
                            else if(typeofflooringspinget.equals("9")){
                                typeofflooringspinget = "Spartex";
                            }
                            else if(typeofflooringspinget.equals("10")){
                                typeofflooringspinget = "Cement";
                            }
                            else if(typeofflooringspinget.equals("11")){
                                typeofflooringspinget = "Stone";
                            }
                            else if(typeofflooringspinget.equals("12")){
                                typeofflooringspinget = "Vinyl";
                            }
                            else if(typeofflooringspinget.equals("13")){
                                typeofflooringspinget = "IPSFinish";
                            }
                            else if(typeofflooringspinget.equals("14")){
                                typeofflooringspinget = "Others";
                            }


                            //powerbackup
                            powerbackupspinget = object.getString("powerbackupspinget");

                            if(powerbackupspinget.equals("1")){
                                powerbackupspinget = "None";
                            }
                            else if(powerbackupspinget.equals("2")){
                                powerbackupspinget = "Partial";
                            }
                            else if(powerbackupspinget.equals("3")){
                                powerbackupspinget = "Full";
                            }




                            //lasttime
                            lasttimespinget = object.getString("lasttimespinget");

                            if(lasttimespinget.contains("1")){
                                lasttimespinget = "7 PM";
                            }
                            if(lasttimespinget.contains("2")){
                                lasttimespinget = "8 PM";
                            }
                            if(lasttimespinget.contains("3")){
                                lasttimespinget = "9 PM";
                            }
                            if(lasttimespinget.contains("4")){
                                lasttimespinget = "10 PM";
                            }
                            if(lasttimespinget.contains("5")){
                                lasttimespinget = "11 PM";
                            }
                            if(lasttimespinget.contains("6")){
                                lasttimespinget = "12 AM";
                            }
                            if(lasttimespinget.contains("7")){
                                lasttimespinget = "1 AM";
                            }
                            if(lasttimespinget.contains("8")){
                                lasttimespinget = "2 AM";
                            }
                            if(lasttimespinget.contains("9")){
                                lasttimespinget = "3 AM";
                            }

                            if(lasttimespinget.contains("10")){
                                lasttimespinget = "24 x 7";
                            }










                            //pet
                            pet = object.getString("pet");
                            if(pet.equals("1")){
                                pet = "Yes";
                            }else {
                                pet = "No";
                            }


                            //visiter
                            visiter = object.getString("visiter");
                            if(visiter.equals("1")){
                                visiter = "Yes";
                            }else {
                                visiter = "No";
                            }


                            //smoking
                            smoking = object.getString("smoking");

                            if(smoking.equals("1")){
                                smoking = "Yes";
                            }else {
                                smoking = "No";
                            }


                            //alcohol
                            alcohol = object.getString("alcohol");
                            if(alcohol.equals("1")){
                                alcohol = "Yes";
                            }else {
                                alcohol = "No";
                            }


                            //event
                            event = object.getString("event");
                            if(event.equals("1")){
                                event = "Yes";
                            }else {
                                event = "No";
                            }


                            //anemities
                            anemitiesitem = object.getString("anemitiesitem");


                            if(anemitiesitem.contains("1")){
                                anemitiesitem1.add("Lift");
                            }

                            if(anemitiesitem.contains("2")){
                                anemitiesitem1.add("Park");
                            }

                            if(anemitiesitem.contains("3")){
                                anemitiesitem1.add("Maintenance Staff");
                            }

                            if(anemitiesitem.contains("4")){
                                anemitiesitem1.add("Visiter Parking");
                            }

                            if(anemitiesitem.contains("5")){
                                anemitiesitem1.add("FengShui/Vaastu Compliant");
                            }

                            if(anemitiesitem.contains("6")){
                                anemitiesitem1.add("Intercome Facility");
                            }

                            if(anemitiesitem.contains("7")){
                                anemitiesitem1.add("Water Storage");
                            }

                            if(anemitiesitem.contains("8")){
                                anemitiesitem1.add("Security/Fire Alaram");
                            }

                            anemitiesitem = String.valueOf(anemitiesitem1);






                            //moreanemities
                            moreanemitiesitem = object.getString("moreanemitiesitem");

                            moreanemitiesitem = moreanemitiesitem.toString().replace("[", "").replace("]", "");
                            String resultflatamore = moreanemitiesitem.replaceAll("\\s", "");

                            moreanemitiesitem3 = new ArrayList<String>(Arrays.asList(resultflatamore.split(",")));


                            moreanemitiesitem2.addAll(moreanemitiesitem3);

                            System.out.println("morrrrrrr ::: "+ moreanemitiesitem2);


                            if(moreanemitiesitem2.contains("1")){
                                moreanemitiesitem1.add("Swimming Pool");
                            }

                            if(moreanemitiesitem2.contains("2")){
                                moreanemitiesitem1.add("Centrally Air Conditioned");
                            }

                            if(moreanemitiesitem2.contains("3")){
                                moreanemitiesitem1.add("Garden");
                            }

                            if(moreanemitiesitem2.contains("4")){
                                moreanemitiesitem1.add("Wifi");
                            }

                            if(moreanemitiesitem2.contains("5")){
                                moreanemitiesitem1.add("Piped-Gas");
                            }

                            if(moreanemitiesitem2.contains("6")){
                                moreanemitiesitem1.add("Water Purifier");
                            }

                            if(moreanemitiesitem2.contains("7")){
                                moreanemitiesitem1.add("Club House");
                            }

                            if(moreanemitiesitem2.contains("8")){
                                moreanemitiesitem1.add("Shopping Center");
                            }

                            if(moreanemitiesitem2.contains("9")){
                                moreanemitiesitem1.add("Water Disposal");
                            }

                            if(moreanemitiesitem2.contains("10")){
                                moreanemitiesitem1.add("RainWater Harvesting");
                            }

                            if(moreanemitiesitem2.contains("11")){
                                moreanemitiesitem1.add("Bank Attached Property");
                            }

                            if(moreanemitiesitem2.contains("12")){
                                moreanemitiesitem1.add("Gym");
                            }

                            moreanemitiesitem = String.valueOf(moreanemitiesitem1);

                            System.out.println("morrrr more ::"+ moreanemitiesitem1);



                            //watersource
                            watersourceitem = object.getString("watersourceitem");

                            if(watersourceitem.contains("1")){
                                watersourceitem1.add("Municipal Corporation");
                            }
                            if(watersourceitem.contains("2")){
                                watersourceitem1.add("Borewell");
                            }

                            watersourceitem = String.valueOf(watersourceitem1);



                            //overlooking
                            overlookingitem = object.getString("overlookingitem");

                            if(overlookingitem.contains("1")){
                                overlookingitem1.add("Park/Garden");
                            }

                            if(overlookingitem.contains("2")){
                                overlookingitem1.add("Main Road");
                            }

                            if(overlookingitem.contains("3")){
                                overlookingitem1.add("Club");
                            }

                            if(overlookingitem.contains("4")){
                                overlookingitem1.add("Lake Facing");
                            }

                            if(overlookingitem.contains("5")){
                                overlookingitem1.add("Sea Facing");
                            }

                            if(overlookingitem.contains("6")){
                                overlookingitem1.add("Others");
                            }

                            overlookingitem = String.valueOf(overlookingitem1);



                            //somefeatures
                            somefeatureitem = object.getString("somefeatureitem");
                            if(somefeatureitem.contains("1")){
                                somefeatureitem1.add("In a Gated Society");
                            }

                            if(somefeatureitem.contains("2")){
                                somefeatureitem1.add("Corner Property");
                            }
                            if(somefeatureitem.contains("3")){
                                somefeatureitem1.add("Pet Friendly");
                            }

                            if(somefeatureitem.contains("4")){
                                somefeatureitem1.add("Wheelchair Friendly");
                            }

                            somefeatureitem = String.valueOf(somefeatureitem1);



                            //byres
                            byersitem = object.getString("byersitem");

                            if (byersitem.contains("1")) {
                                byersitem1.add("24 x 7 Water");
                            }
                            if (byersitem.contains("2")) {
                                byersitem1.add("Visiter Parking Available");
                            }
                            if (byersitem.contains("3")) {
                                byersitem1.add("Close To School");
                            }
                            if (byersitem.contains("4")) {
                                byersitem1.add("Close To Bank");
                            }
                            if (byersitem.contains("5")) {
                                byersitem1.add("Natural Light");
                            }
                            if (byersitem.contains("6")) {
                                byersitem1.add("Close To Hospital");
                            }
                            if (byersitem.contains("7")) {
                                byersitem1.add("Close To Market");
                            }
                            if (byersitem.contains("8")) {
                                byersitem1.add("Airy Rooms");
                            }

                            byersitem = String.valueOf(byersitem1);


                            //times
                            timeitems = object.getString("timeitems");

                            if(timeitems.contains("1")){
                                timeitems1.add("8am - 12pm");
                            }

                            if(timeitems.contains("2")){
                                timeitems1.add("12pm - 3pm");
                            }
                            if(timeitems.contains("3")){
                                timeitems1.add("3pm - 6pm");
                            }

                            if(timeitems.contains("4")){
                                timeitems1.add("6pm - 9pm");
                            }

                            if(timeitems.contains("5")){
                                timeitems1.add("9pm - 11pm");
                            }

                            if(timeitems.contains("6")){
                                timeitems1.add("None");
                            }

                            timeitems = String.valueOf(timeitems1);



                            widthfacingget = object.getString("widthfacingget");
                            descriptionget = object.getString("descriptionget");


                            //bowndywall
                            boundrywall = object.getString("boundrywall");
                            if(boundrywall.equals("1")){
                                boundrywall = "Yes";
                            }else {
                                boundrywall = "No";
                            }

                            //flattype
                            flattype = object.getString("flattype");


                            if(flattype.equals("1")){
                                flattype = "1 BHK";
                            }
                            else if(flattype.equals("2")){
                                flattype = "2 BHK";
                            }
                            else if(flattype.equals("3")){
                                flattype = "3 BHK";
                            }
                            else if(flattype.equals("4")){
                                flattype = "4 BHK";
                            }
                            else if(flattype.equals("5")){
                                flattype = "5 BHK";
                            }
                            else if(flattype.equals("6")){
                                flattype = "6 BHK";
                            }
                            else if(flattype.equals("7")){
                                flattype = "7 BHK";
                            }
                            else if(flattype.equals("8")){
                                flattype = "8 BHK";
                            }
                            else if(flattype.equals("9")){
                                flattype = "9 BHK";
                            }
                            else if(flattype.equals("10")){
                                flattype = "10 BHK";
                            }
                            else if(flattype.equals("11")){
                                flattype = "11 BHK";
                            }


                            user_id= object.getString("user_id");
                            newprice= object.getString("newprice");
                            image= Endpoints.base_url+ object.getString("image");
                            shortlistedvalue= object.getString("shortlistedvalue");

                            //rera
                            getrera= object.getString("rera");
                            if(getrera.equals("1")){
                                getrera = "Yes";
                            }else {
                                getrera = "No";
                            }

                            mobile= object.getString("mobile");
                            reg_date= object.getString("reg_date");
                            fname= object.getString("fname");
                            email= object.getString("email");
                            lname= object.getString("lname");
                            latlong= object.getString("latlong");




                            SimilarPropertyDataModel similarPropertyDataModel = new SimilarPropertyDataModel(id ,usertype , type , category , categorytype , agrement , pgavaliablefor , pgshareprivate ,
                                    sharingspinnumber , propertylistfor , willing , pgsuitablefor ,state, city , locality , projectname , address ,
                                    unit1 ,unit2 ,unit3 ,availabilitydate , otherroom , homethings , builtupa , carpeta , plota , bed ,
                                    bath , balconie , totalfloorget , reservedpark , availabilityspinget , ageofpropertyspinget ,
                                    furnishedspinget ,possessionbyspinget ,
                                    noofroomspinget , qualityratingspinget , floorallowedspinget , roomavailabespinget , priceget , maintenanceget ,
                                    bookingamountget ,
                                    durationofrentagget , monthofnoticeget  , securitydepositspinget , maintenancemonthlyspinget ,
                                    ownweshipspinget ,
                                    typeoffood , pricestep , facilityincluded , weekdays , weeends , earlylivingchargesget , contractdurationget ,
                                    annualduespayableget ,
                                    depositeaget , facingspinget , unitspinget , typeofflooringspinget , powerbackupspinget , lasttimespinget ,
                                    pet , visiter , smoking ,
                                    alcohol , event , anemitiesitem , moreanemitiesitem , watersourceitem , overlookingitem ,
                                    somefeatureitem , byersitem , timeitems ,
                                    widthfacingget , descriptionget , boundrywall ,flattype, user_id , newprice, image, shortlistedvalue ,
                                    getrera,mobile, reg_date, fname, lname, latlong, email);
                            similarPropertyDataModels.add(similarPropertyDataModel);
                            similarPropertyAdapter.notifyDataSetChanged();
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
                params.put("propertylistfor", propertylistfor);
                params.put("category", category);
                params.put("userid", userid);

                System.out.println("response123 ::"+ params);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
    }

    private void getmultipleimages(){
        sliderImg.clear();
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.getmultipleimages, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                System.out.println("response slider :::" + response);
                progressDialog.dismiss();
                try {
                    JSONObject obj = new JSONObject(response);
                    String succes = obj.getString("success");
                    JSONArray jsonArray = obj.getJSONArray("data");

                    if(succes.equals("1")){

                        for(int i=0; i<jsonArray.length();i++){
                            JSONObject object = jsonArray.getJSONObject(i);
                            String id =object.getString("id");
                            String image =Endpoints.base_url+object.getString("img");




                            SliderUtils2 sliderUtils2 = new SliderUtils2(image, id);
                            sliderImg.add(sliderUtils2);
                            ViewPager2Adapter viewPager2Adapter = new ViewPager2Adapter(sliderImg,getContext());
                            propertyimage.setAdapter(viewPager2Adapter);

                        }
                    }

                } catch (JSONException e) {
                    System.out.println("problem ::"+e);
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                SmsData smsData = new SmsData();
                Map<String, String> params = new HashMap<>();
                params.put("header", smsData.token);
                params.put("propertyid", ListingData.getInstance(getContext()).getpropertyid());
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
    }

    private void showmessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
    public class MyTimertask extends TimerTask {

//        ViewPager2Adapter mAdapter = new ViewPager2Adapter(sliderImg,getContext());

        @Override
        public void run() {
            if(getActivity() != null){
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        propertyimage.post(new Runnable(){


                            @Override
                            public void run() {
                                ViewPager2Adapter viewPager2Adapter = new ViewPager2Adapter(sliderImg,getContext());
                                ConnectivityManager manager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                                NetworkInfo activenetwork = manager.getActiveNetworkInfo();
                                System.out.println("whatssss :: "+ activenetwork);


                                if (activenetwork == null)
                                {
                                    AlphaAnimation blinkanimation= new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
                                    blinkanimation.setDuration(300); // duration - half a second
                                    blinkanimation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
                                    blinkanimation.setRepeatCount(3); // Repeat animation infinitely
                                    blinkanimation.setRepeatMode(Animation.REVERSE);

                                    nointernet.setAnimation(blinkanimation);
                                    settingsDialog.show();
//                                    Toast.makeText(getContext(), "Please Start Internet", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    if(activenetwork.isConnected())
                                    {
                                        settingsDialog.dismiss();

                                        if(viewPager2Adapter.getCount() == 0){
                                            progressDialog.show();
                                        }else {
                                            propertyimage.setCurrentItem((propertyimage.getCurrentItem()+1)%viewPager2Adapter.getCount());
                                        }
                                    }
                                    else
                                    {
                                        AlphaAnimation blinkanimation= new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
                                        blinkanimation.setDuration(300); // duration - half a second
                                        blinkanimation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
                                        blinkanimation.setRepeatCount(3); // Repeat animation infinitely
                                        blinkanimation.setRepeatMode(Animation.REVERSE);

                                        nointernet.setAnimation(blinkanimation);
                                        settingsDialog.show();
//                                        Toast.makeText(getContext(), "Please Start Internet", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });

                    }
                });
            }

        }
    }


}