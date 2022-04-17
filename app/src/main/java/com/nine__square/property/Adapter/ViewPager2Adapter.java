package com.nine__square.property.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.toolbox.ImageLoader;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nine__square.property.Fragments.AddImages;
import com.nine__square.property.Fragments.DeleteFragment;
import com.nine__square.property.Fragments.PicZoomFragment;
import com.nine__square.property.R;
import com.nine__square.property.Utils.ListingData;
import com.nine__square.property.Utils.SliderUtils2;

import java.util.List;

public class ViewPager2Adapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<SliderUtils2> sliderImg;
    private ImageLoader imageLoader;
    Bitmap bitmap;


    public ViewPager2Adapter(List<SliderUtils2> sliderImg, Context context) {
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
        final View view = layoutInflater.inflate(R.layout.propertyimagerecyle, null);
        final SliderUtils2 utils = sliderImg.get(position);


        ImageView propertyimage = view.findViewById(R.id.propertyimage);
        ImageView addimage = view.findViewById(R.id.addimage);
        ImageView delete = view.findViewById(R.id.delete);

//        Glide.with(context).load(utils.getImage()).into(propertyimage);


//        if (utils.getImage().length() > 80){
//            Glide.with(context).load(utils.getImage()).into(propertyimage);
//        }else{
//            Glide.with(context).load(R.drawable.nopropertyimage).into(propertyimage);
//        }


        Glide.with(context)
                .load(utils.getImage())
                .apply(RequestOptions
                        .placeholderOf(R.drawable.nopropertyimage)
                        .dontAnimate()
                        .error(R.drawable.nopropertyimage))
                .into(propertyimage);



        addimage.setVisibility(View.GONE);
            delete.setVisibility(View.GONE);

        if(ListingData.getInstance(context).getinstance() != null){

            if(ListingData.getInstance(context).getinstance().equals("Reply")){
            addimage.setVisibility(View.VISIBLE);
            delete.setVisibility(View.VISIBLE);
        }}


        if(sliderImg.get(position).getId().equals("")){
            delete.setVisibility(View.GONE);
        }

        System.out.println("getposition ::: "+ sliderImg.get(position));


        addimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("propertyid", ListingData.getInstance(context).getpropertyid()); // Put anything what you want


                Fragment fragment = new AddImages();
                fragment.setArguments(bundle);
                FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.containerfragment, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("img", utils.getImage()); // Put anything what you want
                bundle.putString("id", utils.getId()); // Put anything what you want



                Fragment fragment = new DeleteFragment();
                fragment.setArguments(bundle);

                FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.containerfragment, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        propertyimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("img", utils.getImage()); // Put anything what you want


                 Fragment fragment = new PicZoomFragment();
                fragment.setArguments(bundle);

                FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.containerfragment, fragment)
                        .addToBackStack(null)
                        .commit();

                /*ArrayList<String> list = new ArrayList<>();

                for( SliderUtils2 slid : sliderImg){
                    list.add(slid.getImage());
                }

                Intent intent = new Intent(context, ViewImageActivity.class);
                intent.putStringArrayListExtra("img", list);
                context.startActivity(intent);*/



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
