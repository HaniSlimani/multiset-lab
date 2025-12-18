package pobj.multiset;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MultiSetParser {

    public static MultiSet<String> parse(String fileName) throws InvalidMultiSetFormat {
        MultiSet<String> ms = new HashMultiSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {
                lineNumber++;
                line = line.trim();
                if (line.isEmpty()) continue;

                // Vérifie qu'il y a un ':'
                int colonIndex = line.indexOf(':');
                if (colonIndex == -1) {
                    throw new InvalidMultiSetFormat("Ligne " + lineNumber + " ne contient pas ':' : " + line);
                }

                String word = line.substring(0, colonIndex).trim();
                String countStr = line.substring(colonIndex + 1).trim();

                try {
                    int count = Integer.decode(countStr);
                    if (count <= 0) {
                        throw new InvalidMultiSetFormat("Ligne " + lineNumber + " : count <= 0 : " + line);
                    }
                    ms.add(word, count);
                } catch (NumberFormatException e) {
                    throw new InvalidMultiSetFormat("Ligne " + lineNumber + " : count non entier : " + line, e);
                }
            }
        } catch (IOException e) {
            throw new InvalidMultiSetFormat("Erreur lecture fichier : " + e.getMessage(), e);
        }

        return ms;
    }
}
