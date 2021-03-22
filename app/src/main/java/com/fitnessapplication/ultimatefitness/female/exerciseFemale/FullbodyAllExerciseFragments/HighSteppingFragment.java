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

public class
HighSteppingFragment extends Fragment {
    GifImageView HSFGif;
    TextView HSFReady,HSFFinish;
    ImageView HSFStart,HSFResume,HSFPause,HSFNext;
    CircularView circularViewHSF;
    TextToSpeech textToSpeechHSF;
    private AdView mAdView;
    public HighSteppingFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_high_stepping, container, false);
        HSFGif=view.findViewById(R.id.HSFGif);
        HSFReady=view.findViewById(R.id.HSFStartPoint);
        HSFFinish=view.findViewById(R.id.HSFEndpoint);
        HSFStart=view.findViewById(R.id.HSFstartBtn);
        HSFResume=view.findViewById(R.id.HSFresumeBtn);
        HSFPause=view.findViewById(R.id.HSFpauseBtn);
        HSFNext=view.findViewById(R.id.HSFForwordBtn);

        circularViewHSF=view.findViewById(R.id.HSFcircular_view);
        //ad
        mAdView=new AdView(getActivity());
        mAdView.setAdUnitId("ca-app-pub-2342822035961981~6396888741");
        mAdView.setAdSize(AdSize.FULL_BANNER);
        AdView adView=view.findViewById(R.id.adView);
        adView.addView(mAdView);
        AdRequest adRequest= new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        HSFNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager sidehop = getFragmentManager();
                FragmentTransaction ft = sidehop.beginTransaction();
                ft.replace(R.id.FBGFrameChange, new SideHopFragment());
                ft.commit();
                circularViewHSF.stopTimer();
            }
        });
        textToSpeechHSF=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (SUCCESS==status){
                    int lang=textToSpeechHSF.setLanguage(Locale.ENGLISH);
                }
            }
        });
        HSFStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s= HSFReady.getText().toString();
                int speech=textToSpeechHSF.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                circularViewHSF.startTimer();
            }
        });
        HSFResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewHSF.resumeTimer();
            }
        });
        HSFPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewHSF.pauseTimer();
            }
        });
        CircularView.OptionsBuilder builderWithTimer=
                new CircularView.OptionsBuilder()
                        .shouldDisplayText(true)
                        .setCounterInSeconds(30)
                        .setCircularViewCallback(new CircularViewCallback(){
                    @Override
                    public void onTimerFinish() {
                        String e= HSFFinish.getText().toString();
                        int speech=textToSpeechHSF.speak(e,TextToSpeech.QUEUE_FLUSH,null);
                        FragmentManager sidehop = getFragmentManager();
                        FragmentTransaction ft = sidehop.beginTransaction()
                                .replace(R.id.FBGFrameChange, new SideHopFragment());
                        ft.commit();
                    }

                    @Override
                    public void onTimerCancelled() {
                        Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();

                    }
                });
        circularViewHSF.setOptions(builderWithTimer);
        return view;
    }
}