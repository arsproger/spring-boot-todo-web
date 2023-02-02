package com.arsen.controllers;

import com.arsen.models.User;
import com.arsen.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    UserService service;

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/all")
    public List<User> getAllUser() {
        return service.getAllUser();
    }

    @PostMapping("/save")
    public Long saveUser(@RequestParam String name,
                         @RequestParam String email,
                         @RequestParam String password) {
        return service.newUser(name, email, password);
    }

    @DeleteMapping("/delete")
    public String deleteUserById(@PathVariable Long id) {
        return service.deleteUserById(id);
    }

    @PatchMapping("/update")
    public String updateUserById(Long id, String name) {
        return service.updateUserById(id, name);
    }
}
