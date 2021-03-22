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


public class PunchesFragment extends Fragment {
    ImageView PunchesImageViewForeword,PunchesImageViewBack,PunchesStart,PunchesResume,PunchesPause;
    GifImageView PunchesGif;
    TextView PunchesReadyToGo,PunchesFinish;
    TextToSpeech textToSpeechPunches;
    CircularView circularViewWithTimerPunches;
    private AdView mAdView;
    public PunchesFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_punches, container, false);
        PunchesImageViewForeword=view.findViewById(R.id.punchesGifForewordBtn);
        PunchesImageViewBack=view.findViewById(R.id.punchesGifBackBtn);
        PunchesPause=view.findViewById(R.id.punchesGifpauseBtn);
        PunchesResume=view.findViewById(R.id.punchesGifresumeBtn);
        PunchesStart=view.findViewById(R.id.punchesGifstartBtn);
        PunchesGif=view.findViewById(R.id.punchesGif);
        PunchesReadyToGo=view.findViewById(R.id.PunchesStartPoint);
        PunchesFinish=view.findViewById(R.id.punchesGifEndpoint);

        circularViewWithTimerPunches=view.findViewById(R.id.punchescircular_view);
        //ad
        mAdView=new AdView(getActivity());
        mAdView.setAdUnitId("ca-app-pub-2342822035961981~6396888741");
        mAdView.setAdSize(AdSize.FULL_BANNER);
        AdView adView=view.findViewById(R.id.adView);
        adView.addView(mAdView);
        AdRequest adRequest= new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        PunchesImageViewForeword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager tricepsDips = getFragmentManager();
                FragmentTransaction ft = tricepsDips.beginTransaction();
                ft.replace(R.id.scrollView, new TricepsDipsFragment());
                ft.commit();
                circularViewWithTimerPunches.stopTimer();
            }

        });
        PunchesImageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager dynamicChest = getFragmentManager();
                FragmentTransaction ft = dynamicChest.beginTransaction();
                ft.replace(R.id.scrollView, new DyamanicChestFragment());
                ft.commit();
                circularViewWithTimerPunches.stopTimer();
            }
        });
        PunchesStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s= PunchesReadyToGo.getText().toString();
                int speech=textToSpeechPunches.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                circularViewWithTimerPunches.startTimer();
            }
        });
        PunchesResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerPunches.resumeTimer();
            }
        });
        PunchesPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerPunches.pauseTimer();
            }
        });
        textToSpeechPunches=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (SUCCESS == status){
                    int lang=textToSpeechPunches.setLanguage(Locale.ENGLISH);
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
                                String e=PunchesFinish.getText().toString();
                                int speech=textToSpeechPunches.speak(e,TextToSpeech.QUEUE_FLUSH,null);
                                FragmentManager td = getFragmentManager();
                                FragmentTransaction ft = td.beginTransaction();
                                ft.replace(R.id.scrollView, new TricepsDipsFragment());
                                ft.commit();
                            }

                            @Override
                            public void onTimerCancelled() {
                                Toast.makeText(getContext(),"Timer Cancelled",Toast.LENGTH_SHORT).show();
                            }
                        });
        circularViewWithTimerPunches.setOptions(builderWithTimer);
        return view;
    }

}