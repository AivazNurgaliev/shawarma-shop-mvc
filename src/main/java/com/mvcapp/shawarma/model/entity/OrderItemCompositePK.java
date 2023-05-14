package com.mvcapp.shawarma.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemCompositePK implements Serializable {
    private Integer orderId;
    private Integer productId;
}
