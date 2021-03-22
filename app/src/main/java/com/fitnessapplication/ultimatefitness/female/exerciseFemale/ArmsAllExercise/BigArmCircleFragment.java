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

public class BigArmCircleFragment extends Fragment {
    ImageView BACImageViewForeword,BACImageViewBack,BACStart,BACResume,BACPause;
    GifImageView BACGif;
    TextView BACReadyToGo,BACFinish;
    TextToSpeech textToSpeechBAC;
    CircularView circularViewWithTimerBAC;
    private AdView mAdView;
    public BigArmCircleFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_big_arm_circle, container, false);
        BACImageViewForeword=view.findViewById(R.id.BACForwordBtn);
        BACImageViewBack=view.findViewById(R.id.BACBackbtn);
        BACPause=view.findViewById(R.id.BACpauseBtn);
        BACResume=view.findViewById(R.id.BACresumeBtn);
        BACStart=view.findViewById(R.id.BACstartBtn);
        BACGif=view.findViewById(R.id.BACGif);
        BACReadyToGo=view.findViewById(R.id.BACStartPoint);
        BACFinish=view.findViewById(R.id.BACEndpoint);

        circularViewWithTimerBAC=view.findViewById(R.id.BACcircular_view);
        //ad
        mAdView=new AdView(getActivity());
        mAdView.setAdUnitId("ca-app-pub-2342822035961981~6396888741");
        mAdView.setAdSize(AdSize.FULL_BANNER);
        AdView adView=view.findViewById(R.id.adView);
        adView.addView(mAdView);
        AdRequest adRequest= new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        BACImageViewForeword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager tricepsStretchLeft = getFragmentManager();
                FragmentTransaction ft = tricepsStretchLeft.beginTransaction();
                ft.replace(R.id.scrollView, new TricpesStretchLeftFragment());
                ft.commit();
                circularViewWithTimerBAC.stopTimer();
            }

        });
        BACImageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager armsScissor = getFragmentManager();
                FragmentTransaction ft = armsScissor.beginTransaction();
                ft.replace(R.id.scrollView, new ArmScissorsFragment());
                ft.commit();
                circularViewWithTimerBAC.stopTimer();
            }
        });
        BACStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s= BACReadyToGo.getText().toString();
                int speech=textToSpeechBAC.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                circularViewWithTimerBAC.startTimer();
            }
        });
        BACResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerBAC.resumeTimer();
            }
        });
        BACPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerBAC.pauseTimer();
            }
        });
        textToSpeechBAC=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (SUCCESS == status){
                    int lang=textToSpeechBAC.setLanguage(Locale.ENGLISH);
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
                                String e= BACFinish.getText().toString();
                                int speech=textToSpeechBAC.speak(e,TextToSpeech.QUEUE_FLUSH,null);
                                FragmentManager tricepsstretchleft = getFragmentManager();
                                FragmentTransaction ft = tricepsstretchleft.beginTransaction();
                                ft.replace(R.id.scrollView, new TricpesStretchLeftFragment());
                                ft.commit();
                            }

                            @Override
                            public void onTimerCancelled() {
                                Toast.makeText(getContext(),"Timer Cancelled",Toast.LENGTH_SHORT).show();
                            }
                        });
        circularViewWithTimerBAC.setOptions(builderWithTimer);
        return view;
    }

}