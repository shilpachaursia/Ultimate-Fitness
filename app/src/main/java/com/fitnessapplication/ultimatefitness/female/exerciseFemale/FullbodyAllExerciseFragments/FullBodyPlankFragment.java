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

public class FullBodyPlankFragment extends Fragment {
    GifImageView Gifpl;
    TextView Readypl,Finishpl;
    ImageView Startpl,Resumepl,Pausepl,Nextpl,Backpl;
    CircularView circularViewpl;
    TextToSpeech textToSpeechpl;
    private AdView mAdView;
    public FullBodyPlankFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_full_body_plank, container, false);
        Gifpl=view.findViewById(R.id.Gifpl);
        Readypl=view.findViewById(R.id.StartPointpl);
        Finishpl=view.findViewById(R.id.Endpointpl);
        Startpl=view.findViewById(R.id.startBtnpl);
        Resumepl=view.findViewById(R.id.resumeBtnpl);
        Pausepl=view.findViewById(R.id.pauseBtnpl);
        Nextpl=view.findViewById(R.id.ForwordBtnpl);
        Backpl=view.findViewById(R.id.Backbtnpl);
        circularViewpl=view.findViewById(R.id.circular_viewpl);
        //ad
        mAdView=new AdView(getActivity());
        mAdView.setAdUnitId("ca-app-pub-2342822035961981~6396888741");
        mAdView.setAdSize(AdSize.FULL_BANNER);
        AdView adView=view.findViewById(R.id.adView);
        adView.addView(mAdView);
        AdRequest adRequest= new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        Nextpl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager cobra=getFragmentManager();
                FragmentTransaction t=cobra.beginTransaction();
                t.replace(R.id.FBGFrameChange,new CobraStretchFragment());
                t.commit();
                circularViewpl.stopTimer();
            }
        });
        Backpl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fhr=getFragmentManager();
                FragmentTransaction fragmentTransaction=fhr.beginTransaction();
                fragmentTransaction.replace(R.id.FBGFrameChange,new FireHydrantRightFragment());
                fragmentTransaction.commit();
                circularViewpl.stopTimer();
            }
        });
        textToSpeechpl=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (SUCCESS==status){
                    int lang=textToSpeechpl.setLanguage(Locale.ENGLISH);
                }
            }
        });
        Startpl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s= Readypl.getText().toString();
                int speech=textToSpeechpl.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                circularViewpl.startTimer();
            }
        });
        Resumepl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewpl.resumeTimer();
            }
        });
        Pausepl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewpl.pauseTimer();
            }
        });
        CircularView.OptionsBuilder builderWithTimer=
                new CircularView.OptionsBuilder()
                        .shouldDisplayText(true)
                        .setCounterInSeconds(30)
                        .setCircularViewCallback(new CircularViewCallback(){
                            @Override
                            public void onTimerFinish() {
                                String e= Finishpl.getText().toString();
                                int speech=textToSpeechpl.speak(e,TextToSpeech.QUEUE_FLUSH,null);
                                FragmentManager cobra = getFragmentManager();
                                FragmentTransaction ft = cobra.beginTransaction();
                                ft.replace(R.id.FBGFrameChange, new CobraStretchFragment());
                                ft.commit();}

                            @Override
                            public void onTimerCancelled() {
                                Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();

                            }
                        });
        circularViewpl.setOptions(builderWithTimer);
        return view;
    }

}