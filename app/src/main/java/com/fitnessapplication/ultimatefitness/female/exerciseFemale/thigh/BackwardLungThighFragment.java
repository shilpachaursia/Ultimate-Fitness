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

public class BackwardLungThighFragment extends Fragment {
    ImageView imageViewforwordBL,backBtnBL,startBL,resumeBL,pauseBL;
    GifImageView gifBL;
    TextView readyBL,endBL;
    TextToSpeech textToSpeechBL;
    CircularView circularViewWithTimerBL;
    private AdView mAdView;
    public BackwardLungThighFragment() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_backward_lung_thigh, container, false);
        imageViewforwordBL=view.findViewById(R.id.BLNext);
        gifBL=view.findViewById(R.id.BLGIf);
        backBtnBL=view.findViewById(R.id.BLback);
        readyBL=view.findViewById(R.id.BLStart);
        endBL=view.findViewById(R.id.BLEndpoint);
        startBL=view.findViewById(R.id.BLstartBtn);
        resumeBL=view.findViewById(R.id.BLresumeBtn);
        pauseBL=view.findViewById(R.id.BLpauseBtn);

        circularViewWithTimerBL=view.findViewById(R.id.BLcircular_view);
        //ad
        mAdView=new AdView(getActivity());
        mAdView.setAdUnitId("ca-app-pub-2342822035961981~6396888741");
        mAdView.setAdSize(AdSize.FULL_BANNER);
        AdView adView=view.findViewById(R.id.adView);
        adView.addView(mAdView);
        AdRequest adRequest= new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        imageViewforwordBL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager topLyingLeg = getFragmentManager();
                FragmentTransaction ft = topLyingLeg.beginTransaction();
                ft.replace(R.id.frameContainerThighExercise, new TopLyingLegLiftThighFragment());
                ft.commit();
            }
        });
        backBtnBL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager donkeyKick=getFragmentManager();
                FragmentTransaction t=donkeyKick.beginTransaction()
                        .replace(R.id.frameContainerThighExercise,new DonkeyKickThighFragment());
                t.commit();
            }
        });
        startBL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s= readyBL.getText().toString();
                int speech=textToSpeechBL.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                circularViewWithTimerBL.startTimer();
            }
        });
        resumeBL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerBL.resumeTimer();
            }
        });
        pauseBL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerBL.pauseTimer();
            }
        });
        textToSpeechBL=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (SUCCESS == status){
                    int lang=textToSpeechBL.setLanguage(Locale.ENGLISH);
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
                                String e=endBL.getText().toString();
                                int speech=textToSpeechBL.speak(e,TextToSpeech.QUEUE_FLUSH,null);
                                FragmentManager toplyingleg = getFragmentManager();
                                FragmentTransaction ft = toplyingleg.beginTransaction();
                                ft.replace(R.id.frameContainerThighExercise, new TopLyingLegLiftThighFragment());
                                ft.commit();
                            }

                            @Override
                            public void onTimerCancelled() {
                            }
                        });
        circularViewWithTimerBL.setOptions(builderWithTimer);
        return view;
    }}

