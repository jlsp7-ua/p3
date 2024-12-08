package Genericidad;

import java.util.List;

// Clase de demostración
public class InventoryDemo {

    public static void main(String[] args) {
        // Crear un gestor de inventario
        InventoryManager<Product<?, ?>> inventoryManager = new InventoryManager<>();

        // Añadir productos electrónicos
        ElectronicProduct laptop = new ElectronicProduct(
            "LAPTOP001",
            "MacBook Pro",
            new ElectronicSpecs(3, 16),
            1999.99
        );
        inventoryManager.addProduct(laptop);

        // Añadir productos de ropa
        ClothingProduct tShirt = new ClothingProduct(
            42,
            "Camiseta Básica",
            new ClothingSpecs("M", "Algodón"),
            29.99
        );
        inventoryManager.addProduct(tShirt);

        // Filtrar productos por precio
        List<Product<?, ?>> expensiveProducts = inventoryManager.filterProducts(
            p -> p.getPrice() > 1000
        );

        System.out.println("Productos caros:");
        for (Product<?, ?> product : expensiveProducts) {
            product.displayDetails();
        }
    }
}
