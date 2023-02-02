package com.arsen.services;

import com.arsen.models.User;
import com.arsen.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public long newTask(String name, String email, String password) {
        User user = new User(name, email, password);
        userRepository.save(user);
        return user.getId();
    }

    public User identification(String email, String password) {
        List<User> userList = userRepository.findAll();
        for(User user: userList) {
            if(user.getEmail().equals(email) && user.getPassword().equals(password))
                return user;
        }
        return null;
    }
}
