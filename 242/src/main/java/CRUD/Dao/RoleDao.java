package CRUD.Dao;

import CRUD.model.Role;

import java.util.Set;

public interface RoleDao {
    void setRole(Role role);
    Set<Role> getAllRoles();
    Role getRoleByName(String name);
}
