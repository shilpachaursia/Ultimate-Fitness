package com.example.ultimatefitness.female.exerciseFemale.absFemaleAllExercise;

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


public class LyingTwistStretchAbsFragment extends Fragment {
    ImageView backBBA,startBBA,resumeBBA,pauseBBA;
    GifImageView gifBBA;
    TextView readyBBA,endBBA;
    TextToSpeech textToSpeechBBA;
    CircularView circularViewWithTimerBBA;
    private AdView mAdView;
    public LyingTwistStretchAbsFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lying_twist_stretch_abs, container, false);
        gifBBA=view.findViewById(R.id.CJJAGif);
        readyBBA=view.findViewById(R.id.CJJAStartPoint);
        backBBA=view.findViewById(R.id.CJJAback);
        endBBA=view.findViewById(R.id.CJJAEndpoint);
        startBBA=view.findViewById(R.id.CJJAstartBtn);
        resumeBBA=view.findViewById(R.id.CJJAresumeBtn);
        pauseBBA=view.findViewById(R.id.CJJApauseBtn);

        circularViewWithTimerBBA=view.findViewById(R.id.CJJAcircular_view);
        //ad
        mAdView=new AdView(getActivity());
        mAdView.setAdUnitId("ca-app-pub-2342822035961981~6396888741");
        mAdView.setAdSize(AdSize.FULL_BANNER);
        AdView adView=view.findViewById(R.id.adView);
        adView.addView(mAdView);
        AdRequest adRequest= new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        backBBA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager hs=getFragmentManager();
                FragmentTransaction transaction=hs.beginTransaction();
                transaction.replace(R.id.absFrameLayout,new CobarStretchAbsFragment()).commit();
                circularViewWithTimerBBA.stopTimer();
            }
        });
        startBBA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s= readyBBA.getText().toString();
                int speech=textToSpeechBBA.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                circularViewWithTimerBBA.startTimer();
            }
        });
        resumeBBA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerBBA.resumeTimer();
            }
        });
        pauseBBA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerBBA.pauseTimer();
            }
        });
        textToSpeechBBA=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (SUCCESS == status){
                    int lang=textToSpeechBBA.setLanguage(Locale.ENGLISH);
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
                                String e=endBBA.getText().toString();
                                int speech=textToSpeechBBA.speak(e,TextToSpeech.QUEUE_FLUSH,null);
                            }

                            @Override
                            public void onTimerCancelled() {
                                Toast.makeText(getContext(),"Timer Cancelled",Toast.LENGTH_SHORT).show();
                            }
                        });
        circularViewWithTimerBBA.setOptions(builderWithTimer);
        return view;
    }
}