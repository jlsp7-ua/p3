package es.ua.dlsi.prog3.p1;
import java.util.ArrayList;

/**
 * Clase Summary Statisticas.
 * Representa las estadísticas sobre un array de la calase Integer.
 * Entre los cálculos se encuentran el máximo, mínimo y media. 
 * Además, permite agregar nuevos valores al array y contar cuantos son.
 * Se utiliza la copia defensiva para evitar que el arraylist orignila sea modificado por el código cliente.
 * @author José Luis Solivella Poveda 29521020Z
*/
public class SummaryStatistics {
	/**
	 * Atributo de la clase que almacena el id que será asignado a la próxima instancia de la clase.
	 * Todos los objetos pueden acceder a este atributo que tiene el mismo valor para todos al ser atributo de clase.
	 */
    private static int NEXT_ID = 1;
    /**
     * Atributo de instancia que indica el id del objeto.
     * Cada objeto tiene su propio id, por eso es un atributo de instancia.
     */
    private int id;
    /**
     * Atributo de instancia que contiene el array de valores sobre los que se calculará la estadística.
     * Es de instancia porque cada objeto contiene unos valores diferentes dentro del array.
     */
    private ArrayList<Integer> values;
    /**
     * Constructor por defecto de la clase.
     * Construye un objeto SummaryStatistics asignńdole un id y creando una lista vacía.
     * No requiere de ningún parámetro.
     */
    public SummaryStatistics() {
        id = NEXT_ID++;	// Asignamos el id y aumentamos NEXT_ID
        values = new ArrayList<>();	// Creamos un arraylist vacío
    }
    /**
     * Constructor sobrecargado de la clase conociendo el arraylist de valores inicial.
     * Se usa cuando disponemos desde el primer momento de los valores sobre los que queremos obtener las estadísticas..
     * Se usa copia defensiva en el arraylist de valores.
     * 
     * @param values ArrayList que contiene los enteros para crear el objeto
     */
    public SummaryStatistics(ArrayList<Integer> values) {
        id = NEXT_ID++;	// Asignamos el id y aumentamos NEXT_ID
        this.values = new ArrayList<>(values); // Copia defensiva del arraylist de valores
    }
    /**
     * Constructor de copia.
     * Construye un objeto SummaryStatistics a partir de la copia defensiva de otro.
     * La copia defensiva o copia profunda evita que se modifique el objeto pasado por parámetro. 
     * 
     * @param ss Objeto SummaryStatistics
     */
    public SummaryStatistics(SummaryStatistics ss) {
        this.id = NEXT_ID++;	// Asignamos el id y aumentamos NEXT_ID
        this.values = new ArrayList<>(ss.values); // Copia defensiva del arraylist de valores del objeto pasado
    }
    /**
     * Método add.
     * Método para añadir valores al array values para realizar la estadística.
     * @param value entero que se añadirá al arraylist values
     */
    public void add (int value) {
        values.add(value); // Añade un valor al arraylist de valores
    }
    /**
     * Método que getID.
     * Método getter del atributo id del objeto.
     * @return id del objeto actual (int)
     */
    public int getId() {
        return id; // retorna el valor del id
    }
    /**
     * Método que getAvarage.
     * Método que calcula la media de los valores del array values.
     * 
     * @return Media de valores Integer.
     */
    public Integer getAverage() {
        if (values.isEmpty()) return null; // Si el array esta vacío, devolvemos null
        int suma = 0;	// Establecemos la suma en 0
        for (Integer num: values) {	// Para cada valor del array
            suma += num.intValue();	// lo sumamos, haciendo unbocxhing del valor
        }
        return suma/values.size();	// Calculamos la media
    }
    /**
     * Método getMax.
     * Método que iterando sobre el array de valores encuentra el valor máximo.
     * Si el array está vacío devolverá null.
     * Hace unboxing al usar Math::max.
     * 
     * @return Devuelve el Integer máximo.
     */
    public Integer getMax() {
        return values.isEmpty() ? null : values.stream().reduce(Integer.MIN_VALUE, Math::max); // Tomamos `Integer.MIN_VALUE` para asegurarnos que coge el primer elemento de `values`
    }
    /**
     * Método getMin.
     * Método que iterando sobre el array de valores encuentra el valor mínimo.
     * Si el array está vacío devolverá null.
     * Hace unboxing al usar Math::min.
     * 
     * @return Devuelve el Integer mínmo.
     */
    public Integer getMin() {
        return values.isEmpty() ? null : values.stream().reduce(Integer.MAX_VALUE, Math::min);	// Tomamos `Integer.MAX_VALUE` para asegurarnos que coge el primer elemento de `values`
    }
    /**
     * Método getSize.
     * Método que calcula el tamaño del array de valores.
     * 
     * @return Devuevle un int con el tamaño del array.
     */
    public int getSize() {
        return values.size();	// Retronamos el valor del tamaño del array
    }
    /**
     * Método de la clase COUTN_INSTANCES
     * Método que cuenta la cantidad de objetos de la clase SummaryTest creados.
     * 
     * @return int número de instancias.
     */
    public static int COUNT_INSTANCES() {
        return NEXT_ID-1;	// Devolvemos el numero de instancias restando 1 al siguiente id
    }
}
