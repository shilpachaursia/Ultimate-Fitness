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

public class KneeHugThighFragment extends Fragment {
    ImageView imageViewforwordKHT,backBtnKHT,startKHT,resumeKHT,pauseKHT;
    GifImageView gifKHT;
    TextView readyKHT,endKHT;
    TextToSpeech textToSpeechKHT;
    CircularView circularViewWithTimerKHT;
    private AdView mAdView;
    public KneeHugThighFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_knee_hug_thigh, container, false);
        imageViewforwordKHT=view.findViewById(R.id.KHTNext);
        gifKHT=view.findViewById(R.id.KHTGIf);
        backBtnKHT=view.findViewById(R.id.KHTback);
        readyKHT=view.findViewById(R.id.KHTStart);
        endKHT=view.findViewById(R.id.KHTEndpoint);
        startKHT=view.findViewById(R.id.KHTstartBtn);
        resumeKHT=view.findViewById(R.id.KHTresumeBtn);
        pauseKHT=view.findViewById(R.id.KHTpauseBtn);

        circularViewWithTimerKHT=view.findViewById(R.id.circular_viewKHT);
        //ad
        mAdView=new AdView(getActivity());
        mAdView.setAdUnitId("ca-app-pub-2342822035961981~6396888741");
        mAdView.setAdSize(AdSize.FULL_BANNER);
        AdView adView=view.findViewById(R.id.adView);
        adView.addView(mAdView);
        AdRequest adRequest= new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        imageViewforwordKHT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager spidermanPlank = getFragmentManager();
                FragmentTransaction ft = spidermanPlank.beginTransaction();
                ft.replace(R.id.frameContainerThighExercise, new SpidermanPlankThighFragmnet());
                ft.commit();
                circularViewWithTimerKHT.stopTimer();
            }
        });
        backBtnKHT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager modifiedBufpres=getFragmentManager();
                FragmentTransaction t=modifiedBufpres.beginTransaction()
                        .replace(R.id.frameContainerThighExercise,new MdifiedBurpeesThighFrangment());
                t.commit();
            }
        });
        startKHT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s= readyKHT.getText().toString();
                int speech=textToSpeechKHT.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                circularViewWithTimerKHT.startTimer();
            }
        });
        resumeKHT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerKHT.resumeTimer();
            }
        });
        pauseKHT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerKHT.pauseTimer();
            }
        });
        textToSpeechKHT=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (SUCCESS == status){
                    int lang=textToSpeechKHT.setLanguage(Locale.ENGLISH);
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
                                String e=endKHT.getText().toString();
                                int speech=textToSpeechKHT.speak(e,TextToSpeech.QUEUE_FLUSH,null);
                                FragmentManager spidermanPlank = getFragmentManager();
                                FragmentTransaction ft = spidermanPlank.beginTransaction();
                                ft.replace(R.id.frameContainerThighExercise, new SpidermanPlankThighFragmnet());
                                ft.commit();
                            }

                            @Override
                            public void onTimerCancelled() {
                            }
                        });
        circularViewWithTimerKHT.setOptions(builderWithTimer);
        return view;
    }}