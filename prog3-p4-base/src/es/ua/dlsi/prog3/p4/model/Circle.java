package es.ua.dlsi.prog3.p4.model;

/**
 * Clase que hereda de la clase Shape2D. Modela un círculo al conocer
 * la posición y el radio.
 *
 * @solve
 */

public class Circle extends Shape2D {

    /**
     * Atributo de instancia.
     * Indica el radio del círculo.
     */
    private double radius;

    /**
     * Constructor por defecto de la clase.
     * Establece la posición como inválida y el radio a 0.
     */
    public Circle() {
        super();
        radius = 0.0;
    }

    /**
     * Constructor sobrecargado de la clase.
     * @param c coordenada en la que se creará el círculo.
     * @param r radio del círculo que se va a crear.
     */
    public Circle(Coordinate c, double r) {
        super(c);
        if (r < 0) throw new IllegalArgumentException();
        radius = r;
    }

    /**
     * Método getter del atributo radio.
     * @return radius que es el radio del círculo.
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Constructor de copia.
     * @param c es el objeto de clase cículo que pensamos copiar.
     */
    public Circle(Circle c) {
        super(c.getPosition());
        radius = c.getRadius();
    }

    /**
     * Método de instancia que escala el círculo.
     * @param s es el factor para escalar la figura. 100 deja
     * la figura como está.
     */
    public void scale(double s) {
        if (s <= 0) throw new IllegalArgumentException();
        radius *= s / 100.0;
    }

    /**
     * Implementación del método abstracto de la superclase clone().
     * Clona los objetos de la clase círculo.
     * @return clon del objeto círculo sobre el que se aplica el método.
     */
    @Override
    public Shape2D clone() {
        return new Circle(this);
    }

    /**
     * Método que imprime las características de el círculo.
     * @return string con la forma (x,y),radius=r
     */
    public String toString() {
        return super.toString() + ",radius=" + getRadius();
    }

    /**
     * Método equals para los objetos de la clase Circle.
     * Compara un objeto con uno de la clase Circle para ver si son el mismo.
     * @param obj es el objeto con el que será coparado el objeto Circle
     * @return un booleano que será verdadero si los objetos son iguales y false si no
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (!(obj instanceof Circle)) return false;
        Circle other = (Circle) obj;
        return (
            Double.doubleToLongBits(radius) ==
            Double.doubleToLongBits(other.radius)
        );
    }
}
