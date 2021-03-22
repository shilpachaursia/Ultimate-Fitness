package com.example.ultimatefitness.female.exerciseFemale.FullbodyAllExerciseFragments;

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


public class WallPushUpFragment extends Fragment {
    GifImageView WPGif;
    TextView WPReady,WPFinish;
    ImageView WPStart,WPResume,WPPause,WPNext,WPBack;
    CircularView circularViewWP;
    TextToSpeech textToSpeechWP;
    private AdView mAdView;
    public WallPushUpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_wall_push_up, container, false);
        WPGif=view.findViewById(R.id.WPGif);
        WPReady=view.findViewById(R.id.WPStartPoint);
        WPFinish=view.findViewById(R.id.WPEndpoint);
        WPStart=view.findViewById(R.id.WPstartBtn);
        WPResume=view.findViewById(R.id.WPresumeBtn);
        WPPause=view.findViewById(R.id.WPpauseBtn);
        WPNext=view.findViewById(R.id.WPForwordBtn);
        WPBack=view.findViewById(R.id.WPBackbtn);
        circularViewWP=view.findViewById(R.id.WPcircular_view);
        //ad
        mAdView=new AdView(getActivity());
        mAdView.setAdUnitId("ca-app-pub-2342822035961981~6396888741");
        mAdView.setAdSize(AdSize.FULL_BANNER);
        AdView adView=view.findViewById(R.id.adView);
        adView.addView(mAdView);
        AdRequest adRequest= new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        WPNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager buttbridge=getFragmentManager();
                FragmentTransaction t=buttbridge.beginTransaction();
                t.replace(R.id.FBGFrameChange,new FullBodyButtBrigeFragment());
                t.commit();
                circularViewWP.stopTimer();
            }
        });
        WPBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager squat=getFragmentManager();
                FragmentTransaction fragmentTransaction=squat.beginTransaction();
                fragmentTransaction.replace(R.id.FBGFrameChange,new SquatsFragment());
                fragmentTransaction.commit();
                circularViewWP.stopTimer();
            }
        });
        textToSpeechWP=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (SUCCESS==status){
                    int lang=textToSpeechWP.setLanguage(Locale.ENGLISH);
                }
            }
        });
        WPStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s= WPReady.getText().toString();
                int speech=textToSpeechWP.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                circularViewWP.startTimer();
            }
        });
        WPResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWP.resumeTimer();
            }
        });
        WPPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWP.pauseTimer();
            }
        });
        CircularView.OptionsBuilder builderWithTimer=
                new CircularView.OptionsBuilder()
                        .shouldDisplayText(true)
                        .setCounterInSeconds(30)
                        .setCircularViewCallback(new CircularViewCallback(){
                            @Override
                            public void onTimerFinish() {
                                String e= WPFinish.getText().toString();
                                int speech=textToSpeechWP.speak(e,TextToSpeech.QUEUE_FLUSH,null);
                                FragmentManager buttbridge = getFragmentManager();
                                FragmentTransaction ft = buttbridge.beginTransaction()
                                      .replace(R.id.FBGFrameChange, new FullBodyButtBrigeFragment());
                                ft.commit();}

                            @Override
                            public void onTimerCancelled() {
                                Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();

                            }
                        });
        circularViewWP.setOptions(builderWithTimer);
        return view;
    }


}