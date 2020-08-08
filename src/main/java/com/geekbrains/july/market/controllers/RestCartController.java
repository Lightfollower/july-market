package com.geekbrains.july.market.controllers;


import com.geekbrains.july.market.beans.Cart;
import com.geekbrains.july.market.entities.Order;
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

    @PostMapping()
    @ApiOperation("Put one product to cart")
    public List<OrderItem> addProductToCartById(@RequestBody Product product) {
//        Product product = productsService.findById(productId);
        cart.add(product);
        return cart.getItems();
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

    @PutMapping(consumes = "application/json", produces = "application/json")
    @ApiOperation("Modifies an quantity")
    public List<OrderItem> modifyQuantity(@RequestBody Product product) {
        cart.decrement(product);
        return cart.getItems();
    }

    @ExceptionHandler
    public ResponseEntity<?> handleException(ProductNotFoundException exc) {
        return new ResponseEntity<>(exc.getMessage(), HttpStatus.NOT_FOUND);
    }
}
