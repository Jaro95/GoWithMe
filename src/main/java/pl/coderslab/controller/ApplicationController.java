package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.model.UserDetails;
import pl.coderslab.repository.ActivitiesPlanRepository;
import pl.coderslab.repository.UserDetailsRepository;

@Controller
@RequestMapping("/gowithme/app/")
public class ApplicationController {

    private final UserDetailsRepository userDetailsRepository;
    private final ActivitiesPlanRepository activitiesPlanRepository;

    public ApplicationController(UserDetailsRepository userDetailsRepository, ActivitiesPlanRepository activitiesPlanRepository) {
        this.userDetailsRepository = userDetailsRepository;
        this.activitiesPlanRepository = activitiesPlanRepository;
    }

    @GetMapping("/main")
    public String main(Model model) {
        model.addAttribute("activities",activitiesPlanRepository.findAll());
        return "application/appMain";
    }

    @GetMapping("/update")
    public String getUpdateUser(Model model, @RequestParam long id) {
        model.addAttribute("userDetails", userDetailsRepository.findById(id).get());
        return "application/update";
    }

    @PostMapping("/update")
    public String postUpdateUser(UserDetails userDetails) {
        userDetailsRepository.save(userDetails);
        return "redirect:/gowithme/home/alluser";
    }
}


