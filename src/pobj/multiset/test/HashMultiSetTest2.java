package pobj.multiset.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pobj.multiset.HashMultiSet;
import pobj.multiset.*;



public class HashMultiSetTest2 {

    private MultiSet<String> m;

    @BeforeEach
    public void setUp() {
        m = new MultiSetDecorator<>(new HashMultiSet<>());
    }

    // ===== Tests add =====
    @Test
    public void testAddSimple() {
        m.add("a");
        m.add("a", 5);
        assertEquals(6, m.count("a"));
    }

    @Test
    public void testAddIllegalArgument() {
        assertThrows(IllegalArgumentException.class,
            () -> m.add("a", -1));
    }

    @Test
    public void testAddZero() {
        assertFalse(m.add("b", 0));
        assertEquals(0, m.count("b"));
    }

    // ===== Tests remove =====
    @Test
    public void testRemoveSimple() {
        m.add("a", 3);
        m.add("b", 2);
        assertTrue(m.remove("a"));       // supprime 1 occurrence
        assertEquals(2, m.count("a"));
        assertTrue(m.remove("b", 2));    // supprime toutes les occurrences
        assertEquals(0, m.count("b"));
        assertFalse(m.remove("c"));      // élément inexistant
    }

    @Test
    public void testRemoveIllegalArgument() {
        m.add("x", 2);
        assertThrows(IllegalArgumentException.class,
            () -> m.remove("x", -3));
    }

    @Test
    public void testRemoveZero() {
        m.add("y", 5);
        assertFalse(m.remove("y", 0));
        assertEquals(5, m.count("y"));
    }

    // ===== Test size =====
    @Test
    public void testSize() {
        m.add("a", 2);
        m.add("b", 3);
        assertEquals(5, m.size());
        m.remove("a");
        assertEquals(4, m.size());
    }

    // ===== Test clear =====
    @Test
    public void testClear() {
        m.add("a", 2);
        m.add("b", 3);
        m.clear();
        assertEquals(0, m.size());
        assertEquals(0, m.count("a"));
        assertEquals(0, m.count("b"));
    }

    // ===== Test toString =====
    @Test
    public void testToString() {
        m.add("a", 2);
        m.add("b", 1);
        String s = m.toString();
        assertTrue(s.contains("a:2"));
        assertTrue(s.contains("b:1"));
        assertTrue(s.startsWith("[") && s.endsWith("]"));
    }

    // ===== Séquence complexe =====
    @Test
    public void testSequenceAddRemove() {
        m.add("a", 3);
        m.add("b", 2);
        m.add("c", 5);

        assertEquals(3, m.count("a"));
        assertEquals(2, m.count("b"));
        assertEquals(5, m.count("c"));
        assertEquals(10, m.size());

        m.remove("a", 2);
        m.remove("b");
        m.remove("c", 5);

        assertEquals(1, m.count("a"));
        assertEquals(1, m.count("b"));
        assertEquals(0, m.count("c"));
        assertEquals(2, m.size());
    }

    // ===== Cas particuliers =====
    @Test
    public void testAddThenRemoveSameCount() {
        m.add("x", 4);
        assertEquals(4, m.count("x"));
        m.remove("x", 4);
        assertEquals(0, m.count("x"));
    }

    @Test
    public void testCountNeverAdded() {
        assertEquals(0, m.count("inexistant"));
    }

}
