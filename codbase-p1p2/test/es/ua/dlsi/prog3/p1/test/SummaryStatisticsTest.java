package es.ua.dlsi.prog3.p1.test;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.ua.dlsi.prog3.p1.SummaryStatistics;

/**
 * Unit tests for the class SummaryStatistics 
 */
public class SummaryStatisticsTest {
	/**
	 * This vector will be initialized before each test method 
	 */
	ArrayList<Integer> v;

	/**
	 * Executed before each method annotated with @Test
	 */
	@Before
    public void setUp() {
		v = new ArrayList<>();		
		v.addAll(Arrays.asList(34, 10, 293, 23));
	}
	
	/**
	 * The class method COUNT_INSTANCES is checked by creating different objects. In order to test that the copy constructor increments the instance count we've added it to the test.
	 * Note this method may be invoked at any point, so the COUNT_INSTANCES depends on when it's invoked
	 */
	@Test
	public void testCopyConstructorAndInstanceCount() {
		int currentInstances = SummaryStatistics.COUNT_INSTANCES();
		assertTrue("Current instances", currentInstances >= 0);
		SummaryStatistics s1 = new SummaryStatistics(v);
		assertEquals("First instance", currentInstances+1, SummaryStatistics.COUNT_INSTANCES());
		SummaryStatistics s2 = new SummaryStatistics(v);
		assertEquals("Second instance", currentInstances+2, SummaryStatistics.COUNT_INSTANCES());
		assertNotEquals("Different ids", s1.getId(), s2.getId());
		assertEquals("Averages", s1.getAverage(), s2.getAverage());
		assertEquals("Mins", s1.getMin(), s2.getMin());
		assertEquals("Maxs", s1.getMax(), s2.getMax());
		
	}	
	
	
	
	/**
	 * When the instance is initialized with the default constructor, `getMin` and `getMax` must return `null`, and `getSize` 0.
	 */
	@Test
	public void testEmpty() {
		SummaryStatistics s = new SummaryStatistics();
		assertNull("Average should be null", s.getAverage());
		assertNull(s.getMin());
		assertNull(s.getMax());
		assertEquals(0, s.getSize());		
	}
    
	/**
	 * The `add` method is tested by adding three values and computing the size and average
	 */
	@Test
	public void testAdd() {
		SummaryStatistics s = new SummaryStatistics();
		s.add(10);
		s.add(10);
		s.add(40);
		assertEquals("Size", 3, s.getSize());
		assertEquals("Average", (Integer)20, s.getAverage()); // if the cast is not used, Java complains about an ambiguous call
	}	
	
	/**
	 * getAverage method tested with the values initialized in setUp()
	 */
	@Test
	public void testGetAverage() {
		SummaryStatistics statistics = new SummaryStatistics(v);
		assertEquals("AVG", (Integer)90, statistics.getAverage()); // if the cast is not used, Java complains about an ambiguous call 
	}

	/**
	 * getMax method test tested with the values initialized in setUp()
	 */
	@Test
	public void testGetMax() {
		SummaryStatistics statistics = new SummaryStatistics(v);
		assertEquals("Max", (Integer)293, statistics.getMax());  // if the cast is not used, Java complains about an ambiguous call
	}

	/**
	 * getMinmethod test tested with the values initialized in setUp()
	 */	
	@Test
	public void testGetMin() {
		SummaryStatistics statistics = new SummaryStatistics(v);
		assertEquals("Min", (Integer)10, statistics.getMin()); // if the cast is not used, Java complains about an ambiguous call
	}

	/**
	 * Constructor is checked to use defensive copy
	 */
	@Test
	public void testDefensiveCopyInConstructor() {
		SummaryStatistics s1 = new SummaryStatistics(v);
		Integer average = s1.getAverage();
		// modify v - if it's correctly initialized the average should not change after the change of v
		v.set(0, 100);
		assertEquals("Average after v change", average, s1.getAverage());		
		
	}
	
}
