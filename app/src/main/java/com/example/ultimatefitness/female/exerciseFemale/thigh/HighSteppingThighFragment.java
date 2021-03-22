package com.example.ultimatefitness.female.exerciseFemale.thigh;

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

public class HighSteppingThighFragment extends Fragment {
    ImageView imageViewforwordHST,startHST,resumeHST,pauseHST;
    GifImageView gifHST;
    TextView readyHST,endHST;
    TextToSpeech textToSpeechHST;
    CircularView circularViewWithTimerHST;
    private AdView mAdView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_high_stepping_thigh, container, false);
        imageViewforwordHST=view.findViewById(R.id.HSTForwordBtn);
        gifHST=view.findViewById(R.id.thighHighSteppingGif);
        readyHST=view.findViewById(R.id.HSTStartPoint);
        endHST=view.findViewById(R.id.HSTEndpoint);
        startHST=view.findViewById(R.id.HSTstartBtn);
        resumeHST=view.findViewById(R.id.HSTresumeBtn);
        pauseHST=view.findViewById(R.id.HSTpauseBtn);

        circularViewWithTimerHST=view.findViewById(R.id.circular_viewHST);
        //ad
        mAdView=new AdView(getActivity());
        mAdView.setAdUnitId("ca-app-pub-2342822035961981~6396888741");
        mAdView.setAdSize(AdSize.FULL_BANNER);
        AdView adView=view.findViewById(R.id.adView);
        adView.addView(mAdView);
        AdRequest adRequest= new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        imageViewforwordHST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager jjp = getFragmentManager();
                FragmentTransaction ft = jjp.beginTransaction();
                ft.replace(R.id.frameContainerThighExercise, new JumpingJackThighFragment());
                ft.commit();
                circularViewWithTimerHST.stopTimer();
            }
        });
        startHST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s= readyHST.getText().toString();
                int speech=textToSpeechHST.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                circularViewWithTimerHST.startTimer();
            }
        });
        resumeHST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerHST.resumeTimer();
            }
        });
        pauseHST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerHST.pauseTimer();
            }
        });
        textToSpeechHST=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (SUCCESS == status){
                    int lang=textToSpeechHST.setLanguage(Locale.ENGLISH);
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
                                String e=endHST.getText().toString();
                                int speech=textToSpeechHST.speak(e,TextToSpeech.QUEUE_FLUSH,null);
                                FragmentManager jjp = getFragmentManager();
                                FragmentTransaction ft = jjp.beginTransaction();
                                ft.replace(R.id.frameContainerThighExercise, new JumpingJackThighFragment());
                                ft.commit();
                            }

                            @Override
                            public void onTimerCancelled() {
                                Toast.makeText(getContext(),"Timer Cancelled",Toast.LENGTH_SHORT).show();
                            }
                        });
        circularViewWithTimerHST.setOptions(builderWithTimer);
        return view;
    }}

