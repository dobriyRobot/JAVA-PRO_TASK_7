package org.semennikov.service;

import org.semennikov.domain.Product;
import org.semennikov.dto.response.ProductResponseDto;
import org.semennikov.exception.InsufficientFundsException;
import org.semennikov.exception.ProductNotFoundException;
import org.semennikov.mapper.ProductMapper;
import org.semennikov.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<ProductResponseDto> getUserProducts(Long userId) {
        List<Product> products = productRepository.findByUserId(userId);
        return products.stream()
                .map(productMapper::toResponseDto)
                .toList();
    }

    public ProductResponseDto getProductByPaymentNumber(String paymentNumber) {
        Product product = productRepository.findByPaymentNumber(paymentNumber)
                .orElseThrow(() -> new ProductNotFoundException(String.format("Product not found with payment number: %s", paymentNumber)));
        return productMapper.toResponseDto(product);
    }

    public void updateProductBalance(String paymentNumber, BigDecimal amount) {
        Product product = productRepository.findByPaymentNumber(paymentNumber)
                .orElseThrow(() -> new ProductNotFoundException(String.format("Product not found with payment number: %s", paymentNumber)));

        if (product.getBalance().add(amount).compareTo(BigDecimal.ZERO) < 0) {
            throw new InsufficientFundsException(String.format("Insufficient funds on product: %s", paymentNumber));
        }

        product.setBalance(product.getBalance().add(amount));
        productRepository.save(product);
    }

    public boolean checkProductExists(String paymentNumber) {
        return productRepository.existsByPaymentNumber(paymentNumber);
    }
}
