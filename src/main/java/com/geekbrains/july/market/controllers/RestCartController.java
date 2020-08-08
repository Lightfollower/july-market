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


    @DeleteMapping("/{id}")
    @ApiOperation("Removes one item by id")
    public List<OrderItem> deleteOneProducts(@PathVariable Long id) {
        cart.removeByProductId(id);
        return cart.getCart();
    }

    @PostMapping()
    @ApiOperation("Put one product to cart")
    public List<OrderItem> addProductToCartById(@RequestBody Product product) {
        cart.add(product);
        return cart.getItems();
    }

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
