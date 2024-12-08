package es.ua.dlsi.prog3.p2.model;

import java.util.Objects;

/**
 * Esta clase agrupa los tipos de neumáticos de los que puede ser una rueda,
 * es decir, un objeto de la clase Wheel de este mismo paquete. Por tanto,
 * la relación entre esta clase y la clase Wheel es de agregación.
 * 
 * @author JOSE LUIS SOLIVELLA POVEDA 29521020Z
 */
public final class TyreType {
    /**
     * Atributo de instancia de tipo String que contiene una descripción
     * que diferencia unos tipos de rueda de otros.
     */
    private final String description;

    /**
     * Este atributo de instancia marca la presión mínima que debe tener una
     * rueda de este tipo.
     */
    private final double min_pressure;

    /**
     * Este atributo de instancia marca la presión máxima que debe tener una
     * rueda de este tipo.
     */
    private final double max_pressure;

    /**
     * Constructor sobrecargado.
     * Se realizan una serie de comprobaciones de los parámetros para asegurarnos
     * de la coherencia del objeto.
     * 
     * @param desc descripción del tipo de rueda de la cual se hace
     * copia profunda.
     * @param minp parámetro double que indica la presión mínima de
     * este tipo de rueda.
     * @param maxp parámetro double que indica la presión máxima de
     * este tipo de rueda.
     */
    public TyreType(String desc, double minp, double maxp) {
        /**
         * Comprobamos que la descripción no sea nula ni esté vacía, también que
         * las presiones no sean negativas ni que la mínima sea mayor a la máxima.
         */
        if (desc == null || desc.isEmpty() || minp < 0 || maxp < 0 || minp > maxp)
            throw new IllegalArgumentException("Argumento no válido."); // Lanzamos la excepción

        description = new String(desc); // Realizamos la copia profunda
        min_pressure = minp; // Al ser un tipo nativo no requiere copia profunda
        max_pressure = maxp; // Al ser un tipo nativo no requiere copia profunda
    }

    /**
     * Constructor de copia que realiza una copia profunda del objeto TyreType
     * del que se hará la copia. Si la referencia es nula se lanzará una excepción.
     * 
     * @param tyre_type objeto TyreType del cual copiaremos los valores de sus atributos.
     */
    public TyreType(TyreType tyre_type) {
        if (tyre_type == null) throw new NullPointerException();
        description = new String(tyre_type.description); // Requiere de copia profunda para evitar referencias peligrosas
        min_pressure = tyre_type.min_pressure; // Al ser un tipo nativo no requiere copia profunda
        max_pressure = tyre_type.max_pressure; // Al ser un tipo nativo no requiere copia profunda
    }

    /**
     * Método de instancia.
     * Retorna el valor mínimo de presión que este tipo de neumático soporta.
     * Al ser un tipo nativo no requiere copia profunda.
     * 
     * @return min_pressure la presión mínima para el tipo de neumático.
     */
    public double getMinPressure() {
        return min_pressure; // Lo devolvemos directamente
    }

    /**
     * Método de instancia.
     * Retorna el valor máximo de presión que este tipo de neumático soporta.
     * Al ser un tipo nativo no requiere copia profunda.
     * 
     * @return max_pressure la presión máxima para el tipo de neumático.
     */
    public double getMaxPressure() {
        return max_pressure; // Lo devolvemos directamente
    }

    /**
     * Método de instancia.
     * Retorna la descripción de este tipo de neumático.
     * Al no ser un tipo nativo requiere copia profunda.
     * 
     * @return description la descripción para el tipo de neumático.
     */
    public String getDescription() {
        return new String(description);
    }

    /**
     * Método de instancia.
     * Expresa el tipo de neumático en forma de cadena de texto "TyreType [min,max]"
     * 
     * @return String cadena de texto con la información del tipo de neumático.
     */
    public String toString() {
        return "TyreType " + this.description + " [" + this.getMinPressure() + "," + this.getMaxPressure() + "]";
    }

    /**
     * Método de instancia.
     * Indica si dos objetos de esta clase tienen igual contenido.
     * 
     * @param obj objeto cualquiera.
     * @return boolean devuelve verdadero si ambos objetos contienen el mismo contenido, sino falso.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof TyreType)) return false;
        TyreType other = (TyreType) obj;
        return Objects.equals(description, other.description)
                && Double.doubleToLongBits(max_pressure) == Double.doubleToLongBits(other.max_pressure)
                && Double.doubleToLongBits(min_pressure) == Double.doubleToLongBits(other.min_pressure);
    }
}
