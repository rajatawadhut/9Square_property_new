package com.nine__square.property.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.nine__square.property.Models.ReviewDataModel;
import com.nine__square.property.R;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ShowData>{

    private List<ReviewDataModel> dataSet;
    private Context context;

    public ReviewAdapter(
            List<ReviewDataModel> dataSet, Context context) {
        this.dataSet = dataSet;
        this.context = context;
    }

    @NonNull
    @Override
    public ShowData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_rating_show, parent, false);
        return new ShowData(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final ShowData holder, final int position) {

//        if(dataSet.get(position).getValueid().equals("1")){
//            holder.reviewbtn.setVisibility(View.GONE);
//        }else{
//            holder.reviewbtn.setVisibility(View.VISIBLE);
//        }


        final String value2 =  dataSet.get(position).getReview();
        if(value2.length()>17){
            holder.review.setText((value2.substring(0, Math.min(18, value2.length())) + "...."));
            holder.tvShowMore.setVisibility(View.VISIBLE);

        }else{
            holder.review.setText(value2);
            holder.tvShowMore.setVisibility(View.GONE);

        }

        holder.ratingBar.setEnabled(false);
//        holder.review.setText(dataSet.get(position).getReview());
        String fullname = dataSet.get(position).getFname()+ " "+ dataSet.get(position).getLname();
        holder.ratingpoint.setText(dataSet.get(position).getRating());
        holder.name.setText(fullname);
        holder.ratingBar.setRating(Float.parseFloat(dataSet.get(position).getRating()));
//        holder.review.setText(dataSet.get(position).getReview());

        LayerDrawable stars = (LayerDrawable) holder.ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(context.getResources().getColor(R.color.Gold), PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(0).setColorFilter(Color.GRAY,PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(1).setColorFilter(context.getResources().getColor(R.color.Gold), PorterDuff.Mode.SRC_ATOP);

        if (Float.parseFloat(dataSet.get(position).getRating()) <= 2) {
            holder.ratingpoint.setTextColor(Color.BLACK);
        }
        if (Float.parseFloat(dataSet.get(position).getRating()) > 2 && Float.parseFloat(dataSet.get(position).getRating()) <= 3) {
//            holder.ratingpoint.setTextColor(Color.rgb(255,170,0));
            holder.ratingpoint.setTextColor(Color.BLACK);
        }
        if (Float.parseFloat(dataSet.get(position).getRating()) > 3) {
//            holder.ratingpoint.setTextColor(Color.rgb(0,128,0));
            holder.ratingpoint.setTextColor(Color.BLACK);
        }
//
//        holder.flatlayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Toast.makeText(context, dataSet.get(position).getRating(), Toast.LENGTH_SHORT).show();
//            }
//        });



        holder.tvShowMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.tvShowMore.getText().toString().equals("Show More..")) {
                    holder.tvShowMore.setText("Show Less..");
                    holder.review.setText(dataSet.get(position).getReview());
                    holder.review.setMaxLines(Integer.MAX_VALUE);
                } else {
                    holder.tvShowMore.setText("Show More..");
                    holder.review.setMaxLines(1);
                    String value2 =  dataSet.get(position).getReview();
                    holder.review.setText((value2.substring(0, Math.min(13, value2.length())) + "..."));
                    holder.review.setEllipsize(TextUtils.TruncateAt.END);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    class ShowData extends RecyclerView.ViewHolder {
        TextView review,name, ratingpoint, tvShowMore;
        RatingBar ratingBar;
//        CardView flatlayout;
        Button reviewbtn;



        public ShowData(@NonNull View itemView) {
            super(itemView);


            name = itemView.findViewById(R.id.name);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            ratingpoint = itemView.findViewById(R.id.ratingpoint);
            review = itemView.findViewById(R.id.review);
//            flatlayout = itemView.findViewById(R.id.flatlayout);
            reviewbtn = itemView.findViewById(R.id.reviewbtn);
            tvShowMore = itemView.findViewById(R.id.tvShowMore);



        }
    }

}



