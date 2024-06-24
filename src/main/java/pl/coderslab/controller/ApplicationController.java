package pl.coderslab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.service.CurrentUser;
import pl.coderslab.service.NotificationService;
import pl.coderslab.service.UserServiceImpl;
import pl.coderslab.dto.WaitingOnAccessToActivityDTO;
import pl.coderslab.model.*;
import pl.coderslab.repository.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@SessionAttributes("notificationsList")
@RequestMapping("/gowithme/app")
public class ApplicationController {

    private final UserDetailsRepository userDetailsRepository;
    private final ActivitiesPlanRepository activitiesPlanRepository;
    private final CategoryRepository categoryRepository;
    private final WaitOnAccessToActivityRepository waitOnAccessToActivityRepository;
    private final NotificationService notificationService;
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final UserServiceImpl userServiceImpl;


    @ModelAttribute
    public void setNotificationList(@RequestParam Optional<Integer> page,
                                    @RequestParam Optional<Integer> size,
                                    @AuthenticationPrincipal CurrentUser currentUser, Model model) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(3);
        Page<Notification> notificationPage = notificationRepository.findAllByUserDetailsIdOrderByCreateDateTimeDesc
                (userDetailsRepository.findByUser(currentUser.getUser()).getId(), PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("notificationsList", notificationPage);
        // return notificationRepository.findAllByUserDetailsOrderByCreateDateTimeDesc(userDetailsRepository.findByUser(currentUser.getUser()));
    }

    @GetMapping("")
    public String main(Model model) {
        model.addAttribute("activities", activitiesPlanRepository.findAll());
        return "application/appMain";
    }

    @GetMapping("/activity/add")
    public String getAddActivity(Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        model.addAttribute("activitiesPlan", new ActivitiesPlan());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("activitiesPlanList", activitiesPlanRepository.findByCity(userDetailsRepository.findByUser(currentUser.getUser()).getCity())
                .stream().filter(el -> !el.getUser().equals(userDetailsRepository.findByUser(currentUser.getUser()))).collect(Collectors.toList()));
        return "application/activityAdd";
    }

    @PostMapping("/activity/add")
    public String postAddActivity(@Valid ActivitiesPlan activitiesPlan, BindingResult result,
                                  Model model, RedirectAttributes redirect,
                                  @AuthenticationPrincipal CurrentUser currentUser) {
        if (result.hasErrors()) {
            model.addAttribute("activitiesPlan", activitiesPlan);
            model.addAttribute("categories", categoryRepository.findAll());
            model.addAttribute("activitiesPlanList", activitiesPlanRepository.findByCity(userDetailsRepository.findByUser(currentUser.getUser()).getCity())
                    .stream().filter(el -> !el.getUser().equals(userDetailsRepository.findByUser(currentUser.getUser()))).collect(Collectors.toList()));
            model.addAttribute("errors", result.getAllErrors());
            return "application/activityAdd";
        }
        activitiesPlan.setUser(userDetailsRepository.findByUserId(currentUser.getUser().getId()));
        activitiesPlan.setEnabled(true);
        activitiesPlan.setUsersJoined(new ArrayList<>());
        activitiesPlanRepository.save(activitiesPlan);
        redirect.addFlashAttribute("message", "Dodano Aktywność");
        return "redirect:/gowithme/app/activity/add";
    }

    @GetMapping("/activity/edit")
    public String getEditActivity(@RequestParam long id, Model model) {
        model.addAttribute("activitiesPlan", activitiesPlanRepository.findById(id).get());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("userList", activitiesPlanRepository.findById(id).get().getUsersJoined());
        model.addAttribute("activityId", id);
        return "application/activityEdit";
    }

