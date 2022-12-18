package com.nine__square.property.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.toolbox.ImageLoader;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nine__square.property.Avtivities.ShowAdsActivity;
import com.nine__square.property.Models.AdsDataModel;
import com.nine__square.property.R;

import java.util.List;

public class AdsViewPager extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<AdsDataModel> sliderImg;
    private ImageLoader imageLoader;

    Bitmap bitmap;


    public AdsViewPager(List<AdsDataModel> sliderImg, Context context) {
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
        final View view = layoutInflater.inflate(R.layout.adslayout, null);
        final AdsDataModel utils = sliderImg.get(position);


        ImageView propertyimage = view.findViewById(R.id.adsimage);
        CardView cardImage = view.findViewById(R.id.cardImage);


        Glide.with(context)
                .load(utils.getImage())
                .apply(RequestOptions
                        .placeholderOf(R.drawable.nopropertyimage)
                        .dontAnimate()
                        .error(R.drawable.nopropertyimage))
                .into(propertyimage);

        cardImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!utils.getUrl().equals("#")){
                    Intent intent = new Intent(context, ShowAdsActivity.class);
                    intent.putExtra("url", utils.getUrl());
                    context.startActivity(intent);
                }
            }
        });








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
