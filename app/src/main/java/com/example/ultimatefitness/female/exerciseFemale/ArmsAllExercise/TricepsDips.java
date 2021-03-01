package com.example.ultimatefitness.female.exerciseFemale.ArmsAllExercise;

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

public class TricepsDips extends AppCompatActivity {
    ImageView imageViewforword,imageViewback;
    GifImageView gif;
    TextView ready,end;
    TextToSpeech textToSpeech;
    CircularView circularViewWithTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triceps_dips);
        imageViewforword = findViewById(R.id.TDForewordBtn);
        imageViewback=findViewById(R.id.TDBackbtn);
        gif=findViewById(R.id.TDGif);
        ready=findViewById(R.id.TDStartPoint);
        end=findViewById(R.id.TDEndPoint);
        imageViewback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TricepsDips.this,Punches.class));
            }
        });
        imageViewforword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TricepsDips.this,PushUps.class));
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
        circularViewWithTimer=findViewById(R.id.circular_view);
        CircularView.OptionsBuilder builderWithTimer=
                new CircularView.OptionsBuilder()
                        .shouldDisplayText(true)
                        .setCounterInSeconds(30)
                        .setCircularViewCallback(new CircularViewCallback() {
                            @Override
                            public void onTimerFinish() {
                                String e=end.getText().toString();
                                int speech=textToSpeech.speak(e,TextToSpeech.QUEUE_FLUSH,null);
                                startActivity(new Intent(TricepsDips.this,PushUps.class));
                            }

                            @Override
                            public void onTimerCancelled() {
                                Toast.makeText(TricepsDips.this, "Timer Cancelled", Toast.LENGTH_SHORT).show();
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