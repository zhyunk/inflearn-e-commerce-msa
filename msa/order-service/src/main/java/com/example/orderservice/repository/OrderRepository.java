package com.example.orderservice.repository;

import com.example.orderservice.entity.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends CrudRepository<Order, Long> {
    Optional<Order> findByOrderId(String productId);
    List<Order> findByUserId(String userId);
}
