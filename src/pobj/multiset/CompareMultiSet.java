package pobj.multiset;

import pobj.util.Chrono;

public class CompareMultiSet {

    public static void main(String[] args) throws Exception {

        String file = "data/WarAndPeace.txt";

        System.out.println("=== HashMultiSet ===");
        MultiSet<String> hash = new HashMultiSet<>();
        Chrono chrono1 = new Chrono();
        WordCount.wordcount(hash, file);
        chrono1.stop();

        System.out.println("\n=== NaiveMultiSet ===");
        MultiSet<String> naive = new NaiveMultiSet<>();
        Chrono chrono2 = new Chrono();
        WordCount.wordcount(naive, file);
        chrono2.stop();
    }
}
