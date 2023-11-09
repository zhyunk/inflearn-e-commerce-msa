package com.example.orderservice.service;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.entity.Order;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.vo.ResponseOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final ModelMapper mapper;
    private final OrderRepository orderRepository;

    @Override
    public ResponseOrder createOrder(OrderDto orderDto) {
        orderDto.setOrderId(UUID.randomUUID().toString());
        orderDto.setTotalPrice(orderDto.getQty() * orderDto.getUnitPrice());

        Order saved = orderRepository.save(mapper.map(orderDto, Order.class));

        return mapper.map(saved, ResponseOrder.class);
    }

    @Override
    public List<ResponseOrder> getOrdersByUserId(String userId) {

        return orderRepository.findByUserId(userId)
                .stream()
                .map(order -> mapper.map(order, ResponseOrder.class))
                .peek(order -> {
                        System.out.println("order = " + order);
                })
                .collect(Collectors.toList());
    }

    @Override
    public ResponseOrder getOrderByOrderId(String orderId) {
        Optional<Order> optional = orderRepository.findByOrderId(orderId);

        if (optional.isEmpty())
            throw new RuntimeException("Order Not Found");

        return mapper.map(optional.get(), ResponseOrder.class);
    }

}
