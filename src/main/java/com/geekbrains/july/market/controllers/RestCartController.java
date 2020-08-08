package com.geekbrains.july.market.controllers;


import com.geekbrains.july.market.beans.Cart;
import com.geekbrains.july.market.entities.OrderItem;
import com.geekbrains.july.market.entities.Product;
import com.geekbrains.july.market.exceptions.ProductNotFoundException;
import com.geekbrains.july.market.services.ProductsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/cart")
@Api("Set of endpoints for CRUD operations for Cart")
@AllArgsConstructor
public class RestCartController {
    private ProductsService productsService;
    private Cart cart;

    @GetMapping(produces = "application/json")
    @ApiOperation("Returns list of items in the cart")
    public List<OrderItem> getCart() {
        return cart.getCart();
    }

//    @GetMapping(value = "/{id}", produces = "application/json")
//    @ApiOperation("Returns one product by id")
//    public ResponseEntity<?> getOneProduct(@PathVariable @ApiParam("Id of the product to be requested. Cannot be empty") Long id) {
//        if (!productsService.existsById(id)) {
//            throw new ProductNotFoundException("Product not found, id: " + id);
//        }
//        return new ResponseEntity<>(productsService.findById(id), HttpStatus.OK);
//    }

//    @DeleteMapping
//    @ApiOperation("Removes all products")
//    public void deleteAllProducts() {
//        productsService.deleteAll();
//    }

    @DeleteMapping("/{id}")
    @ApiOperation("Removes one item by id")
    public List<OrderItem> deleteOneProducts(@PathVariable Long id) {
        cart.removeByProductId(id);
        return cart.getCart();
    }

    @PostMapping("/{productId}")
    public void addProductToCartById(@PathVariable Long productId/*, HttpServletRequest request, HttpServletResponse response*/) {
        Product product = productsService.findById(productId);
        cart.add(product);
//        response.sendRedirect(request.getHeader("referer"));
    }

//    @PostMapping(consumes = "application/json", produces = "application/json")
//    @ResponseStatus(HttpStatus.CREATED)
//    @ApiOperation("Creates a new product")
//    public Product saveNewProduct(@RequestBody Product product) {
//        if (product.getId() != null) {
//            product.setId(null);
//        }
//        return productsService.saveOrUpdate(product);
//    }

//    @PutMapping(consumes = "application/json", produces = "application/json")
//    @ApiOperation("Modifies an existing product")
//    public ResponseEntity<?> modifyProduct(@RequestBody Product product) {
//        if (product.getId() == null || !productsService.existsById(product.getId())) {
//            throw new ProductNotFoundException("Product not found, id: " + product.getId());
//        }
//        if (product.getPrice().doubleValue() < 0.0) {
//            return new ResponseEntity<>("Product's price can not be negative", HttpStatus.BAD_REQUEST);
//        }
//        return new ResponseEntity<>(productsService.saveOrUpdate(product), HttpStatus.OK);
//    }

    @ExceptionHandler
    public ResponseEntity<?> handleException(ProductNotFoundException exc) {
        return new ResponseEntity<>(exc.getMessage(), HttpStatus.NOT_FOUND);
    }
}
