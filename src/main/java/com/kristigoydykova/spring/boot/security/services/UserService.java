package com.kristigoydykova.spring.boot.security.services;


import com.kristigoydykova.spring.boot.security.entities.Role;
import com.kristigoydykova.spring.boot.security.entities.User;
import java.util.List;
import java.util.Optional;


public interface UserService {

    List<User> getAllUsers();

    void saveOrUpdate(User user);

    void deleteUser(Long id);

    Optional<User> getUserById(Long id);

    User findByEmail(String email);

    User findByUsername(String username);

    List <Role> getRoleList();
}
