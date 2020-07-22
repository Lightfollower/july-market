package com.geekbrains.july.market.controllers;

import com.geekbrains.july.market.entities.Product;
import com.geekbrains.july.market.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cart")
public class CartController {
    private CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public String cart(Model model) {
        Map<Product, Integer> products = cartService.getCart();
        model.addAttribute("products", products);
        return "cart";
    }
}
