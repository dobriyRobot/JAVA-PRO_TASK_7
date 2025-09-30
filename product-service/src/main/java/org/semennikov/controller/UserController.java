package org.semennikov.controller;

import org.semennikov.domain.User;
import org.semennikov.dto.response.ProductResponseDto;
import org.semennikov.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/v1/users/{id}")
    public User getUserById(@PathVariable(name = "id") Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/api/v1/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/api/v1/users/{id}/products")
    public List<ProductResponseDto> getAllProductsByUser(@PathVariable(name = "id") Long id) {
        return userService.getAllProductsByUserId(id);
    }

    @PostMapping("/api/v1/users")
    public User createUser(@RequestParam(name = "username") String username) {
        return userService.createUser(username);
    }

    @PutMapping("/api/v1/users/{id}")
    public User updateUser(@PathVariable(name = "id") Long id, @RequestParam(name = "username") String username) {
        return userService.updateUser(id, username);
    }

    @DeleteMapping("/api/v1/users/{id}")
    public void deleteUser(@PathVariable(name = "id") Long id) {
        userService.deleteUser(id);
    }
}
