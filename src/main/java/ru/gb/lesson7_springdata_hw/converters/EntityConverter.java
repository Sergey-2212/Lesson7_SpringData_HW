package ru.gb.lesson7_springdata_hw.converters;

import org.springframework.stereotype.Component;
import ru.gb.lesson7_springdata_hw.dto.ProductDto;
import ru.gb.lesson7_springdata_hw.entities.Product;

@Component
public class EntityConverter {

    public Product dtoToEntityConverter (ProductDto productDto) {
        return new Product(productDto.getId(), productDto.getTitle(), productDto.getPrice());
    }

    public ProductDto entityToDto (Product product) {
        return new ProductDto(product);
    }
}
