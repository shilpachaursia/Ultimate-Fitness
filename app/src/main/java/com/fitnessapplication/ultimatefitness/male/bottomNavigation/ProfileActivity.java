package com.fitnessapplication.ultimatefitness.male.bottomNavigation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.fitnessapplication.ultimatefitness.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

public class ProfileActivity extends AppCompatActivity {
    ImageView editName;
    Button btn,editbtn,saveImage;
    FirebaseAuth auth;
    FirebaseUser firebaseUser;
    TextView name,about;
    SeekBar progress;
    RelativeLayout relativeLayout;
    private Uri filePath;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    CalendarView calendar;
    private RoundedImageView profileImage;
    private DatabaseReference databaseReference;
    private static final String TAG="Calender Activity";
    //sharedPre
    SharedPreferences preferences;
    private static final String SHARED_PREF_NAME="mypref";
    private static final String KEY_NAME="name";
    private static final String LEY_INFO="info";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        auth = FirebaseAuth.getInstance();
        btn = findViewById(R.id.Profile_button);
        editbtn = findViewById(R.id.Profile_EditBtn);
        saveImage = findViewById(R.id.saveImage);
        progress = findViewById(R.id.seekbar);
        calendar = findViewById(R.id.calender);
        editName=findViewById(R.id.nameEdit);
        name=findViewById(R.id.textname);
        about=findViewById(R.id.textabout);
        relativeLayout=findViewById(R.id.relativeLayout);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("User");
        firebaseUser = auth.getCurrentUser();
        profileImage = findViewById(R.id.imageprofile);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        name.setText(firebaseUser.getDisplayName());

        editName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this,EditNameActivity.class));
            }
        });
        //save data
        preferences=getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        String name1=preferences.getString(KEY_NAME,null);
        String info=preferences.getString(LEY_INFO,null);
        about.setText(info);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                signout();
            }
        });
        if (firebaseUser != null) {
            name.setText(name1);
        }
        getUserInfo();
        progress();
        calendar();
        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
                saveImage.setVisibility(View.VISIBLE);
            }
        });
        saveImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
                saveImage.setVisibility(View.GONE);
            }
        });
    }
    private void uploadImage() {
        if (filePath!=null){
            final ProgressDialog progressDialog=new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
            StorageReference reference=storageReference.child("image/"+ UUID.randomUUID().toString());

            reference.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressDialog.dismiss();
                    Toast.makeText(ProfileActivity.this, "Image Uploaded", Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    double progress=(100.0*snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                    progressDialog.setMessage("Upload"+(int)progress+"%");
                }
            });
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode==RESULT_OK && data.getData()!=null){
            filePath=data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filePath);
                profileImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void chooseImage() {
        Intent intent= new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Image"), 1);
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (firebaseUser==null){
            startActivity(new Intent(ProfileActivity.this,LoginActivity.class));
            finish();
        }
    }
    private void progress() {
        progress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Toast.makeText(getApplicationContext(),"seekbar progress: "+progress, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(),"seekbar touch started!", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(),"seekbar touch stopped!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void calendar() {
        calendar.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            String date=year+"/"+month+"/"+dayOfMonth;
            Log.d(TAG,"onSelectedDayChange: date"+date);
        });
    }
    private void getUserInfo(){
        databaseReference.child(Objects.requireNonNull(auth.getCurrentUser()).getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()&&snapshot.getChildrenCount()>0){
                    String name= Objects.requireNonNull(snapshot.child("name").getValue()).toString();

                    if (snapshot.hasChild("image")){
                        String image= Objects.requireNonNull(snapshot.child("image").getValue()).toString();
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