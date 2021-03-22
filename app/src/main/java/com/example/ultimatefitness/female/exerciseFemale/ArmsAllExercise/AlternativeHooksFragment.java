package com.example.ultimatefitness.female.exerciseFemale.ArmsAllExercise;

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

public class AlternativeHooksFragment extends Fragment {
    ImageView AHImageViewForeword,AHImageViewBack,AHStart,AHResume,AHPause;
    GifImageView AHGif;
    TextView AHReadyToGo,AHFinish;
    TextToSpeech textToSpeechAH;
    CircularView circularViewWithTimerAH;
    private AdView mAdView;
    public AlternativeHooksFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_alternative_hooks, container, false);
        AHImageViewForeword=view.findViewById(R.id.AHForwordBtn);
        AHImageViewBack=view.findViewById(R.id.AHBackbtn);
        AHPause=view.findViewById(R.id.AHpauseBtn);
        AHResume=view.findViewById(R.id.AHresumeBtn);
        AHStart=view.findViewById(R.id.AHstartBtn);
        AHGif=view.findViewById(R.id.AHGif);
        AHReadyToGo=view.findViewById(R.id.AHStartPoint);
        AHFinish=view.findViewById(R.id.AHEndpoint);

        circularViewWithTimerAH=view.findViewById(R.id.AHcircular_view);
        //ad
        mAdView=new AdView(getActivity());
        mAdView.setAdUnitId("ca-app-pub-2342822035961981~6396888741");
        mAdView.setAdSize(AdSize.FULL_BANNER);
        AdView adView=view.findViewById(R.id.adView);
        adView.addView(mAdView);
        AdRequest adRequest= new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        AHImageViewForeword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager updownPlank = getFragmentManager();
                FragmentTransaction ft = updownPlank.beginTransaction();
                ft.replace(R.id.scrollView, new UpDownPlankFragment());
                ft.commit();
                circularViewWithTimerAH.stopTimer();
            }

        });
        AHImageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager kneepushup = getFragmentManager();
                FragmentTransaction ft = kneepushup.beginTransaction();
                ft.replace(R.id.scrollView, new KneePushUpFragment());
                ft.commit();
                circularViewWithTimerAH.stopTimer();
            }
        });
        AHStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s= AHReadyToGo.getText().toString();
                int speech=textToSpeechAH.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                circularViewWithTimerAH.startTimer();
            }
        });
        AHResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerAH.resumeTimer();
            }
        });
        AHPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerAH.pauseTimer();
            }
        });
        textToSpeechAH=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (SUCCESS == status){
                    int lang=textToSpeechAH.setLanguage(Locale.ENGLISH);
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
                                String e= AHFinish.getText().toString();
                                int speech=textToSpeechAH.speak(e,TextToSpeech.QUEUE_FLUSH,null);
                                FragmentManager updownPlank = getFragmentManager();
                                FragmentTransaction ft = updownPlank.beginTransaction();
                                ft.replace(R.id.scrollView, new UpDownPlankFragment());
                                ft.commit();
                            }

                            @Override
                            public void onTimerCancelled() {
                                Toast.makeText(getContext(),"Timer Cancelled",Toast.LENGTH_SHORT).show();
                            }
                        });
        circularViewWithTimerAH.setOptions(builderWithTimer);
        return view;
    }
}