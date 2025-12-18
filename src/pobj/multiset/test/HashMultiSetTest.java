package pobj.multiset.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pobj.multiset.HashMultiSet;
import pobj.multiset.*;

public class HashMultiSetTest {
	
	private MultiSet<String> m;
	
	@BeforeEach
	public void before() {
		m = new MultiSetDecorator<>(new HashMultiSet<>());
		m.add("a");
		m.add("a",5);
		m.add("b",3);
	}
	
	@Test 
	public void test1() {
		assertFalse(m.add("c", 0));
		assertTrue(m.add("e",4));
		assertEquals(6, m.count("a"));
		assertEquals(3, m.count("b"));
		assertEquals(13,m.size());
		assertEquals(0,m.count("d"));
		assertEquals(0,m.count("c"));
	}
	
	@Test
	public void test2() {
		m.clear();
		assertEquals(0, m.count("a"));
		assertEquals(0, m.count("b"));
		assertEquals(0,m.size());
	}
	
	@Test
	public void test3() {
		
		assertTrue(m.remove("a"));
		assertTrue(m.remove("b",2));
		assertFalse(m.remove("c",1));
		
	
		assertEquals(5, m.count("a"));
		assertEquals(1, m.count("b"));
		assertEquals(0,m.count("d"));
		assertEquals(6,m.size());
		
		assertTrue(m.remove("b",10));	
	}
}
