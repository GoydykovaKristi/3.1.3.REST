package com.kristigoydykova.spring.boot.security.repository;

import com.kristigoydykova.spring.boot.security.entities.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

}
