package es.ua.dlsi.prog3.p4.model;

/**
 * Clase que hereda de AbstractPoolygon que modela los rectángulos.
 * Por esto hereda atributos como posición y ángulo pero específicamente
 * tiene atributos como ancho y largo.
 * @author solve
 */
public class Rectangle extends AbstractPolygon {

    /**
     * Atributo de instancia que define el largo del rectángulo.
     */
    private double length;
    /**
     * Atributo de instancia que define el ancho del rectángulo.
     */
    private double width;

    /**
     * Constructor por defecto de la clase que utiliza el constructor
     * de la superclase y establece el ancho y largo como 0.
     */
    public Rectangle() {
        super();
        length = 0;
        width = 0;
    }

    /**
     * Constructor sobrecargado de la clase.
     * @param c coordenada en la que se crea el rectángulo.
     * @param ang ángulo con el que se crea el polígono.
     * @param l longitud del rectángulo.
     * @param w ancho del rectángulo.
     */
    public Rectangle(Coordinate c, double ang, double l, double w) {
        super(c, ang);
        if (l < 0 || w < 0) throw new IllegalArgumentException();
        length = l;
        width = w;
    }

    /**
     * Método de instancia getter del atributo longitud del rectángulo.
     * @return length atributo longitud del rectángulo
     */
    public double getLength() {
        return length;
    }

    /**
     * Método de instancia getter del atributo ancho del rectángulo.
     * @return width atributo ancho del rectángulo
     */
    public double getWidth() {
        return width;
    }

    /**
     * Constructor de copia de la clase.
     * @param r objeto de tipo rectangle
     */
    public Rectangle(Rectangle r) {
        super(r); // .getPosition(),r.getAngle()
        length = r.getLength();
        width = r.getWidth();
    }

    /**
     * Implementación del método clone para la clase que se declara
     * como abstracta en la super clase Shape2D().
     * @return devuelve una copia del rectángulo sobre el que se aplica.
     */
    @Override
    public Shape2D clone() {
        return new Rectangle(this);
    }

    /**
     * Implementación del método de instancia que se declara
     * como abstracta en la super clase Shape2D(). Escala el rectángulo.
     * @param s es el factor de escala.
     */
    @Override
    public void scale(double s) {
        if (s <= 0) throw new IllegalArgumentException();
        length *= s / 100;
        width *= s / 100;
    }

    /**
     * Método que devuelve los valores de los atributos en un string.
     * @return string con la forma (x,y),angle=a,length=l,width=w
     */
    public String toString() {
        return (
            super.toString() + ",length=" + getLength() + ",width=" + getWidth()
        );
    }

    /**
     * Método que compara un objeto con otro de la clase rectángulo.
     * @return devuelve un booleano que es verdadero si son iguales
     * y falso si no.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (!(obj instanceof Rectangle)) return false;
        if (getClass() != obj.getClass()) return false;
        Rectangle other = (Rectangle) obj;
        return (
            Double.doubleToLongBits(length) ==
                Double.doubleToLongBits(other.length) &&
            Double.doubleToLongBits(width) ==
            Double.doubleToLongBits(other.width)
        );
    }
}
