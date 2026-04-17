package com.example.clothingstore.controller;

import com.example.clothingstore.entity.CartItem;
import com.example.clothingstore.entity.Product;
import com.example.clothingstore.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {

    private final ProductService productService;

    public CartController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/cart/add/{id}")
    public String addToCart(@PathVariable Long id, HttpSession session) {
        Product product = productService.getProductById(id);

        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

        if (cart == null) {
            cart = new ArrayList<>();
        }

        boolean found = false;

        for (CartItem item : cart) {
            if (item.getProduct().getId().equals(id)) {
                item.setQuantity(item.getQuantity() + 1);
                found = true;
                break;
            }
        }

        if (!found) {
            cart.add(new CartItem(product, 1));
        }

        session.setAttribute("cart", cart);
        return "redirect:/";
    }

    @GetMapping("/cart")
    public String viewCart(HttpSession session, Model model) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

        if (cart == null) {
            cart = new ArrayList<>();
        }

        double total = 0;
        for (CartItem item : cart) {
            total += item.getTotalPrice();
        }

        model.addAttribute("cart", cart);
        model.addAttribute("total", total);

        return "cart";
    }

    @GetMapping("/cart/remove/{id}")
    public String removeFromCart(@PathVariable Long id, HttpSession session) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

        if (cart != null) {
            cart.removeIf(item -> item.getProduct().getId().equals(id));
        }

        session.setAttribute("cart", cart);
        return "redirect:/cart";
    }
}