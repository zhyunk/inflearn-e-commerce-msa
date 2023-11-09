package com.example.orderservice.service;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.entity.Order;
import com.example.orderservice.vo.ResponseOrder;

import java.util.List;

public interface OrderService {

    ResponseOrder createOrder(OrderDto orderDetails);
    List<ResponseOrder> getOrdersByUserId(String orderId);
    ResponseOrder getOrderByOrderId(String orderId);

}
