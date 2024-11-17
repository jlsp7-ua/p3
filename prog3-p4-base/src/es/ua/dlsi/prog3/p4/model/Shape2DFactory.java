package es.ua.dlsi.prog3.p4.model;

/**
 * Clase factoría, es la que crea las figuras de la jerarquía Shape2D.
 * No tiene ningún parámetro solo un método.
 * @author solve
 */
public class Shape2DFactory {

    /**
     * Este método crea y devuelve la figura solicitada usando el constructor
     * por defecto de su clase. El parámetro del método contendrá el nombre
     * de la clase de figura a crear (Circle, Square o Rectangle). Lanza la excepción
     * IllegalArgumentException si el nombre de la figura no es Circle, Square o Rectangle.
     * @param clase es el string con el nombre de la clase
     * @return devuelve la figura correspondiente a la clase que se solicita por el argumento.
     */
    public static Shape2D createShape2D(String clase) {
        if (clase == null) throw new IllegalArgumentException(
            "El argumento 'clase' no puede ser null."
        );
        if (clase.equals("Circle")) return new Circle();
        if (clase.equals("Square")) return new Square();
        if (clase.equals("Rectangle")) return new Rectangle();
        throw new IllegalArgumentException(
            "El nombre de la figura nos en Circle, ni Square ni Rectangle."
        );
    }
}
