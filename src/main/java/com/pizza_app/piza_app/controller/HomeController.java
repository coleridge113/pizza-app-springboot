package com.pizza_app.piza_app.controller;

import org.springframework.stereotype.Controller;

@Controller
public class HomeController {
    public String home() {
        return "home";
    }
}
