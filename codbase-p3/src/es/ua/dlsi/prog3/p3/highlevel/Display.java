package es.ua.dlsi.prog3.p3.highlevel;

import java.nio.BufferUnderflowException;

import es.ua.dlsi.prog3.p3.lowlevel.*;

/**
 * Representa una pantalla cuadrada de NxN píxeles en blanco y negro.
 * Funciona mediante una matriz de bytes que cuando el valor del byte
 * está a 0 el pixel está encendido y cuando tiene un valor distinto
 * de 0 está desactivado.
 * 
 * @author JOSE LUIS SOLIVELLA POVEDA 29521020Z
 */
public class Display extends OutputDevice {
	/**
	 * Tamaño de de la pantalla cuadrada.
	 */
	private int pixel_rows;
	
	/**
	 * Matriz de bytes de la pantalla donde 0 es blanco y cualquier
	 * otro valor es negro. La coordenada (0,0) es la esquina superior izquierda.
	 */
	private byte[][] display;
	
	/**
	 * El constructor por defecto debe ser sobrecargado porque
	 * es un dispositivo de salida y requiere como mínimo del tamaño del buffer.
	 * Pero en este caso también del tamaño de pantalla. El búffer tendrá un tamaño
	 * de NxN*2.
	 * @param N es el parametro que marca el tamaño de la pantalla y también el buffer
	 */
	public Display(int N) {
		super(N*N*2);
		pixel_rows = N;
		display = new byte[N][N];
	}
	
	/**
	 * Método que devuelve el tamaño de la pantalla.
	 * @return pixel_rows tamaño de la pantalla
	 */
	public int getDisplaySize() {return pixel_rows;};
	
	/**
	 * Desactiva todos los píxeles de la pantalla, es decir, le asigna
	 * un valor distinto 0 a cada uno de los bytes para que sean interpretados
	 * como negros.
	 */
	public void clear() {
		for(int j=0; j<pixel_rows; j++) {
			for (int k=0; k<pixel_rows; k++) {
				display[j][k] = 1;
			}
		}
	}
	
	/**
	 * Espera encontrar en el canal las coordenadas de los pixeles de la pantalla
	 * que deben activarse. Esas coordenadas de la matriz display las pondrá a 0
	 * mientras las demás a cualquier valor distinto de 0. Si el canal no contiene
	 * datos no modificará la matriz display.
	 * @return copia_display una copia defensiva de la matriz display
	 */
	public byte[][] refresh() {
		Channel canal = getChannel();
		if (canal == null) throw new IllegalStateException();
		byte[] buffer = get(pixel_rows*pixel_rows*2);
		if (buffer.length < 2) throw new BufferUnderflowException();
		clear();	// Limpiamos la pantalla
		int x, y;
		for (int i=0; i<(pixel_rows*pixel_rows*2); i+=2) {
			x = buffer[i];
			y = buffer[i+1];
			if (0>x || x>pixel_rows || 0>y || y>pixel_rows) throw new IndexOutOfBoundsException("Las coordenadas de los pixeles están fuera del tamaño de la matriz de la pantalla.");
			display[x][y] = 0;
		}
		byte[][] copia_display = new byte[pixel_rows][pixel_rows];
		for(int j=0; j<pixel_rows; j++) {
			for (int k=0; k<pixel_rows; k++) {
				copia_display[j][k] = display[j][k];
			}
		}
		return copia_display;
	};
}
