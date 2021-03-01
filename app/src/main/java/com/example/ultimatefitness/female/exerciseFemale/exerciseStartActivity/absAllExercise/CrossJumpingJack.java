package com.example.ultimatefitness.female.exerciseFemale.exerciseStartActivity.absAllExercise;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ultimatefitness.R;

import java.util.Locale;

import ticker.views.com.ticker.widgets.circular.timer.callbacks.CircularViewCallback;
import ticker.views.com.ticker.widgets.circular.timer.view.CircularView;

public class CrossJumpingJack extends AppCompatActivity {
        ImageView gif,imageViewforword;
        TextView ready,end;
        CircularView circularViewWithTimer;
        TextToSpeech textToSpeech;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_fullbody);
        gif=findViewById(R.id.crossjumpingjackgif);
        end= findViewById(R.id.endpoint);
        ready=findViewById(R.id.readytogo);
        textToSpeech=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status==textToSpeech.SUCCESS){
                    int lang=textToSpeech.setLanguage(Locale.ENGLISH);
                }
            }
        });
        imageViewforword=findViewById(R.id.crossjummpingjackForeword);
        imageViewforword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forword= new Intent(CrossJumpingJack.this,HighStepping.class);
                startActivity(forword);
            }
        });
        circularViewWithTimer = findViewById(R.id.circular_view);
        CircularView.OptionsBuilder builderWithTimer =
                new CircularView.OptionsBuilder()
                        .shouldDisplayText(true)
                        .setCounterInSeconds(30)
                        .setCircularViewCallback(new CircularViewCallback() {
                            @Override
                            public void onTimerFinish() {
                                String e= end.getText().toString();
                                int speech=textToSpeech.speak(e,TextToSpeech.QUEUE_FLUSH,null);
                                Intent forword= new Intent(CrossJumpingJack.this,HighStepping.class);
                                startActivity(forword);
                            }

                            @Override
                            public void onTimerCancelled() {

                                // Will be called if stopTimer is called
                                Toast.makeText(CrossJumpingJack.this, "CircularCallback: Timer Cancelled ", Toast.LENGTH_SHORT).show();
                            }
                        });

        circularViewWithTimer.setOptions(builderWithTimer);
    }

    public void resume(View view) {
        circularViewWithTimer.resumeTimer();
    }

    public void start(View view) {
        String s= ready.getText().toString();
        int speech=textToSpeech.speak(s,TextToSpeech.QUEUE_FLUSH,null);
        circularViewWithTimer.startTimer();
    }

    public void pause(View view) {
        circularViewWithTimer.pauseTimer();
    }
}