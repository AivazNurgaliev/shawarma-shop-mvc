package com.mvcapp.shawarma.service;

import java.util.ArrayList;
import java.util.List;

import com.mvcapp.shawarma.model.dto.OrderItemDTO;
import org.springframework.stereotype.Service;

import com.mvcapp.shawarma.model.entity.OrderItemCompositePK;
import com.mvcapp.shawarma.model.entity.OrderItemEntity;
import com.mvcapp.shawarma.repository.OrderItemRepository;

@Service
public class OrderItemService {
    
    private final OrderItemRepository orderItemRepository;

     public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }
    public List<OrderItemEntity> findAll(){
        return orderItemRepository.findAll();
    }
    public List<OrderItemDTO> findByOrderId(Integer orderId) {
        List<OrderItemDTO> orderItemDTOS = new ArrayList<>();

        List<OrderItemEntity> orderItemEntities = orderItemRepository.findByOrderId(orderId);

        for (OrderItemEntity o : orderItemEntities) {
            OrderItemDTO orderItemDTO = OrderItemDTO.builder()
                    .orderId(o.getOrderId())
                    .productName(o.getProduct().getName())
                    .productPrice(o.getProductPrice())
                    .productCount(o.getProductCount())
                    .build();
            orderItemDTOS.add(orderItemDTO);
        }

        return orderItemDTOS;
    }
    public OrderItemEntity save(OrderItemEntity orderItem){
        return orderItemRepository.save(orderItem);
    }
    public void deleteById(OrderItemCompositePK id){
        orderItemRepository.deleteById(id);
    }

}
