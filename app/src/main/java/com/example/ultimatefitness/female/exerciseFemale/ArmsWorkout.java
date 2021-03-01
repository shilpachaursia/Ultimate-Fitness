package com.example.ultimatefitness.female.exerciseFemale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.ultimatefitness.R;
import com.example.ultimatefitness.female.FemaleActivity;
import com.example.ultimatefitness.female.exerciseFemale.ArmsAllExercise.DyanamicChest;

public class ArmsWorkout extends AppCompatActivity {
    ImageView arms_back_btn;
    Button startExerciseBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arms_workout);
        arms_back_btn = findViewById(R.id.arms_bact_btn);
        startExerciseBtn=findViewById(R.id.ArmsStartExercise);
        arms_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ArmsWorkout.this, FemaleActivity.class);
                startActivity(intent);
            }
        });
        startExerciseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ArmsWorkout.this, DyanamicChest.class));
            }
        });
    }
}