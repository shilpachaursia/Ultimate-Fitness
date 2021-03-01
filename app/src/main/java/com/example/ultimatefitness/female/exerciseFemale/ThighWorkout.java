package com.example.ultimatefitness.female.exerciseFemale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import com.example.ultimatefitness.R;
import com.example.ultimatefitness.female.FemaleActivity;

public class ThighWorkout extends AppCompatActivity {
    ImageView thigh_back_btn;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thigh_workout);

        thigh_back_btn=findViewById(R.id.thigh_bact_btn);
       // searchView= (SearchView)findViewById(R.id.search_view);
        thigh_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ThighWorkout.this, FemaleActivity.class);
                startActivity(intent);
            }
        });
    }
}