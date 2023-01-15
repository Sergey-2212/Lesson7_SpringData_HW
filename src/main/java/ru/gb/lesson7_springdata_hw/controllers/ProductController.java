package ru.gb.lesson7_springdata_hw.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.gb.lesson7_springdata_hw.converters.EntityConverter;
import ru.gb.lesson7_springdata_hw.dto.ProductDto;
import ru.gb.lesson7_springdata_hw.entities.Product;
import ru.gb.lesson7_springdata_hw.exceptions.NotFoundException;
import ru.gb.lesson7_springdata_hw.services.ProductService;
import ru.gb.lesson7_springdata_hw.validators.ProductValidator;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor //объявив переменные final можно использовать эту аннотацию чтобы не писать конструктор.
public class ProductController {
    private final ProductService productService;
    private final ProductValidator validator;
    private final EntityConverter converter;


    @GetMapping("/{id}")
    public ProductDto getProductById (@PathVariable Long id) {
        return productService.getProductById(id).map(s -> new ProductDto(s)).orElseThrow(() -> new NotFoundException("Unable to find id: " + id));
    }

//    @GetMapping("/products")
//     public List<Product> getAllProducts() {
//        return productService.getAllProducts();
//    }

    @GetMapping
    public Page<ProductDto> getAllProductsBetween(@RequestParam(defaultValue = "0") Integer min,
                                                  @RequestParam(required = false) Integer max,
                                                  @RequestParam(required = false) Integer pageNumber,
                                                  @RequestParam(required = false) Integer pageSize,
                                                  @RequestParam(required = false) String titleLike,
                                                  @RequestParam(required = false) String sortProp) {
        if(max == null) { max = Integer.MAX_VALUE;}

        if(sortProp == null) { sortProp = "id";}

        if(pageNumber == null || pageNumber < 1) { pageNumber = 1;}

        if(pageSize == null) {pageSize = 50;}

        return productService.findAll(
                min, max, pageNumber - 1,pageSize, titleLike,sortProp)
                .map( s -> new ProductDto(s));
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
    @ResponseStatus(code = HttpStatus.CREATED)
    public ProductDto addProductJSON (@RequestBody ProductDto productDto) {
        validator.validateProductDto(productDto);
        Product product = converter.dtoToEntityConverter(productDto);
        return converter.entityToDto(productService.addProduct(product));
    }
    @PutMapping("/")
    @ResponseStatus(code = HttpStatus.CREATED) //Эта аннотация возращает ResponseEntity с кодом 201
    public ProductDto updateProduct (@RequestBody ProductDto productDto) {
        validator.validateProductDto(productDto);
        Product product = converter.dtoToEntityConverter(productDto);
           return  converter.entityToDto(productService.updateProduct(product));
    }

    @PutMapping("/change_price/")
    public void changeScore(@RequestParam Long studentId, @RequestParam Integer delta) {
        productService.changePrice(studentId, delta);
    }
}
