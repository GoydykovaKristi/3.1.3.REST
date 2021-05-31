package com.kristigoydykova.spring.boot.security.controllers;


import com.kristigoydykova.spring.boot.security.entities.User;
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
@RequestMapping(value = "/admin", produces = MediaType.APPLICATION_JSON_VALUE)
public class ForAdminRESTController {

    private final UserService userService;


    @Autowired
    public ForAdminRESTController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> showAllUsers(){
        final List<User> allUsers =  userService.getAllUsers();

        return allUsers != null && !allUsers.isEmpty()
                ? new ResponseEntity<>(allUsers, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/infoUser")
    public ResponseEntity<User> getUser(Principal principal){
        User userByUsername = userService.findByUsername(principal.getName());

        return new ResponseEntity<>(userByUsername, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Optional<User>> userById(@PathVariable("id") Long id){
        Optional<User> userById = userService.getUserById(id);

        return new ResponseEntity<>(userById, HttpStatus.OK);
    }

    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> addNewUser(@RequestBody User user){
        userService.saveOrUpdate(user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping(value = "/users/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody User user) {
        userService.saveOrUpdate(user);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        userService.deleteUser(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
