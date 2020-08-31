package com.geekbrains.july.market.services;

import com.geekbrains.july.market.entities.Product;
import com.geekbrains.july.market.entities.dtos.ProductDto;
import com.geekbrains.july.market.exceptions.ProductNotFoundException;
import com.geekbrains.july.market.products.ProductSOAP;
import com.geekbrains.july.market.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductsService {
    private ProductsRepository productsRepository;

    @Autowired
    public void setProductsRepository(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public Product saveOrUpdate(Product product) {
        return productsRepository.save(product);
    }

    public Product findById(Long id) {
        return productsRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Can't found product with id = " + id));
    }

    public List<Product> findAll() {
        return productsRepository.findAll();
    }

    public Page<Product> findAll(Specification<Product> spec, Integer page) {
        if (page < 1L) {
            page = 1;
        }
        return productsRepository.findAll(spec, PageRequest.of(page - 1, 5));
    }

    public void deleteById(Long id) {
        productsRepository.deleteById(id);
    }

    public void deleteAll() {
        productsRepository.deleteAll();
    }

    public boolean existsById(Long id) {
        return productsRepository.existsById(id);
    }

    public List<ProductDto> getDtoData() {
        return productsRepository.findAllBy();
    }

    public ProductSOAP findOneProductSOAPByTitle(String title) {
        Product product = productsRepository.findByTitle(title);
        ProductSOAP productSOAP = new ProductSOAP();
        productSOAP.setId(product.getId());
        productSOAP.setTitle(product.getTitle());
        productSOAP.setPrice(product.getPrice());
        return productSOAP;
    }

    public List<ProductSOAP> findAllSOAP() {
        List<Product> products = productsRepository.findAll();
        List<ProductSOAP> productSOAPS = new ArrayList<>();
        ProductSOAP productSOAP;
        for (Product p:
             products) {
            productSOAP = new ProductSOAP();
            productSOAP.setId(p.getId());
            productSOAP.setTitle(p.getTitle());
            productSOAP.setPrice(p.getPrice());
            productSOAPS.add(productSOAP);
        }
        return productSOAPS;
    }
}
