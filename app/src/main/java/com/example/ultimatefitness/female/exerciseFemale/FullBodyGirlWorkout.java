package com.example.ultimatefitness.female.exerciseFemale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.ultimatefitness.R;
import com.example.ultimatefitness.female.FemaleActivity;

public class
FullBodyGirlWorkout extends AppCompatActivity {
    ImageView fullbody_back_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_body_girl_workout);
        fullbody_back_btn= findViewById(R.id.fullbody_bact_btn);

        fullbody_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(FullBodyGirlWorkout.this, FemaleActivity.class);
                startActivity(intent);
            }
        });

    }

}