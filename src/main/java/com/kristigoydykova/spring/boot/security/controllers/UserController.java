package com.kristigoydykova.spring.boot.security.controllers;


import com.kristigoydykova.spring.boot.security.entities.User;
import com.kristigoydykova.spring.boot.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;


@Controller
@RequestMapping("")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public String forUser(Principal principal, Model model){
        User usernameUs = userService.findUserByName(principal.getName());
        model.addAttribute("user", usernameUs);
        return "user";
    }
}