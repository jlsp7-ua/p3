package Genericidad;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

// Gestor de inventario genérico
class InventoryManager<T extends Product<?, ?>> {

    private List<T> inventory = new ArrayList<>();

    public void addProduct(T product) {
        inventory.add(product);
    }

    public List<T> filterProducts(Predicate<T> condition) {
        List<T> filteredProducts = new ArrayList<>();
        for (T product : inventory) {
            if (condition.test(product)) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }

    public void displayInventory() {
        for (T product : inventory) {
            product.displayDetails();
            System.out.println("-------------------");
        }
    }
}
