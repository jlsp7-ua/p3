package es.ua.dlsi.prog3.p4.model;

/**
 * Clase abstracta que hereda de la clase Shape2D.
 * Clase base de los polígonos.
 * No define nuevos métodos abstractos y los que hereda no los implementa.
 * @author solve
 */
public abstract class AbstractPolygon extends Shape2D {

    /**
     * Atributo de instancia que indica el ángulo de inclinación del
     * polígono.
     */
    private double angle;

    /**
     * Constructor por defecto de la clase.
     * Utiliza el constructor de la superclase para que su posición sea
     * no válida y establece el ángulo de inclinación como 0
     */
    protected AbstractPolygon() {
        super();
        angle = 0;
    }

    /**
     * Constructor sobrecargado de la clase.
     * @param c coordenadas en la que será creado el polígono.
     * @param a ángulo de inclinación que tendrá el polígono.
     */
    protected AbstractPolygon(Coordinate c, double a) {
        super(c);
        if (a < 0.0 || a >= 360.0) throw new IllegalArgumentException();
        angle = a;
    }

    /**
     * Método de instancia getter del atributo ángulo.
     * @return angle que es el valor del atributo que marca el ángulo.
     */
    public double getAngle() {
        return angle;
    }

    /**
     * Constructor de copia de la clase.
     * @param absP objeto de la clase AbstractPolygon que sera copiado.
     */
    protected AbstractPolygon(AbstractPolygon absP) {
        super(absP);
        angle = absP.getAngle();
    }

    /**
     * Método de instancia que de vuelve los valores de los atributo del
     * objeto con un formato concreto.
     * @return devuélve un string con la forma (x,y),angle=angle
     */
    public String toString() {
        return super.toString() + ",angle=" + this.getAngle();
    }

    /**
     * Método de instancia que compara un objeto de la clase con un objeto
     * cualquiera para saber si son iguales.
     * @return devuelve true si son iguales, sino false
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (!(obj instanceof AbstractPolygon)) return false;
        if (getClass() != obj.getClass()) return false;
        AbstractPolygon other = (AbstractPolygon) obj;
        return (
            Double.doubleToLongBits(angle) ==
            Double.doubleToLongBits(other.angle)
        );
    }

    /**
     * Método que rota al polígono según los grados del argumento.
     * @param r ángulo en el que el polígono será rotado
     */
    public void rotate(double r) {
        if (r <= -360.0 || r >= 360.0) throw new IllegalArgumentException();
        angle += r;
        if (angle < 0.0) angle += 360;
        else if (angle >= 360.0) angle -= 360;
    }
}
