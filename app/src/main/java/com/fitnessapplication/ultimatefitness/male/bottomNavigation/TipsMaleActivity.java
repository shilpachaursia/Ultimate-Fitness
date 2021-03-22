package com.fitnessapplication.ultimatefitness.male.bottomNavigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;

import com.fitnessapplication.ultimatefitness.R;
import com.fitnessapplication.ultimatefitness.bottom_menu.WeightGainFragment;
import com.fitnessapplication.ultimatefitness.bottom_menu.weightLossFragment;
import com.fitnessapplication.ultimatefitness.male.MaleActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TipsMaleActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ImageView backbtn;
    private AdView mAdView;
    CardView weightLoss,weightGain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips_male);
        weightLoss=findViewById(R.id.weightLossLayout);
        weightGain=findViewById(R.id.weightgainLayout);
        weightGain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weightGain.setVisibility(View.GONE);
                weightLoss.setVisibility(View.GONE);
                FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.framecontainer,new WeightGainFragment()).commit();
            }
        });
        weightLoss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weightGain.setVisibility(View.GONE);
                weightLoss.setVisibility(View.GONE);
                FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.framecontainer,new weightLossFragment()).commit();
            }
        });
        bottomNavigationView=findViewById(R.id.bottom_navigation_view);
        backbtn= findViewById(R.id.tipsback);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(TipsMaleActivity.this,MaleActivity.class);
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
                Toast.makeText(TipsMaleActivity.this, "Ad Loaded", Toast.LENGTH_SHORT).show();
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
                        Intent exercise= new Intent(TipsMaleActivity.this, MaleActivity.class);
                        exercise.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(exercise);
                        finish();
                        break;
                    case R.id.plan:
                        Intent intent1= new Intent(TipsMaleActivity.this, PlanMaleActivity.class);
                        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent1);
                        finish();
                        break;
                    case R.id.tips:
                        Intent tips=new Intent(TipsMaleActivity.this,TipsMaleActivity.class);
                        tips.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        break;
                    case R.id.utility:
                        Intent intent3= new Intent(TipsMaleActivity.this, UtiltiyMaleActivity.class);
                        intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent3);
                        finish();
                        break;
                    case R.id.login:
                        Intent intent4= new Intent(TipsMaleActivity.this, RegisterActivity.class);
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