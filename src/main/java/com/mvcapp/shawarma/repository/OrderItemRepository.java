package com.mvcapp.shawarma.repository;

import com.mvcapp.shawarma.model.entity.OrderItemCompositePK;
import com.mvcapp.shawarma.model.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEntity, OrderItemCompositePK> {
}
