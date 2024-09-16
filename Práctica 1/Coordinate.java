/**
    @author José Luis Solivella Poveda
    @version 1.0
    DNI: 29521020 Z
*/

import java.util.Arrays;

/**
    La clase es inmutable porque se ha declarado final, no tiene setters, los atributos son privados y definidos con final, no se referencia a ningún objeto externo mutable ni se ofrece una referencia que pueda modificar el contendio a través de los getters.
    */
public final class Coordinate {

    /**
        Array atributo de la clase que contiene las coordenadas.
    */
    private final double[] components;

    /**
        Constructor de la clase a partir de array de componentes.
        @param componentes : paso por referencia del array de componentes, que si es null tendrá longitud 0
    */
    public Coordinate(double[] components) {
        if (components != null) {
            this.components = components.clone(); // Para evitar referenciar a objetos mutables
        } else {
            this.components = new double[0];
        }
    }

    /**
        Constructor de copia
        @param coordinate : paso por referencia de un objeto Coordinate
    */
    public Coordinate(Coordinate coordinate) {
        this.components = coordinate.components.clone(); // Copia defensiva para inmutabilidad
    }

    /**
        getter de las componentes
        @return Devuelve la refencia a una copia del array de coordenadas del objeto.
    */
    public double[] getComponents() {
        return components.clone();
    }

    /**
        Método get para conocer las dimensiones
        @return Utiliza el método 'lenght' de los arrays para devolver el número de dimensiones de la coordenada.
    */
    public int getDimensions() {
        return components.length;
    }

    /**
        Método para generar el hashCode
        @return Deevuelve el hashCode de la coordenada gracias al método hashCodede los arrays.
    */
    public int hashCode() {
        return Arrays.hashCode(components);
    }

    /**
        Método de comparación
        Compara el estado de dos objetos Coordinate.
        @param obj : objeto con el cual será comparado.
        @return Devuelve verdadero si el estado de los objetos son iguales y falso si no es así o son objetos de distintas clases.
    */
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Coordinate)) return false;
        Coordinate objCoor = (Coordinate) obj;
        return Arrays.equals(this.components, objCoor.components);
    }
}
