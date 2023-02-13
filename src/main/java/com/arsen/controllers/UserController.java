package com.arsen.controllers;

import com.arsen.models.User;
import com.arsen.services.UserService;
import com.arsen.util.UserValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("user")
public class UserController {
    private final UserService service;
    private final UserValidator validator;

    @Autowired
    public UserController(UserService service, UserValidator validator) {
        this.service = service;
        this.validator = validator;
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
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        validator.validate(user, bindingResult);

        if(bindingResult.hasErrors())
            return "user-new";

        service.newUser(user);
        return "redirect:/user";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id) {
        service.deleteUserById(id);
        return "redirect:/user";
    }


}
