package es.ua.dlsi.prog3.p4.model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MySquareTest {    
    Coordinate position;
    Square square;
    double angle, side;

    @Before
    public void setUp() throws Exception {
        position = new Coordinate(100, 500);
        angle = 45;
        side = 99;
        square = new Square(position, angle, side);
    }
    
    /**
     * Test default constructor
     */
    @Test
    public final void testDefaultConstructor() {
        Square s = new Square();
        assertEquals("Default constructor: side should be 0", 0.0, s.getSide(), 0.001);
        assertEquals("Default constructor: angle should be 0", 0.0, s.getAngle(), 0.001);
        assertTrue("Default constructor: position should be invalid", 
                  Double.isNaN(s.getPosition().getX()) && Double.isNaN(s.getPosition().getY()));
    }

    /**
     * Test copy constructor
     */
    @Test
    public final void testCopyConstructor() {
        Square copy = new Square(square);
        assertEquals("Copy constructor: position should match", square.getPosition(), copy.getPosition());
        assertEquals("Copy constructor: angle should match", square.getAngle(), copy.getAngle(), 0.001);
        assertEquals("Copy constructor: side should match", square.getSide(), copy.getSide(), 0.001);
        assertNotSame("Copy constructor: should create new instance", square, copy);
    }

    /**
     * Test parametrized constructor with valid values
     */
    @Test
    public final void testParametrizedConstructor() {
        Square s = new Square(position, angle, side);
        assertEquals("Constructor: position should match", position, s.getPosition());
        assertEquals("Constructor: angle should match", angle, s.getAngle(), 0.001);
        assertEquals("Constructor: side should match", side, s.getSide(), 0.001);
    }

    /**
     * Test constructor with negative side
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testConstructorNegativeSide() {
        new Square(position, angle, -1.0);
    }

    /**
     * Test constructor with zero side
     */
    @Test
    public final void testConstructorZeroSide() {
        Square s = new Square(position, angle, 0.0);
        assertEquals("Constructor with zero side should be allowed", 0.0, s.getSide(), 0.001);
    }

    /**
     * Test getSide method
     */
    @Test
    public final void testGetSide() {
        assertEquals("getSide should return correct value", side, square.getSide(), 0.001);
    }

    /**
     * Test scale with various percentages
     */
    @Test
    public final void testScale() {
        // Test scaling up
        Square s1 = new Square(position, angle, 100);
        s1.scale(200);
        assertEquals("Scale 200% should double the side", 200.0, s1.getSide(), 0.001);

        // Test scaling down
        Square s2 = new Square(position, angle, 100);
        s2.scale(50);
        assertEquals("Scale 50% should halve the side", 50.0, s2.getSide(), 0.001);

        // Test scaling with 100% (no change)
        Square s3 = new Square(position, angle, 100);
        s3.scale(100);
        assertEquals("Scale 100% should not change the side", 100.0, s3.getSide(), 0.001);
    }

    /**
     * Test scale with invalid percentages
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testScaleNegative() {
        square.scale(-50);
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testScaleZero() {
        square.scale(0);
    }

    /**
     * Test clone method
     */
    @Test
    public final void testClone() {
        Square cloned = (Square) square.clone();
        assertNotNull("Clone should not be null", cloned);
        assertEquals("Cloned square should have same position", square.getPosition(), cloned.getPosition());
        assertEquals("Cloned square should have same angle", square.getAngle(), cloned.getAngle(), 0.001);
        assertEquals("Cloned square should have same side", square.getSide(), cloned.getSide(), 0.001);
        assertNotSame("Clone should be a different instance", square, cloned);
    }

    /**
     * Test toString method
     */
    @Test
    public final void testToString() {
        String expected = "(100.0,500.0),angle=45.0,side=99.0";
        assertEquals("toString should match expected format", expected, square.toString());
        
        // Test with different values including negatives
        Square s = new Square(new Coordinate(-30.4, 27.1), 45.0, 2.5);
        expected = "(-30.4,27.1),angle=45.0,side=2.5";
        assertEquals("toString should handle negative coordinates", expected, s.toString());
    }

    /**
     * Test equals method
     */
    @Test
    public final void testEquals() {
        // Test equal squares
        Square same = new Square(position, angle, side);
        assertTrue("Equal squares should be equal", square.equals(same));
        
        // Test different position
        Square diffPos = new Square(new Coordinate(0, 0), angle, side);
        assertFalse("Squares with different positions should not be equal", square.equals(diffPos));
        
        // Test different angle
        Square diffAngle = new Square(position, angle + 1, side);
        assertFalse("Squares with different angles should not be equal", square.equals(diffAngle));
        
        // Test different side
        Square diffSide = new Square(position, angle, side + 1);
        assertFalse("Squares with different sides should not be equal", square.equals(diffSide));
        
        // Test null
        assertFalse("Square should not be equal to null", square.equals(null));
        
        // Test different type
        assertFalse("Square should not be equal to different type", square.equals(new Object()));
    }
}