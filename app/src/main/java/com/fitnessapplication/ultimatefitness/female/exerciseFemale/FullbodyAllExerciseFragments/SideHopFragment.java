package com.fitnessapplication.ultimatefitness.female.exerciseFemale.FullbodyAllExerciseFragments;

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


public class SideHopFragment extends Fragment {
    ImageView SHImageViewForeword,SHImageViewBack,SHStart,SHResume,SHPause;
    GifImageView SHGif;
    TextView SHReadyToGo,SHFinish;
    TextToSpeech textToSpeechSH;
    CircularView circularViewWithTimerSH;
    private AdView mAdView;
    public SideHopFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_side_hop, container, false);
        SHImageViewForeword=view.findViewById(R.id.SHForwordBtn);
        SHImageViewBack=view.findViewById(R.id.SHBackbtn);
        SHPause=view.findViewById(R.id.SHpauseBtn);
        SHResume=view.findViewById(R.id.SHresumeBtn);
        SHStart=view.findViewById(R.id.SHstartBtn);
        SHGif=view.findViewById(R.id.SHGif);
        SHReadyToGo=view.findViewById(R.id.SHStartPoint);
        SHFinish=view.findViewById(R.id.SHEndpoint);

        circularViewWithTimerSH=view.findViewById(R.id.SHcircular_view);
        //ad
        mAdView=new AdView(getActivity());
        mAdView.setAdUnitId("ca-app-pub-2342822035961981~6396888741");
        mAdView.setAdSize(AdSize.FULL_BANNER);
        AdView adView=view.findViewById(R.id.adView);
        adView.addView(mAdView);
        AdRequest adRequest= new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        SHImageViewForeword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager squat=getFragmentManager();
                FragmentTransaction transaction=squat.beginTransaction()
                        .replace(R.id.FBGFrameChange, new SquatsFragment());
                transaction.commit();
                circularViewWithTimerSH.stopTimer();

            }

        });
        SHImageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager highStepping = getFragmentManager();
                FragmentTransaction ft = highStepping.beginTransaction();
                ft.replace(R.id.FBGFrameChange, new HighSteppingFragment());
                ft.commit();
                circularViewWithTimerSH.stopTimer();
            }
        });
        SHStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s= SHReadyToGo.getText().toString();
                int speech=textToSpeechSH.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                circularViewWithTimerSH.startTimer();
            }
        });
        SHResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerSH.resumeTimer();
            }
        });
        SHPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerSH.pauseTimer();
            }
        });
        textToSpeechSH=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (SUCCESS == status){
                    int lang=textToSpeechSH.setLanguage(Locale.ENGLISH);
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
                                String e=SHFinish.getText().toString();
                                int speech=textToSpeechSH.speak(e,TextToSpeech.QUEUE_FLUSH,null);
                                FragmentManager squats = getFragmentManager();
                                FragmentTransaction ft = squats.beginTransaction();
                                ft.replace(R.id.FBGFrameChange, new SquatsFragment());
                                ft.commit();
                            }

                            @Override
                            public void onTimerCancelled() {
                                Toast.makeText(getContext(),"Timer Cancelled",Toast.LENGTH_SHORT).show();
                            }
                        });
        circularViewWithTimerSH.setOptions(builderWithTimer);
        return view;
    }

}