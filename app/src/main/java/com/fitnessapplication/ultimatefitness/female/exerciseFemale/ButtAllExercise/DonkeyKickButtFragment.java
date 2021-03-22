package com.fitnessapplication.ultimatefitness.female.exerciseFemale.ButtAllExercise;

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

public class DonkeyKickButtFragment extends Fragment {
    ImageView backDKB,startDKB,resumeDKB,pauseDKB;
    GifImageView gifDKB;
    TextView readyDKB,endDKB;
    TextToSpeech textToSpeechDKB;
    CircularView circularViewWithTimerDKB;
    private AdView mAdView;
    public DonkeyKickButtFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_donkey_kick_butt, container, false);
        gifDKB=view.findViewById(R.id.DKBGif);
        readyDKB=view.findViewById(R.id.DKBStartPoint);
        backDKB=view.findViewById(R.id.DKBback);
        endDKB=view.findViewById(R.id.DKBEndpoint);
        startDKB=view.findViewById(R.id.DKBstartBtn);
        resumeDKB=view.findViewById(R.id.DKBresumeBtn);
        pauseDKB=view.findViewById(R.id.DKBpauseBtn);

        circularViewWithTimerDKB=view.findViewById(R.id.DKBcircular_view);
        //ad
        mAdView=new AdView(getActivity());
        mAdView.setAdUnitId("ca-app-pub-2342822035961981~6396888741");
        mAdView.setAdSize(AdSize.FULL_BANNER);
        AdView adView=view.findViewById(R.id.adView);
        adView.addView(mAdView);
        AdRequest adRequest= new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        backDKB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager mc=getFragmentManager();
                FragmentTransaction transaction=mc.beginTransaction();
                transaction.replace(R.id.frameButt,new MountainClimberButtFragment()).commit();
            }
        });
        startDKB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s= readyDKB.getText().toString();
                int speech=textToSpeechDKB.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                circularViewWithTimerDKB.startTimer();
            }
        });
        resumeDKB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerDKB.resumeTimer();
            }
        });
        pauseDKB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerDKB.pauseTimer();
            }
        });
        textToSpeechDKB=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (SUCCESS == status){
                    int lang=textToSpeechDKB.setLanguage(Locale.ENGLISH);
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
                                String e=endDKB.getText().toString();
                                int speech=textToSpeechDKB.speak(e,TextToSpeech.QUEUE_FLUSH,null);
                                }

                            @Override
                            public void onTimerCancelled() {
                                Toast.makeText(getContext(),"Timer Cancelled",Toast.LENGTH_SHORT).show();
                            }
                        });
        circularViewWithTimerDKB.setOptions(builderWithTimer);
        return view;
    }
}
