package com.geekbrains.july.market.configs;

import com.geekbrains.july.market.products.Country;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class ProductRepository {
    private static final Map<String, Country> products = new HashMap<>();

    @PostConstruct
    public void initData() {
        Country spain = new Country();
        spain.setTitle("Spain");
        spain.setPrice(46704314);
        products.put(spain.getTitle(), spain);

        Country uk = new Country();
        uk.setTitle("United Kingdom");
        uk.setPrice(63705000);
        products.put(uk.getTitle(), uk);
    }

    public Country findProduct(String title) {
        Assert.notNull(title, "The country's name must not be null");
        return products.get(title);
    }
}
