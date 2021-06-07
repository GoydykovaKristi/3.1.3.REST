package com.kristigoydykova.spring.boot.security.controllers;


import com.kristigoydykova.spring.boot.security.entities.User;
import com.kristigoydykova.spring.boot.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@RestController
@RequestMapping("/user")
public class UserRESTController {

    private final UserService userService;

    @Autowired
    public UserRESTController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/infoUsername")
    public ResponseEntity<User> getCurrentUser(Principal principal) {
        return ResponseEntity.ok(userService.findByUsername(principal.getName()));
    }

}
