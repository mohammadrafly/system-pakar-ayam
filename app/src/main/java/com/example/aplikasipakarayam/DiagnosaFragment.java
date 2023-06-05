package com.example.aplikasipakarayam;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.aplikasipakarayam.BayesianModel;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

public class DiagnosaFragment extends Fragment {
    private TextView resultTextView;
    private BayesianModel model;
    private List<String> selectedItems;
    private ChipGroup chipGroup;

    public DiagnosaFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diagnosa, container, false);

        model = new BayesianModel();

        Button predictButton = view.findViewById(R.id.predictButton);
        resultTextView = view.findViewById(R.id.resultTextView);
        chipGroup = view.findViewById(R.id.chipGroup);
        selectedItems = new ArrayList<>();

        predictButton.setOnClickListener(v -> {
            String symptoms = getSelectedItemsAsString();
            String prediction = model.predictDisease(symptoms);
            resultTextView.setText(prediction);
        });

        AutoCompleteTextView autoCompleteTextView = view.findViewById(R.id.autoCompleteTextView);
        String[] items = {"Ayam tampak lesu",
                "Sayap menggantung dan lemas",
                "Napsu makan menurun",
                "Tubuh ayam bergetar",
                "Terjadi pembengkakan di daerah mata dan muka",
                "Produksi telur menurun",
                "Ngorok basah",
                "Keluar lendir pada hidung",
                "Ayam bersin",
                "Mata berair",
                "Sinusitis",
                "Ayam batuk",
                "Badan lemah",
                "Diare",
                "Napas mengih-mengih",
                "Leleran di hidung",
                "Pembengkakan di sinus",
                "Lesu",
                "Napsu makan & Berat badan turun drastis",
                "Badan ayam bergetar",
                "Bulu tampak jatuh kebawah",
                "Ayam mengap-mengap",
                "Jengger berwarna keabuan",
                "Kotoran berwarna putih",
                "Lumpuh karena aritis"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line, items);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setOnItemClickListener((parent, view1, position, id) -> {
            String selectedItem = (String) parent.getItemAtPosition(position);
            addChip(selectedItem);
            autoCompleteTextView.setText("");
        });

        return view;
    }

    private void addChip(String text) {
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

    private String getSelectedItemsAsString() {
        StringBuilder sb = new StringBuilder();
        for (String item : selectedItems) {
            sb.append(item).append(", ");
        }
        if (sb.length() > 2) {
            sb.delete(sb.length() - 2, sb.length());
        }
        return sb.toString();
    }
}