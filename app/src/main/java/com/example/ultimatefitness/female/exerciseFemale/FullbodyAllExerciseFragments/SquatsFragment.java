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

public class SquatsFragment extends Fragment {
    GifImageView SGif;
    TextView SReady,SFinish;
    ImageView SStart,SResume,SPause,SNext,SBack;
    CircularView circularViewS;
    TextToSpeech textToSpeechS;
    private AdView mAdView;
    public SquatsFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_squats, container, false);
        SGif=view.findViewById(R.id.SFGif);
        SReady=view.findViewById(R.id.SFStartPoint);
        SFinish=view.findViewById(R.id.SFEndpoint);
        SStart=view.findViewById(R.id.SFstartBtn);
        SResume=view.findViewById(R.id.SFresumeBtn);
        SPause=view.findViewById(R.id.SFpauseBtn);
        SNext=view.findViewById(R.id.SFForwordBtn);
        SBack=view.findViewById(R.id.SFBackbtn);
        circularViewS=view.findViewById(R.id.SFcircular_view);
        //ad
        mAdView=new AdView(getActivity());
        mAdView.setAdUnitId("ca-app-pub-2342822035961981~6396888741");
        mAdView.setAdSize(AdSize.FULL_BANNER);
        AdView adView=view.findViewById(R.id.adView);
        adView.addView(mAdView);
        AdRequest adRequest= new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        SNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager wallPushUp=getFragmentManager();
                FragmentTransaction t=wallPushUp.beginTransaction();
                t.replace(R.id.FBGFrameChange,new WallPushUpFragment());
                t.commit();
                circularViewS.stopTimer();
            }
        });
        SBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager sideHop=getFragmentManager();
                FragmentTransaction fragmentTransaction=sideHop.beginTransaction();
                fragmentTransaction.replace(R.id.FBGFrameChange,new SideHopFragment());
                fragmentTransaction.commit();
                circularViewS.stopTimer();
            }
        });
        textToSpeechS=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (SUCCESS==status){
                    int lang=textToSpeechS.setLanguage(Locale.ENGLISH);
                }
            }
        });
        SStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s= SReady.getText().toString();
                int speech=textToSpeechS.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                circularViewS.startTimer();
            }
        });
        SResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewS.resumeTimer();
            }
        });
        SPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewS.pauseTimer();
            }
        });
        CircularView.OptionsBuilder builderWithTimer=
                new CircularView.OptionsBuilder()
                        .shouldDisplayText(true)
                        .setCounterInSeconds(30)
                        .setCircularViewCallback(new CircularViewCallback(){
                            @Override
                            public void onTimerFinish() {
                                String e= SFinish.getText().toString();
                                int speech=textToSpeechS.speak(e,TextToSpeech.QUEUE_FLUSH,null);
                                FragmentManager wallPushUp = getFragmentManager();
                                FragmentTransaction ft = wallPushUp.beginTransaction()
                                        .replace(R.id.FBGFrameChange, new WallPushUpFragment());
                                ft.commit();}

                            @Override
                            public void onTimerCancelled() {
                                Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();

                            }
                        });
        circularViewS.setOptions(builderWithTimer);
        return view;
    }

}