package com.example.ultimatefitness.female;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Toast;
import com.example.ultimatefitness.Model;
import com.example.ultimatefitness.R;
import com.example.ultimatefitness.bottom_menu.PlanActivity;
import com.example.ultimatefitness.bottom_menu.TipsActivity;
import com.example.ultimatefitness.bottom_menu.UtilityActivity;
import com.example.ultimatefitness.male.MaleActivity;
import com.example.ultimatefitness.male.bottomNavigation.LoginActivity;
import com.example.ultimatefitness.male.bottomNavigation.ProfileActivity;
import com.example.ultimatefitness.male.bottomNavigation.RegisterActivity;
import com.example.ultimatefitness.myAdapterClass;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import java.util.ArrayList;
import static com.example.ultimatefitness.R.id.button;
import static com.example.ultimatefitness.R.id.cal_logo;
import static com.example.ultimatefitness.R.id.login;
import static com.example.ultimatefitness.R.id.plan;
import static com.example.ultimatefitness.R.id.tips;
import static com.example.ultimatefitness.R.id.utility;
public class FemaleActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    static final float END_SCALE=0.7f;
    ImageView menu, search, menuside, heart;
    BottomNavigationView bottomNavigationView;
    //drawer layout
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    RelativeLayout contentView;

   
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.side_setting) ;
        Toast.makeText(this, "setting", Toast.LENGTH_SHORT).show();
        if (itemId == R.id.side_share)
            Toast.makeText(this, "share", Toast.LENGTH_SHORT).show();
        if (itemId == R.id.side_privacy) ;
        Toast.makeText(this, "privacy", Toast.LENGTH_SHORT).show();
        if (itemId == R.id.side_feedback) ;
        Toast.makeText(this, "feedback", Toast.LENGTH_SHORT).show();
        if (itemId == R.id.side_rate) ;
        Toast.makeText(this, "rate", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_female);
        menu = (ImageView) findViewById(R.id.menu);
        menuside = (ImageView) findViewById(R.id.menuside);
        search = (ImageView) findViewById(R.id.search);
        heart = (ImageView) findViewById(R.id.heart);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);
        contentView= findViewById(R.id.content);
        //recyclerView
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapterClass adapter = new myAdapterClass(dataQueue(), getApplicationContext());
        mRecyclerView.setAdapter(adapter);
        //drawer layout id's
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view_leftSide);
        navigationView.setNavigationItemSelectedListener((menuItem)->{
            switch (menuItem.getItemId()){
                case R.id.nav_home:
                    startActivity(new Intent(this,FemaleActivity.class));
                    break;
                case R.id.nav_gender:
                    startActivity(new Intent(this, MaleActivity.class));
                    break;
                case  R.id.nav_login:
                    startActivity(new Intent(this, LoginActivity.class));
                    break;
                case R.id.nav_profile:
                    startActivity(new Intent(this, ProfileActivity.class));
                    break;
            }
            return false;
        });
        //navigation view
        //for toogle click
        navigationDrawar();
       
        //side menu
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.exercise:
                        break;
                    case plan:
                        Intent intent = new Intent(FemaleActivity.this, PlanActivity.class);
                        startActivity(intent);
                        break;
                    case tips:
                        Intent intent1 = new Intent(FemaleActivity.this, TipsActivity.class);
                        startActivity(intent1);
                        break;
                    case utility:
                        Intent intent2 = new Intent(FemaleActivity.this, UtilityActivity.class);
                        startActivity(intent2);
                        break;
                    case login:
                        Intent intent3 = new Intent(FemaleActivity.this, RegisterActivity.class);
                        startActivity(intent3);
                        break;
                }
                return false;
            }
        });
    }
    //navigation
    private void navigationDrawar() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
        menuside.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        animateNavigationDrawer();
    }
    private void animateNavigationDrawer() {
        drawerLayout.setScrimColor(Color.TRANSPARENT);
//        drawerLayout.setScrimColor(getResources().getColor(R.color.pink));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);
                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else
        super.onBackPressed();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }
    public ArrayList<Model> dataQueue() {
        ArrayList<Model> holder = new ArrayList<>();
        Model obj1 = new Model();
        obj1.setImage(R.drawable.fullbadygirlbg);
        obj1.setTitle("FULLBODY WORKOUT");
        obj1.setStart("START FULLBODY");
        holder.add(obj1);
        Model obj2 = new Model();
        obj2.setImage(R.drawable.absgirlbg);
        obj2.setTitle("ABS WORKOUT");
        obj2.setStart("START ABS");
        holder.add(obj2);
        Model obj3 = new Model();
        obj3.setImage(R.drawable.buttgirlbg);
        obj3.setTitle("BUTT WORKOUT");
        obj3.setStart("START BUTT");
        holder.add(obj3);
        Model obj4 = new Model();
        obj4.setImage(R.drawable.armgirlbg);
        obj4.setTitle("ARM WORKOUT");
        obj4.setStart("START ARMS");
        holder.add(obj4);
        Model obj5 = new Model();
        obj5.setImage(R.drawable.thighgirlbg);
        obj5.setTitle("THIGH WORKOUT");
        obj5.setStart("START THIGH");
        holder.add(obj5);
        return holder;
    }

}