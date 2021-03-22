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

public class StandingBicycleCrunchFragment extends Fragment {
    ImageView nextSBC,backSBC,startSCB,resumeSBC,pauseSBC;
    GifImageView gifSBC;
    TextView readySBC,endSBC;
    TextToSpeech textToSpeechSBC;
    CircularView circularViewWithTimerSBC;
    private AdView mAdView;
    public StandingBicycleCrunchFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_standing_bicycle_crunch, container, false);
        nextSBC=view.findViewById(R.id.CJJAForwordBtn);
        gifSBC=view.findViewById(R.id.CJJAGif);
        readySBC=view.findViewById(R.id.CJJAStartPoint);
        backSBC=view.findViewById(R.id.CJJAback);
        endSBC=view.findViewById(R.id.CJJAEndpoint);
        startSCB=view.findViewById(R.id.CJJAstartBtn);
        resumeSBC=view.findViewById(R.id.CJJAresumeBtn);
        pauseSBC=view.findViewById(R.id.CJJApauseBtn);

        circularViewWithTimerSBC=view.findViewById(R.id.CJJAcircular_view);
        //ad
        mAdView=new AdView(getActivity());
        mAdView.setAdUnitId("ca-app-pub-2342822035961981~6396888741");
        mAdView.setAdSize(AdSize.FULL_BANNER);
        AdView adView=view.findViewById(R.id.adView);
        adView.addView(mAdView);
        AdRequest adRequest= new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        nextSBC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager rs = getFragmentManager();
                FragmentTransaction ft = rs.beginTransaction();
                ft.replace(R.id.absFrameLayout, new RussianTwistAbsFragment());
                ft.commit();
                circularViewWithTimerSBC.stopTimer();
            }
        });
        backSBC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager hs=getFragmentManager();
                FragmentTransaction transaction=hs.beginTransaction();
                transaction.replace(R.id.absFrameLayout,new HighSteppingAbsFragment()).commit();
                circularViewWithTimerSBC.stopTimer();
            }
        });
        startSCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s= readySBC.getText().toString();
                int speech=textToSpeechSBC.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                circularViewWithTimerSBC.startTimer();
            }
        });
        resumeSBC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerSBC.resumeTimer();
            }
        });
        pauseSBC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerSBC.pauseTimer();
            }
        });
        textToSpeechSBC=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (SUCCESS == status){
                    int lang=textToSpeechSBC.setLanguage(Locale.ENGLISH);
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
                                String e=endSBC.getText().toString();
                                int speech=textToSpeechSBC.speak(e,TextToSpeech.QUEUE_FLUSH,null);
                                FragmentManager rs = getFragmentManager();
                                FragmentTransaction ft = rs.beginTransaction();
                                ft.replace(R.id.absFrameLayout, new RussianTwistAbsFragment());
                                ft.commit();
                            }

                            @Override
                            public void onTimerCancelled() {
                                Toast.makeText(getContext(),"Timer Cancelled",Toast.LENGTH_SHORT).show();
                            }
                        });
        circularViewWithTimerSBC.setOptions(builderWithTimer);
        return view;
    }
}
