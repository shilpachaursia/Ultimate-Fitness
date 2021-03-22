package com.fitnessapplication.ultimatefitness.female.exerciseFemale.ButtAllExercise;

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

public class ButtBridgeButtFragment extends Fragment {
    ImageView nextBBB,backBBB,startBBB,resumeBBB,pauseBBB;
    GifImageView gifBBB;
    TextView readyBBB,endBBB;
    TextToSpeech textToSpeechBBB;
    CircularView circularViewWithTimerBBB;
    private AdView mAdView;
    public ButtBridgeButtFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_butt_bridge_butt, container, false);
        nextBBB=view.findViewById(R.id.BBBForwordBtn);
        gifBBB=view.findViewById(R.id.BBBGif);
        readyBBB=view.findViewById(R.id.BBBStartPoint);
        backBBB=view.findViewById(R.id.BBBback);
        endBBB=view.findViewById(R.id.BBBEndpoint);
        startBBB=view.findViewById(R.id.BBBstartBtn);
        resumeBBB=view.findViewById(R.id.BBBresumeBtn);
        pauseBBB=view.findViewById(R.id.BBBpauseBtn);

        circularViewWithTimerBBB=view.findViewById(R.id.BBBcircular_view);
        //ad
        mAdView=new AdView(getActivity());
        mAdView.setAdUnitId("ca-app-pub-2342822035961981~6396888741");
        mAdView.setAdSize(AdSize.FULL_BANNER);
        AdView adView=view.findViewById(R.id.adView);
        adView.addView(mAdView);
        AdRequest adRequest= new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        nextBBB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager MC = getFragmentManager();
                FragmentTransaction ft = MC.beginTransaction();
                ft.replace(R.id.frameButt, new MountainClimberButtFragment());
                ft.commit();
                circularViewWithTimerBBB.stopTimer();
            }
        });
        backBBB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager SKB=getFragmentManager();
                FragmentTransaction transaction=SKB.beginTransaction();
                transaction.replace(R.id.frameButt,new StandingKickBackButtFragment()).commit();
            }
        });
        startBBB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s= readyBBB.getText().toString();
                int speech=textToSpeechBBB.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                circularViewWithTimerBBB.startTimer();
            }
        });
        resumeBBB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerBBB.resumeTimer();
            }
        });
        pauseBBB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerBBB.pauseTimer();
            }
        });
        textToSpeechBBB=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (SUCCESS == status){
                    int lang=textToSpeechBBB.setLanguage(Locale.ENGLISH);
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
                                String e=endBBB.getText().toString();
                                int speech=textToSpeechBBB.speak(e,TextToSpeech.QUEUE_FLUSH,null);
                                FragmentManager MC = getFragmentManager();
                                FragmentTransaction ft = MC.beginTransaction();
                                ft.replace(R.id.frameButt, new MountainClimberButtFragment());
                                ft.commit();
                            }

                            @Override
                            public void onTimerCancelled() {
                                Toast.makeText(getContext(),"Timer Cancelled",Toast.LENGTH_SHORT).show();
                            }
                        });
        circularViewWithTimerBBB.setOptions(builderWithTimer);
        return view;
    }
}
