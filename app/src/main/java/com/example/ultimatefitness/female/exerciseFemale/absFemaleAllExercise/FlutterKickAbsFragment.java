package com.example.ultimatefitness.female.exerciseFemale.absFemaleAllExercise;

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

public class FlutterKickAbsFragment extends Fragment {
    ImageView nextFKA,backFKA,startFKA,resumeFKA,pauseFKA;
    GifImageView gifFKA;
    TextView readyFKA,endFKA;
    TextToSpeech textToSpeechFKA;
    CircularView circularViewWithTimerFKA;
    private AdView mAdView;
    public FlutterKickAbsFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_flutter_kick_abs, container, false);
        nextFKA=view.findViewById(R.id.CJJAForwordBtn);
        gifFKA=view.findViewById(R.id.CJJAGif);
        readyFKA=view.findViewById(R.id.CJJAStartPoint);
        backFKA=view.findViewById(R.id.CJJAback);
        endFKA=view.findViewById(R.id.CJJAEndpoint);
        startFKA=view.findViewById(R.id.CJJAstartBtn);
        resumeFKA=view.findViewById(R.id.CJJAresumeBtn);
        pauseFKA=view.findViewById(R.id.CJJApauseBtn);

        circularViewWithTimerFKA=view.findViewById(R.id.CJJAcircular_view);
        //ad
        mAdView=new AdView(getActivity());
        mAdView.setAdUnitId("ca-app-pub-2342822035961981~6396888741");
        mAdView.setAdSize(AdSize.FULL_BANNER);
        AdView adView=view.findViewById(R.id.adView);
        adView.addView(mAdView);
        AdRequest adRequest= new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        nextFKA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager rs = getFragmentManager();
                FragmentTransaction ft = rs.beginTransaction();
                ft.replace(R.id.absFrameLayout, new LegAriseAbsFragment());
                ft.commit();
                circularViewWithTimerFKA.stopTimer();
            }
        });
        backFKA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager hs=getFragmentManager();
                FragmentTransaction transaction=hs.beginTransaction();
                transaction.replace(R.id.absFrameLayout,new MountainClimberAbsFragment()).commit();
                circularViewWithTimerFKA.stopTimer();
            }
        });
        startFKA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s= readyFKA.getText().toString();
                int speech=textToSpeechFKA.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                circularViewWithTimerFKA.startTimer();
            }
        });
        resumeFKA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerFKA.resumeTimer();
            }
        });
        pauseFKA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerFKA.pauseTimer();
            }
        });
        textToSpeechFKA=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (SUCCESS == status){
                    int lang=textToSpeechFKA.setLanguage(Locale.ENGLISH);
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
                                String e=endFKA.getText().toString();
                                int speech=textToSpeechFKA.speak(e,TextToSpeech.QUEUE_FLUSH,null);
                                FragmentManager rs = getFragmentManager();
                                FragmentTransaction ft = rs.beginTransaction();
                                ft.replace(R.id.absFrameLayout, new LegAriseAbsFragment());
                                ft.commit();
                            }

                            @Override
                            public void onTimerCancelled() {
                                Toast.makeText(getContext(),"Timer Cancelled",Toast.LENGTH_SHORT).show();
                            }
                        });
        circularViewWithTimerFKA.setOptions(builderWithTimer);
        return view;
    }
}