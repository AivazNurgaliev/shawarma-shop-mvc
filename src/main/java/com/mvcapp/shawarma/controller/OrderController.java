package com.mvcapp.shawarma.controller;

import com.mvcapp.shawarma.model.entity.OrderEntity;
import com.mvcapp.shawarma.model.entity.UserEntity;
import com.mvcapp.shawarma.service.OrderService;
import com.mvcapp.shawarma.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    @Autowired
    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    // TODO: @PreAuthorized
    @GetMapping("/{userId}")
    public String getByClientId(@PathVariable Integer userId, Model model) {
        List<OrderEntity> userOrders = orderService.findByUserId(userId);
        model.addAttribute("userOrders", userOrders);
        return "order";
    }

    @GetMapping
    public String findByUserId(Authentication authentication, Model model) {
        UserEntity user = userService.findByEmail(authentication.getName());
        int userId = user.getId();
        List<OrderEntity> userOrders = orderService.findByUserId(userId);
        model.addAttribute("userOrders", userOrders);
        return "order";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable Integer id) {
        orderService.deleteById(id);
        return "deleted";// TODO: ???
    }

    // @PostMapping("/add")
    // public String addOrder(@RequestBody OrderEntity)

}
