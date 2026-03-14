package com.hqllab.service;

import com.hqllab.entity.Product;
import com.hqllab.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    
    // Create a new product
    public Product createProduct(String name, Double price, Integer quantity, String description) {
        Product product = new Product(name, price, quantity, description);
        return productRepository.save(product);
    }
    
    // Get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    // Get product by ID
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }
    
    // Update a product
    public Product updateProduct(Long id, String name, Double price, Integer quantity, String description) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            Product p = product.get();
            p.setName(name);
            p.setPrice(price);
            p.setQuantity(quantity);
            p.setDescription(description);
            return productRepository.save(p);
        }
        return null;
    }
    
    // Delete a product
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    
    // Get total count
    public long getTotalProducts() {
        return productRepository.count();
    }
}
