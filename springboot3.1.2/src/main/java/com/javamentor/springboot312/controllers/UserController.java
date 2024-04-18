package com.javamentor.springboot312.controllers;

import com.javamentor.springboot312.model.User;
import com.javamentor.springboot312.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String findAll(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("listUser", userService.findAll());
        return "users";
    }

    @GetMapping("/user/add")
    public String createUserForm(@ModelAttribute("user") User user) {
        return "create-user";
    }
    @PostMapping
    public String addUser(@ModelAttribute("user") @Valid User user,
                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "create-user";
        }

        userService.saveUser(user);
        return "redirect:/";
    }

    @PostMapping("/remove")
    public String removeUser(@RequestParam("id") Long id) {
        this.userService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String showUpdateForm(@RequestParam("id") Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "update-user";
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute("user") @Valid User user,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "update-user";
        }
        userService.saveUser(user);
        return "redirect:/";
    }
}
