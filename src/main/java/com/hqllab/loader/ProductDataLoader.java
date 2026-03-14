package com.hqllab.loader;

import com.hqllab.entity.Product;
import com.hqllab.service.ProductService;
import com.hqllab.util.UserInputHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDataLoader {
    
    @Autowired
    private ProductService productService;
    
    /**
     * Interactive menu to manage products
     */
    public void startInteractiveMenu() {
        boolean running = true;
        
        while (running) {
            int choice = UserInputHandler.getMenuChoice(
                "Add New Product",
                "View All Products",
                "Search Product by ID",
                "Update Product",
                "Delete Product",
                "Exit"
            );
            
            switch (choice) {
                case 1:
                    addNewProduct();
                    break;
                case 2:
                    viewAllProducts();
                    break;
                case 3:
                    searchProductById();
                    break;
                case 4:
                    updateProduct();
                    break;
                case 5:
                    deleteProduct();
                    break;
                case 6:
                    running = false;
                    System.out.println("Thank you for using Product Manager!");
                    break;
            }
        }
        UserInputHandler.closeScanner();
    }
    
    /**
     * Add a new product with user input
     */
    private void addNewProduct() {
        System.out.println("\n===== ADD NEW PRODUCT =====");
        
        String name = UserInputHandler.getString("Enter Product Name: ");
        Double price = UserInputHandler.getDouble("Enter Product Price: $");
        Integer quantity = UserInputHandler.getInteger("Enter Quantity: ");
        String description = UserInputHandler.getString("Enter Description: ");
        
        Product product = productService.createProduct(name, price, quantity, description);
        System.out.println("\n✅ Product added successfully!");
        System.out.println("Product ID: " + product.getId());
        System.out.println(product);
    }
    
    /**
     * View all products
     */
    private void viewAllProducts() {
        System.out.println("\n===== ALL PRODUCTS =====");
        List<Product> products = productService.getAllProducts();
        
        if (products.isEmpty()) {
            System.out.println("No products found in database.");
        } else {
            System.out.println("Total Products: " + products.size() + "\n");
            for (Product product : products) {
                displayProduct(product);
            }
        }
    }
    
    /**
     * Search and display a product by ID
     */
    private void searchProductById() {
        System.out.println("\n===== SEARCH PRODUCT =====");
        Long id = UserInputHandler.getInteger("Enter Product ID: ").longValue();
        
        var product = productService.getProductById(id);
        if (product.isPresent()) {
            System.out.println("\n✅ Product found:\n");
            displayProduct(product.get());
        } else {
            System.out.println("\n❌ Product with ID " + id + " not found.");
        }
    }
    
    /**
     * Update an existing product
     */
    private void updateProduct() {
        System.out.println("\n===== UPDATE PRODUCT =====");
        Long id = UserInputHandler.getInteger("Enter Product ID to update: ").longValue();
        
        var product = productService.getProductById(id);
        if (product.isPresent()) {
            System.out.println("\nCurrent Product: ");
            displayProduct(product.get());
            
            String name = UserInputHandler.getString("\nEnter new Product Name: ");
            Double price = UserInputHandler.getDouble("Enter new Price: $");
            Integer quantity = UserInputHandler.getInteger("Enter new Quantity: ");
            String description = UserInputHandler.getString("Enter new Description: ");
            
            Product updated = productService.updateProduct(id, name, price, quantity, description);
            System.out.println("\n✅ Product updated successfully!");
            displayProduct(updated);
        } else {
            System.out.println("\n❌ Product with ID " + id + " not found.");
        }
    }
    
    /**
     * Delete a product
     */
    private void deleteProduct() {
        System.out.println("\n===== DELETE PRODUCT =====");
        Long id = UserInputHandler.getInteger("Enter Product ID to delete: ").longValue();
        
        var product = productService.getProductById(id);
        if (product.isPresent()) {
            displayProduct(product.get());
            
            if (UserInputHandler.getYesNo("\nAre you sure you want to delete this product?")) {
                productService.deleteProduct(id);
                System.out.println("✅ Product deleted successfully!");
            } else {
                System.out.println("❌ Deletion cancelled.");
            }
        } else {
            System.out.println("\n❌ Product with ID " + id + " not found.");
        }
    }
    
    /**
     * Display a product in formatted way
     */
    private void displayProduct(Product product) {
        System.out.println("─────────────────────────");
        System.out.println("ID: " + product.getId());
        System.out.println("Name: " + product.getName());
        System.out.println("Price: $" + String.format("%.2f", product.getPrice()));
        System.out.println("Quantity: " + product.getQuantity());
        System.out.println("Description: " + product.getDescription());
        System.out.println("─────────────────────────");
    }
}
