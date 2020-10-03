package com.geekbrains.july.market.repositories;

import com.geekbrains.july.market.entities.Order;
import com.geekbrains.july.market.entities.User;
import com.geekbrains.july.market.entities.dtos.OrderDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    Optional<OrderDto> getOrderByUserPhone(String phone);
}
