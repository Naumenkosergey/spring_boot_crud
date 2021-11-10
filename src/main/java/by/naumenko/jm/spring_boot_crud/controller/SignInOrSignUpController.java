package by.naumenko.jm.spring_boot_crud.controller;

import by.naumenko.jm.spring_boot_crud.model.User;
import by.naumenko.jm.spring_boot_crud.service.RoleService;
import by.naumenko.jm.spring_boot_crud.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class SignInOrSignUpController {
    private final UserService userService;
    private final RoleService roleService;

    @GetMapping("/signin")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/signup")
    public String registrationPage() {
        return "registration";
    }

    @PostMapping("/signup")
    public String addUser(@ModelAttribute("user") User user, BindingResult result, @RequestParam String authorities) {
        user.setAuthorities(roleService.addAuthorities(authorities));
        userService.saveUser(user);
        return "user";
    }
}