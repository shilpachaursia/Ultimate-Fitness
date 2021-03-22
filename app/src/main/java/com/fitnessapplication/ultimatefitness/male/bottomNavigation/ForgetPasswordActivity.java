package com.fitnessapplication.ultimatefitness.male.bottomNavigation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fitnessapplication.ultimatefitness.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;
public class ForgetPasswordActivity extends AppCompatActivity {
    private EditText newPassword;
    private FirebaseAuth auth;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        newPassword=findViewById(R.id.newPassword);
        Button setNew = findViewById(R.id.newPasswordBtn);
        auth=FirebaseAuth.getInstance();
        setNew.setOnClickListener(v -> validateData());
    }
    private void validateData() {
       email=newPassword.getText().toString();
       if (email.isEmpty()){
           newPassword.setError("Required");
       }else{
           forgetPassword();
       }
    }
    private void forgetPassword() {
        auth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                Toast.makeText(ForgetPasswordActivity.this, "Check Your Email", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ForgetPasswordActivity.this, LoginActivity.class));
                finish();
            }else{
                Toast.makeText(ForgetPasswordActivity.this, "Error: "+ Objects.requireNonNull(task.getException()).  getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}