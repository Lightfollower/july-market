package com.geekbrains.july.market.services;

import com.geekbrains.july.market.entities.Product;
import com.geekbrains.july.market.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CartService {
    CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Map<Product, Integer> getCart() {
        return cartRepository.getCart();
    }

    public Product addProductToCart(Product product) {
        return cartRepository.addProductToCart(product);
    }
}
