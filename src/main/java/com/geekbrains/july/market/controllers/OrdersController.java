package com.geekbrains.july.market.controllers;

import com.geekbrains.july.market.beans.Cart;
import com.geekbrains.july.market.entities.Order;
import com.geekbrains.july.market.entities.User;
import com.geekbrains.july.market.services.OrderService;
import com.geekbrains.july.market.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
public class OrdersController {
    private UserService userService;
    private OrderService orderService;
    private Cart cart;

    @PostMapping("/confirm")
    @ResponseStatus(HttpStatus.OK)
    public void confirmOrder(Principal principal, @RequestParam String address) {
        User user = userService.findByPhone(principal.getName()).get();
        Order order = new Order(user, cart, user.getPhone(), address);
        order = orderService.saveOrder(order);
    }
}
