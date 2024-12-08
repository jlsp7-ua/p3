package es.ua.dlsi.prog3.p2.model;

import java.util.ArrayList;
import java.util.Objects;

import es.ua.dlsi.prog3.p2.exceptions.*;

/**
 * Esta clase representa a los objetos coche que tiene una array de ruedas
 * que como máximo serán 4. Todas serán del mismo tipo. Aunque no tiene
 * porque estar igual de hinchadas.
 * 
 * @author JOSE LUIS SOLIVELLA POVEDA 29521020Z
 */
public final class Car {
    /**
     * Atributo de instancia, concretamente un ArrayList, que contiene
     * las ruedas que lleva el coche. Aquí es donde se forma la relación
     * con la clase Car. En los métodos que se asignen las ruedas deberá
     * realizarse por copia profunda para que la relación sea de composición.
     */
    private ArrayList<Wheel> wheels;

    /** Atributo de clase que marca cuál es el número máximo de ruedas que
     * puede tener un coche instaladas.
     */
    static private final int MAX_WHEELS = 4; 

    /** 
     * Constructor por defecto que inicializa el ArrayList que contiene las
     * ruedas del coche.
     */
    public Car() {
        wheels = new ArrayList<Wheel>();
    }

    /**
     * Constructor de copia que realiza una copia del objeto Car pasado por
     * parámetro. Primero inicializa el ArrayList de las ruedas del coche y luego
     * las introduce una a una, ya que el array se debe copiar profundamente al ser
     * una composición.
     * 
     * @param car es el objeto de clase Car sobre el que se realizará la copia.
     */
    public Car(Car car) {
        wheels = new ArrayList<Wheel>(); // Inicializa el ArrayList
        for (Wheel w : car.wheels) { // Para cada rueda del ArrayList de ruedas origen
            wheels.add(new Wheel(w)); // Se añade una copia profunda al array de ruedas destino
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(wheels);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Car)) return false;
        Car other = (Car) obj;
        return Objects.equals(wheels, other.wheels);
    }

    /**
     * Método de instancia.
     * Añade una rueda al ArrayList de ruedas del coche. Sin embargo, para que esta inserción
     * se lleve a cabo deben cumplirse unas condiciones.
     * 
     * @param w es un objeto de tipo Wheel que se añadirá al array de ruedas del coche.
     * @throws TooManyWheelsException si se aplica este método cuando ya hay 4 ruedas.
     * @throws WrongTyreTypeException si se quiere añadir un tipo de neumático cuando el resto de tipos son nulos y viceversa.
     */
    public void addWheel(Wheel w) throws TooManyWheelsException, WrongTyreTypeException {
        if (wheels.size() == MAX_WHEELS) throw new TooManyWheelsException(); // Comprobación cantidad de ruedas
        if (!wheels.isEmpty()) { // Si hay ruedas previas
            TyreType existingTyreType = wheels.get(0).getTyreType(); // Tomamos su tipo
            if (existingTyreType == null && w.getTyreType() != null ||
                existingTyreType != null && !existingTyreType.equals(w.getTyreType()))
                throw new WrongTyreTypeException(); // Comprobación de que cuadren los tipos
        }
        wheels.add(new Wheel(w)); // Añadimos la rueda
    }

    /**
     * Método de instancia.
     * Retorna el array de ruedas del coche, de las cuales hay que hacer copia profunda para que si se hacen cambios
     * sobre las ruedas devueltas no afecten a las originales.
     * 
     * @return newWheels es la copia del array de ruedas original.
     */
    public ArrayList<Wheel> getWheels() {
        ArrayList<Wheel> newWheels = new ArrayList<Wheel>(); // Instanciamos el array de ruedas
        for (Wheel w : wheels) { // Para cada rueda del array origen
            newWheels.add(new Wheel(w)); // añadimos su copia al array copia
        }
        return newWheels; // Devolvemos el array copia.
    }

    /**
     * Método de instancia.
     * Este método cambia el tipo de todos los neumáticos, lo que puede provocar ciertas excepciones.
     * 
     * @param tt el tipo de neumático que se quiere aplicar.
     * @param pressure la presión a la que se quieren inflar los neumáticos.
     * @throws IllegalArgumentException si el tipo de neumático que queremos aplicar es null.
     * @throws NoTyreTypeException si el tipo de rueda es nulo no se puede inflar.
     * @throws PressureWheelException si la presión a la que se quieren inflar excede los límites.
     */
    public void changeTyres(TyreType tt, double pressure) throws IllegalArgumentException, PressureWheelException {
        // Comprobación de que el tipo de neumático no es null
        if (tt == null) throw new IllegalArgumentException("TyreType no puede ser null");
        
        for (Wheel w : wheels) { // Para cada rueda del coche
            w.setTyreType(tt); // aplicamos el tipo de neumático
            try {
                w.inflate(pressure); // Inflamos la rueda
            } catch (NoTyreTypeException | IllegalArgumentException e) { // Capturamos las posibles excepciones
                throw new RuntimeException(e); // Las relanzamos como RuntimeException
            } catch (PressureWheelException e) { // Capturamos la posible excepción
                System.out.println(e.getMessage()); // La imprimimos como indica el enunciado
                throw e; // Relanzamos la excepción
            }
        }
    }
}
