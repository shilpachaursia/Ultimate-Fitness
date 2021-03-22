package com.fitnessapplication.ultimatefitness.female.exerciseFemale.FullbodyAllExerciseFragments;

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

public class FireHydrantRightFragment extends Fragment {
    GifImageView FHRGif;
    TextView FHRReady,FHRFinish;
    ImageView FHRStart,FHRResume,FHRPause,FHRNext,FHRBack;
    CircularView circularViewFHR;
    TextToSpeech textToSpeechFHR;
    private AdView mAdView;
    public FireHydrantRightFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fire_hydrant_right, container, false);
        FHRGif=view.findViewById(R.id.FHRGif);
        FHRReady=view.findViewById(R.id.FHRStartPoint);
        FHRFinish=view.findViewById(R.id.FHREndpoint);
        FHRStart=view.findViewById(R.id.FHRstartBtn);
        FHRResume=view.findViewById(R.id.FHRresumeBtn);
        FHRPause=view.findViewById(R.id.FHRpauseBtn);
        FHRNext=view.findViewById(R.id.FHRForwordBtn);
        FHRBack=view.findViewById(R.id.FHRBackbtn);
        circularViewFHR=view.findViewById(R.id.FHRcircular_view);
        //ad
        mAdView=new AdView(getActivity());
        mAdView.setAdUnitId("ca-app-pub-2342822035961981~6396888741");
        mAdView.setAdSize(AdSize.FULL_BANNER);
        AdView adView=view.findViewById(R.id.adView);
        adView.addView(mAdView);
        AdRequest adRequest= new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        FHRNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fpalnk=getFragmentManager();
                FragmentTransaction t=fpalnk.beginTransaction();
                t.replace(R.id.FBGFrameChange,new FullBodyPlankFragment());
                t.commit();
                circularViewFHR.stopTimer();
            }
        });
        FHRBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager FireHydrantleft=getFragmentManager();
                FragmentTransaction fragmentTransaction=FireHydrantleft.beginTransaction();
                fragmentTransaction.replace(R.id.FBGFrameChange,new FireHydrantLeftFragment());
                fragmentTransaction.commit();
                circularViewFHR.stopTimer();
            }
        });
        textToSpeechFHR=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (SUCCESS==status){
                    int lang=textToSpeechFHR.setLanguage(Locale.ENGLISH);
                }
            }
        });
        FHRStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s= FHRReady.getText().toString();
                int speech=textToSpeechFHR.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                circularViewFHR.startTimer();
            }
        });
        FHRResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewFHR.resumeTimer();
            }
        });
        FHRPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewFHR.pauseTimer();
            }
        });
        CircularView.OptionsBuilder builderWithTimer=
                new CircularView.OptionsBuilder()
                        .shouldDisplayText(true)
                        .setCounterInSeconds(30)
                        .setCircularViewCallback(new CircularViewCallback(){
                            @Override
                            public void onTimerFinish() {
                                String e= FHRFinish.getText().toString();
                                int speech=textToSpeechFHR.speak(e,TextToSpeech.QUEUE_FLUSH,null);
                                FragmentManager fplank = getFragmentManager();
                                FragmentTransaction ft = fplank.beginTransaction();
                                ft.replace(R.id.FBGFrameChange, new FullBodyPlankFragment());
                                ft.commit();}

                            @Override
                            public void onTimerCancelled() {
                                Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();

                            }
                        });
        circularViewFHR.setOptions(builderWithTimer);
        return view;
    }

}