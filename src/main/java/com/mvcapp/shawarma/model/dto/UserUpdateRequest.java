package com.mvcapp.shawarma.model.dto;

import lombok.Data;

@Data
public class UserUpdateRequest {
    private String email;
    private String password;
    private String phone;
    private String firstName;
    private String lastName;
}
