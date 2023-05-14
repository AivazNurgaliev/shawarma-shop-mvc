package com.mvcapp.shawarma.service;

import java.util.List;

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
    public List<OrderItemEntity> findByOrderId(Integer orderId){
        return orderItemRepository.findByOrderId(orderId);
    }
    public OrderItemEntity save(OrderItemEntity orderItem){
        return orderItemRepository.save(orderItem);
    }
    public void deleteById(OrderItemCompositePK id){
        orderItemRepository.deleteById(id);
    }

}
