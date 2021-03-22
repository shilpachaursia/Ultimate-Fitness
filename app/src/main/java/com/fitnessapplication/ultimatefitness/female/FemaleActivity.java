package com.fitnessapplication.ultimatefitness.female;

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

import com.fitnessapplication.ultimatefitness.Model;
import com.fitnessapplication.ultimatefitness.R;
import com.fitnessapplication.ultimatefitness.bottom_menu.PlanActivity;
import com.fitnessapplication.ultimatefitness.bottom_menu.TipsActivity;
import com.fitnessapplication.ultimatefitness.bottom_menu.UtilityActivity;
import com.fitnessapplication.ultimatefitness.male.MaleActivity;
import com.fitnessapplication.ultimatefitness.male.bottomNavigation.LoginActivity;
import com.fitnessapplication.ultimatefitness.male.bottomNavigation.ProfileActivity;
import com.fitnessapplication.ultimatefitness.male.bottomNavigation.RegisterActivity;
import com.fitnessapplication.ultimatefitness.male.drawerMenuActivity.FeedbackActivity;
import com.fitnessapplication.ultimatefitness.male.drawerMenuActivity.PrivacyActivity;
import com.fitnessapplication.ultimatefitness.male.drawerMenuActivity.SettingActivity;
import com.fitnessapplication.ultimatefitness.myAdapterClass;
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
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;


public class FemaleActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    static final float END_SCALE=0.7f;
    ImageView menuside,sharebtn;
    BottomNavigationView bottomNavigationView;
    SwitchCompat gender;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    RelativeLayout contentView;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_female);
        sharebtn=findViewById(R.id.shareBtn);
        gender=findViewById(R.id.femaleswitchGender);
        bottomNavigationView=findViewById(R.id.bottom_navigation_view);
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.navigation_view_leftSide);
        contentView=findViewById(R.id.content);
        menuside=findViewById(R.id.menuside);
        mAdView=findViewById(R.id.adView);
        firebaseAuth=FirebaseAuth.getInstance();

        //Recycler View
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapterClass adapter = new myAdapterClass(dataQueue(), getApplicationContext());
        mRecyclerView.setAdapter(adapter);

        sharebtn();
        gender();
        ad();
        bottomNavigationView();
        navigationDrawar();
        navigationView();
    }

    private void navigationView() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int i=item.getItemId();
                if (i==R.id.nav_home){
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else if (i==R.id.nav_gender){
                    startActivity(new Intent(FemaleActivity.this,MaleActivity.class));
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else if (i==R.id.nav_login){
                    startActivity(new Intent(FemaleActivity.this, LoginActivity.class));
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else if (i==R.id.nav_profile){
                    startActivity(new Intent(FemaleActivity.this, ProfileActivity.class));
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else if (i==R.id.nav_logout){
                    if (firebaseAuth!=null){
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(FemaleActivity.this,LoginActivity.class));
                        finish();
                        Toast.makeText(FemaleActivity.this, "Logout SuccessFull", Toast.LENGTH_SHORT).show();
                    }
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
                    startActivity(new Intent(FemaleActivity.this, FeedbackActivity.class));
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else if (i==R.id.nav_feedback){
                    startActivity(new Intent(FemaleActivity.this, FeedbackActivity.class));
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else if (i==R.id.nav_setting){
                    startActivity(new Intent(FemaleActivity.this, SettingActivity.class));
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else if (i==R.id.nav_privacy){
                    startActivity(new Intent(FemaleActivity.this, PrivacyActivity.class));
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                return true;
            }
        });
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
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
    private void bottomNavigationView() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.exercise:
                        Intent exercise=new Intent(FemaleActivity.this,FemaleActivity.class);
                        exercise.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        break;
                    case R.id.plan:
                        Intent intent1 = new Intent(FemaleActivity.this, PlanActivity.class);
                        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent1);
                        finish();
                        break;
                    case R.id.tips:
                        Intent intent2 = new Intent(FemaleActivity.this, TipsActivity.class);
                        intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent2);
                        finish();
                        break;
                    case R.id.utility:
                        Intent utility=new Intent(FemaleActivity.this,UtilityActivity.class);
                        utility.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(utility);
                        finish();
                        break;
                    case R.id.login:
                        Intent intent4 = new Intent(FemaleActivity.this, RegisterActivity.class);
                        intent4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent4);
                        finish();
                        break;
                }
                return false;
            }
        });
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
                Toast.makeText(FemaleActivity.this, "Ad Loaded", Toast.LENGTH_SHORT).show();
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
    private void gender() {
       gender.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
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
                           startActivity(new Intent(FemaleActivity.this,MaleActivity.class));
                       }else{
                           SharedPreferences.Editor editor=getSharedPreferences("save",MODE_PRIVATE).edit();
                           editor.putBoolean("value",false);
                           editor.apply();
                           gender.setChecked(false);

                       }
                   }
               });
           }
       });
    }
    private void sharebtn() {
       sharebtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent shareIntent= new Intent(android.content.Intent.ACTION_SEND);
               shareIntent.setType("Text/Plain");
               String shareBody="share the Uri of playstore";//uri playstore
               shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
               shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
               startActivity(Intent.createChooser(shareIntent, "Share via"));
           }
       });
    }
    public ArrayList<Model> dataQueue() {
        ArrayList<Model> holder = new ArrayList<>();
        Model obj1 = new Model();
        obj1.setImage(R.drawable.fullbadygirlbg);
        obj1.setTitle("FULLBODY WORKOUT");
        obj1.setStart("START FULLBODY");
        holder.add(obj1);
        Model obj2 = new Model();
        obj2.setImage(R.drawable.absgirlbg);
        obj2.setTitle("ABS WORKOUT");
        obj2.setStart("START ABS");
        holder.add(obj2);
        Model obj3 = new Model();
        obj3.setImage(R.drawable.buttgirlbg);
        obj3.setTitle("BUTT WORKOUT");
        obj3.setStart("START BUTT");
        holder.add(obj3);
        Model obj4 = new Model();
        obj4.setImage(R.drawable.armgirlbg);
        obj4.setTitle("ARM WORKOUT");
        obj4.setStart("START ARMS");
        holder.add(obj4);
        Model obj5 = new Model();
        obj5.setImage(R.drawable.thighgirlbg);
        obj5.setTitle("THIGH WORKOUT");
        obj5.setStart("START THIGH");
        holder.add(obj5);
        return holder;
    }
}