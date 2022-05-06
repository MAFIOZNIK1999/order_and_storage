package com.example.ordersandstorage.service.mapper;

public interface ResponseDtoMapper<R, M> {
    R mapToDto(M t);
}