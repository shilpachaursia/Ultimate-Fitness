package com.example.ultimatefitness.male.bottomNavigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText userEmail,userPass,username;
    private TextView textView;

    private CheckBox rememberMe;
    private Button loginBtn;
    private ProgressBar progressBar;

    private String email,password,name;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        progressBar=findViewById(R.id.progessLogin);
        userEmail=findViewById(R.id.regEmail);
        userPass= findViewById(R.id.regPassword);
        username=findViewById(R.id.regName);
        textView= findViewById(R.id.notRegister);
        loginBtn= findViewById(R.id.registerbtn);

        rememberMe= findViewById(R.id.checkBox);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));

            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // User user=new User("abc@gmail.com","Shilpa");

                loginUser();
            }
        });



    }

    private void loginUser() {
        email=userEmail.getText().toString();
        password=userPass.getText().toString();
        if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            if (!password.isEmpty()){
                progressBar.setVisibility(View.VISIBLE);
                mAuth.signInWithEmailAndPassword(email,password)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent= new Intent(LoginActivity.this,ProfileActivity.class);
                                name=username.getText().toString();
                                intent.putExtra("Value",name);
                                startActivity(intent);
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }else {
                userPass.setError("Empty Fields are not allowed");
            }
        }else if (email.isEmpty()){
            userEmail.setError("Empty Fields are not allowed");
        }
        else{
            userEmail.setError("Please enter correct email");
        }
    }




}