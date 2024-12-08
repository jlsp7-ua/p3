package es.ua.dlsi.prog3.p2.test;

import es.ua.dlsi.prog3.p2.model.TyreType;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TyreTypeTest {
	private String description;
	private double min_pressure;
	private double  max_pressure;

	@Before
    public void setUp() {
		description = "invierno";
		min_pressure = 4.5;
		max_pressure = 6.0;
	}
	@Test
	public void ConstructorSobrecargadoNormal() {
		TyreType t = new TyreType(description, min_pressure, max_pressure);
		assertEquals("Descripción", "invierno", t.getDescription());
		assertEquals("Presión mínima", 4.5, t.getMinPressure(), 0.001);
		assertEquals("Presión máxima", 6.0, t.getMinPressure(), 0.001);
	}
	@Test(expected = IllegalArgumentException.class)
	public void ConstructorSobrecargadoDescripcion() {
		TyreType tt = new TyreType(null,2.0,3.0);
	}
	@Test(expected = IllegalArgumentException.class)
	public void ConstructorSobrecargadoPresiones() {
		TyreType tt = new TyreType("veranito",7.0,1.0);
	}
	/*
	@Test
	public void ConstructoCopia() {
		TyreType t = new TyreType(description, min_pressure, max_pressure);
		TyreType tt = TyreType(tt);
		
	}*/

}
