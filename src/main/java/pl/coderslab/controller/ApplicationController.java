package pl.coderslab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.Service.CurrentUser;
import pl.coderslab.Service.UserService;
import pl.coderslab.model.ActivitiesPlan;
import pl.coderslab.model.UserDetails;
import pl.coderslab.model.WaitOnAccessToActivity;
import pl.coderslab.repository.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/gowithme/app")
public class ApplicationController {

    private final UserDetailsRepository userDetailsRepository;
    private final ActivitiesPlanRepository activitiesPlanRepository;
    private final UserService userService;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final WaitOnAccessToActivityRepository waitOnAccessToActivityRepository;


    @GetMapping("")
    public String main(Model model) {
        model.addAttribute("activities", activitiesPlanRepository.findAll());
        return "application/appMain";
    }

    @GetMapping("/activity/add")
    public String getAddActivity(Model model) {
        model.addAttribute("activitiesPLan", new ActivitiesPlan());
        model.addAttribute("categories", categoryRepository.findAll());
        return "application/activityAdd";
    }

    @PostMapping("/activity/add")
    public String postAddActivity(@Valid ActivitiesPlan activitiesPlan,
                                  Model model, BindingResult result, RedirectAttributes redirect,
                                  @AuthenticationPrincipal CurrentUser currentUser) {
        if (result.hasErrors()) {
            model.addAttribute("activitiesPLan", activitiesPlan);
            model.addAttribute("errors", result.getAllErrors());
            return "application/activityAdd";
        }
        activitiesPlan.setUser(userDetailsRepository.findByUserId(currentUser.getUser().getId()));
        activitiesPlan.setEnabled(true);
        System.out.println(activitiesPlan.toString());
        activitiesPlanRepository.save(activitiesPlan);
        redirect.addFlashAttribute("message", "Dodano Aktywność");
        return "redirect:/gowithme/app/acitivity/add";
    }

    @GetMapping("/activity/edit")
    public String getEditActivity(@RequestParam long id ,Model model) {
        model.addAttribute("activitiesPLan", activitiesPlanRepository.findById(id));
        model.addAttribute("categories", categoryRepository.findAll());
        return "application/activityEdit";
    }

