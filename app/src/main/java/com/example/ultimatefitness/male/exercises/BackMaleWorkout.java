package com.example.ultimatefitness.male.exercises;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.ultimatefitness.R;
import com.example.ultimatefitness.male.MaleActivity;
import com.example.ultimatefitness.male.exercises.backMaleAllExercise.BackBarbelSquatFragment;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;

public class BackMaleWorkout extends AppCompatActivity {
RelativeLayout relativeLayout,toolbar;
FrameLayout frameLayout;
private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_male_workout);
        relativeLayout=findViewById(R.id.backMaleFrameChange);
        frameLayout=findViewById(R.id.backMaleFrameContainer);
        toolbar=findViewById(R.id.backMaleToolbar);
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                super.onAdLoaded();
                Toast.makeText(BackMaleWorkout.this, "Ad Loaded", Toast.LENGTH_SHORT).show();
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
        startActivity(new Intent(BackMaleWorkout.this, MaleActivity.class));
    }

    public void moveFragment(View view) {
        BackBarbelSquatFragment backBarbelSquatFragment=new BackBarbelSquatFragment();
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.backMaleFrameContainer,backBarbelSquatFragment).commit();
        relativeLayout.setVisibility(View.GONE);
        toolbar.setVisibility(View.GONE);
    }
}