package com.example.dreamsocialclub.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.dreamsocialclub.LoginActivity;
import com.example.dreamsocialclub.PreferenceData;
import com.example.dreamsocialclub.R;
import com.example.dreamsocialclub.home.ui.gallery.GalleryFragment;
import com.example.dreamsocialclub.home.ui.home.HomeFragment;
import com.example.dreamsocialclub.home.ui.send.SendFragment;
import com.example.dreamsocialclub.home.ui.share.ShareFragment;
import com.example.dreamsocialclub.home.ui.slideshow.SlideshowFragment;
import com.example.dreamsocialclub.home.ui.tools.ToolsFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.os.Handler;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private Context context;
    private PreferenceData preferenceData;
    private FirebaseAuth firebaseAuth;
    private ImageView nav_profile_pic;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = HomeActivity.this;
        this.preferenceData = new PreferenceData(context);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        nav_profile_pic = findViewById(R.id.nav_profile_pic);
        setSupportActionBar(toolbar);

        firebaseAuth = FirebaseAuth.getInstance();

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        TextView tv_user_name = headerView.findViewById(R.id.tv_nav_user_name);
        TextView tv_user_mobile = headerView.findViewById(R.id.tv_user_mobile);


        tv_user_name.setText(preferenceData.getStringValue("name"));
        tv_user_mobile.setText(preferenceData.getStringValue("phone"));



        displaySelectedScreen(R.id.nav_home);
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        Picasso.with( MainActivity.this).load("https://firebasestorage.googleapis.com/v0/b/studentmealsystem.appspot.com/o/profile%20picture%2FxjfK39vQh1WrmeBjQsvJGlvxuwk2.jpg?alt=media&token=28e4aa1d-6aff-4f49-b420-426ca14e40b4").placeholder(R.drawable.default_profile).into(nav_profile_pic);
//
//    }

//    private void retrieve_data_for_currient_user(){
//        FirebaseAuth current_user= FirebaseAuth.getInstance();
//        String uid = current_user.getUid();
//        DatabaseReference users = FirebaseDatabase.getInstance().getReference("Users");
//        DatabaseReference child = users.child(uid);
//
//        child.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                NewResisterUser newResisterUser = dataSnapshot.getValue(NewResisterUser.class);
//                preferenceData.setValue("profile_picture",newResisterUser.getProfile_picture().toString());
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }

    private void displaySelectedScreen(int id) {
        Fragment fragment = null;
        Bundle bundle = null;
        switch (id) {
            case R.id.nav_home:
                fragment = new HomeFragment();
                break;
            case R.id.nav_gallery:
                fragment = new GalleryFragment();
                break;
            case R.id.nav_slideshow:
                fragment = new SlideshowFragment();
                break;

            case R.id.nav_tools:
                fragment = new ToolsFragment();
                break;
            case R.id.nav_share:
                fragment = new ShareFragment();
                break;
                case R.id.nav_send:
                fragment = new SendFragment();
                break;
            case R.id.nav_logout:
                firebaseAuth.signOut();
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
        }

        if(fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
            fragmentTransaction.commit();
        }

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
    }


    boolean doubleBackPressed = false;
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if(doubleBackPressed){
                super.onBackPressed();
                finish();
                return;
            }
        }

        this.doubleBackPressed = true;
        Toast.makeText(context, "Press again to exit!", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackPressed = false;
            }
        }, 2000);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        displaySelectedScreen(id);
        return true;
    }

}
