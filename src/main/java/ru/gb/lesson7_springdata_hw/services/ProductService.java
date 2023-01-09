package ru.gb.lesson7_springdata_hw.services;

import org.springframework.stereotype.Service;
import ru.gb.lesson7_springdata_hw.data.Product;
import ru.gb.lesson7_springdata_hw.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProductsBiggerPrice(BigDecimal min) {
        return productRepository.getAllProductsBiggerPrice(min);

    }

    public List<Product> getAllProductsLessPrice(BigDecimal max) {
        return productRepository.getAllProductsLessPrice(max);
    }

    public List<Product> getAllProductsBetweenPrice(BigDecimal min, BigDecimal max) {
        return productRepository.findAllByPriceBetween(min, max);
    }
}
