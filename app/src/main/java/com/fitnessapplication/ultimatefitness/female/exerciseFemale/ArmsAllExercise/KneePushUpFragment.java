package com.fitnessapplication.ultimatefitness.female.exerciseFemale.ArmsAllExercise;

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

import com.fitnessapplication.ultimatefitness.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import java.util.Locale;

import pl.droidsonroids.gif.GifImageView;
import ticker.views.com.ticker.widgets.circular.timer.callbacks.CircularViewCallback;
import ticker.views.com.ticker.widgets.circular.timer.view.CircularView;

import static android.speech.tts.TextToSpeech.SUCCESS;

public class KneePushUpFragment extends Fragment {
    ImageView KPUImageViewForeword,KPUImageViewBack,KPUStart,KPUResume,KPUPause;
    GifImageView KPUGif;
    TextView KPUReadyToGo,KPUFinish;
    TextToSpeech textToSpeechKPU;
    CircularView circularViewWithTimerKPU;
    private AdView mAdView;
    public KneePushUpFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_knee_push_up, container, false);
        KPUImageViewForeword=view.findViewById(R.id.KPUForwordBtn);
        KPUImageViewBack=view.findViewById(R.id.KPUBackbtn);
        KPUPause=view.findViewById(R.id.KPUpauseBtn);
        KPUResume=view.findViewById(R.id.KPUresumeBtn);
        KPUStart=view.findViewById(R.id.KPUstartBtn);
        KPUGif=view.findViewById(R.id.KPUGif);
        KPUReadyToGo=view.findViewById(R.id.KPUStartPoint);
        KPUFinish=view.findViewById(R.id.KPUEndpoint);

        circularViewWithTimerKPU=view.findViewById(R.id.KPUcircular_view);
        //ad
        mAdView=new AdView(getActivity());
        mAdView.setAdUnitId("ca-app-pub-2342822035961981~6396888741");
        mAdView.setAdSize(AdSize.FULL_BANNER);
        AdView adView=view.findViewById(R.id.adView);
        adView.addView(mAdView);
        AdRequest adRequest= new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        KPUImageViewForeword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager alternativeHook = getFragmentManager();
                FragmentTransaction ft = alternativeHook.beginTransaction();
                ft.replace(R.id.scrollView, new AlternativeHooksFragment());
                ft.commit();
                circularViewWithTimerKPU.stopTimer();
            }

        });
        KPUImageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager armCircle = getFragmentManager();
                FragmentTransaction ft = armCircle.beginTransaction();
                ft.replace(R.id.scrollView, new ArmsCircleFragment());
                ft.commit();
                circularViewWithTimerKPU.stopTimer();
            }
        });
        KPUStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s= KPUReadyToGo.getText().toString();
                int speech=textToSpeechKPU.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                circularViewWithTimerKPU.startTimer();
            }
        });
        KPUResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerKPU.resumeTimer();
            }
        });
        KPUPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerKPU.pauseTimer();
            }
        });
        textToSpeechKPU=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (SUCCESS == status){
                    int lang=textToSpeechKPU.setLanguage(Locale.ENGLISH);
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
                                String e= KPUFinish.getText().toString();
                                int speech=textToSpeechKPU.speak(e,TextToSpeech.QUEUE_FLUSH,null);
                                FragmentManager alternativeHook = getFragmentManager();
                                FragmentTransaction ft = alternativeHook.beginTransaction();
                                ft.replace(R.id.scrollView, new AlternativeHooksFragment());
                                ft.commit();
                            }

                            @Override
                            public void onTimerCancelled() {
                                Toast.makeText(getContext(),"Timer Cancelled",Toast.LENGTH_SHORT).show();
                            }
                        });
        circularViewWithTimerKPU.setOptions(builderWithTimer);
        return view;
    }
}