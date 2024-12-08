package es.ua.dlsi.prog3.p2.exceptions;

/**
 * Excepción para indicar que se están intentando insertar más ruedas
 * de las que se pueden.
 * @author JOSE LUIS SOLIVELLA POVEDA 29521020Z
 * */

public class TooManyWheelsException extends Exception {
	/**
	 * Constructor por defecto.
	 */
	public TooManyWheelsException() {
        super();	// Llamamos al constructor de la superclase
    }
	/**
     * Constructor con mensaje.
     * @param message contiene información extra sobre la excepción
     */
    public TooManyWheelsException(String message) {
        super(message);	// Llamamos al constructor de la superclase
    }
}