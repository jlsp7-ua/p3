package es.ua.dlsi.prog3.p2.model;

import es.ua.dlsi.prog3.p2.exceptions.*;

/**
 * Esta clase representa a los objetos rueda, que se relacionarán mediante
 * composición con la clase Car, pero que se relaciona por agregación con
 * TyreType. Es decir, no se realizará copia profunda de TyreType y los objetos
 * de esta clase serán copiados profundamente en un atributo de la clase Car.
 * 
 * @author JOSE LUIS SOLIVELLA POVEDA
 */
public final class Wheel {
    /**
     * Atributo de instancia que indica qué presión tiene actualmente la rueda.
     * Esta debe estar entre el máximo y mínimo marcado por el tipo.
     */
    private double pressure;
    
    /**
     * Atributo de instancia que indica cuál es el tipo de rueda. Aquí es donde
     * se establece la relación. Para que sea de agregación, en los métodos que
     * asignen el tipo de rueda deberán hacerlo con copia superficial.
     */
    private TyreType tyreType;

    /**
     * Constructor por defecto que establece la presión en 0 y el tipo de neumático
     * como null.
     */
    public Wheel () {
        pressure = 0.0;  // establece presión a 0
        tyreType = null;  // establece el tipo de neumático como null
    }

    /**
     * Constructor sobrecargado que establece la presión en 0 pero se le asigna
     * el tipo de neumático pasado por parámetro.
     * 
     * @param tt El tipo de neumático que tendrá la rueda.
     */
    public Wheel (TyreType tt) {
        pressure = 0.0;  // Al ser un tipo nativo no requiere copia profunda
        tyreType = tt;   // Al ser una agregación no requiere de copia profunda
    }

    /**
     * Constructor de copia que copia los valores del objeto Wheel que se le pasa
     * por parámetro.
     * 
     * @param w El objeto Wheel sobre el que realizaremos la copia.
     */
    public Wheel (Wheel w) {
        pressure = w.pressure;  // Al ser un tipo nativo no requiere copia profunda
        this.tyreType = w.tyreType;  // Al ser una agregación no requiere de copia profunda
    }

    /**
     * Establece el tipo de neumático para una rueda.
     * 
     * @param tt El objeto tipo de neumático que se le asocia a la rueda.
     */
    public void setTyreType(TyreType tt) {
        tyreType = tt;  // No se hace copia profunda ya que es una agregación.
    }

    /**
     * Retorna el tipo de neumático de la rueda.
     * 
     * @return El tipo de neumático de la rueda.
     */
    public TyreType getTyreType() {
        return tyreType != null ? tyreType : null;  // Al ser agregación, no copia profunda
    }

    /**
     * Retorna el valor de presión de la rueda.
     * 
     * @return La presión de la rueda.
     */
    public double getPressure() {
        return pressure;  // Al ser de tipo nativo no requiere de copia profunda
    }

    /**
     * Añade la presión indicada en el argumento a la rueda.
     * Esto puede generar numerosas excepciones.
     * 
     * @param plusPressure Cantidad de presión que se añadirá a la rueda.
     * @throws NoTyreTypeException Si no sabemos el tipo de neumático no podemos determinar la presión adecuada.
     * @throws IllegalArgumentException Si la presión que queremos añadir es negativa.
     * @throws PressureWheelException Si al sumarle la presión indicada en el parámetro se excede del máximo permitido
     *         por el tipo de neumático.
     */
    public void inflate(double plusPressure) throws IllegalArgumentException, NoTyreTypeException, PressureWheelException {
        if (tyreType == null) throw new NoTyreTypeException();  // Lanzamos NoTyreTypeException si tyreType es null
        if (plusPressure < 0) throw new IllegalArgumentException(); // Lanzamos IllegalArgumentException si la presión es negativa
        if (plusPressure < tyreType.getMinPressure() || plusPressure > tyreType.getMaxPressure()) {
            throw new PressureWheelException(plusPressure);  // Lanzamos PressureWheelException si la presión está fuera de los límites
        }
        pressure += plusPressure;  // Sumamos la presión
    }
}
