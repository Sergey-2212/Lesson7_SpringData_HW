package ru.gb.lesson7_springdata_hw.controllers;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.gb.lesson7_springdata_hw.dto.ProductDTO;
import ru.gb.lesson7_springdata_hw.entities.Product;
import ru.gb.lesson7_springdata_hw.exceptions.NotFoundException;
import ru.gb.lesson7_springdata_hw.services.ProductService;

import java.math.BigDecimal;
import java.rmi.StubNotFoundException;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
         this.productService = productService;
    }

    @GetMapping("/{id}")
    public ProductDTO getProductById (@PathVariable Long id) {
        return productService.getProductById(id).map(s -> new ProductDTO(s)).orElseThrow(() -> new NotFoundException("Unable to find id: " + id));
    }

//    @GetMapping("/products")
//     public List<Product> getAllProducts() {
//        return productService.getAllProducts();
//    }

    @GetMapping
    public Page<ProductDTO> getAllProductsBetween(@RequestParam(defaultValue = "0") Integer min,
                                                  @RequestParam(required = false) Integer max,
                                                  @RequestParam(required = false) Integer pageNumber,
                                                  @RequestParam(required = false) Integer pageSize,
                                                  @RequestParam(required = false) String titleLike,
                                                  @RequestParam(required = false) String sortProp) {
        if(max == null) { max = Integer.MAX_VALUE;}

        if(sortProp == null) { sortProp = "id";}

        if(pageNumber == null || pageNumber < 1) { pageNumber = 1;}

        if(pageSize == null) {pageSize = 10;}

        return productService.findAll(
                min, max, pageNumber - 1,pageSize, titleLike,sortProp)
                .map( s -> new ProductDTO(s));
    }

//    @GetMapping("/products/between")
//    public List<Product> getAllProductsMinMax(@RequestParam BigDecimal min, @RequestParam BigDecimal max) {
//        return productService.getAllProductsBetweenPrice(min, max);
//    }
    @DeleteMapping("/")
    public void deleteProductById(@RequestParam Long id) {
        productService.deleteProductById(id);
    }
//    @GetMapping("/products/add")
//    public Product addProduct(@RequestParam String title, @RequestParam BigDecimal price) {
//            return productService.addProduct(new Product(title, price));
//    }

    @PostMapping("/")
    public Product addProductJSON (@RequestBody Product product) {
        product.setId(null);
        return productService.addProduct(product);
    }

    @PutMapping("/change_price/")
    public void changeScore(@RequestParam Long studentId, @RequestParam Integer delta) {
        productService.changePrice(studentId, delta);
    }
}
