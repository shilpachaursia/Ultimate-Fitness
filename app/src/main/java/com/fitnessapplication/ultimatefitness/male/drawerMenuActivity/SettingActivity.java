package com.fitnessapplication.ultimatefitness.male.drawerMenuActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.fitnessapplication.ultimatefitness.R;


public class SettingActivity extends AppCompatActivity {
TextView privacyPolicy,termandCondition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        privacyPolicy=findViewById(R.id.priavcypolicy);
        termandCondition=findViewById(R.id.termandPolicy);
        privacyPolicy();
        termandCondition();
    }

    private void privacyPolicy() {
        privacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this,PrivacyActivity.class));
            }
        });
    }

    private void termandCondition() {
        termandCondition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this,TermAndConditionActivity.class));
            }
        });
    }
}