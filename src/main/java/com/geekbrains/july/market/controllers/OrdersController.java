package com.geekbrains.july.market.controllers;

import com.geekbrains.july.market.beans.Cart;
import com.geekbrains.july.market.entities.Order;
import com.geekbrains.july.market.entities.OrderItem;
import com.geekbrains.july.market.entities.User;
import com.geekbrains.july.market.entities.dtos.OrderDto;
import com.geekbrains.july.market.services.OrderService;
import com.geekbrains.july.market.services.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
public class OrdersController {
    private UserService userService;
    private OrderService orderService;
    private Cart cart;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Returns current order")
    @ResponseBody
    public ResponseEntity<?> getOrder(Principal principal){
        OrderDto orderDto = orderService.getOrderByUserPhone(principal.getName()).get();
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }
    @PostMapping("/confirm")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Saving order to database")
    public void confirmOrder(Principal principal, @RequestParam String address) {
        User user = userService.findByPhone(principal.getName()).get();
        Order order = new Order(user, cart, user.getPhone(), address);
        order = orderService.saveOrder(order);
    }
}
