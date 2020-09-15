package com.geekbrains.july.market.controllers;

import com.geekbrains.july.market.entities.Category;
import com.geekbrains.july.market.entities.Product;
import com.geekbrains.july.market.services.CategoryService;
import com.geekbrains.july.market.services.ProductService;
import com.geekbrains.july.market.utils.ProductFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/products")
@Deprecated
public class OldProductsController {
    private ProductService productService;
    private CategoryService categoryService;

    @Autowired
    public OldProductsController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String showAll(Model model, @RequestParam Map<String, String> requestParams, @RequestParam(name = "categories", required = false) List<Long> categoriesIds) {
        Integer pageNumber = Integer.parseInt(requestParams.getOrDefault("p", "1"));

        List<Category> categoriesFilter = null;
        if (categoriesIds != null) {
            categoriesFilter = categoryService.getCategoriesByIds(categoriesIds);
        }
        ProductFilter productFilter = new ProductFilter(requestParams, categoriesFilter);
        Page<Product> products = productService.findAll(productFilter.getSpec(), pageNumber);
        model.addAttribute("products", products);
        model.addAttribute("filterDef", productFilter.getFilterDefinition().toString());
        return "all_products";
    }

    @GetMapping("/add")
    public String showAddForm() {
        return "add_product_form";
    }

    @PostMapping("/add")
    public String saveNewProduct(@ModelAttribute Product product) {
        productService.saveOrUpdate(product);
        return "redirect:/products/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "edit_product_form";
    }

    @PostMapping("/edit")
    public String modifyProduct(@ModelAttribute Product product) {
        productService.saveOrUpdate(product);
        return "redirect:/products/";
    }
}