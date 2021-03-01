package com.example.ultimatefitness.male.bottomNavigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.session.MediaSessionManager;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ultimatefitness.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private EditText mname;
    private EditText memail;
    private EditText mpass;
    private RadioButton male,female;
    ProgressBar progressBar;
    public String gender="";

    private FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    private DatabaseReference dbref;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("user");


        mname = findViewById(R.id.registerName);
        memail = (EditText) findViewById(R.id.loginEmail);
        mpass = (EditText) findViewById(R.id.LoginPassword);
        EditText mconfirmPassword = (EditText) findViewById(R.id.RegisterConformPassword);
        TextView mtextView = (TextView) findViewById(R.id.alreadyRegister);
        male= findViewById(R.id.male);
        female= findViewById(R.id.female);
        progressBar = findViewById(R.id.processbar);
        Button regButton =(Button) findViewById(R.id.Loginbtn);

        mtextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));

            }
        });

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {createUser();}
        });

    }

    private void createUser() {

        String name = mname.getText().toString();
        String email = memail.getText().toString();
        String password = mpass.getText().toString();
        String confirmPassword = mpass.getText().toString();

        if (male.isChecked()){
            gender="Male";
        }
        if (female.isChecked()){
            gender="Female";
        }

        if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            if (!password.isEmpty() && password.equals(confirmPassword)){
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(RegisterActivity.this, "Registered", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterActivity.this, "Registration error", Toast.LENGTH_SHORT).show();
                    }
                });
            }else {
                mpass.setError("Empty Fields are not allowed");
            }
        }else if (email.isEmpty()){
            memail.setError("Empty Fields are not allowed");
        }
        else{
            memail.setError("Please enter correct email");
        }
    }

}