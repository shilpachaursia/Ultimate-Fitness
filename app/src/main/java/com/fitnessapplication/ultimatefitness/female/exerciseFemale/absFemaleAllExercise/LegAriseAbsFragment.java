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


public class LegAriseAbsFragment extends Fragment {
    ImageView nextLAA,backLAA,startLAA,resumeLAA,pauseLAA;
    GifImageView gifLAA;
    TextView readyLAA,endLAA;
    TextToSpeech textToSpeechLAA;
    CircularView circularViewWithTimerLAA;
    private AdView mAdView;
    public LegAriseAbsFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_leg_arise_abs, container, false);
        nextLAA=view.findViewById(R.id.CJJAForwordBtn);
        gifLAA=view.findViewById(R.id.CJJAGif);
        readyLAA=view.findViewById(R.id.CJJAStartPoint);
        backLAA=view.findViewById(R.id.CJJAback);
        endLAA=view.findViewById(R.id.CJJAEndpoint);
        startLAA=view.findViewById(R.id.CJJAstartBtn);
        resumeLAA=view.findViewById(R.id.CJJAresumeBtn);
        pauseLAA=view.findViewById(R.id.CJJApauseBtn);

        circularViewWithTimerLAA=view.findViewById(R.id.CJJAcircular_view);
        //ad
        mAdView=new AdView(getActivity());
        mAdView.setAdUnitId("ca-app-pub-2342822035961981~6396888741");
        mAdView.setAdSize(AdSize.FULL_BANNER);
        AdView adView=view.findViewById(R.id.adView);
        adView.addView(mAdView);
        AdRequest adRequest= new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        nextLAA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager rs = getFragmentManager();
                FragmentTransaction ft = rs.beginTransaction();
                ft.replace(R.id.absFrameLayout, new ButtBridgeAbsFragment());
                ft.commit();
                circularViewWithTimerLAA.stopTimer();
            }
        });
        backLAA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager hs=getFragmentManager();
                FragmentTransaction transaction=hs.beginTransaction();
                transaction.replace(R.id.absFrameLayout,new FlutterKickAbsFragment()).commit();
                circularViewWithTimerLAA.stopTimer();
            }
        });
        startLAA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s= readyLAA.getText().toString();
                int speech=textToSpeechLAA.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                circularViewWithTimerLAA.startTimer();
            }
        });
        resumeLAA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerLAA.resumeTimer();
            }
        });
        pauseLAA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerLAA.pauseTimer();
            }
        });
        textToSpeechLAA=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (SUCCESS == status){
                    int lang=textToSpeechLAA.setLanguage(Locale.ENGLISH);
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
                                String e=endLAA.getText().toString();
                                int speech=textToSpeechLAA.speak(e,TextToSpeech.QUEUE_FLUSH,null);
                                FragmentManager rs = getFragmentManager();
                                FragmentTransaction ft = rs.beginTransaction();
                                ft.replace(R.id.absFrameLayout, new ButtBridgeAbsFragment());
                                ft.commit();
                            }

                            @Override
                            public void onTimerCancelled() {
                                Toast.makeText(getContext(),"Timer Cancelled",Toast.LENGTH_SHORT).show();
                            }
                        });
        circularViewWithTimerLAA.setOptions(builderWithTimer);
        return view;
    }
}