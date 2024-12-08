package es.ua.dlsi.prog3.p2.test;

import static org.junit.Assert.*;
import org.junit.Test;
import es.ua.dlsi.prog3.p2.model.*;

public class TyreTypePreTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor1() {
		new TyreType(null,-1,0);
	}		
		
	@Test
	public void testConstructor4() {
		new TyreType("185/65 R15",1,4);
	}
	
	/* TEST CONSTRUCTOR COPIA */
	@Test
    public void testCopyConstructorValidTyreType() {
        // Creamos un objeto TyreType válido
        TyreType original = new TyreType("205/65 R16", 2.0, 4.0);
        
        TyreType copy = new TyreType(original);

        assertEquals(original.getMinPressure(), copy.getMinPressure(), 0.0);
        assertEquals(original.getMaxPressure(), copy.getMaxPressure(), 0.0);

        // Verificamos el valor de la descripción
        assertEquals(original.getDescription(), copy.getDescription());

        // Verificamos que no es la misma instancia (referencia)
        assertNotSame(original.getDescription(), copy.getDescription());
    }

    @Test(expected = NullPointerException.class)
    public void testCopyConstructorWithNullTyreType() {
        
        TyreType copy = new TyreType(null);
        fail("Expected a NullPointerException");
    }

    @Test
    public void testCopyConstructorBoundaryValues() {

        TyreType original = new TyreType("Small", Double.MIN_VALUE, Double.MAX_VALUE);
        TyreType copy = new TyreType(original);

        assertEquals(Double.MIN_VALUE, copy.getMinPressure(), 0.0);
        assertEquals(Double.MAX_VALUE, copy.getMaxPressure(), 0.0);
    }

    @Test
    public void testCopyConstructorExtremeDescriptionLength() {
        // Caso con una descripción muy larga
        String longDescription = new String(new char[1000]).replace("\0", "A");
        TyreType original = new TyreType(longDescription, 2.0, 4.0);
        TyreType copy = new TyreType(original);

        // Verificamos que la descripción larga se copia correctamente
        assertEquals(original.getDescription(), copy.getDescription());
        assertNotSame(original.getDescription(), copy.getDescription());
    }
    
    /* -+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+- */
	
	@Test
	public void testToString() {
		TyreType t = new TyreType("185/65 R15",1,4);
		String s = "TyreType 185/65 R15 [1.0,4.0]";
		
		assertEquals(s,t.toString());	
	}
	
	@Test
	public void testEquals() {
		TyreType t1 = new TyreType("185/65 R15",1,4);
		TyreType t2 = new TyreType("185/65 R15",1,4);
		
		assertEquals(t1,t2);
	}
	
	@Test
	public void testGetMinMaxPressure() {
		TyreType t = new TyreType("185/65 R15",1,4);
		
		assertEquals(t.getMinPressure(),1,0);
		assertEquals(t.getMaxPressure(),4,0);
	}
}
