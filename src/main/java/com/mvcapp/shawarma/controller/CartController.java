package com.mvcapp.shawarma.controller;

import com.mvcapp.shawarma.model.entity.CartEntity;
import com.mvcapp.shawarma.model.entity.ProductEntity;
import com.mvcapp.shawarma.model.entity.UserEntity;
import com.mvcapp.shawarma.repository.CartRepository;
import com.mvcapp.shawarma.service.ProductService;
import com.mvcapp.shawarma.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartRepository cartRepository;

    private final ProductService productService;

    private final UserService userService;

    public CartController(CartRepository cartRepository, ProductService productService, UserService userService) {
        this.cartRepository = cartRepository;
        this.productService = productService;
        this.userService = userService;
    }


    @GetMapping
    public String getUserCart(Authentication authentication, Model model) {
        UserEntity user = userService.findByEmail(authentication.getName());
        List<CartEntity> cartItems = cartRepository.findByUserId(user.getId());
        model.addAttribute("cartItems", cartItems);
        return "cart";
    }

    @RequestMapping(value = "/add/{productId}", method = {RequestMethod.GET, RequestMethod.POST})
    public String addToCart(Authentication authentication, @PathVariable Integer productId, Model model) {
        Optional<ProductEntity> product = productService.findById(productId);
        UserEntity user = userService.findByEmail(authentication.getName());
        Integer userId = user.getId();
        if (product.isEmpty()) {
            model.addAttribute("message", "Product not found");
            return "error";
        }
        Optional<CartEntity> cartItem = cartRepository.findByUserIdAndProductId(userId, productId);
        CartEntity cartEntity;
        if (cartItem.isPresent()) {
            cartEntity = cartItem.get();
            cartEntity.setCountProducts(cartEntity.getCountProducts() + 1);
        } else {
            cartEntity = new CartEntity();
            cartEntity.setUserId(userId);
            cartEntity.setProductId(product.get().getId());
            cartEntity.setCountProducts(1);
        }
        cartRepository.save(cartEntity);
        return "redirect:/cart";
    }
    @Transactional
    @RequestMapping(value = "/remove/{productId}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String removeFromCart(Authentication authentication, @PathVariable Integer productId) {
        UserEntity user = userService.findByEmail(authentication.getName());
        Integer userId = user.getId();
        cartRepository.deleteByUserIdAndProductId(userId, productId);
        return "redirect:/cart";
    }
}

