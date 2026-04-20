package com.example.clothingstore.controller;

import com.example.clothingstore.entity.CartItem;
import com.example.clothingstore.entity.Order;
import com.example.clothingstore.entity.Product;
import com.example.clothingstore.service.OrderService;
import com.example.clothingstore.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class OrderController {

    private final OrderService orderService;
    private final ProductService productService;

    public OrderController(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }

    @GetMapping("/checkout")
    public String showCheckoutPage() {
        return "checkout";
    }

    @PostMapping("/place-order")
    public String placeOrder(Order order, Model model, HttpSession session) {
    List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

    com.example.clothingstore.entity.User loggedInUser =
            (com.example.clothingstore.entity.User) session.getAttribute("loggedInUser");

    if (loggedInUser != null) {
        order.setCustomerName(loggedInUser.getUsername());
    }

    if (cart != null) {
        double total = 0.0;

        for (CartItem item : cart) {
            Product product = item.getProduct();

            int newStock = product.getStockQuantity() - item.getQuantity();
            product.setStockQuantity(newStock);

            productService.saveProduct(product);

            total += product.getPrice() * item.getQuantity();
        }

        order.setTotalPrice(total);
    }

    orderService.saveOrder(order);
    session.removeAttribute("cart");

    model.addAttribute("order", order);
    return "confirmation";
}

    @GetMapping("/orders")
    public String viewOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "orders";
    }
}