package pl.coderslab.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.Service.CustomUserDetails;
import pl.coderslab.Service.UserService;
import pl.coderslab.Service.UserServiceImpl;
import pl.coderslab.model.ActivitiesPlan;
import pl.coderslab.model.Category;
import pl.coderslab.model.UserDetails;
import pl.coderslab.repository.ActivitiesPlanRepository;
import pl.coderslab.repository.CategoryRepository;
import pl.coderslab.repository.UserDetailsRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/gowithme/app")
public class ApplicationController {

    private final UserDetailsRepository userDetailsRepository;
    private final ActivitiesPlanRepository activitiesPlanRepository;
    private final UserService userService;
    private final CategoryRepository categoryRepository;

    public ApplicationController(UserDetailsRepository userDetailsRepository, ActivitiesPlanRepository activitiesPlanRepository, UserService userService, CategoryRepository categoryRepository) {
        this.userDetailsRepository = userDetailsRepository;
        this.activitiesPlanRepository = activitiesPlanRepository;
        this.userService = userService;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("")
    public String main(Model model) {
        model.addAttribute("activities", activitiesPlanRepository.findAll());
        return "application/appMain";
    }

    @GetMapping("/add_activity")
    public String getAddActivity(Model model) {
        model.addAttribute("activitiesPLan", new ActivitiesPlan());
        model.addAttribute("categories", categoryRepository.findAll());
        return "application/addActivity";
    }

    @PostMapping("/add_activity")
    public String postAddActivity(@Valid ActivitiesPlan activitiesPlan,
                                  Model model, BindingResult result, RedirectAttributes redirect,
                                  @AuthenticationPrincipal CustomUserDetails customUser) {
        if (result.hasErrors()) {
            model.addAttribute("activitiesPLan", activitiesPlan);
            model.addAttribute("errors", result.getAllErrors());
            return "application/addActivity";
        }
        activitiesPlan.setUser(userDetailsRepository.findByUserId(customUser.getId()));
        activitiesPlan.setEnabled(true);
        System.out.println(activitiesPlan.toString());
        activitiesPlanRepository.save(activitiesPlan);
        redirect.addFlashAttribute("message", "Dodano Aktywność");
        return "redirect:/gowithme/app/add_activity";
    }

    @GetMapping("/profile")
    public String getProfile(Model model, @AuthenticationPrincipal CustomUserDetails customUser) {

        UserDetails userDetails = userDetailsRepository.findByUserId(customUser.getId());
        model.addAttribute("firstName", userDetails.getFirstName());
        model.addAttribute("lastName", userDetails.getLastName());
        model.addAttribute("city", userDetails.getCity());
        model.addAttribute("age", userDetails.getAge());
        model.addAttribute("description", userDetails.getDescription());
        model.addAttribute("activities", activitiesPlanRepository.findByUserId(userDetails.getId()));
        return "application/profile";
    }

    @GetMapping("/random")
    public String getRandom(Model model) {
        model.addAttribute("activities", activitiesPlanRepository.findAll());
        return "application/random";
    }

    @GetMapping("/profile/edit")
    public String getProfileEdit(Model model, @AuthenticationPrincipal CustomUserDetails customUser) {
        model.addAttribute("userDetails", userDetailsRepository.findByUserId(customUser.getId()));
        return "application/profileEdit";
    }

    @PostMapping("/profile/edit")
    public String postUpdateUser(@Valid UserDetails userDetails, Model model, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("userDetails", userDetails);
            return "application/profileEdit";
        }
        userDetailsRepository.save(userDetails);
        return "redirect:/gowithme/app/profile";
    }


}


