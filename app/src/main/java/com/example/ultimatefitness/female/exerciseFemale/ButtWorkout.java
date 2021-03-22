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
import com.example.ultimatefitness.female.exerciseFemale.ButtAllExercise.HighSteppingButtFragment;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;

public class ButtWorkout extends AppCompatActivity {
    ImageView butt_back_btn;
    FrameLayout frameLayout;
    Button startButtExercise;
    RelativeLayout relativeLayout,toolbar;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butt_workout);
        butt_back_btn = findViewById(R.id.butt_bact_btn);
        startButtExercise=findViewById(R.id.buttStart);
        frameLayout=findViewById(R.id.frameButt);
        relativeLayout=findViewById(R.id.ButtChange);
        toolbar=findViewById(R.id.buttToolbar);
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                super.onAdLoaded();
                Toast.makeText(ButtWorkout.this, "Ad Loaded", Toast.LENGTH_SHORT).show();
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

        butt_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ButtWorkout.this, FemaleActivity.class);
                startActivity(intent);
            }
        });
        startButtExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HighSteppingButtFragment highSteppingButtFragment=new HighSteppingButtFragment();
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameButt,highSteppingButtFragment);
                transaction.commit();
                relativeLayout.setVisibility(View.GONE);
                toolbar.setVisibility(View.GONE);
            }
        });
    }
}