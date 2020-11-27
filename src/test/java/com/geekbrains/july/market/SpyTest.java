package com.geekbrains.july.market;


import com.geekbrains.july.market.beans.Cart;
import com.geekbrains.july.market.entities.Product;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SpyTest {
    @Spy
    private Cart spiedCart = new Cart();

    @Test
    public void spyTest() {
        spiedCart.init();
        spiedCart.add(new Product(1L, "ololo1", new BigDecimal(10.0)));
        spiedCart.add(new Product(2L, "ololo2", new BigDecimal(10.0)));
        spiedCart.add(new Product(3L, "ololo3", new BigDecimal(10.0)));
        spiedCart.add(new Product(4L, "ololo4", new BigDecimal(10.0)));

        assertEquals(4, spiedCart.getItems().size());

        Mockito.doReturn(100).when(spiedCart.getItems()).size();

        assertEquals(100, spiedCart.getItems().size());

        System.out.println(spiedCart.getClass().getName());
    }
}
