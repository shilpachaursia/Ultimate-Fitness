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

public class VShitThighFragment extends Fragment {
    ImageView imageViewforwordVS,backBtnVS,startVS,resumeVS,pauseVS;
    GifImageView gifVS;
    TextView readyVS,endVS;
    TextToSpeech textToSpeechVS;
    CircularView circularViewWithTimerVS;
    private AdView mAdView;
    public VShitThighFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_v_shit_thigh, container, false);
        gifVS=view.findViewById(R.id.VSGIf);
        backBtnVS=view.findViewById(R.id.VSback);
        readyVS=view.findViewById(R.id.VSStart);
        endVS=view.findViewById(R.id.VSEndpoint);
        startVS=view.findViewById(R.id.VSstartBtn);
        resumeVS=view.findViewById(R.id.VSresumeBtn);
        pauseVS=view.findViewById(R.id.VSpauseBtn);

        circularViewWithTimerVS=view.findViewById(R.id.circular_viewVS);
        //ad
        mAdView=new AdView(getActivity());
        mAdView.setAdUnitId("ca-app-pub-2342822035961981~6396888741");
        mAdView.setAdSize(AdSize.FULL_BANNER);
        AdView adView=view.findViewById(R.id.adView);
        adView.addView(mAdView);
        AdRequest adRequest= new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        backBtnVS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager Spiderman=getFragmentManager();
                FragmentTransaction t=Spiderman.beginTransaction()
                        .replace(R.id.frameContainerThighExercise,new SpidermanPlankThighFragmnet());
                t.commit();
            }
        });
        startVS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s= readyVS.getText().toString();
                int speech=textToSpeechVS.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                circularViewWithTimerVS.startTimer();
            }
        });
        resumeVS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerVS.resumeTimer();
            }
        });
        pauseVS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerVS.pauseTimer();
            }
        });
        textToSpeechVS=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (SUCCESS == status){
                    int lang=textToSpeechVS.setLanguage(Locale.ENGLISH);
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
                                String e=endVS.getText().toString();
                                int speech=textToSpeechVS.speak(e,TextToSpeech.QUEUE_FLUSH,null);
                            }

                            @Override
                            public void onTimerCancelled() {
                            }
                        });
        circularViewWithTimerVS.setOptions(builderWithTimer);
        return view;
    }}
