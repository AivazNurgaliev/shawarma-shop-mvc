package com.mvcapp.shawarma.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
@Data
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
