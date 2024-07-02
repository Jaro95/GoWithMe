package pl.coderslab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.model.chat.ChatMessages;
import pl.coderslab.service.UserService;
import pl.coderslab.model.*;
import pl.coderslab.repository.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
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
    private final ChatMessagesRepository chatMessagesRepository;

    @GetMapping("/create-start")
    public String createStart() {
        if (!userRepository.findAll().isEmpty()) {
            return "redirect:/gowithme/admin";
        }
        User god = User.builder().email("god@god")
                .password("qwerty")
                .createdAccount(LocalDateTime.now())
                .enabled(true)
                .token("verified")
                .build();
        User admin = User.builder().email("admin@admin")
                .password("admin")
                .createdAccount(LocalDateTime.now())
                .enabled(true)
                .token("verified")
                .build();
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
        chatMessagesRepository.save(ChatMessages.builder()
                .userChat(userDetailsRepository.findByUserEmail(god.getEmail()))
                .build());
        chatMessagesRepository.save(ChatMessages.builder()
                .userChat(userDetailsRepository.findByUserEmail(admin.getEmail()))
                .build());
        createContact();
        createCategories();
        return "redirect:/gowithme/admin";
    }


    @GetMapping("")
    public String allUser(Model model) {
        model.addAttribute("userDetails", userDetailsRepository.findAll());
        return "admin/adminPanel";
    }

    @GetMapping("/user/update")
    public String getUpdateUser(@RequestParam long id, Model model) {
        UserDetails updateUser = userDetailsRepository.findById(id).get();
        model.addAttribute("userId", updateUser.getUser().getId());
        model.addAttribute("userEmail", updateUser.getUser().getEmail());
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("userRole", updateUser.getUser().getRoles());
        model.addAttribute("userDetails", updateUser);
        return "admin/updateUser";
    }

    @PostMapping("/user/update")
    public String postUpdateUser(@Valid UserDetails userDetails, BindingResult result,
                                 Model model, RedirectAttributes redirectAttributes) {
        if(result.hasErrors()) {
            model.addAttribute("userId", userDetails.getUser().getId());
            model.addAttribute("userEmail", userDetails.getUser().getEmail());
            model.addAttribute("roles", roleRepository.findAll());
            model.addAttribute("userRole", userDetails.getUser().getRoles());
            model.addAttribute("userDetails", userDetails);
            model.addAttribute("errors", result.getAllErrors());
            return "admin/updateUser";
        }
        userRepository.save(userDetails.getUser());
        userDetailsRepository.save(userDetails);
        redirectAttributes.addFlashAttribute("message", "Użytkownik został zaaktualizowany");
        return "redirect:/gowithme/admin/";
    }

    @GetMapping("/user/delete")
    public String getDeleteUser(@RequestParam long id,RedirectAttributes redirectAttributes) {
        User deleteUser = userRepository.findById(id).get();
        deleteUser.getRoles().clear();
        System.out.println(deleteUser);
        userRepository.delete(deleteUser);
        redirectAttributes.addFlashAttribute("message" , "Usunięto użytkownika");
        return "redirect:/gowithme/admin/";
    }

    @GetMapping("/contact")
    public String getContact(@RequestParam(required = false) Long updateId, Model model) {
        if(updateId != null) {
            model.addAttribute("updateId",updateId);
        }
        model.addAttribute("contactList", contactRepository.findAll());
        return "admin/contact";
    }
    @PostMapping("/contact")
    public String postUpdateContact(@RequestParam long contactId, @RequestParam String address,
                                    @RequestParam String email,@RequestParam int phoneNumber,
                                     RedirectAttributes redirectAttributes) {
        Contact contact = contactRepository.findById(contactId).get();
        contact.setAddress(address);
        contact.setEmail(email);
        contact.setPhoneNumber(phoneNumber);
        contactRepository.save(contact);
        redirectAttributes.addFlashAttribute("message", "Zaktualizowano kontakt");
        return "redirect:/gowithme/admin/contact";
    }

    @GetMapping("/contactDelete")
    public String getDeleteContact(@RequestParam(required = false) Long deleteId,
                                    RedirectAttributes redirectAttributes) {
        contactRepository.deleteById(deleteId);
        redirectAttributes.addFlashAttribute("message", "Usunięto kontakt");
        return "redirect:/gowithme/admin/contact";
    }

    @GetMapping("/contactAdd")
    public String getAddContact(Model model) {
        model.addAttribute("contact", new Contact());
        return "admin/newContact";
    }

    @PostMapping("/contactAdd")
    public String postAddContact(@Valid Contact contact, Model model,
                              BindingResult result,
                              RedirectAttributes redirectAttributes) {
        if(contactRepository.findByAddress(contact.getAddress()) != null) {
            model.addAttribute("contact", contact);
            model.addAttribute("messageError", "Podany kontakt już istnieje");
            return "admin/newContact";
        }
        if (result.hasErrors()) {
            model.addAttribute("contact", contact);
            model.addAttribute("errors", result.getAllErrors());
            return "admin/newContact";
        }
        contactRepository.save(contact);
        redirectAttributes.addFlashAttribute("message", "Dodano nowy kontakt");
        return "redirect:/gowithme/admin/contact";
    }

    @GetMapping("/category")
    public String getCategory(@RequestParam(required = false) Long updateId, Model model) {
        if(updateId != null) {
            model.addAttribute("updateId",updateId);
        }
        model.addAttribute("categoryList", categoryRepository.findAll());
        return "admin/category";
    }

    @GetMapping("/categoryAdd")
    public String getNewCategory(Model model) {
        model.addAttribute("category", new Category());
        return "admin/newCategory";
    }

    @PostMapping("/categoryAdd")
    public String postNewCategory(@Valid Category category, Model model,
                              BindingResult result,
                              RedirectAttributes redirectAttributes) {
        if(categoryRepository.findByName(category.getName()) != null) {
            model.addAttribute("category", category);
            model.addAttribute("messageError", "Podana kategoria już istnieje");
            return "admin/newCategory";
        }
        if (result.hasErrors()) {
            model.addAttribute("category", category);
            model.addAttribute("errors", result.getAllErrors());
            return "admin/newCategory";
        }
        categoryRepository.save(category);
        redirectAttributes.addFlashAttribute("message", "Dodano nową kategorię");
        return "redirect:/gowithme/admin/category";
    }


    @PostMapping("/category")
    public String postUpdateCategory(@RequestParam long categoryId, @RequestParam String categoryName,
                               RedirectAttributes redirectAttributes) {
        Category category = categoryRepository.findById(categoryId).get();
        category.setName(categoryName);
        categoryRepository.save(category);
        redirectAttributes.addFlashAttribute("message", "Zaktualizowano kategorię");
        return "redirect:/gowithme/admin/category";
    }

    @GetMapping("/categoryDelete")
    public String getDeleteCategory(@RequestParam(required = false) Long deleteId,
                                    RedirectAttributes redirectAttributes) {
        categoryRepository.deleteById(deleteId);
        redirectAttributes.addFlashAttribute("message", "Usunięto kategorię");
        return "redirect:/gowithme/admin/category";
    }



    public void createContact() {
        Contact contact = Contact.builder().address("Konin, ul.bez ulicy")
                .email("gowithme@gmail.com")
                .phoneNumber(889992993).build();

        contactRepository.save(contact);
    }

    public void createCategories() {
        List<String> category = Arrays.asList("Relaks", "Bieganie", "Gotowanie", "Spacer"
                , "Wycieczka", "Pływanie", "Jazda na rowerze", "Nordic walking"
                , "Street workout i kalistenika", "Tenis", "Badminton", "Joga", "Siłownia", "Jazda na rolkach"
                , "Kajaki", "Jazda na deskorolce", "Kitesurfing"
                , "Golf", "Triathlon", "Spacer z psem", "Siatkówka", "Siatkówka plażowa", "Aerobik", "Park linowy"
                , "Piłka nożna", "Paintball");
        List<Category> categoryList = category.stream().map(Category::new).toList();
        categoryRepository.saveAll(categoryList);
    }

}
