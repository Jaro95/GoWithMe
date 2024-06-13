package pl.coderslab.controller;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.model.User;
import pl.coderslab.repository.UserRepository;

import java.util.Locale;

@Controller
@RequestMapping("/gowithme/home")
public class HomeController {

    private final UserRepository userRepository;

    public HomeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("")
    public String hello() {
        Faker faker = new Faker(new Locale("pl"));
        return "hey";
    }

    @GetMapping("/alluser")
    public String allUser(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "/home/userList";
    }


    @GetMapping("/registration")
    public String getAddUser(Model model) {
        model.addAttribute("user", new User());
        return "/home/registration";
    }

    @PostMapping("/registration")
    public String postAddUser(Model model,User user) {
        userRepository.save(user);
        return "redirect:/gowithme/home/alluser";
    }

    @GetMapping("/delete")
    public String postAddUser(@RequestParam long id) {
        userRepository.delete(userRepository.findById(id).get());
        return "redirect:/gowithme/home/alluser";
    }

    @GetMapping("/update")
    public String getUpdateUser(Model model,@RequestParam long id) {
        model.addAttribute("user", userRepository.findById(id).get());
        return "/home/update";
    }

    @PostMapping("/update")
    public String postUpdateUser(Model model,User user) {
        userRepository.save(user);
        return "redirect:/gowithme/home/alluser";
    }


}
