package com.mvcapp.shawarma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String viewCart(Authentication authentication, Model model) {
        UserEntity user = userService.findByEmail(authentication.getName());
        List<ProductEntity> cartItems = user.getCart();
        double cartTotal = calculateCartTotal(cartItems);
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("cartTotal", cartTotal);
        return "cart";
    }

    @PostMapping("/{productId}/add")
    public String addToCart(@PathVariable("productId") Long productId, Authentication authentication) {
        UserEntity user = userService.findByEmail(authentication.getName());
        ProductEntity product = productService.findById(productId);
        if (product != null) {
            user.getCart().add(product);
            userService.save(user);
        }
        return "redirect:/cart";
    }

    @PostMapping("/{productId}/remove")
    public String removeFromCart(@PathVariable("productId") Long productId, Authentication authentication) {
        UserEntity user = userService.findByEmail(authentication.getName());
        ProductEntity product = productService.findById(productId);
        if (product != null) {
            user.getCart().remove(product);
            userService.save(user);
        }
        return "redirect:/cart";
    }

    private double calculateCartTotal(List<ProductEntity> cartItems) {
        double total = 0.0;
        for (ProductEntity product : cartItems) {
            total += product.getPrice();
        }
        return total;
    }
}

