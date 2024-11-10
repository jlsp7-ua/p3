package es.ua.dlsi.prog3.p4.model;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Test suite for Shape2DFactory static methods
 */
public class MyFactoryFigurasTest {
    
    /**
     * Test creation of Circle with exact string match
     */
    @Test
    public void testCreateCircle() {
        Shape2D shape = Shape2DFactory.createShape2D("Circle");
        assertNotNull("Created shape should not be null", shape);
        assertTrue("Shape should be instance of Circle", shape instanceof Circle);
        
        // Verify default constructor was used
        Circle circle = (Circle) shape;
        assertEquals("Default circle should have radius 0", 0.0, circle.getRadius(), 0.001);
        assertTrue("Default circle should have invalid position", 
                  Double.isNaN(circle.getPosition().getX()) && 
                  Double.isNaN(circle.getPosition().getY()));
    }

    /**
     * Test creation of Square with exact string match
     */
    @Test
    public void testCreateSquare() {
        Shape2D shape = Shape2DFactory.createShape2D("Square");
        assertNotNull("Created shape should not be null", shape);
        assertTrue("Shape should be instance of Square", shape instanceof Square);
        
        // Verify default constructor was used
        Square square = (Square) shape;
        assertEquals("Default square should have side 0", 0.0, square.getSide(), 0.001);
        assertTrue("Default square should have invalid position", 
                  Double.isNaN(square.getPosition().getX()) && 
                  Double.isNaN(square.getPosition().getY()));
    }

    /**
     * Test creation of Rectangle with exact string match
     */
    @Test
    public void testCreateRectangle() {
        Shape2D shape = Shape2DFactory.createShape2D("Rectangle");
        assertNotNull("Created shape should not be null", shape);
        assertTrue("Shape should be instance of Rectangle", shape instanceof Rectangle);
        
        // Verify default constructor was used
        Rectangle rectangle = (Rectangle) shape;
        assertEquals("Default rectangle should have width 0", 0.0, rectangle.getWidth(), 0.001);
        assertEquals("Default rectangle should have length 0", 0.0, rectangle.getLength(), 0.001);
        assertTrue("Default rectangle should have invalid position", 
                  Double.isNaN(rectangle.getPosition().getX()) && 
                  Double.isNaN(rectangle.getPosition().getY()));
    }

    /**
     * Test invalid shape names
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidShapeName() {
        Shape2DFactory.createShape2D("Triangle");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyString() {
        Shape2DFactory.createShape2D("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullString() {
        Shape2DFactory.createShape2D(null);
    }

    /**
     * Test case sensitivity
     */
    @Test(expected = IllegalArgumentException.class)
    public void testLowerCaseCircle() {
        Shape2DFactory.createShape2D("circle");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpperCaseCircle() {
        Shape2DFactory.createShape2D("CIRCLE");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMixedCaseCircle() {
        Shape2DFactory.createShape2D("CiRcLe");
    }

    /**
     * Test whitespace handling
     */
    @Test(expected = IllegalArgumentException.class)
    public void testLeadingWhitespace() {
        Shape2DFactory.createShape2D(" Circle");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTrailingWhitespace() {
        Shape2DFactory.createShape2D("Circle ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWhitespaceInMiddle() {
        Shape2DFactory.createShape2D("Cir cle");
    }

    /**
     * Test similar but invalid names
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCircleSimilar() {
        Shape2DFactory.createShape2D("Circles");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSquareSimilar() {
        Shape2DFactory.createShape2D("Squared");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRectangleSimilar() {
        Shape2DFactory.createShape2D("Rectangular");
    }

    /**
     * Test special characters
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSpecialCharacters() {
        Shape2DFactory.createShape2D("Circle!");
    }

    /**
     * Test that each creation returns a new instance
     */
    @Test
    public void testMultipleInstancesCircle() {
        Shape2D shape1 = Shape2DFactory.createShape2D("Circle");
        Shape2D shape2 = Shape2DFactory.createShape2D("Circle");
        assertNotSame("Factory should create new instances", shape1, shape2);
    }

    @Test
    public void testMultipleInstancesSquare() {
        Shape2D shape1 = Shape2DFactory.createShape2D("Square");
        Shape2D shape2 = Shape2DFactory.createShape2D("Square");
        assertNotSame("Factory should create new instances", shape1, shape2);
    }

    @Test
    public void testMultipleInstancesRectangle() {
        Shape2D shape1 = Shape2DFactory.createShape2D("Rectangle");
        Shape2D shape2 = Shape2DFactory.createShape2D("Rectangle");
        assertNotSame("Factory should create new instances", shape1, shape2);
    }
}