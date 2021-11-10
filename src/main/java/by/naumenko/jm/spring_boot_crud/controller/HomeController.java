package by.naumenko.jm.spring_boot_crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String getHome(Model model){
        model.addAttribute("title","HomePage");
        return "index";
    }
}
