package com.example.ordersandstorage.controller;

import java.util.List;
import java.util.stream.Collectors;
import com.example.ordersandstorage.dto.OrderRequestDto;
import com.example.ordersandstorage.dto.OrderResponseDto;
import com.example.ordersandstorage.model.Order;
import com.example.ordersandstorage.service.OrderService;
import com.example.ordersandstorage.service.mapper.OrderMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private OrderService service;
    private OrderMapper mapper;

    @GetMapping
    ResponseEntity<List<OrderResponseDto>> getAllOrders() {
        return ResponseEntity.ok(service.findAll()
                .stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public OrderResponseDto getById(@PathVariable Long id) {
        return mapper.mapToDto(service.getById(id));
    }

    @PostMapping
    public OrderResponseDto create(@RequestBody OrderRequestDto order) {
        return mapper.mapToDto(service.save(mapper.mapToModel(order)));
    }

    @GetMapping("/get-by-item")
    public ResponseEntity<List<OrderResponseDto>> getByItem(@RequestParam String item) {
        List<Order> lowestOrderByItem = service.findLowestOrderByItem(item);
        return ResponseEntity.ok(lowestOrderByItem.stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList()));
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}
