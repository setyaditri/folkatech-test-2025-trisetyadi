package com.example.springboot_restapi_belanja.dto;

import lombok.Data;

@Data
public class RegisterRequestDTO {

    private String email;
    private String password;
    private String name;

}