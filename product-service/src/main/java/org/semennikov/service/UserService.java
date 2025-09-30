package org.semennikov.service;

import jakarta.transaction.Transactional;
import org.semennikov.domain.Product;
import org.semennikov.domain.User;
import org.semennikov.dto.response.ProductResponseDto;
import org.semennikov.exception.ProductNotFoundException;
import org.semennikov.mapper.ProductMapper;
import org.semennikov.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ProductMapper productMapper;

    @Autowired
    public UserService(UserRepository userRepository, ProductMapper productMapper) {
        this.userRepository = userRepository;
        this.productMapper = productMapper;
    }

    @Transactional
    public User createUser(String username) {
        User user = new User(username);
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(String.format("User with id: %d not found", id)));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<ProductResponseDto> getAllProductsByUserId(Long id) {
        List<Product> products = getUserById(id)
                .getProducts();

        return products.stream()
                .map(productMapper::toResponseDto)
                .toList();
    }

    @Transactional
    public User updateUser(Long id, String newUsername) {
        User user = getUserById(id);

        user.setUsername(newUsername);
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
