package com.example.ultimatefitness.female.exerciseFemale.ArmsAllExercise;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.ultimatefitness.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import java.util.Locale;

import pl.droidsonroids.gif.GifImageView;
import ticker.views.com.ticker.widgets.circular.timer.callbacks.CircularViewCallback;
import ticker.views.com.ticker.widgets.circular.timer.view.CircularView;

import static android.speech.tts.TextToSpeech.SUCCESS;

public class ArmsCircleFragment extends Fragment {
    ImageView ACImageViewForeword,ACImageViewBack,ACStart,ACResume,ACPause;
    GifImageView ACGif;
    TextView ACReadyToGo,ACFinish;
    TextToSpeech textToSpeechAC;
    CircularView circularViewWithTimerAC;
    private AdView mAdView;

    public ArmsCircleFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_arms_circle, container, false);
        ACImageViewForeword=view.findViewById(R.id.ACForwordBtn);
        ACImageViewBack=view.findViewById(R.id.AcBackbtn);
        ACPause=view.findViewById(R.id.ACPause);
        ACResume=view.findViewById(R.id.ACresumeBtn);
        ACStart=view.findViewById(R.id.ACstartBtn);
        ACGif=view.findViewById(R.id.ACGif);
        ACReadyToGo=view.findViewById(R.id.ACStartPoint);
        ACFinish=view.findViewById(R.id.ACEndpoint);

        circularViewWithTimerAC=view.findViewById(R.id.ACcircular_view);
        //ad
        mAdView=new AdView(getActivity());
        mAdView.setAdUnitId("ca-app-pub-2342822035961981~6396888741");
        mAdView.setAdSize(AdSize.FULL_BANNER);
        AdView adView=view.findViewById(R.id.adView);
        adView.addView(mAdView);
        AdRequest adRequest= new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        ACImageViewForeword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager kneePushup = getFragmentManager();
                FragmentTransaction ft = kneePushup.beginTransaction();
                ft.replace(R.id.scrollView, new KneePushUpFragment());
                ft.commit();
                circularViewWithTimerAC.stopTimer();
            }

        });
        ACImageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager crossJumpingJack = getFragmentManager();
                FragmentTransaction ft = crossJumpingJack.beginTransaction();
                ft.replace(R.id.scrollView, new CrossJumpingJackFragment());
                ft.commit();
                circularViewWithTimerAC.stopTimer();
            }
        });
        ACStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s= ACReadyToGo.getText().toString();
                int speech=textToSpeechAC.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                circularViewWithTimerAC.startTimer();
            }
        });
        ACResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerAC.resumeTimer();
            }
        });
        ACPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerAC.pauseTimer();
            }
        });
        textToSpeechAC=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (SUCCESS == status){
                    int lang=textToSpeechAC.setLanguage(Locale.ENGLISH);
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
                                String e= ACFinish.getText().toString();
                                int speech=textToSpeechAC.speak(e,TextToSpeech.QUEUE_FLUSH,null);
                                FragmentManager kneePushUp = getFragmentManager();
                                FragmentTransaction ft = kneePushUp.beginTransaction();
                                ft.replace(R.id.scrollView, new KneePushUpFragment());
                                ft.commit();
                            }

                            @Override
                            public void onTimerCancelled() {
                                Toast.makeText(getContext(),"Timer Cancelled",Toast.LENGTH_SHORT).show();
                            }
                        });
        circularViewWithTimerAC.setOptions(builderWithTimer);
        return view;
    }
}