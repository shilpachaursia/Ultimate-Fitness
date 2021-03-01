package com.example.ultimatefitness.bottom_menu;

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
import com.example.ultimatefitness.female.FemaleActivity;
import com.example.ultimatefitness.male.bottomNavigation.RegisterActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static com.example.ultimatefitness.R.id.login;
import static com.example.ultimatefitness.R.id.plan;
import static com.example.ultimatefitness.R.id.start;
import static com.example.ultimatefitness.R.id.tips;
import static com.example.ultimatefitness.R.id.utility;

public class UtilityActivity extends AppCompatActivity {

    ImageView bmi,protein,fat,femaleback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        BottomNavigationView bottomNavigationView;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utility);
        femaleback=findViewById(R.id.utilityFemaleBack);
        femaleback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(UtilityActivity.this,FemaleActivity.class);
                startActivity(intent);
            }
        });
        bottomNavigationView =(BottomNavigationView)findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.exercise:
                        Intent intent = new Intent(UtilityActivity.this, FemaleActivity.class);
                        startActivity(intent);
                        break;
                    case plan:
                        Intent intent4= new Intent(UtilityActivity.this, PlanActivity.class);
                        startActivity(intent4);
                        break;
                    case tips:
                        Intent intent1 = new Intent(UtilityActivity.this, TipsActivity.class);
                        startActivity(intent1);
                        break;
                    case utility:

                    case login:
                        Intent intent3 = new Intent(UtilityActivity.this, RegisterActivity.class);
                        startActivity(intent3);
                        break;
                }
                return false;
            }
        });

        bmi=(ImageView)findViewById(R.id.bmi_cal);
        protein=(ImageView)findViewById(R.id.protein_cal);
        fat=(ImageView)findViewById(R.id.fat_cal);
        bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UtilityActivity.this, BmiCalculator.class);
                startActivity(intent);
            }
        });

        protein.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UtilityActivity.this, ProteinCalculator.class);
                startActivity(intent);
            }
        });

        fat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UtilityActivity.this, FatCalculator.class);
                startActivity(intent);
            }
        });

    }
}