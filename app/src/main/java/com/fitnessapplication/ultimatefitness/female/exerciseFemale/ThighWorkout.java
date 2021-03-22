package com.fitnessapplication.ultimatefitness.female.exerciseFemale;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.fitnessapplication.ultimatefitness.R;
import com.fitnessapplication.ultimatefitness.female.FemaleActivity;
import com.fitnessapplication.ultimatefitness.female.exerciseFemale.thigh.HighSteppingThighFragment;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;

public class ThighWorkout extends AppCompatActivity {
    ImageView thigh_back_btn;
    FrameLayout frameLayout;
    RelativeLayout relativeLayout,toolbar;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thigh_workout);

        thigh_back_btn=findViewById(R.id.thigh_bact_btn);
        frameLayout=findViewById(R.id.frameContainerThighExercise);
        relativeLayout=findViewById(R.id.frameChanger);
        toolbar=findViewById(R.id.thighToolbar);
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                super.onAdLoaded();
                Toast.makeText(ThighWorkout.this, "Ad Loaded", Toast.LENGTH_SHORT).show();
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

        // searchView= (SearchView)findViewById(R.id.search_view);
        thigh_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ThighWorkout.this, FemaleActivity.class);
                startActivity(intent);
            }
        });
    }

    public void ExerciseStartMethod(View view) {
//        HighSteppingThighFragment highSteppingThighFragment=new HighSteppingThighFragment();
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction().replace(R.id.frameContainerThighExercise,new HighSteppingThighFragment());
        transaction.commit();
        relativeLayout.setVisibility(View.GONE);
        toolbar.setVisibility(View.GONE);

    }
}