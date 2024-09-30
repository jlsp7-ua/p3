package es.ua.dlsi.prog3.p1;
import java.util.Arrays;

/**
 * Esta clase represenata una cordenada de n dimensiones.
 * La clase es inmutable porque se ha declarado final, no tiene setters,
 * los atributos son privados y definidos con final, no se referencia a ningún objeto
 * externo mutable ni se ofrece una referencia que pueda modificar el contendio
 * a través de los getters.
 * 
 * @author José Luis Solivella Poveda 29521020Z
*/
public final class Coordinate {

    /**
     * Array atributo de la instancia que contiene las coordenadas.
     * Cada objeto Coordinate tendrá sus propias componentes, por eso este atributo es de instancia y no de clase.
     * Para garantizar que sea inmutable se ha establecido como final.
    */
    private final double[] components;

    /**
    *   Constructor sobrecargado de la clase Coordinate.
    *   Crea un objeto Coordinate a partir de un array de coordenadas del que se hace
    *   una copia defensiva para evitar modificaciones desde el código cliente.
    *   Si el array es null, se incializa con longitud 0.
    *   
    *   @param components : paso por referencia del array de componentes, que si es null tendrá longitud 0
    */
    public Coordinate(double[] components) {
        if (components == null) this.components = new double[0];   // Si el parametro es una lista vacía crea una vacía 
        else this.components = components.clone(); // Para evitar referenciar a objetos mutables
    }

    /**
    *   Constructor de copia.
    *   A partir de un objeto Coordinate crea otro con los mismos atributos.
    *   Se utiliza copia defensiva en las componentes para evitar que el código cliente modifique el objeto original.
    *   
    *   @param coordinate : paso por referencia de un objeto Coordinate
    */
    public Coordinate(Coordinate coordinate) {
        this.components = coordinate.components.clone(); // Copia defensiva de las componentes para inmutabilidad
    }

    /**
    *   Método getter de las componentes.
    *   Método para obtener las componentes que conforman el array components.
    *   Para evitar modificaciones en el objeto se devuelve una copia defensiva.
    *   
    *   @return Devuelve la refencia a una copia del array de coordenadas del objeto.
    */
    public double[] getComponents() {
        return components.clone();	// Devolvemos una copia de las componentes para asegurar la inmutabilidad
    }

    /**
    *   Método get dimensiones.
    *   Método que calcula en qué dimensión se encuentra la coordenada a partir del array components.
    *   
    *   @return Utiliza el método 'length' de los arrays para devolver el número de dimensiones de la coordenada.
    */
    public int getDimensions() {
        return components.length;	// Devolvemos la longitud del array de componentes, que son las dimensiones
    }
    /**
    *   Método hashCode.
    *   Método que genera el hashcode asociado al array components.
    *   @return Devuelve el hashCode de la coordenada gracias al método hashCode los arrays.
    */
	@Override
	public int hashCode() {
		final int prime = 31;	// Establecemos un número primo
		int result = 1;	// Establecemos un resultado
		result = prime * result + Arrays.hashCode(components);	// Calculamos el resultado
		return result;	// Devolvemos el resultado
	}

	/**
	 *	Método equals.
	 *	Método que comprueba si un objeto obj es igual a uno Coordinate,`
	 *	comprobando si la referencia es la misma, si las componentes son iguales
	 *  y por último comparando los objetos directamente.
	 *  
	 *  @param obj que es el objeto a comparar con el objeto Coordinate.
	 *	@return devuelve verdadero si son iguales y falso si no lo son.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;	// Si el objeto pasdo es una referencia al que estamos comparado devolvemos verdadero
		if (!(obj instanceof Coordinate)) return false;	// Si no es un objeto de la clase cCoordinate no puede ser igual
		Coordinate other = (Coordinate) obj;	// Asignamos el objeto al tipo Coordinate
		return Arrays.equals(components, other.components);	// Los comparamos
	}
}
