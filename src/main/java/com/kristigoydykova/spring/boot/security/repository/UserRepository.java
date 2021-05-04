package com.kristigoydykova.spring.boot.security.repository;

import com.kristigoydykova.spring.boot.security.entities.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {
    List<User> getAllUsers();

    void saveUser(User user);

    void updateUser(User user);

    User getUserById(long id);

    void deleteUser(long id);

    User findUserByName(String username);
}
