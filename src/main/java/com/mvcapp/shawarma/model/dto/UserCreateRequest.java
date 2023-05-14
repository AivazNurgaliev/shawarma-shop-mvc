package com.mvcapp.shawarma.model.dto;

import lombok.Data;

@Data
public class UserCreateRequest {
    private String email;
    private String password;
    private String phone;
    private String firstName;
    private String lastName;
}
