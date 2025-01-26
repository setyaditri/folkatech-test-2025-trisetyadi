package com.example.springboot_restapi_belanja.repository;

import com.example.springboot_restapi_belanja.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {



}