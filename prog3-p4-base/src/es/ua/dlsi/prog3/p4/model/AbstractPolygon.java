package es.ua.dlsi.prog3.p4.model;

/**
 * Clase abstracta que hereda de la clase Shape2D.
 * Clase base de los polígonos.
 * No define nuevos métodos abstractos y los que hereda no los implementa.
 */
public abstract class AbstractPolygon extends Shape2D{
	private double angle;
	protected AbstractPolygon() {
		super();
		angle = 0.0;
	};
	
	protected AbstractPolygon(Coordinate c, double a) {
		super(c);
		if (a < 0.0 || a >= 360.0) throw new IllegalArgumentException();
		angle = a;
	};
	
	public double getAngle() {return angle;};
	
	protected AbstractPolygon(AbstractPolygon absP) {
		super(absP);
		angle = absP.getAngle();
	};
	
	public String toString() {return super.toString() + ",angle=" + this.getAngle();};
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!super.equals(obj)) return false;
		if (!(obj instanceof AbstractPolygon)) return false;
		AbstractPolygon other = (AbstractPolygon) obj;
		return Double.doubleToLongBits(angle) == Double.doubleToLongBits(other.angle);
	};
	public void rotate(double r) {
		if (r <= -360.0 || r >= 360.0) throw new IllegalArgumentException();
		angle += r;
		if (angle<0.0) angle += 360;
		else if (angle >= 360.0) angle -= 360;
	}
}
