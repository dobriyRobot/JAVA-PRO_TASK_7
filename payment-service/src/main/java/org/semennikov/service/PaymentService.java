package org.semennikov.service;

import org.semennikov.dto.request.PaymentRequestDto;
import org.semennikov.dto.response.PaymentResponseDto;
import org.semennikov.dto.response.ProductResponseDto;
import org.semennikov.exception.InsufficientFundsException;
import org.semennikov.exception.PaymentProcessingException;
import org.semennikov.exception.ProductNotFoundException;
import org.semennikov.integration.ProductServiceClient;
import org.semennikov.mapper.PaymentMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PaymentService {

    private final ProductServiceClient productServiceClient;
    private final PaymentMapper paymentMapper;

    public PaymentService(ProductServiceClient productServiceClient, PaymentMapper paymentMapper) {
        this.productServiceClient = productServiceClient;
        this.paymentMapper = paymentMapper;
    }

    public PaymentResponseDto processPayment(PaymentRequestDto paymentRequest) {
        if (!productServiceClient.checkProductExists(paymentRequest.getPaymentNumber())) {
            throw new ProductNotFoundException(String.format("Payment product not found: %s", paymentRequest.getPaymentNumber()));
        }

        ProductResponseDto product = productServiceClient.getProduct(paymentRequest.getPaymentNumber());

        if (product.getBalance().compareTo(paymentRequest.getAmount()) < 0) {
            throw new InsufficientFundsException(
                    String.format("Insufficient funds. Available: %.2f, Required: %.2f",
                            product.getBalance(), paymentRequest.getAmount()));
        }

        try {
            BigDecimal refundAmount = paymentRequest.getAmount().negate();
            productServiceClient.updateProductBalance(paymentRequest.getPaymentNumber(), refundAmount);

            return paymentMapper.toPaymentResponse(paymentRequest, "COMPLETED");

        } catch (Exception ex) {
            throw new PaymentProcessingException(String.format("Payment processing failed: %s", ex.getMessage()));
        }
    }

    public List<ProductResponseDto> getUserProducts(Long userId) {
        return productServiceClient.getUserProducts(userId);
    }
}
