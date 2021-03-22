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

public class SquatButtFragment extends Fragment {
    ImageView nextSB,backSB,startSB,resumeSB,pauseSB;
    GifImageView gifSB;
    TextView readySB,endSB;
    TextToSpeech textToSpeechSB;
    CircularView circularViewWithTimerSB;
    private AdView mAdView;
    public SquatButtFragment() {
        // Required empty public constructor
    }
   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_squat_butt, container, false);
       nextSB=view.findViewById(R.id. SBForwordBtn);
       gifSB=view.findViewById(R.id.SBGif);
       readySB=view.findViewById(R.id.SBStartPoint);
       backSB=view.findViewById(R.id.SBback);
       endSB=view.findViewById(R.id.SBEndpoint);
       startSB=view.findViewById(R.id.SBstartBtn);
       resumeSB=view.findViewById(R.id.SBresumeBtn);
       pauseSB=view.findViewById(R.id.SBpauseBtn);

       circularViewWithTimerSB=view.findViewById(R.id.SBcircular_view);
       //ad
       mAdView=new AdView(getActivity());
       mAdView.setAdUnitId("ca-app-pub-2342822035961981~6396888741");
       mAdView.setAdSize(AdSize.FULL_BANNER);
       AdView adView=view.findViewById(R.id.adView);
       adView.addView(mAdView);
       AdRequest adRequest= new AdRequest.Builder().build();
       mAdView.loadAd(adRequest);
       nextSB.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               FragmentManager SKB = getFragmentManager();
               FragmentTransaction ft = SKB.beginTransaction();
               ft.replace(R.id.frameButt, new StandingKickBackButtFragment());
               ft.commit();
               circularViewWithTimerSB.stopTimer();
           }
       });
       backSB.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               FragmentManager hs=getFragmentManager();
               FragmentTransaction transaction=hs.beginTransaction();
               transaction.replace(R.id.frameButt,new HighSteppingButtFragment()).commit();
           }
       });
       startSB.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String s= readySB.getText().toString();
               int speech=textToSpeechSB.speak(s,TextToSpeech.QUEUE_FLUSH,null);
               circularViewWithTimerSB.startTimer();
           }
       });
       resumeSB.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               circularViewWithTimerSB.resumeTimer();
           }
       });
       pauseSB.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               circularViewWithTimerSB.pauseTimer();
           }
       });
       textToSpeechSB=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
           @Override
           public void onInit(int status) {
               if (SUCCESS == status){
                   int lang=textToSpeechSB.setLanguage(Locale.ENGLISH);
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
                               String e=endSB.getText().toString();
                               int speech=textToSpeechSB.speak(e,TextToSpeech.QUEUE_FLUSH,null);
                               FragmentManager skb = getFragmentManager();
                               FragmentTransaction ft = skb.beginTransaction();
                               ft.replace(R.id.frameButt, new StandingKickBackButtFragment());
                               ft.commit();
                           }

                           @Override
                           public void onTimerCancelled() {
                               Toast.makeText(getContext(),"Timer Cancelled",Toast.LENGTH_SHORT).show();
                           }
                       });
       circularViewWithTimerSB.setOptions(builderWithTimer);
       return view;
   }
}
