package com.example.aplikasipakarayam;

import java.util.HashMap;
import java.util.Map;

public class PenyakitModel {
    private static final String[] PENYAKIT = {
            "Gumboro",
            "Infectious Coryza",
            "Avian Influenza",
            "Infectious Bronchitis",
            "New Castle Disease",
            "Pullorum Disease"
    };

    private static final double[] PROBABILITAS_PENYAKIT = {
            0.3,
            0.4,
            0.3,
            0.3,
            0.4,
            0.3
    };

    private static final Map<String, Map<String, Double>> DATA_PENYAKIT = new HashMap<>();

    static {
        // Gumboro
        Map<String, Double> gumboroGejala = new HashMap<>();
        gumboroGejala.put("Ayam tampak lesu", 0.6);
        gumboroGejala.put("Sayap menggantung dan lemas", 0.6);
        gumboroGejala.put("Napsu makan menurun", 0.4);
        gumboroGejala.put("Tubuh ayam bergetar", 0.4);
        DATA_PENYAKIT.put("Gumboro", gumboroGejala);

        // Infectious Coryza
        Map<String, Double> coryzaGejala = new HashMap<>();
        coryzaGejala.put("Terjadi pembengkakan di daerah mata dan muka", 0.7);
        coryzaGejala.put("Produksi telur menurun", 0.7);
        coryzaGejala.put("Ngorok basah", 0.6);
        coryzaGejala.put("Keluar lendir pada hidung", 0.6);
        coryzaGejala.put("Napsu makan menurun", 0.4);
        DATA_PENYAKIT.put("Infectious Coryza", coryzaGejala);

        // Avian Influenza
        Map<String, Double> influenzaGejala = new HashMap<>();
        influenzaGejala.put("Ayam bersin", 0.8);
        influenzaGejala.put("Mata berair", 0.8);
        influenzaGejala.put("Sinusitis", 0.7);
        influenzaGejala.put("Ayam batuk", 0.4);
        influenzaGejala.put("Badan lemah", 0.4);
        influenzaGejala.put("Diare", 0.4);
        DATA_PENYAKIT.put("Avian Influenza", influenzaGejala);

        // Infectious Bronchitis
        Map<String, Double> bronchitisGejala = new HashMap<>();
        bronchitisGejala.put("Napas mengih-mengih", 0.8);
        bronchitisGejala.put("Leleran di hidung", 0.8);
        bronchitisGejala.put("Mata berair", 0.8);
        bronchitisGejala.put("Ayam bersin", 0.8);
        bronchitisGejala.put("Pembengkakan di sinus", 0.7);
        bronchitisGejala.put("Ngorok basah", 0.6);
        bronchitisGejala.put("Ayam batuk", 0.4);
        bronchitisGejala.put("Lesu", 0.4);
        bronchitisGejala.put("Napsu makan & Berat badan turun drastis", 0.4);
        DATA_PENYAKIT.put("Infectious Bronchitis", bronchitisGejala);

        // New Castle Disease
        Map<String, Double> newCastleGejala = new HashMap<>();
        newCastleGejala.put("Bulu tampak jatuh kebawah", 0.8);
        newCastleGejala.put("Ayam mengap-mengap", 0.8);
        newCastleGejala.put("Ayam bersin", 0.8);
        newCastleGejala.put("Ayam batuk", 0.8);
        newCastleGejala.put("Badan ayam bergetar", 0.6);
        newCastleGejala.put("Ngorok basah", 0.6);
        newCastleGejala.put("Ayam tampak lesu", 0.4);
        newCastleGejala.put("Jengger berwarna keabuan", 0.4);
        newCastleGejala.put("Napsu makan menurun", 0.4);
        newCastleGejala.put("Diare Kehijauan", 0.4);
        DATA_PENYAKIT.put("New Castle Disease", newCastleGejala);

        // Pullorum Disease
        Map<String, Double> pullorumGejala = new HashMap<>();
        pullorumGejala.put("Sayap menggantung dan lemas", 0.7);
        pullorumGejala.put("Kotoran berwarna putih", 0.6);
        pullorumGejala.put("Lumpuh karena arthritis", 0.5);
        pullorumGejala.put("Napsu makan pada ayam menurun", 0.4);
        pullorumGejala.put("Jengger berwarna keabuan", 0.4);
        pullorumGejala.put("Ayam tampak lesu", 0.4);
        DATA_PENYAKIT.put("Pullorum Disease", pullorumGejala);
    }

    public String deteksiPenyakit(String[] gejalaTerpilih) {
        StringBuilder hasilDeteksi = new StringBuilder();

        double[] probabilitasPenyakit = new double[PENYAKIT.length];
        double totalProbabilitasGejala = 0.0;

        for (int i = 0; i < PENYAKIT.length; i++) {
            String penyakit = PENYAKIT[i];
            double probabilitasPenyakitAwal = PROBABILITAS_PENYAKIT[i];
            Map<String, Double> gejalaPenyakit = DATA_PENYAKIT.get(penyakit);

            double probabilitasGejala = 1.0;
            for (String gejala : gejalaTerpilih) {
                if (gejalaPenyakit.containsKey(gejala)) {
                    double probabilitas = gejalaPenyakit.get(gejala);
                    probabilitasGejala *= probabilitas;
                } else {
                    probabilitasGejala *= 0.1; // Faktor ketidaktahuan
                }
            }

            probabilitasPenyakit[i] = probabilitasPenyakitAwal * probabilitasGejala;
            totalProbabilitasGejala += probabilitasPenyakit[i];
        }

        if (totalProbabilitasGejala != 0) {
            for (int i = 0; i < PENYAKIT.length; i++) {
                String penyakit = PENYAKIT[i];
                double probabilitas = probabilitasPenyakit[i] / totalProbabilitasGejala;

                hasilDeteksi.append(penyakit).append(": ").append(probabilitas * 100).append("%\n");
            }
        }

        return hasilDeteksi.toString();
    }
}