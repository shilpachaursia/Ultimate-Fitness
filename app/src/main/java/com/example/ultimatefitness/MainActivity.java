package com.example.ultimatefitness;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ultimatefitness.female.FemaleActivity;
import com.example.ultimatefitness.male.MaleActivity;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.firebase.messaging.FirebaseMessaging;


public class MainActivity extends AppCompatActivity {
    private InterstitialAd mInterstitialAd;
    ImageView Male,Female;
    TextView t1,t2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Male=(ImageView)findViewById(R.id.boy);
        Female=(ImageView)findViewById(R.id.girl);
        t1=(TextView)findViewById(R.id.boytext);
        t2=(TextView)findViewById(R.id.girltext);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                createPersonalizedAd();
            }
        });

        Male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mInterstitialAd != null) {
                    mInterstitialAd.show(MainActivity.this);
                } else {
                    Log.d("--AdMob--", "The interstitial ad wasn't ready yet.");
                }
                Intent intent = new Intent(MainActivity.this, MaleActivity.class);
                startActivity(intent);
            }

        });
        FirebaseMessaging.getInstance().subscribeToTopic("FitnessTips");

        Female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mInterstitialAd != null) {
                    mInterstitialAd.show(MainActivity.this);
                } else {
                    Log.d("--AdMob--", "The interstitial ad wasn't ready yet.");
                }
                Intent intent = new Intent(MainActivity.this, FemaleActivity.class);
                startActivity(intent);
            }
        });

    } 

    private void createPersonalizedAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        createInterstitialAd(adRequest);
    }
    private void createInterstitialAd(AdRequest adRequest){
        InterstitialAd.load(this,"ca-app-pub-2342822035961981/5833797875", adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                // The mInterstitialAd reference will be null until
                // an ad is loaded.
                mInterstitialAd = interstitialAd;
                Log.d("--AdMob--", "onAdLoaded");
                mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                    @Override
                    public void onAdDismissedFullScreenContent() {
                        // Called when fullscreen content is dismissed.
                        Log.d("--AdMob--", "The ad was dismissed.");

                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                        // Called when fullscreen content failed to show.
                        Log.d("--AdMob--", "The ad failed to show.");
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        // Called when fullscreen content is shown.
                        // Make sure to set your reference to null so you don't
                        // show it a second time.
                        mInterstitialAd = null;
                        Log.d("--AdMob--", "The ad was shown.");
                    }
                });
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error
                Log.d("--AdMob--", loadAdError.getMessage());
                mInterstitialAd = null;
            }
        });
    }
}