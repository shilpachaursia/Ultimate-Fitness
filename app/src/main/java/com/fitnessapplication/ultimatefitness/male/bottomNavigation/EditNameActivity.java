package com.fitnessapplication.ultimatefitness.male.bottomNavigation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.fitnessapplication.ultimatefitness.R;


public class EditNameActivity extends AppCompatActivity {
    private EditText name,info;
    private Button sendBtn;
    SharedPreferences preferences;
    private static final String SHARED_PREF_NAME="mypref";
    private static final String KEY_NAME="name";
    private static final String LEY_INFO="info";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_name);
        name=findViewById(R.id.nameEdit);
        info=findViewById(R.id.editSubInfo);
        sendBtn=findViewById(R.id.EditBtn);
        preferences=getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor=preferences.edit();
                editor.putString(KEY_NAME,name.getText().toString());
                editor.putString(LEY_INFO,info.getText().toString());
                editor.apply();
                Intent intent=new Intent(EditNameActivity.this,ProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}