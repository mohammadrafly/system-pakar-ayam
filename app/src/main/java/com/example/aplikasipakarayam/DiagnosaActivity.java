package com.example.aplikasipakarayam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class DiagnosaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosa);

        View backButton = findViewById(R.id.buttonBack);
        backButton.setOnClickListener(v -> finish());

        View bantuanButton = findViewById(R.id.bantuanButton);
        bantuanButton.setOnClickListener(v -> {
            Intent intent = new Intent(DiagnosaActivity.this, BantuanActivity.class);
            startActivity(intent);
        });

        View tentangButton = findViewById(R.id.tentangButton);
        tentangButton.setOnClickListener(v -> {
            Intent intent = new Intent(DiagnosaActivity.this, TentangActivity.class);
            startActivity(intent);
        });

        View diagnosaButton = findViewById(R.id.diagnosaButton);
        diagnosaButton.setOnClickListener(v -> {
            Intent intent = new Intent(DiagnosaActivity.this, DiagnosaActivity.class);
            startActivity(intent);
        });
    }
}
