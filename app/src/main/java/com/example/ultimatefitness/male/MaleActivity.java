package com.example.ultimatefitness.male;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.ultimatefitness.Model;
import com.example.ultimatefitness.R;
import com.example.ultimatefitness.male.bottomNavigation.RegisterActivity;
import com.example.ultimatefitness.male.bottomNavigation.PlanMaleActivity;
import com.example.ultimatefitness.male.bottomNavigation.TipsMaleActivity;
import com.example.ultimatefitness.male.bottomNavigation.UtiltiyMaleActivity;
import com.example.ultimatefitness.myAdapterClass;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import static com.example.ultimatefitness.R.id.login;
import static com.example.ultimatefitness.R.id.plan;
import static com.example.ultimatefitness.R.id.thbrek;
import static com.example.ultimatefitness.R.id.tips;
import static com.example.ultimatefitness.R.id.utility;

public class MaleActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    static final float END_SCALE=0.7f;
    ImageView menuside;
    BottomNavigationView bottomNavigationView;

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    RelativeLayout contentView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_male);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.exercise:
                        Intent intent = new Intent(MaleActivity.this, MaleActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case plan:
                        Intent intent1 = new Intent(MaleActivity.this, PlanMaleActivity.class);
                        startActivity(intent1);
                        finish();
                        break;
                    case tips:
                        Intent intent2 = new Intent(MaleActivity.this, TipsMaleActivity.class);
                        startActivity(intent2);
                        finish();
                        break;
                    case utility:
                        Intent intent3 = new Intent(MaleActivity.this, UtiltiyMaleActivity.class);
                        startActivity(intent3);
                        finish();
                    case login:
                        Intent intent4 = new Intent(MaleActivity.this, RegisterActivity.class);
                        startActivity(intent4);
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

        //navigation view
        navigationDrawar();

        //recycler view
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.menRecycleView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        AdapterMen adapter = new AdapterMen(dataQueue(), getApplicationContext());
        mRecyclerView.setAdapter(adapter);


    }


    //navigation
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
        obj1.setImage(R.drawable.boy);
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
