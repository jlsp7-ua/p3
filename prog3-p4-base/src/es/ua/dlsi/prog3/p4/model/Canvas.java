package es.ua.dlsi.prog3.p4.model;

import java.util.ArrayList;

/**
 * Canvas representa un lienzo que contiene figuras geométricas
 * bidimensionales. Este contiene una serie de figuras, tiene un título
 * y un tamaño.
 * @author solve
 */

public class Canvas {

    /**
     * Atributo de clase con el valor por defecto del tamaño.
     */
    public static final float DEFAULT_SIZE = 1000;
    /**
     * Atributo de instancia con el título del canvas.
     */
    private String title;
    /**
     * Atributo de instancia que indica el ancho del canvas.
     */
    private double width;
    /**
     * Atributo de instancia que indica el alto del canvas.
     */
    private double height;
    /**
     * ArrayList que almacena las figuras que se encuentran en el canvas.
     */
    private ArrayList<Shape2D> shapes;

    /**
     * Constructor por defecto de la clase que establece como título
     * "default canvas", al ancho y alto a el por defecto dle atributo
     * de clase e incializa el arrayList de figuras.
     */
    public Canvas() {
        title = "default canvas";
        width = DEFAULT_SIZE;
        height = DEFAULT_SIZE;
        shapes = new ArrayList<Shape2D>();
    }

    /**
     * Constructor sobrecargado para poder especificar el título,
     * el ancho y el alto.
     * @param title será el título del canvas.
     * @param w será el ancho del canvas.
     * @param h será el alto del canvas.
     */
    public Canvas(String title, double w, double h) {
        if (w < 0 || h < 0) throw new IllegalArgumentException();
        this.title = title;
        this.width = w;
        this.height = h;
        shapes = new ArrayList<Shape2D>();
    }

    /**
     * Método para añadir figura a los canvas.
     * @param s2 figura que se añadira al canvas.
     */
    public void addShape(Shape2D s2) {
        shapes.add(s2.clone());
    }

    /**
     * Constructor de copia para los objetos de clase canvas.
     * @param c objeto que será copiado.
     */
    public Canvas(Canvas c) {
        title = c.title;
        width = c.width;
        height = c.height;
        shapes = new ArrayList<Shape2D>();
        for (Shape2D s2 : c.shapes) {
            addShape(s2);
        }
    }

    /**
     * Método clone de la clase canvas.
     * @return devuelve una copia del objeto Canvas sobre el que se aplica.
     */
    public Canvas clone() {
        return new Canvas(this);
    }

    /**
     * Método de instancia que devuelve el número de figuras que tiene el canvas.
     * @return longitud del ArrayList que almacena los objetos Shape2D.
     */
    public int getNumShapes() {
        return shapes.size();
    }

    /**
     * Método de instancia que devuelve el ancho del canvas.
     * @return width atributo que describe el ancho del objeto canvas.
     */
    public double getWidth() {
        return width;
    }

    /**
     * Método de instancia que devuelve el alto del canvas,.
     * @return height que es el atributo que guarda la altura del canvas
     */
    public double getHeight() {
        return height;
    }

    /**
     * Método que devuelve una figura en concreto.
     * @param pos parámetro que indica la posición en el ArrayList que almacena las shapes.
     * @return Shape que ocupa la posición pasada por el argumento.
     */
    public Shape2D getShape(int pos) {
        if (
            pos < 1 || pos > getNumShapes()
        ) throw new IndexOutOfBoundsException();
        return shapes.get(pos - 1).clone();
    }

    /**
     * Método que sirve para eliminar una de las figuras del ArrayList que las almacena.
     * @param pos posición en el ArrayList de la figura que vamos a eliminar.
     */
    public void removeShape(int pos) {
        if (
            pos < 1 || pos > shapes.size()
        ) throw new IndexOutOfBoundsException();
        shapes.remove(pos - 1);
    }

    /**
     * Método de instancia que devuelve el valor de los atributos del canvas en forma de string.
     * @return string con la forma "Prog3 (1024.0,768.0) with 3 shapes"
     */
    @Override
    public String toString() {
        return (
            this.title +
            " (" +
            this.width +
            "," +
            this.height +
            ") with " +
            this.getNumShapes() +
            " shapes"
        );
    }
}
