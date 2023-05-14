package com.mvcapp.shawarma.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CookieController {

    @GetMapping("/get-cookie")
    public String getCookie(HttpServletRequest request, Model model) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                //System.out.println(cookie.getName());
                model.addAttribute("myCookieValue", cookie.getValue());
            }
        }
        return "my-cookie";
    }
}
