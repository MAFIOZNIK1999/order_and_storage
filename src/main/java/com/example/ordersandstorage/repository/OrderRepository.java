package com.example.ordersandstorage.repository;

import java.util.List;
import java.util.Optional;
import com.example.ordersandstorage.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findOrderByItemAndPrice(String item, Long price);

    List<Order> findOrderByItem(String item);

    Optional<Order> deleteOrderById(Long id);

    @Query(value = "SELECT * FROM orders o WHERE o.item = ?1 ORDER BY o.price ASC LIMIT 1", nativeQuery = true)
    Optional<Order> findOrder(String item);
}
