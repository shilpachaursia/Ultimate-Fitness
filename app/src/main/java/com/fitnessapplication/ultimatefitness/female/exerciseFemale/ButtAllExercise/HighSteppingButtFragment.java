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

public class HighSteppingButtFragment extends Fragment {
    ImageView naexHSB,startHSB,resumeHSB,pauseHSB;
    GifImageView gifHSB;
    TextView readyHSB,endHSB;
    TextToSpeech textToSpeechHSB;
    CircularView circularViewWithTimerHSB;
    private AdView mAdView;
    public HighSteppingButtFragment() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_high_stepping_butt, container, false);
        naexHSB=view.findViewById(R.id. HSBForwordBtn);
        gifHSB=view.findViewById(R.id.HSBGif);
        readyHSB=view.findViewById(R.id.HSBStartPoint);
        endHSB=view.findViewById(R.id.HSBEndpoint);
        startHSB=view.findViewById(R.id.HSBstartBtn);
        resumeHSB=view.findViewById(R.id.HSBresumeBtn);
        pauseHSB=view.findViewById(R.id.HSBpauseBtn);

        circularViewWithTimerHSB=view.findViewById(R.id.HSBcircular_view);
        //ad
        mAdView=new AdView(getActivity());
        mAdView.setAdUnitId("ca-app-pub-2342822035961981~6396888741");
        mAdView.setAdSize(AdSize.FULL_BANNER);
        AdView adView=view.findViewById(R.id.adView);
        adView.addView(mAdView);
        AdRequest adRequest= new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        naexHSB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager buttkick = getFragmentManager();
                FragmentTransaction ft = buttkick.beginTransaction();
                ft.replace(R.id.frameButt, new ButtKickButtFragment());
                ft.commit();
                circularViewWithTimerHSB.stopTimer();
            }
        });
        startHSB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s= readyHSB.getText().toString();
                int speech=textToSpeechHSB.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                circularViewWithTimerHSB.startTimer();
            }
        });
        resumeHSB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerHSB.resumeTimer();
            }
        });
        pauseHSB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerHSB.pauseTimer();
            }
        });
        textToSpeechHSB=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (SUCCESS == status){
                    int lang=textToSpeechHSB.setLanguage(Locale.ENGLISH);
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
                                String e=endHSB.getText().toString();
                                int speech=textToSpeechHSB.speak(e,TextToSpeech.QUEUE_FLUSH,null);
                                FragmentManager buttkick = getFragmentManager();
                                FragmentTransaction ft = buttkick.beginTransaction();
                                ft.replace(R.id.frameButt, new ButtKickButtFragment());
                                ft.commit();
                            }

                            @Override
                            public void onTimerCancelled() {
                                Toast.makeText(getContext(),"Timer Cancelled",Toast.LENGTH_SHORT).show();
                            }
                        });
        circularViewWithTimerHSB.setOptions(builderWithTimer);
        return view;
    }


}