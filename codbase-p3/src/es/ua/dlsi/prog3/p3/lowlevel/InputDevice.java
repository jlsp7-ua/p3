package es.ua.dlsi.prog3.p3.lowlevel;

import java.nio.BufferOverflowException;

/**
 * Representa a todos los dispositivos de entrada.
 * Estos se encargan de enviar datos al canal.
 * Incluye al ratón y al teclado.
 * 
 * @author JOSE LUIS SOLIVELLA POVEDA 29521020Z
 */
public class InputDevice extends IODevice {
	/**
	 * Constructor por defecto.
	 * En el llamamos al constructor de la superclase para
	 * así construir un dispositivo de entrada.
	 * */
	protected InputDevice() {
		super();
	};
	/**
	 * Permite enviar un byte al canal asociado.
	 * Antes comprueba si tiene asignado un canal y si está lleno.
	 * @param b byte que será enviado al canal.
	 * @throws IllegalStateException Si no tiene un canal asociado.
	 * @throws BufferOverflowException Si el canal está lleno.
	 */
	protected void sendToChannel(byte b) {
		Channel canal = getChannel();
		if (canal == null) throw new IllegalStateException();
		if (canal.isFull()) throw new BufferOverflowException();
		canal.input(b);
	};
	/**
	 * Permite enviar un array de bytes al canal asociado.
	 * Antes comprueba si tiene asignado un canal y si está lleno.
	 * @param bs array de bytes que será enviado al canal.
	 * @throws IllegalStateException Si no tiene un canal asociado.
	 * @throws BufferOverflowException Si el canal está lleno.
	 */
	protected void put(byte[] bs) {
		Channel canal = getChannel();
		if (canal == null) throw new IllegalStateException();
		for (byte b : bs) {sendToChannel(b);}
	};
}
