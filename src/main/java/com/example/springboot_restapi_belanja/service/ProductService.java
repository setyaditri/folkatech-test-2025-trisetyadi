package com.example.springboot_restapi_belanja.service;

import com.example.springboot_restapi_belanja.entity.Product;
import com.example.springboot_restapi_belanja.exception.ResourceNotFoundException;
import com.example.springboot_restapi_belanja.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with ID " + id + " not found."));
    }

}