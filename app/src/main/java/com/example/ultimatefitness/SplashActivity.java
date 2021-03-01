package com.example.ultimatefitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    ImageView img;
    TextView t1,t2;
    Animation logo,text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        img= (ImageView)findViewById(R.id.imageview);
        t1= (TextView) findViewById(R.id.textview);
        t2= (TextView) findViewById(R.id.textview2);

        logo= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.logo_image);
        text= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.main_text);
        img.setAnimation(logo);
        t1.setAnimation(text);

        int splashScreen = 5000;
        Handler handler= new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },splashScreen);
    }
}