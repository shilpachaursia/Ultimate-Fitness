package com.example.ultimatefitness.male;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ultimatefitness.R;
import com.example.ultimatefitness.female.FemaleActivity;
import com.example.ultimatefitness.male.bottomNavigation.LoginActivity;
import com.example.ultimatefitness.male.bottomNavigation.PlanMaleActivity;
import com.example.ultimatefitness.male.bottomNavigation.ProfileActivity;
import com.example.ultimatefitness.male.bottomNavigation.RegisterActivity;
import com.example.ultimatefitness.male.bottomNavigation.TipsMaleActivity;
import com.example.ultimatefitness.male.bottomNavigation.UtiltiyMaleActivity;
import com.example.ultimatefitness.male.drawerMenuActivity.FeedbackActivity;
import com.example.ultimatefitness.male.drawerMenuActivity.PrivacyActivity;
import com.example.ultimatefitness.male.drawerMenuActivity.SettingActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MaleActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    static final float END_SCALE=0.7f;
    ImageView menuside,sharebtn;
    BottomNavigationView bottomNavigationView;
    SwitchCompat gender;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    RelativeLayout contentView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_male);
        sharebtn=findViewById(R.id.shareBtn);
        gender=findViewById(R.id.switchGender);
        //share
        sharebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Here is the share content body";//url of playstore app
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });
        //gender Switch
        SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
        gender.setChecked(sharedPreferences.getBoolean("value",true));
        gender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gender.isChecked()){
                    SharedPreferences.Editor e=getSharedPreferences("save",MODE_PRIVATE).edit();
                    e.putBoolean("value",true);
                    e.apply();
                    gender.setChecked(true);
                    startActivity(new Intent(MaleActivity.this,FemaleActivity.class));
                }else{
                    SharedPreferences.Editor editor=getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value",false);
                    editor.apply();
                    gender.setChecked(false);

                }
            }
        });
        //bottom menu
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.exercise:
                        Intent exercise=new Intent(MaleActivity.this,MaleActivity.class);
                        exercise.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        break;
                    case R.id.plan:
                        Intent plan = new Intent(MaleActivity.this, PlanMaleActivity.class);
                        plan.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(plan);
                        finish();
                        break;
                    case R.id.tips:
                        Intent tips = new Intent(MaleActivity.this, TipsMaleActivity.class);
                        tips.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(tips);
                        finish();
                        break;
                    case R.id.utility:
                        Intent utility=new Intent(MaleActivity.this,UtiltiyMaleActivity.class);
                        utility.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(utility);
                        finish();
                        break;
                    case R.id.login:
                        Intent login = new Intent(MaleActivity.this, RegisterActivity.class);
                        login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(login);
                        finish();
                        break;
                }
                return false;
            }
        });
        menuside = (ImageView) findViewById(R.id.menuside);
        contentView = findViewById(R.id.content1);
        //drawer layout id's
        drawerLayout = findViewById(R.id.drawer_layout1);
        navigationView = findViewById(R.id.navigation_view_leftSide);
        //navigation view
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
        navigationDrawar();
        //recycler view
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.menRecycleView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        AdapterMen adapter = new AdapterMen(dataQueue(), getApplicationContext());
        mRecyclerView.setAdapter(adapter);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int i=item.getItemId();
                if (i==R.id.nav_home){
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else if (i==R.id.nav_gender){
                   startActivity(new Intent(MaleActivity.this,FemaleActivity.class));
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else if (i==R.id.nav_login){
                   startActivity(new Intent(MaleActivity.this, LoginActivity.class));
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else if (i==R.id.nav_profile){
                   startActivity(new Intent(MaleActivity.this, ProfileActivity.class));
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else if (i==R.id.nav_logout){
                    Intent intent=new Intent(MaleActivity.this, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else if (i==R.id.nav_share){
                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    String shareBody = "Here is the share content body";//url of playstore app
                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                    startActivity(Intent.createChooser(sharingIntent, "Share via"));
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else if (i==R.id.nav_rate){
                    startActivity(new Intent(MaleActivity.this, FeedbackActivity.class));
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else if (i==R.id.nav_feedback){
                    startActivity(new Intent(MaleActivity.this, FeedbackActivity.class));
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else if (i==R.id.nav_setting){
                    startActivity(new Intent(MaleActivity.this, SettingActivity.class));
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else if (i==R.id.nav_privacy){
                    startActivity(new Intent(MaleActivity.this, PrivacyActivity.class));
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                return true;
            }
        });
        ad();
    }

    private void ad() {
        //admob banner
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });//ca-app-pub-2342822035961981~6396888741
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                super.onAdLoaded();
                Toast.makeText(MaleActivity.this, "Ad Loaded", Toast.LENGTH_SHORT).show();
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
        //video Ad
        InterstitialAd.load(this,"ca-app-pub-2342822035961981/5833797875",adRequest,new InterstitialAdLoadCallback(){
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                super.onAdLoaded(interstitialAd);
                mInterstitialAd=interstitialAd;
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                mInterstitialAd=null;
            }
        });
    }
    private void navigationDrawar() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
        menuside.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        animateNavigationDrawer();
    }
    private void animateNavigationDrawer() {
        drawerLayout.setScrimColor(Color.TRANSPARENT);
//        drawerLayout.setScrimColor(getResources().getColor(R.color.pink));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);
                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else
            super.onBackPressed();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }
    public ArrayList<ModelMen> dataQueue() {
        ArrayList<ModelMen> holder = new ArrayList<>();
        ModelMen obj1 = new ModelMen();
        obj1.setImage(R.drawable.malebg);
        obj1.setExerciseName("FULLBODY WORKOUT");
        obj1.setExerciseStart("START FULLBODY");
        holder.add(obj1);
        ModelMen obj2 = new ModelMen();
        obj2.setImage(R.drawable.chestboybg);
        obj2.setExerciseName("CHEST WORKOUT");
        obj2.setExerciseStart("START CHEST");
        holder.add(obj2);
        ModelMen obj3 = new ModelMen();
        obj3.setImage(R.drawable.backboybg);
        obj3.setExerciseName("BACK WORKOUT");
        obj3.setExerciseStart("START BACK");
        holder.add(obj3);
        ModelMen obj4 = new ModelMen();
        obj4.setImage(R.drawable.biceps);
        obj4.setExerciseName("BICEPS WORKOUT");
        obj4.setExerciseStart("START BICEPS");
        holder.add(obj4);
        ModelMen obj5 = new ModelMen();
        obj5.setImage(R.drawable.tricepsbg);
        obj5.setExerciseName("TRICEPS WORKOUT");
        obj5.setExerciseStart("START TRICEPS");
        holder.add(obj5);
        ModelMen obj6 = new ModelMen();
        obj6.setImage(R.drawable.sholderboybg);
        obj6.setExerciseName("SHOULDER WORKOUT");
        obj6.setExerciseStart("START SHOULDER");
        holder.add(obj6);
        ModelMen obj7 = new ModelMen();
        obj7.setImage(R.drawable.boyslegbg);
        obj7.setExerciseName("LEGS WORKOUT");
        obj7.setExerciseStart("START LEGS");
        holder.add(obj7);
        ModelMen obj8 = new ModelMen();
        obj8.setImage(R.drawable.cardioboybg);
        obj8.setExerciseName("CARDIO WORKOUT");
        obj8.setExerciseStart("START CARDIO");
        holder.add(obj8);
        ModelMen obj9 = new ModelMen();
        obj9.setImage(R.drawable.absboybg);
        obj9.setExerciseName("ABS WORKOUT");
        obj9.setExerciseStart("START ABS");
        holder.add(obj9);
        return holder;
    }
}
