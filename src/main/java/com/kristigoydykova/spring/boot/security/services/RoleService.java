package com.kristigoydykova.spring.boot.security.services;



import com.kristigoydykova.spring.boot.security.entities.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
}
