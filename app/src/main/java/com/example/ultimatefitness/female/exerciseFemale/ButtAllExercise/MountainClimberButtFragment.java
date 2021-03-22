package com.example.ultimatefitness.female.exerciseFemale.ButtAllExercise;

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

public class MountainClimberButtFragment extends Fragment {
    ImageView nextMCB,backMCB,startMCB,resumeMCB,pauseMCB;
    GifImageView gifMCB;
    TextView readyMCB,endMCB;
    TextToSpeech textToSpeechMCB;
    CircularView circularViewWithTimerMCB;
    private AdView mAdView;
    public MountainClimberButtFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_mountain_climber_butt, container, false);
        nextMCB=view.findViewById(R.id.MCBForwordBtn);
        gifMCB=view.findViewById(R.id.MCBGif);
        readyMCB=view.findViewById(R.id.MCBStartPoint);
        backMCB=view.findViewById(R.id.MCBback);
        endMCB=view.findViewById(R.id.MCBEndpoint);
        startMCB=view.findViewById(R.id.MCBstartBtn);
        resumeMCB=view.findViewById(R.id.MCBresumeBtn);
        pauseMCB=view.findViewById(R.id.MCBpauseBtn);

        circularViewWithTimerMCB=view.findViewById(R.id.MCBcircular_view);
        //ad
        mAdView=new AdView(getActivity());
        mAdView.setAdUnitId("ca-app-pub-2342822035961981~6396888741");
        mAdView.setAdSize(AdSize.FULL_BANNER);
        AdView adView=view.findViewById(R.id.adView);
        adView.addView(mAdView);
        AdRequest adRequest= new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        nextMCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager DK = getFragmentManager();
                FragmentTransaction ft = DK.beginTransaction();
                ft.replace(R.id.frameButt, new DonkeyKickButtFragment());
                ft.commit();
                circularViewWithTimerMCB.stopTimer();
            }
        });
        backMCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager BB=getFragmentManager();
                FragmentTransaction transaction=BB.beginTransaction();
                transaction.replace(R.id.frameButt,new ButtBridgeButtFragment()).commit();
            }
        });
        startMCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s= readyMCB.getText().toString();
                int speech=textToSpeechMCB.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                circularViewWithTimerMCB.startTimer();
            }
        });
        resumeMCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerMCB.resumeTimer();
            }
        });
        pauseMCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerMCB.pauseTimer();
            }
        });
        textToSpeechMCB=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (SUCCESS == status){
                    int lang=textToSpeechMCB.setLanguage(Locale.ENGLISH);
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
                                String e=endMCB.getText().toString();
                                int speech=textToSpeechMCB.speak(e,TextToSpeech.QUEUE_FLUSH,null);
                                FragmentManager DK = getFragmentManager();
                                FragmentTransaction ft = DK.beginTransaction();
                                ft.replace(R.id.frameButt, new DonkeyKickButtFragment());
                                ft.commit();
                            }

                            @Override
                            public void onTimerCancelled() {
                                Toast.makeText(getContext(),"Timer Cancelled",Toast.LENGTH_SHORT).show();
                            }
                        });
        circularViewWithTimerMCB.setOptions(builderWithTimer);
        return view;
    }
}
