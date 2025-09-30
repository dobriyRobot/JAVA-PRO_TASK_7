package org.semennikov.dto.request;

import java.math.BigDecimal;

public class PaymentRequestDto {

    private String paymentNumber;

    private BigDecimal amount;

    private String description;

    public PaymentRequestDto() {}

    public PaymentRequestDto(String paymentNumber, BigDecimal amount, String description) {
        this.paymentNumber = paymentNumber;
        this.amount = amount;
        this.description = description;
    }

    public String getPaymentNumber() { return paymentNumber; }
    public void setPaymentNumber(String paymentNumber) { this.paymentNumber = paymentNumber; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
