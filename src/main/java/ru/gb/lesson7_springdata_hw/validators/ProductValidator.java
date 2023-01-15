package ru.gb.lesson7_springdata_hw.validators;

import org.springframework.stereotype.Component;
import ru.gb.lesson7_springdata_hw.dto.ProductDto;
import ru.gb.lesson7_springdata_hw.exceptions.ValidationException;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductValidator {
    private final List<String> errors = new ArrayList<>();

    public void validateProductDto(ProductDto productDTO) {
        if (productDTO.getPrice().doubleValue() <= 0) {
            errors.add("Стоимость не может быть <= 0. :" + productDTO.getPrice());
        }
        if (productDTO.getTitle().isBlank()) {
            errors.add("Наименование товара не может быть пустым :" + productDTO.getTitle());
        }
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }

}
