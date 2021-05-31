package com.kristigoydykova.spring.boot.security.repository;

import com.kristigoydykova.spring.boot.security.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<User,Long> {
// вместо EAGER
    @Query("from User u  inner JOIN FETCH u.roles as roles WHERE u.email = :email")
    User findByEmail(@Param("email") String email);

    @Query("from User u  inner JOIN FETCH u.roles as roles WHERE u.username = :username")
    User findByUsername(@Param("username")String username);
}
