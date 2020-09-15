package com.geekbrains.july.market.controllers;

import com.geekbrains.july.market.entities.Product;
import com.geekbrains.july.market.entities.dtos.ProductDto;
import com.geekbrains.july.market.exceptions.ResourceNotFoundException;
import com.geekbrains.july.market.services.ProductService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/products")
@Api("Set of endpoints for CRUD operations for Products")
@AllArgsConstructor
public class ProductsController {
    private ProductService productService;

    @GetMapping(produces = "application/json")
    @ApiOperation("Returns list of all products")
    public List<ProductDto> getAllProducts(@RequestParam(name = "p") int pageNum) {
        return productService.getDtoData(pageNum).getContent();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @ApiOperation("Returns one product by id")
//    @ApiImplicitParams(value = {
//            @ApiImplicitParam(name = "demo", type = "String", required = false, paramType = "query")
//    })
    public ResponseEntity<?> getOneProduct(@PathVariable @ApiParam("Id of the product to be requested. Cannot be empty") Long id) {
        if (!productService.existsById(id)) {
            throw new ResourceNotFoundException("Product not found, id: " + id);
        }
        return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping
    @ApiOperation("Removes all products")
    public void deleteAllProducts() {
        productService.deleteAll();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Deletes a product from the system. 404 if the product's identifier is not found.")
    public void deleteOneProduct(@PathVariable Long id) {
        if (!productService.existsById(id)) {
            throw new ResourceNotFoundException("Product with id: " + id + " doesn't exists (for delete)");
        }
        productService.deleteById(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Creates a new product. If id != null, then it will be cleared")
    public Product saveNewProduct(@RequestBody Product product) {
        if (product.getId() != null) {
            product.setId(null);
        }
        return productService.saveOrUpdate(product);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    @ApiOperation("Modifies an existing product")
    public ResponseEntity<?> modifyProduct(@RequestBody Product product) {
        if (product.getId() == null || !productService.existsById(product.getId())) {
            throw new ResourceNotFoundException("Product not found, id: " + product.getId());
        }
        if (product.getPrice().doubleValue() < 0.0) {
            return new ResponseEntity<>("Product's price can not be negative", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(productService.saveOrUpdate(product), HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleException(ResourceNotFoundException exc) {
        return new ResponseEntity<>(exc.getMessage(), HttpStatus.NOT_FOUND);
    }
}