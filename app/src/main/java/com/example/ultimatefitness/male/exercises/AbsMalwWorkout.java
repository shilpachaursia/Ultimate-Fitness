package com.example.ultimatefitness.male.exercises;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.ultimatefitness.R;
import com.example.ultimatefitness.male.MaleActivity;
import com.example.ultimatefitness.male.exercises.absMenAllExercise.BirdDogFragment;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;

public class AbsMalwWorkout extends AppCompatActivity {
RelativeLayout relativeLayout,toolbar;
FrameLayout frameLayout;
    private AdView mAdView;
    Button startBtn;
    ImageView backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abs_malw_workout);
        relativeLayout=findViewById(R.id.absFrameChanger);
        toolbar=findViewById(R.id.absMaleToolbar);
        frameLayout=findViewById(R.id.absFrameContainer);
        mAdView=findViewById(R.id.adView);
        startBtn=findViewById(R.id.Abs_startExercise);
        backBtn=findViewById(R.id.abs_back_btn);
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                super.onAdLoaded();
                Toast.makeText(AbsMalwWorkout.this, "Ad Loaded", Toast.LENGTH_SHORT).show();
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
        startActivity(new Intent(AbsMalwWorkout.this, MaleActivity.class));
    }
    public void moveFragment(View view) {
        Fragment fragment = new BirdDogFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.absFrameContainer, fragment, fragment.getClass().getSimpleName())
                .commit();
//        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction()
//                .replace(R.id.absFrameContainer,new aFragment());
//        transaction.commit();
        relativeLayout.setVisibility(View.GONE);
        toolbar.setVisibility(View.GONE);
    }
}