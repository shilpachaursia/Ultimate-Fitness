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

public class StandingBicycleStretchRightFragment extends Fragment {
    ImageView SBSRImageViewBack,SBSRStart,SBSRResume,SBSRPause;
    GifImageView SBSRGif;
    TextView SBSRReadyToGo,SBSRFinish;
    TextToSpeech textToSpeechSBSR;
    CircularView circularViewWithTimerSBSR;
    private AdView mAdView;
    public StandingBicycleStretchRightFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_standing_bicycle_stretch_right, container, false);

        SBSRImageViewBack=view.findViewById(R.id.SBSRBackbtn);
        SBSRPause=view.findViewById(R.id.SBSRpauseBtn);
        SBSRResume=view.findViewById(R.id.SBSRresumeBtn);
        SBSRStart=view.findViewById(R.id.SBSRstartBtn);
        SBSRGif=view.findViewById(R.id.SBSRGif);
        SBSRReadyToGo=view.findViewById(R.id.SBSRStartPoint);
        SBSRFinish=view.findViewById(R.id.SBSREndpoint);

        circularViewWithTimerSBSR=view.findViewById(R.id.SBSRcircular_view);
        //ad
        mAdView=new AdView(getActivity());
        mAdView.setAdUnitId("ca-app-pub-2342822035961981~6396888741");
        mAdView.setAdSize(AdSize.FULL_BANNER);
        AdView adView=view.findViewById(R.id.adView);
        adView.addView(mAdView);
        AdRequest adRequest= new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        SBSRImageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager sbsl = getFragmentManager();
                FragmentTransaction ft = sbsl.beginTransaction();
                ft.replace(R.id.scrollView, new StandingBicycleStretchLeftFragment());
                ft.commit();
                circularViewWithTimerSBSR.stopTimer();
            }
        });
        SBSRStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s= SBSRReadyToGo.getText().toString();
                int speech=textToSpeechSBSR.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                circularViewWithTimerSBSR.startTimer();
            }
        });
        SBSRResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerSBSR.resumeTimer();
            }
        });
        SBSRPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerSBSR.pauseTimer();
            }
        });
        textToSpeechSBSR=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (SUCCESS == status){
                    int lang=textToSpeechSBSR.setLanguage(Locale.ENGLISH);
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
                                String e= SBSRFinish.getText().toString();
                                int speech=textToSpeechSBSR.speak(e,TextToSpeech.QUEUE_FLUSH,null);
                            }

                            @Override
                            public void onTimerCancelled() {
                                Toast.makeText(getContext(),"Timer Cancelled",Toast.LENGTH_SHORT).show();
                            }
                        });
        circularViewWithTimerSBSR.setOptions(builderWithTimer);
        return view;
    }

}