package com.mvcapp.shawarma.model.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Embeddable
public class OrderItemCompositeId implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private int orderId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private int productId;
}
