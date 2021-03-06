package com.fitnessapplication.ultimatefitness.female.exerciseFemale.absFemaleAllExercise;

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

public class RussianTwistAbsFragment extends Fragment {
    ImageView nextRTA,backRTA,startRTA,resumeRTA,pauseRTA;
    GifImageView gifRTA;
    TextView readyRTA,endRTA;
    TextToSpeech textToSpeechRTA;
    CircularView circularViewWithTimerRTA;
    private AdView mAdView;
    public RussianTwistAbsFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_russian_twist, container, false);
        nextRTA=view.findViewById(R.id.CJJAForwordBtn);
        gifRTA=view.findViewById(R.id.CJJAGif);
        readyRTA=view.findViewById(R.id.CJJAStartPoint);
        backRTA=view.findViewById(R.id.CJJAback);
        endRTA=view.findViewById(R.id.CJJAEndpoint);
        startRTA=view.findViewById(R.id.CJJAstartBtn);
        resumeRTA=view.findViewById(R.id.CJJAresumeBtn);
        pauseRTA=view.findViewById(R.id.CJJApauseBtn);

        circularViewWithTimerRTA=view.findViewById(R.id.CJJAcircular_view);
        //ad
        mAdView=new AdView(getActivity());
        mAdView.setAdUnitId("ca-app-pub-2342822035961981~6396888741");
        mAdView.setAdSize(AdSize.FULL_BANNER);
        AdView adView=view.findViewById(R.id.adView);
        adView.addView(mAdView);
        AdRequest adRequest= new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        nextRTA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager rs = getFragmentManager();
                FragmentTransaction ft = rs.beginTransaction();
                ft.replace(R.id.absFrameLayout, new MountainClimberAbsFragment());
                ft.commit();
                circularViewWithTimerRTA.stopTimer();
            }
        });
        backRTA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager hs=getFragmentManager();
                FragmentTransaction transaction=hs.beginTransaction();
                transaction.replace(R.id.absFrameLayout,new StandingBicycleCrunchFragment()).commit();
                circularViewWithTimerRTA.stopTimer();
            }
        });
        startRTA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s= readyRTA.getText().toString();
                int speech=textToSpeechRTA.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                circularViewWithTimerRTA.startTimer();
            }
        });
        resumeRTA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerRTA.resumeTimer();
            }
        });
        pauseRTA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerRTA.pauseTimer();
            }
        });
        textToSpeechRTA=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (SUCCESS == status){
                    int lang=textToSpeechRTA.setLanguage(Locale.ENGLISH);
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
                                String e=endRTA.getText().toString();
                                int speech=textToSpeechRTA.speak(e,TextToSpeech.QUEUE_FLUSH,null);
                                FragmentManager rs = getFragmentManager();
                                FragmentTransaction ft = rs.beginTransaction();
                                ft.replace(R.id.absFrameLayout, new MountainClimberAbsFragment());
                                ft.commit();
                            }

                            @Override
                            public void onTimerCancelled() {
                                Toast.makeText(getContext(),"Timer Cancelled",Toast.LENGTH_SHORT).show();
                            }
                        });
        circularViewWithTimerRTA.setOptions(builderWithTimer);
        return view;
    }
}
