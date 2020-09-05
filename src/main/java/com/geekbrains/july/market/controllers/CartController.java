package com.geekbrains.july.market.controllers;

import com.geekbrains.july.market.beans.Cart;
import com.geekbrains.july.market.services.ProductsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {
    private ProductsService productsService;
    private Cart cart;

    @GetMapping
    public String showCartPage(Model model) {
        return "cart";
    }
}
