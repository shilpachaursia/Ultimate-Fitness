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

public class KneeTOChestStretchFragment extends Fragment {
    GifImageView Gifkcs;
    TextView Readykcs,Finishkcs;
    ImageView Startkcs,Resumekcs,Pausekcs,Backkcs;
    CircularView circularViewkcs;
    TextToSpeech textToSpeechkcs;
    private AdView mAdView;
    public KneeTOChestStretchFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_knee_t_o_chest_stretch, container, false);
        Gifkcs=view.findViewById(R.id.Gifkcs);
        Readykcs=view.findViewById(R.id.StartPointkcs);
        Finishkcs=view.findViewById(R.id.Endpointkcs);
        Startkcs=view.findViewById(R.id.startBtnkcs);
        Resumekcs=view.findViewById(R.id.resumeBtnkcs);
        Pausekcs=view.findViewById(R.id.pauseBtnkcs);

        Backkcs=view.findViewById(R.id.Backbtkcs);
        circularViewkcs=view.findViewById(R.id.circular_viewkcs);
        //ad
        mAdView=new AdView(getActivity());
        mAdView.setAdUnitId("ca-app-pub-2342822035961981~6396888741");
        mAdView.setAdSize(AdSize.FULL_BANNER);
        AdView adView=view.findViewById(R.id.adView);
        adView.addView(mAdView);
        AdRequest adRequest= new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        Backkcs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager chilpose=getFragmentManager();
                FragmentTransaction fragmentTransaction=chilpose.beginTransaction();
                fragmentTransaction.replace(R.id.FBGFrameChange,new ChildPoseFragment());
                fragmentTransaction.commit();
                circularViewkcs.stopTimer();
            }
        });
        textToSpeechkcs=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (SUCCESS==status){
                    int lang=textToSpeechkcs.setLanguage(Locale.ENGLISH);
                }
            }
        });
        Startkcs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s= Readykcs.getText().toString();
                int speech=textToSpeechkcs.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                circularViewkcs.startTimer();
            }
        });
        Resumekcs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewkcs.resumeTimer();
            }
        });
        Pausekcs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewkcs.pauseTimer();
            }
        });
        CircularView.OptionsBuilder builderWithTimer=
                new CircularView.OptionsBuilder()
                        .shouldDisplayText(true)
                        .setCounterInSeconds(30)
                        .setCircularViewCallback(new CircularViewCallback(){
                            @Override
                            public void onTimerFinish() {
                                String e= Finishkcs.getText().toString();
                                int speech=textToSpeechkcs.speak(e,TextToSpeech.QUEUE_FLUSH,null);
                            }

                            @Override
                            public void onTimerCancelled() {
                                Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();

                            }
                        });
        circularViewkcs.setOptions(builderWithTimer);
        return view;
    }

}