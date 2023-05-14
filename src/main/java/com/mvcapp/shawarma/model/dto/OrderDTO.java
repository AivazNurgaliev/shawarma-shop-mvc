package com.mvcapp.shawarma.model.dto;

import com.mvcapp.shawarma.model.entity.OrderItemEntity;
import com.mvcapp.shawarma.model.entity.UserEntity;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;

public class OrderDTO {
    private Integer userId;
    private Timestamp orderDate;
    private UserEntity user;
    private List<OrderItemEntity> orderItems;
}
