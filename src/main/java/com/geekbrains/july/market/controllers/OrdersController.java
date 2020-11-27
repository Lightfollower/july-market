package com.geekbrains.july.market.controllers;

import com.geekbrains.july.market.beans.Cart;
import com.geekbrains.july.market.entities.Order;
import com.geekbrains.july.market.entities.User;
import com.geekbrains.july.market.services.OrderService;
import com.geekbrains.july.market.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/orders")
@AllArgsConstructor
public class OrdersController {
    private UserService userService;
    private OrderService orderService;
    private Cart cart;

    @GetMapping("/create")
    public String createOrder(Principal principal, Model model) {
        User user = userService.findByPhone(principal.getName()).get();
        model.addAttribute("user", user);
        return "order_info";
    }

    @PostMapping("/confirm")
    @ResponseBody
    public String confirmOrder(Principal principal, @RequestParam String address, @RequestParam String phone) {
        User user = userService.findByPhone(principal.getName()).get();
        Order order = new Order(user, cart, phone, address);
        order = orderService.saveOrder(order);
        return order.getId() + " " + order.getPrice();
    }
}
