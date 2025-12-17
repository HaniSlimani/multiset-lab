package pobj.multiset;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class WordCount {

    public static void wordcount(MultiSet<String> ms, String filename) throws IOException {
        // Lecture du fichier et remplissage du multi-ensemble
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = br.readLine()) != null) {
            for (String word : line.split("\\P{L}+")) { // séparer par tout ce qui n'est pas une lettre
                if (word.equals("")) continue; 
                ms.add(word.toLowerCase()); // mettre en minuscule pour uniformiser
            }
        }
        br.close();

        // Récupération des éléments uniques
        List<String> words = ms.elements();

        // Tri par fréquence décroissante
        Collections.sort(words, new Comparator<String>() {
            @Override
            public int compare(String w1, String w2) {
                return Integer.compare(ms.count(w2), ms.count(w1));
            }
        });

        // Affichage des 10 premiers mots les plus fréquents
        System.out.println("Top 10 mots les plus fréquents :");
        for (int i = 0; i < Math.min(10, words.size()); i++) {
            String w = words.get(i);
            System.out.println(w + " : " + ms.count(w));
        }
    }

    // Méthode main pour exécuter l'application
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage : java WordCount <fichier.txt>");
            return;
        }
        String filename = args[0];
        MultiSet<String> ms = new HashMultiSet<>();
        try {
            wordcount(ms, filename);
        } catch (IOException e) {
            System.err.println("Erreur lecture fichier : " + e.getMessage());
        }
    }
}
