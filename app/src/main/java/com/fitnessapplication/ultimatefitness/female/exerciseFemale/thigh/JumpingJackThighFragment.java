package com.fitnessapplication.ultimatefitness.female.exerciseFemale.thigh;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.fitnessapplication.ultimatefitness.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import java.util.Locale;

import pl.droidsonroids.gif.GifImageView;
import ticker.views.com.ticker.widgets.circular.timer.callbacks.CircularViewCallback;
import ticker.views.com.ticker.widgets.circular.timer.view.CircularView;

import static android.speech.tts.TextToSpeech.SUCCESS;

public class JumpingJackThighFragment extends Fragment {
    ImageView imageViewforwordJJT,backBtnJJT,startJJT,resumeJJT,pauseJJT;
    GifImageView gifJJT;
    TextView readyJJT,endJJT;
    TextToSpeech textToSpeechJJT;
    CircularView circularViewWithTimerJJT;
    private AdView mAdView;
    public JumpingJackThighFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_jumping_jack_thigh, container, false);
        imageViewforwordJJT=view.findViewById(R.id.JJTForwordBtn);
        gifJJT=view.findViewById(R.id.JJTGif);
        backBtnJJT=view.findViewById(R.id.backjjt);
        readyJJT=view.findViewById(R.id.JJTStart);
        endJJT=view.findViewById(R.id.JJTEndpoint);
        startJJT=view.findViewById(R.id.JJTstartBtn);
        resumeJJT=view.findViewById(R.id.JJTresumeBtn);
        pauseJJT=view.findViewById(R.id.JJTpauseBtn);

        circularViewWithTimerJJT=view.findViewById(R.id.circular_viewJJT);
        //ad
        mAdView=new AdView(getActivity());
        mAdView.setAdUnitId("ca-app-pub-2342822035961981~6396888741");
        mAdView.setAdSize(AdSize.FULL_BANNER);
        AdView adView=view.findViewById(R.id.adView);
        adView.addView(mAdView);
        AdRequest adRequest= new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        imageViewforwordJJT.setOnClickListener(v -> {
            FragmentManager squats = getFragmentManager();
            FragmentTransaction ft = squats.beginTransaction();
            ft.replace(R.id.frameContainerThighExercise, new SquatsThighFragment());
            ft.commit();
            circularViewWithTimerJJT.stopTimer();
        });
        backBtnJJT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager highStepping=getFragmentManager();
                FragmentTransaction t=highStepping.beginTransaction()
                        .replace(R.id.frameContainerThighExercise,new HighSteppingThighFragment());
                t.commit();
            }
        });
        startJJT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s= readyJJT.getText().toString();
                int speech=textToSpeechJJT.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                circularViewWithTimerJJT.startTimer();
            }
        });
        resumeJJT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerJJT.resumeTimer();
            }
        });
        pauseJJT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerJJT.pauseTimer();
            }
        });
        textToSpeechJJT=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (SUCCESS == status){
                    int lang=textToSpeechJJT.setLanguage(Locale.ENGLISH);
                }
            }
        });

        CircularView.OptionsBuilder builderWithTimer=
                new CircularView.OptionsBuilder()
                        .shouldDisplayText(true)
                        .setCounterInSeconds(30)
                        .setCircularViewCallback(new CircularViewCallback() {
                            @Override
                            public void onTimerFinish() {
                                String e=endJJT.getText().toString();
                                int speech=textToSpeechJJT.speak(e,TextToSpeech.QUEUE_FLUSH,null);
                                FragmentManager squats = getFragmentManager();
                                FragmentTransaction ft = squats.beginTransaction()
                                .replace(R.id.frameContainerThighExercise, new SquatsThighFragment());
                                ft.commit();
                            }

                            @Override
                            public void onTimerCancelled() {
                            }
                        });
        circularViewWithTimerJJT.setOptions(builderWithTimer);
        return view;
    }}