    @PostMapping("/activity/edit")
    public String postEditActivity(@Valid ActivitiesPlan activitiesPlan, BindingResult bindingResult
            , RedirectAttributes redirectAttributes, @AuthenticationPrincipal CurrentUser currentUser, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("errors",bindingResult.getAllErrors());
            model.addAttribute("activitiesPlan", activitiesPlan);
            model.addAttribute("categories", categoryRepository.findAll());
            return "application/activityEdit";
        }
        activitiesPlan.setUser(userDetailsRepository.findByUserId(currentUser.getUser().getId()));
        activitiesPlanRepository.save(activitiesPlan);
        redirectAttributes.addFlashAttribute("messageActivity", "Aktywność zaktualizowana");
        return "redirect:/gowithme/app/profile";
    }

    @GetMapping("/activity/delete")
    public String getDeleteActivity(@RequestParam long id,RedirectAttributes redirectAttributes) {
       activitiesPlanRepository.deleteById(id);
       redirectAttributes.addFlashAttribute("messageActivity", "Aktywność usunięta");
        return "redirect:/gowithme/app/profile";
    }

    @GetMapping("/activity/details")
    public String getDetailsUserActivity(Model model, @RequestParam long id, @RequestParam long activityId, @AuthenticationPrincipal CurrentUser currentUser) {

        UserDetails userDetails = userDetailsRepository.findByUserId(id);
        System.out.println(userDetails.toString());
        model.addAttribute("firstName", userDetails.getFirstName());
        model.addAttribute("lastName", userDetails.getLastName());
        model.addAttribute("city", userDetails.getCity());
        model.addAttribute("age", userDetails.getAge());
        model.addAttribute("description", userDetails.getDescription());
        ActivitiesPlan activityPlan = activitiesPlanRepository.findById(activityId).get();
        model.addAttribute("activities", activitiesPlanRepository.findById(activityId).stream().toList());
        model.addAttribute("waitOnAccessToActivity", WaitOnAccessToActivity.builder()
                .activityPlan(activityPlan)
                .userDetails(userDetailsRepository.findByUserId(currentUser.getUser().getId()))
                .build());
        model.addAttribute("userId",id);
        return "application/detailsUserActivity";
    }

    /**
*dodać sprawdzanie czy już jest w bazie i czeka na potwierdzenie
     */
    @PostMapping("/activity/details")
    public String postDetailsUserActivity(WaitOnAccessToActivity waitOnAccessToActivity, RedirectAttributes redirectAttributes, @AuthenticationPrincipal CurrentUser currentUser) {
        if(currentUser.getUser().getId().equals(waitOnAccessToActivity.getActivityPlan().getUser().getId())) {
            redirectAttributes.addFlashAttribute("messageError", "Nie możesz wysłać prośby o dołączenie do swojej aktywności");
            return "redirect:/gowithme/app/activity/details?id=" + waitOnAccessToActivity.getActivityPlan().getUser().getId()+ "&activityId=" + waitOnAccessToActivity.getActivityPlan().getId();
        }
        if (waitOnAccessToActivityRepository.validateContainInList(waitOnAccessToActivity.getActivityPlan(),waitOnAccessToActivity.getUserDetails()) != null) {
            redirectAttributes.addFlashAttribute("messageError", "Użytkownik otrzymał juz Twoją prośbę o dołączenie");
            return "redirect:/gowithme/app/activity/details?id=" + waitOnAccessToActivity.getActivityPlan().getUser().getId()+ "&activityId=" + waitOnAccessToActivity.getActivityPlan().getId();
        }
        waitOnAccessToActivity.setRequestDate(LocalDateTime.now());
        waitOnAccessToActivityRepository.save(waitOnAccessToActivity);
        redirectAttributes.addFlashAttribute("messageAssign", "Wysłano prośbę o dołączenie");
        return "redirect:/gowithme/app/activity/details?id=" + waitOnAccessToActivity.getActivityPlan().getUser().getId()+ "&activityId=" + waitOnAccessToActivity.getActivityPlan().getId();
    }

    @GetMapping("/activity/assign")
    public String getAssignActivity(Model model, @RequestParam long id/*,@RequestParam(required = false) long userId*/) {
        model.addAttribute("activities", activitiesPlanRepository.findById(id).stream().toList());
        List<UserDetails> userDetailsList = waitOnAccessToActivityRepository.allWaitingUsersInActivity(id);
        System.out.println("TESTY");
        System.out.println(userDetailsList.toString());
        model.addAttribute("userList",userDetailsList);
        UserDetails userDetails = userDetailsRepository.findById(id).get();
        model.addAttribute("activities", activitiesPlanRepository.findById(userDetails.getId()));
        return "application/assignToActivity";
    }

    @GetMapping("/profile")
    public String getProfile(Model model, @AuthenticationPrincipal CurrentUser currentUser) {

        UserDetails userDetails = userDetailsRepository.findByUserId(currentUser.getUser().getId());
        model.addAttribute("firstName", userDetails.getFirstName());
        model.addAttribute("lastName", userDetails.getLastName());
        model.addAttribute("city", userDetails.getCity());
        model.addAttribute("age", userDetails.getAge());
        model.addAttribute("description", userDetails.getDescription());
        model.addAttribute("activities", activitiesPlanRepository.findByUserId(currentUser.getUser().getId()));
        return "application/profile";
    }

    @GetMapping("/random")
    public String getRandom(Model model) {
        model.addAttribute("activities", activitiesPlanRepository.findAll());
        return "application/random";
    }

    @GetMapping("/profile/edit")
    public String getProfileEdit(Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        model.addAttribute("userDetails", userDetailsRepository.findByUserId(currentUser.getUser().getId()));
        return "application/profileEdit";
    }

    @PostMapping("/profile/edit")
    public String postUpdateUser(@Valid UserDetails userDetails, Model model,RedirectAttributes redirectAttributes, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("userDetails", userDetails);
            return "application/profileEdit";
        }
        redirectAttributes.addFlashAttribute("messageUpdate", "Konto zaktualizowane ");
        userDetailsRepository.save(userDetails);
        return "redirect:/gowithme/app/profile";
    }


}


