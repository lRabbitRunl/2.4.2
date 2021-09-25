package CRUD.Service;

import CRUD.model.Role;

import java.util.Set;

public interface RoleService {

    void setRole(Role role);
    Set<Role> getAllRoles();
    Role getRoleByName(String name);
}

