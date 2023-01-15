package ru.gb.lesson7_springdata_hw.repository;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.lesson7_springdata_hw.dto.ProductDto;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CartRepository {
    private final List<ProductDto> cart = new ArrayList<>();

    public void addProductToTheCart (ProductDto product) {
        cart.add(product);
    }

    public boolean deleteProductFromTheCart (ProductDto product) {
        for (ProductDto prod :
             cart) {
            if(prod.getId().equals(product.getId())) {
               return cart.remove(prod);
            }
        }
        return false;
    }

    public List<ProductDto> getCart () {
        return cart;
    }

}
