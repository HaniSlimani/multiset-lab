package pobj.multiset.test;

import pobj.multiset.*;

public class MultiSetParserTest {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage : java MultiSetParserTest <fichier.txt>");
            return;
        }

        String filename = args[0];

        try {
            MultiSet<String> ms = MultiSetParser.parse(filename);
            System.out.println("Contenu du multi-ensemble :");
            System.out.println(ms);
        } catch (InvalidMultiSetFormat e) {
            System.err.println("Erreur : " + e.getMessage());
        }
    }
}
