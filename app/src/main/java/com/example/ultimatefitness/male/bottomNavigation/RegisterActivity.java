package com.example.ultimatefitness.male.bottomNavigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ultimatefitness.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {
        private EditText regName,regPass,regEmail;
        private Button login,register;
        private  String name, email,password;
        private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        regName=findViewById(R.id.registerName);
        regEmail=findViewById(R.id.regEmail);
        regPass=findViewById(R.id.regPassword);
        login=findViewById(R.id.Loginbtn);
        register=findViewById(R.id.RegisterBtn);
        auth=FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                finish();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateUser();
            }
        });
    }
    private void validateUser() {
        name=regName.getText().toString();
        email=regEmail.getText().toString();
        password=regPass.getText().toString();
        if (name.isEmpty()||email.isEmpty()||password.isEmpty()){
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
        }else {
            registeruser();
        }
    }

    private void registeruser() {
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                        updateUser();
                }else {
                    Toast.makeText(RegisterActivity.this, "Error! User is not created"+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void updateUser() {
        UserProfileChangeRequest changeRequest = new UserProfileChangeRequest.Builder().setDisplayName(name)
                .build();
        Objects.requireNonNull(auth.getCurrentUser()).updateProfile(changeRequest);
        openLogin();
    }

    private void openLogin() {
        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (auth.getCurrentUser() != null){
            startActivity(new Intent(this,LoginActivity.class));
            finish();
        }
    }
}