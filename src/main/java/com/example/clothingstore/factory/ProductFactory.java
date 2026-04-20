package com.example.clothingstore.factory;

import com.example.clothingstore.entity.Product;

public class ProductFactory {

    public static Product createProduct(String title, double price) {
        Product product = new Product();
        product.setTitle(title);
        product.setPrice(price);
        return product;
    }
}