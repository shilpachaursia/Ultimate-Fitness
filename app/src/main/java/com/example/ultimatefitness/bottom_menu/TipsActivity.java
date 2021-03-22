package com.example.ultimatefitness.bottom_menu;

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

import com.example.ultimatefitness.R;
import com.example.ultimatefitness.female.FemaleActivity;
import com.example.ultimatefitness.male.bottomNavigation.RegisterActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class TipsActivity extends AppCompatActivity {
    private AdView mAdView;
    CardView weightLoss,weightGain,weightGain2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        BottomNavigationView bottomNavigationView;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);
        ImageView backbtn;
        backbtn= findViewById(R.id.back_btn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(TipsActivity.this,FemaleActivity.class);
                startActivity(intent);
            }
        });
        weightGain=(CardView)findViewById(R.id.weightgainLayout);
        weightLoss=(CardView)findViewById(R.id.weightLossLayout);
        weightGain2=(CardView)findViewById(R.id.weightgainLayout1) ;
        weightGain2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weightGain2.setVisibility(View.GONE);
                weightGain.setVisibility(View.GONE);
                weightLoss.setVisibility(View.GONE);
                FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainer,new WeightGainFragment()).commit();

            }
        });
        weightLoss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weightGain2.setVisibility(View.GONE);
                weightLoss.setVisibility(View.GONE);
                weightGain.setVisibility(View.GONE);
                FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainer,new weightLossFragment()).commit();
            }
        });
        weightGain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weightGain2.setVisibility(View.GONE);
                weightLoss.setVisibility(View.GONE);
                weightGain.setVisibility(View.GONE);
                FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainer,new WeightGainFragment()).commit();
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
                Toast.makeText(TipsActivity.this, "Ad Loaded", Toast.LENGTH_SHORT).show();
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
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.exercise:
                        Intent intent = new Intent(TipsActivity.this, FemaleActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.plan:
                        Intent intent4= new Intent(TipsActivity.this, PlanActivity.class);
                        intent4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent4);
                        finish();
                        break;
                    case R.id.tips:
                        Intent tips=new Intent(TipsActivity.this,TipsActivity.class);
                        tips.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        break;
                    case R.id.utility:
                        Intent intent2 = new Intent(TipsActivity.this, UtilityActivity.class);
                        intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent2);
                        finish();
                        break;
                    case R.id.login:
                        Intent intent3 = new Intent(TipsActivity.this, RegisterActivity.class);
                        startActivity(intent3);finish();
                        break;
                }
                return false;
            }
        });
    }
}