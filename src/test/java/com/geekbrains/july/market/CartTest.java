package com.geekbrains.july.market;

import com.geekbrains.july.market.beans.Cart;
import com.geekbrains.july.market.entities.OrderItem;
import com.geekbrains.july.market.entities.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.function.Consumer;

@SpringBootTest
public class CartTest {
    @Autowired
    private Cart cart;

    @Test
    public void cartDevastationTest() {
        for (int i = 0; i < 10; i++) {
            Product product = new Product();
            long productId = i + 1;
            product.setId(productId);
            product.setPrice(new BigDecimal(100 + productId * 10));
            product.setTitle("Product #" + productId);
            cart.add(product);
        }

        cart.clear();
        Assertions.assertEquals(0, cart.getPrice().doubleValue());
        Assertions.assertEquals(0, cart.getItems().size());
    }

    @Test
    public void takingAwayOnePieceAtATimeTest() {
        int i = 5;
        for (int a = 0; a < i; a++) {
            Product product = new Product();
            long productId = a + 1;
            product.setId(productId);
            product.setPrice(new BigDecimal(100 + productId * 10));
            product.setTitle("Product #" + productId);
            for (int j = 0; j < 5; j++) {
                cart.add(product);
            }
        }
        cart.decrement(cart.getItems().get(1).getProduct());
        Assertions.assertEquals(4, cart.getItems().get(1).getQuantity());
        cart.decrement(cart.getItems().get(1).getProduct());
        Assertions.assertEquals(3, cart.getItems().get(1).getQuantity());
    }

    @Test
    public void FindAndEliminateAProductByNameTest() {
        int i = 5;
        for (int a = 0; a < i; a++) {
            Product product = new Product();
            long productId = a + 1;
            product.setId(productId);
            product.setPrice(new BigDecimal(100 + productId * 10));
            product.setTitle("Product #" + productId);
            for (int j = 0; j < 5; j++) {
                cart.add(product);
            }
        }
        long id = 1;
        cart.removeByProductId(id);
        for (OrderItem orderItem :
                cart.getItems()) {
            if (orderItem.getProduct().getId() == id)
                Assertions.fail();
        }
    }

    @Test
    public void countingEveryPennyTest() {
        BigDecimal totalPrice = new BigDecimal(0);
        BigDecimal priceOfNewProduct;
        int i = 5;
        for (int a = 0; a < i; a++) {
            Product product = new Product();
            long productId = a + 1;
            product.setId(productId);
            priceOfNewProduct = new BigDecimal(100 + productId * 10);
            product.setPrice(priceOfNewProduct);
            product.setTitle("Product #" + productId);
            for (int j = 0; j < 5; j++) {
                totalPrice = totalPrice.add(priceOfNewProduct);
                cart.add(product);
            }
        }
        Assertions.assertEquals(totalPrice, cart.getPrice());
        cart.removeByProductId(1L);
        for (int j = 0; j < 5; j++) {
            totalPrice = totalPrice.subtract(new BigDecimal(100 + 1 * 10));
        }
        Assertions.assertEquals(totalPrice, cart.getPrice());
    }

    @Test
    public void cartFillingTest() {
        for (int i = 0; i < 10; i++) {
            Product product = new Product();
            long productId = i / 2 + 1;
            product.setId(productId);
            product.setPrice(new BigDecimal(100 + productId * 10));
            product.setTitle("Product #" + productId);
            cart.add(product);
        }
        Assertions.assertEquals(5, cart.getItems().size());
        cart.clear();
        Assertions.assertEquals(0, cart.getItems().size());
    }
}