package es.ua.dlsi.prog3.p4.model;
import static org.junit.Assert.*;
//import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
//import es.ua.dlsi.prog3.p4.util.Util;

public class MyCanvasTest {
    class NewShape2D extends Shape2D {
        NewShape2D() {
        }
        NewShape2D(Coordinate pos) {
            super(pos);
        }
        
        NewShape2D(NewShape2D other) { 
            super(other);
        }
        
        @Override
        public void scale(double porcentaje)  {
        }
        
        @Override
        public NewShape2D clone() {
            return new NewShape2D(this);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof NewShape2D)) return false;
            NewShape2D other = (NewShape2D) obj;
            return super.equals(other);
        }
    }

    Canvas lienzo;
    
    @Before
    public void setUp() throws Exception {
        lienzo = new Canvas();
        lienzo.addShape(new NewShape2D());
        lienzo.addShape(new NewShape2D());
    }

    @Test
    public final void testDefaultCtor() {
        Canvas l = new Canvas();
        
        assertEquals("Alto por defecto", Canvas.DEFAULT_SIZE, l.getHeight(), 0.001);
        assertEquals("Ancho por defecto", Canvas.DEFAULT_SIZE, l.getWidth(), 0.001);
        assertEquals("No figures in new Canvas", 0, l.getNumShapes());
        assertEquals("Default title", "default canvas", l.getTitle());
    }

    @Test
    public final void testCanvasStringFloatFloat() {
        Canvas l = new Canvas("Prueba", 10, 20);
        assertEquals("Height 20", 20, l.getHeight(), 0.001);
        assertEquals("Width 10", 10, l.getWidth(), 0.001);
        assertEquals("No figures in new Canvas", 0, l.getNumShapes());
        assertEquals("Title", "Prueba", l.getTitle());
    }

    @Test
    public final void testCopyCtor() {
        Canvas original = new Canvas("Test", 100, 200);
        NewShape2D shape = new NewShape2D();
        original.addShape(shape);
        
        Canvas copy = new Canvas(original);
        
        assertEquals("Same title", original.getTitle(), copy.getTitle());
        assertEquals("Same width", original.getWidth(), copy.getWidth(), 0.001);
        assertEquals("Same height", original.getHeight(), copy.getHeight(), 0.001);
        assertEquals("Same number of shapes", original.getNumShapes(), copy.getNumShapes());
        
        // Verificar que es una copia profunda
        assertNotSame("Different shape objects", original.getShape(1), copy.getShape(1));
        assertEquals("Equal shapes", original.getShape(1), copy.getShape(1));
    }

    @Test(expected=IllegalArgumentException.class)
    public final void testBadCanvasNegativeWidth() {
        new Canvas("Bad Canvas dimensions", -10, 20);
    }

    @Test(expected=IllegalArgumentException.class)
    public final void testBadCanvasNegativeHeight() {
        new Canvas("Bad Canvas dimensions", 10, -20);
    }

    @Test
    public final void testAddShape() {
        Canvas l = new Canvas();
        NewShape2D shape = new NewShape2D();
        l.addShape(shape);
        assertEquals("One shape added", 1, l.getNumShapes());
        assertEquals("Shape retrieved correctly", shape, l.getShape(1));
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public final void testGetShapeIndexZero() {
        lienzo.getShape(0);
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public final void testGetShapeIndexTooLarge() {
        lienzo.getShape(lienzo.getNumShapes() + 1);
    }

    @Test
    public final void testGetShapeOK() {
        Shape2D shape = new NewShape2D();
        lienzo.addShape(shape);
        assertEquals("Shape retrieved correctly", shape, lienzo.getShape(3));
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public final void testRemoveShapeIndexZero() {
        lienzo.removeShape(0);
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public final void testRemoveShapeIndexTooLarge() {
        lienzo.removeShape(lienzo.getNumShapes() + 1);
    }

    @Test
    public final void testRemoveShapeOK() {
        int originalCount = lienzo.getNumShapes();
        lienzo.removeShape(1);
        assertEquals("Shape removed", originalCount - 1, lienzo.getNumShapes());
    }

    @Test
    public final void testClone() {
        Canvas clone = lienzo.clone();
        
        assertNotSame("Different objects", lienzo, clone);
        assertEquals("Same title", lienzo.getTitle(), clone.getTitle());
        assertEquals("Same width", lienzo.getWidth(), clone.getWidth(), 0.001);
        assertEquals("Same height", lienzo.getHeight(), clone.getHeight(), 0.001);
        assertEquals("Same number of shapes", lienzo.getNumShapes(), clone.getNumShapes());
        
        // Verificar que es una copia profunda
        for (int i = 1; i <= lienzo.getNumShapes(); i++) {
            assertNotSame("Different shape objects", lienzo.getShape(i), clone.getShape(i));
            assertEquals("Equal shapes", lienzo.getShape(i), clone.getShape(i));
        }
    }

    @Test
    public final void testGetNumShapes() {
        assertEquals("Initial number of shapes", 2, lienzo.getNumShapes());
        lienzo.addShape(new NewShape2D());
        assertEquals("After adding a shape", 3, lienzo.getNumShapes());
        lienzo.removeShape(1);
        assertEquals("After removing a shape", 2, lienzo.getNumShapes());
    }

    @Test
    public final void testToString() {
        String expected = "default canvas (1000.0,1000.0) with 2 shapes";
        assertEquals("Canvas.toString()", expected, lienzo.toString());
        
        Canvas custom = new Canvas("Prog3", 1024, 768);
        custom.addShape(new NewShape2D());
        custom.addShape(new NewShape2D());
        custom.addShape(new NewShape2D());
        expected = "Prog3 (1024.0,768.0) with 3 shapes";
        assertEquals("Custom canvas toString", expected, custom.toString());
    }
}