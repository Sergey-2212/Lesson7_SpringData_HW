package ru.gb.lesson7_springdata_hw.services;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.lesson7_springdata_hw.dto.ProductDto;
import ru.gb.lesson7_springdata_hw.repository.CartRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    public void addProductToTheCart(ProductDto productDto) {
        cartRepository.addProductToTheCart(productDto);
    }

    public boolean deleteProductFromTheCart(ProductDto productDto) {
        return cartRepository.deleteProductFromTheCart(productDto);
    }

    public List<ProductDto> getCart() {
        return cartRepository.getCart();
    }
}
