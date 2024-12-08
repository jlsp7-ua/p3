package es.ua.dlsi.prog3.p2.exceptions;

/**
 * Excepción para indicar que el tipo de neumático es erróneo.
 * @author JOSE LUIS SOLIVELLA POVEDA 29521020Z
 * */

public class WrongTyreTypeException extends Exception {
	/**
	 * Constructor por defecto.
	 */
	public WrongTyreTypeException() {
        super(); // Llamamos al constructor de la superclase
    }
	/**
     * Constructor con mensaje.
     * @param message contiene información extra sobre la excepción
     */
    public WrongTyreTypeException(String message) {
        super(message);	// Llamamos al constructor de la superclase
    }
}