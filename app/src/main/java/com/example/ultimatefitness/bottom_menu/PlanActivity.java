package com.example.ultimatefitness.bottom_menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ultimatefitness.R;
import com.example.ultimatefitness.female.FemaleActivity;
import com.example.ultimatefitness.male.bottomNavigation.RegisterActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PlanActivity extends AppCompatActivity {
    ImageView img1, img2, img3, img4, img5, img6, planBackBtn;
    private AdView mAdView;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
        img1 = findViewById(R.id.fullbodylock);
        img2 = findViewById(R.id.abslock);
        img3 = findViewById(R.id.buttlock);
        img4 = findViewById(R.id.armlock);
        img5 = findViewById(R.id.thighlock);
        img6 = findViewById(R.id.abslock1);
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                super.onAdLoaded();
                Toast.makeText(PlanActivity.this, "Ad Loaded", Toast.LENGTH_SHORT).show();
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
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PlanActivity.this, "cannot open without pay", Toast.LENGTH_SHORT).show();
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PlanActivity.this, "cannot open without pay", Toast.LENGTH_SHORT).show();
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PlanActivity.this, "cannot open without pay", Toast.LENGTH_SHORT).show();
            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PlanActivity.this, "cannot open without pay", Toast.LENGTH_SHORT).show();
            }
        });
        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PlanActivity.this, "cannot open without pay", Toast.LENGTH_SHORT).show();
            }
        });
        img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PlanActivity.this, "cannot open without pay", Toast.LENGTH_SHORT).show();
            }
        });
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.exercise:
                        Intent intent = new Intent(PlanActivity.this, FemaleActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.plan:
                        Intent intent1=new Intent(PlanActivity.this,PlanActivity.class);
                        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            break;
                    case R.id.tips:
                        Intent intent4 = new Intent(PlanActivity.this, TipsActivity.class);
                        intent4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent4);
                        finish();
                        break;
                    case R.id.utility:
                        Intent intent2 = new Intent(PlanActivity.this, UtilityActivity.class);
                        intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent2);
                        finish();
                        break;
                    case R.id.login:
                        Intent intent3 = new Intent(PlanActivity.this, RegisterActivity.class);
                        startActivity(intent3);
                        finish();
                        break;
                }
                return false;
            }
        });
    }
    public void backbtn(View view) {
        startActivity(new Intent(PlanActivity.this,FemaleActivity.class));
    }
}
