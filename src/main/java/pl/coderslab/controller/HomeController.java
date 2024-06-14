package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.Service.UserService;
import pl.coderslab.model.Contact;
import pl.coderslab.model.User;
import pl.coderslab.model.UserDetails;
import pl.coderslab.repository.ContactRepository;
import pl.coderslab.repository.UserDetailsRepository;

@Controller
@RequestMapping("/gowithme")
public class HomeController {

    private final UserDetailsRepository userDetailsRepository;
    private final ContactRepository contactRepository;
    private final UserService userService;

    public HomeController(UserDetailsRepository userDetailsRepository, ContactRepository contactRepository, UserService userService) {
        this.userDetailsRepository = userDetailsRepository;
        this.contactRepository = contactRepository;
        this.userService = userService;
    }


    @GetMapping("/home")
    public String main(Model model) {
        return "/home/main";
    }

    @GetMapping("/login")
    public String login() {
        return "/home/login";
    }
    @GetMapping("/contact")
    public String login(Model model) {
        Contact contact = contactRepository.findAll().get(0);
        model.addAttribute("address", contact.getAddress());
        model.addAttribute("phone",contact.getPhoneNumber());
        model.addAttribute("email", contact.getEmail());
        return "/home/contact";
    }

    @GetMapping("/alluser")
    public String allUser(Model model) {
        model.addAttribute("userDetails", userDetailsRepository.findAll());
        return "application/userList";
    }


    @GetMapping("/registration")
    public String getAddUser(Model model) {
        model.addAttribute("userDetails", new UserDetails());
        return "home/registration";
    }

    @PostMapping("/registration")
    public String postAddUser(UserDetails userDetails, User user) {
        userDetailsRepository.save(userDetails);
        user.setUserDetails(userDetails);
        userService.saveUser(user);
        return "redirect:/gowithme/home/alluser";
    }

    @GetMapping("/delete")
    public String postAddUser(@RequestParam long id) {
        userDetailsRepository.delete(userDetailsRepository.findById(id).get());
        return "redirect:/gowithme/home/alluser";
    }

    @GetMapping("/update")
    public String getUpdateUser(Model model,@RequestParam long id) {
        model.addAttribute("userDetails", userDetailsRepository.findById(id).get());
        return "application/update";
    }

    @PostMapping("/update")
    public String postUpdateUser(UserDetails userDetails) {
        userDetailsRepository.save(userDetails);
        return "redirect:/gowithme/home/alluser";
    }

}