    @PostMapping("/activity/edit")
    public String postEditActivity(@Valid ActivitiesPlan activitiesPlan, BindingResult bindingResult
            , RedirectAttributes redirectAttributes, @AuthenticationPrincipal CurrentUser currentUser, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("activitiesPlan", activitiesPlan);
            model.addAttribute("categories", categoryRepository.findAll());
            model.addAttribute("userList", activitiesPlanRepository.findById(activitiesPlan.getId()).get().getUsersJoined());
            model.addAttribute("activityId", activitiesPlan.getId());
            return "application/activityEdit";
        }
        activitiesPlan.setUser(userDetailsRepository.findByUserId(currentUser.getUser().getId()));
        activitiesPlanRepository.save(activitiesPlan);
        redirectAttributes.addFlashAttribute("messageActivity", "Aktywność zaktualizowana");
        return "redirect:/gowithme/app/profile";
    }

    @GetMapping("/activity/deleteUserFromJoinedList")
    public String getDeleteUserFromJoinedList(@RequestParam long activityId, @RequestParam long userId,
                                              @AuthenticationPrincipal CurrentUser currentUser, RedirectAttributes redirectAttributes) {
        ActivitiesPlan activitiesPlan = activitiesPlanRepository.findById(activityId).get();
        activitiesPlan.setUsersJoined(activitiesPlan.getUsersJoined()
                .stream().filter(el -> !el.equals(userDetailsRepository.findById(userId).get())).collect(Collectors.toList()));
        activitiesPlanRepository.save(activitiesPlan);
        notificationService.addNotificationRejectActivity(notificationRepository, userDetailsRepository.findById(userId).get(), userDetailsRepository.findByUserId(currentUser.getUser().getId())
                , " usunął Cię z aktywności ", activitiesPlan.getCategory());
        redirectAttributes.addFlashAttribute("messageDelete", "Usunięto użytkownika z aktywności");
        return "redirect:/gowithme/app/activity/edit?id=" + activityId;
    }

    @GetMapping("/activity/delete")
    public String getDeleteActivity(@RequestParam long id, @RequestParam String url, RedirectAttributes redirectAttributes) {
        activitiesPlanRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("messageActivity", "Aktywność usunięta");
        return "redirect:" + url;
    }

    @GetMapping("/activity/details")
    public String getDetailsUserActivity(Model model, @RequestParam long id, @RequestParam long activityId, @AuthenticationPrincipal CurrentUser currentUser) {

        UserDetails userDetails = userDetailsRepository.findByUserId(id);
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
        ActivitiesPlan activitiesPlan = activitiesPlanRepository.findById(waitingOnAccessToActivityDTO.activityPlanId()).get();
        if (currentUser.getUser().getId().equals(activitiesPlan.getUser().getId())) {
            redirectAttributes.addFlashAttribute("messageError", "Nie możesz wysłać prośby o dołączenie do swojej aktywności");
            return "redirect:/gowithme/app/activity/details?id=" + waitingOnAccessToActivityDTO.userCreatedActivityId() + "&activityId=" + waitingOnAccessToActivityDTO.activityPlanId();
        }
        if (waitOnAccessToActivityRepository.allWaitingUsersInActivity(waitingOnAccessToActivityDTO.activityPlanId())
                .contains(userDetailsRepository.findByUserId(currentUser.getUser().getId()))) {
            redirectAttributes.addFlashAttribute("messageError", "Użytkownik otrzymał juz Twoją prośbę o dołączenie");
            return "redirect:/gowithme/app/activity/details?id=" + waitingOnAccessToActivityDTO.userCreatedActivityId() + "&activityId=" + waitingOnAccessToActivityDTO.activityPlanId();
        }
        WaitOnAccessToActivity waitOnAccessToActivity = WaitOnAccessToActivity.builder()
                .activityPlan(activitiesPlan)
                .userDetails(userDetailsRepository.findByUserId(currentUser.getUser().getId()))
                .requestDate(LocalDateTime.now()).build();

        waitOnAccessToActivityRepository.save(waitOnAccessToActivity);
        notificationService.addNotificationRejectActivity(notificationRepository, activitiesPlan.getUser(), userDetailsRepository.findByUserId(currentUser.getUser().getId()),
                " wysłał prośbę o dołączenie do ", activitiesPlan.getCategory());
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
        if (userDetailsList.contains(userDetailsRepository.findById(userId).get())) {
            redirectAttributes.addFlashAttribute("messageError", "Użytkownik jest już przypisany do aktywności");
            return "redirect:/gowithme/app/activity/assign?id=" + activityId;
        }
        userDetailsList.add(userDetailsRepository.findById(userId).get());
        activitiesPlan.setUsersJoined(userDetailsList);
        activitiesPlanRepository.save(activitiesPlan);
        notificationService.addNotificationRejectActivity(notificationRepository, userDetailsRepository.findById(userId).get(),
                activitiesPlan.getUser(), " dodał Cię do aktywności ", activitiesPlan.getCategory());
        redirectAttributes.addFlashAttribute("messageSuccess", "Dodano użytkownika do aktywności");
        return "redirect:/gowithme/app/activity/assign?id=" + activityId;
    }

    @GetMapping("/activity/deleteUserFroWaitingList")
    public String getDeleteUserFromWaitingList(@RequestParam long activityId, @RequestParam long userId,
                                               RedirectAttributes redirectAttributes) {
        waitOnAccessToActivityRepository.deleteFromWaitingList(activityId, userId);
        ActivitiesPlan activitiesPlan = activitiesPlanRepository.findById(activityId).get();
        notificationService.addNotificationRejectActivity(notificationRepository, userDetailsRepository.findById(userId).get(),
                activitiesPlan.getUser(), " usunął Cię z listy oczekujących dla aktywności ", activitiesPlan.getCategory());
        redirectAttributes.addFlashAttribute("messageDelete", "Usunięto użytkownika z listy oczekujących");
        return "redirect:/gowithme/app/activity/assign?id=" + activityId;
    }

    @GetMapping("/activities/{type}")
    public String getActivitiesUser(@PathVariable String type, Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        List<ActivitiesPlan> activitiesPlanList = new ArrayList<>();

        if (type.equals("user")) {
            activitiesPlanList = activitiesPlanRepository.findByUserId(currentUser.getUser().getId());
            model.addAttribute("user", "enabled");
        }

        if (type.equals("userAssigned")) {
            activitiesPlanList = activitiesPlanRepository.findByUsersJoinedId(currentUser.getUser().getId());
            model.addAttribute("userAssigned", "enabled");
        }
        if (type.equals("userWaitingList")) {
            List<ActivitiesPlan> activitiesUserJoinedPlan = activitiesPlanRepository.findByUsersJoinedId(currentUser.getUser().getId());
            activitiesPlanList = waitOnAccessToActivityRepository.findByUserDetailsId(currentUser.getUser().getId())
                    .stream()
                    .filter(el -> !activitiesUserJoinedPlan.contains(el))
                    .collect(Collectors.toList());
            model.addAttribute("userWaitingList", "enabled");
        }

        model.addAttribute("activities", activitiesPlanList);
        return "application/activitiesUser";
    }

    @GetMapping("/activity/deleteAssign")
    public String getDeleteFromAssignedPlan(@RequestParam long activityId, @AuthenticationPrincipal CurrentUser currentUser,
                                            RedirectAttributes redirectAttributes) {
        UserDetails userDetails = userDetailsRepository.findByUser(currentUser.getUser());
        ActivitiesPlan activitiesPlan = activitiesPlanRepository.findById(activityId).get();
        activitiesPlan.setUsersJoined(activitiesPlan.getUsersJoined()
                .stream()
                .filter(el -> !activitiesPlan.getUsersJoined().contains(userDetails))
                .collect(Collectors.toList()));
        activitiesPlanRepository.save(activitiesPlan);
        waitOnAccessToActivityRepository.deleteFromWaitingList(activitiesPlan.getId(), userDetails.getId());
        redirectAttributes.addFlashAttribute("messageActivity",
                "Wypisałeś się z aktywności " + activitiesPlan.getCategory().getName());
        notificationService.addNotificationRejectActivity(notificationRepository, activitiesPlan.getUser(),
                userDetails, " wypisał się z aktywności ", activitiesPlan.getCategory());
        return "redirect:/gowithme/app/activities/userAssigned";
    }

    @GetMapping("/activity/deleteRequest")
    public String getDeleteRequestFromWaitingList(@RequestParam long activityId, @AuthenticationPrincipal CurrentUser currentUser,
                                                  RedirectAttributes redirectAttributes) {
        UserDetails userDetails = userDetailsRepository.findByUser(currentUser.getUser());
        ActivitiesPlan activitiesPlan = activitiesPlanRepository.findById(activityId).get();
        waitOnAccessToActivityRepository.deleteFromWaitingList(activitiesPlanRepository.findById(activityId).get().getId(),
                userDetailsRepository.findByUser(currentUser.getUser()).getId());
        redirectAttributes.addFlashAttribute("messageActivity",
                "Usunąłeś prośbę o dołączenie do aktywności " + activitiesPlan.getCategory().getName());
        notificationService.addNotificationRejectActivity(notificationRepository, activitiesPlan.getUser(),
                userDetails, " usunął prośbę o dołączenie do aktywności ", activitiesPlan.getCategory());
        return "redirect:/gowithme/app/activities/userWaitingList";
    }

    @GetMapping("/profile")
    public String getProfile(Model model, @AuthenticationPrincipal CurrentUser currentUser) {

        UserDetails userDetails = userDetailsRepository.findByUserId(currentUser.getUser().getId());
        model.addAttribute("firstName", userDetails.getFirstName());
        model.addAttribute("lastName", userDetails.getLastName());
        model.addAttribute("city", userDetails.getCity());
        model.addAttribute("age", userDetails.getAge());
        model.addAttribute("description", userDetails.getDescription());
        model.addAttribute("login", currentUser.getUser().getEmail());
        model.addAttribute("activities", activitiesPlanRepository.findByUserId(currentUser.getUser().getId()));
        return "application/profile";
    }


    @GetMapping("/profile/edit/{type}")
    public String getProfileEditData(@PathVariable String type,
                                     Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        model.addAttribute(type.equals("email") ? "email" : "password", type);
        UserDetails userDetails = userDetailsRepository.findByUserId(currentUser.getUser().getId());
        model.addAttribute("firstName", userDetails.getFirstName());
        model.addAttribute("lastName", userDetails.getLastName());
        model.addAttribute("city", userDetails.getCity());
        model.addAttribute("age", userDetails.getAge());
        model.addAttribute("description", userDetails.getDescription());
        model.addAttribute("activities", activitiesPlanRepository.findByUserId(currentUser.getUser().getId()));
        return "application/profileEditLogin";
    }

    @PostMapping("/profile/edit/editLogin")
    public String postProfileEditLogin(@RequestParam String email,
                                       @AuthenticationPrincipal CurrentUser currentUser, RedirectAttributes redirectAttributes) {
        if (userRepository.findByEmail(email) != null) {
            redirectAttributes.addFlashAttribute("messageError", email + " jest zajęty");
            return "redirect:/gowithme/app/profile/edit/email";
        }
        if (email.length() > 50) {
            redirectAttributes.addFlashAttribute("messageError", "Email może zawierać maksymalnie 50 znaków");
            return "redirect:/gowithme/app/profile/edit/email";
        }
        userServiceImpl.changeEmail(email, currentUser.getUser());
        redirectAttributes.addFlashAttribute("messageUpdate", "Email został zmieniony");
        return "redirect:/gowithme/app/profile";
    }

    @PostMapping("/profile/edit/editPassword")
    public String postProfileEditPassword( @RequestParam String password,
                                          @RequestParam String repeatPassword,
                                          @AuthenticationPrincipal CurrentUser currentUser, RedirectAttributes redirectAttributes) {
        if (password.length() < 5) {
            redirectAttributes.addFlashAttribute("messageError", "Hasła musi zawierać minimum 5 znaków");
            return "redirect:/gowithme/app/profile/edit/password";
        }
        if (!password.equals(repeatPassword)) {
            redirectAttributes.addFlashAttribute("messageError", "Hasła nie są jednakowe");
            return "redirect:/gowithme/app/profile/edit/password";
        }

        userServiceImpl.changePassword(password, currentUser.getUser());
        redirectAttributes.addFlashAttribute("messageUpdate", "Hasło zostało zmienione");
        return "redirect:/gowithme/app/profile";
    }

    @GetMapping("/profile/edit")
    public String getProfileEdit(Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        model.addAttribute("userDetails", userDetailsRepository.findByUserId(currentUser.getUser().getId()));
        return "application/profileEdit";
    }

    @PostMapping("/profile/edit")
    public String postUpdateUser(@Valid UserDetails userDetails, BindingResult result, Model model,
                                 RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("userDetails", userDetails);
            model.addAttribute("errors", result.getAllErrors());
            return "application/profileEdit";
        }
        System.out.println(userDetails.toString());

        redirectAttributes.addFlashAttribute("messageUpdate", "Konto zaktualizowane ");
        userDetailsRepository.save(userDetails);
        return "redirect:/gowithme/app/profile";
    }

    @GetMapping("/chat")
    public String getChat(Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        model.addAttribute("userDetails", userDetailsRepository.findByUserId(currentUser.getUser().getId()));
        return "application/communicator";
    }

}
