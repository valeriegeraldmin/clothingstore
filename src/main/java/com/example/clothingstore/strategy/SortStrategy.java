package com.example.clothingstore.strategy;

import com.example.clothingstore.entity.Product;
import java.util.List;

public interface SortStrategy {
    List<Product> sort(List<Product> products);
}