package es.ua.dlsi.prog3.p4.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class FactoryFigurasTest {

	@Test
	public final void testCrearFiguraCirculo() throws Exception {
		Shape2D f = Shape2DFactory.createShape2D("Circle");
		assertTrue(f instanceof Circle);
	}

}
