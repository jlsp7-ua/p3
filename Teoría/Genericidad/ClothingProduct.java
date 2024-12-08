package Genericidad;

class ClothingProduct extends Product<ClothingSpecs, Integer> {

    public ClothingProduct(
        Integer id,
        String name,
        ClothingSpecs specs,
        double price
    ) {
        super(id, name, specs, price);
    }

    @Override
    public void displayDetails() {
        System.out.println("Prenda: " + getName());
        System.out.println("Talla: " + getCharacteristics());
        System.out.println("Precio: €" + getPrice());
    }
}

class ClothingSpecs {

    private String size;
    private String material;

    public ClothingSpecs(String size, String material) {
        this.size = size;
        this.material = material;
    }

    @Override
    public String toString() { return size + " (" + material + ")";}
}
