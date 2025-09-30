package org.semennikov.dto.response;

import java.math.BigDecimal;

public class PaymentResponseDto {

    private String paymentId;

    private String status;

    private String paymentNumber;

    private BigDecimal amount;

    private String description;

    public PaymentResponseDto() {}

    public PaymentResponseDto(String paymentId, String status, String paymentNumber,
                              BigDecimal amount, String description) {
        this.paymentId = paymentId;
        this.status = status;
        this.paymentNumber = paymentNumber;
        this.amount = amount;
        this.description = description;
    }

    public String getPaymentId() { return paymentId; }
    public void setPaymentId(String paymentId) { this.paymentId = paymentId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getPaymentNumber() { return paymentNumber; }
    public void setPaymentNumber(String paymentNumber) { this.paymentNumber = paymentNumber; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
