package com.example.aplikasipakarayam.penyakit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.aplikasipakarayam.R;

public class PullorumFragment extends Fragment {
    public PullorumFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pullorum, container, false);
    }
}
