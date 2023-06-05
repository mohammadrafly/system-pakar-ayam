package com.example.aplikasipakarayam;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.aplikasipakarayam.penyakit.BrochitisFragment;
import com.example.aplikasipakarayam.penyakit.CoryzaFragment;
import com.example.aplikasipakarayam.penyakit.GumboroFragment;
import com.example.aplikasipakarayam.penyakit.InfluenzaFragment;
import com.example.aplikasipakarayam.penyakit.NewcastleFragment;
import com.example.aplikasipakarayam.penyakit.PullorumFragment;

public class PenyakitFragment extends Fragment {

    // Define the list of pictures and titles
    private int[] pictureList = {R.mipmap.gumboro_image_foreground, R.mipmap.coryza_image_foreground, R.mipmap.influenza_image_foreground, R.mipmap.bronchitis_image_foreground, R.mipmap.newcastle_image_foreground, R.mipmap.pullorum_image_foreground};
    private String[] titleList = {"Penyakit Gumboro", "Penyakit Infectious Coryza", "Penyakit Avian Influenza (Flu Burung)", "Penyakit Infectious Bronchitis", "Penyakit New Castle Disease (Tetelo)", "Penyakit Pullorum Disease (Berak Kapur)"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_penyakit, container, false);

        // Get the LinearLayout from the layout file
        LinearLayout linearLayout = view.findViewById(R.id.linear_layout);

        // Create and add views for each picture with title
        for (int i = 0; i < pictureList.length; i++) {
            final int index = i;
            View itemView = inflater.inflate(R.layout.item_picture_title, container, false);

            // Set the picture and title
            ImageView imageView = itemView.findViewById(R.id.picture_image_view);
            TextView titleTextView = itemView.findViewById(R.id.title_text_view);
            imageView.setImageResource(pictureList[i]);
            titleTextView.setText(titleList[i]);

            // Set click listener to navigate to another screen based on the title
            itemView.setOnClickListener(v -> {
                String selectedTitle = titleList[index];

                Fragment fragment;
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                switch (selectedTitle) {
                    case "Penyakit Gumboro":
                        fragment = new GumboroFragment();
                        fragmentTransaction.replace(R.id.flFragment, fragment);
                        break;

                    case "Penyakit Infectious Coryza":
                        fragment = new CoryzaFragment();
                        fragmentTransaction.replace(R.id.flFragment, fragment);
                        break;

                    case "Penyakit Avian Influenza (Flu Burung)":
                        fragment = new InfluenzaFragment();
                        fragmentTransaction.replace(R.id.flFragment, fragment);
                        break;

                    case "Penyakit Infectious Bronchitis":
                        fragment = new BrochitisFragment();
                        fragmentTransaction.replace(R.id.flFragment, fragment);
                        break;

                    case "Penyakit New Castle Disease (Tetelo)":
                        fragment = new NewcastleFragment();
                        fragmentTransaction.replace(R.id.flFragment, fragment);
                        break;

                    case "Penyakit Pullorum Disease (Berak Kapur)":
                        fragment = new PullorumFragment();
                        fragmentTransaction.replace(R.id.flFragment, fragment);
                        break;
                }

                fragmentTransaction.commit();
            });

            // Add the item view to the linear layout
            linearLayout.addView(itemView);
        }

        return view;
    }
}
