package com.nine__square.property.Avtivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.nine__square.property.R;

public class ShowAdsActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;

    String geturl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_ads);



        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Advertisement");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(true);


        geturl = getIntent().getStringExtra("url");



        WebView wv = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = wv.getSettings();
        wv.getSettings().setJavaScriptEnabled(true);
        progressDialog.show();
        wv.setWebViewClient(new MyWebViewClient());
        webSettings.setBuiltInZoomControls(true);
        wv.loadUrl(geturl);
//        wv.loadUrl("https://www.amazon.com");

    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);

            if (!progressDialog.isShowing()) {
                progressDialog.show();
            }

            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            System.out.println("on finish");
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }

        }


    }
}
