package com.ironhack.OrderService.repository;

import com.ironhack.OrderService.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByOrderByDateDesc();

    @Query(value = "SELECT * FROM orders ORDER BY date DESC LIMIT 1", nativeQuery = true)
    Order findLastOrder();
}
