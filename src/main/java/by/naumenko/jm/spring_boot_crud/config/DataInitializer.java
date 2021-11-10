package by.naumenko.jm.spring_boot_crud.config;

import by.naumenko.jm.spring_boot_crud.model.Role;
import by.naumenko.jm.spring_boot_crud.model.User;
import by.naumenko.jm.spring_boot_crud.repo.UserRepository;
import by.naumenko.jm.spring_boot_crud.service.RoleService;
import by.naumenko.jm.spring_boot_crud.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@AllArgsConstructor
public class DataInitializer {
    private final UserService userServise;
    private final RoleService roleService;

    @PostConstruct
    private void initData() {
        roleService.saveRole(new Role("ADMIN"));
        roleService.saveRole(new Role("USER"));

        User user = User.builder()
                .username("naymchik")
                .name("Sergey")
                .surname("Naumenko")
                .email("naumenkosergeyns@gmail.com")
                .password("12345")
                .authorities(roleService.addAuthorities("ADMIN", "USER"))
                .build();
        user.setConfirmPassword(user.getPassword());


        userServise.saveUser(user);

        User user2 = User.builder()
                .username("test")
                .name("Test")
                .surname("test")
                .email("test@yandex.ru")
                .password("test")
                .authorities(roleService.addAuthorities("USER"))
                .build();
        user2.setConfirmPassword(user2.getPassword());

        userServise.saveUser(user2);
    }
}
