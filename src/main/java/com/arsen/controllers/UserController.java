package com.arsen.controllers;

import com.arsen.models.User;
import com.arsen.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("user")
public class UserController {
    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }


    @GetMapping("/{id}")
    public String getUserById(@PathVariable Long id, Model model) {
        model.addAttribute("user", service.getById(id));
        return "show";
    }

    @GetMapping()
    public String getAllUser(Model model) {
        model.addAttribute("users", service.getAllUser());
        return "index";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("user") User user) {
        return "user-new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        service.newUser(user);
        return "redirect:/user";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id) {
        service.deleteUserById(id);
        return "redirect:/user";
    }


}
