package com.example.ultimatefitness.female.exerciseFemale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.ultimatefitness.R;
import com.example.ultimatefitness.female.FemaleActivity;
import com.example.ultimatefitness.female.exerciseFemale.exerciseStartActivity.absAllExercise.CrossJumpingJack;

public class AbsWorkout extends AppCompatActivity {
    ImageView abs_back_btn;
    Button startExerciseBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abs_workout);

        abs_back_btn= findViewById(R.id.abs_bact_btn);
        startExerciseBtn= findViewById(R.id.startExercise);
        abs_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AbsWorkout.this, FemaleActivity.class);
                startActivity(intent);
            }
        });
        startExerciseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(AbsWorkout.this, CrossJumpingJack.class);
                startActivity(intent);
            }
        });
    }
}