package com.example.ordersandstorage.service;

import java.util.List;
import com.example.ordersandstorage.model.Order;

public interface OrderService {
    Order save(Order order);

    List<Order> findAll();

    Order getById(Long id);

    void deleteById(Long id);

    List<Order> findLowestOrderByItem(String item);
}
