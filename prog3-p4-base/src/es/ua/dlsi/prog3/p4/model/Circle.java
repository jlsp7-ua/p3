package es.ua.dlsi.prog3.p4.model;

public class Circle extends Shape2D {
	private double radius;
	
	public Circle() {
		super();
		radius = 0.0;
	}
	
	public Circle(Coordinate c, double r) {
		super(c);
		radius = r;
	}
	
	public double getRadius() {return radius;}
	
	public Circle(Circle c) {
		super(c.getPosition());
		radius = c.getRadius();
	}
	
	public void scale(double s) {
		if (s <= 0) throw new IllegalArgumentException();
		radius *= s/100.0;
	}
	
	@Override
    public Shape2D clone() {
        Circle clonedCircle = new Circle(this.getPosition(), this.radius); // Copia la posición actual
        return clonedCircle;
    }
	
	public String toString() {return super.toString() + ",radius=" + getRadius();}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!super.equals(obj)) return false;
		if (!(obj instanceof Circle)) return false;
		Circle other = (Circle) obj;
		return Double.doubleToLongBits(radius) == Double.doubleToLongBits(other.radius);
	}
	
}
