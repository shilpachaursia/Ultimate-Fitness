package com.example.ultimatefitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ultimatefitness.female.FemaleActivity;
import com.example.ultimatefitness.male.MaleActivity;

public class MainActivity extends AppCompatActivity {

    ImageView Male,Female;
    TextView t1,t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Male=(ImageView)findViewById(R.id.boy);
        Female=(ImageView)findViewById(R.id.girl);
        t1=(TextView)findViewById(R.id.boytext);
        t2=(TextView)findViewById(R.id.girltext);

        Male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MaleActivity.class);
                startActivity(intent);
            }
        });

        Female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FemaleActivity.class);
                startActivity(intent);
            }
        });
    }
}