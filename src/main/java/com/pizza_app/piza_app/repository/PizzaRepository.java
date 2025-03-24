package com.pizza_app.piza_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pizza_app.piza_app.model.Pizza;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
    public Pizza findByName(String name);
    public Pizza findByImage(String image);
}
