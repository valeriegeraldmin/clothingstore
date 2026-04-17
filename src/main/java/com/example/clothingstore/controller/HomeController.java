package com.example.clothingstore.controller;

import com.example.clothingstore.entity.Product;
import com.example.clothingstore.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "index";
    }

    @GetMapping("/add-product")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "add-product";
    }

    @PostMapping("/save-product")
    public String saveProduct(Product product) {
        productService.saveProduct(product);
        return "redirect:/";
    }
    @GetMapping("/delete-product/{id}")
public String deleteProduct(@PathVariable Long id) {
    productService.deleteProduct(id);
    return "redirect:/";
}
}