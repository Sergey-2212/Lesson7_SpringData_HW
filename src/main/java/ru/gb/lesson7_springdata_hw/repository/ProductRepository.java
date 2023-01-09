package ru.gb.lesson7_springdata_hw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.gb.lesson7_springdata_hw.data.Product;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p from Product p WHERE p.price > ?1 order by p.id")
    List<Product> getAllProductsBiggerPrice(BigDecimal min);

    @Query("SELECT p from Product p WHERE p.price < ?1 order by p.id")
    List<Product> getAllProductsLessPrice(BigDecimal max);

    List<Product> findAllByPriceBetween(BigDecimal min, BigDecimal max);

}
