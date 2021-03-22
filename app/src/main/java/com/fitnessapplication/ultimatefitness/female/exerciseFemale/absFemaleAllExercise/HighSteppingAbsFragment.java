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

public class HighSteppingAbsFragment extends Fragment {
    ImageView nextHSA,backHSA,startHSA,resumeHSA,pauseHSA;
    GifImageView gifHSA;
    TextView readyHSA,endHSA;
    TextToSpeech textToSpeechHSA;
    CircularView circularViewWithTimerHSA;
    private AdView mAdView;
    public HighSteppingAbsFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_high_stepping_abs, container, false);
        backHSA=view.findViewById(R.id.CJJAback);
        nextHSA=view.findViewById(R.id.CJJAForwordBtn);
        gifHSA=view.findViewById(R.id.CJJAGif);
        readyHSA=view.findViewById(R.id.CJJAStartPoint);
        endHSA=view.findViewById(R.id.CJJAEndpoint);
        startHSA=view.findViewById(R.id.CJJAstartBtn);
        resumeHSA=view.findViewById(R.id.CJJAresumeBtn);
        pauseHSA=view.findViewById(R.id.CJJApauseBtn);

        circularViewWithTimerHSA=view.findViewById(R.id.CJJAcircular_view);
        //ad
        mAdView=new AdView(getActivity());
        mAdView.setAdUnitId("ca-app-pub-2342822035961981~6396888741");
        mAdView.setAdSize(AdSize.FULL_BANNER);
        AdView adView=view.findViewById(R.id.adView);
        adView.addView(mAdView);
        AdRequest adRequest= new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        nextHSA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager sbc = getFragmentManager();
                FragmentTransaction ft = sbc.beginTransaction();
                ft.replace(R.id.absFrameLayout, new StandingBicycleCrunchFragment());
                ft.commit();
                circularViewWithTimerHSA.stopTimer();
            }
        });
        backHSA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager cjja = getFragmentManager();
                FragmentTransaction ft = cjja.beginTransaction();
                ft.replace(R.id.absFrameLayout, new CrossJumpingJackAbsFragment());
                ft.commit();
                circularViewWithTimerHSA.stopTimer();

            }
        });
        startHSA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s= readyHSA.getText().toString();
                int speech=textToSpeechHSA.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                circularViewWithTimerHSA.startTimer();
            }
        });
        resumeHSA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerHSA.resumeTimer();
            }
        });
        pauseHSA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerHSA.pauseTimer();
            }
        });
        textToSpeechHSA=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (SUCCESS == status){
                    int lang=textToSpeechHSA.setLanguage(Locale.ENGLISH);
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
                                String e=endHSA.getText().toString();
                                int speech=textToSpeechHSA.speak(e,TextToSpeech.QUEUE_FLUSH,null);
                                FragmentManager sbc = getFragmentManager();
                                FragmentTransaction ft = sbc.beginTransaction();
                                ft.replace(R.id.absFrameLayout, new StandingBicycleCrunchFragment());
                                ft.commit();
                            }

                            @Override
                            public void onTimerCancelled() {
                                Toast.makeText(getContext(),"Timer Cancelled",Toast.LENGTH_SHORT).show();
                            }
                        });
        circularViewWithTimerHSA.setOptions(builderWithTimer);
        return view;
    }
}
