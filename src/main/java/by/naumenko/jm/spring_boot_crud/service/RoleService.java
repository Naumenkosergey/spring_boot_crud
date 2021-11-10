package by.naumenko.jm.spring_boot_crud.service;

import by.naumenko.jm.spring_boot_crud.model.Role;
import by.naumenko.jm.spring_boot_crud.model.User;

import java.util.List;
import java.util.Set;

public interface RoleService {
    List<Role> findAll();

    void instRole(User user);

    Role saveRole(Role role);

    Set<Role> addAuthorities(String... authorities);
}
