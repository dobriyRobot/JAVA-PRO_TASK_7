package org.semennikov.dto.response;

import java.util.List;

public class UserProductsResponseDto {

    private Long userId;

    private String username;

    private List<ProductResponseDto> products;

    public UserProductsResponseDto() {}

    public UserProductsResponseDto(Long userId, String username, List<ProductResponseDto> products) {
        this.userId = userId;
        this.username = username;
        this.products = products;
    }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public List<ProductResponseDto> getProducts() { return products; }
    public void setProducts(List<ProductResponseDto> products) { this.products = products; }
}
