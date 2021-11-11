package by.naumenko.jm.spring_boot_crud.service.impl;

import by.naumenko.jm.spring_boot_crud.model.User;
import by.naumenko.jm.spring_boot_crud.repo.UserRepository;
import by.naumenko.jm.spring_boot_crud.service.RoleService;
import by.naumenko.jm.spring_boot_crud.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException(String.format("User %s not found", username)));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void removeUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User saveUser(User user) throws PersistenceException {
        String oldPassword = "";
        if (user.getId() != null && getUserById(user.getId()) != null)
            oldPassword = getUserById(user.getId()).getPassword();
        if (user.getPassword().equals(user.getConfirmPassword())) {
            if (user.getPassword() != "") {
                user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            } else
                user.setPassword(oldPassword);
            roleService.instRole(user);
            return userRepository.save(user);
        } else
            return null;
    }

    @Override
    public User savePostConstruct(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        roleService.instRole(user);
        return userRepository.save(user);
    }
}
