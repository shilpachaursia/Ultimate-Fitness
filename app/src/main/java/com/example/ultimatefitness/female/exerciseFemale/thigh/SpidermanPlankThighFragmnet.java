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

public class SpidermanPlankThighFragmnet extends Fragment {
    ImageView imageViewforwordSP,backBtnSP,startSP,resumeSP,pauseSP;
    GifImageView gifSP;
    TextView readySP,endSP;
    TextToSpeech textToSpeechSP;
    CircularView circularViewWithTimerSP;
    private AdView mAdView;
    public SpidermanPlankThighFragmnet() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_spiderman_plank_thigh_fragmnet,container, false);
        imageViewforwordSP=view.findViewById(R.id.SPNext);
        gifSP=view.findViewById(R.id.SPGIf);
        backBtnSP=view.findViewById(R.id.SPback);
        readySP=view.findViewById(R.id.SPStart);
        endSP=view.findViewById(R.id.SPEndpoint);
        startSP=view.findViewById(R.id.SPstartBtn);
        resumeSP=view.findViewById(R.id.SPresumeBtn);
        pauseSP=view.findViewById(R.id.SPpauseBtn);

        circularViewWithTimerSP=view.findViewById(R.id.circular_viewSP);
        //ad
        mAdView=new AdView(getActivity());
        mAdView.setAdUnitId("ca-app-pub-2342822035961981~6396888741");
        mAdView.setAdSize(AdSize.FULL_BANNER);
        AdView adView=view.findViewById(R.id.adView);
        adView.addView(mAdView);
        AdRequest adRequest= new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        imageViewforwordSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager VShit = getFragmentManager();
                FragmentTransaction ft = VShit.beginTransaction();
                ft.replace(R.id.frameContainerThighExercise, new VShitThighFragment());
                ft.commit();
                circularViewWithTimerSP.stopTimer();
            }
        });
        backBtnSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager kneeHug=getFragmentManager();
                FragmentTransaction t=kneeHug.beginTransaction()
                        .replace(R.id.frameContainerThighExercise,new KneeHugThighFragment());
                t.commit();
            }
        });
        startSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s= readySP.getText().toString();
                int speech=textToSpeechSP.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                circularViewWithTimerSP.startTimer();
            }
        });
        resumeSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerSP.resumeTimer();
            }
        });
        pauseSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerSP.pauseTimer();
            }
        });
        textToSpeechSP=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (SUCCESS == status){
                    int lang=textToSpeechSP.setLanguage(Locale.ENGLISH);
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
                                String e=endSP.getText().toString();
                                int speech=textToSpeechSP.speak(e,TextToSpeech.QUEUE_FLUSH,null);
                                FragmentManager vshit = getFragmentManager();
                                FragmentTransaction ft = vshit.beginTransaction();
                                ft.replace(R.id.frameContainerThighExercise, new VShitThighFragment());
                                ft.commit();
                            }

                            @Override
                            public void onTimerCancelled() {
                            }
                        });
        circularViewWithTimerSP.setOptions(builderWithTimer);
        return view;
    }}
