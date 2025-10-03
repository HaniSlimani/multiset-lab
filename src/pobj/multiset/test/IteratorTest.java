package pobj.multiset.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import pobj.multiset.HashMultiSet;

public class IteratorTest {

	@SuppressWarnings("unused")
	@Test
	public void testIteratorVide() {
		HashMultiSet<String> m = new HashMultiSet<>();
		for (String s : m) {
			assertTrue(false);
		}
	}
	
	@Test
	public void testIterator() {
		HashMultiSet<String> m = new HashMultiSet<>();
		List<String> l = new ArrayList<>();
		m.add("b");
		m.add("a",3);
		m.add("c",2);
		for (String s : m) {
			l.add(s);
		}
		Collections.sort(l);
		assertEquals(6, l.size());
		assertEquals("a", l.get(0));
		assertEquals("a", l.get(1));
		assertEquals("a", l.get(2));
		assertEquals("b", l.get(3));
		assertEquals("c", l.get(4));
		assertEquals("c", l.get(5));
	}
}
