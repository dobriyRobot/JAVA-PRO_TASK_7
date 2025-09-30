package org.semennikov.dto.request;

import java.math.BigDecimal;

public class BalanceUpdateRequestDto {

    private BigDecimal amount;

    public BalanceUpdateRequestDto() {}

    public BalanceUpdateRequestDto(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
}
