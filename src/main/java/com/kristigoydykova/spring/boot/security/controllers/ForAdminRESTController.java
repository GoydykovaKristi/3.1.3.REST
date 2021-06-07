package com.kristigoydykova.spring.boot.security.controllers;


import com.kristigoydykova.spring.boot.security.entities.Role;
import com.kristigoydykova.spring.boot.security.entities.User;
import com.kristigoydykova.spring.boot.security.services.RoleService;
import com.kristigoydykova.spring.boot.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.security.Principal;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/admin")
public class ForAdminRESTController {

    private final UserService userService;
    private final RoleService roleService;


    @Autowired
    public ForAdminRESTController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;

    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> showAllUsers(){
        final List<User> allUsers =  userService.getAllUsers();

        return allUsers != null && !allUsers.isEmpty()
                ? ResponseEntity.ok(allUsers)
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/infoUsername")
    public ResponseEntity<User> getUser(Principal principal){
        User userByUsername = userService.findByUsername(principal.getName());

        return ResponseEntity.ok(userByUsername);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Optional<User>> userById(@PathVariable("id") Long id){
        Optional<User> userById = userService.getUserById(id);

        return ResponseEntity.ok(userById);
    }

    @PostMapping("/users")
    public ResponseEntity<User> addNewUser(@RequestBody User user){
        userService.saveOrUpdate(user);

        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/users")
    public ResponseEntity<?> update(@RequestBody User user) {
        userService.saveOrUpdate(user);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        userService.deleteUser(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> showAllRoles(){
        final List<Role> allRoles =  roleService.getAllRoles();

        return allRoles != null && !allRoles.isEmpty()
                ? ResponseEntity.ok(allRoles)
                : ResponseEntity.notFound().build();
    }



}
