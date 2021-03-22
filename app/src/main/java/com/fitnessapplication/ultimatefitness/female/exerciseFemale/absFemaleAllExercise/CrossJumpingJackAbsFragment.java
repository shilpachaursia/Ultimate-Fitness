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

public class CrossJumpingJackAbsFragment extends Fragment {
    ImageView nextCJJA,startCJJA,resumeCJJA,pauseCJJA;
    GifImageView gifCJJA;
    TextView readyCJJA,endCJJA;
    TextToSpeech textToSpeechCJJA;
    CircularView circularViewWithTimerCJJA;
    private AdView mAdView;
    public CrossJumpingJackAbsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_cross_jumping_jack_abs, container, false);
        nextCJJA=view.findViewById(R.id.CJJAForwordBtn);
        gifCJJA=view.findViewById(R.id.CJJAGif);
        readyCJJA=view.findViewById(R.id.CJJAStartPoint);
        endCJJA=view.findViewById(R.id.CJJAEndpoint);
        startCJJA=view.findViewById(R.id.CJJAstartBtn);
        resumeCJJA=view.findViewById(R.id.CJJAresumeBtn);
        pauseCJJA=view.findViewById(R.id.CJJApauseBtn);

        circularViewWithTimerCJJA=view.findViewById(R.id.CJJAcircular_view);
        //ad
        mAdView=new AdView(getActivity());
        mAdView.setAdUnitId("ca-app-pub-2342822035961981~6396888741");
        mAdView.setAdSize(AdSize.FULL_BANNER);
        AdView adView=view.findViewById(R.id.adView);
        adView.addView(mAdView);
        AdRequest adRequest= new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        nextCJJA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager HS = getFragmentManager();
                FragmentTransaction ft = HS.beginTransaction();
                ft.replace(R.id.absFrameLayout, new HighSteppingAbsFragment());
                ft.commit();
                circularViewWithTimerCJJA.stopTimer();
            }
        });
        startCJJA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s= readyCJJA.getText().toString();
                int speech=textToSpeechCJJA.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                circularViewWithTimerCJJA.startTimer();
            }
        });
        resumeCJJA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerCJJA.resumeTimer();
            }
        });
        pauseCJJA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerCJJA.pauseTimer();
            }
        });
        textToSpeechCJJA=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (SUCCESS == status){
                    int lang=textToSpeechCJJA.setLanguage(Locale.ENGLISH);
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
                                String e=endCJJA.getText().toString();
                                int speech=textToSpeechCJJA.speak(e,TextToSpeech.QUEUE_FLUSH,null);
                                FragmentManager hs = getFragmentManager();
                                FragmentTransaction ft = hs.beginTransaction();
                                ft.replace(R.id.absFrameLayout, new HighSteppingAbsFragment());
                                ft.commit();
                            }

                            @Override
                            public void onTimerCancelled() {
                                Toast.makeText(getContext(),"Timer Cancelled",Toast.LENGTH_SHORT).show();
                            }
                        });
        circularViewWithTimerCJJA.setOptions(builderWithTimer);
        return view;
    }
}
