package pobj.multiset;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.List;

public class HashMultiSet<T> extends AbstractCollection<T> implements MultiSet<T> {

    private Map<T, Integer> map;
    private int size; // somme de toutes les occurrences

    // Constructeur vide
    public HashMultiSet() {
        map = new HashMap<>();
        size = 0;
    }

    // Constructeur de copie à partir d'une collection
    public HashMultiSet(Collection<T> collection) {
        this();
        for (T elem : collection) {
            add(elem);
        }
    }

    @Override
    public boolean add(T e, int count) {
        if (count <= 0) return false;
        map.put(e, map.getOrDefault(e, 0) + count);
        size += count;
        return true;
    }

    @Override
    public boolean add(T e) {
        return add(e, 1);
    }

    @Override
    public boolean remove(Object e) {
        return remove(e, 1);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean remove(Object e, int count) {
        if (count <= 0 || !map.containsKey(e)) return false;
        int currentCount = map.get((T) e);
        if (currentCount <= count) {
            map.remove(e);
            size -= currentCount;
        } else {
            map.put((T) e, currentCount - count);
            size -= count;
        }
        return true;
    }

    @Override
    public int count(T o) {
        return map.getOrDefault(o, 0);
    }

    @Override
    public void clear() {
        map.clear();
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    // Itérateur
    @Override
    public Iterator<T> iterator() {
        return new HashMultiSetIterator();
    }

    private class HashMultiSetIterator implements Iterator<T> {

        private Iterator<Map.Entry<T, Integer>> mapIter;
        private Map.Entry<T, Integer> currentEntry;
        private int remaining; // occurrences restantes

        public HashMultiSetIterator() {
            mapIter = map.entrySet().iterator();
            currentEntry = null;
            remaining = 0;
        }

        @Override
        public boolean hasNext() {
            return remaining > 0 || mapIter.hasNext();
        }

        @Override
        public T next() {
            if (remaining == 0) {
                if (!mapIter.hasNext()) {
                    throw new NoSuchElementException();
                }
                currentEntry = mapIter.next();
                remaining = currentEntry.getValue();
            }
            remaining--;
            return currentEntry.getKey();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove not supported by this iterator");
        }
        
    }
    @Override
    public List<T> elements() {
        return new ArrayList<>(map.keySet());
    }
}
