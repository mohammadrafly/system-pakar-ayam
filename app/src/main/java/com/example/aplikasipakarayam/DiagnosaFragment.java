package com.example.aplikasipakarayam;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
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
    private TextView resultTextView;  // TextView untuk menampilkan hasil
    private PenyakitModel model;  // Instance dari kelas PenyakitModel
    private List<String> selectedItems;  // List untuk menyimpan item yang dipilih
    private ChipGroup chipGroup;  // ChipGroup untuk menampilkan item yang dipilih sebagai chip
    private ArrayAdapter<String> adapter;  // ArrayAdapter untuk ListView
    private ProgressBar progressBar;  // ProgressBar untuk menampilkan progres
    private Button predictButton;

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

        container = view.findViewById(R.id.linearLayoutGejala);
        ViewGroup finalContainerGejala = container;
        diseaseListView.setOnItemClickListener((parent, view1, position, id) -> {
            finalContainerGejala.setVisibility(View.VISIBLE);
            String selectedItem = itemsList[position];
            addChip(selectedItem);
        });

        predictButton = view.findViewById(R.id.predictButton);
        resultTextView = view.findViewById(R.id.resultTextView);
        chipGroup = view.findViewById(R.id.chipGroup);
        selectedItems = new ArrayList<>();

        progressBar = view.findViewById(R.id.progressBar);
        container = view.findViewById(R.id.resultTextViewContainer);
        ViewGroup finalContainer = container;
        predictButton.setOnClickListener(v -> {
            showSpinner();
            finalContainer.setVisibility(View.VISIBLE);
            resultTextView.setText("Sedang diproses..");
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
        chip.setCloseIconContentDescription("Hapus");
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

                // Menghapus simbol persentase dari string
                String percentageWithoutSymbol = percentage.replace("%", "");

                try {
                    double currentPercentage = Double.parseDouble(percentageWithoutSymbol);
                    if (currentPercentage > highestPercentage) {
                        highestPercentage = currentPercentage;
                        highestDisease = diseaseName;
                    }

                    // Memformat string persentase
                    String formattedPercentage = String.format("%.2f", currentPercentage);

                    formattedPrediction.append(diseaseName).append(": ").append(formattedPercentage).append("%\n");
                } catch (NumberFormatException e) {
                    // Menghandle kesalahan parsing di sini
                    e.printStackTrace();
                }
            }
        }

        // Menampilkan hasil prediksi yang diformat
        String formattedPredictionString = formattedPrediction.toString();
        Log.d("Prediksi", prediction);
        if (!formattedPredictionString.isEmpty()) {
            resultTextView.setText(formattedPredictionString);
        } else {
            resultTextView.setText("Prediksi tidak tersedia");
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
                    String coryzaAction = "Penanganan: Pengobatan dapat dilakukan dengan menggunakan beberapa preparat sulfanamides dan antibiotik yang diberikan melalui pakan atau air minum usahakan melakukan pengobatan ini jangan sampai berhenti karena dikhawatirkan ayam akan kambuh lagi. Beberapa obat dan antibiotik yang digunakan streptomycin, Sulfadimethoxine, dan tylosin.";
                    resultTextView.append("\n\n" + coryzaAction);
                    break;
                case "Avian Influenza":
                    String influenzaAction = "Penanganan: Cara menanggulangi penyakit ini dengan cara pemberian antibiotik, multivitamin, melaksanakan vaksisnasi; mengisolasi farm atau peternakan yang terkena; memusnahkan semua ayam yang terinfeksi; melarang keluar masuk peralatan, orang, dan kendaraan ke daerah peternakan yang terkena AI; melakukan sanitasi (biosecurity) ketat; serta mengistirahatkan farm yang terinfeksi.\n";
                    resultTextView.append("\n\n" + influenzaAction);
                    break;
                case "Infectious Bronchitis":
                    String bronchitisAction = "Penanganan: Penggobatan supportif dengan cara pemberian multivitamin atau campuran multivitamin dan elektrolit juga perlu dilakukan untuk mempercepat proses kesembuhan jaringan yang rusak akibat virus IB.\n";
                    resultTextView.append("\n\n" + bronchitisAction);
                    break;
                case "New Castle Disease":
                    String castleAction = "Penanganan: Pencegahan penyakit ND hanya bisa dilakukan dengan cara memberikan vaksinasi, tetapi untuk penyembuhan penyakit ini belum ada obat.";
                    resultTextView.append("\n\n" + castleAction);
                    break;
                case "Pullorum Disease":
                    String pullorumAction = "Penanganan: Pemberian Furazolidone pada anak ayam akan mengurangi kematian. Furazolidone diberikan melalui pakan dengan dosis 100 g/ton pakan yang diberikan selama 2 minggu.\n";
                    resultTextView.append("\n\n" + pullorumAction);
                    break;
                default:
                    break;
            }
        }
    }

    private String[] getSelectedItemsAsArray() {
        String[] itemsArray = new String[selectedItems.size()];
        itemsArray = selectedItems.toArray(itemsArray);
        return itemsArray;
    }
}