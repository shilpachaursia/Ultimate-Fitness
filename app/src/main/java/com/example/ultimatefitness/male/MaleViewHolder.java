package com.example.ultimatefitness.male;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ultimatefitness.R;


public class MaleViewHolder extends RecyclerView.ViewHolder {
    ImageView MaleImage;
    TextView MaleExercise,MaleStart;
    public MaleViewHolder(@NonNull View itemView) {
        super(itemView);
        MaleImage=(ImageView)itemView.findViewById(R.id.men_mainImage);
        MaleExercise=(TextView)itemView.findViewById(R.id.men_main_text);
        MaleStart=(TextView) itemView.findViewById(R.id.men_start_btn);
    }
}
