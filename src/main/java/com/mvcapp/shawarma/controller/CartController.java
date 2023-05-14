package com.mvcapp.shawarma.controller;

import com.mvcapp.shawarma.model.entity.*;
import com.mvcapp.shawarma.repository.CartRepository;
import com.mvcapp.shawarma.repository.OrderItemRepository;
import com.mvcapp.shawarma.repository.OrderRepository;
import com.mvcapp.shawarma.service.ProductService;
import com.mvcapp.shawarma.service.UserService;
import jakarta.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartRepository cartRepository;

    private final ProductService productService;

    private final UserService userService;

    public CartController(OrderRepository orderRepository,
            OrderItemRepository orderItemRepository,
            CartRepository cartRepository,
            ProductService productService,
            UserService userService) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
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

    @Transactional
    @RequestMapping(value = "/add/{productId}", method = { RequestMethod.GET, RequestMethod.POST })
    public String addToCart(Authentication authentication, @PathVariable Integer productId,
            Model model) {
        Optional<ProductEntity> product = productService.findById(productId);
        UserEntity user = userService.findByEmail(authentication.getName());
        Integer userId = user.getId();
        if (product.isEmpty()) {
            model.addAttribute("message", "Product not found");
            return "error";
        }
        Optional<CartEntity> cartItem = cartRepository.findByUserIdAndProductId(userId, productId);
        CartEntity cartEntity = new CartEntity();
        if (cartItem.isPresent()) {
            cartEntity = cartItem.get();
            cartEntity.setCountProducts(cartItem.get().getCountProducts() + 1);
        } else {
            // cartEntity = new CartEntity();
            cartEntity.setUserId(userId);
            cartEntity.setProductId(product.get().getId());
            cartEntity.setCountProducts(1);
        }
        cartRepository.save(cartEntity);
        return "redirect:/cart";
    }

    @Transactional
    @RequestMapping(value = "/remove/{productId}", method = { RequestMethod.GET, RequestMethod.DELETE })
    public String removeFromCart(Authentication authentication, @PathVariable Integer productId,
            @RequestParam(required = false) Integer quantity) {
        if (quantity == null) {
            quantity = 1;
        }
        UserEntity user = userService.findByEmail(authentication.getName());
        Integer userId = user.getId();

        Optional<CartEntity> cartItem = cartRepository.findByUserIdAndProductId(userId, productId);
        CartEntity cartEntity;
        if (cartItem.isPresent()) {
            cartEntity = cartItem.get();
            cartEntity.setCountProducts(cartEntity.getCountProducts() - quantity);
            if (cartEntity.getCountProducts() < 1) {
                cartRepository.delete(cartItem.get());
            }
        } else {
            return "error";
        }
        return "redirect:/cart";
    }

    @Transactional
    @RequestMapping(value = "/confirm", method = { RequestMethod.POST })
    public String confirmCart(Authentication authentication) {
        // TODO: 24.04.2023 all lines with this user id send to order or order items
        // TODO: 24.04.2023 then we need to delete all lines with this user id in
        // cart
        UserEntity user = userService.findByEmail(authentication.getName());
        Integer userId = user.getId();
        List<CartEntity> userCart = cartRepository.findByUserId(userId);
        OrderEntity userOrder = new OrderEntity();
        userOrder.setUserId(userId);
        userOrder.setOrderDate(new Timestamp(new Date().getTime()));
        orderRepository.save(userOrder);

        Integer orderId = userOrder.getId();
        // List<OrderItemEntity> orderItems = new ArrayList<>();
        for (CartEntity c : userCart) {
            OrderItemEntity orderItem = new OrderItemEntity();
            orderItem.setOrderId(orderId);
            orderItem.setProductId(c.getProductId());
            orderItem.setProductCount(c.getCountProducts());
            orderItem.setProductPrice(c.getProduct().getPrice());

            orderItemRepository.save(orderItem);
        }

        cartRepository.deleteAll(userCart);

        return "redirect:/"; // FIXME: "redirect:/orders"
    }
}
