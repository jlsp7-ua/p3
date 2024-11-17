package es.ua.dlsi.prog3.p4.model;

/**
 * Clase que hereda de AbstractPoolygon que modela los rectángulos.
 * Por esto hereda atributos como posición y ángulo pero específicamente
 * tiene atributos como ancho y largo.
 * @author solve
 */
public class Square extends AbstractPolygon {

    /**
     * Atributo de instancia que define el largo del lado del cuadrado.
     */
    private double side;

    /**
     * Constructor por defecto de la clase que utiliza el constructor
     * de la superclase y establece el ancho del lado.
     */
    public Square() {
        super();
        side = 0.0;
    }

    /**
     * Constructor sobrecargado de la clase.
     * @param c coordenada en la que se crea el rectángulo.
     * @param ang ángulo con el que se crea el polígono.
     * @param s longitud del lado.
     */
    public Square(Coordinate c, double ang, double s) {
        super(c, ang);
        if (s < 0) throw new IllegalArgumentException();
        side = s;
    }

    /**
     * Constructor de copia de la clase.
     * @param s objeto de tipo square.
     */
    public Square(Square s) {
        super(s); //s.getPosition(), s.getAngle()
        side = s.getSide();
    }

    /**
     * Método de instancia que devuelve el valor del atributo side.
     * @return side ancho del lado del cuadrado.
     */
    public double getSide() {
        return side;
    }

    /**
     * Método de instancia que debía ser implementado por ser declarado
     * en la clase Shape2D.
     * @return copia del objeto Square sobre el que se aplica.
     */
    @Override
    public Shape2D clone() {
        return new Square(this);
    }

    /**
     * Implementación del método abstracto de la clase Shape2D.
     * Escala el lado del cuadrado.
     * @param s el factor de escala que se aplica.
     */
    @Override
    public void scale(double s) {
        if (s <= 0) throw new IllegalArgumentException();
        side *= s / 100;
    }

    /**
     * Método que devuelve los valores de los atributos en un string.
     * @return string con la forma (x,y),angle=a,side=s
     */
    public String toString() {
        return super.toString() + ",side=" + getSide();
    }

    /**
     * Método que compara un objeto con otro de la clase cuadrado.
     * @return devuelve un booleano que es verdadero si son iguales
     * y falso si no.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (!(obj instanceof Square)) return false;
        if (getClass() != obj.getClass()) return false;
        Square other = (Square) obj;
        return (
            Double.doubleToLongBits(side) == Double.doubleToLongBits(other.side)
        );
    }
}
