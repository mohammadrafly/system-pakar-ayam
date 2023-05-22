package com.example.aplikasipakarayam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        View guestButton = findViewById(R.id.buttonBack);
        guestButton.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}
