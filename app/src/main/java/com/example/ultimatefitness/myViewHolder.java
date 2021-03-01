package com.example.ultimatefitness;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class myViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView exercise,start;

    public myViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView=(ImageView)itemView.findViewById(R.id.fullbodyImg);
        exercise=(TextView)itemView.findViewById(R.id.fullbodyText);
        start=(TextView) itemView.findViewById(R.id.start_btn);
    }
}
