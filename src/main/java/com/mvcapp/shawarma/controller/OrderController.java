package com.mvcapp.shawarma.controller;

import com.mvcapp.shawarma.model.entity.OrderEntity;
import com.mvcapp.shawarma.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

//    @PreAuthorized
    @GetMapping("/{userId}")
    public String getByClientId(@PathVariable Integer userId, Model model) {
        List<OrderEntity> userOrders = orderService.findByUserId(userId);
        model.addAttribute("userOrders", userOrders);
        return "order";
    }

    @GetMapping
    public String findByUserId(Integer userId, Model model) {
        //userServise.getId
        List<OrderEntity> userOrders = orderService.findByUserId(userId);
        model.addAttribute("userOrders", userOrders);
        return "order";
    }
    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable Integer id){
        orderService.deleteById(id);
        return "deleted";//TODO: ???
    }


//    @PostMapping("/add")
//    public String addOrder(@RequestBody OrderEntity)

}
