package com.example.clothingstore.strategy;

import com.example.clothingstore.entity.Product;

import java.util.Comparator;
import java.util.List;

public class PriceLowToHighStrategy implements SortStrategy {

    @Override
    public List<Product> sort(List<Product> products) {
        products.sort(Comparator.comparing(Product::getPrice));
        return products;
    }
}