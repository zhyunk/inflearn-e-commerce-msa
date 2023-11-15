package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.messagequeue.KafkaProducer;
import com.example.orderservice.messagequeue.OrderProducer;
import com.example.orderservice.service.OrderService;
import com.example.orderservice.vo.RequestOrder;
import com.example.orderservice.vo.ResponseOrder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class OrderController {
    private final Environment env;
    private final ModelMapper mapper;
    private final OrderService orderService;
    private final KafkaProducer kafkaProducer;
    private final OrderProducer orderProducer;

    @GetMapping("/health_check")
    public String status() {
        return String.format("It's Working in Order Service !! Port is [ %s ]", env.getProperty("local.server.port"));
    }

    @PostMapping("/{userId}/orders")
    public ResponseEntity<ResponseOrder> createOrder(@PathVariable String userId, @RequestBody RequestOrder order) {

        OrderDto dto = mapper.map(order, OrderDto.class);
        dto.setUserId(userId);

//        db 바로 저장
//        ResponseOrder responseOrder = orderService.createOrder(dto);

        // kafka
        dto.setOrderId(UUID.randomUUID().toString());
        dto.setTotalPrice(order.getQty() * order.getUnitPrice());

        // send this order to the kafka
        kafkaProducer.send("example-catalog-topic", dto);
        orderProducer.send("orders", dto);

        ResponseOrder responseOrder = mapper.map(dto, ResponseOrder.class);

        return ResponseEntity
                .created(ServletUriComponentsBuilder
                        .fromCurrentRequest().build().toUri())
                .body(responseOrder);
    }

    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<ResponseOrder>> getOrder(@PathVariable String userId) {

        return ResponseEntity
                .ok(orderService.getOrdersByUserId(userId));
    }

}
