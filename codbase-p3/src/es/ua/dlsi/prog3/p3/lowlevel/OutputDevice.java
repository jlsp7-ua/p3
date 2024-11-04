
package es.ua.dlsi.prog3.p3.lowlevel;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.util.ArrayList;

/**
 * Clase de la que heredan todos los dispositivos de salida.
 * Cuenta con los métodos necesarios para recibir información
 * de los canales.
 * 
 * @author JOSE LUIS SOLIVELLA POVEDA 29521020Z
 */
public class OutputDevice extends IODevice {
	
	/**
	 * Constructor de la clase que llama al constructor de la superclase
	 * sobrecargado ya que así se determina que es un dispositivo de salida.
	 * Es necesario ya que requerimos de determinar el tamaño del búffer.
	 * @param tamBuffer es el tamaño del canal del dispositivo
	 */
	protected OutputDevice(int tamBuffer) {
		super(tamBuffer);
	};
	/**
	 * Lee un bit del canal asociado.
	 * @throws IllegalStateException si no hay canal asociado al dispositivo.
	 * @throws BufferOverflowException si no hay datos que leer en el canal.
	 * @return el byte leído del canal.
	 */
	protected byte recieveFromChannel() {
		Channel canal = getChannel();
		if (canal == null) throw new IllegalStateException();
		if (!canal.hasData()) throw new BufferUnderflowException();
		return canal.output();
	};
	/**
	 * Lee cómo máximo n bytes del canal asociado.
	 * @param n cantidad de bytes que se leerán como máximo.
	 * @return array array de bytes que contiene cómo máximo n bytes leídos del canal.
	 */
	protected byte[] get(int n) {
		Channel canal = getChannel();
		if (canal == null) throw new IllegalStateException();
		if (!canal.hasData()) throw new BufferUnderflowException();
		// Tomar los bytes del canal en un ArrayList
		ArrayList<Byte> result = new ArrayList<>();
		for (int i=0; i<n && canal.hasData(); i++) {
			result.add(recieveFromChannel());
		}
		// Pasar de ArrayList a array
		byte[] array = new byte[result.size()];
		for (int i=0; i< result.size(); i++) {
			array[i] = result.get(i);
		}
		return array;
	};
	/**
	 * Reads a string from the channel. 
	 * 
	 * The channel MUST contain a string of characteres encoded as
	 * [length][A-Ba-b0-9]+
	 * which means that the first byte is the string length, and
	 * the rest of bytes are the actual string 
	 * 
	 * @return the string read, as an String object, or an empty if there is no data in the channel 
	 * @throws BufferUnderflowException if the channel becomes empty before the whole string is read, i.e. the data in the channel is corrupted
	 * @throws IllegalStateException if there is no channel associated to this device 
	 */
	protected String readStoredString() {
		byte[] data = null;
		char[] string = null;
		data = get(1);
		if (data.length != 1) 
			return "";
		int string_length = data[0];		
		data = get(string_length);
		if (data.length != string_length)
			throw new BufferUnderflowException();
		string = new char[string_length];
		for (int i=0; i < string_length; i++)
			string[i] = (char) data[i];
		return String.valueOf(string);
	}

}
