package com.example.ultimatefitness.male;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        holder.MaleStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(data.get(position).getExerciseStart().matches("START FULLBODY")){
                    Intent fullbody = new Intent(context, FullbodyMenExercise.class);
                    fullbody.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(fullbody);
                }
                else if (data.get(position).getExerciseStart().matches("START CHEST")){
                    Intent chest = new Intent(context, ChestMaleWorkout.class);
                    chest.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(chest);
                }
                else if (data.get(position).getExerciseStart().matches("START BACK")){
                    Intent back = new Intent(context, BackMaleWorkout.class);
                    back.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(back);
                }
                else if (data.get(position).getExerciseStart().matches("START BICEPS")){
                    Intent biceps = new Intent(context, BicepsMaleWorkout.class);
                    biceps.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(biceps);
                }
                else if (data.get(position).getExerciseStart().matches("START TRICEPS")){
                    Intent triceps = new Intent(context, TricepsMaleWokout.class);
                    triceps.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(triceps);
                }
                else if (data.get(position).getExerciseStart().matches("START SHOULDER")){
                    Intent shoulder = new Intent(context, ShoulderMaleWorkout.class);
                    shoulder.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(shoulder);
                }
                else if (data.get(position).getExerciseStart().matches("START LEGS")){
                    Intent leg = new Intent(context, LegsMaleWorkout.class);
                    leg.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(leg);
                }
                else if (data.get(position).getExerciseStart().matches("START CARDIO")){
                    Intent cardio = new Intent(context, CardioMaleWorkout.class);
                    cardio.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(cardio);
                }
                else if (data.get(position).getExerciseStart().matches("START ABS")){
                    Intent abs = new Intent(context, AbsMalwWorkout.class);
                    abs.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(abs);
                } }}); }
    @Override
    public int getItemCount() {
        return data.size();
    }
}