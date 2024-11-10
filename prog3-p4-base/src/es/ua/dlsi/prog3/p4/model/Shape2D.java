package es.ua.dlsi.prog3.p4.model;

import java.util.Objects;

public abstract class Shape2D {
	/**
	 * Atributo de la clase que indica la posición de la figura
	 * en el canvas.
	 */
	private Coordinate coordinate;
	/**
	 * Constructor por defecto de la clase.
	 * El valor por defecto de las coordenadas es NOT_VALID.
	 */
	protected Shape2D() {
		coordinate = new Coordinate();
	};
	/**
	 * Constructor de la clase sobrecargado.
	 * Emplea copia profunda sobre la coordenada del argumento.
	 * @param c coordenada de la figura.
	 */
	protected Shape2D(Coordinate c) {
		coordinate = new Coordinate(c);
	};
	// Copia profunda
	/**
	 * Constructor de copia de la clase.
	 * Emplea copia profunda sobre la coordenada del objeto a copiar.
	 * @param shape2d es el objeto de esta clase que va a ser copiado.
	 */
	protected Shape2D(Shape2D shape2d) {
		coordinate = new Coordinate(shape2d.getPosition());
	};
	/**
	 * Método que devuelve la coordenada de la figura.
	 * @return una copia profunda del atributo coordenada del objeto.
	 */
	public Coordinate getPosition() {return new Coordinate(coordinate);};
	/**
	 * Método que mueve la figuar a la posición que se le pasa como argumento.
	 * @param c posición a la que se desplazará la figura.
	 * @return vieja_coordenada coordenada en la que estaba la figura antes del método.
	 */
	public Coordinate move(Coordinate c) {
		Coordinate vieja_coordenada = new Coordinate(coordinate);
		if (c != null) coordinate = new Coordinate(c);
		return vieja_coordenada;
	};
	/**
	 * Método equals.
	 * @return devuelve cierto si las figuras comparadas tienen posiciones iguales.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(coordinate);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)return true;
		if (!(obj instanceof Shape2D))return false;
		Shape2D other = (Shape2D) obj;
		return Objects.equals(coordinate, other.coordinate);
	}
	/**
	 * Método que devuelve las coordenadas en un string.
	 * @return Coordenadas en formato "(X,Y)".
	 */
	public String toString() {return "(" + coordinate.getX() + "," + coordinate.getY() + ")";}
	/**
	 * Método abstracto que deben implementar las subclases de Shape2D
	 * y que devuelve una copia de la figura.
	 */
	public abstract Shape2D clone();
	/**
	 * Método clone no abstracto que realiza una clonación en otra posición.
	 * @param c posición en la que se hallará la clonación.
	 * @return clon se devuelve la figura clonada en otra posición.
	 */
	public Shape2D clone(Coordinate c) {
		Shape2D clon = clone();
		clon.move(c);
		return clon;
	}
	/**
	 * Método abstracto que deben implementar las subclases de Shape 2D
	 * para escalar las dimensiones de la figura. Cuando el argumento es 100
	 * el tamaño de la figura no se ve alterado, si es menor disminuye y si es mayor
	 * aumenta. Si el porcentaje es negativo o 0 lanza IllegalArgumentException.
	 * @param s
	 */
	public abstract void scale(double s);
}
