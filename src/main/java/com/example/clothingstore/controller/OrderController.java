package com.example.clothingstore.controller;

import com.example.clothingstore.entity.Order;
import com.example.clothingstore.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/checkout")
    public String showCheckoutPage() {
        return "checkout";
    }

    @PostMapping("/place-order")
public String placeOrder(Order order, Model model) {
    System.out.println("customerName = " + order.getCustomerName());
    System.out.println("address = " + order.getAddress());
    System.out.println("phone = " + order.getPhone());
    System.out.println("totalPrice = " + order.getTotalPrice());

    orderService.saveOrder(order);

    model.addAttribute("order", order);
    return "confirmation";
}
}