package com.example.ordersandstorage.service.mapper;

import com.example.ordersandstorage.dto.OrderRequestDto;
import com.example.ordersandstorage.dto.OrderResponseDto;
import com.example.ordersandstorage.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper implements RequestDtoMapper<OrderRequestDto, Order>,
        ResponseDtoMapper<OrderResponseDto, Order> {
    @Override
    public Order mapToModel(OrderRequestDto requestDto) {
        Order order = new Order();
        order.setItem(requestDto.getItem());
        order.setPrice(requestDto.getPrice());
        return order;
    }

    @Override
    public OrderResponseDto mapToDto(Order order) {
        OrderResponseDto responseDto = new OrderResponseDto();
        responseDto.setId(order.getId());
        responseDto.setItem(order.getItem());
        responseDto.setPrice(order.getPrice());
        responseDto.setQuantity(order.getQuantity());
        return responseDto;
    }
}
