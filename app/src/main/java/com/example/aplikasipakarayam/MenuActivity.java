package com.example.aplikasipakarayam;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private HomeFragment homeFragment;
    private DiagnosaFragment diagnosaFragment;
    private BantuanFragment bantuanFragment;
    private PenyakitFragment penyakitFragment;
    private TentangFragment tentangFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        View backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> onBackPressed());

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        initializeFragments();

        fragmentManager = getSupportFragmentManager();

        bottomNavigationView.setSelectedItemId(R.id.home);
    }

    private void initializeFragments() {
        homeFragment = new HomeFragment();
        diagnosaFragment = new DiagnosaFragment();
        bantuanFragment = new BantuanFragment();
        penyakitFragment = new PenyakitFragment();
        tentangFragment = new TentangFragment();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int selectedItemId = item.getItemId();

        if (selectedItemId == R.id.home) {
            replaceFragment(homeFragment);
            return true;
        } else if (selectedItemId == R.id.diagnosa) {
            replaceFragment(diagnosaFragment);
            return true;
        } else if (selectedItemId == R.id.bantuan) {
            replaceFragment(bantuanFragment);
            return true;
        } else if (selectedItemId == R.id.penyakit) {
            replaceFragment(penyakitFragment);
            return true;
        } else if (selectedItemId == R.id.tentang) {
            replaceFragment(tentangFragment);
            return true;
        }

        return false;
    }

    private void replaceFragment(Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.flFragment, fragment)
                .addToBackStack(null) // Add the transaction to the back stack
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack(); // Navigate back to the previous fragment
        } else {
            super.onBackPressed();
        }
    }
}
