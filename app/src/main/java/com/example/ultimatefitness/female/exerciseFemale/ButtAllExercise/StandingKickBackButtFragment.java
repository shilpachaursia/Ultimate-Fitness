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

public class StandingKickBackButtFragment extends Fragment {
    ImageView nextSKB,backSKB,startSKB,resumeSKB,pauseSKB;
    GifImageView gifSKB;
    TextView readySKB,endSKB;
    TextToSpeech textToSpeechSKB;
    CircularView circularViewWithTimerSKB;
    private AdView mAdView;
    public StandingKickBackButtFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_standing_kick_back_butt, container, false);
        nextSKB=view.findViewById(R.id. SKBForwordBtn);
        gifSKB=view.findViewById(R.id.SKBGif);
        readySKB=view.findViewById(R.id.SKBStartPoint);
        backSKB=view.findViewById(R.id.SKBback);
        endSKB=view.findViewById(R.id.SKBEndpoint);
        startSKB=view.findViewById(R.id.SKBstartBtn);
        resumeSKB=view.findViewById(R.id.SKBresumeBtn);
        pauseSKB=view.findViewById(R.id.SKBpauseBtn);

        circularViewWithTimerSKB=view.findViewById(R.id.SKBcircular_view);
        //ad
        mAdView=new AdView(getActivity());
        mAdView.setAdUnitId("ca-app-pub-2342822035961981~6396888741");
        mAdView.setAdSize(AdSize.FULL_BANNER);
        AdView adView=view.findViewById(R.id.adView);
        adView.addView(mAdView);
        AdRequest adRequest= new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        nextSKB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager BB = getFragmentManager();
                FragmentTransaction ft = BB.beginTransaction();
                ft.replace(R.id.frameButt, new ButtBridgeButtFragment());
                ft.commit();
                circularViewWithTimerSKB.stopTimer();
            }
        });
        backSKB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager S=getFragmentManager();
                FragmentTransaction transaction=S.beginTransaction();
                transaction.replace(R.id.frameButt,new SquatButtFragment()).commit();
            }
        });
        startSKB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s= readySKB.getText().toString();
                int speech=textToSpeechSKB.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                circularViewWithTimerSKB.startTimer();
            }
        });
        resumeSKB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerSKB.resumeTimer();
            }
        });
        pauseSKB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerSKB.pauseTimer();
            }
        });
        textToSpeechSKB=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (SUCCESS == status){
                    int lang=textToSpeechSKB.setLanguage(Locale.ENGLISH);
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
                                String e=endSKB.getText().toString();
                                int speech=textToSpeechSKB.speak(e,TextToSpeech.QUEUE_FLUSH,null);
                                FragmentManager BB = getFragmentManager();
                                FragmentTransaction ft = BB.beginTransaction();
                                ft.replace(R.id.frameButt, new ButtBridgeButtFragment());
                                ft.commit();
                            }

                            @Override
                            public void onTimerCancelled() {
                                Toast.makeText(getContext(),"Timer Cancelled",Toast.LENGTH_SHORT).show();
                            }
                        });
        circularViewWithTimerSKB.setOptions(builderWithTimer);
        return view;
    }
}
