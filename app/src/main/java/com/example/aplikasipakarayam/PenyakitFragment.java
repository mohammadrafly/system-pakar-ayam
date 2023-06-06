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

import com.example.aplikasipakarayam.penyakit.BronchitisActivity;
import com.example.aplikasipakarayam.penyakit.CoryzaActivity;
import com.example.aplikasipakarayam.penyakit.GumboroActivity;
import com.example.aplikasipakarayam.penyakit.InfluenzaActivity;
import com.example.aplikasipakarayam.penyakit.NewCastleActivity;
import com.example.aplikasipakarayam.penyakit.PullorumActivity;

public class PenyakitFragment extends Fragment {

    private int[] pictureList = {R.mipmap.gumboro_image_foreground, R.mipmap.coryza_image_foreground, R.mipmap.influenza_image_foreground, R.mipmap.bronchitis_image_foreground, R.mipmap.newcastle_image_foreground, R.mipmap.pullorum_image_foreground};
    private String[] titleList = {"Penyakit Gumboro", "Penyakit Infectious Coryza", "Penyakit Avian Influenza (Flu Burung)", "Penyakit Infectious Bronchitis", "Penyakit New Castle Disease (Tetelo)", "Penyakit Pullorum Disease (Berak Kapur)"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_penyakit, container, false);

        LinearLayout linearLayout = view.findViewById(R.id.linear_layout);

        for (int i = 0; i < pictureList.length; i++) {
            final int index = i;
            View itemView = inflater.inflate(R.layout.item_picture_title, container, false);

            ImageView imageView = itemView.findViewById(R.id.picture_image_view);
            TextView titleTextView = itemView.findViewById(R.id.title_text_view);
            imageView.setImageResource(pictureList[i]);
            titleTextView.setText(titleList[i]);

            itemView.setOnClickListener(v -> {
                String selectedTitle = titleList[index];

                Intent intent;

                switch (selectedTitle) {
                    case "Penyakit Gumboro":
                        intent = new Intent(requireActivity(), GumboroActivity.class);
                        break;

                    case "Penyakit Infectious Coryza":
                        intent = new Intent(requireActivity(), CoryzaActivity.class);
                        break;

                    case "Penyakit Avian Influenza (Flu Burung)":
                        intent = new Intent(requireActivity(), InfluenzaActivity.class);
                        break;

                    case "Penyakit Infectious Bronchitis":
                        intent = new Intent(requireActivity(), BronchitisActivity.class);
                        break;

                    case "Penyakit New Castle Disease (Tetelo)":
                        intent = new Intent(requireActivity(), NewCastleActivity.class);
                        break;

                    case "Penyakit Pullorum Disease (Berak Kapur)":
                        intent = new Intent(requireActivity(), PullorumActivity.class);
                        break;

                    default:
                        intent = new Intent(requireActivity(), GumboroActivity.class);
                        break;
                }

                startActivity(intent);
            });

            linearLayout.addView(itemView);
        }

        return view;
    }
}
