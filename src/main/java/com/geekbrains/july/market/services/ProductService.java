package com.geekbrains.july.market.services;

import com.geekbrains.july.market.entities.Product;
import com.geekbrains.july.market.entities.dtos.ProductDto;
import com.geekbrains.july.market.entities.dtos.ProductSOAPDto;
import com.geekbrains.july.market.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product saveOrUpdate(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Page<Product> findAll(Specification<Product> spec, Integer page) {
        if (page < 1L) {
            page = 1;
        }
        return productRepository.findAll(spec, PageRequest.of(page - 1, 5));
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public void deleteAll() {
        productRepository.deleteAll();
    }

    public boolean existsById(Long id) {
        return productRepository.existsById(id);
    }

    public List<ProductDto> getDtoData() {
       return productRepository.findAllBy();
    }

    public ProductSOAPDto findOneProductSOAPByTitle(String title) {
        Product product = productRepository.findByTitle(title);
        ProductSOAPDto productSOAPDto = new ProductSOAPDto();
        productSOAPDto.setId(product.getId());
        productSOAPDto.setTitle(product.getTitle());
        productSOAPDto.setPrice(product.getPrice());
        return productSOAPDto;
    }

    public List<ProductSOAPDto> findAllSOAP() {
        List<Product> products = productRepository.findAll();
        List<ProductSOAPDto> productSOAPDtos = new ArrayList<>();
        ProductSOAPDto productSOAPDto;
        for (Product p :
                products) {
            productSOAPDto = new ProductSOAPDto();
            productSOAPDto.setId(p.getId());
            productSOAPDto.setTitle(p.getTitle());
            productSOAPDto.setPrice(p.getPrice());
            productSOAPDtos.add(productSOAPDto);
        }
        return productSOAPDtos;
    }
}
