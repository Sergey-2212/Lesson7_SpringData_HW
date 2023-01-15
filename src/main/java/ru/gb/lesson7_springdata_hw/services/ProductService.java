package ru.gb.lesson7_springdata_hw.services;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.gb.lesson7_springdata_hw.converters.EntityConverter;
import ru.gb.lesson7_springdata_hw.dto.ProductDto;
import ru.gb.lesson7_springdata_hw.entities.Product;
import ru.gb.lesson7_springdata_hw.repository.ProductRepository;
import ru.gb.lesson7_springdata_hw.repository.specifications.ProductSpecifications;
import ru.gb.lesson7_springdata_hw.validators.ProductValidator;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;


    public ProductService(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }

    public Page<Product> findAll(Integer minPrice, Integer maxPrice, Integer pageNumber, Integer pageSize,  String titleLike, String sortProp) {
        //Lesson9 00:51
        Specification<Product> spec = Specification.where(null);
        //Создаю объект Sort для определения порядка сортировки.
        Sort sort = Sort.by(Sort.Direction.ASC, sortProp);

        if(minPrice != null) {
            spec = spec.and(ProductSpecifications.priceGreaterOrEqualTo(minPrice));
        }

        if(maxPrice != null) {
            spec = spec.and(ProductSpecifications.priceLessOrEqualTo(maxPrice));
        }

        if(titleLike != null) {
            spec = spec.and(ProductSpecifications.titleLike(titleLike));
        }

        return productRepository.findAll(spec, PageRequest.of(pageNumber, pageSize, sort));
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

//    public List<Product> getAllProducts() {
//
////        return productRepository.findAll();
//        return productRepository.getAllSortedProducts();
//    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    public Product addProduct(Product product) {
        product.setId(null);
        return productRepository.save(product);
    }

    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

//    public List<Product> getAllProductsBetweenPrice(BigDecimal min, BigDecimal max) {
//        return productRepository.findAllByPriceBetween(min, max);
//    }
    @Transactional
    public void changePrice(Long id, Integer delta) {
        Product product = productRepository.findById(id).get();
        product.setPrice(product.getPrice().add(new BigDecimal(delta)));
    }
}
