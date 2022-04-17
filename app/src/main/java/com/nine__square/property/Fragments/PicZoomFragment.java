package com.nine__square.property.Fragments;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jsibbold.zoomage.ZoomageView;
import com.nine__square.property.R;


public class PicZoomFragment extends Fragment {

    String getimg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_pic_zoom, container, false);

        Toolbar toolbar = v.findViewById(R.id.toolbar);
        toolbar.setTitle("Property Image");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });




        Bundle bundle = this.getArguments();

        if(bundle != null){
            getimg = bundle.getString("img");
        }

        ZoomageView imageView = (ZoomageView) v.findViewById(R.id.photo_view);


        Glide.with(getContext())
                .load(getimg)
                .apply(RequestOptions
                        .placeholderOf(R.drawable.nopropertyimage)
                        .dontAnimate()
                        .error(R.drawable.nopropertyimage))
                .into(imageView);




        return v;
    }

}