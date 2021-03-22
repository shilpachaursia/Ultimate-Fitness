package com.example.ultimatefitness.male.drawerMenuActivity;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ultimatefitness.R;


public class TermAndConditionActivity extends AppCompatActivity {
WebView termandcondition;
public String fileName="TeamAndCondition";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_and_condition);
        termandcondition=findViewById(R.id.termAndCondition);
        termandcondition.getSettings().setJavaScriptEnabled(true);
        termandcondition.loadUrl("file:///android_asset/"+ fileName);
    }
}