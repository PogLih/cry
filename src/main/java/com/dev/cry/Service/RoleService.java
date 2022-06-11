package com.dev.cry.Service;

import com.dev.cry.Entity.Role;

public interface RoleService {
    Role findRoleByName(String roleName);

    Iterable<Role> findAll();

    void save(Role role);
}
