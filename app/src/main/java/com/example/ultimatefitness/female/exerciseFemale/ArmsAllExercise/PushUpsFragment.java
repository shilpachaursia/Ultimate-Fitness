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

public class PushUpsFragment extends Fragment {
    ImageView PUImageViewForeword,PUImageViewBack,PUStart,PUResume,PUPause;
    GifImageView PUGif;
    TextView PUReadyToGo,PUFinish;
    TextToSpeech textToSpeechPU;
    CircularView circularViewWithTimerPU;
    private AdView mAdView;
    public PushUpsFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_push_ups, container, false);
        PUImageViewForeword=view.findViewById(R.id.PUForwordBtn);
        PUImageViewBack=view.findViewById(R.id.PUBackbtn);
        PUPause=view.findViewById(R.id.PUpauseBtn);
        PUResume=view.findViewById(R.id.PUresumeBtn);
        PUStart=view.findViewById(R.id.PUstartBtn);
        PUGif=view.findViewById(R.id.PUGif);
        PUReadyToGo=view.findViewById(R.id.PUStartPoint);
        PUFinish=view.findViewById(R.id.PUEndpoint);

        circularViewWithTimerPU=view.findViewById(R.id.PUcircular_view);
        //ad
        mAdView=new AdView(getActivity());
        mAdView.setAdUnitId("ca-app-pub-2342822035961981~6396888741");
        mAdView.setAdSize(AdSize.FULL_BANNER);
        AdView adView=view.findViewById(R.id.adView);
        adView.addView(mAdView);
        AdRequest adRequest= new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        PUImageViewForeword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager crossJumpingJack = getFragmentManager();
                FragmentTransaction ft = crossJumpingJack.beginTransaction();
                ft.replace(R.id.scrollView, new CrossJumpingJackFragment());
                ft.commit();
                circularViewWithTimerPU.stopTimer();
            }

        });
        PUImageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager tricepsdip = getFragmentManager();
                FragmentTransaction ft = tricepsdip.beginTransaction();
                ft.replace(R.id.scrollView, new TricepsDipsFragment());
                ft.commit();
                circularViewWithTimerPU.stopTimer();
            }
        });
        PUStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s= PUReadyToGo.getText().toString();
                int speech=textToSpeechPU.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                circularViewWithTimerPU.startTimer();
            }
        });
        PUResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerPU.resumeTimer();
            }
        });
        PUPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerPU.pauseTimer();
            }
        });
        textToSpeechPU=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (SUCCESS == status){
                    int lang=textToSpeechPU.setLanguage(Locale.ENGLISH);
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
                                String e= PUFinish.getText().toString();
                                int speech=textToSpeechPU.speak(e,TextToSpeech.QUEUE_FLUSH,null);
                                FragmentManager crossJumpingJack = getFragmentManager();
                                FragmentTransaction ft = crossJumpingJack.beginTransaction();
                                ft.replace(R.id.scrollView, new CrossJumpingJackFragment());
                                ft.commit();
                            }

                            @Override
                            public void onTimerCancelled() {
                                Toast.makeText(getContext(),"Timer Cancelled",Toast.LENGTH_SHORT).show();
                            }
                        });
        circularViewWithTimerPU.setOptions(builderWithTimer);
        return view;
    }
}