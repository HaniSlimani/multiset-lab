package pobj.multiset;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class WordCount {

    public static void wordcount(MultiSet<String> ms, String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = br.readLine()) != null) {
            for (String word : line.split("\\P{L}+")) {
                if (word.equals("")) continue;
                ms.add(word.toLowerCase());
            }
        }
        br.close();

        // >>> AJOUT POUR LA QUESTION 5.3 <<<
        System.out.println(ms);

        List<String> words = ms.elements();

        Collections.sort(words, new Comparator<String>() {
            @Override
            public int compare(String w1, String w2) {
                return Integer.compare(ms.count(w2), ms.count(w1));
            }
        });

        System.out.println("Top 10 mots les plus fréquents :");
        for (int i = 0; i < Math.min(10, words.size()); i++) {
            String w = words.get(i);
            System.out.println(w + " : " + ms.count(w));
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage : java WordCount <fichier.txt>");
            return;
        }
        String filename = args[0];

        System.out.println("=== HashMultiSet ===");
        MultiSet<String> hashMs = new MultiSetDecorator<>(new HashMultiSet<>());        long start = System.currentTimeMillis();
        try {
            wordcount(hashMs, filename);
        } catch (IOException e) {
            System.err.println("Erreur lecture fichier : " + e.getMessage());
        }
        long end = System.currentTimeMillis();
        System.out.println("temps écoulé : " + (end - start) + " millisecondes\n");

        /*
        System.out.println("=== NaiveMultiSet ===");
        MultiSet<String> naiveMs = new MultiSetDecorator<>(new NaiveMultiSet<>());
        start = System.currentTimeMillis();
        try {
            wordcount(naiveMs, filename);
        } catch (IOException e) {
            System.err.println("Erreur lecture fichier : " + e.getMessage());
        }
        end = System.currentTimeMillis();
        System.out.println("temps écoulé : " + (end - start) + " millisecondes");
        */ 
    }
}

