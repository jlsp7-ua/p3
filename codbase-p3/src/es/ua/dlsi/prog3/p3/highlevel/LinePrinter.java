package es.ua.dlsi.prog3.p3.highlevel;

import es.ua.dlsi.prog3.p3.highlevel.exceptions.NoLineForPrintingException;
import es.ua.dlsi.prog3.p3.lowlevel.*;

/**
 *  Este dispositivo espera que en su buffer se encuentre una cadena de caracteres
 *  con el siguiente formato: el primer byte indica el número de bytes que hay
 *  a continuación en el buffer, los cuales representan caracteres ASCII imprimibles.
 *  
 *  @author JOSE LUIS SOLIVELLA POVEDA 29521020Z
 */
public class LinePrinter extends OutputDevice{
	/**
	 * El atributo de clase constante marca la longitud del buffer
	 * de todos los dispositivos de este tipo.
	 */
	private static final int MAX_LINE_LENGTH = 80;
	
	/**
	 * El constructor por defecto debe ser sobrecargado porque
	 * es un dispositivo de salida y requiere del tamaño del buffer.
	 */
	public LinePrinter() {super(MAX_LINE_LENGTH+1);};
	
	/**
	 * Devuelve un string con la cadena leída del buffer.
	 * @throws NoLineForPrintingException se lanza cuando no hay ninguna linea que leer del canal por el que recibe los datos el LinesPrinter
	 * @return linea la cadena leída del buffer
	 */
	public String printLine() throws NoLineForPrintingException {
		Channel canal = getChannel();
		if (canal == null) throw new IllegalStateException();
		if (!canal.hasData()) throw new NoLineForPrintingException();
		String linea = readStoredString(); // Puede lanzar BufferUnderflowException
		return linea;
	};
}
