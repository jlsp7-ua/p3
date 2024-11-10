package es.ua.dlsi.prog3.p4.model;

import java.util.ArrayList;

public class Canvas {
	public static final double DEFAULT_SIZE = 1000.0;
	private String title;
	private double width;
	private double height;
	private ArrayList<Shape2D> shapes;
	
	public Canvas() {
		title = "default canvas";
		width = DEFAULT_SIZE;
		height = DEFAULT_SIZE;
		shapes = new ArrayList<Shape2D>();
	}
	
	public Canvas(String title, double w, double h) {
		this.title = title;
		if (w<0 || h<0) throw new IllegalArgumentException();
		this.width = w;
		this.height = h;
		shapes = new ArrayList<Shape2D>();
	}
	
	public void addShape(Shape2D s2) {shapes.add(s2.clone());}
	
	public Canvas(Canvas c) {
		title = c.getTitle();
		width = c.getWidth();
		height = c.getHeight();
		shapes = new ArrayList<Shape2D>();
		for (Shape2D s2 : c.shapes) {
			addShape(s2);
		}
	}
	
	public Canvas clone() {return new Canvas(this);}
	
	public int getNumShapes() {return shapes.size();}
	
	public Shape2D getShape(int pos) {
		if (pos < 1 || pos > getNumShapes()) throw new IndexOutOfBoundsException();
		return shapes.get(pos-1).clone();
	}
	
	public double getWidth() {return width;}
	public double getHeight() {return height;}
	String getTitle() {return new String(title);}
	
	public void removeShape(int pos) {
		if (pos < 1 || pos > getNumShapes()) throw new IndexOutOfBoundsException();
		shapes.remove(pos-1);
	}

	@Override
	public String toString() {
	    return title+" ("+width+","+height+") with "+getNumShapes()+" shapes";
	}
}
