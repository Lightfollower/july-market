package com.geekbrains.july.market.services;

import com.geekbrains.july.market.entities.Order;
import com.geekbrains.july.market.entities.User;
import com.geekbrains.july.market.entities.dtos.OrderDto;
import com.geekbrains.july.market.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public Optional<OrderDto> getOrderByUserPhone(String phone){
        return orderRepository.getOrderByUserPhone(phone);
    }
}
