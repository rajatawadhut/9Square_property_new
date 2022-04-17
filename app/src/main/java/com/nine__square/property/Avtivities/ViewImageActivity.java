package com.nine__square.property.Avtivities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.nine__square.property.R;


import java.util.ArrayList;

public class ViewImageActivity extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);

/*
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Property Images");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        test = getIntent().getStringArrayListExtra("img");

        scrollGalleryView = findViewById(R.id.scroll_gallery_view);

        scrollGalleryView
                .setThumbnailSize(200)
                .setZoom(true)
                .withHiddenThumbnails(false)
                .hideThumbnailsOnClick(true)
                .hideThumbnailsAfter(5000)
                .addOnImageClickListener((position) -> {
                    Log.i(getClass().getName(), "You have clicked on image #" + position);
                })
                .setFragmentManager(getSupportFragmentManager());



        if(test != null && test.size()>0){
            for (String imageUrl : test) {
                scrollGalleryView.addMedia(
                        MediaInfo.mediaLoader(new PicassoImageLoader(imageUrl))
                );
            }
        }*/
    }


}