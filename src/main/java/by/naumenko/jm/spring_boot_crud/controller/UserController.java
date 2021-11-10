package by.naumenko.jm.spring_boot_crud.controller;

import by.naumenko.jm.spring_boot_crud.model.User;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/user")
    public String user(@CurrentSecurityContext(expression = "authentication.principal") User principal,
                       Model model) {
        model.addAttribute("user", principal);
        return "user";
    }
}
