package com.example.ultimatefitness.male.bottomNavigation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ultimatefitness.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfilePhoto extends AppCompatActivity {

    private RoundedImageView profileImage;
    private Button saveBtn,closeBtn;
    private TextView profileChangeBtn;

    private DatabaseReference databaseReference;
    private FirebaseAuth mauth;

    private Uri imageUri;
    private String myUri="";
    private StorageTask uploadTask;
    private StorageReference storageProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_photo);

        profileImage = findViewById(R.id.profile_image);
        saveBtn= findViewById(R.id.save_btn);
        closeBtn= findViewById(R.id.close_btn);
        profileChangeBtn= findViewById(R.id.profile_btn);


        //init
        mauth= FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("User");
        storageProfile= FirebaseStorage.getInstance().getReference().child("Profile Pic");
         closeBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent= new Intent(EditProfilePhoto.this, ProfileActivity.class);
                 startActivity(intent);
             }
         });

         saveBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 uploadProfileImage();
             }
         });
            profileChangeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CropImage.activity().setAspectRatio(1,1).start(EditProfilePhoto.this);
                }
            });
            getUserInfo();
    }
    private void getUserInfo(){
        databaseReference.child(mauth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()&&snapshot.getChildrenCount()>0){
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode==RESULT_OK && data != null){
            CropImage.ActivityResult result=CropImage.getActivityResult(data);
        }else {
            Toast.makeText(this, "Error!! Try Again", Toast.LENGTH_SHORT).show();
        }
    }

    private void uploadProfileImage() {
        final ProgressDialog progressDialog= new ProgressDialog(this);
        progressDialog.setTitle("Set Your Profile");
        progressDialog.setMessage("Please Wait!! While we are setting your data");
        progressDialog.show();

        if (imageUri != null){
            final StorageReference fileRef= storageProfile.child(mauth.getCurrentUser().getUid()+".jpg");
            uploadTask= fileRef.putFile(imageUri);

            uploadTask.continueWithTask(new Continuation() {
                @Override
                public Object then(@NonNull Task task) throws Exception {

                    if (!task.isSuccessful()){
                        throw task.getException();
                    }
                    return fileRef.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if (task.isSuccessful()){
                        Uri downloadUri = (Uri) task.getResult();
                        myUri = downloadUri.toString();

                        HashMap<String, Object> userMap= new HashMap<>();

                        userMap.put("image",myUri);

                        databaseReference.child(mauth.getCurrentUser().getUid()).updateChildren(userMap);
                        progressDialog.dismiss();
                    }
                }
            });
        }
        else {
            progressDialog.dismiss();
            Toast.makeText(this, "Image not selected", Toast.LENGTH_SHORT).show();
        }
    }
}