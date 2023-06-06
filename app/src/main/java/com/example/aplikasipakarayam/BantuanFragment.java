package com.example.aplikasipakarayam;

import android.os.Bundle;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AlignmentSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class BantuanFragment extends Fragment {
    private TextView descriptionTextView;
    public BantuanFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bantuan, container, false);

        descriptionTextView = view.findViewById(R.id.descriptionTextView);
        String description = "Sistem ini merupakan sebuah perangkat lunak yang digunakan untuk memecahkan masalah yang biasanya diselesaikan oleh para pakar. Cara kerja dari Aplikasi ini adalah peternak terlebih dahulu mengetahui gejala apa yang dialami oleh ayam, setelah itu peternak dapat memilih gejala yang terdapat di aplikasi (pilihlah sesuai dengan gejala yang dirasa benar), setelah cukup yakin maka peternak dapat melakukan atau klik tombol diagnosa pada aplikasi, maka selanjutnya sistem akan melakukan perhitungan menggunakan metode Teorema Bayes melalui gejala yang dipilih dan hasil analisa penyakit yang diderita ayam akan muncul sesuai dengan pilihan gejala peternak.\n\nDalam aplikasi ini juga memiliki solusi atau cara pencegahan pada ayam yang terkena penyakit, sehingga mempermudah peternak dalam penanganan penyakit ayam. Apabila selama 3 hari penyakit tidak kunjung sembuh atau pulih, segera hubungi dokter hewan.";
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(description);
        spannableStringBuilder.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_NORMAL), 0, description.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        descriptionTextView.setText(spannableStringBuilder);

        return view;

    }
}
