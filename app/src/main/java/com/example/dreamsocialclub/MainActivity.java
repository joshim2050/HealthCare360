package com.example.dreamsocialclub;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.example.dreamsocialclub.home.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
        Thread thread = new Thread(){
            @Override
            public void run() {
                try{
                    sleep(3000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    ///  change this advantage is for test app easily   StartPageActivity

                    FirebaseUser currient_user = firebaseAuth.getCurrentUser();
                    if (currient_user == null){
                        Intent toy = new Intent(MainActivity.this, LoginActivity.class);

                        startActivity(toy);
                    }else {
                        Intent baby = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(baby);
                    }
                    finish();

                }
            }
        };
        thread.start();
    }
}
