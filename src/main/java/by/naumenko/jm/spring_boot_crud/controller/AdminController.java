package by.naumenko.jm.spring_boot_crud.controller;

import by.naumenko.jm.spring_boot_crud.model.User;
import by.naumenko.jm.spring_boot_crud.service.RoleService;
import by.naumenko.jm.spring_boot_crud.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;


    @GetMapping("")
    public String userList(@CurrentSecurityContext(expression = "authentication.principal") User principal,
                           Model model) {
        model.addAttribute("principal", principal);
        model.addAttribute("listUser", userService.findAll());
        return "admin";
    }

    @GetMapping("/new")
    public String addUser(User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.findAll());
        return "add-user";
    }

    @PostMapping("/new")
    public String CreateOrUpdateUser(@ModelAttribute("user") User user, BindingResult bindingResult,
                                     @RequestParam String[] authorities) {
        user.setAuthorities(roleService.addAuthorities(authorities));
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/remove")
    public String removeUser(@PathVariable("id") Long id) {
        userService.removeUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String editUserById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roleService.findAll());
        return "add-user";
    }

    @GetMapping("user/{id}/info")
    public String getUserById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user";
    }
}