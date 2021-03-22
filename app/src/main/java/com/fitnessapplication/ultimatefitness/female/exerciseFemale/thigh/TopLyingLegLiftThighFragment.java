package com.fitnessapplication.ultimatefitness.female.exerciseFemale.thigh;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

public class TopLyingLegLiftThighFragment extends Fragment {
    ImageView imageViewforwordTLL,backBtnTLL,startTLL,resumeTLL,pauseTLL;
    GifImageView gifTLL;
    TextView readyTLL,endTLL;
    TextToSpeech textToSpeechTLL;
    CircularView circularViewWithTimerTLL;
    private AdView mAdView;
    public TopLyingLegLiftThighFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_top_lying_leg_lift_thigh, container, false);
        imageViewforwordTLL=view.findViewById(R.id.TLLNext);
        gifTLL=view.findViewById(R.id.TLLGIf);
        backBtnTLL=view.findViewById(R.id.TLLback);
        readyTLL=view.findViewById(R.id.TLLStart);
        endTLL=view.findViewById(R.id.TLLEndpoint);
        startTLL=view.findViewById(R.id.TLLstartBtn);
        resumeTLL=view.findViewById(R.id.TLLresumeBtn);
        pauseTLL=view.findViewById(R.id.TLLpauseBtn);

        circularViewWithTimerTLL=view.findViewById(R.id.TLLcircular_view);
        //ad
        mAdView=new AdView(getActivity());
        mAdView.setAdUnitId("ca-app-pub-2342822035961981~6396888741");
        mAdView.setAdSize(AdSize.FULL_BANNER);
        AdView adView=view.findViewById(R.id.adView);
        adView.addView(mAdView);
        AdRequest adRequest= new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        imageViewforwordTLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager modifiedBurpees = getFragmentManager();
                FragmentTransaction ft = modifiedBurpees.beginTransaction();
                ft.replace(R.id.frameContainerThighExercise, new MdifiedBurpeesThighFrangment());
                ft.commit();
                circularViewWithTimerTLL.stopTimer();
            }
        });
        backBtnTLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager backwardLung=getFragmentManager();
                FragmentTransaction t=backwardLung.beginTransaction()
                        .replace(R.id.frameContainerThighExercise,new BackwardLungThighFragment());
                t.commit();
            }
        });
        startTLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s= readyTLL.getText().toString();
                int speech=textToSpeechTLL.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                circularViewWithTimerTLL.startTimer();
            }
        });
        resumeTLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerTLL.resumeTimer();
            }
        });
        pauseTLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerTLL.pauseTimer();
            }
        });
        textToSpeechTLL=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (SUCCESS == status){
                    int lang=textToSpeechTLL.setLanguage(Locale.ENGLISH);
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
                                String e=endTLL.getText().toString();
                                int speech=textToSpeechTLL.speak(e,TextToSpeech.QUEUE_FLUSH,null);
                                FragmentManager modifiedBurpees = getFragmentManager();
                                FragmentTransaction ft = modifiedBurpees.beginTransaction();
                                ft.replace(R.id.frameContainerThighExercise, new MdifiedBurpeesThighFrangment());
                                ft.commit();
                            }

                            @Override
                            public void onTimerCancelled() {
                            }
                        });
        circularViewWithTimerTLL.setOptions(builderWithTimer);
        return view;
    }}

