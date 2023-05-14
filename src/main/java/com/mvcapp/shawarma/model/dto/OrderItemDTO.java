package com.mvcapp.shawarma.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {
    private Integer orderId;
    private String productName;
    private Integer productCount;
    private Double productPrice;
}
