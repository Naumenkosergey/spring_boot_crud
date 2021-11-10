package by.naumenko.jm.spring_boot_crud.service.impl;

import by.naumenko.jm.spring_boot_crud.model.Role;
import by.naumenko.jm.spring_boot_crud.model.User;
import by.naumenko.jm.spring_boot_crud.repo.RoleRepository;
import by.naumenko.jm.spring_boot_crud.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
@AllArgsConstructor
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role saveRole(Role role) {
       return roleRepository.save(role);
    }

    private Role findRoleByAuthority(String authority) {
        return findAll().stream()
                .filter(role -> authority.equals(role.getAuthority()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(String.format("Role %s not found", authority)));
    }

    @Override
    public void instRole(User user) {
        for (Role role : user.getAuthorities()) {
            try {
                role.setId(findRoleByAuthority(role.getAuthority()).getId());
            } catch (NoSuchElementException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public Set<Role> addAuthorities(String... authorities) {
        Set<Role> roles = new HashSet<>();
        for (String authority : authorities) {
            Role role = new Role();
            role.setAuthority(authority);
            roles.add(role);
        }
        return roles;
    }
}
