package com.example.aplikasipakarayam;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DiagnosaFragment extends Fragment {
    private TextView resultTextView;
    private PenyakitModel model;
    private List<String> selectedItems;
    private ChipGroup chipGroup;
    private ArrayAdapter<String> adapter;
    private ProgressBar progressBar;

    public DiagnosaFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diagnosa, container, false);

        model = new PenyakitModel();

        ListView diseaseListView = view.findViewById(R.id.diseaseListView);
        String[] itemsList = getResources().getStringArray(R.array.symptoms_array);
        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, itemsList);
        diseaseListView.setAdapter(adapter);

        diseaseListView.setOnItemClickListener((parent, view1, position, id) -> {
            String selectedItem = itemsList[position];
            addChip(selectedItem);
        });

        Button predictButton = view.findViewById(R.id.predictButton);
        resultTextView = view.findViewById(R.id.resultTextView);
        chipGroup = view.findViewById(R.id.chipGroup);
        selectedItems = new ArrayList<>();

        progressBar = view.findViewById(R.id.progressBar);
        predictButton.setOnClickListener(v -> {
            showSpinner();
            resultTextView.setText("Sedang proses..");
            resultTextView.setGravity(Gravity.CENTER);
            new Handler().postDelayed(() -> {
                performPrediction();
                resultTextView.setGravity(Gravity.START);
            }, 3000);
        });

        return view;
    }

    private void showSpinner() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideSpinner() {
        progressBar.setVisibility(View.GONE);
    }

    private void addChip(String text) {
        if (selectedItems.contains(text)) {
            Toast.makeText(requireContext(), "Gejala sudah dipilih!", Toast.LENGTH_SHORT).show();
            return;
        }

        selectedItems.add(text);
        Chip chip = new Chip(requireContext());
        chip.setText(text);
        chip.setCloseIconVisible(true);
        chip.setCloseIconTintResource(com.google.android.material.R.color.design_default_color_error);
        chip.setCloseIconContentDescription("Remove");
        chip.setOnCloseIconClickListener(v -> {
            chipGroup.removeView(chip);
            selectedItems.remove(text);
        });

        chipGroup.addView(chip);
    }

    private void performPrediction() {
        String[] symptoms = getSelectedItemsAsArray();
        String prediction = model.deteksiPenyakit(symptoms);

        hideSpinner();
        Toast.makeText(requireContext(), "Gejala yang dipilih: " + symptoms, Toast.LENGTH_SHORT).show();

        // Memformat output String prediction
        String[] diseases = prediction.split("\n");
        StringBuilder formattedPrediction = new StringBuilder();
        String highestDisease = "";
        double highestPercentage = 0;

        for (String disease : diseases) {
            String[] parts = disease.split(":");
            if (parts.length == 2) {
                String diseaseName = parts[0].trim();
                String percentage = parts[1].trim();

                // Remove the percentage symbol from the string
                String percentageWithoutSymbol = percentage.replace("%", "");

                try {
                    double currentPercentage = Double.parseDouble(percentageWithoutSymbol);
                    if (currentPercentage > highestPercentage) {
                        highestPercentage = currentPercentage;
                        highestDisease = diseaseName;
                    }

                    // Format the percentage string
                    String formattedPercentage = String.format("%.2f", currentPercentage);

                    formattedPrediction.append(diseaseName).append(": ").append(formattedPercentage).append("%\n");
                } catch (NumberFormatException e) {
                    // Handle any parsing errors here
                    e.printStackTrace();
                }
            }
        }

        // Menampilkan hasil prediksi yang diformat
        String formattedPredictionString = formattedPrediction.toString();
        if (!formattedPredictionString.isEmpty()) {
            resultTextView.setText(formattedPredictionString);
        }

        // Menampilkan penyakit dengan presentase tertinggi
        if (!highestDisease.isEmpty()) {
            String highestDiseaseString = "Penyakit dengan presentase tertinggi:\n" + highestDisease + ": " + String.format("%.2f", highestPercentage) + "%";
            resultTextView.append("\n\n" + highestDiseaseString);

            // Mengambil tindakan berdasarkan penyakit dengan presentase tertinggi
            switch (highestDisease) {
                case "Gumboro":
                    String gumboroAction = "Penanganan: Pencegahan bisa dilakukan dengan cara memberikan vaksinasi gumboro. Vaksinasi pada ayam breeding ketika masa pertumbuhan dan dewasa bisa meningkatkan sistem kekebalan induk pada anak ayam.";
                    resultTextView.append("\n\n" + gumboroAction);
                    break;
                case "Infectious Coryza":
                    String CoryzaAction = "Penanganan: Pengobatan dapat dilakukan dengan menggunakan beberapa preparat sulfanamides dan antibiotik yang diberikan melalui pakan atau air minum usahakan melakukan pengobatan ini jangan sampai berhenti karena dikhawatirkan ayam akan kambuh lagi. Beberapa obat dan antibiotik yang digunakan streptomycin,Sulfadimethoxine, dan tylosin.";
                    resultTextView.append("\n\n" + CoryzaAction);
                    break;
                case "Avian Influenza":
                    String InfluenzaAction = "Penanganan: Cara menanggulangi penyakit ini dengan cara pemberian antibiotik, multivitamin, melaksanakan vaksisnasi ; mengisolasi farm atau peternakan yang terkena ; memusnahkan semua ayama yang terinfeksi; melarang keluar masuk peralatan, orang dan kendaraan ke daerah peternakan yang terkena AI ; melakukan sanitasi(biosecurity) ketat; serta mengistirahatkan farm yang  terinfeksi.\n";
                    resultTextView.append("\n\n" + InfluenzaAction);
                    break;
                case "Infectious Bronchitis":
                    String BronchitisAction = "Penanganan: Penggobatan supportif dengan cara pemberian multivitamin atau campuran multivitamin dan elektrolit juga perlu dilakukan untuk mempercepat proses kesembuhan jaringan yang rusak akibat virus IB. \n";
                    resultTextView.append("\n\n" + BronchitisAction);
                    break;
                case "New Castle Disease":
                    String CastleAction = "Penanganan: Pencegahan penyakit ND hanya bisa dengan cara memberikan vaksinasi tetapi untuk penyembuhan penyakit ini belum ada obat.";
                    resultTextView.append("\n\n" + CastleAction);
                    break;
                case "Pullorum Disease":
                    String PullorumAction = "Penanganan: Pemberian Furazolidone pada anak ayam akan mengurangi kematian. Furazolidone diberikan melalui pakan dengan dosis 100 g/ton pakan yang diberikan selama 2 minggu.\n";
                    resultTextView.append("\n\n" + PullorumAction);
                    break;
                default:
                    break;
            }
        }
    }

    private String[] getSelectedItemsAsArray() {
        String[] selectedItemsArray = new String[selectedItems.size()];
        for (int i = 0; i < selectedItems.size(); i++) {
            selectedItemsArray[i] = selectedItems.get(i);
        }
        return selectedItemsArray;
    }
}