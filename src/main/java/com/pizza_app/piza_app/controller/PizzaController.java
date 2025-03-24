package com.pizza_app.piza_app.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.pizza_app.piza_app.model.Order;
import com.pizza_app.piza_app.repository.OrderRepository;
import com.pizza_app.piza_app.repository.PizzaRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {
    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping({ "/", "" })
    public String getPizzas(Model model) {
        var pizzas = pizzaRepository.findAll();
        model.addAttribute("pizzas", pizzas);
        return "pizzas/index";
    }

    @GetMapping("/order")
    public String orderPizza(Model model, @RequestParam("id") int id) {
        var pizza = pizzaRepository.findById(id).orElse(null);

        if (pizza == null) {
            return "redirect:/pizzas";
        }

        model.addAttribute("pizza", pizza);
        model.addAttribute("order", new Order());

        return "pizzas/order";
    }

    @PostMapping("/order")
    public String orderPizza(
            @RequestParam("id") int id,
            @Valid @ModelAttribute Order order,
            BindingResult bindingResult,
            Model model) {

        var pizza = pizzaRepository.findById(id).orElse(null);

        if (bindingResult.hasErrors()) {
            model.addAttribute("pizza", pizza);
            return "pizzas/order";
        }


        if (pizza == null) {
            return "redirect:/pizzas";
        }

        try {
            order.setId(null);
            order.setPizzaName(pizza.getName());
            order.setDate(new Date());
            orderRepository.save(order);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("pizza", pizza);
            return "pizzas/order";
        }

        return "redirect:/pizzas";
    }

}
