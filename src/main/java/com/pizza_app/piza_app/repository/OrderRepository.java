package com.pizza_app.piza_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pizza_app.piza_app.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    
}
