package com.example.ultimatefitness.male.bottomNavigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.ultimatefitness.R;
import com.example.ultimatefitness.calculators.BmiCalculator;
import com.example.ultimatefitness.calculators.FatCalculator;
import com.example.ultimatefitness.calculators.ProteinCalculator;
import com.example.ultimatefitness.male.MaleActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UtiltiyMaleActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ImageView bmi,protein,fat,backbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utiltiy_male);
        bottomNavigationView=findViewById(R.id.bottom_navigation_view);

        bmi=(ImageView)findViewById(R.id.bmi_cal);
        protein=(ImageView)findViewById(R.id.protein_cal);
        fat=(ImageView)findViewById(R.id.fat_cal);

        backbtn= findViewById(R.id.utility_back);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(UtiltiyMaleActivity.this,MaleActivity.class);
                startActivity(intent);
            }
        });

        bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UtiltiyMaleActivity.this, BmiCalculator.class);
                startActivity(intent);
            }
        });

        protein.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UtiltiyMaleActivity.this, ProteinCalculator.class);
                startActivity(intent);
            }
        });

        fat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UtiltiyMaleActivity.this, FatCalculator.class);
                startActivity(intent);
            }
        });


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.exercise:
                        Intent intent= new Intent(UtiltiyMaleActivity.this, MaleActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.plan:
                        Intent intent1= new Intent(UtiltiyMaleActivity.this, PlanMaleActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.tips:
                        Intent intent2= new Intent(UtiltiyMaleActivity.this, TipsMaleActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.utility:
                        Intent intent3= new Intent(UtiltiyMaleActivity.this, UtiltiyMaleActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.login:
                        Intent intent4= new Intent(UtiltiyMaleActivity.this, RegisterActivity.class);
                        startActivity(intent4);
                        break;


                }
                return false;
            }
        });
    }
}