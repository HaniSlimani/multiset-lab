package pobj.multiset;

import java.util.Iterator;
import java.util.List;

public class MultiSetDecorator<T> implements MultiSet<T> {

    private MultiSet<T> decorated;

    public MultiSetDecorator(MultiSet<T> decorated) {
        this.decorated = decorated;
    }

    // Méthodes add
    @Override
    public boolean add(T e, int count) {
        boolean result = decorated.add(e, count);
        checkConsistency();
        return result;
    }

    @Override
    public boolean add(T e) {
        boolean result = decorated.add(e);
        checkConsistency();
        return result;
    }

    // Méthodes remove
    @Override
    public boolean remove(Object e) {
        boolean result = decorated.remove(e);
        checkConsistency();
        return result;
    }

    @Override
    public boolean remove(Object e, int count) {
        boolean result = decorated.remove(e, count);
        checkConsistency();
        return result;
    }

    @Override
    public int count(T o) {
        return decorated.count(o);
    }

    @Override
    public void clear() {
        decorated.clear();
        checkConsistency();
    }

    @Override
    public int size() {
        return decorated.size();
    }

    @Override
    public List<T> elements() {
        return decorated.elements();
    }

    @Override
    public Iterator<T> iterator() {
        return decorated.iterator();
    }

    @Override
    public String toString() {
        return decorated.toString();
    }

    // Vérifie la cohérence si le multi-ensemble décoré est un HashMultiSet
    private void checkConsistency() {
        if (decorated instanceof HashMultiSet) {
            HashMultiSet<?> h = (HashMultiSet<?>) decorated;
            if (!h.isConsistent()) {
                throw new InternalError("MultiSet incohérent !");
            }
        }
        // On peut aussi ajouter un support pour NaiveMultiSet si nécessaire
    }
}
