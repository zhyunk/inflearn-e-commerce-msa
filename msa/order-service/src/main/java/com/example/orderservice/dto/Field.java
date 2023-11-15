package com.example.orderservice.dto;

import lombok.*;

@ToString
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Field {

    private String type;
    private boolean optional;
    private String field;

}
