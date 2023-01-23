package ru.gb.lesson7_springdata_hw.dto;

import ru.gb.lesson7_springdata_hw.entities.Product;

import java.math.BigDecimal;

public class ProductDTO {

    private Long id;
    private String title;
    private BigDecimal price;

    public ProductDTO() {
    }

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
