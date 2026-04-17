package com.example.clothingstore.service;

import com.example.clothingstore.entity.Product;

import java.util.List;
import org.springframework.data.domain.Sort;

public interface ProductService {

    Product saveProduct(Product product);

    List<Product> getAllProducts();

    Product getProductById(Long id);

    void deleteProduct(Long id);

    List<Product> searchProductsByTitle(String title);

    List<Product> getAllProducts(Sort sort);
}