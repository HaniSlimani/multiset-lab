package pobj.multiset;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class NaiveMultiSet<T> extends AbstractCollection<T> implements MultiSet<T> {

    private ArrayList<T> list;

    public NaiveMultiSet() {
        list = new ArrayList<>();
    }

    @Override
    public boolean add(T e, int count) {
        if (count <= 0) return false;
        for (int i = 0; i < count; i++) {
            list.add(e);
        }
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

    @Override
    public boolean remove(Object e, int count) {
        int removed = 0;
        for (int i = 0; i < count; i++) {
            if (list.remove(e)) {
                removed++;
            } else {
                break;
            }
        }
        return removed > 0;
    }

    @Override
    public int count(T o) {
        int c = 0;
        for (T x : list) {
            if (x.equals(o)) c++;
        }
        return c;
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

    @Override
    public List<T> elements() {
        return new ArrayList<>(new HashSet<>(list));
    }
}
