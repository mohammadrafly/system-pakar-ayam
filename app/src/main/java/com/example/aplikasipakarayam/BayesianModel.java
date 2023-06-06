package com.example.aplikasipakarayam;

import java.util.HashMap;
import java.util.Map;

public class BayesianModel {
    private Map<String, Double> priorProbabilities;
    private Map<String, String> handlingSteps;
    private Map<String, Map<String, Double>> conditionalProbabilities;
    private double defaultConditionalProbability;
    private double defaultPriorProbability;
    public BayesianModel() {
        priorProbabilities = new HashMap<>();
        conditionalProbabilities = new HashMap<>();
        defaultConditionalProbability = 0.01;
        defaultPriorProbability = 0.0;

        priorProbabilities.put("Penyakit Gumboro", 0.3);
        priorProbabilities.put("Infectious Coryza", 0.3);
        priorProbabilities.put("Avian Influenza", 0.3);
        priorProbabilities.put("Infectious Bronchitis", 0.3);
        priorProbabilities.put("New Castle Disease", 0.3);
        priorProbabilities.put("Pullorum Disease", 0.3);

        conditionalProbabilities.put("Gumboro", createGumboroConditionalProbabilities());
        conditionalProbabilities.put("Infectious Coryza", createCoryzaConditionalProbabilities());
        conditionalProbabilities.put("Avian Influenza", createInfluenzaConditionalProbabilities());
        conditionalProbabilities.put("Infectious Bronchitis", createBronchitisConditionalProbabilities());
        conditionalProbabilities.put("New Castle Disease", createNewCastleConditionalProbabilities());
        conditionalProbabilities.put("Pullorum Disease", createPullorumConditionalProbabilities());

        handlingSteps = new HashMap<>();
        handlingSteps.put("Gumboro", "Langkah-langkah penanganan Gumboro:\n- Langkah 1\n- Langkah 2\n- Langkah 3");
        handlingSteps.put("Infectious Coryza", "Langkah-langkah penanganan Infectious Coryza:\n- Langkah 1\n- Langkah 2\n- Langkah 3");
        handlingSteps.put("Avian Influenza", "Langkah-langkah penanganan Avian Influenza:\n- Langkah 1\n- Langkah 2\n- Langkah 3");
        handlingSteps.put("Infectious Bronchitis", "Langkah-langkah penanganan Infectious Bronchitis:\n- Langkah 1\n- Langkah 2\n- Langkah 3");
        handlingSteps.put("New Castle Disease", "Langkah-langkah penanganan New Castle Disease:\n- Langkah 1\n- Langkah 2\n- Langkah 3");
        handlingSteps.put("Pullorum Disease", "Langkah-langkah penanganan Pullorum Disease:\n- Langkah 1\n- Langkah 2\n- Langkah 3");
    }

    private Map<String, Double> createGumboroConditionalProbabilities() {
        Map<String, Double> conditionalProbGumboro = new HashMap<>();
        conditionalProbGumboro.put("Ayam tampak lesu", 0.6);
        conditionalProbGumboro.put("Sayap menggantung dan lemas", 0.6);
        conditionalProbGumboro.put("Napsu makan menurun", 0.4);
        conditionalProbGumboro.put("Tubuh ayam bergetar", 0.4);
        return conditionalProbGumboro;
    }

    private Map<String, Double> createCoryzaConditionalProbabilities() {
        Map<String, Double> conditionalProbCoryza = new HashMap<>();
        conditionalProbCoryza.put("Terjadi pembengkakan di daerah mata dan muka", 0.7);
        conditionalProbCoryza.put("Produksi telur menurun", 0.7);
        conditionalProbCoryza.put("Ngorok basah", 0.6);
        conditionalProbCoryza.put("Keluar lendir pada hidung", 0.6);
        conditionalProbCoryza.put("Napsu makan menurun", 0.4);
        return conditionalProbCoryza;
    }

