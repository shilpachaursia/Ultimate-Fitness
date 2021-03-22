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

public class SquatsThighFragment extends Fragment {
    ImageView imageViewforwordST,backBtnST,startST,resumeST,pauseST;
    GifImageView gifST;
    TextView readyST,endST;
    TextToSpeech textToSpeechST;
    CircularView circularViewWithTimerST;
    private AdView mAdView;
    public SquatsThighFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_squats_thigh, container, false);
        imageViewforwordST=view.findViewById(R.id.STForwordBtn);
        gifST=view.findViewById(R.id.STGif);
        backBtnST=view.findViewById(R.id.backST);
        readyST=view.findViewById(R.id.STStart);
        endST=view.findViewById(R.id.STEndpoint);
        startST=view.findViewById(R.id.STstartBtn);
        resumeST=view.findViewById(R.id.STresumeBtn);
        pauseST=view.findViewById(R.id.STpauseBtn);

        circularViewWithTimerST=view.findViewById(R.id.circular_viewST);
        //ad
        mAdView=new AdView(getActivity());
        mAdView.setAdUnitId("ca-app-pub-2342822035961981~6396888741");
        mAdView.setAdSize(AdSize.FULL_BANNER);
        AdView adView=view.findViewById(R.id.adView);
        adView.addView(mAdView);
        AdRequest adRequest= new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        circularViewWithTimerST=view.findViewById(R.id.circular_viewST);
        imageViewforwordST.setOnClickListener(v -> {
            FragmentManager donkeykick = getFragmentManager();
            FragmentTransaction ft = donkeykick.beginTransaction();
            ft.replace(R.id.frameContainerThighExercise, new DonkeyKickThighFragment());
            ft.commit();
            circularViewWithTimerST.stopTimer();
        });
        backBtnST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager jumpingJack=getFragmentManager();
                FragmentTransaction t=jumpingJack.beginTransaction()
                        .replace(R.id.frameContainerThighExercise,new JumpingJackThighFragment());
                t.commit();
                circularViewWithTimerST.stopTimer();
            }
        });
        startST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s= readyST.getText().toString();
                int speech=textToSpeechST.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                circularViewWithTimerST.startTimer();
            }
        });
        resumeST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerST.resumeTimer();
            }
        });
        pauseST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerST.pauseTimer();
            }
        });
        textToSpeechST=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (SUCCESS == status){
                    int lang=textToSpeechST.setLanguage(Locale.ENGLISH);
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
                                String e=endST.getText().toString();
                                int speech=textToSpeechST.speak(e,TextToSpeech.QUEUE_FLUSH,null);
                                FragmentManager donkeykick = getFragmentManager();
                                FragmentTransaction ft = donkeykick.beginTransaction();
                                ft.replace(R.id.frameContainerThighExercise, new DonkeyKickThighFragment());
                                ft.commit();
                            }

                            @Override
                            public void onTimerCancelled() {
                            }
                        });
        circularViewWithTimerST.setOptions(builderWithTimer);
        return view;
    }}




