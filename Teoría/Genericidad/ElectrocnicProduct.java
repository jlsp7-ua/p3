package Genericidad;

// Implementaciones concretas de productos
class ElectronicProduct extends Product<ElectronicSpecs, String> {

    public ElectronicProduct(
        String id,
        String name,
        ElectronicSpecs specs,
        double price
    ) {
        super(id, name, specs, price);
    }

    @Override
    public void displayDetails() {
        System.out.println("Producto: " + getName());
        System.out.println("Especificaciones: " + getCharacteristics());
        System.out.println("Precio: €" + getPrice());
    }
}

// Clases de especificaciones con sus propios tipos
class ElectronicSpecs {

    private int processorSpeed;
    private int ramGB;

    public ElectronicSpecs(int processorSpeed, int ramGB) {
        this.processorSpeed = processorSpeed;
        this.ramGB = ramGB;
    }

    @Override
    public String toString() { return processorSpeed + "GHz, " + ramGB + "GB RAM";}
}
