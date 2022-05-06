package com.example.ordersandstorage.service.impl;

import java.time.Instant;
import java.util.List;
import com.example.ordersandstorage.model.Order;
import com.example.ordersandstorage.repository.OrderRepository;
import com.example.ordersandstorage.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private OrderRepository repository;
    public static final String SEND_TIME = "60000";
    public static final Integer DELETE_TIME = 600;


    @Override
    public Order save(Order order) {
        if (repository.findOrderByItemAndPrice(order.getItem(), order.getPrice()).isEmpty()) {
            order.setCreateTime(Instant.now());
            repository.findOrderByItem(order.getItem());
            order.setQuantity(1L);
            return repository.save(order);
        }
        return addQuantity(order);
    }

    @Override
    public List<Order> findAll() {
        return repository.findAll();
    }

    @Override
    public Order getById(Long id) {
        return repository.getById(id);
    }

    public void deleteById(Long id) {
        repository.deleteOrderById(id).orElseThrow(() ->
                new RuntimeException("Can't deleted order by id " + id));
    }

    @Override
    public List<Order> findLowestOrderByItem(String item) {
        if (repository.findOrder(item).isEmpty()) {
            return findAll();
        }
        return List.of(findOrder(item));
    }

    private Order findOrder(String item) {
        return repository.findOrder(item).orElseThrow(() ->
                new RuntimeException("Can't find order by item " + item));
    }

    private Order findOrderByItemAndPrice(String item, Long price) {
        return repository.findOrderByItemAndPrice(item, price).orElseThrow(() ->
                new RuntimeException("Can't find order by price and item " + price + " " + item));
    }

    private Order addQuantity(Order order) {
        Order orderByItem = findOrderByItemAndPrice(order.getItem(), order.getPrice());
        orderByItem.setQuantity(orderByItem.getQuantity() + 1);
        return repository.save(orderByItem);
    }

    @Scheduled(fixedRateString = SEND_TIME)
    private void checkOrder() {
        Instant now = Instant.now();
        for (Order order : findAll()) {
            Instant orderTime = order.getCreateTime();
            long milliseconds = now.getEpochSecond() - orderTime.getEpochSecond();
            if (milliseconds >= DELETE_TIME) {
                deleteById(order.getId());
            }
        }
    }
}
