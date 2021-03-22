package com.fitnessapplication.ultimatefitness.male.bottomNavigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.fitnessapplication.ultimatefitness.R;
import com.fitnessapplication.ultimatefitness.calculators.BmiCalculator;
import com.fitnessapplication.ultimatefitness.calculators.FatCalculator;
import com.fitnessapplication.ultimatefitness.calculators.ProteinCalculator;
import com.fitnessapplication.ultimatefitness.male.MaleActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UtiltiyMaleActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ImageView bmi,protein,fat,backbtn;
    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utiltiy_male);
        bottomNavigationView=findViewById(R.id.bottom_navigation_view);

        bmi=(ImageView)findViewById(R.id.bmi_cal);
        protein=(ImageView)findViewById(R.id.protein_cal);
        fat=(ImageView)findViewById(R.id.fat_cal);

        backbtn= findViewById(R.id.utility_back);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(UtiltiyMaleActivity.this,MaleActivity.class);
                startActivity(intent);
            }
        });

        bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UtiltiyMaleActivity.this, BmiCalculator.class);
                startActivity(intent);
            }
        });

        protein.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UtiltiyMaleActivity.this, ProteinCalculator.class);
                startActivity(intent);
            }
        });

        fat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UtiltiyMaleActivity.this, FatCalculator.class);
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
                Toast.makeText(UtiltiyMaleActivity.this, "Ad Loaded", Toast.LENGTH_SHORT).show();
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


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.exercise:
                        Intent exercise= new Intent(UtiltiyMaleActivity.this, MaleActivity.class);
                        exercise.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(exercise);
                        finish();
                        break;
                    case R.id.plan:
                        Intent intent1= new Intent(UtiltiyMaleActivity.this, PlanMaleActivity.class);
                        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent1);
                        finish();
                        break;
                    case R.id.tips:
                        Intent intent2= new Intent(UtiltiyMaleActivity.this, TipsMaleActivity.class);
                        intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent2);
                        finish();
                        break;
                    case R.id.utility:
                        Intent utility=new Intent(UtiltiyMaleActivity.this,UtiltiyMaleActivity.class);
                        utility.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        break;
                    case R.id.login:
                        Intent intent4= new Intent(UtiltiyMaleActivity.this, RegisterActivity.class);
                        intent4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent4);
                        finish();
                        break;
                }
                return false;
            }
        });
    }
}