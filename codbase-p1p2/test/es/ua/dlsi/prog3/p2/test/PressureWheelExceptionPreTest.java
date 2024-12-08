package es.ua.dlsi.prog3.p2.test;

import static org.junit.Assert.*;
import org.junit.Test;

import es.ua.dlsi.prog3.p2.exceptions.PressureWheelException;

public class PressureWheelExceptionPreTest {

	static boolean containsString(String str, String substr) {
		String str2 = str.toLowerCase().replaceAll("\\s+", "").replace('s', 'z');
		String substr2 = substr.toLowerCase().replaceAll("\\s+", "").replace('s', 'z');
		
		return str2.contains(substr2);
	}
	
	@Test
	public void testGetMessage() {
		PressureWheelException e = new PressureWheelException(2.5);
		
		String msg = e.getMessage();
		assertTrue(containsString(msg, "2.5"));
	}
}
