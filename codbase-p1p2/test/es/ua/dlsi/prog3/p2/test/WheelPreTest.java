package es.ua.dlsi.prog3.p2.test;

import static org.junit.Assert.*;
import org.junit.Test;
import es.ua.dlsi.prog3.p2.exceptions.NoTyreTypeException;
import es.ua.dlsi.prog3.p2.exceptions.PressureWheelException;
import es.ua.dlsi.prog3.p2.model.*;

public class WheelPreTest {

    // Test constructor por defecto
    @Test
    public void testConstructor1() {
        Wheel w = new Wheel();
        assertNull(w.getTyreType()); // No debe tener un tipo de neumático asignado
        assertEquals(0.0, w.getPressure(), 0.01); // La presión debe ser 0
    }

    // Test constructor con TyreType
    @Test
    public void testConstructor2() {
        TyreType t = new TyreType("205/65 R16", 2, 4);
        Wheel w = new Wheel(t);
        assertNotNull(w.getTyreType()); // Debe tener un tipo de neumático asignado
        assertEquals(0.0, w.getPressure(), 0.01); // La presión debe ser 0
    }

    // Test constructor de copia
    @Test
    public void testCopyConstructor() throws IllegalArgumentException, NoTyreTypeException, PressureWheelException {
        TyreType t = new TyreType("205/65 R16", 2, 4);
        Wheel original = new Wheel(t);
        original.inflate(2.5); // Inflamos la rueda original
        
        Wheel copy = new Wheel(original); // Copiamos la rueda original
        assertEquals(original.getPressure(), copy.getPressure(), 0.01); // La presión debe ser la misma
        assertNotSame(original.getTyreType(), copy.getTyreType()); // Debe ser una copia profunda del TyreType
        assertEquals(original.getTyreType().getDescription(), copy.getTyreType().getDescription()); // Las descripciones deben ser iguales
        
        // Comprobamos que la copia es realmente independiente (modificamos una y la otra no debe cambiar)
        original.inflate(3.0);
        assertEquals(2.5, copy.getPressure(), 0.01); // La copia no debe haber cambiado
        assertEquals(5.5, original.getPressure(), 0.01); // La original cambia
    }

    // Test setter para el TyreType
    @Test
    public void testSetTyreType() {
        TyreType t = new TyreType("205/65 R16", 2, 4);
        Wheel w = new Wheel();
                
        assertNull(w.getTyreType()); // Inicialmente no debe tener un tipo de neumático
        
        w.setTyreType(t);
        
        assertNotNull(w.getTyreType()); // Ahora debe tener un tipo de neumático
        assertEquals("205/65 R16", w.getTyreType().getDescription()); // Verificamos el tipo de neumático asignado
        assertEquals(0.0, w.getPressure(), 0.01); // La presión debe seguir siendo 0
    }

    // Test para IllegalArgumentException en inflate() con presión negativa
    @Test(expected = IllegalArgumentException.class)
    public void testInflateNegativePressure() {
        TyreType t = new TyreType("205/65 R16", 2, 4);
        Wheel w = new Wheel(t);
                
        try {
            w.inflate(-1); // Esto debe lanzar IllegalArgumentException
        } catch (NoTyreTypeException | PressureWheelException e) {
            fail("Unexpected exception " + e.getMessage());
        }
    }

    // Test para NoTyreTypeException cuando no hay TyreType asignado
    @Test(expected = NoTyreTypeException.class)
    public void testInflateNoTyreType() throws NoTyreTypeException, PressureWheelException {
        Wheel w = new Wheel();
        w.inflate(2.0); // Esto debe lanzar NoTyreTypeException porque no hay tipo de neumático
    }

    // Test para PressureWheelException cuando la presión está fuera de los límites
    @Test(expected = PressureWheelException.class)
    public void testInflateOutOfRange() throws NoTyreTypeException, PressureWheelException {
        TyreType t = new TyreType("205/65 R16", 2, 4);
        Wheel w = new Wheel(t);
        w.inflate(5.0); // Esto debe lanzar PressureWheelException porque está fuera de los límites
    }

    // Test para inflar correctamente dentro de los límites
    @Test
    public void testInflateCorrectPressure() {
        TyreType t = new TyreType("205/65 R16", 2, 4);
        Wheel w = new Wheel(t);
        
        try {
            w.inflate(3.0); // Dentro de los límites, no debe lanzar excepciones
            assertEquals(3.0, w.getPressure(), 0.01); // La presión debe ser 3.0
        } catch (NoTyreTypeException | PressureWheelException e) {
            fail("Unexpected exception " + e.getMessage());
        }
    }
}
