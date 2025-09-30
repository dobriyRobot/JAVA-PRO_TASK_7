package org.semennikov.mapper;

import org.semennikov.dto.request.PaymentRequestDto;
import org.semennikov.dto.response.PaymentResponseDto;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PaymentMapper {

    public PaymentResponseDto toPaymentResponse(PaymentRequestDto request, String status) {
        PaymentResponseDto response = new PaymentResponseDto();
        response.setPaymentId(generatePaymentId());
        response.setStatus(status);
        response.setPaymentNumber(request.getPaymentNumber());
        response.setAmount(request.getAmount());
        response.setDescription(request.getDescription());
        return response;
    }

    private String generatePaymentId() {
        return "PAY_" + System.currentTimeMillis() + "_" + UUID.randomUUID().toString().substring(0, 8);
    }
}
