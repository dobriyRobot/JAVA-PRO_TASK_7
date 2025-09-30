package org.semennikov.integration;

import org.semennikov.dto.request.BalanceUpdateRequestDto;
import org.semennikov.dto.response.ProductResponseDto;
import org.semennikov.exception.InsufficientFundsException;
import org.semennikov.exception.ProductNotFoundException;
import org.semennikov.exception.ProductServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.util.List;

@Component
public class ProductServiceClient {

    private final RestClient productRestClient;

    private static final String GET_PRODUCTS_BY_USER_ID_URL = "/api/v1/users/{id}/products";
    private static final String GET_PRODUCT_BY_PAYMENT_NUMBER_URL = "/api/v1/products/{paymentNumber}";
    private static final String GET_BALANCE_BY_PAYMENT_NUMBER_URL = "/api/v1/products/{paymentNumber}/balance";
    private static final String CHECK_PAYMENT_NUMBER_URL = "/api/v1/products/{paymentNumber}/exists";

    @Autowired
    public ProductServiceClient(RestClient productRestClient) {
        this.productRestClient = productRestClient;
    }

    public List<ProductResponseDto> getUserProducts(Long userId) {
        try {
            return productRestClient.get()
                    .uri(GET_PRODUCTS_BY_USER_ID_URL, userId)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {});

        } catch (ProductNotFoundException ex) {
            throw new ProductNotFoundException(String.format("User not found with id: %d", userId));
        } catch (Exception ex) {
            throw new ProductServiceException(String.format("Error calling product service: %s", ex.getMessage()));
        }
    }

    public ProductResponseDto getProduct(String paymentNumber) {
        try {
            return productRestClient.get()
                    .uri(GET_PRODUCT_BY_PAYMENT_NUMBER_URL, paymentNumber)
                    .retrieve()
                    .body(ProductResponseDto.class);

        } catch (ProductNotFoundException ex) {
            throw new ProductNotFoundException(String.format("Product not found: %s", paymentNumber));
        } catch (Exception ex) {
            throw new ProductServiceException(String.format("Error calling product service: %s", ex.getMessage()));
        }
    }

    public void updateProductBalance(String paymentNumber, BigDecimal amount) {
        try {
            BalanceUpdateRequestDto request = new BalanceUpdateRequestDto(amount);

            productRestClient.post()
                    .uri(GET_BALANCE_BY_PAYMENT_NUMBER_URL, paymentNumber)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(request)
                    .retrieve()
                    .toBodilessEntity();

        } catch (InsufficientFundsException ex) {
            throw new InsufficientFundsException(String.format("Insufficient funds for payment on product: %s", paymentNumber));
        } catch (Exception ex) {
            throw new ProductServiceException(String.format("Error updating product balance: %s", ex.getMessage()));
        }
    }

    public boolean checkProductExists(String paymentNumber) {
        try {
            Boolean exists = productRestClient.get()
                    .uri(CHECK_PAYMENT_NUMBER_URL, paymentNumber)
                    .retrieve()
                    .body(Boolean.class);

            return Boolean.TRUE.equals(exists);
        } catch (Exception ex) {
            throw new ProductServiceException(String.format("Error checking product existence: %s", ex.getMessage()));
        }
    }
}
