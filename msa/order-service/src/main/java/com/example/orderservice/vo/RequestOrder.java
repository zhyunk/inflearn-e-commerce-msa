package com.example.orderservice.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
public class RequestOrder {

    private String productId;
    private Integer qty;
    private Integer unitPrice;

}
