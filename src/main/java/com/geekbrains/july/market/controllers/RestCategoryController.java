package com.geekbrains.july.market.controllers;

import com.geekbrains.july.market.entities.Category;
import com.geekbrains.july.market.exceptions.CategoryNotFoundException;
import com.geekbrains.july.market.services.CategoriesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/categories")
@Api("Set of endpoints for CRUD operations for Categories")
@AllArgsConstructor
public class RestCategoryController {
    private CategoriesService categoriesService;

    @GetMapping(produces = "application/json")
    @ApiOperation("Returns list of all categories")
    public List<Category> getAllCategories(@RequestParam Map<String, String> requestParams) {
        return categoriesService.findAll();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @ApiOperation("Returns one product by id")
    public ResponseEntity<?> getOneCategory(@PathVariable @ApiParam("Id of the category to be requested. Cannot be empty") Long id) {
        return new ResponseEntity<>(categoriesService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping
    @ApiOperation("Removes all categories")
    public void deleteAllCategories() {
        categoriesService.deleteAll();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Removes one category by id")
    public String deleteOneCategory(@PathVariable Long id) {
        String category = categoriesService.findById(id).toString();
        categoriesService.deleteById(id);
        return category;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Creates a new category")
    public Category saveNewCategory(@RequestBody Category category) {
        if (category.getId() != null) {
            category.setId(null);
        }
        return categoriesService.saveOrUpdate(category);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    @ApiOperation("Modifies an existing category")
    public ResponseEntity<?> modifyProduct(@RequestBody Category category) {
        if (category.getId() == null || !categoriesService.existsById(category.getId())) {
            throw new CategoryNotFoundException("Product not found, id: " + category.getId());
        }
        return new ResponseEntity<>(categoriesService.saveOrUpdate(category), HttpStatus.OK);
    }
}