package org.semennikov.controller;

import org.semennikov.dto.request.PaymentRequestDto;
import org.semennikov.dto.response.PaymentResponseDto;
import org.semennikov.dto.response.ProductResponseDto;
import org.semennikov.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/process")
    public ResponseEntity<PaymentResponseDto> processPayment(@RequestBody PaymentRequestDto paymentRequest) {

        PaymentResponseDto response = paymentService.processPayment(paymentRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}/products")
    public ResponseEntity<List<ProductResponseDto>> getUserProducts(@PathVariable Long userId) {
        List<ProductResponseDto> response = paymentService.getUserProducts(userId);
        return ResponseEntity.ok(response);
    }
}
