package es.ua.dlsi.prog3.p4.model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MyAbstractPolygonTest {
    // Clase concreta para probar AbstractPolygon
    class ConcretePolygon extends AbstractPolygon {
        public ConcretePolygon() {
            super();
        }
        
        public ConcretePolygon(Coordinate c, double angle) {
            super(c, angle);
        }
        
        public ConcretePolygon(ConcretePolygon other) {
            super(other);
        }
        
        @Override
        public void scale(double factor) {
            // No necesitamos implementación para las pruebas
        }
        
        @Override
        public ConcretePolygon clone() {
            return new ConcretePolygon(this);
        }
    }
    
    private ConcretePolygon polygon;
    private static final double DELTA = 0.001;
    
    @Before
    public void setUp() {
        polygon = new ConcretePolygon();
    }
    
    // Tests del constructor por defecto
    @Test
    public void testDefaultConstructor() {
        assertEquals("Default angle should be 0.0", 0.0, polygon.getAngle(), DELTA);
        Coordinate defaultPos = polygon.getPosition();
        assertNotNull("Default position should not be null", defaultPos);
        assertEquals("Default X coordinate should be NOT_VALID", Coordinate.NOT_VALID, defaultPos.getX(), DELTA);
        assertEquals("Default Y coordinate should be NOT_VALID", Coordinate.NOT_VALID, defaultPos.getY(), DELTA);
    }
    
    // Tests del constructor con parámetros
    @Test
    public void testParameterizedConstructor() {
        Coordinate pos = new Coordinate(1000.0, 2000.0);
        ConcretePolygon p = new ConcretePolygon(pos, 45.0);
        
        assertEquals("Angle should be 45.0", 45.0, p.getAngle(), DELTA);
        assertEquals("Position should match", pos, p.getPosition());
    }
    
    // Tests de límites de coordenadas
    @Test(expected = IllegalArgumentException.class)
    public void testCoordinateTooLarge() {
        new ConcretePolygon(new Coordinate(Coordinate.MAX_VALUE + 1, 0), 45.0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCoordinateTooSmall() {
        new ConcretePolygon(new Coordinate(Coordinate.MIN_VALUE - 1, 0), 45.0);
    }
    
    @Test
    public void testValidCoordinateLimits() {
        new ConcretePolygon(new Coordinate(Coordinate.MAX_VALUE, Coordinate.MAX_VALUE), 45.0);
        new ConcretePolygon(new Coordinate(Coordinate.MIN_VALUE, Coordinate.MIN_VALUE), 45.0);
    }
    
    // Tests de ángulos límite válidos
    @Test
    public void testValidAngleLimits() {
        new ConcretePolygon(new Coordinate(0,0), 0.0);  // Límite inferior
        new ConcretePolygon(new Coordinate(0,0), 359.999);  // Límite superior
    }
    
    // Tests de ángulos inválidos
    @Test(expected = IllegalArgumentException.class)
    public void testNegativeAngle() {
        new ConcretePolygon(new Coordinate(0,0), -0.001);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testTooLargeAngle() {
        new ConcretePolygon(new Coordinate(0,0), 360.0);
    }
    
    // Tests del constructor de copia
    @Test
    public void testCopyConstructor() {
        Coordinate pos = new Coordinate(30.0, 40.0);
        ConcretePolygon original = new ConcretePolygon(pos, 90.0);
        ConcretePolygon copy = new ConcretePolygon(original);
        
        assertEquals("Copied angle should match", original.getAngle(), copy.getAngle(), DELTA);
        assertEquals("Copied position should match", original.getPosition(), copy.getPosition());
        assertNotSame("Position should be a new object", original.getPosition(), copy.getPosition());
    }
    
    // Tests de equals
    @Test
    public void testEquals() {
        Coordinate pos = new Coordinate(-30.4, 27.1);
        ConcretePolygon p1 = new ConcretePolygon(pos, 45.0);
        ConcretePolygon p2 = new ConcretePolygon(new Coordinate(-30.4, 27.1), 45.0);
        ConcretePolygon p3 = new ConcretePolygon(new Coordinate(-30.4, 27.2), 45.0);
        ConcretePolygon p4 = new ConcretePolygon(pos, 45.1);
        
        assertTrue("Same polygon should be equal", p1.equals(p1));
        assertTrue("Polygons with same position and angle should be equal", p1.equals(p2));
        assertFalse("Polygons with different positions should not be equal", p1.equals(p3));
        assertFalse("Polygons with different angles should not be equal", p1.equals(p4));
        assertFalse("Polygon should not be equal to null", p1.equals(null));
        assertFalse("Polygon should not be equal to other type", p1.equals("not a polygon"));
    }
    
    // Tests de toString
    @Test
    public void testToString() {
        ConcretePolygon p = new ConcretePolygon(new Coordinate(-30.4, 27.1), 45.0);
        String expected = "(-30.4,27.1),angle=45.0";
        assertEquals("toString format should match specification", expected, p.toString());
    }
    
    // Tests de rotate
    @Test
    public void testRotatePositive() {
        ConcretePolygon p = new ConcretePolygon(new Coordinate(0,0), 45.0);
        p.rotate(90.0);
        assertEquals("Angle after positive rotation", 135.0, p.getAngle(), DELTA);
    }
    
    @Test
    public void testRotateNegative() {
        ConcretePolygon p = new ConcretePolygon(new Coordinate(0,0), 45.0);
        p.rotate(-90.0);
        assertEquals("Angle after negative rotation", 315.0, p.getAngle(), DELTA);
    }
    
    @Test
    public void testRotateWraparound() {
        ConcretePolygon p = new ConcretePolygon(new Coordinate(0,0), 350.0);
        p.rotate(20.0);
        assertEquals("Angle should wrap around to [0,360)", 10.0, p.getAngle(), DELTA);
        
        p = new ConcretePolygon(new Coordinate(0,0), 10.0);
        p.rotate(-20.0);
        assertEquals("Negative rotation should wrap around correctly", 350.0, p.getAngle(), DELTA);
    }
    
    // Tests de límites de rotate
    @Test(expected = IllegalArgumentException.class)
    public void testRotateTooNegative() {
        polygon.rotate(-360.0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testRotateTooPositive() {
        polygon.rotate(360.0);
    }
    
    @Test
    public void testRotateValidLimits() {
        polygon.rotate(359.999);  // Casi 360
        polygon.rotate(-359.999); // Casi -360
    }
    
    // Test de múltiples rotaciones
    @Test
    public void testMultipleRotations() {
        ConcretePolygon p = new ConcretePolygon(new Coordinate(0,0), 0.0);
        p.rotate(45.0);
        p.rotate(45.0);
        p.rotate(45.0);
        assertEquals("Multiple rotations should accumulate correctly", 135.0, p.getAngle(), DELTA);
    }
}