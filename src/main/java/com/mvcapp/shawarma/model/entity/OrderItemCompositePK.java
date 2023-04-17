package com.mvcapp.shawarma.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
@Data
@Embeddable
public class OrderItemCompositePK implements Serializable {
    private int orderId;
    private int productId;
}
