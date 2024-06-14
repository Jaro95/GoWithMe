package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.Service.UserService;
import pl.coderslab.model.User;
import pl.coderslab.repository.UserDetailsRepository;
import pl.coderslab.repository.UserRepository;

@Controller
@RequestMapping("/gowithme/admin")
public class AdminController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final UserDetailsRepository userDetailsRepository;

    public AdminController(UserService userService, UserRepository userRepository, UserDetailsRepository userDetailsRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.userDetailsRepository = userDetailsRepository;
    }

    @GetMapping("/create-admin?email")
    public String createUser(@RequestParam String email) {
        User user = new User();
        user.setEmail(email);
        user.setPassword("admin");
        userService.saveAdmin(user);
        return "redirect:/gowithme/admin";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("")
    public String allUser(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "admin/adminPanel";
    }

        @GetMapping("/delete")
    public String postAddUser(@RequestParam long id) {
        userRepository.delete(userRepository.findById(id).get());
        return "redirect:/gowithme/home/alluser";
    }

}
