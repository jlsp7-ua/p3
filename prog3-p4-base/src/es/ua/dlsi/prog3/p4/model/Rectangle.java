package es.ua.dlsi.prog3.p4.model;

public class Rectangle extends AbstractPolygon {
	private double length;
	private double width;
	
	public Rectangle() {
		super();
		length = 0;
		width = 0;
	}
	public Rectangle(Coordinate c, double ang, double l, double w) {
		super(c, ang);
		if (l<0 || w<0) throw new IllegalArgumentException();
		length = l;
		width = w;
	}
	
	public double getLength() {return length;}
	public double getWidth() {return width;}
	
	public Rectangle(Rectangle r) {
		super(r); // .getPosition(),r.getAngle()
		length = r.getLength();
		width = r.getWidth();
	}
	
	@Override
	public Shape2D clone() {return new Rectangle(this);}

	@Override
	public void scale(double s) {
		if (s <= 0) throw new IllegalArgumentException();
		length *= s/100;
		width *= s/100;
	}
	
	public String toString() {return super.toString()+",length="+getLength()+",width="+getWidth();}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!super.equals(obj)) return false;
		if (!(obj instanceof Rectangle)) return false;
		Rectangle other = (Rectangle) obj;
		return Double.doubleToLongBits(length) == Double.doubleToLongBits(other.length)
				&& Double.doubleToLongBits(width) == Double.doubleToLongBits(other.width);
	}
}
