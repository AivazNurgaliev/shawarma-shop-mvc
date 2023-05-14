package com.mvcapp.shawarma.controller;

import com.mvcapp.shawarma.exception.UserAlreadyExistException;
import com.mvcapp.shawarma.model.dto.UserCreateRequest;
import com.mvcapp.shawarma.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class UserController {
    private final UserService userService;
    private Pattern pattern;
    private Matcher matcher;
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final String PHONE_PATTERN = "\\+\\d{11}";

    public boolean validateEmail(final String email) {
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public boolean validatePhone(final String phone) {
        pattern = Pattern.compile(PHONE_PATTERN);
        matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/registration")
    public String register() {
        return "registration";
    }

    @PostMapping("/registration")
    public String register(@ModelAttribute UserCreateRequest request, Model model) {
        if (!validateEmail(request.getEmail())) {
            model.addAttribute("error", "Invalid email address");
            return "registration";
        }
        if (!validatePhone(request.getPhone())) {
            model.addAttribute("error", "Invalid phone");
            return "registration";
        }

        try {
            var user = userService.createMe(request);
            return "redirect:/";
        } catch (UserAlreadyExistException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
