package com.mvcapp.shawarma.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SessionController {

    @GetMapping("/set-session")
    public String setSession(HttpSession session, Authentication authentication) {
        session.setAttribute("myAttribute", "Hello to you " + authentication.getName());
        return "redirect:/get-session";
    }

    @GetMapping("/get-session")
    public String getSession(HttpSession session, Model model) {
        String myAttribute = (String) session.getAttribute("myAttribute");
        model.addAttribute("myAttribute", myAttribute);
        return "my-session";
    }
}
