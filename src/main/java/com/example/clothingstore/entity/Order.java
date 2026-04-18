package com.example.clothingstore.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    @Column(name = "customer_name")
    private String customerName;

    private String phone;

    @Column(name = "total_price")
    private Double totalPrice;

    public Order() {
    }

    public Order(String customerName, String address, String phone, Double totalPrice) {
        this.customerName = customerName;
        this.address = address;
        this.phone = phone;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}