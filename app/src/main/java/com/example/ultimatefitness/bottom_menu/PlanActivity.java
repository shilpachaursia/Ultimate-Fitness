package com.example.ultimatefitness.bottom_menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ultimatefitness.R;
import com.example.ultimatefitness.female.FemaleActivity;
import com.example.ultimatefitness.male.bottomNavigation.PlanMaleActivity;
import com.example.ultimatefitness.male.bottomNavigation.RegisterActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static com.example.ultimatefitness.R.id.button;
import static com.example.ultimatefitness.R.id.login;
import static com.example.ultimatefitness.R.id.plan;
import static com.example.ultimatefitness.R.id.tips;
import static com.example.ultimatefitness.R.id.utility;

public class PlanActivity extends AppCompatActivity {

    ImageView img1, img2, img3, img4, img5, img6, back;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        img1 = findViewById(R.id.fullbodylock);
        img2 = findViewById(R.id.abslock);
        img3 = findViewById(R.id.buttlock);
        img4 = findViewById(R.id.armlock);
        img5 = findViewById(R.id.thighlock);
        img6 = findViewById(R.id.abslock1);
        back = findViewById(R.id.female_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlanActivity.this, FemaleActivity.class);
                startActivity(intent);
                finish();
            }
        });

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PlanActivity.this, "cannot open without pay", Toast.LENGTH_SHORT).show();
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PlanActivity.this, "cannot open without pay", Toast.LENGTH_SHORT).show();
            }
        });

        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PlanActivity.this, "cannot open without pay", Toast.LENGTH_SHORT).show();
            }
        });

        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PlanActivity.this, "cannot open without pay", Toast.LENGTH_SHORT).show();
            }
        });

        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PlanActivity.this, "cannot open without pay", Toast.LENGTH_SHORT).show();
            }
        });

        img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PlanActivity.this, "cannot open without pay", Toast.LENGTH_SHORT).show();
            }
        });


        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.exercise:
                        Intent intent = new Intent(PlanActivity.this, FemaleActivity.class);
                        startActivity(intent);
                        break;
                    case plan:

                    case tips:
                        Intent intent1 = new Intent(PlanActivity.this, TipsActivity.class);
                        startActivity(intent1);
                        break;
                    case utility:
                        Intent intent2 = new Intent(PlanActivity.this, UtilityActivity.class);
                        startActivity(intent2);
                        break;
                    case login:
                        Intent intent3 = new Intent(PlanActivity.this, RegisterActivity.class);
                        startActivity(intent3);
                        break;
                }
                return false;
            }
        });


    }


}
