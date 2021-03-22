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

public class TricepsDipsFragment extends Fragment {
    ImageView TDImageViewForeword,TDImageViewBack,TDStart,TDResume,TDPause;
    GifImageView TDGif;
    TextView TDReadyToGo,TDFinish;
    TextToSpeech textToSpeechTD;
    CircularView circularViewWithTimerTD;
    private AdView mAdView;
    public TricepsDipsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_triceps_dips, container, false);
        TDImageViewForeword=view.findViewById(R.id.TDForwordBtn);
        TDImageViewBack=view.findViewById(R.id.TDGifBackBtn);
        TDPause=view.findViewById(R.id.TDpauseBtn);
        TDResume=view.findViewById(R.id.TDresumeBtn);
        TDStart=view.findViewById(R.id.TDstartBtn);
        TDGif=view.findViewById(R.id.TDGif);
        TDReadyToGo=view.findViewById(R.id.TDStartPoint);
        TDFinish=view.findViewById(R.id.TDEndpoint);

        circularViewWithTimerTD=view.findViewById(R.id.TDcircular_view);
        //ad
        mAdView=new AdView(getActivity());
        mAdView.setAdUnitId("ca-app-pub-2342822035961981~6396888741");
        mAdView.setAdSize(AdSize.FULL_BANNER);
        AdView adView=view.findViewById(R.id.adView);
        adView.addView(mAdView);
        AdRequest adRequest= new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        TDImageViewForeword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager pushup = getFragmentManager();
                FragmentTransaction ft = pushup.beginTransaction();
                ft.replace(R.id.scrollView, new PushUpsFragment());
                ft.commit();
                circularViewWithTimerTD.stopTimer();
            }

        });
        TDImageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager punches = getFragmentManager();
                FragmentTransaction ft = punches.beginTransaction();
                ft.replace(R.id.scrollView, new PunchesFragment());
                ft.commit();
                circularViewWithTimerTD.stopTimer();
            }
        });
        TDStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s= TDReadyToGo.getText().toString();
                int speech=textToSpeechTD.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                circularViewWithTimerTD.startTimer();
            }
        });
        TDResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerTD.resumeTimer();
            }
        });
        TDPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerTD.pauseTimer();
            }
        });
        textToSpeechTD=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (SUCCESS == status){
                    int lang=textToSpeechTD.setLanguage(Locale.ENGLISH);
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
                                String e=TDFinish.getText().toString();
                                int speech=textToSpeechTD.speak(e,TextToSpeech.QUEUE_FLUSH,null);
                                FragmentManager pushup = getFragmentManager();
                                FragmentTransaction ft = pushup.beginTransaction();
                                ft.replace(R.id.scrollView, new PushUpsFragment());
                                ft.commit();
                            }

                            @Override
                            public void onTimerCancelled() {
                                Toast.makeText(getContext(),"Timer Cancelled",Toast.LENGTH_SHORT).show();
                            }
                        });
        circularViewWithTimerTD.setOptions(builderWithTimer);
        return view;
    }

}