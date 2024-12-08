package Genericidad;

// Clase genérica base para productos
public abstract class Product<T, ID> {

    private ID id;
    private String name;
    private T characteristics;
    private double price;

    public Product(ID id, String name, T characteristics, double price) {
        this.id = id;
        this.name = name;
        this.characteristics = characteristics;
        this.price = price;
    }

    // Getters y métodos abstractos
    public ID getId() {return id;}

    public String getName() {return name;}

    public T getCharacteristics() {return characteristics;}

    public double getPrice() {return price;}

    // Método abstracto para mostrar detalles específicos
    public abstract void displayDetails();
}
