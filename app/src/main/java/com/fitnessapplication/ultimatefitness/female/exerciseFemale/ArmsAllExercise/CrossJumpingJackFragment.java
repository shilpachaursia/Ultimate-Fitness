package com.fitnessapplication.ultimatefitness.female.exerciseFemale.ArmsAllExercise;

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

public class CrossJumpingJackFragment extends Fragment {
    ImageView CJJImageViewForeword,CJJImageViewBack,CJJStart,CJJResume,CJJPause;
    GifImageView CJJGif;
    TextView CJJReadyToGo,CJJFinish;
    TextToSpeech textToSpeechCJJ;
    CircularView circularViewWithTimerCJJ;
    private AdView mAdView;
    public CrossJumpingJackFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_cross_jumping_jack, container, false);
        CJJImageViewForeword=view.findViewById(R.id.CJJForwordBtn);
        CJJImageViewBack=view.findViewById(R.id.CJJBackbtn);
        CJJPause=view.findViewById(R.id.CJJPause);
        CJJResume=view.findViewById(R.id.CJJresumeBtn);
        CJJStart=view.findViewById(R.id.CJJstartBtn);
        CJJGif=view.findViewById(R.id.CJJGif);
        CJJReadyToGo=view.findViewById(R.id.CJJStartPoint);
        CJJFinish=view.findViewById(R.id.CJJEndpoint);

        circularViewWithTimerCJJ=view.findViewById(R.id.CJJcircular_view);
        //ad
        mAdView=new AdView(getActivity());
        mAdView.setAdUnitId("ca-app-pub-2342822035961981~6396888741");
        mAdView.setAdSize(AdSize.FULL_BANNER);
        AdView adView=view.findViewById(R.id.adView);
        adView.addView(mAdView);
        AdRequest adRequest= new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        CJJImageViewForeword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager armsCircle = getFragmentManager();
                FragmentTransaction ft = armsCircle.beginTransaction();
                ft.replace(R.id.scrollView, new ArmsCircleFragment());
                ft.commit();
                circularViewWithTimerCJJ.stopTimer();
            }

        });
        CJJImageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager pushup = getFragmentManager();
                FragmentTransaction ft = pushup.beginTransaction();
                ft.replace(R.id.scrollView, new PushUpsFragment());
                ft.commit();
                circularViewWithTimerCJJ.stopTimer();
            }
        });
        CJJStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s= CJJReadyToGo.getText().toString();
                int speech=textToSpeechCJJ.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                circularViewWithTimerCJJ.startTimer();
            }
        });
        CJJResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerCJJ.resumeTimer();
            }
        });
        CJJPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularViewWithTimerCJJ.pauseTimer();
            }
        });
        textToSpeechCJJ=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (SUCCESS == status){
                    int lang=textToSpeechCJJ.setLanguage(Locale.ENGLISH);
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
                                String e= CJJFinish.getText().toString();
                                int speech=textToSpeechCJJ.speak(e,TextToSpeech.QUEUE_FLUSH,null);
                                FragmentManager armsCircle = getFragmentManager();
                                FragmentTransaction ft = armsCircle.beginTransaction();
                                ft.replace(R.id.scrollView, new ArmsCircleFragment());
                                ft.commit();
                            }

                            @Override
                            public void onTimerCancelled() {
                                Toast.makeText(getContext(),"Timer Cancelled",Toast.LENGTH_SHORT).show();
                            }
                        });
        circularViewWithTimerCJJ.setOptions(builderWithTimer);
        return view;
    }
}