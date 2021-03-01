package com.example.ultimatefitness.female.exerciseFemale.exerciseStartActivity.absAllExercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ultimatefitness.R;

import java.util.Locale;

import pl.droidsonroids.gif.GifImageView;
import ticker.views.com.ticker.widgets.circular.timer.callbacks.CircularViewCallback;
import ticker.views.com.ticker.widgets.circular.timer.view.CircularView;

public class ButtBridge extends AppCompatActivity {
    ImageView imageViewback,imageViewforword;
    GifImageView gif;
    TextView ready,end;
    CircularView circularViewWithTimer;
    TextToSpeech textToSpeech;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butt_bridge);
        gif=findViewById(R.id.buttbridgeGif);
        ready=findViewById(R.id.readytogo);
        end=findViewById(R.id.BBEbdPoint);
        imageViewback=findViewById(R.id.highsteppingback);
        imageViewforword= findViewById(R.id.highsteppingforword);

        imageViewback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ButtBridge.this,LegsArise.class));
            }
        });
        imageViewforword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ButtBridge.this,Plank.class));
            }
        });
        textToSpeech=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status==textToSpeech.SUCCESS){
                    int lang=textToSpeech.setLanguage(Locale.ENGLISH);
                }
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
                                String e=end.getText().toString();
                                int speech=textToSpeech.speak(e,TextToSpeech.QUEUE_FLUSH,null);
                                startActivity(new Intent(ButtBridge.this,Plank.class));
                            }

                            @Override
                            public void onTimerCancelled() {

                                // Will be called if stopTimer is called
                                Toast.makeText(ButtBridge.this, "CircularCallback: Timer Cancelled ", Toast.LENGTH_SHORT).show();
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