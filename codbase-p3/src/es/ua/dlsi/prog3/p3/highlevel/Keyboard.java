package es.ua.dlsi.prog3.p3.highlevel;

import java.nio.BufferOverflowException;

import es.ua.dlsi.prog3.p3.lowlevel.*;

/**
 * Representa a los dispositivos de entrada de tipo teclado.
 * El teclado permite enviar caracteres uno a uno al canal
 * para su posterior lectura.
 * 
 * @author JOSE LUIS SOLIVELLA POVEDA 29521020Z
 */
public class Keyboard extends InputDevice {
	/**
	 * Constructor de la clase a partir del constructor
	 * de la superclase InputDevice.
	 */
	public Keyboard() {super();}
	
	/**
	 * Envía un carácter al canal asociado.
	 * @param a caracter que se enviará al canal
	 * @throws IllegalStateException si el dispositivo no tiene canal asociado
	 * @throws BufferOverflowException si el canal ya está lleno
	 */
	public void put(char a) {
		Channel canal = getChannel();
		if (canal == null) throw new IllegalStateException();
		if (canal.isFull()) throw new BufferOverflowException();
		sendToChannel((byte)a);
	}
}
