package com.mvcapp.shawarma.controller;

import com.mvcapp.shawarma.exception.UserAlreadyExistException;
import com.mvcapp.shawarma.model.dto.UserCreateRequest;
import com.mvcapp.shawarma.model.entity.UserEntity;
import com.mvcapp.shawarma.service.UserService;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /*@PostMapping("/registration")
    public UserEntity register(@RequestBody UserCreateRequest request) {
        try {
            return userService.createMe(request);
        } catch (UserAlreadyExistException e) {
            throw new RuntimeException("error while registration");
        }

    }*/
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/registration")
    public String register() {
        return "registration";
    }

    @PostMapping("/registration")
    public String register(@ModelAttribute UserCreateRequest request) {
        try {
            var user = userService.createMe(request);
            return "redirect:/";
        } catch (UserAlreadyExistException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
