package com.example.orderservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Setter
@Builder
public class Schema {

    private String type;
    private List<Field> fields;
    private boolean optional;
    private String name;

}
