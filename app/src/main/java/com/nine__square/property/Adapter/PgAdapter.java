package com.nine__square.property.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nine__square.property.AddPropertActivity.BasicDetails;
import com.nine__square.property.Avtivities.DetailsPropertyShowActivity;
import com.nine__square.property.Avtivities.MyPropertyActivity;
import com.nine__square.property.Helper.DateConvert;
import com.nine__square.property.Helper.ShortlistedHelper;
import com.nine__square.property.Models.PgDataModel;
import com.nine__square.property.R;
import com.nine__square.property.Utils.ListingData;
import com.nine__square.property.Utils.VolleySingleton;

import java.util.List;

public class PgAdapter extends RecyclerView.Adapter<PgAdapter.ShowData>{



    private List<PgDataModel> dataSet;
    private Context context;


    public PgAdapter(
            List<PgDataModel> dataSet, Context context) {
        this.dataSet = dataSet;
        this.context = context;
    }

    @NonNull
    @Override
    public ShowData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.land12, parent, false);
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.land12, parent, false);

        ViewGroup.LayoutParams layoutParams = itemView.getLayoutParams();
        layoutParams.width = (int) (parent.getWidth() * 0.93);
        itemView.setLayoutParams(layoutParams);

        return new ShowData(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ShowData holder, final int position) {


        if(dataSet.get(position).getCategory().equals("Residential Land") || dataSet.get(position).getCategory().equals("Others")){
            holder.bhk.setVisibility(View.GONE);
        }


        if(!VolleySingleton.getInstance(context).isLogin()){
            holder.shortlisted.setVisibility(View.GONE);
        }

        holder.bhk.setText(dataSet.get(position).getFlattype());



        if(dataSet.get(position).getType().equals("Commercial")){
            holder.bhk.setVisibility(View.VISIBLE);
            holder.bhk.setText(dataSet.get(position).getType());
        }


        if (dataSet.get(position).getPropertylistfor().equals("Sell")){
            holder.label.setBackgroundColor(Color.parseColor("#F04236"));
            holder.label.setText("Sell");
        }
        if (dataSet.get(position).getPropertylistfor().equals("Rent")){
            holder.label.setBackgroundColor(Color.parseColor("#4FD557"));
            holder.label.setText("Rent");
        }
        if (dataSet.get(position).getPropertylistfor().equals("Paying Guest")){
            holder.label.setBackgroundColor(Color.parseColor("#E6C61C"));
            holder.label.setText("Paying Guest");
        }


        final String value = dataSet.get(position).getCategory();
        if(dataSet.get(position).getPropertylistfor().equals("Paying Guest")){
            if(value.length()>11){
                holder.category.setText((value.substring(0, Math.min(11, value.length())) + "..."));

            }
        }
        else{
            holder.category.setText(dataSet.get(position).getCategory());
        }


        final String value2 =  dataSet.get(position).getAddress();
        if(value2.length()>30){
            holder.address.setText((value2.substring(0, Math.min(30, value2.length())) + "..."));

        }else{
            holder.address.setText(value2);

        }

        holder.price.setText(context.getResources().getString(R.string.rupees)+" " +dataSet.get(position).getNewprice());
        holder.builtupa.setText(dataSet.get(position).getPlota());
        holder.builtupaunit.setText(dataSet.get(position).getUnit1());
        if(dataSet.get(position).getCategorytype().isEmpty()){
            holder.categorytype.setText("N/A");
        }else{
            holder.categorytype.setText(dataSet.get(position).getCategorytype());
        }        holder.listingfor.setText(dataSet.get(position).getPropertylistfor());

        holder.usertype.setText(dataSet.get(position).getUsertype()+ " - ");

        DateConvert dateConvert = new DateConvert(dataSet.get(position).getReg_date());
        String date = dateConvert.getdateconvert();


        holder.date.setText(date);
        holder.fname.setText(dataSet.get(position).getFname());
        holder.lname.setText(dataSet.get(position).getLname());

//        if (dataSet.get(position).getImage().length() > 80){
//            Glide.with(context).load(dataSet.get(position).getImage()).into(holder.propertyimage);
//        }else{
//            Glide.with(context).load(R.drawable.nopropertyimage).into(holder.propertyimage);
//        }

        Glide.with(context)
                .load(dataSet.get(position).getImage())
                .apply(RequestOptions
                        .placeholderOf(R.drawable.nopropertyimage)
                        .dontAnimate()
                        .error(R.drawable.nopropertyimage))
                .into(holder.propertyimage);


        holder.edit.setVisibility(View.GONE);

        if(context instanceof MyPropertyActivity){
            holder.edit.setVisibility(View.VISIBLE);
            holder.shortlisted.setVisibility(View.GONE);
        }

        if(dataSet.get(position).getShortlistedvalue().equals("1")){
            holder.shortlisted.setColorFilter(Color.parseColor("#FFC107"), PorterDuff.Mode.SRC_IN);
        }else{
            holder.shortlisted.setColorFilter(null);
        }

        holder.shortlisted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!dataSet.get(position).getShortlistedvalue().equals("1")){
                    holder.shortlisted.setColorFilter(Color.parseColor("#FFC107"), PorterDuff.Mode.SRC_IN);
                    ListingData.getInstance(context).propertyid(dataSet.get(position).getId());
                    ListingData.getInstance(context).shortlistedvalue("1");

                    String userid = VolleySingleton.getInstance(context).id();
                    String propertyid = ListingData.getInstance(context).getpropertyid();
                    String shortlistedvalue = ListingData.getInstance(context).getshortlistedvalue();

                    dataSet.get(position).setShortlistedvalue("1");

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
                    holder.shortlisted.setColorFilter(null);
                    ListingData.getInstance(context).propertyid(dataSet.get(position).getId());
                    ListingData.getInstance(context).shortlistedvalue("0");

                    String userid = VolleySingleton.getInstance(context).id();
                    String propertyid = ListingData.getInstance(context).getpropertyid();
                    String shortlistedvalue = ListingData.getInstance(context).getshortlistedvalue();

                    dataSet.get(position).setShortlistedvalue("0");

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

        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ListingData.getInstance(context).setnewprice(dataSet.get(position).getNewprice());
                ListingData.getInstance(context).propertyid(dataSet.get(position).getId());
                if (dataSet.get(position).getShortlistedvalue().equals("1")) {
                    ListingData.getInstance(context).shortlistedvalue("1");
                }else{
                    ListingData.getInstance(context).shortlistedvalue("0");
                }


                ListingData.getInstance(context).basicdetails(
                        dataSet.get(position).getUsertype(),
                        dataSet.get(position).getType(),
                        dataSet.get(position).getCategory(),
                        dataSet.get(position).getCategorytype(),
                        dataSet.get(position).getAgrement(),
                        dataSet.get(position).getPgavaliablefor(),
                        dataSet.get(position).getPgshareprivate(),
                        dataSet.get(position).getSharingspinnumber(),
                        dataSet.get(position).getPropertylistfor(),
                        dataSet.get(position).getWilling(),
                        dataSet.get(position).getPgsuitablefor());


                ListingData.getInstance(context).addlocation(
                        dataSet.get(position).getState(),
                        dataSet.get(position).getCity(),
                        dataSet.get(position).getAddress(),
                        dataSet.get(position).getLocality(),
                        dataSet.get(position).getProjectname());
                ListingData.getInstance(context).setLatLog(dataSet.get(position).getLatlong());


                ListingData.getInstance(context).addpropertydetails(
                        dataSet.get(position).getUnit1(),
                        dataSet.get(position).getUnit2(),
                        dataSet.get(position).getUnit3(),
                        dataSet.get(position).getAvailabilitydate(),
                        dataSet.get(position).getHomethings(),
                        dataSet.get(position).getOtherroom(),
                        dataSet.get(position).getBuiltupa(),
                        dataSet.get(position).getCarpeta(),
                        dataSet.get(position).getPlota(),
                        dataSet.get(position).getBed(),
                        dataSet.get(position).getBath(),
                        dataSet.get(position).getBalconie(),
                        dataSet.get(position).getTotalfloorget(),
                        dataSet.get(position).getReservedpark(),
                        dataSet.get(position).getAvailabilityspinget(),
                        dataSet.get(position).getAgeofpropertyspinget(),
                        dataSet.get(position).getFurnishedspinget(),
                        dataSet.get(position).getPossessionbyspinget(),
                        dataSet.get(position).getNoofroomspinget(),
                        dataSet.get(position).getQualityratingspinget(),
                        dataSet.get(position).getFloorallowedspinget(),
                        dataSet.get(position).getRoomavailabespinget(),
                        dataSet.get(position).getFlattype(),
                        dataSet.get(position).getRera()
                );


                ListingData.getInstance(context).addpricing(
                        dataSet.get(position).getPriceget(),
                        dataSet.get(position).getMaintenanceget(),
                        dataSet.get(position).getBookingamountget(),
                        dataSet.get(position).getDurationofrentagget(),
                        dataSet.get(position).getMonthofnoticeget(),
                        dataSet.get(position).getSecuritydepositspinget(),
                        dataSet.get(position).getMaintenancemonthlyspinget(),
                        dataSet.get(position).getOwnweshipspinget(),
                        dataSet.get(position).getTypeoffood(),
                        dataSet.get(position).getPricestep(),
                        dataSet.get(position).getFacilityincluded(),
                        dataSet.get(position).getWeekdays(),
                        dataSet.get(position).getWeeends(),
                        dataSet.get(position).getEarlylivingchargesget(),
                        dataSet.get(position).getContractdurationget(),
                        dataSet.get(position).getAnnualduespayableget(),
                        dataSet.get(position).getDepositeaget()
                );

                ListingData.getInstance(context).addfeatures(dataSet.get(position).getFacingspinget(),
                        dataSet.get(position).getUnitspinget(),
                        dataSet.get(position).getTypeofflooringspinget(),
                        dataSet.get(position).getPowerbackupspinget(),
                        dataSet.get(position).getLasttimespinget(),
                        dataSet.get(position).getPet(),
                        dataSet.get(position).getVisiter(),
                        dataSet.get(position).getSmoking(),
                        dataSet.get(position).getAlcohol(),
                        dataSet.get(position).getEvent(),
                        dataSet.get(position).getAnemitiesitem(),
                        dataSet.get(position).getMoreanemitiesitem(),
                        dataSet.get(position).getWatersourceitem(),
                        dataSet.get(position).getOverlookingitem(),
                        dataSet.get(position).getSomefeatureitem(),
                        dataSet.get(position).getByersitem(),
                        dataSet.get(position).getTimeitems(),
                        dataSet.get(position).getWidthfacingget(),
                        dataSet.get(position).getDescriptionget(),
                        dataSet.get(position).getBoundrywall(),
                        dataSet.get(position).getImage()
                        );
                ListingData.getInstance(context).setmobile(dataSet.get(position).getMobile());


                String date,fname,lname, email;
                date = dataSet.get(position).getReg_date();
                fname = dataSet.get(position).getFname();
                lname = dataSet.get(position).getLname();
                email = dataSet.get(position).getEmail();
                ListingData.getInstance(context).setdatename(date,fname,lname,email);



                context.startActivity(new Intent(context, DetailsPropertyShowActivity.class));


            }








        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListingData.getInstance(context).setnewprice(dataSet.get(position).getNewprice());
                ListingData.getInstance(context).propertyid(dataSet.get(position).getId());



                ListingData.getInstance(context).basicdetails(
                        dataSet.get(position).getUsertype(),
                        dataSet.get(position).getType(),
                        dataSet.get(position).getCategory(),
                        dataSet.get(position).getCategorytype(),
                        dataSet.get(position).getAgrement(),
                        dataSet.get(position).getPgavaliablefor(),
                        dataSet.get(position).getPgshareprivate(),
                        dataSet.get(position).getSharingspinnumber(),
                        dataSet.get(position).getPropertylistfor(),
                        dataSet.get(position).getWilling(),
                        dataSet.get(position).getPgsuitablefor());


                ListingData.getInstance(context).addlocation(
                        dataSet.get(position).getState(),
                        dataSet.get(position).getCity(),
                        dataSet.get(position).getAddress(),
                        dataSet.get(position).getLocality(),
                        dataSet.get(position).getProjectname());

                ListingData.getInstance(context).setLatLog(dataSet.get(position).getLatlong());

                ListingData.getInstance(context).addpropertydetails(
                        dataSet.get(position).getUnit1(),
                        dataSet.get(position).getUnit2(),
                        dataSet.get(position).getUnit3(),
                        dataSet.get(position).getAvailabilitydate(),
                        dataSet.get(position).getHomethings(),
                        dataSet.get(position).getOtherroom(),
                        dataSet.get(position).getBuiltupa(),
                        dataSet.get(position).getCarpeta(),
                        dataSet.get(position).getPlota(),
                        dataSet.get(position).getBed(),
                        dataSet.get(position).getBath(),
                        dataSet.get(position).getBalconie(),
                        dataSet.get(position).getTotalfloorget(),
                        dataSet.get(position).getReservedpark(),
                        dataSet.get(position).getAvailabilityspinget(),
                        dataSet.get(position).getAgeofpropertyspinget(),
                        dataSet.get(position).getFurnishedspinget(),
                        dataSet.get(position).getPossessionbyspinget(),
                        dataSet.get(position).getNoofroomspinget(),
                        dataSet.get(position).getQualityratingspinget(),
                        dataSet.get(position).getFloorallowedspinget(),
                        dataSet.get(position).getRoomavailabespinget(),
                        dataSet.get(position).getFlattype(),
                        dataSet.get(position).getRera()
                );


                ListingData.getInstance(context).addpricing(
                        dataSet.get(position).getPriceget(),
                        dataSet.get(position).getMaintenanceget(),
                        dataSet.get(position).getBookingamountget(),
                        dataSet.get(position).getDurationofrentagget(),
                        dataSet.get(position).getMonthofnoticeget(),
                        dataSet.get(position).getSecuritydepositspinget(),
                        dataSet.get(position).getMaintenancemonthlyspinget(),
                        dataSet.get(position).getOwnweshipspinget(),
                        dataSet.get(position).getTypeoffood(),
                        dataSet.get(position).getPricestep(),
                        dataSet.get(position).getFacilityincluded(),
                        dataSet.get(position).getWeekdays(),
                        dataSet.get(position).getWeeends(),
                        dataSet.get(position).getEarlylivingchargesget(),
                        dataSet.get(position).getContractdurationget(),
                        dataSet.get(position).getAnnualduespayableget(),
                        dataSet.get(position).getDepositeaget()
                );

                ListingData.getInstance(context).addfeatures(dataSet.get(position).getFacingspinget(),
                        dataSet.get(position).getUnitspinget(),
                        dataSet.get(position).getTypeofflooringspinget(),
                        dataSet.get(position).getPowerbackupspinget(),
                        dataSet.get(position).getLasttimespinget(),
                        dataSet.get(position).getPet(),
                        dataSet.get(position).getVisiter(),
                        dataSet.get(position).getSmoking(),
                        dataSet.get(position).getAlcohol(),
                        dataSet.get(position).getEvent(),
                        dataSet.get(position).getAnemitiesitem(),
                        dataSet.get(position).getMoreanemitiesitem(),
                        dataSet.get(position).getWatersourceitem(),
                        dataSet.get(position).getOverlookingitem(),
                        dataSet.get(position).getSomefeatureitem(),
                        dataSet.get(position).getByersitem(),
                        dataSet.get(position).getTimeitems(),
                        dataSet.get(position).getWidthfacingget(),
                        dataSet.get(position).getDescriptionget(),
                        dataSet.get(position).getBoundrywall(),
                        dataSet.get(position).getImage()
                );


                ListingData.getInstance(context).setinstrance("Update");
                context.startActivity(new Intent(context, BasicDetails.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    class ShowData extends RecyclerView.ViewHolder {
        TextView projectname,price, builtupa, builtupaunit,bhk, categorytype, address, listingfor, category,label, usertype, date, fname,lname;
        CardView cardview;
        RecyclerView recyclerviewlatest;
        ImageView propertyimage, shortlisted, edit;

        public ShowData(@NonNull View itemView) {
            super(itemView);

            price = itemView.findViewById(R.id.price);
            builtupa = itemView.findViewById(R.id.builtupa);
            builtupaunit = itemView.findViewById(R.id.builtupaunit);
            bhk = itemView.findViewById(R.id.bhk);
            categorytype = itemView.findViewById(R.id.categorytype);
            address = itemView.findViewById(R.id.address);
            cardview = itemView.findViewById(R.id.cardview);
            recyclerviewlatest = itemView.findViewById(R.id.recyclerviewlatest);
            listingfor = itemView.findViewById(R.id.listingfor);
            category = itemView.findViewById(R.id.category);
            propertyimage = itemView.findViewById(R.id.propertyimage);
            shortlisted = itemView.findViewById(R.id.shortlisted);
            edit = itemView.findViewById(R.id.edit);
            label = itemView.findViewById(R.id.label);
            usertype = itemView.findViewById(R.id.usertype);
            date = itemView.findViewById(R.id.date);
            fname = itemView.findViewById(R.id.fname);
            lname = itemView.findViewById(R.id.lname);



        }
    }

}



