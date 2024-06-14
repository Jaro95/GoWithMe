package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.Service.RegistrationWrapper;
import pl.coderslab.Service.UserService;
import pl.coderslab.model.Contact;
import pl.coderslab.model.MessageContact;
import pl.coderslab.model.User;
import pl.coderslab.model.UserDetails;
import pl.coderslab.repository.ContactRepository;
import pl.coderslab.repository.MessageContactRepository;
import pl.coderslab.repository.UserDetailsRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/gowithme")
public class HomeController {

    private final UserDetailsRepository userDetailsRepository;
    private final ContactRepository contactRepository;
    private final UserService userService;
    private final MessageContactRepository messageContactRepository;

    public HomeController(UserDetailsRepository userDetailsRepository, ContactRepository contactRepository, UserService userService, MessageContactRepository messageContactRepository) {
        this.userDetailsRepository = userDetailsRepository;
        this.contactRepository = contactRepository;
        this.userService = userService;
        this.messageContactRepository = messageContactRepository;
    }


    @GetMapping("/home")
    public String main(Model model) {
        return "/home/main";
    }

//    @GetMapping("/login")
//    public String login() {
//        return "/home/login";
//    }
    @GetMapping("/contact")
    public String login(Model model) {
        Contact contact = contactRepository.findAll().get(0);
        model.addAttribute("address", contact.getAddress());
        model.addAttribute("phone",contact.getPhoneNumber());
        model.addAttribute("email", contact.getEmail());
        model.addAttribute("messageContact", new MessageContact());
        return "/home/contact";
    }

    @PostMapping("/contact")
    public String login(MessageContact messageContact, BindingResult bindingResult,Model model ) {
        if(bindingResult.hasErrors()) {
            Contact contact = contactRepository.findAll().get(0);
            model.addAttribute("address", contact.getAddress());
            model.addAttribute("phone",contact.getPhoneNumber());
            model.addAttribute("email", contact.getEmail());
            model.addAttribute("messageContact", new MessageContact());
            return "/home/contact";
        }
        messageContactRepository.save(messageContact);
        return "redirect:/home/contact";
    }

    @GetMapping("/alluser")
    public String allUser(Model model) {
        model.addAttribute("userDetails", userDetailsRepository.findAll());
        return "application/userList";
    }


    @GetMapping("/registration")
    public String getAddUser(ModelMap model) {
        model.addAttribute("registrationWrapper", new RegistrationWrapper());
        return "home/registration";
    }

    @PostMapping("/registration")
    public String postAddUser(@Valid RegistrationWrapper wrapper, BindingResult result, Model model) {
        if(result.hasErrors()){

        }
        userDetailsRepository.save(wrapper.getUserDetails());
        wrapper.getUser().setUserDetails(wrapper.getUserDetails());
        userService.saveUser(wrapper.getUser());
        return "redirect:/gowithme/home/alluser";
    }

    @GetMapping("/delete")
    public String postAddUser(@RequestParam long id) {
        userDetailsRepository.delete(userDetailsRepository.findById(id).get());
        return "redirect:/gowithme/home/alluser";
    }

    @GetMapping("/update")
    public String getUpdateUser(Model model,@RequestParam long id) {
        model.addAttribute("userDetails", userDetailsRepository.findById(id).get());
        return "application/update";
    }

    @PostMapping("/update")
    public String postUpdateUser(UserDetails userDetails) {
        userDetailsRepository.save(userDetails);
        return "redirect:/gowithme/home/alluser";
    }

}
