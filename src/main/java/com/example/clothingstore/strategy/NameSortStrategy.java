package com.example.clothingstore.strategy;

import com.example.clothingstore.entity.Product;

import java.util.Comparator;
import java.util.List;

public class NameSortStrategy implements SortStrategy {

    @Override
    public List<Product> sort(List<Product> products) {
        products.sort(Comparator.comparing(Product::getTitle));
        return products;
    }
}