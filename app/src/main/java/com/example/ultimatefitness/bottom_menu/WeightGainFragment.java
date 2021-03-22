package com.example.ultimatefitness.bottom_menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ultimatefitness.R;


public class WeightGainFragment extends Fragment {
    ImageView backbtn;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.weightgainfragment, container, false);
        backbtn=root.findViewById(R.id.weightGainBackBtn);
        return root;
    }
}
