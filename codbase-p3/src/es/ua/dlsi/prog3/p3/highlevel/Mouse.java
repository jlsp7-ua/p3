package es.ua.dlsi.prog3.p3.highlevel;

import java.nio.BufferOverflowException;

import es.ua.dlsi.prog3.p3.lowlevel.*;

/**
 * Clase ratón que permite enviar coordenadas 2D de la
 * posición del cursor en la pantalla. Es un dispositivo
 * de entrada salida por tanto no necesita el constructor sobrecargado.
 * 
 * @author JOSE LUIS SOLIVELLA POVEDA 29521020Z
 */
public class Mouse extends InputDevice {
	/**
	 * Constructor por defecto de la clase que
	 * utilaza el constructor de la superclase.
	 */
	public Mouse() {super();}
	
	/**
	 * Envía dos bytes al canal que representan
	 * las coordenads 2D de la posición del cursor en la pantalla.
	 * @param x posición en el eje X
	 * @param y posición en el eje Y
	 * @throws IllegalStateException si el dispositivo no tiene canal asociado
	 * @throws BufferOverflowException si el canal ya está lleno
	 */
	public void put(byte x, byte y) {
		Channel canal = getChannel();
		if (canal == null) throw new IllegalStateException();
		if (canal.isFull()) throw new BufferOverflowException();
		
		sendToChannel(x);
		try {
			sendToChannel(y);
		} catch (BufferOverflowException e) {
			throw new BufferOverflowException();
		}
	}
}
