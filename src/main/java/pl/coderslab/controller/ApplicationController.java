package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.Service.UserService;
import pl.coderslab.Service.UserServiceImpl;
import pl.coderslab.model.UserDetails;
import pl.coderslab.repository.ActivitiesPlanRepository;
import pl.coderslab.repository.UserDetailsRepository;

@Controller
@RequestMapping("/gowithme/app/")
public class ApplicationController {

    private final UserDetailsRepository userDetailsRepository;
    private final ActivitiesPlanRepository activitiesPlanRepository;
    private final UserService userService;

    public ApplicationController(UserDetailsRepository userDetailsRepository, ActivitiesPlanRepository activitiesPlanRepository, UserService userService) {
        this.userDetailsRepository = userDetailsRepository;
        this.activitiesPlanRepository = activitiesPlanRepository;
        this.userService = userService;
    }

    @GetMapping("/main")
    public String main(Model model) {
        model.addAttribute("activities",activitiesPlanRepository.findAll());
        return "application/appMain";
    }

    @GetMapping("/add_activity")
    public String getAddActivity(Model model) {
        model.addAttribute("activities",activitiesPlanRepository.findAll());
        return "application/addActivity";
    }

    @GetMapping("/profile")
    public String getProfile(Model model) {
        UserDetails userDetails = userDetailsRepository.findByUserId(2);
        model.addAttribute("firstName", userDetails.getFirstName());
        model.addAttribute("lastName",userDetails.getLastName());
        model.addAttribute("city",userDetails.getCity());
        model.addAttribute("age",userDetails.getAge());
        model.addAttribute("description",userDetails.getDescription());
        return "application/profile";
    }

    @GetMapping("/random")
    public String getRandom(Model model) {
        model.addAttribute("activities",activitiesPlanRepository.findAll());
        return "application/random";
    }

    @GetMapping("/profile/edit")
    public String getProfileEdit(Model model) {
       // model.addAttribute("userDetails", userDetailsRepository.findById(id).get());
        return "application/profileEdit";
    }

    @PostMapping("/profile/edit")
    public String postUpdateUser(UserDetails userDetails) {
        userDetailsRepository.save(userDetails);
        return "redirect:/gowithme/app/profile";
    }
}


