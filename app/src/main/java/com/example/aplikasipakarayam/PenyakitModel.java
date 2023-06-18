package com.example.aplikasipakarayam;

import java.util.HashMap;
import java.util.Map;

public class PenyakitModel {
    // Array yang menyimpan nama-nama penyakit
    private static final String[] PENYAKIT = {
            "Gumboro",
            "Infectious Coryza",
            "Avian Influenza",
            "Infectious Bronchitis",
            "New Castle Disease",
            "Pullorum Disease"
    };

    // Array yang menyimpan probabilitas masing-masing penyakit
    private static final double[] PROBABILITAS_PENYAKIT = {
            0.3,
            0.4,
            0.3,
            0.3,
            0.4,
            0.3
    };

    // HashMap yang menyimpan data gejala-gejala penyakit
    private static final Map<String, Map<String, Double>> DATA_PENYAKIT = new HashMap<>();

    //hashMap atau peta penyakit dengan gejala dan angka probabilitasnya
    static {
        // Gumboro
        Map<String, Double> gumboroGejala = new HashMap<>();
        gumboroGejala.put("ayam tampak lesu", 0.6);
        gumboroGejala.put("sayap menggantung dan lemas", 0.6);
        gumboroGejala.put("napsu makan menurun", 0.4);
        gumboroGejala.put("tubuh ayam bergetar", 0.4);
        DATA_PENYAKIT.put("Gumboro", gumboroGejala);

        // Infectious Coryza
        Map<String, Double> coryzaGejala = new HashMap<>();
        coryzaGejala.put("terjadi pembengkakan di daerah mata dan muka", 0.7);
        coryzaGejala.put("produksi telur menurun", 0.4);
        coryzaGejala.put("ngorok basah", 0.6);
        coryzaGejala.put("keluar lendir pada hidung", 0.6);
        coryzaGejala.put("napsu makan menurun", 0.4);
        DATA_PENYAKIT.put("Infectious Coryza", coryzaGejala);

        // Avian Influenza
        Map<String, Double> influenzaGejala = new HashMap<>();
        influenzaGejala.put("ayam bersin", 0.8);
        influenzaGejala.put("mata berair", 0.8);
        influenzaGejala.put("sinusitis", 0.7);
        influenzaGejala.put("ayam batuk", 0.4);
        influenzaGejala.put("badan lemah", 0.4);
        influenzaGejala.put("diare", 0.4);
        DATA_PENYAKIT.put("Avian Influenza", influenzaGejala);

        // Infectious Bronchitis
        Map<String, Double> bronchitisGejala = new HashMap<>();
        bronchitisGejala.put("napas mengih-mengih", 0.8);
        bronchitisGejala.put("leleran di hidung", 0.8);
        bronchitisGejala.put("mata berair", 0.8);
        bronchitisGejala.put("ayam bersin", 0.8);
        bronchitisGejala.put("pembengkakan di sinus", 0.7);
        bronchitisGejala.put("ngorok basah", 0.6);
        bronchitisGejala.put("ayam batuk", 0.4);
        bronchitisGejala.put("lesu", 0.4);
        bronchitisGejala.put("napsu makan dan berat badan turun drastis", 0.4);
        DATA_PENYAKIT.put("Infectious Bronchitis", bronchitisGejala);

        // New Castle Disease
        Map<String, Double> newCastleGejala = new HashMap<>();
        newCastleGejala.put("bulu tampak jatuh kebawah", 0.8);
        newCastleGejala.put("ayam mengap-mengap", 0.8);
        newCastleGejala.put("ayam bersin", 0.8);
        newCastleGejala.put("ayam batuk", 0.8);
        newCastleGejala.put("badan ayam bergetar", 0.6);
        newCastleGejala.put("ngorok basah", 0.6);
        newCastleGejala.put("ayam tampak lesu", 0.4);
        newCastleGejala.put("jengger berwarna keabuan", 0.4);
        newCastleGejala.put("napsu makan menurun", 0.4);
        newCastleGejala.put("diare kehijauan", 0.4);
        DATA_PENYAKIT.put("New Castle Disease", newCastleGejala);

        // Pullorum Disease
        Map<String, Double> pullorumGejala = new HashMap<>();
        pullorumGejala.put("sayap menggantung dan lemas", 0.7);
        pullorumGejala.put("kotoran berwarna putih", 0.6);
        pullorumGejala.put("lumpuh karena artritis", 0.5);
        pullorumGejala.put("napsu makan pada ayam menurun", 0.4);
        pullorumGejala.put("jengger berwarna keabuan", 0.4);
        pullorumGejala.put("ayam tampak lesu", 0.4);
        DATA_PENYAKIT.put("Pullorum Disease", pullorumGejala);
    }

    /**
     * Mendeteksi penyakit berdasarkan gejala yang terpilih.
     * @param gejalaTerpilih Array yang berisi gejala yang terpilih.
     * @return String yang berisi hasil deteksi penyakit beserta probabilitasnya.
     */
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
                }
            }

            probabilitasPenyakit[i] = probabilitasPenyakitAwal * probabilitasGejala;
            totalProbabilitasGejala += probabilitasPenyakit[i];
        }

        for (int i = 0; i < PENYAKIT.length; i++) {
            String penyakit = PENYAKIT[i];
            double probabilitas = totalProbabilitasGejala > 0 ? probabilitasPenyakit[i] / totalProbabilitasGejala : 0.0;

            hasilDeteksi.append(penyakit).append(": ").append(probabilitas * 100).append("%\n");
        }

        return hasilDeteksi.toString();
    }
}