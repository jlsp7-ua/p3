package es.ua.dlsi.prog3.p2.test;

import static org.junit.Assert.*;
import org.junit.Test;
import es.ua.dlsi.prog3.p2.model.*;
import es.ua.dlsi.prog3.p2.exceptions.PressureWheelException;
import es.ua.dlsi.prog3.p2.exceptions.TooManyWheelsException;
import es.ua.dlsi.prog3.p2.exceptions.WrongTyreTypeException;

public class CarPreTest {

    // Test constructor por defecto
    @Test
    public void testConstructor() {
        Car c = new Car();
        assertEquals(c.getWheels().size(), 0); // Debe iniciar con 0 ruedas
    }

    // Test para añadir 4 ruedas con el mismo TyreType
    @Test
    public void testAddWheel3() {
        Car c = new Car();
        
        try {
            TyreType t = new TyreType("205/65 R16", 1.5, 4);
            
            // Añadimos 4 ruedas con el mismo TyreType
            c.addWheel(new Wheel(t));
            c.addWheel(new Wheel(t));
            c.addWheel(new Wheel(t));
            c.addWheel(new Wheel(t));
        } catch (TooManyWheelsException | WrongTyreTypeException e) {
            fail("Unexpected exception " + e.getClass());
        }
        
        // Debe haber exactamente 4 ruedas
        assertEquals(4, c.getWheels().size());
    }

    // Test para añadir una rueda con un tipo de neumático diferente (WrongTyreTypeException)
    @Test(expected = WrongTyreTypeException.class)
    public void testAddWheelWithDifferentTyreType() throws TooManyWheelsException, WrongTyreTypeException {
        Car c = new Car();
        TyreType t1 = new TyreType("205/65 R16", 1.5, 4);
        TyreType t2 = new TyreType("185/55 R15", 1.0, 3.5);
        
        c.addWheel(new Wheel(t1)); // Añadimos una rueda con TyreType t1
        c.addWheel(new Wheel(t2)); // Debe lanzar WrongTyreTypeException al añadir una rueda con TyreType diferente
    }

    // Test para añadir demasiadas ruedas (TooManyWheelsException)
    @Test(expected = TooManyWheelsException.class)
    public void testAddTooManyWheels() throws TooManyWheelsException, WrongTyreTypeException {
        Car c = new Car();
        TyreType t = new TyreType("205/65 R16", 1.5, 4);
        
        // Añadimos 4 ruedas, y al intentar añadir la quinta, debe lanzar TooManyWheelsException
        c.addWheel(new Wheel(t));
        c.addWheel(new Wheel(t));
        c.addWheel(new Wheel(t));
        c.addWheel(new Wheel(t));
        c.addWheel(new Wheel(t)); // Esta debe lanzar TooManyWheelsException
    }

    // Test para changeTyres() con TyreType nulo (IllegalArgumentException)
    @Test(expected = IllegalArgumentException.class)
    public void testChangeTyres1() {
        Car c = new Car();
        
        try {
            Wheel w = new Wheel();
            c.addWheel(w);
            c.addWheel(w);
            c.changeTyres(null, 2.25); // Debe lanzar IllegalArgumentException
        } catch (TooManyWheelsException | WrongTyreTypeException | PressureWheelException e) {
            fail("Unexpected exception " + e.getClass());
        }
    }

    // Test para changeTyres() con presión fuera de rango (PressureWheelException)
    @Test(expected = PressureWheelException.class)
    public void testChangeTyresPressureOutOfRange() throws TooManyWheelsException, WrongTyreTypeException, PressureWheelException {
        Car c = new Car();
        TyreType t = new TyreType("205/65 R16", 1.5, 4);
        
        // Añadimos 2 ruedas con el mismo TyreType
        c.addWheel(new Wheel(t));
        c.addWheel(new Wheel(t));
        
        // Intentamos cambiar los neumáticos con una presión fuera del rango permitido
        c.changeTyres(t, 5.0); // Esto debe lanzar PressureWheelException
    }

    // Test para changeTyres() con presión válida
    @Test
    public void testChangeTyresValidPressure() {
        Car c = new Car();
        TyreType t = new TyreType("205/65 R16", 1.5, 4);
        
        try {
            // Añadimos 4 ruedas con el mismo TyreType
            c.addWheel(new Wheel(t));
            c.addWheel(new Wheel(t));
            c.addWheel(new Wheel(t));
            c.addWheel(new Wheel(t));
            
            // Cambiamos los neumáticos con una presión dentro de los límites
            c.changeTyres(t, 3.0);
            
            // Verificamos que todas las ruedas tienen la presión correcta
            for (Wheel w : c.getWheels()) {
                assertEquals(3.0, w.getPressure(), 0.01);
            }
        } catch (TooManyWheelsException | WrongTyreTypeException | PressureWheelException e) {
            fail("Unexpected exception " + e.getClass());
        }
    }

    // Test para getWheels() sin ruedas instaladas
    @Test
    public void testGetWheelsEmpty() {
        Car c = new Car();
        assertTrue(c.getWheels().isEmpty()); // La lista de ruedas debe estar vacía inicialmente
    }

    // Test para getWheels() con ruedas instaladas
    @Test
    public void testGetWheelsWithWheels() throws TooManyWheelsException, WrongTyreTypeException {
        Car c = new Car();
        TyreType t = new TyreType("205/65 R16", 1.5, 4);
        
        // Añadimos 2 ruedas
        Wheel w1 = new Wheel(t);
        Wheel w2 = new Wheel(t);
        c.addWheel(w1);
        c.addWheel(w2);


        assertEquals(2, c.getWheels().size()); // Debe haber 2 ruedas
        assertSame(w1, c.getWheels().get(0));  // La primera rueda debe ser w1
        assertSame(w2, c.getWheels().get(1));  // La segunda rueda debe ser w2
    }
}
