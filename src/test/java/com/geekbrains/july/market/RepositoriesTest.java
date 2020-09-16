package com.geekbrains.july.market;

import com.geekbrains.july.market.entities.Product;
import com.geekbrains.july.market.repositories.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class RepositoriesTest {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void productRepositoryTest() {
        Product product = new Product(null, "Milk", new BigDecimal(350.0));
        Product out = entityManager.persist(product);
        entityManager.flush();

        List<Product> productsList = productRepository.findAll();

        Assertions.assertEquals(2, productsList.size());
        Assertions.assertEquals("Milk", productsList.get(0).getTitle());
    }

    @Test
    public void initDbTest() {
        List<Product> productsList = productRepository.findAll();
        Assertions.assertEquals(1, productsList.size());
    }
}
