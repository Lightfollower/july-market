package com.geekbrains.july.market.repositories;

import com.geekbrains.july.market.entities.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CartRepository {
    Map<Product, Integer> cart;

    public CartRepository() {
        cart = new HashMap<>();
//        cart.add(new Product(1L, "Ololo", 500));
//        cart.add(new Product(2L, "DOlolo", 600));
    }

    public Map<Product, Integer> getCart() {
        return cart;
    }

    public Product addProductToCart(Product product) {
        for (Product p :
                cart.keySet()) {
            if (p.getId() == product.getId()) {
                cart.replace(p, cart.get(p)+1);
                return product;
            }
        }
        cart.put(product, 1);
        return product;
    }
}
