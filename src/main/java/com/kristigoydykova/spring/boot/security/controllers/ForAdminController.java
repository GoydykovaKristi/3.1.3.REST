package com.kristigoydykova.spring.boot.security.controllers;


import com.kristigoydykova.spring.boot.security.entities.User;
import com.kristigoydykova.spring.boot.security.services.RoleService;
import com.kristigoydykova.spring.boot.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.security.Principal;


@Controller
@RequestMapping("/admin")
public class ForAdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public ForAdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("")
    public String showAllUsers(Model model){

        model.addAttribute("user", new User());

        model.addAttribute("allUsers", userService.getAllUsers());

        model.addAttribute("allRoles", roleService.getAllRoles());

        return "/admin_page";
    }

    @GetMapping("/{id}")
    public String forUser(Principal principal, Model model){
        User username = userService.findByUsername(principal.getName());
        model.addAttribute("userByUsername", username);
        return "user";
    }

    @PostMapping("/adduser")
    public String addUser(@Validated(User.class) @ModelAttribute("user") User user){
        userService.saveOrUpdate(user);
        return "redirect:/admin";
    }


    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return "redirect:/admin";
    }

}
