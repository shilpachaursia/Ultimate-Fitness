package com.example.ultimatefitness.male.drawerMenuActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ultimatefitness.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class FeedbackActivity extends AppCompatActivity {
Button sendBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        sendBtn=findViewById(R.id.feedbackBtn);
        sendBtn();
    }

    private void sendBtn() {
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }
    private void showDialog(){
        final AlertDialog.Builder dialog= new AlertDialog.Builder(this);
        dialog.setTitle("FeedbackForm");
        dialog.setMessage("Provide your Valuable Feedback");

        LayoutInflater inflater=LayoutInflater.from(this);
        View reg_layout=inflater.inflate(R.layout.feedback_send,null);

        final MaterialEditText edtEmail=reg_layout.findViewById(R.id.emailFeedback);
        final MaterialEditText edtName=reg_layout.findViewById(R.id.nameFeedback);
        final MaterialEditText edtFeedback=reg_layout.findViewById(R.id.feedback);
        dialog.setView(reg_layout);
        dialog.setPositiveButton("SEND", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (TextUtils.isEmpty((edtEmail.getText().toString()))){
                    Toast.makeText(FeedbackActivity.this, "Enter your Email Please", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty((edtName.getText().toString()))){
                    Toast.makeText(FeedbackActivity.this, "Enter your Name Please", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty((edtFeedback.getText().toString()))){
                    Toast.makeText(FeedbackActivity.this, "Feedback Field Can't be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                FirebaseDatabase database=FirebaseDatabase.getInstance();
                DatabaseReference myref=database.getReference();
                myref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Object value= snapshot.getValue();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(FeedbackActivity.this, "Failed to read Value", Toast.LENGTH_SHORT).show();
                    }
                });
                myref.child("User").child(edtName.getText().toString())
                        .child("Email").setValue(edtEmail.getText().toString());
                myref.child("User").child(edtName.getText().toString())
                        .child("Feedback").setValue(edtFeedback.getText().toString());
                myref.child("User").child(edtName.getText().toString())
                        .child("Name").setValue(edtName.getText().toString());
                Toast.makeText(FeedbackActivity.this, "Thanks for yor valuable feedback..", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}