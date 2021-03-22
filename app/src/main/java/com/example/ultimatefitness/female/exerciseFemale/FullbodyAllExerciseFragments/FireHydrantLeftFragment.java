package com.example.ultimatefitness.female.exerciseFemale.FullbodyAllExerciseFragments;

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

public class FireHydrantLeftFragment extends Fragment {
    GifImageView FHLGif;
    TextView FHLReady,FHLFinish;
    ImageView FHLStart,FHLResume,FHLPause,FHLNext,FHLBack;
    CircularView circularViewFHL;
    TextToSpeech textToSpeechFHL;
    private AdView mAdView;
    public FireHydrantLeftFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fire_hydrant_left, container, false);
        FHLGif=view.findViewById(R.id.FHLGif);
        FHLReady=view.findViewById(R.id.FHLStartPoint);
        FHLFinish=view.findViewById(R.id.FHLEndpoint);
        FHLStart=view.findViewById(R.id.FHLstartBtn);
        FHLResume=view.findViewById(R.id.FHLresumeBtn);
        FHLPause=view.findViewById(R.id.FHLpauseBtn);
        FHLNext=view.findViewById(R.id.FHLForwordBtn);
        FHLBack=view.findViewById(R.id.FHLBackbtn);
        circularViewFHL=view.findViewById(R.id.FHLcircular_view);
        //ad
        mAdView=new AdView(getActivity());
        mAdView.setAdUnitId("ca-app-pub-2342822035961981~6396888741");
        mAdView.setAdSize(AdSize.FULL_BANNER);
        AdView adView=view.findViewById(R.id.adView);
        adView.addView(mAdView);
        AdRequest adRequest= new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        FHLNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fireHydrantRight=getFragmentManager();
                FragmentTransaction t=fireHydrantRight.beginTransaction();
                t.replace(R.id.FBGFrameChange,new FireHydrantRightFragment());
                t.commit();
                circularViewFHL.stopTimer();
            }
        });
        FHLBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager FireHydrantRight=getFragmentManager();
                FragmentTransaction fragmentTransaction=FireHydrantRight.beginTransaction();
                fragmentTransaction.replace(R.id.FBGFrameChange,new FullBodyButtBrigeFragment());
                fragmentTransaction.commit();
                circularViewFHL.stopTimer();
            }
        });
        textToSpeechFHL=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (SUCCESS==status){
                    int lang=textToSpeechFHL.setLanguage(Locale.ENGLISH);
                }
            }
        });
        FHLStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s= FHLReady.getText().toString();
                int speech=textToSpeechFHL.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                circularViewFHL.startTimer();
            }
        });
        FHLResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewFHL.resumeTimer();
            }
        });
        FHLPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewFHL.pauseTimer();
            }
        });
        CircularView.OptionsBuilder builderWithTimer=
                new CircularView.OptionsBuilder()
                        .shouldDisplayText(true)
                        .setCounterInSeconds(30)
                        .setCircularViewCallback(new CircularViewCallback(){
                            @Override
                            public void onTimerFinish() {
                                String e= FHLFinish.getText().toString();
                                int speech=textToSpeechFHL.speak(e,TextToSpeech.QUEUE_FLUSH,null);
                                FragmentManager fireHydrantRight = getFragmentManager();
                                FragmentTransaction ft = fireHydrantRight.beginTransaction();
                                ft.replace(R.id.FBGFrameChange, new FireHydrantRightFragment());
                                ft.commit();}

                            @Override
                            public void onTimerCancelled() {
                                Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();

                            }
                        });
        circularViewFHL.setOptions(builderWithTimer);
        return view;
    }

}