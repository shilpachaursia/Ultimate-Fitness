package com.example.ultimatefitness.female.exerciseFemale.thigh;

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

import com.example.ultimatefitness.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import java.util.Locale;

import pl.droidsonroids.gif.GifImageView;
import ticker.views.com.ticker.widgets.circular.timer.callbacks.CircularViewCallback;
import ticker.views.com.ticker.widgets.circular.timer.view.CircularView;

import static android.speech.tts.TextToSpeech.SUCCESS;

public class MdifiedBurpeesThighFrangment extends Fragment {
    ImageView imageViewforwordMB,backBtnMB,startMB,resumeMB,pauseMB;
    GifImageView gifMB;
    TextView readyMB,endMB;
    TextToSpeech textToSpeechMB;
    CircularView circularViewWithTimerMB;
    private AdView mAdView;
    public MdifiedBurpeesThighFrangment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_mdified_burpees_thigh_frangment, container, false);
        imageViewforwordMB=view.findViewById(R.id.MBNext);
        gifMB=view.findViewById(R.id.MBGIf);
        backBtnMB=view.findViewById(R.id.backMB);
        readyMB=view.findViewById(R.id.MBStart);
        endMB=view.findViewById(R.id.MBEndpoint);
        startMB=view.findViewById(R.id.MBstartBtn);
        resumeMB=view.findViewById(R.id.MBresumeBtn);
        pauseMB=view.findViewById(R.id.MBpauseBtn);

        circularViewWithTimerMB=view.findViewById(R.id.circular_viewMB);
        //ad
        mAdView=new AdView(getActivity());
        mAdView.setAdUnitId("ca-app-pub-2342822035961981~6396888741");
        mAdView.setAdSize(AdSize.FULL_BANNER);
        AdView adView=view.findViewById(R.id.adView);
        adView.addView(mAdView);
        AdRequest adRequest= new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        imageViewforwordMB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager kneeHug = getFragmentManager();
                FragmentTransaction ft = kneeHug.beginTransaction();
                ft.replace(R.id.frameContainerThighExercise, new KneeHugThighFragment());
                ft.commit();
                circularViewWithTimerMB.stopTimer();
            }
        });
        backBtnMB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager topLying=getFragmentManager();
                FragmentTransaction t=topLying.beginTransaction()
                        .replace(R.id.frameContainerThighExercise,new TopLyingLegLiftThighFragment());
                t.commit();
            }
        });
        startMB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s= readyMB.getText().toString();
                int speech=textToSpeechMB.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                circularViewWithTimerMB.startTimer();
            }
        });
        resumeMB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerMB.resumeTimer();
            }
        });
        pauseMB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerMB.pauseTimer();
            }
        });
        textToSpeechMB=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (SUCCESS == status){
                    int lang=textToSpeechMB.setLanguage(Locale.ENGLISH);
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
                                String e=endMB.getText().toString();
                                int speech=textToSpeechMB.speak(e,TextToSpeech.QUEUE_FLUSH,null);
                                FragmentManager KNEEhUG = getFragmentManager();
                                FragmentTransaction ft = KNEEhUG.beginTransaction();
                                ft.replace(R.id.frameContainerThighExercise, new KneeHugThighFragment());
                                ft.commit();
                            }

                            @Override
                            public void onTimerCancelled() {
                            }
                        });
        circularViewWithTimerMB.setOptions(builderWithTimer);
        return view;
    }}


