package com.example.ultimatefitness.bottom_menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.ultimatefitness.R;
import com.example.ultimatefitness.female.FemaleActivity;
import com.example.ultimatefitness.male.bottomNavigation.RegisterActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static com.example.ultimatefitness.R.id.login;
import static com.example.ultimatefitness.R.id.plan;
import static com.example.ultimatefitness.R.id.tips;
import static com.example.ultimatefitness.R.id.utility;

public class TipsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        BottomNavigationView bottomNavigationView;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);

        CardView weightLoss,weightGain;

        ImageView backbtn;
        backbtn= findViewById(R.id.back_btn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(TipsActivity.this,FemaleActivity.class);
                startActivity(intent);
            }
        });

        weightGain=(CardView)findViewById(R.id.weightgainLayout);
        weightLoss=(CardView)findViewById(R.id.weightLossLayout);
        weightGain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weightGain.setVisibility(View.GONE);
                weightLoss.setVisibility(View.GONE);
                FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainer,new WeightGainFragment()).commit();

            }
        });
        weightLoss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weightLoss.setVisibility(View.GONE);
                weightGain.setVisibility(View.GONE);
                FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainer,new weightLossFragment()).commit();
            }
        });

        bottomNavigationView =(BottomNavigationView)findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.exercise:
                        Intent intent = new Intent(TipsActivity.this, FemaleActivity.class);
                        startActivity(intent);
                        break;
                    case plan:
                        Intent intent4= new Intent(TipsActivity.this, PlanActivity.class);
                        startActivity(intent4);
                        break;
                    case tips:

                    case utility:
                        Intent intent2 = new Intent(TipsActivity.this, UtilityActivity.class);
                        startActivity(intent2);
                        break;
                    case login:
                        Intent intent3 = new Intent(TipsActivity.this, RegisterActivity.class);
                        startActivity(intent3);
                        break;
                }
                return false;
            }
        });

    }
}