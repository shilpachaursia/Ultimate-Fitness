package com.example.ultimatefitness.male.bottomNavigation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ultimatefitness.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    private  EditText logEmail,logPass;
    private  Button login,register;
    private CheckBox rememberMe;
    private TextView forgetPassword;
    private String email,pass;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logEmail=findViewById(R.id.loginEmail);
        logPass=findViewById(R.id.loginPassword);
        login=findViewById(R.id.login);
        register=findViewById(R.id.notregister);
        rememberMe=findViewById(R.id.checkBox);
        forgetPassword=findViewById(R.id.forgetPassword);
        auth=FirebaseAuth.getInstance();
        SharedPreferences preferences=getSharedPreferences("remember",MODE_PRIVATE);
       String checkbox= preferences.getString("remember","");
       if (checkbox.equals("true")){
            Intent intent=new Intent(LoginActivity.this,ProfileActivity.class);
            startActivity(intent);
       }else if(checkbox.equals("false")){
           Toast.makeText(this, "Please Login", Toast.LENGTH_SHORT).show();

       }
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                finish();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateUser();
            }
        });
        rememberMe();
        forgetPassword();
    }



    private void validateUser() {
        email=logEmail.getText().toString();
        pass=logPass.getText().toString();
        if (email.isEmpty()|| pass.isEmpty()){
            Toast.makeText(this, "Please fill all the Fields", Toast.LENGTH_SHORT).show();
        }else {
            loginUser();
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (auth.getCurrentUser() != null){
            startActivity(new Intent(this,ProfileActivity.class));
            finish();
        }
    }

    private void loginUser() {
        auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this,ProfileActivity.class));
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, "Error: "+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void rememberMe() {
        rememberMe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()){
                    SharedPreferences preferences=getSharedPreferences("checked",MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("remember","true");
                    editor.apply();
                    Toast.makeText(LoginActivity.this, "Checked", Toast.LENGTH_SHORT).show();
                }else if(!buttonView.isChecked()){
                    SharedPreferences preferences=getSharedPreferences("checked",MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("remember","false");
                    editor.apply();
                    Toast.makeText(LoginActivity.this, "UnChecked", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void forgetPassword() {
        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,ForgetPasswordActivity.class));
                finish();
            }
        });
    }
}