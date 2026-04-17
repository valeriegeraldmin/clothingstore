package com.example.clothingstore.controller;

import com.example.clothingstore.entity.Product;
import com.example.clothingstore.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestParam;

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
@GetMapping("/sort")
public String sortProducts(@RequestParam("field") String field,
                           @RequestParam("direction") String direction,
                           Model model) {

    Sort sort = direction.equalsIgnoreCase("asc")
            ? Sort.by(field).ascending()
            : Sort.by(field).descending();

    model.addAttribute("products", productService.getAllProducts(sort));
    return "index";
}
}