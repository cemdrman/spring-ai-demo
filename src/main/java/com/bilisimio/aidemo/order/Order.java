package com.bilisimio.aidemo.order;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Order {

    private Long userId;
    private String productName;
    private LocalDate orderDate;

}