package es.ua.dlsi.prog3.p2.exceptions;

/**
 * Excepción que indica que la presión que se le está intentando
 * aplicar a una rueda no es correcta por exceder los límites.
 * @author JOSE LUIS SOLIVELLA POVEDA 29521020Z
 * */

public class PressureWheelException extends Exception {
    private final double pressure;

    /**
     * Constructor sobrecargado de la clase.
     * Llama al constructor de la superclase y le da valor al
     * atributo pressure.
     * @param pressure : double que indica la presión por la que
     * se está lanzando la excepción.
     * */
    public PressureWheelException(double pressure) {
        super();    // Llamamos al constructor de la superclase
        this.pressure = pressure;	// Establecemos el valor de presión de la excepción
    };

    /**
     * Método de instancia que devuelve un string con la presión
     * que ha provocado el lanzamiento de la excepción.
     * @return cadena de texto que indica la presión que lanzó la excepción
     * */
    @Override
    public String getMessage() {
        return "Pressure of " + pressure + " BAR";
    };
}
