package com.fitnessapplication.ultimatefitness.female.exerciseFemale.ArmsAllExercise;

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

public class TricpesStretchRightFragment extends Fragment {
    ImageView TSRImageViewForeword,TSRImageViewBack,TSRStart,TSRResume,TSRPause;
    GifImageView TSRGif;
    TextView TSRReadyToGo,TSRFinish;
    TextToSpeech textToSpeechTSR;
    CircularView circularViewWithTimerTSR;
    private AdView mAdView;
    public TricpesStretchRightFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_tricpes_stretch_right, container, false);
        TSRImageViewForeword=view.findViewById(R.id.TSRForwordBtn);
        TSRImageViewBack=view.findViewById(R.id.TSRBackbtn);
        TSRPause=view.findViewById(R.id.TSRpauseBtn);
        TSRResume=view.findViewById(R.id.TSRresumeBtn);
        TSRStart=view.findViewById(R.id.TSRstartBtn);
        TSRGif=view.findViewById(R.id.TSRGif);
        TSRReadyToGo=view.findViewById(R.id.TSRStartPoint);
        TSRFinish=view.findViewById(R.id.TSREndpoint);

        circularViewWithTimerTSR=view.findViewById(R.id.TSRcircular_view);
        //ad
        mAdView=new AdView(getActivity());
        mAdView.setAdUnitId("ca-app-pub-2342822035961981~6396888741");
        mAdView.setAdSize(AdSize.FULL_BANNER);
        AdView adView=view.findViewById(R.id.adView);
        adView.addView(mAdView);
        AdRequest adRequest= new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        TSRImageViewForeword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager standingBicycleStretchLeft = getFragmentManager();
                FragmentTransaction ft = standingBicycleStretchLeft.beginTransaction();
                ft.replace(R.id.scrollView, new StandingBicycleStretchLeftFragment());
                ft.commit();
                circularViewWithTimerTSR.stopTimer();
            }

        });
        TSRImageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager TSL = getFragmentManager();
                FragmentTransaction ft = TSL.beginTransaction();
                ft.replace(R.id.scrollView, new TricpesStretchLeftFragment());
                ft.commit();
                circularViewWithTimerTSR.stopTimer();
            }
        });
        TSRStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s= TSRReadyToGo.getText().toString();
                int speech=textToSpeechTSR.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                circularViewWithTimerTSR.startTimer();
            }
        });
        TSRResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerTSR.resumeTimer();
            }
        });
        TSRPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerTSR.pauseTimer();
            }
        });
        textToSpeechTSR=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (SUCCESS == status){
                    int lang=textToSpeechTSR.setLanguage(Locale.ENGLISH);
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
                                String e= TSRFinish.getText().toString();
                                int speech=textToSpeechTSR.speak(e,TextToSpeech.QUEUE_FLUSH,null);
                                FragmentManager SBCL = getFragmentManager();
                                FragmentTransaction ft = SBCL.beginTransaction();
                                ft.replace(R.id.scrollView, new StandingBicycleStretchLeftFragment());
                                ft.commit();
                            }

                            @Override
                            public void onTimerCancelled() {
                                Toast.makeText(getContext(),"Timer Cancelled",Toast.LENGTH_SHORT).show();
                            }
                        });
        circularViewWithTimerTSR.setOptions(builderWithTimer);
        return view;
    }

}