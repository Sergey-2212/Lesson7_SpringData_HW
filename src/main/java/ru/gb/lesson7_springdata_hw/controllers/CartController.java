package ru.gb.lesson7_springdata_hw.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.gb.lesson7_springdata_hw.dto.ProductDto;
import ru.gb.lesson7_springdata_hw.exceptions.NotFoundException;
import ru.gb.lesson7_springdata_hw.services.CartService;
import ru.gb.lesson7_springdata_hw.validators.ProductValidator;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final ProductValidator validator;

    @GetMapping("/")
    public List<ProductDto> getCart() {
        return cartService.getCart();
    }

    @PostMapping("/")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void addProduct (@RequestBody ProductDto productDto) {
        validator.validateProductDto(productDto);
        cartService.addProductToTheCart(productDto);
    }
    @DeleteMapping("/")
    @ResponseStatus(code = HttpStatus.FOUND)
    public void deleteProduct (@RequestBody ProductDto productDto) {
        if (!cartService.deleteProductFromTheCart(productDto)) {
            throw new NotFoundException(String.format("Продукт " + productDto.getTitle() + " не найдет в корзине."));
        }
    }
}
