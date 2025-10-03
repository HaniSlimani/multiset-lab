package pobj.multiset.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;

import org.junit.jupiter.api.Test;

import pobj.multiset.HashMultiSet;

public class CollectionTest {

	@Test
	public void testImplementsCollection() {
		HashMultiSet<String> m = new HashMultiSet<>();
		assertTrue(m instanceof Collection);
	}
}
