package es.ua.dlsi.prog3.p4.model;

public class Shape2DFactory {
	public static Shape2D createShape2D(String clase) {	
		if (clase.equals("Circle")) return new Circle();
		if (clase.equals("Square")) return new Square();
		if (clase.equals("Rectangle")) return new Rectangle();
		throw new IllegalArgumentException("El nombre de la figura nos en Circle, ni Square ni Rectangle.");
	}
}
