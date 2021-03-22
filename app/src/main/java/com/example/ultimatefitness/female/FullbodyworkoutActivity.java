package com.example.ultimatefitness.female;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ultimatefitness.R;


public class FullbodyworkoutActivity extends AppCompatActivity {

    ImageView backbtn,searchbtn;
    ImageView img;
    TextView text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullbodyworkout);

        backbtn=(ImageView)findViewById(R.id.bact_btn);
        searchbtn=(ImageView)findViewById(R.id.search_btn);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FullbodyworkoutActivity.super.onBackPressed();
                Intent intent = new Intent(FullbodyworkoutActivity.this,FemaleActivity.class);
                startActivity(intent);
            }
        });

        //exercise page
        img=(ImageView)findViewById(R.id.ex_img);
        text=(TextView)findViewById(R.id.ex_text);

        img.setImageResource(getIntent().getIntExtra("Image Name", 0));
        text.setText(getIntent().getStringExtra("Exercise Title"));



    }
}