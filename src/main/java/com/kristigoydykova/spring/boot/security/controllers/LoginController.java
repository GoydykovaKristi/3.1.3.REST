package com.kristigoydykova.spring.boot.security.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class LoginController {

    @GetMapping(value = "/login")
    public String getLoginPage() {
        return "login";
    }
}