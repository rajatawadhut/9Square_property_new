package com.nine__square.property.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.toolbox.ImageLoader;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nine__square.property.Avtivities.DetailsPropertyShowActivity;
import com.nine__square.property.Helper.ShortlistedHelper;
import com.nine__square.property.R;
import com.nine__square.property.Utils.ListingData;
import com.nine__square.property.Utils.SliderUtils;
import com.nine__square.property.Utils.VolleySingleton;


import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<SliderUtils> sliderImg;
    private ImageLoader imageLoader;
    Bitmap bitmap;
//    private ProgressDialog progressDialog;


    TextView projectname,price, builtupa, builtupaunit,bhk, address, listingfor, category, usertype, fname,lname;;
    CardView cardview;
    RecyclerView recyclerviewlatest;
    ImageView propertyimage, shortlisted, edit;
//    private Integer [] images = {R.drawable.about, R.drawable.about, R.drawable.about, R.drawable.about, R.drawable.about};

    public ViewPagerAdapter(List<SliderUtils> sliderImg, Context context) {
        this.sliderImg = sliderImg;
        this.context = context;
    }

    @Override
    public int getCount() {
        return sliderImg.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = layoutInflater.inflate(R.layout.property, null);
        final SliderUtils utils = sliderImg.get(position);

//        progressDialog = new ProgressDialog(context);
//        progressDialog.setMessage("Please Wait...");
//        progressDialog.setCancelable(true);
//
//        progressDialog.show();




        price = view.findViewById(R.id.price);
        builtupa = view.findViewById(R.id.builtupa);
        builtupaunit = view.findViewById(R.id.builtupaunit);
        bhk = view.findViewById(R.id.bhk);
//        categorytype = view.findViewById(R.id.categorytype);
        address = view.findViewById(R.id.address);
        cardview = view.findViewById(R.id.cardview);
        recyclerviewlatest = view.findViewById(R.id.recyclerviewlatest);
        listingfor = view.findViewById(R.id.listingfor);
        category = view.findViewById(R.id.category);
        propertyimage = view.findViewById(R.id.propertyimage);
        shortlisted = view.findViewById(R.id.shortlisted);
        edit = view.findViewById(R.id.edit);
        usertype = view.findViewById(R.id.usertype);
        fname = view.findViewById(R.id.fname);
        lname = view.findViewById(R.id.lname);


        {

            shortlisted.setVisibility(View.GONE);

//            if(utils.getCategory().equals("Residential Land")){
//                 bhk.setVisibility(View.GONE);
//                 categorytype.setVisibility(View.GONE);
//            }else{
//                categorytype.setVisibility(View.VISIBLE);
//            }


            final String value = utils.getCategory();
            if(utils.getPropertylistfor().equals("Paying Guest")){
                if(value.length()>11){
                    category.setText((value.substring(0, Math.min(11, value.length())) + "..."));

                }
            }
            else{
                category.setText(utils.getCategory());
            }

            final String value2 =  utils.getAddress();
            if(value2.length()>30){
                address.setText((value2.substring(0, Math.min(30, value2.length())) + "..."));

            }else{
                address.setText(value2);

            }


            price.setText(context.getResources().getString(R.string.rupees)+" " +utils.getNewprice());
             builtupa.setText(utils.getPlota());
             builtupaunit.setText(utils.getUnit1());
             bhk.setText(utils.getFlattype());

            usertype.setText(utils.getUsertype());


            fname.setText(utils.getFname());
            lname.setText(utils.getLname());

            if(utils.getType().equals("Commercial") || utils.getFlattype().isEmpty()){
                bhk.setText(utils.getType());
            }

//             categorytype.setText(utils.getCategorytype());

             listingfor.setText(utils.getPropertylistfor());
            Glide.with(context).load(utils.getImage()).into( propertyimage);

//            if (utils.getImage().length() > 80){
//                Glide.with(context).load(utils.getImage()).into(propertyimage);
//            }else{
//                Glide.with(context).load(R.drawable.nopropertyimage).into(propertyimage);
//            }



            Glide.with(context)
                    .load(utils.getImage())
                    .apply(RequestOptions
                            .placeholderOf(R.drawable.nopropertyimage)
                            .dontAnimate()
                            .error(R.drawable.nopropertyimage))
                    .into(propertyimage);



            edit.setVisibility(View.GONE);


            if(utils.getShortlistedvalue().equals("1")){
                 shortlisted.setColorFilter(Color.parseColor("#E240DB"), PorterDuff.Mode.SRC_IN);
            }else{
                 shortlisted.setColorFilter(null);
            }

             shortlisted.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!utils.getShortlistedvalue().equals("1")){
                         shortlisted.setColorFilter(Color.parseColor("#E240DB"), PorterDuff.Mode.SRC_IN);
                        ListingData.getInstance(context).propertyid(utils.getId());
                        ListingData.getInstance(context).shortlistedvalue("1");

                        String userid = VolleySingleton.getInstance(context).id();
                        String propertyid = ListingData.getInstance(context).getpropertyid();
                        String shortlistedvalue = ListingData.getInstance(context).getshortlistedvalue();

                        utils.setShortlistedvalue("1");

                        ShortlistedHelper shortlistedHelper = new ShortlistedHelper(userid,propertyid,shortlistedvalue);
                        shortlistedHelper.addshortlisted();

                        final ProgressDialog dialog = new ProgressDialog(context);
                        dialog.setCancelable(false);
                        dialog.setMessage("Please wait.....");
                        dialog.show();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                Toast.makeText(context,"Shortlisted Successfully",Toast.LENGTH_LONG).show();
                                dialog.dismiss();
                            }
                        }, 1300);



                    }else {
                         shortlisted.setColorFilter(null);
                        ListingData.getInstance(context).propertyid(utils.getId());
                        ListingData.getInstance(context).shortlistedvalue("0");

                        String userid = VolleySingleton.getInstance(context).id();
                        String propertyid = ListingData.getInstance(context).getpropertyid();
                        String shortlistedvalue = ListingData.getInstance(context).getshortlistedvalue();

                        utils.setShortlistedvalue("0");

                        ShortlistedHelper shortlistedHelper = new ShortlistedHelper(userid,propertyid,shortlistedvalue);
                        shortlistedHelper.addshortlisted();


                        final ProgressDialog dialog = new ProgressDialog(context);
                        dialog.setCancelable(false);
                        dialog.setMessage("Please wait.....");
                        dialog.show();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                Toast.makeText(context,"Remove Shortlisted Successfully",Toast.LENGTH_LONG).show();
                                dialog.dismiss();
                            }
                        }, 1300);



                    }
                }
            });

             cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ListingData.getInstance(context).setnewprice(utils.getNewprice());
                    ListingData.getInstance(context).propertyid(utils.getId());
                    if (utils.getShortlistedvalue().equals("1")) {
                        ListingData.getInstance(context).shortlistedvalue("1");
                    }else{
                        ListingData.getInstance(context).shortlistedvalue("0");
                    }


                    ListingData.getInstance(context).basicdetails(
                            utils.getUsertype(),
                            utils.getType(),
                            utils.getCategory(),
                            utils.getCategorytype(),
                            utils.getAgrement(),
                            utils.getPgavaliablefor(),
                            utils.getPgshareprivate(),
                            utils.getSharingspinnumber(),
                            utils.getPropertylistfor(),
                            utils.getWilling(),
                            utils.getPgsuitablefor());


                    ListingData.getInstance(context).addlocation(
                            utils.getState(),
                            utils.getCity(),
                            utils.getAddress(),
                            utils.getLocality(),
                            utils.getProjectname());

                    ListingData.getInstance(context).setLatLog(utils.getLatlong());


                    ListingData.getInstance(context).addpropertydetails(
                            utils.getUnit1(),
                            utils.getUnit2(),
                            utils.getUnit3(),
                            utils.getAvailabilitydate(),
                            utils.getHomethings(),
                            utils.getOtherroom(),
                            utils.getBuiltupa(),
                            utils.getCarpeta(),
                            utils.getPlota(),
                            utils.getBed(),
                            utils.getBath(),
                            utils.getBalconie(),
                            utils.getTotalfloorget(),
                            utils.getReservedpark(),
                            utils.getAvailabilityspinget(),
                            utils.getAgeofpropertyspinget(),
                            utils.getFurnishedspinget(),
                            utils.getPossessionbyspinget(),
                            utils.getNoofroomspinget(),
                            utils.getQualityratingspinget(),
                            utils.getFloorallowedspinget(),
                            utils.getRoomavailabespinget(),
                            utils.getFlattype(),
                            utils.getRera()
                    );


                    ListingData.getInstance(context).addpricing(
                            utils.getPriceget(),
                            utils.getMaintenanceget(),
                            utils.getBookingamountget(),
                            utils.getDurationofrentagget(),
                            utils.getMonthofnoticeget(),
                            utils.getSecuritydepositspinget(),
                            utils.getMaintenancemonthlyspinget(),
                            utils.getOwnweshipspinget(),
                            utils.getTypeoffood(),
                            utils.getPricestep(),
                            utils.getFacilityincluded(),
                            utils.getWeekdays(),
                            utils.getWeeends(),
                            utils.getEarlylivingchargesget(),
                            utils.getContractdurationget(),
                            utils.getAnnualduespayableget(),
                            utils.getDepositeaget()
                    );

                    ListingData.getInstance(context).addfeatures(utils.getFacingspinget(),
                            utils.getUnitspinget(),
                            utils.getTypeofflooringspinget(),
                            utils.getPowerbackupspinget(),
                            utils.getLasttimespinget(),
                            utils.getPet(),
                            utils.getVisiter(),
                            utils.getSmoking(),
                            utils.getAlcohol(),
                            utils.getEvent(),
                            utils.getAnemitiesitem(),
                            utils.getMoreanemitiesitem(),
                            utils.getWatersourceitem(),
                            utils.getOverlookingitem(),
                            utils.getSomefeatureitem(),
                            utils.getByersitem(),
                            utils.getTimeitems(),
                            utils.getWidthfacingget(),
                            utils.getDescriptionget(),
                            utils.getBoundrywall(),
                            utils.getImage()
                    );

                    ListingData.getInstance(context).setmobile(utils.getMobile());


                    String date,fname,lname,email;
                    date = utils.getReg_date();
                    fname = utils.getFname();
                    lname = utils.getLname();
                    email = utils.getEmail();
                    ListingData.getInstance(context).setdatename(date,fname,lname,email);


                        context.startActivity(new Intent(context, DetailsPropertyShowActivity.class));


                }








            });



        }

        ViewPager vp = (ViewPager) container;
        vp.addView(view,0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }
}
