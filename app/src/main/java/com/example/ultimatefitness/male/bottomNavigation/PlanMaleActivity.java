package com.example.ultimatefitness.male.bottomNavigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.ultimatefitness.R;
import com.example.ultimatefitness.bottom_menu.TipsActivity;
import com.example.ultimatefitness.female.FemaleActivity;
import com.example.ultimatefitness.male.MaleActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PlanMaleActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_male);

        imageView=findViewById(R.id.planMaleBackBtn);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(PlanMaleActivity.this,MaleActivity.class);
                startActivity(intent);
            }
        });

        bottomNavigationView=findViewById(R.id.bottom_navigation_view);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.exercise:
                        Intent intent= new Intent(PlanMaleActivity.this, MaleActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.plan:
                        Intent intent1= new Intent(PlanMaleActivity.this, PlanMaleActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.tips:
                        Intent intent2= new Intent(PlanMaleActivity.this, TipsMaleActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.utility:
                        Intent intent3= new Intent(PlanMaleActivity.this, UtiltiyMaleActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.login:
                        Intent intent4= new Intent(PlanMaleActivity.this, RegisterActivity.class);
                        startActivity(intent4);
                        break;
                }
                return false;
            }
        });
    }
}