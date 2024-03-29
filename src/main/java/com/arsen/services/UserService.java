package com.arsen.services;

import com.arsen.models.User;
import com.arsen.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void newUser(User user) {
        ;
        userRepository.save(user);
    }

    public User identification(String email, String password) {
        List<User> userList = userRepository.findAll();
        for (User user : userList) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password))
                return user;
        }
        return null;
    }

    public User getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public Optional<User> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public String updateUserById(Long id, String name) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) return null;
        user.setName(name);
        userRepository.save(user);
        return "User id: " + id + " new name " + name;
    }
}
