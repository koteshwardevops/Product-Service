package com.example.productservice.controller;

import com.example.productservice.entity.Order;
import com.example.productservice.entity.Product;
import com.example.productservice.productrepository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@RestController
@RequestMapping ("/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<?> createOrder(@RequestBody Order order){

        try {
            for (Long productId : order.getProducts()) {
                String productServiceUrl = "http://localhost:8080/products/" + productId;
                ResponseEntity<Product> response = restTemplate.getForEntity(productServiceUrl, Product.class);
                if (!response.getStatusCode().is2xxSuccessful()) {
                    throw new RuntimeException("Product not found: " + productId);
                }
            }
            order.setOrderDate(LocalDate.now());
            return ResponseEntity.ok(orderRepository.save(order));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create order: " + e.getMessage());
        }
    }
}
