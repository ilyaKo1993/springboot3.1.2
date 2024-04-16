package com.javamentor.springboot312.controllers;

import com.javamentor.springboot312.model.User;
import com.javamentor.springboot312.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

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

    @PostMapping(value = "/user/add")
    public String addUser(@ModelAttribute("user") User user) {
        this.userService.saveUser(user);
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
    public String editUser(@ModelAttribute("user") @Valid User user) {
        userService.saveUser(user);
        return "redirect:/";
    }
}
