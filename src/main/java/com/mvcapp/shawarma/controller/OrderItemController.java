package com.mvcapp.shawarma.controller;

import com.mvcapp.shawarma.model.dto.OrderItemDTO;
import com.mvcapp.shawarma.model.entity.OrderItemEntity;
import com.mvcapp.shawarma.model.entity.ProductEntity;
import com.mvcapp.shawarma.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mvcapp.shawarma.service.OrderItemService;

import java.util.List;
import java.util.Set;


@Controller
@RequestMapping("/order-items")
public class OrderItemController {
    private final OrderItemService orderItemService;
    private final ProductService productService;
    
    public OrderItemController(OrderItemService orderItemService, ProductService productService){
        this.orderItemService = orderItemService;
        this.productService = productService;
    }


    @GetMapping("/{orderId}")
    public String findByOrderId(@PathVariable Integer orderId, Model model){
        List<OrderItemDTO> orderItems = orderItemService.findByOrderId(orderId);
        model.addAttribute("orderItems",orderItems);
        return "orderItems";
    }

/*
    @Transactional
    @RequestMapping(value="add/{productId}/{orderId}", method={RequestMethod.GET, RequestMethod.POST})
    public String requestMethodName(Authentication authentication,@PathVariable Integer productId, @PathVariable Integer orderId) {
        OrderEntity order = orderService.findById(orderId);
        ProductEntity product = productService.findById(productId);
        T//if not exists
        OrderItemEntity = new OrderItemEntity();
        OrderItemEntity.setOrderId(orderId);
        OrderItemEntity.productId(productId);
        OrderItemEntity.productCount(1);
        OrderItemEntity.productPrice(product.getPrice());




        return "orderItems";
    }
*/

}
