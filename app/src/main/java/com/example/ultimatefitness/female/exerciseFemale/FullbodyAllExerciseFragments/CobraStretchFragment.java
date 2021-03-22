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

public class CobraStretchFragment extends Fragment {
    GifImageView Gifcs;
    TextView Readycs,Finishcs;
    ImageView Startcs,Resumecs,Pausecs,Nextcs,Backcs;
    CircularView circularViewcs;
    TextToSpeech textToSpeechcs;
    private AdView mAdView;
    public CobraStretchFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_cobra_stretch, container, false);
        Gifcs=view.findViewById(R.id.Gifcs);
        Readycs=view.findViewById(R.id.StartPointcs);
        Finishcs=view.findViewById(R.id.Endpointcs);
        Startcs=view.findViewById(R.id.startBtncs);
        Resumecs=view.findViewById(R.id.resumeBtncs);
        Pausecs=view.findViewById(R.id.pauseBtncs);
        Nextcs=view.findViewById(R.id.ForwordBtncs);
        Backcs=view.findViewById(R.id.Backbtncs);
        circularViewcs=view.findViewById(R.id.circular_viewcs);
        //ad
        mAdView=new AdView(getActivity());
        mAdView.setAdUnitId("ca-app-pub-2342822035961981~6396888741");
        mAdView.setAdSize(AdSize.FULL_BANNER);
        AdView adView=view.findViewById(R.id.adView);
        adView.addView(mAdView);
        AdRequest adRequest= new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        Nextcs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager childpose=getFragmentManager();
                FragmentTransaction t=childpose.beginTransaction();
                t.replace(R.id.FBGFrameChange,new ChildPoseFragment());
                t.commit();
                circularViewcs.stopTimer();
            }
        });
        Backcs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager plank=getFragmentManager();
                FragmentTransaction fragmentTransaction=plank.beginTransaction();
                fragmentTransaction.replace(R.id.FBGFrameChange,new FullBodyPlankFragment());
                fragmentTransaction.commit();
                circularViewcs.stopTimer();
            }
        });
        textToSpeechcs=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (SUCCESS==status){
                    int lang=textToSpeechcs.setLanguage(Locale.ENGLISH);
                }
            }
        });
        Startcs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s= Readycs.getText().toString();
                int speech=textToSpeechcs.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                circularViewcs.startTimer();
            }
        });
        Resumecs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewcs.resumeTimer();
            }
        });
        Pausecs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewcs.pauseTimer();
            }
        });
        CircularView.OptionsBuilder builderWithTimer=
                new CircularView.OptionsBuilder()
                        .shouldDisplayText(true)
                        .setCounterInSeconds(30)
                        .setCircularViewCallback(new CircularViewCallback(){
                            @Override
                            public void onTimerFinish() {
                                String e= Finishcs.getText().toString();
                                int speech=textToSpeechcs.speak(e,TextToSpeech.QUEUE_FLUSH,null);
                                FragmentManager childpose = getFragmentManager();
                                FragmentTransaction ft =childpose.beginTransaction();
                                ft.replace(R.id.FBGFrameChange, new ChildPoseFragment());
                                ft.commit();}

                            @Override
                            public void onTimerCancelled() {
                                Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();

                            }
                        });
        circularViewcs.setOptions(builderWithTimer);
        return view;
    }

}