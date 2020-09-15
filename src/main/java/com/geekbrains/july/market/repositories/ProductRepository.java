package com.geekbrains.july.market.repositories;

import com.geekbrains.july.market.entities.Product;
import com.geekbrains.july.market.entities.dtos.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    Page<ProductDto> findAllBy(Pageable pageable);
    Product findByTitle(String title);
}