package com.mvcapp.shawarma.model.entity;

import jakarta.persistence.*;
import org.springframework.security.core.userdetails.User;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "order_date", nullable = false)
    private Timestamp orderDate;

    @Column(name = "order_status", nullable = true, length = 255)
    private String orderStatus;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserEntity user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemEntity> orderItems;
}
