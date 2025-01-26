package com.example.springboot_restapi_belanja.repository;

import com.example.springboot_restapi_belanja.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByEmail(String email);
    Optional<User> findByPhone(String phone);

}