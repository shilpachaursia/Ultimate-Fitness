package com.fitnessapplication.ultimatefitness;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fitnessapplication.ultimatefitness.female.exerciseFemale.AbsWorkout;
import com.fitnessapplication.ultimatefitness.female.exerciseFemale.ArmsWorkout;
import com.fitnessapplication.ultimatefitness.female.exerciseFemale.ButtWorkout;
import com.fitnessapplication.ultimatefitness.female.exerciseFemale.FullBodyGirlWorkout;
import com.fitnessapplication.ultimatefitness.female.exerciseFemale.ThighWorkout;

import java.util.ArrayList;

public class myAdapterClass extends RecyclerView.Adapter<myViewHolder> {

    ArrayList<Model> data;

    Context context;

    public myAdapterClass(ArrayList<Model> data, Context context) {
        this.data = data;
        this.context = context;

    }
    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.grid_layout,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        final Model temp=data.get(position);

        holder.exercise.setText(data.get(position).getTitle());
        holder.imageView.setImageResource(data.get(position).getImage());
        holder.start.setText(data.get(position).getTitle());

        holder.start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(data.get(position).getStart().matches("START FULLBODY")){
                    Intent fullbody = new Intent(context, FullBodyGirlWorkout.class);
                    fullbody.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(fullbody);
                }
                else if (data.get(position).getStart().matches("START ABS")){
                    Intent abs = new Intent(context, AbsWorkout.class);
                    abs.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(abs);
                }
                else if (data.get(position).getStart().matches("START BUTT")){
                    Intent butt = new Intent(context, ButtWorkout.class);
                    butt.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(butt);
                }
                else if (data.get(position).getStart().matches("START ARMS")){
                    Intent arms = new Intent(context, ArmsWorkout.class);
                    arms.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(arms);
                }
                else if (data.get(position).getStart().matches("START THIGH")){
                    Intent thigh = new Intent(context,ThighWorkout.class);
                    thigh.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(thigh);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return data.size();
    }


}