package com.mvcapp.shawarma.repository;

import com.mvcapp.shawarma.model.entity.OrderItemCompositePK;
import com.mvcapp.shawarma.model.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEntity, OrderItemCompositePK> {
     public List<OrderItemEntity> findByOrderId(Integer orderId);
        
}
