package com.example.ultimatefitness.male.bottomNavigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ultimatefitness.R;
import com.example.ultimatefitness.male.MaleActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    Button btn,editbtn;
    FirebaseAuth auth;
    FirebaseUser firebaseUser;
    TextView name;

    private RoundedImageView profileImage;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        auth=FirebaseAuth.getInstance();
        btn =findViewById(R.id.button);
        editbtn= findViewById(R.id.EditBtn);

        name= findViewById(R.id.textname);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("User");
        firebaseUser=auth.getCurrentUser();
       profileImage= findViewById(R.id.imageprofile);


        //edit profile
        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editProfile= new Intent(ProfileActivity.this,EditProfilePhoto.class);
                startActivity(editProfile);
            }
        });


//        String st;
//        st=getIntent().getExtras().getString("Value");
//        name.setText(st);
        name.setText(firebaseUser.getDisplayName());
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              auth.getInstance().signOut();
              signout();
            }
        });

        getUserInfo();
    }
    private void getUserInfo(){
        databaseReference.child(auth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()&&snapshot.getChildrenCount()>0){
                    String name=snapshot.child("name").getValue().toString();

                    if (snapshot.hasChild("image")){
                        String image= snapshot.child("image").getValue().toString();
                        Picasso.get().load(image).into(profileImage);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void signout() {
        Intent i=new Intent(ProfileActivity.this, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        finish();

    }




}