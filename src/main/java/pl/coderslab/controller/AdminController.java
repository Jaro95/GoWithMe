package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.Service.UserService;
import pl.coderslab.model.Contact;
import pl.coderslab.model.Role;
import pl.coderslab.model.User;
import pl.coderslab.model.UserDetails;
import pl.coderslab.repository.ContactRepository;
import pl.coderslab.repository.RoleRepository;
import pl.coderslab.repository.UserDetailsRepository;
import pl.coderslab.repository.UserRepository;

import java.util.List;

@Controller
@RequestMapping("/gowithme/admin")
public class AdminController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final UserDetailsRepository userDetailsRepository;
    private final RoleRepository roleRepository;
    private final ContactRepository contactRepository;

    public AdminController(UserService userService, UserRepository userRepository, UserDetailsRepository userDetailsRepository, RoleRepository roleRepository, ContactRepository contactRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.userDetailsRepository = userDetailsRepository;
        this.roleRepository = roleRepository;
        this.contactRepository = contactRepository;
    }

    @GetMapping("/create-contact")
    public String createUser() {
        Contact contact = Contact.builder().address("Konin, ul.bez ulicy")
                .email("gowithme@gmail.com")
                .phoneNumber(889992993).build();

        contactRepository.save(contact);
        return "redirect:/gowithme/admin";
    }

    @GetMapping("/create-start")
    public String createStart() {
        User god = User.builder().email("god@god")
                .password("qwerty").build();
        User admin = User.builder().email("admin@admin")
                .password("admin").build();
        Role adminRole = Role.builder().name("ROLE_GOD").build();
        Role godRole = Role.builder().name("ROLE_ADMIN").build();
        Role userRole = Role.builder().name("ROLE_USER").build();

        roleRepository.saveAll(List.of(godRole, adminRole, userRole));
        userService.saveGod(god);
        userService.saveAdmin(admin);
        UserDetails godDetails = UserDetails.builder()
                .firstName("Jarek")
                .lastName("Marciniak")
                .city("Konin")
                .user(userRepository.findByEmail(god.getEmail())).build();
        userDetailsRepository.save(godDetails);
        UserDetails adminDetails = UserDetails.builder()
                .firstName("Krzysztof")
                .lastName("Wysocki")
                .city("Poznań")
                .user(userRepository.findByEmail(admin.getEmail())).build();
        userDetailsRepository.save(adminDetails);
        return "redirect:/gowithme/admin";
    }

//    @GetMapping("/login")
//    public String login() {
//        return "login";
//    }

    @GetMapping("")
    public String allUser(Model model) {
        model.addAttribute("userDetails", userDetailsRepository.findAll());
        return "admin/adminPanel";
    }

    @GetMapping("/delete")
    public String postAddUser(@RequestParam long id) {
        userRepository.delete(userRepository.findById(id).get());
        return "redirect:/gowithme/home/alluser";
    }

}
