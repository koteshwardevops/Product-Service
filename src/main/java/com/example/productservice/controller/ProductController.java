package com.example.productservice.controller;

import com.example.productservice.entity.Product;
import com.example.productservice.productrepository.ProductRepository;
import com.example.productservice.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    // this is static data process
    /*private final ProductService productService;


    public ProductController(ProductService productService) {

        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts(){

        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {

        return productService.getProductById(id);
    }*/

    // this is for dynamic data for communicating database

    private ProductRepository pdoductRepository;

    @GetMapping
    public List<Product> getAllProducts(){
        System.out.println("getAllProducts called...");
        return pdoductRepository.findAll();
    }

    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        System.out.println("getProductById called...");
        return pdoductRepository.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

}
