package es.ua.dlsi.prog3.p2.exceptions;

/**
 * Excepción para indicar que una rueda no tiene un tipo
 * de rueda asignado.
 * @author JOSE LUIS SOLIVELLA POVEDA 29521020Z
 * */

public class NoTyreTypeException extends Exception {
    /**
     * Constructor por defecto.
     */
    public NoTyreTypeException() {
        super();	// Llamamos al constructor de la superclase
    }

    /**
     * Constructor con mensaje.
     * @param message contiene información extra sobre la excepción
     */
    public NoTyreTypeException(String message) {
        super(message);	// Llamamos al constructor de la superclase
    }
}
