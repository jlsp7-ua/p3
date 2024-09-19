package es.ua.dlsi.prog3.p1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for the class SummaryStatistics 
 */
public class CoordinateTest {
	double [] v;

	/**
	 * Executed before each method annotated with @Test
	 */
	@Before
    public void setUp() {
		v = new double[] {20.5, 31.4};
	}
    
	@Test
	public void testGetDimensions() {
		Coordinate c = new Coordinate(v);
		assertEquals("Dimensions", 2, c.getDimensions());
		assertEquals("1st dimension", 20.5, c.getComponents()[0], 0.0001); // 0.001 is the precision of the comparison between doubles
		assertEquals("2nd dimension", 31.4, c.getComponents()[1], 0.0001); // 0.001 is the precision of the comparison between doubles
	}

	@Test
	public void testEqualsHashCode() {
		Coordinate c1 = new Coordinate(v);
		Coordinate c2 = new Coordinate(v);
		
		assertTrue("Same hashCode", c1.hashCode() == c2.hashCode());
		assertTrue("Equals", c1.equals(c2));
		
		double [] v2 = new double[] {34.0, 10.0};
		Coordinate c3 = new Coordinate(v2);
		assertFalse("HashCode", c1.hashCode() == c3.hashCode());
		assertFalse("Not equals", c1.equals(c3));		
	}

	@Test
	public void testImmutable() {
		Coordinate s1 = new Coordinate(v);
		double d1 = s1.getComponents()[0];
		
		// modify v - if it's correctly initialized the dimension will remain the same
		v[0] = 100.0;
		assertEquals("Dimension after v change", d1, s1.getComponents()[0], 0.001);		
		
	}
	
	@Test
	public void testDefensiveCopy() {
		Coordinate s1 = new Coordinate(v);
		s1.getComponents()[0] = 100;
		Coordinate s2 = new Coordinate(v);
		assertEquals("s1 should remain the same", s1, s2); // this invokes the equals method of Coordinate
	}
	
	@Test
	public void testCopyConstructor() {
		Coordinate c1 = new Coordinate(v);
		Coordinate c2 = new Coordinate(c1);
		assertEquals("Copy constructor", c1, c2); // this invokes the equals method of Coordinate		
		
	}	
	
}
