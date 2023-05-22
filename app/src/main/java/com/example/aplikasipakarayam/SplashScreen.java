package com.example.aplikasipakarayam;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        ImageView spinnerImageView = findViewById(R.id.spinnerImageView);
        Animation rotationAnimation = AnimationUtils.loadAnimation(this, R.anim.spinner);
        spinnerImageView.startAnimation(rotationAnimation);

        // Simulate a delay before launching the main activity
        Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(3000);  // Adjust the delay as needed
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        timerThread.start();
    }
}
