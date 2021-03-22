package com.fitnessapplication.ultimatefitness.male.exercises.tricepsMaleAllExericse;

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

public class StandOverheadRopeFragment extends Fragment {
    ImageView nextMale,backMale,startMale,resumeMale,pauseMale;
    GifImageView gifMale;
    TextView readyMale,endMale;
    TextToSpeech textToSpeechMale;
    CircularView circularViewWithTimerMale;
    private AdView mAdView;
    public StandOverheadRopeFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_stand_overhead_rope, container, false);
        nextMale=view.findViewById(R.id.MaleForwordBtn);
        gifMale=view.findViewById(R.id.MaleGif);
        readyMale=view.findViewById(R.id.MaleStartPoint);
        endMale=view.findViewById(R.id.MaleEndpoint);
        startMale=view.findViewById(R.id.MalestartBtn);
        resumeMale=view.findViewById(R.id.MaleresumeBtn);
        pauseMale=view.findViewById(R.id.MalepauseBtn);
        backMale=view.findViewById(R.id.MaleBackBtn);

        circularViewWithTimerMale=view.findViewById(R.id.Malecircular_view);
        //ad
        mAdView=new AdView(getActivity());
        mAdView.setAdUnitId("ca-app-pub-2342822035961981~6396888741");
        mAdView.setAdSize(AdSize.FULL_BANNER);
        AdView adView=view.findViewById(R.id.adView);
        adView.addView(mAdView);
        AdRequest adRequest= new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        nextMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager rs = getFragmentManager();
                FragmentTransaction ft = rs.beginTransaction();
                ft.replace(R.id.tricepsMaleFrameContainer, new VBarPushdownFragment());
                ft.commit();
                circularViewWithTimerMale.stopTimer();
            }
        });
        backMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager rs = getFragmentManager();
                FragmentTransaction ft = rs.beginTransaction();
                ft.replace(R.id.tricepsMaleFrameContainer, new ReverseSingleCableFragment());
                ft.commit();
                circularViewWithTimerMale.stopTimer();
            }
        });

        startMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s= readyMale.getText().toString();
                int speech=textToSpeechMale.speak(s, TextToSpeech.QUEUE_FLUSH,null);
                circularViewWithTimerMale.startTimer();
            }
        });
        resumeMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerMale.resumeTimer();
            }
        });
        pauseMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerMale.pauseTimer();
            }
        });
        textToSpeechMale=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (SUCCESS == status){
                    int lang=textToSpeechMale.setLanguage(Locale.ENGLISH);
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
                                String e=endMale.getText().toString();
                                int speech=textToSpeechMale.speak(e,TextToSpeech.QUEUE_FLUSH,null);
                                FragmentManager rs = getFragmentManager();
                                FragmentTransaction ft = rs.beginTransaction();
                                ft.replace(R.id.tricepsMaleFrameContainer, new VBarPushdownFragment());
                                ft.commit();
                            }

                            @Override
                            public void onTimerCancelled() {
                                Toast.makeText(getContext(),"Timer Cancelled",Toast.LENGTH_SHORT).show();
                            }
                        });
        circularViewWithTimerMale.setOptions(builderWithTimer);
        return view;
    }


}