package com.example.ultimatefitness.male.drawerMenuActivity;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ultimatefitness.R;


public class PrivacyActivity extends AppCompatActivity {
        WebView webView;
        public  String fileName="privacyPolicy";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);
        webView=findViewById(R.id.privacy_policy);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/"+fileName);
    }
}