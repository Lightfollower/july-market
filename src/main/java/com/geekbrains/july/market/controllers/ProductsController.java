package com.geekbrains.july.market.controllers;

import com.geekbrains.july.market.entities.Category;
import com.geekbrains.july.market.entities.Product;
import com.geekbrains.july.market.services.CartService;
import com.geekbrains.july.market.services.CategoriesService;
import com.geekbrains.july.market.services.ProductsService;
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
public class ProductsController {
    private ProductsService productsService;
    private CategoriesService categoriesService;
    private CartService cartService;

    @Autowired
    public ProductsController(ProductsService productsService, CategoriesService categoriesService, CartService cartService) {
        this.productsService = productsService;
        this.categoriesService = categoriesService;
        this.cartService = cartService;
    }

    @GetMapping
    public String showAll(Model model, @RequestParam Map<String, String> requestParams, @RequestParam(name = "categories", required = false) List<Long> categoriesIds) {
        Integer pageNumber = Integer.parseInt(requestParams.getOrDefault("p", "1"));
        List<Category> categoriesFilter = null;
        if (categoriesIds != null) {
            categoriesFilter = categoriesService.getCategoriesByIds(categoriesIds);
        }
        ProductFilter productFilter = new ProductFilter(requestParams, categoriesFilter);
        Page<Product> products = productsService.findAll(productFilter.getSpec(), pageNumber);
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
        productsService.saveOrUpdate(product);
        return "redirect:/products/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productsService.findById(id));
        return "edit_product_form";
    }

    @PostMapping("/edit")
    public String modifyProduct(@ModelAttribute Product product) {
        productsService.saveOrUpdate(product);
        return "redirect:/products/";
    }

    @GetMapping("/buy/{id}")
    public String buyProduct(@PathVariable Long id) {
        System.out.println("ololo");
        cartService.addProductToCart(productsService.findById(id));
        return "redirect:/products/";
    }
}