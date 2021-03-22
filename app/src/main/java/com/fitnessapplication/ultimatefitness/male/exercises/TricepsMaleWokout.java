package com.fitnessapplication.ultimatefitness.male.exercises;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.fitnessapplication.ultimatefitness.R;
import com.fitnessapplication.ultimatefitness.male.MaleActivity;
import com.fitnessapplication.ultimatefitness.male.exercises.tricepsMaleAllExericse.GripBenchPressFragment;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;

public class TricepsMaleWokout extends AppCompatActivity {
        RelativeLayout relativeLayout,toolbar;
        FrameLayout frameLayout;
        private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triceps_male_wokout);
        relativeLayout=findViewById(R.id.tricepsMaleFrameChanger);
        frameLayout=findViewById(R.id.bicepsMaleFrameContainer);
        toolbar=findViewById(R.id.tricepsToolBar);
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                super.onAdLoaded();
                Toast.makeText(TricepsMaleWokout.this, "Ad Loaded", Toast.LENGTH_SHORT).show();
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

    }

    public void back(View view) {
        startActivity(new Intent(TricepsMaleWokout.this, MaleActivity.class));
    }

    public void moveFragment(View view) {
        GripBenchPressFragment gripBenchPressFragment=new GripBenchPressFragment();
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction()
                .replace(R.id.tricepsMaleFrameContainer,gripBenchPressFragment);
        transaction.commit();
        relativeLayout.setVisibility(View.GONE);
        toolbar.setVisibility(View.GONE);
    }
}