    private Map<String, Double> createInfluenzaConditionalProbabilities() {
        Map<String, Double> conditionalProbInfluenza = new HashMap<>();
        conditionalProbInfluenza.put("Ayam bersin", 0.8);
        conditionalProbInfluenza.put("Mata berair", 0.8);
        conditionalProbInfluenza.put("Sinusitis", 0.7);
        conditionalProbInfluenza.put("Ayam batuk", 0.4);
        conditionalProbInfluenza.put("Badan lemah", 0.4);
        conditionalProbInfluenza.put("Diare", 0.4);
        return conditionalProbInfluenza;
    }

    private Map<String, Double> createBronchitisConditionalProbabilities() {
        Map<String, Double> conditionalProbBronchitis = new HashMap<>();
        conditionalProbBronchitis.put("Napas mengih-mengih", 0.8);
        conditionalProbBronchitis.put("Leleran di hidung", 0.8);
        conditionalProbBronchitis.put("Mata berair", 0.8);
        conditionalProbBronchitis.put("Ayam bersin", 0.8);
        conditionalProbBronchitis.put("Pembengkakan di sinus", 0.7);
        conditionalProbBronchitis.put("Ngorok basah", 0.6);
        conditionalProbBronchitis.put("Ayam batuk", 0.4);
        conditionalProbBronchitis.put("Lesu", 0.4);
        conditionalProbBronchitis.put("Napsu makan & Berat badan turun drastis", 0.4);
        return conditionalProbBronchitis;
    }

    private Map<String, Double> createNewCastleConditionalProbabilities() {
        Map<String, Double> conditionalProbNewCastle = new HashMap<>();
        conditionalProbNewCastle.put("Bulu tampak jatuh kebawah", 0.8);
        conditionalProbNewCastle.put("Ayam mengap-mengap", 0.8);
        conditionalProbNewCastle.put("Ayam bersin", 0.8);
        conditionalProbNewCastle.put("Ayam batuk", 0.8);
        conditionalProbNewCastle.put("Badan ayam bergetar", 0.6);
        conditionalProbNewCastle.put("Ngorok basah", 0.6);
        conditionalProbNewCastle.put("Ayam tampak lesu", 0.4);
        conditionalProbNewCastle.put("Jengger berwarna keabuan", 0.4);
        conditionalProbNewCastle.put("Napsu makan menurun", 0.4);
        conditionalProbNewCastle.put("Diare Kehijauan", 0.4);
        return conditionalProbNewCastle;
    }

    private Map<String, Double> createPullorumConditionalProbabilities() {
        Map<String, Double> conditionalProbPullorum = new HashMap<>();
        conditionalProbPullorum.put("Sayap menggantung lemas", 0.7);
        conditionalProbPullorum.put("Kotoran berwarna putih", 0.6);
        conditionalProbPullorum.put("Lumpuh karena aritis", 0.5);
        conditionalProbPullorum.put("Napsu makan pada ayam menurun", 0.4);
        conditionalProbPullorum.put("Jengger berwarna keabuan", 0.4);
        conditionalProbPullorum.put("Ayam tampak lesu", 0.4);
        return conditionalProbPullorum;
    }

    // Helper method to calculate the likelihood of symptoms given a disease
    private double calculateLikelihood(String[] observedSymptoms, String disease,
                                       Map<String, Map<String, Double>> conditionalProbabilities,
                                       double defaultConditionalProbability) {
        double likelihood = 1.0;

        Map<String, Double> conditionalProb = conditionalProbabilities.getOrDefault(disease, new HashMap<>());

        for (String symptom : observedSymptoms) {
            double symptomProbability = conditionalProb.getOrDefault(symptom, defaultConditionalProbability);
            likelihood *= symptomProbability;
        }

        return likelihood;
    }

    // Helper method to calculate the posterior probability
    private double calculatePosterior(double posterior, double evidenceProbability) {
        return (posterior / evidenceProbability) * 100.0;
    }

