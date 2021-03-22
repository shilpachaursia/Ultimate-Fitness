package com.fitnessapplication.ultimatefitness.female.exerciseFemale.ArmsAllExercise;

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

public class TricpesStretchLeftFragment extends Fragment {
    ImageView TSLImageViewForeword,TSLImageViewBack,TSLStart,TSLResume,TSLPause;
    GifImageView TSLGif;
    TextView TSLReadyToGo,TSLFinish;
    TextToSpeech textToSpeechTSL;
    CircularView circularViewWithTimerTSL;
    private AdView mAdView;
    public TricpesStretchLeftFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_tricpes_stretch_left, container, false);
        TSLImageViewForeword=view.findViewById(R.id.TSLForwordBtn);
        TSLImageViewBack=view.findViewById(R.id.TSLBackbtn);
        TSLPause=view.findViewById(R.id.TSLpauseBtn);
        TSLResume=view.findViewById(R.id.TSLresumeBtn);
        TSLStart=view.findViewById(R.id.TSLstartBtn);
        TSLGif=view.findViewById(R.id.TSLGif);
        TSLReadyToGo=view.findViewById(R.id.TSLStartPoint);
        TSLFinish=view.findViewById(R.id.TSLEndpoint);

        circularViewWithTimerTSL=view.findViewById(R.id.TSLcircular_view);
        //ad
        mAdView=new AdView(getActivity());
        mAdView.setAdUnitId("ca-app-pub-2342822035961981~6396888741");
        mAdView.setAdSize(AdSize.FULL_BANNER);
        AdView adView=view.findViewById(R.id.adView);
        adView.addView(mAdView);
        AdRequest adRequest= new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        TSLImageViewForeword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager tricepsStretchright = getFragmentManager();
                FragmentTransaction ft = tricepsStretchright.beginTransaction();
                ft.replace(R.id.scrollView, new TricpesStretchRightFragment());
                ft.commit();
                circularViewWithTimerTSL.stopTimer();
            }

        });
        TSLImageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager bigarmscircle = getFragmentManager();
                FragmentTransaction ft = bigarmscircle.beginTransaction();
                ft.replace(R.id.scrollView, new BigArmCircleFragment());
                ft.commit();
                circularViewWithTimerTSL.stopTimer();
            }
        });
        TSLStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s= TSLReadyToGo.getText().toString();
                int speech=textToSpeechTSL.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                circularViewWithTimerTSL.startTimer();
            }
        });
        TSLResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerTSL.resumeTimer();
            }
        });
        TSLPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerTSL.pauseTimer();
            }
        });
        textToSpeechTSL=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (SUCCESS == status){
                    int lang=textToSpeechTSL.setLanguage(Locale.ENGLISH);
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
                                String e= TSLFinish.getText().toString();
                                int speech=textToSpeechTSL.speak(e,TextToSpeech.QUEUE_FLUSH,null);
                                FragmentManager tricepsstretchright = getFragmentManager();
                                FragmentTransaction ft = tricepsstretchright.beginTransaction();
                                ft.replace(R.id.scrollView, new TricpesStretchRightFragment());
                                ft.commit();
                            }

                            @Override
                            public void onTimerCancelled() {
                                Toast.makeText(getContext(),"Timer Cancelled",Toast.LENGTH_SHORT).show();
                            }
                        });
        circularViewWithTimerTSL.setOptions(builderWithTimer);
        return view;
    }

}