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

public class FullBodyButtBrigeFragment extends Fragment {
    GifImageView BBGif;
    TextView BBReady,BBFinish;
    ImageView BBStart,BBResume,BBPause,BBNext,BBBack;
    CircularView circularViewBB;
    TextToSpeech textToSpeechBB;
    private AdView mAdView;
    public FullBodyButtBrigeFragment() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_full_body_butt_brige, container, false);
        BBGif=view.findViewById(R.id.BBGif);
        BBReady=view.findViewById(R.id.BBStartPoint);
        BBFinish=view.findViewById(R.id.BBEndpoint);
        BBStart=view.findViewById(R.id.BBstartBtn);
        BBResume=view.findViewById(R.id.BBresumeBtn);
        BBPause=view.findViewById(R.id.BBpauseBtn);
        BBNext=view.findViewById(R.id.BBForwordBtn);
        BBBack=view.findViewById(R.id.BBBackbtn);
        circularViewBB=view.findViewById(R.id.BBcircular_view);
        //ad
        mAdView=new AdView(getActivity());
        mAdView.setAdUnitId("ca-app-pub-2342822035961981~6396888741");
        mAdView.setAdSize(AdSize.FULL_BANNER);
        AdView adView=view.findViewById(R.id.adView);
        adView.addView(mAdView);
        AdRequest adRequest= new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        BBNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fireHydrantLeft=getFragmentManager();
                FragmentTransaction t=fireHydrantLeft.beginTransaction();
                t.replace(R.id.FBGFrameChange,new FireHydrantLeftFragment());
                t.commit();
                circularViewBB.stopTimer();
            }
        });
        BBBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager wallpushup=getFragmentManager();
                FragmentTransaction fragmentTransaction=wallpushup.beginTransaction();
                fragmentTransaction.replace(R.id.FBGFrameChange,new WallPushUpFragment());
                fragmentTransaction.commit();
                circularViewBB.stopTimer();
            }
        });
        textToSpeechBB=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (SUCCESS==status){
                    int lang=textToSpeechBB.setLanguage(Locale.ENGLISH);
                }
            }
        });
        BBStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s= BBReady.getText().toString();
                int speech=textToSpeechBB.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                circularViewBB.startTimer();
            }
        });
        BBResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewBB.resumeTimer();
            }
        });
        BBPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewBB.pauseTimer();
            }
        });
        CircularView.OptionsBuilder builderWithTimer=
                new CircularView.OptionsBuilder()
                        .shouldDisplayText(true)
                        .setCounterInSeconds(30)
                        .setCircularViewCallback(new CircularViewCallback(){
                            @Override
                            public void onTimerFinish() {
                                String e= BBFinish.getText().toString();
                                int speech=textToSpeechBB.speak(e,TextToSpeech.QUEUE_FLUSH,null);
                                FragmentManager fireHydrantLest = getFragmentManager();
                                FragmentTransaction ft = fireHydrantLest.beginTransaction();
                                ft.replace(R.id.FBGFrameChange, new FireHydrantLeftFragment());
                                ft.commit();}

                            @Override
                            public void onTimerCancelled() {
                                Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();

                            }
                        });
        circularViewBB.setOptions(builderWithTimer);
        return view;
    }

}