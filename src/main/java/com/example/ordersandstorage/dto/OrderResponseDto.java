package com.example.ordersandstorage.dto;

import lombok.Data;

@Data
public class OrderResponseDto {
    private Long id;
    private Long quantity;
    private Long price;
    private String item;
}
