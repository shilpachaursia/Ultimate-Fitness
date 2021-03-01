package com.example.ultimatefitness.female.exerciseFemale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.ultimatefitness.R;
import com.example.ultimatefitness.female.FemaleActivity;

public class ButtWorkout extends AppCompatActivity {
    ImageView butt_back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butt_workout);
        butt_back_btn = findViewById(R.id.butt_bact_btn);
        butt_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ButtWorkout.this, FemaleActivity.class);
                startActivity(intent);
            }
        });
    }
}