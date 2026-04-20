package com.example.clothingstore.controller;

import com.example.clothingstore.entity.Product;
import com.example.clothingstore.factory.ProductFactory;
import com.example.clothingstore.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {

    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String home(@RequestParam(value = "sortType", required = false) String sortType,
                       Model model) {

        List<Product> products;

        if (sortType != null && !sortType.isEmpty()) {
            products = productService.getSortedProducts(sortType);
        } else {
            products = productService.getAllProducts();
        }

        model.addAttribute("products", products);
        return "index";
    }

    @GetMapping("/add-product")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "add-product";
    }

    @PostMapping("/save-product")
    public String saveProduct(Product product) {
        Product newProduct = ProductFactory.createProduct(product.getTitle(), product.getPrice());
        productService.saveProduct(newProduct);
        return "redirect:/";
    }

    @GetMapping("/delete-product/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/";
    }

    @GetMapping("/edit-product/{id}")
    public String showEditProductForm(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "edit-product";
    }

    @PostMapping("/update-product")
    public String updateProduct(Product product) {
        productService.saveProduct(product);
        return "redirect:/";
    }

    @GetMapping("/search")
    public String searchProducts(@RequestParam("keyword") String keyword, Model model) {
        model.addAttribute("products", productService.searchProductsByTitle(keyword));
        model.addAttribute("keyword", keyword);
        return "index";
    }
}