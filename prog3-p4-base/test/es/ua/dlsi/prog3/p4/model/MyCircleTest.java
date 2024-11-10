package es.ua.dlsi.prog3.p4.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertThrows;

import org.junit.Before;
import org.junit.Test;

public class MyCircleTest {	
	Coordinate rc;
	Shape2D c; 

	@Before
	public void setUp() throws Exception {
		rc = new Coordinate(100, 500);
		c = new Circle(rc, 231);
	}

	@Test
	public final void testCtor1() {
		Circle c = new Circle();
		assertEquals("Default ctor, radius==0", 0.0, c.getRadius(), 0.001);
		assertEquals("Default ctor, position", new Coordinate(), c.getPosition());
	}

	@Test
	public final void testCtor2() {
		Circle c2 = new Circle((Circle) c);
		assertEquals("Copy ctor position", c.getPosition(), c2.getPosition());
		assertEquals("Copy ctor radius", ((Circle) c).getRadius(), c2.getRadius(), 0.001);
		assertNotSame("Copy ctor creates distinct object", c, c2);
	}

	@Test
	public final void testCtor3() {
		Coordinate coord = new Coordinate(50.0, 75.0);
		double radius = 25.0;
		Circle c3 = new Circle(coord, radius);
		assertEquals("Circle center coordinate", coord, c3.getPosition());
		assertEquals("Circle radius", radius, c3.getRadius(), 0.001);

		// Test for IllegalArgumentException with negative radius
		assertThrows("Negative radius should throw exception", IllegalArgumentException.class, () -> {
			new Circle(coord, -10.0);
		});
	}

	@Test
	public final void testEscalar200() throws Exception {
		Coordinate coord = new Coordinate(10.0, -20.0);
		Circle c1 = new Circle(coord, 10.0);
		c1.scale(200.0);
		assertEquals("Circle scale 200%", 20.0, c1.getRadius(), 0.001);
		assertEquals("Circle scale 200% same pos", coord, c1.getPosition());
	}

	@Test
	public final void testEscalar50() throws Exception {
		Coordinate coord = new Coordinate(10.0, -20.0);
		Circle c1 = new Circle(coord, 20.0);
		c1.scale(50.0);
		assertEquals("Circle scale 50%", 10.0, c1.getRadius(), 0.001);
	}

	@Test
	public final void testEscalarInvalid() {
		Circle c1 = new Circle(rc, 10.0);
		assertThrows("Scale with 0 should throw exception", IllegalArgumentException.class, () -> {
			c1.scale(0.0);
		});
		assertThrows("Scale with negative should throw exception", IllegalArgumentException.class, () -> {
			c1.scale(-10.0);
		});
	}

	@Test
	public final void testCloneIsDefined() {
		Coordinate coord = new Coordinate();
		Circle c1 = new Circle(coord, 10);
		Circle clonedCircle = (Circle) c1.clone();
		assertNotSame("Clone should create distinct object", c1, clonedCircle);
		assertEquals("Cloned circle should have same position", c1.getPosition(), clonedCircle.getPosition());
		assertEquals("Cloned circle should have same radius", c1.getRadius(), clonedCircle.getRadius(), 0.001);
	}

	@Test
	public final void testCloneWithNewPosition() {
		Circle c1 = new Circle(rc, 10);
		Circle clonedCircle = (Circle) c1.clone(new Coordinate(1.1, 1.1));
		assertEquals("Cloned circle new position", new Coordinate(1.1, 1.1), clonedCircle.getPosition());
		assertEquals("Cloned circle should keep radius", c1.getRadius(), clonedCircle.getRadius(), 0.001);
	}

	@Test
	public final void testToString() {
		String expectedString = "(100.0,500.0),radius=231.0";
		assertEquals("Circle toString()", expectedString, c.toString());
	}

	@Test
	public final void testEquals() {
		Circle c2 = new Circle(rc, 231);
		assertEquals("Circle.equals() with identical attributes", c, c2);

		Circle c3 = new Circle(rc, 150);
		assertNotEquals("Circle.equals() with different radius", c, c3);

		Coordinate otherCoord = new Coordinate(200, 300);
		Circle c4 = new Circle(otherCoord, 231);
		assertNotEquals("Circle.equals() with different position", c, c4);
	}
}
