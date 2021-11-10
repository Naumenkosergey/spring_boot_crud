package by.naumenko.jm.spring_boot_crud.service;

import by.naumenko.jm.spring_boot_crud.model.Role;
import by.naumenko.jm.spring_boot_crud.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {
    @Override
    UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;

    List<User> findAll();

    User getUserById(Long id);

    void removeUser(Long id);

    User saveUser(User user);
}
