package com.example.ultimatefitness.male;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ultimatefitness.R;

import com.example.ultimatefitness.male.exercises.AbsMalwWorkout;
import com.example.ultimatefitness.male.exercises.BackMaleWorkout;
import com.example.ultimatefitness.male.exercises.BicepsMaleWorkout;
import com.example.ultimatefitness.male.exercises.CardioMaleWorkout;
import com.example.ultimatefitness.male.exercises.ChestMaleWorkout;
import com.example.ultimatefitness.male.exercises.FullbodyMenExercise;
import com.example.ultimatefitness.male.exercises.LegsMaleWorkout;
import com.example.ultimatefitness.male.exercises.ShoulderMaleWorkout;
import com.example.ultimatefitness.male.exercises.TricepsMaleWokout;


import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class AdapterMen extends RecyclerView.Adapter<MaleViewHolder> {
    ArrayList<ModelMen> data;
    Context context;

    public AdapterMen(ArrayList<ModelMen> data,Context context) {
        this.data = data;
        this.context=context;
    }

    @NonNull
    @Override
    public MaleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.men_exercise_page,parent,false);
        return new MaleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MaleViewHolder holder, int position) {
        holder.MaleImage.setImageResource(data.get(position).getImage());
        holder.MaleExercise.setText(data.get(position).getExerciseName());
        holder.MaleStart.setText(data.get(position).getExerciseStart());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}