    public String predictDisease(String symptoms) {
        String[] observedSymptoms = symptoms.split(",");

        Map<String, Double> posteriorProbabilities = new HashMap<>();
        double evidenceProbability = 0.0;

        for (String disease : priorProbabilities.keySet()) {
            double prior = priorProbabilities.getOrDefault(disease, defaultPriorProbability);
            double likelihood = calculateLikelihood(observedSymptoms, disease, conditionalProbabilities, defaultConditionalProbability);

            double posterior = prior * likelihood;
            posteriorProbabilities.put(disease, posterior);
            evidenceProbability += posterior;
        }

        for (String disease : posteriorProbabilities.keySet()) {
            double posterior = calculatePosterior(posteriorProbabilities.get(disease), evidenceProbability);
            posteriorProbabilities.put(disease, posterior);
        }

        String predictedDisease = "";
        double maxProbability = 0.0;

        for (String disease : posteriorProbabilities.keySet()) {
            double probability = posteriorProbabilities.get(disease);

            if (probability > maxProbability) {
                maxProbability = probability;
                predictedDisease = disease;
            }
        }

        if (maxProbability == 0.0) {
            return "No prediction";
        }

        String handlingSteps = "";
        switch (predictedDisease) {
            case "Gumboro":
                handlingSteps = "Langkah-langkah penanganan Gumboro:\n"
                        + "- Lakukan vaksinasi yang tepat sesuai jadwal.\n"
                        + "- Lakukan pemisahan ayam yang terinfeksi untuk mencegah penyebaran penyakit.\n"
                        + "- Berikan makanan dan minuman yang berkualitas tinggi untuk meningkatkan kekebalan ayam.\n"
                        + "- Bersihkan dan disinfeksi kandang secara rutin.";
                break;
            case "Infectious Coryza":
                handlingSteps = "Langkah-langkah penanganan Infectious Coryza:\n"
                        + "- Isolasi ayam yang terinfeksi untuk mencegah penyebaran penyakit.\n"
                        + "- Berikan antibiotik yang diresepkan oleh dokter hewan.\n"
                        + "- Bersihkan dan disinfeksi kandang secara rutin.\n"
                        + "- Lindungi ayam dari cuaca yang ekstrem dan suhu yang fluktuatif.";
                break;
            case "Avian Influenza":
                handlingSteps = "Langkah-langkah penanganan Avian Influenza:\n"
                        + "- Laporkan penyakit ini kepada otoritas kesehatan hewan setempat.\n"
                        + "- Lakukan pemusnahan ayam yang terinfeksi.\n"
                        + "- Tingkatkan kebersihan dan biosecurity pada peternakan.\n"
                        + "- Batasi akses ayam terhadap kontak dengan unggas liar atau burung liar.";
                break;
            case "Infectious Bronchitis":
                handlingSteps = "Langkah-langkah penanganan Infectious Bronchitis:\n"
                        + "- Lakukan vaksinasi yang tepat sesuai jadwal.\n"
                        + "- Isolasi ayam yang terinfeksi untuk mencegah penyebaran penyakit.\n"
                        + "- Berikan makanan dan minuman yang berkualitas tinggi untuk meningkatkan kekebalan ayam.\n"
                        + "- Bersihkan dan disinfeksi kandang secara rutin.";
                break;
            case "New Castle Disease":
                handlingSteps = "Langkah-langkah penanganan New Castle Disease:\n"
                        + "- Lakukan vaksinasi yang tepat sesuai jadwal.\n"
                        + "- Laporkan penyakit ini kepada otoritas kesehatan hewan setempat.\n"
                        + "- Lakukan pemisahan ayam yang terinfeksi untuk mencegah penyebaran penyakit.\n"
                        + "- Tingkatkan biosecurity pada peternakan.";
                break;
            case "Pullorum Disease":
                handlingSteps = "Langkah-langkah penanganan Pullorum Disease:\n"
                        + "- Lakukan vaksinasi yang tepat sesuai jadwal.\n"
                        + "- Bersihkan dan disinfeksi kandang secara rutin.\n"
                        + "- Buang dan hancurkan telur yang terinfeksi.\n"
                        + "- Hindari kontak langsung dengan ayam yang terinfeksi.";
                break;
            default:
                handlingSteps = "Tidak ada langkah-langkah penanganan yang tersedia untuk penyakit ini.";
                break;
        }
        return predictedDisease + "Probability: " + maxProbability + "%" + "\n\n" + handlingSteps;
    }
}