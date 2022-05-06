package com.example.ordersandstorage.service.mapper;

public interface RequestDtoMapper<R, M> {
    M mapToModel(R dto);
}