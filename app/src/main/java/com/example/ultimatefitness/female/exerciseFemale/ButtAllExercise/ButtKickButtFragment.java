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

public class ButtKickButtFragment extends Fragment {
    ImageView nextBK,backBK,startBK,resumeBK,pauseBK;
    GifImageView gifBK;
    TextView readyBK,endBK;
    TextToSpeech textToSpeechBK;
    CircularView circularViewWithTimerBK;
    private AdView mAdView;
    public ButtKickButtFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_butt_kick_butt, container, false);
        nextBK=view.findViewById(R.id. BKForwordBtn);
        gifBK=view.findViewById(R.id.BKGif);
        readyBK=view.findViewById(R.id.BKStartPoint);
        backBK=view.findViewById(R.id.backBK);
        endBK=view.findViewById(R.id.BKEndpoint);
        startBK=view.findViewById(R.id.BKstartBtn);
        resumeBK=view.findViewById(R.id.BKresumeBtn);
        pauseBK=view.findViewById(R.id.BKpauseBtn);

        circularViewWithTimerBK=view.findViewById(R.id.BKcircular_view);
        //ad
        mAdView=new AdView(getActivity());
        mAdView.setAdUnitId("ca-app-pub-2342822035961981~6396888741");
        mAdView.setAdSize(AdSize.FULL_BANNER);
        AdView adView=view.findViewById(R.id.adView);
        adView.addView(mAdView);
        AdRequest adRequest= new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        nextBK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager sqt = getFragmentManager();
                FragmentTransaction ft = sqt.beginTransaction();
                ft.replace(R.id.frameButt, new SquatButtFragment());
                ft.commit();
                circularViewWithTimerBK.stopTimer();
            }
        });
        backBK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager hs=getFragmentManager();
                FragmentTransaction transaction=hs.beginTransaction();
                transaction.replace(R.id.frameButt,new HighSteppingButtFragment()).commit();
            }
        });
        startBK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s= readyBK.getText().toString();
                int speech=textToSpeechBK.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                circularViewWithTimerBK.startTimer();
            }
        });
        resumeBK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerBK.resumeTimer();
            }
        });
        pauseBK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerBK.pauseTimer();
            }
        });
        textToSpeechBK=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (SUCCESS == status){
                    int lang=textToSpeechBK.setLanguage(Locale.ENGLISH);
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
                                String e=endBK.getText().toString();
                                int speech=textToSpeechBK.speak(e,TextToSpeech.QUEUE_FLUSH,null);
                                FragmentManager sqt = getFragmentManager();
                                FragmentTransaction ft = sqt.beginTransaction();
                                ft.replace(R.id.frameButt, new SquatButtFragment());
                                ft.commit();
                            }

                            @Override
                            public void onTimerCancelled() {
                                Toast.makeText(getContext(),"Timer Cancelled",Toast.LENGTH_SHORT).show();
                            }
                        });
        circularViewWithTimerBK.setOptions(builderWithTimer);
        return view;
    }
}