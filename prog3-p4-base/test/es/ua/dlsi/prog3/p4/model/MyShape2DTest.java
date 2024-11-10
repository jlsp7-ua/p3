package es.ua.dlsi.prog3.p4.model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for Shape2D abstract class
 */
public class MyShape2DTest {
    // Concrete implementation of Shape2D for testing
    class NewShape2D extends Shape2D {
        NewShape2D() {
            super();
        }

        NewShape2D(Coordinate pos) {
            super(pos);
        }

        NewShape2D(NewShape2D other) {
            super(other);
        }

        @Override
        public void scale(double porcentaje) {
            if (porcentaje <= 0) {
                throw new IllegalArgumentException("Scale percentage must be positive");
            }
            // Implementation left empty as per requirements
        }

        @Override
        public NewShape2D clone() {
            return new NewShape2D(this);
        }
    }

    Shape2D default_shape, positioned_shape, copy_shape;
    Coordinate position;

    @Before
    public void setUp() throws Exception {
        position = new Coordinate(1, 1);
        default_shape = new NewShape2D();
        positioned_shape = new NewShape2D(position);
        copy_shape = new NewShape2D((NewShape2D)positioned_shape);
    }

    /**
     * Test default constructor
     */
    @Test
    public final void testShape2D() {
        Coordinate pos = default_shape.getPosition();
        assertTrue("X coordinate should be NOT_VALID", Double.isNaN(pos.getX()));
        assertTrue("Y coordinate should be NOT_VALID", Double.isNaN(pos.getY()));
    }

    /**
     * Test constructor with position
     */
    @Test
    public final void testShape2DCoordinate() {
        Coordinate pos = positioned_shape.getPosition();
        assertEquals("Constructor with position should set correct position", position, pos);
    }

    /**
     * Test copy constructor
     */
    @Test
    public final void testShape2DCopy() {
        assertEquals("Copy constructor should copy position", 
                    positioned_shape.getPosition(), copy_shape.getPosition());
    }

    /**
     * Test move method
     */
    @Test
    public final void testMove() {
        Coordinate newpos = new Coordinate(3.2, -2.3);
        Coordinate oldpos = positioned_shape.move(newpos);
        
        assertEquals("Move should return old position", position, oldpos);
        assertEquals("Move should update position", newpos, positioned_shape.getPosition());
    }

    /**
     * Test move with null coordinate
     */
    @Test
    public final void testMoveNull() {
        Coordinate currentPos = positioned_shape.getPosition();
        Coordinate returnedPos = positioned_shape.move(null);
        
        assertEquals("Move with null should return current position", currentPos, returnedPos);
        assertEquals("Move with null should not change position", currentPos, positioned_shape.getPosition());
    }

    /**
     * Test equals method
     */
    @Test
    public final void testEquals() {
        Shape2D samePos = new NewShape2D(new Coordinate(1, 1));
        Shape2D differentPos = new NewShape2D(new Coordinate(2, 2));
        
        assertTrue("Shapes with same position should be equal", positioned_shape.equals(samePos));
        assertFalse("Shapes with different positions should not be equal", positioned_shape.equals(differentPos));
        assertFalse("Shape should not be equal to null", positioned_shape.equals(null));
        assertFalse("Shape should not be equal to different type", positioned_shape.equals(new Object()));
    }

    /**
     * Test toString method
     */
    @Test
    public final void testToString() {
        String expected = "(1.0,1.0)";
        assertEquals("toString should return correct format", expected, positioned_shape.toString());
    }

    /**
     * Test clone method
     */
    @Test
    public final void testClone() {
        Shape2D cloned = positioned_shape.clone();
        assertNotNull("Clone should not return null", cloned);
        assertEquals("Cloned shape should have same position", positioned_shape.getPosition(), cloned.getPosition());
        assertNotSame("Clone should be a different object", positioned_shape, cloned);
    }

    /**
     * Test clone with position method
     */
    @Test
    public final void testCloneWithPosition() {
        Coordinate newPos = new Coordinate(5.0, 5.0);
        Shape2D cloned = positioned_shape.clone(newPos);
        
        assertNotNull("Clone with position should not return null", cloned);
        assertEquals("Cloned shape should have new position", newPos, cloned.getPosition());
        assertNotSame("Clone should be a different object", positioned_shape, cloned);
    }

    /**
     * Test scale method with valid percentage
     */
    @Test
    public final void testScaleValid() {
        positioned_shape.scale(150.0); // Should not throw exception
    }

    /**
     * Test scale method with invalid percentage
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testScaleInvalidNegative() {
        positioned_shape.scale(-50.0);
    }

    /**
     * Test scale method with zero
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testScaleInvalidZero() {
        positioned_shape.scale(0.0);
    }
}