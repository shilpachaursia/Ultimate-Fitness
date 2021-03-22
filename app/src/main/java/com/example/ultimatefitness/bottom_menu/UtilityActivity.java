package com.example.ultimatefitness.bottom_menu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ultimatefitness.R;
import com.example.ultimatefitness.calculators.BmiCalculator;
import com.example.ultimatefitness.calculators.FatCalculator;
import com.example.ultimatefitness.calculators.ProteinCalculator;
import com.example.ultimatefitness.female.FemaleActivity;
import com.example.ultimatefitness.male.bottomNavigation.RegisterActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class UtilityActivity extends AppCompatActivity {
    ImageView bmi,protein,fat,femaleback;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        BottomNavigationView bottomNavigationView;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utility);
        femaleback=findViewById(R.id.utilityFemaleBack);
        femaleback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(UtilityActivity.this,FemaleActivity.class);
                startActivity(intent);
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                super.onAdLoaded();
                Toast.makeText(UtilityActivity.this, "Ad Loaded", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                // Code to be executed when an ad request fails.
                super.onAdFailedToLoad(adError);
                mAdView.loadAd(adRequest);
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
                super.onAdOpened();
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
                super.onAdOpened();
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });
        bottomNavigationView =(BottomNavigationView)findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.exercise:
                        Intent intent = new Intent(UtilityActivity.this, FemaleActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        break;
                    case R.id.plan:
                        Intent plan= new Intent(UtilityActivity.this, PlanActivity.class);
                        plan.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(plan);
                        break;
                    case R.id.tips:
                        Intent tips = new Intent(UtilityActivity.this, TipsActivity.class);
                        tips.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(tips);
                        break;
                    case R.id.utility:
                        Intent utility=new Intent(UtilityActivity.this,UtilityActivity.class);
                        utility.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        break;
                    case R.id.login:
                        Intent login = new Intent(UtilityActivity.this, RegisterActivity.class);
                        login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(login);
                        break;
                }
                return false;
            }
        });
        bmi=(ImageView)findViewById(R.id.bmi_cal);
        protein=(ImageView)findViewById(R.id.protein_cal);
        fat=(ImageView)findViewById(R.id.fat_cal);
        bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UtilityActivity.this, BmiCalculator.class);
                startActivity(intent);
            }
        });
        protein.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UtilityActivity.this, ProteinCalculator.class);
                startActivity(intent);
            }
        });
        fat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UtilityActivity.this, FatCalculator.class);
                startActivity(intent);
            }
        });

    }
}