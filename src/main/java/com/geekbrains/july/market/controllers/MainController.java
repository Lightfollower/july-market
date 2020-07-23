package com.geekbrains.july.market.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String homePage() {
        System.out.println("ololo");
        return "redirect:/products";
    }
}
