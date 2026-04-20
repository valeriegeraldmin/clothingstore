package com.example.clothingstore.service;

import com.example.clothingstore.entity.Product;
import com.example.clothingstore.repository.ProductRepository;
import com.example.clothingstore.strategy.NameSortStrategy;
import com.example.clothingstore.strategy.PriceHighToLowStrategy;
import com.example.clothingstore.strategy.PriceLowToHighStrategy;
import com.example.clothingstore.strategy.SortStrategy;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> searchProductsByTitle(String title) {
        return productRepository.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public List<Product> getAllProducts(Sort sort) {
        return productRepository.findAll(sort);
    }

    @Override
    public List<Product> getSortedProducts(String sortType) {
        List<Product> products = productRepository.findAll();

        SortStrategy strategy;

        switch (sortType) {
            case "priceAsc":
                strategy = new PriceLowToHighStrategy();
                break;
            case "priceDesc":
                strategy = new PriceHighToLowStrategy();
                break;
            case "name":
                strategy = new NameSortStrategy();
                break;
            default:
                return products;
        }

        return strategy.sort(products);
    }
}