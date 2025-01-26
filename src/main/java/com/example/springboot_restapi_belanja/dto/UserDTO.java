package com.example.springboot_restapi_belanja.dto;

import com.example.springboot_restapi_belanja.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String email;
    private String name;

    private String role;

    public UserDTO convertToDTO(User user) {
        return new UserDTO(user.getId(), user.getEmail(), user.getFirst_name() + " " + user.getLast_name(), user.getRole());
    }    

}