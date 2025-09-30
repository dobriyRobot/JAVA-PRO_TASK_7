package org.semennikov.config;

import org.semennikov.exception.InsufficientFundsException;
import org.semennikov.exception.ProductNotFoundException;
import org.semennikov.exception.ProductServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean
    public RestClient productRestClient(@Value("${integration.product-service-url}") String productServiceUrl) {
        return RestClient.builder()
                .baseUrl(productServiceUrl)
                .defaultStatusHandler(
                        HttpStatusCode::is4xxClientError,
                        (request, response) -> {
                            if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                                throw new ProductNotFoundException(String.format("Resource not found: %s", request.getURI()));
                            } else if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
                                throw new InsufficientFundsException("Insufficient funds or bad request");
                            } else {
                                throw new ProductServiceException(String.format("Client error: %d", response.getStatusCode().value()));
                            }
                        })
                .defaultStatusHandler(
                        HttpStatusCode::is5xxServerError,
                        (request, response) -> {
                            throw new ProductServiceException(String.format("Product service error: %d", response.getStatusCode().value()));
                        })
                .build();
    }
}
