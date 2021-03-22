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

public class StandingBicycleStretchLeftFragment extends Fragment {
    ImageView SBSLImageViewForeword,SBSLImageViewBack,SBSLStart,SBSLResume,SBSLPause;
    GifImageView SBSLGif;
    TextView SBSLReadyToGo,SBSLFinish;
    TextToSpeech textToSpeechSBSL;
    CircularView circularViewWithTimerSBSL;
    private AdView mAdView;
    public StandingBicycleStretchLeftFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_standing_bicycle_stretch_left, container, false);
        SBSLImageViewForeword=view.findViewById(R.id.SBSLForwordBtn);
        SBSLImageViewBack=view.findViewById(R.id.SBSLBackbtn);
        SBSLPause=view.findViewById(R.id.SBSLpauseBtn);
        SBSLResume=view.findViewById(R.id.SBSLresumeBtn);
        SBSLStart=view.findViewById(R.id.SBSLstartBtn);
        SBSLGif=view.findViewById(R.id.SBSLGif);
        SBSLReadyToGo=view.findViewById(R.id.SBSLStartPoint);
        SBSLFinish=view.findViewById(R.id.SBSLEndpoint);

        circularViewWithTimerSBSL=view.findViewById(R.id.SBSLcircular_view);
        //ad
        mAdView=new AdView(getActivity());
        mAdView.setAdUnitId("ca-app-pub-2342822035961981~6396888741");
        mAdView.setAdSize(AdSize.FULL_BANNER);
        AdView adView=view.findViewById(R.id.adView);
        adView.addView(mAdView);
        AdRequest adRequest= new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        SBSLImageViewForeword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager standingBicycleStretchRight = getFragmentManager();
                FragmentTransaction ft = standingBicycleStretchRight.beginTransaction();
                ft.replace(R.id.scrollView, new StandingBicycleStretchRightFragment());
                ft.commit();
                circularViewWithTimerSBSL.stopTimer();
            }

        });
        SBSLImageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager TSr = getFragmentManager();
                FragmentTransaction ft = TSr.beginTransaction();
                ft.replace(R.id.scrollView, new TricpesStretchRightFragment());
                ft.commit();
                circularViewWithTimerSBSL.stopTimer();
            }
        });
        SBSLStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s= SBSLReadyToGo.getText().toString();
                int speech=textToSpeechSBSL.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                circularViewWithTimerSBSL.startTimer();
            }
        });
        SBSLResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerSBSL.resumeTimer();
            }
        });
        SBSLPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerSBSL.pauseTimer();
            }
        });
        textToSpeechSBSL=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (SUCCESS == status){
                    int lang=textToSpeechSBSL.setLanguage(Locale.ENGLISH);
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
                                String e= SBSLFinish.getText().toString();
                                int speech=textToSpeechSBSL.speak(e,TextToSpeech.QUEUE_FLUSH,null);
                                FragmentManager SBCr = getFragmentManager();
                                FragmentTransaction ft = SBCr.beginTransaction();
                                ft.replace(R.id.scrollView, new StandingBicycleStretchRightFragment());
                                ft.commit();
                            }

                            @Override
                            public void onTimerCancelled() {
                                Toast.makeText(getContext(),"Timer Cancelled",Toast.LENGTH_SHORT).show();
                            }
                        });
        circularViewWithTimerSBSL.setOptions(builderWithTimer);
        return view;
    }

}