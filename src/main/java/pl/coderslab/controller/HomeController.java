package pl.coderslab.controller;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.model.UserDetails;
import pl.coderslab.repository.UserDetailsRepository;

import java.util.Locale;

@Controller
@RequestMapping("/gowithme/home")
public class HomeController {

    private final UserDetailsRepository userDetailsRepository;

    public HomeController(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    @GetMapping("")
    public String hello() {
        Faker faker = new Faker(new Locale("pl"));
        return "hey";
    }

    @GetMapping("/alluser")
    public String allUser(Model model) {
        model.addAttribute("users", userDetailsRepository.findAll());
        return "/home/userList";
    }


    @GetMapping("/registration")
    public String getAddUser(Model model) {
        model.addAttribute("user", new UserDetails());
        return "/home/registration";
    }

    @PostMapping("/registration")
    public String postAddUser(Model model, UserDetails userDetails) {
        userDetailsRepository.save(userDetails);
        return "redirect:/gowithme/home/alluser";
    }

    @GetMapping("/delete")
    public String postAddUser(@RequestParam long id) {
        userDetailsRepository.delete(userDetailsRepository.findById(id).get());
        return "redirect:/gowithme/home/alluser";
    }

    @GetMapping("/update")
    public String getUpdateUser(Model model,@RequestParam long id) {
        model.addAttribute("user", userDetailsRepository.findById(id).get());
        return "/home/update";
    }

    @PostMapping("/update")
    public String postUpdateUser(Model model, UserDetails userDetails) {
        userDetailsRepository.save(userDetails);
        return "redirect:/gowithme/home/alluser";
    }


}
