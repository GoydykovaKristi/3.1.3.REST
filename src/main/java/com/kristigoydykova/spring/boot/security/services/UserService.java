package com.kristigoydykova.spring.boot.security.services;


import com.kristigoydykova.spring.boot.security.entities.Role;
import com.kristigoydykova.spring.boot.security.entities.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void saveUser(User user);

    User getUserById(Long id);

    void updateUser(User user, String[] role);

    Role showRole(Long id);

    void deleteUser(Long id);

    User findUserByName(String username3);
}
