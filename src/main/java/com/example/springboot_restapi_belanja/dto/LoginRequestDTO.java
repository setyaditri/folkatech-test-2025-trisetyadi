package com.example.springboot_restapi_belanja.dto;

import lombok.Data;

@Data
public class LoginRequestDTO {

    private String email;
    private String password;

}