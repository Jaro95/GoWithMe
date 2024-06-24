package pl.coderslab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.service.UserService;
import pl.coderslab.model.*;
import pl.coderslab.repository.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/gowithme/admin")
public class AdminController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final UserDetailsRepository userDetailsRepository;
    private final RoleRepository roleRepository;
    private final ContactRepository contactRepository;
    private final CategoryRepository categoryRepository;

    @GetMapping("/create-start")
    public String createStart() {
        if(!userRepository.findAll().isEmpty()){
            return "redirect:/gowithme/admin";
        }
        User god = User.builder().email("god@god")
                .password("qwerty").build();
        User admin = User.builder().email("admin@admin")
                .password("admin").build();
        Role adminRole = Role.builder().name("ROLE_SUPER_ADMIN").build();
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
        createContact();
        createCategories();
        return "redirect:/gowithme/admin";
    }



    @GetMapping("")
    public String allUser(Model model) {
        model.addAttribute("userDetails", userDetailsRepository.findAll());
        return "admin/adminPanel";
    }

    @GetMapping("/category")
    public String getCategory(Model model) {
        createCategories();
        return "admin/adminPanel";
    }

    @GetMapping("/contact")
    public String getContact(Model model) {

        return "admin/adminPanel";
    }

    @GetMapping("/delete")
    public String postAddUser(@RequestParam long id) {
        userRepository.delete(userRepository.findById(id).get());
        return "redirect:/gowithme/home/alluser";
    }

    public void createContact() {
        Contact contact = Contact.builder().address("Konin, ul.bez ulicy")
                .email("gowithme@gmail.com")
                .phoneNumber(889992993).build();

        contactRepository.save(contact);
    }

    public void createCategories() {
        List<String> category = Arrays.asList("Relaks","Bieganie","Gotowanie","Spacer"
                ,"Wycieczka","Pływanie","Jazda na rowerze","Nordic walking"
                ,"Street workout i kalistenika", "Tenis" , "Badminton", "Joga", "Siłownia", "Jazda na rolkach"
                , "Kajaki", "Jazda na deskorolce", "Kitesurfing"
                ,"Golf", "Triathlon", "Spacer z psem", "Siatkówka", "Siatkówka plażowa", "Aerobik", "Park linowy"
                ,"Piłka nożna", "Paintball");
        List<Category> categoryList = category.stream().map(Category::new).toList();
        categoryRepository.saveAll(categoryList);
    }

}
