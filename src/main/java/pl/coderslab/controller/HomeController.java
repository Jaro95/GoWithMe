package pl.coderslab.controller;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.model.User;

import java.util.Locale;

@Controller
@RequestMapping("/gowithme/main")
public class HomeController {

    @GetMapping("")
    public String hello() {
        Faker faker = new Faker(new Locale("pl"));
        return "hey";
    }

    @GetMapping("/login")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "/home/login";
    }

    @GetMapping("/login2")
    public String addUser2(Model model) {
        model.addAttribute("user", new User());
        return "/home/login2";
    }
}
