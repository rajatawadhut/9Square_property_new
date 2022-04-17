package com.nine__square.property.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nine__square.property.Avtivities.ShowUSerList;
import com.nine__square.property.Models.UserListDataModel;
import com.nine__square.property.R;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ShowData>{

    private List<UserListDataModel> dataSet;
    private Context context;

    public UserListAdapter(
            List<UserListDataModel> dataSet, Context context) {
        this.dataSet = dataSet;
        this.context = context;
    }

    @NonNull
    @Override
    public ShowData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.userlistsearch, parent, false);
        return new ShowData(view);
    }

    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull final ShowData holder, final int position) {


//        if (dataSet.get(position).getImage().length() > 80){
//            System.out.println("getimagelength :; " + dataSet.get(position).getImage());
//            System.out.println("getimagelength :; " + dataSet.get(position).getImage().length());
//            Glide.with(context).load(dataSet.get(position).getImage()).into(holder.image);
//        }else{
//            System.out.println("getimagelength d :; " + dataSet.get(position).getImage());
//            System.out.println("getimagelength d:; " + dataSet.get(position).getImage().length());
//            Glide.with(context).load(R.drawable.personq).into(holder.image);
//        }


        Glide.with(context)
                .load(dataSet.get(position).getImage())
                .apply(RequestOptions
                        .placeholderOf(R.drawable.personq)
                        .dontAnimate()
                        .error(R.drawable.personq))
                .into(holder.image);





        holder.fullname.setText(dataSet.get(position).getFullname());
        holder.email.setText(dataSet.get(position).getEmail());
        holder.mobile.setText(dataSet.get(position).getMobile());
        holder.city.setText(dataSet.get(position).getCity());




        if(dataSet.get(position).getSubscriptionplan().equals("0")){
            String mobile, firstFourChars;

            mobile = dataSet.get(position).getMobile();
            firstFourChars = mobile.substring(0, 3);

            for (int i =0; i< 7; i++){
                firstFourChars = firstFourChars + "*";
            }
            holder.mobile.setText(firstFourChars);
            holder.emaillayout.setVisibility(View.GONE);
            holder.call_button.setVisibility(View.GONE);
        }



        holder.call_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = dataSet.get(position).getMobile();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));
                context.startActivity(intent);


            }
        });

        holder.flatlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , ShowUSerList.class);
                intent.putExtra("usertype1", dataSet.get(position).getUsertype());
                intent.putExtra("userid1", dataSet.get(position).getUserid());
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    class ShowData extends RecyclerView.ViewHolder {
        TextView fullname, email, mobile, city, property;
        ImageView image;
        CardView flatlayout;
        LinearLayout namelayout, emaillayout, mobilelayout, citylayout, propertylayout, call_button;


        public ShowData(@NonNull View itemView) {
            super(itemView);


            fullname = itemView.findViewById(R.id.fullname);
            email = itemView.findViewById(R.id.email);
            mobile = itemView.findViewById(R.id.mobile);
            city = itemView.findViewById(R.id.city);
            namelayout = itemView.findViewById(R.id.namelayout);
            emaillayout = itemView.findViewById(R.id.emaillayout);
            mobilelayout = itemView.findViewById(R.id.mobilelayout);
            citylayout = itemView.findViewById(R.id.citylayout);
            call_button = itemView.findViewById(R.id.call_button);
            image = itemView.findViewById(R.id.image);
            flatlayout = itemView.findViewById(R.id.flatlayout);



        }
    }

}



