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
import pl.coderslab.dto.WaitingOnAccessToActivityDTO;
import pl.coderslab.model.ActivitiesPlan;
import pl.coderslab.model.UserDetails;
import pl.coderslab.model.WaitOnAccessToActivity;
import pl.coderslab.repository.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        activitiesPlan.setUsersJoined(new ArrayList<>());
        System.out.println(activitiesPlan.toString());
        activitiesPlanRepository.save(activitiesPlan);
        redirect.addFlashAttribute("message", "Dodano Aktywność");
        return "redirect:/gowithme/app/activity/add";
    }

//    @GetMapping("/activity/edit")
//    public String getEditActivity(@RequestParam long id, Model model) {
//        model.addAttribute("activitiesPLan", activitiesPlanRepository.findById(id));
//        model.addAttribute("categories", categoryRepository.findAll());
//        return "application/activityEdit";
//    }
    @GetMapping("/activity/edit")
    public String getEditActivity(@RequestParam long id, Model model) {
        model.addAttribute("activitiesPLan", activitiesPlanRepository.findById(id).get());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("userList", activitiesPlanRepository.findById(id).get().getUsersJoined());
        model.addAttribute("activityId", id);
        return "application/activityEditNew";
    }

    @PostMapping("/activity/edit")
    public String postEditActivity(@Valid ActivitiesPlan activitiesPlan, BindingResult bindingResult
            , RedirectAttributes redirectAttributes, @AuthenticationPrincipal CurrentUser currentUser, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("activitiesPlan", activitiesPlan);
            model.addAttribute("categories", categoryRepository.findAll());
            return "application/activityEdit";
        }
        activitiesPlan.setUser(userDetailsRepository.findByUserId(currentUser.getUser().getId()));
        activitiesPlanRepository.save(activitiesPlan);
        redirectAttributes.addFlashAttribute("messageActivity", "Aktywność zaktualizowana");
        return "redirect:/gowithme/app/profile";
    }

    @GetMapping("/activity/deleteUserFromJoinedList")
    public String getDeleteUserFromJoinedList(@RequestParam long activityId, @RequestParam long userId, RedirectAttributes redirectAttributes) {
        ActivitiesPlan activitiesPlan = activitiesPlanRepository.findById(activityId).get();
        activitiesPlan.setUsersJoined(activitiesPlan.getUsersJoined()
                .stream().filter(el -> !el.equals(userDetailsRepository.findById(userId).get())).collect(Collectors.toList()));
        activitiesPlanRepository.save(activitiesPlan);
        redirectAttributes.addFlashAttribute("messageDelete", "Usunięto użytkownika z aktywności");
        return "redirect:/gowithme/app/activity/edit?id=" + activityId;
    }

    @GetMapping("/activity/delete")
    public String getDeleteActivity(@RequestParam long id, RedirectAttributes redirectAttributes) {
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
        model.addAttribute("activities", activitiesPlanRepository.findById(activityId).stream().toList());
        model.addAttribute("waitOnAccessToActivityDTO", new WaitingOnAccessToActivityDTO(activityId, id));
        return "application/detailsUserActivity";
    }

    @PostMapping("/activity/details")
    public String postDetailsUserActivity(WaitingOnAccessToActivityDTO waitingOnAccessToActivityDTO, RedirectAttributes redirectAttributes, @AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser.getUser().getId().equals(activitiesPlanRepository.findById(waitingOnAccessToActivityDTO.activityPlanId()).get().getUser().getId())) {
            redirectAttributes.addFlashAttribute("messageError", "Nie możesz wysłać prośby o dołączenie do swojej aktywności");
            return "redirect:/gowithme/app/activity/details?id=" + waitingOnAccessToActivityDTO.userCreatedActivityId() + "&activityId=" + waitingOnAccessToActivityDTO.activityPlanId();
        }
        if (waitOnAccessToActivityRepository.allWaitingUsersInActivity(waitingOnAccessToActivityDTO.activityPlanId())
                .contains(userDetailsRepository.findByUserId(currentUser.getUser().getId()))) {
            redirectAttributes.addFlashAttribute("messageError", "Użytkownik otrzymał juz Twoją prośbę o dołączenie");
            return "redirect:/gowithme/app/activity/details?id=" + waitingOnAccessToActivityDTO.userCreatedActivityId() + "&activityId=" + waitingOnAccessToActivityDTO.activityPlanId();
        }
        WaitOnAccessToActivity waitOnAccessToActivity = WaitOnAccessToActivity.builder()
                .activityPlan(activitiesPlanRepository.findById(waitingOnAccessToActivityDTO.activityPlanId()).get())
                .userDetails(userDetailsRepository.findByUserId(currentUser.getUser().getId()))
                .requestDate(LocalDateTime.now()).build();

        waitOnAccessToActivityRepository.save(waitOnAccessToActivity);
        redirectAttributes.addFlashAttribute("messageAssign", "Wysłano prośbę o dołączenie");
        return "redirect:/gowithme/app/activity/details?id=" + waitingOnAccessToActivityDTO.userCreatedActivityId() + "&activityId=" + waitingOnAccessToActivityDTO.activityPlanId();
    }

    @GetMapping("/activity/assign")
    public String getAssign(@RequestParam long id, Model model) {
        model.addAttribute("activities", activitiesPlanRepository.findById(id).stream().toList());
        model.addAttribute("userList", waitOnAccessToActivityRepository.allWaitingUsersInActivity(id));
        model.addAttribute("activityId", id);
        return "application/activityAssignPeople";
    }

    @GetMapping("/activity/assignUser")
    public String getAssignUserToActivity(@RequestParam long activityId, @RequestParam long userId, RedirectAttributes redirectAttributes) {
        ActivitiesPlan activitiesPlan = activitiesPlanRepository.findById(activityId).get();
        List<UserDetails> userDetailsList = activitiesPlan.getUsersJoined();
        if(userDetailsList.contains(userDetailsRepository.findById(userId).get())) {
            redirectAttributes.addFlashAttribute("messageError", "Użytkownik jest już przypisany do aktywności");
            return "redirect:/gowithme/app/activity/assign?id=" + activityId;
        }
        userDetailsList.add(userDetailsRepository.findById(userId).get());
        activitiesPlan.setUsersJoined(userDetailsList);
        System.out.println(activitiesPlan.toString());
        activitiesPlanRepository.save(activitiesPlan);
        redirectAttributes.addFlashAttribute("messageSuccess", "Dodano użytkownika do aktywności");
        return "redirect:/gowithme/app/activity/assign?id=" + activityId;
    }

    @GetMapping("/activity/deleteUserFroWaitingList")
    public String getDeleteUserFromWaitingList(@RequestParam long activityId, @RequestParam long userId, RedirectAttributes redirectAttributes) {
        waitOnAccessToActivityRepository.deleteFromWaitingList(activityId, userId);
        redirectAttributes.addFlashAttribute("messageDelete", "Usunięto użytkownika z listy oczekujących");
        return "redirect:/gowithme/app/activity/assign?id=" + activityId;
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
    public String postUpdateUser(@Valid UserDetails userDetails, Model model, RedirectAttributes redirectAttributes, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("userDetails", userDetails);
            return "application/profileEdit";
        }
        redirectAttributes.addFlashAttribute("messageUpdate", "Konto zaktualizowane ");
        userDetailsRepository.save(userDetails);
        return "redirect:/gowithme/app/profile";
    }


}


