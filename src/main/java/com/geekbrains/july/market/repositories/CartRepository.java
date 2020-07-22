package com.geekbrains.july.market.repositories;

import com.geekbrains.july.market.entities.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartRepository {
    List<Product> cart;

    public CartRepository() {
        cart = new ArrayList<>();
//        cart.add(new Product(1L, "Ololo", 500));
//        cart.add(new Product(2L, "DOlolo", 600));
    }

    public List<Product> getCart() {
        return cart;
    }

    public Product addProductToCart(Product product) {
        cart.add(product);
        return product;
    }
}
