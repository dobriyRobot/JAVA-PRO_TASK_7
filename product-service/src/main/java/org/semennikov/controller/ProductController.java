package org.semennikov.controller;

import org.semennikov.dto.request.BalanceUpdateRequestDto;
import org.semennikov.dto.response.ProductResponseDto;
import org.semennikov.dto.response.UserProductsResponseDto;
import org.semennikov.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserProductsResponseDto> getUserProducts(@PathVariable Long userId) {
        List<ProductResponseDto> products = productService.getUserProducts(userId);
        UserProductsResponseDto response = new UserProductsResponseDto();
        response.setUserId(userId);
        response.setProducts(products);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{paymentNumber}")
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable String paymentNumber) {
        ProductResponseDto product = productService.getProductByPaymentNumber(paymentNumber);
        return ResponseEntity.ok(product);
    }

    @PostMapping("/{paymentNumber}/balance")
    public ResponseEntity<Void> updateBalance(@PathVariable String paymentNumber,
                                              @RequestBody BalanceUpdateRequestDto request) {
        productService.updateProductBalance(paymentNumber, request.getAmount());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{paymentNumber}/exists")
    public ResponseEntity<Boolean> checkProductExists(@PathVariable String paymentNumber) {
        boolean exists = productService.checkProductExists(paymentNumber);
        return ResponseEntity.ok(exists);
    }
}
