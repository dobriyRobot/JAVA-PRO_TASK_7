package org.semennikov.mapper;

import org.semennikov.domain.Product;
import org.semennikov.dto.response.ProductResponseDto;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductResponseDto toResponseDto(Product product) {
        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(product.getId());
        dto.setPaymentNumber(product.getPaymentNumber());
        dto.setType(product.getType());
        dto.setBalance(product.getBalance());
        if (product.getUser() != null) {
            dto.setUserId(product.getUser().getId());
        }
        return dto;
    }
}
