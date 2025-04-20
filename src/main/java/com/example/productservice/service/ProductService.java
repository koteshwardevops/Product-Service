package com.example.productservice.service;

import com.example.productservice.model.Product;
import org.springframework.stereotype.Service;

import java.nio.file.ProviderNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>();

    public ProductService() {
        products.add(new Product(1L, "Laptop", "High performance laptop", 999.99, "http://example.com/laptop.jpg"));
        products.add(new Product(2L, "Smartphone", "Latest model smartphone", 799.49, "http://example.com/phone.jpg"));
        products.add(new Product(3L, "Headphones", "Noise-cancelling headphones", 199.99, "http://example.com/headphones.jpg"));
    }

    public List<Product> getAllProducts() {
        return products;

    }

    public Product getProductById(Long id) {

        return products.stream().filter(p ->p.getId().equals(id)).findFirst()
                .orElseThrow(() -> new ProviderNotFoundException("Product with id " + id + " not found."));

    }
}