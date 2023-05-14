package com.mvcapp.shawarma.service;

import com.mvcapp.shawarma.model.entity.OrderEntity;
import com.mvcapp.shawarma.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository ordersRepository;


    public OrderService(OrderRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public List<OrderEntity> findAll() {
        return ordersRepository.findAll();
    }

    public OrderEntity findById(Integer id) {
        return ordersRepository.findById(id).orElseThrow();
    }

    public Integer save(OrderEntity orderEntity) {
        ordersRepository.save(orderEntity);
        return orderEntity.getId();
    }

    public void deleteById(Integer id) {
        ordersRepository.deleteById(id);
    }

    public List<OrderEntity> findByUserId(Integer userId){
        return ordersRepository.findByUserId(userId);
    }
    public Optional<OrderEntity> findOrder(Integer orderId){
        return ordersRepository.findById(orderId);
    }
}
