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

public class UpDownPlankFragment extends Fragment {
    ImageView UDPImageViewForeword,UDPImageViewBack,UDPStart,UDPResume,UDPPause;
    GifImageView UDPGif;
    TextView UDPReadyToGo,UDPFinish;
    TextToSpeech textToSpeechUDP;
    CircularView circularViewWithTimerUDP;
    private AdView mAdView;
    public UpDownPlankFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_up_down_plank, container, false);
        UDPImageViewForeword=view.findViewById(R.id.UDPForwordBtn);
        UDPImageViewBack=view.findViewById(R.id.UDPBackbtn);
        UDPPause=view.findViewById(R.id.UDPpauseBtn);
        UDPResume=view.findViewById(R.id.UDPresumeBtn);
        UDPStart=view.findViewById(R.id.UDPstartBtn);
        UDPGif=view.findViewById(R.id.UDPGif);
        UDPReadyToGo=view.findViewById(R.id.UDPStartPoint);
        UDPFinish=view.findViewById(R.id.UDPEndpoint);

        circularViewWithTimerUDP=view.findViewById(R.id.UDPcircular_view);
        //ad
        mAdView=new AdView(getActivity());
        mAdView.setAdUnitId("ca-app-pub-2342822035961981~6396888741");
        mAdView.setAdSize(AdSize.FULL_BANNER);
        AdView adView=view.findViewById(R.id.adView);
        adView.addView(mAdView);
        AdRequest adRequest= new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        UDPImageViewForeword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager armsScissor = getFragmentManager();
                FragmentTransaction ft = armsScissor.beginTransaction();
                ft.replace(R.id.scrollView, new ArmScissorsFragment());
                ft.commit();
                circularViewWithTimerUDP.stopTimer();
            }

        });
        UDPImageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager alternativeHooks = getFragmentManager();
                FragmentTransaction ft = alternativeHooks.beginTransaction();
                ft.replace(R.id.scrollView, new AlternativeHooksFragment());
                ft.commit();
                circularViewWithTimerUDP.stopTimer();
            }
        });
        UDPStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s= UDPReadyToGo.getText().toString();
                int speech=textToSpeechUDP.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                circularViewWithTimerUDP.startTimer();
            }
        });
        UDPResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerUDP.resumeTimer();
            }
        });
        UDPPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerUDP.pauseTimer();
            }
        });
        textToSpeechUDP=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (SUCCESS == status){
                    int lang=textToSpeechUDP.setLanguage(Locale.ENGLISH);
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
                                String e= UDPFinish.getText().toString();
                                int speech=textToSpeechUDP.speak(e,TextToSpeech.QUEUE_FLUSH,null);
                                FragmentManager armsScissor = getFragmentManager();
                                FragmentTransaction ft = armsScissor.beginTransaction();
                                ft.replace(R.id.scrollView, new ArmScissorsFragment());
                                ft.commit();
                            }

                            @Override
                            public void onTimerCancelled() {
                                Toast.makeText(getContext(),"Timer Cancelled",Toast.LENGTH_SHORT).show();
                            }
                        });
        circularViewWithTimerUDP.setOptions(builderWithTimer);
        return view;
    }

}