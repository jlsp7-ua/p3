package es.ua.dlsi.prog3.p4.model;

public class Square extends AbstractPolygon {
	public double side;
	
	public Square() {
		super();
		side = 0.0;
	}
	
	public Square(Coordinate c, double ang, double s) {
		super(c,ang);
		if (s < 0) throw new IllegalArgumentException();
		side = s;
	}
	
	public Square(Square s) {
		super(s); //s.getPosition(), s.getAngle()
		side = s.getSide();
	}
	
	public double getSide() {return side;}
	
	@Override
	public Shape2D clone() {return new Square(this);}

	@Override
	public void scale(double s) {
		if (s <= 0) throw new IllegalArgumentException();
		side *= s/100;
	}
	
	public String toString() {return super.toString() + ",side=" + getSide();}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!super.equals(obj)) return false;
		if (!(obj instanceof Square)) return false;
		Square other = (Square) obj;
		return Double.doubleToLongBits(side) == Double.doubleToLongBits(other.side);
	}	
}
