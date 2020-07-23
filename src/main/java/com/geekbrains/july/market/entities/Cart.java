package com.geekbrains.july.market.entities;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Cart {
    List<Product> cart;

    public Cart() {
        cart = new ArrayList<>();
    }
}
