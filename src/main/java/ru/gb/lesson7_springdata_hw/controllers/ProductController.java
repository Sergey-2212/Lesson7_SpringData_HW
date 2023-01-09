package ru.gb.lesson7_springdata_hw.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.lesson7_springdata_hw.data.Product;
import ru.gb.lesson7_springdata_hw.exceptions.AppError;
import ru.gb.lesson7_springdata_hw.exceptions.NotFoundException;
import ru.gb.lesson7_springdata_hw.services.ProductService;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/products/{id}")
    public Product getProductById (@PathVariable Long id) {
        return productService.getProductById(id).orElseThrow(() -> new NotFoundException("Unable to find id: " + id));
    }
    @GetMapping("/products")
     public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/more/{min}")
     public List<Product> getAllProductsBiggerPrice(@PathVariable BigDecimal min) {
        return productService.getAllProductsBiggerPrice(min);
    }

    @GetMapping("/products/less/{max}")
    public List<Product> getAllProductsLessPrice(@PathVariable BigDecimal max) {
        return productService.getAllProductsLessPrice(max);
    }

    @GetMapping("/products/between")
    public List<Product> getAllProductsLessPrice(@RequestParam BigDecimal min, @RequestParam BigDecimal max) {
        return productService.getAllProductsBetweenPrice(min, max);
    }



    @GetMapping("/products/delete/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }

    @GetMapping("/products/add")
    public Product addProduct(@RequestParam String title, @RequestParam BigDecimal price) {
            return productService.addProduct(new Product(title, price));
    }


}
