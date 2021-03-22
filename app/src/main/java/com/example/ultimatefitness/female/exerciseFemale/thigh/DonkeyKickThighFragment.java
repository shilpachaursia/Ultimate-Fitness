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

public class DonkeyKickThighFragment extends Fragment {
    ImageView imageViewforwordDK,backBtnDK,startDK,resumeDK,pauseDK;
    GifImageView gifDK;
    TextView readyDK,endDK;
    TextToSpeech textToSpeechDK;
    CircularView circularViewWithTimerDK;
    private AdView mAdView;
    public DonkeyKickThighFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {    // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_donkey_kick_thigh, container, false);
        imageViewforwordDK=view.findViewById(R.id.DKNext);
        gifDK=view.findViewById(R.id.DKGIf);
        backBtnDK=view.findViewById(R.id.backDK);
        readyDK=view.findViewById(R.id.DKStart);
        endDK=view.findViewById(R.id.DKEndpoint);
        startDK=view.findViewById(R.id.DKstartBtn);
        resumeDK=view.findViewById(R.id.DKresumeBtn);
        pauseDK=view.findViewById(R.id.DKpauseBtn);

        circularViewWithTimerDK=view.findViewById(R.id.circular_viewDK);
        //ad
        mAdView=new AdView(getActivity());
        mAdView.setAdUnitId("ca-app-pub-2342822035961981~6396888741");
        mAdView.setAdSize(AdSize.FULL_BANNER);
        AdView adView=view.findViewById(R.id.adView);
        adView.addView(mAdView);
        AdRequest adRequest= new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        imageViewforwordDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager backwardLung = getFragmentManager();
                FragmentTransaction ft = backwardLung.beginTransaction();
                ft.replace(R.id.frameContainerThighExercise, new BackwardLungThighFragment());
                ft.commit();
                circularViewWithTimerDK.stopTimer();
            }
        });
        backBtnDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager squats=getFragmentManager();
                FragmentTransaction t=squats.beginTransaction().replace(R.id.frameContainerThighExercise,new SquatsThighFragment());
                t.commit();
                circularViewWithTimerDK.stopTimer();
            }
        });
        startDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s= readyDK.getText().toString();
                int speech=textToSpeechDK.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                circularViewWithTimerDK.startTimer();
            }
        });
        resumeDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerDK.resumeTimer();
            }
        });
        pauseDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerDK.pauseTimer();
            }
        });
        textToSpeechDK=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (SUCCESS == status){
                    int lang=textToSpeechDK.setLanguage(Locale.ENGLISH);
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
                                String e=endDK.getText().toString();
                                int speech=textToSpeechDK.speak(e,TextToSpeech.QUEUE_FLUSH,null);
                                FragmentManager backwardLung = getFragmentManager();
                                FragmentTransaction ft = backwardLung.beginTransaction();
                                ft.replace(R.id.frameContainerThighExercise, new BackwardLungThighFragment());
                                ft.commit();
                            }

                            @Override
                            public void onTimerCancelled() {
                            }
                        });
        circularViewWithTimerDK.setOptions(builderWithTimer);
        return view;
    }}



