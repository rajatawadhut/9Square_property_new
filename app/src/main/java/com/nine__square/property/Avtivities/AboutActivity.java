package com.nine__square.property.Avtivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;

import com.nine__square.property.R;


public class AboutActivity extends AppCompatActivity {


    private ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("About Us..");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });
        progressDialog = new ProgressDialog(AboutActivity.this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);

}
}
