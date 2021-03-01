package com.example.ultimatefitness.calculators;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ultimatefitness.MainActivity;
import com.example.ultimatefitness.R;

public class BmiCalculator extends AppCompatActivity {

    EditText weight, height, age;
    Button result;
    TextView result_show;
    ImageView backbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_calculator);

        weight = findViewById(R.id.weight_btn);
        height = findViewById(R.id.height_btn);
        age = findViewById(R.id.age_btn);
        result = findViewById(R.id.result_btn);
        result_show=findViewById(R.id.result_show);
        backbtn= findViewById(R.id.BMICalBackBtn);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BmiCalculator.this, MainActivity.class);
                startActivity(intent);
            }
        });

        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1=weight.getText().toString();
                String s2=height.getText().toString();

                if (s1.equals("")){
                    weight.setError("Please Enter your weight");
                    weight.requestFocus();
                    return;
                }
                if (s2.equals("")){
                    height.setError("Please Enter your height");
                    return;
                }
                float weight =Float.parseFloat(s1);
                float height=Float.parseFloat(s2);

                float bmiValue =BmiCalculator(weight,height);
                result_show.setText("Your BMI Result is : "+ bmiValue);
            }
        });


    }

    public float BmiCalculator(float weight, float height) {
        return weight / (height*height);
    }

    public String interpreteBMI(float bmi) {
        if (bmi < 16) {
           return "Serevely Under Weight";
        } else if (bmi < 18.5) {
            return  "Under Weight";
        } else if (bmi >= 18.5 && bmi <= 24.9) {
            return  "Normal Weight";
        } else if (bmi > 24.5) {
            return  "Overweight";
        }

        return null;
    }
}



