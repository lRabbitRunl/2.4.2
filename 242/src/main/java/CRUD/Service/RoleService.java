package CRUD.Service;

import CRUD.model.Role;
import CRUD.model.User;

import java.util.Set;

public interface RoleService {

    void setRole(Role role);
    Set<Role> getAllRoles();
    Role getRoleByName(String name);
    void updateUser(User user);
}

