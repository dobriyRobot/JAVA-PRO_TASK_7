package org.semennikov.dto.response;


import org.semennikov.dto.common.ProductType;

import java.math.BigDecimal;

public class ProductResponseDto {

    private Long id;
    private String paymentNumber;
    private ProductType type;
    private BigDecimal balance;
    private Long userId;

    public ProductResponseDto() {}

    public ProductResponseDto(Long id, String paymentNumber, ProductType type, BigDecimal balance, Long userId) {
        this.id = id;
        this.paymentNumber = paymentNumber;
        this.type = type;
        this.balance = balance;
        this.userId = userId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getPaymentNumber() { return paymentNumber; }
    public void setPaymentNumber(String paymentNumber) { this.paymentNumber = paymentNumber; }
    public ProductType getType() { return type; }
    public void setType(ProductType type) { this.type = type; }
    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}
