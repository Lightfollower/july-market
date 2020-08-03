package com.geekbrains.july.market.entities;

import lombok.Data;

@Data
public class OrderItem {
    private Product product;

    private int quantity;

    private int price;

    public OrderItem(Product product, int quantity, int price) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }
}
