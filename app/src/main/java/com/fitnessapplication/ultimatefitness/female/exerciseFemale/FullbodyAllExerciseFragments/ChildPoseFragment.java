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

public class ChildPoseFragment extends Fragment {
    GifImageView Gifcp;
    TextView Readycp,Finishcp;
    ImageView Startcp,Resumecp,Pausecp,Nextcp,Backcp;
    CircularView circularViewcp;
    TextToSpeech textToSpeechcp;
    private AdView mAdView;
    public ChildPoseFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_child_pose, container, false);
        Gifcp=view.findViewById(R.id.Gifcp);
        Readycp=view.findViewById(R.id.StartPointcp);
        Finishcp=view.findViewById(R.id.Endpointcp);
        Startcp=view.findViewById(R.id.startBtncp);
        Resumecp=view.findViewById(R.id.resumeBtncp);
        Pausecp=view.findViewById(R.id.pauseBtncp);
        Nextcp=view.findViewById(R.id.ForwordBtncp);
        Backcp=view.findViewById(R.id.Backbtncp);
        circularViewcp=view.findViewById(R.id.circular_viewcp);
        //ad
        mAdView=new AdView(getActivity());
        mAdView.setAdUnitId("ca-app-pub-2342822035961981~6396888741");
        mAdView.setAdSize(AdSize.FULL_BANNER);
        AdView adView=view.findViewById(R.id.adView);
        adView.addView(mAdView);
        AdRequest adRequest= new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        Nextcp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager KCL=getFragmentManager();
                FragmentTransaction t=KCL.beginTransaction();
                t.replace(R.id.FBGFrameChange,new KneeTOChestStretchFragment());
                t.commit();
                circularViewcp.stopTimer();
            }
        });
        Backcp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager cobra=getFragmentManager();
                FragmentTransaction fragmentTransaction=cobra.beginTransaction();
                fragmentTransaction.replace(R.id.FBGFrameChange,new CobraStretchFragment());
                fragmentTransaction.commit();
                circularViewcp.stopTimer();
            }
        });
        textToSpeechcp=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (SUCCESS==status){
                    int lang=textToSpeechcp.setLanguage(Locale.ENGLISH);
                }
            }
        });
        Startcp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s= Readycp.getText().toString();
                int speech=textToSpeechcp.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                circularViewcp.startTimer();
            }
        });
        Resumecp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewcp.resumeTimer();
            }
        });
        Pausecp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewcp.pauseTimer();
            }
        });
        CircularView.OptionsBuilder builderWithTimer=
                new CircularView.OptionsBuilder()
                        .shouldDisplayText(true)
                        .setCounterInSeconds(30)
                        .setCircularViewCallback(new CircularViewCallback(){
                            @Override
                            public void onTimerFinish() {
                                String e= Finishcp.getText().toString();
                                int speech=textToSpeechcp.speak(e,TextToSpeech.QUEUE_FLUSH,null);
                                FragmentManager KTCF = getFragmentManager();
                                FragmentTransaction ft =KTCF.beginTransaction();
                                ft.replace(R.id.FBGFrameChange, new KneeTOChestStretchFragment());
                                ft.commit();}

                            @Override
                            public void onTimerCancelled() {
                                Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();

                            }
                        });
        circularViewcp.setOptions(builderWithTimer);
        return view;
    }

}