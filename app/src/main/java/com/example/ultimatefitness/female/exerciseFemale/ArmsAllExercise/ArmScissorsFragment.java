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

public class ArmScissorsFragment extends Fragment {
    ImageView ASImageViewForeword,ASImageViewBack,ASStart,ASResume,ASPause;
    GifImageView ASGif;
    TextView ASReadyToGo,ASFinish;
    TextToSpeech textToSpeechAS;
    private AdView mAdView;
    CircularView circularViewWithTimerAS;
    public ArmScissorsFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_arm_scissors, container, false);
        ASImageViewForeword=view.findViewById(R.id.ASForwordBtn);
        ASImageViewBack=view.findViewById(R.id.ASBackbtn);
        ASPause=view.findViewById(R.id.ASpauseBtn);
        ASResume=view.findViewById(R.id.ASresumeBtn);
        ASStart=view.findViewById(R.id.ASstartBtn);
        ASGif=view.findViewById(R.id.ASGif);
        ASReadyToGo=view.findViewById(R.id.ASStartPoint);
        ASFinish=view.findViewById(R.id.ASEndpoint);

        circularViewWithTimerAS=view.findViewById(R.id.AScircular_view);
        //ad
        mAdView=new AdView(getActivity());
        mAdView.setAdUnitId("ca-app-pub-2342822035961981~6396888741");
        mAdView.setAdSize(AdSize.FULL_BANNER);
        AdView adView=view.findViewById(R.id.adView);
        adView.addView(mAdView);
        AdRequest adRequest= new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        ASImageViewForeword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager bigArmCircle = getFragmentManager();
                FragmentTransaction ft = bigArmCircle.beginTransaction();
                ft.replace(R.id.scrollView, new BigArmCircleFragment());
                ft.commit();
                circularViewWithTimerAS.stopTimer();
            }

        });
        ASImageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager updownPlank = getFragmentManager();
                FragmentTransaction ft = updownPlank.beginTransaction();
                ft.replace(R.id.scrollView, new UpDownPlankFragment());
                ft.commit();
                circularViewWithTimerAS.stopTimer();
            }
        });
        ASStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s= ASReadyToGo.getText().toString();
                int speech=textToSpeechAS.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                circularViewWithTimerAS.startTimer();
            }
        });
        ASResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerAS.resumeTimer();
            }
        });
        ASPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerAS.pauseTimer();
            }
        });
        textToSpeechAS=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (SUCCESS == status){
                    int lang=textToSpeechAS.setLanguage(Locale.ENGLISH);
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
                                String e= ASFinish.getText().toString();
                                int speech=textToSpeechAS.speak(e,TextToSpeech.QUEUE_FLUSH,null);
                                FragmentManager bigarmcircle = getFragmentManager();
                                FragmentTransaction ft = bigarmcircle.beginTransaction();
                                ft.replace(R.id.scrollView, new BigArmCircleFragment());
                                ft.commit();
                            }

                            @Override
                            public void onTimerCancelled() {
                                Toast.makeText(getContext(),"Timer Cancelled",Toast.LENGTH_SHORT).show();
                            }
                        });
        circularViewWithTimerAS.setOptions(builderWithTimer);
        return view;
    }


}