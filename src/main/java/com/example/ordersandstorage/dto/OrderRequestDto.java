package com.example.ordersandstorage.dto;

import lombok.Data;

@Data
public class OrderRequestDto {
    private Long price;
    private String item;
}
