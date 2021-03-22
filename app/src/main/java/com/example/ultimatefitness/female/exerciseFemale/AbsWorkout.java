package com.example.ultimatefitness.female.exerciseFemale;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.ultimatefitness.R;
import com.example.ultimatefitness.female.FemaleActivity;
import com.example.ultimatefitness.female.exerciseFemale.absFemaleAllExercise.CrossJumpingJackAbsFragment;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;


public class AbsWorkout extends AppCompatActivity {
    ImageView abs_back_btn;
    Button startExerciseBtn;
    FrameLayout frameLayout;
    RelativeLayout scrollView,toolbar;
    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abs_workout);
        abs_back_btn= findViewById(R.id.abs_bact_btn);
        startExerciseBtn= findViewById(R.id.startExercise);
        frameLayout=findViewById(R.id.absFrameLayout);
        scrollView=findViewById(R.id.absScrollView);
        toolbar=findViewById(R.id.absToolbar);
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                super.onAdLoaded();
                Toast.makeText(AbsWorkout.this, "Ad Loaded", Toast.LENGTH_SHORT).show();
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

        abs_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AbsWorkout.this, FemaleActivity.class);
                startActivity(intent);
            }
        });
        startExerciseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CrossJumpingJackAbsFragment crossJumpingJackAbsFragment= new CrossJumpingJackAbsFragment();
                FragmentTransaction transaction= getSupportFragmentManager().beginTransaction().replace(R.id.absFrameLayout,crossJumpingJackAbsFragment);
                transaction.commit();
                scrollView.setVisibility(View.GONE);
                toolbar.setVisibility(View.GONE);
            }
        });

}

}