package com.kristigoydykova.spring.boot.security.controllers;

import com.kristigoydykova.spring.boot.security.entities.User;
import com.kristigoydykova.spring.boot.security.services.RoleService;
import com.kristigoydykova.spring.boot.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.security.Principal;

@Controller
public class BasicController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public BasicController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String userPage(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("allRoles", roleService.getAllRoles());
        model.addAttribute("user", user);
        return user.getRoles().stream()
                .anyMatch(role -> "ROLE_ADMIN".equalsIgnoreCase(role.getName()))? "adminpage" : "userpage";
    }

}
