package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.dto.RegistrationDTO;
import pl.coderslab.Service.UserService;
import pl.coderslab.model.Contact;
import pl.coderslab.model.ContactForm;
import pl.coderslab.model.User;
import pl.coderslab.repository.ContactRepository;
import pl.coderslab.repository.ContactFormRepository;
import pl.coderslab.repository.UserDetailsRepository;
import pl.coderslab.repository.UserRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/gowithme")
public class HomeController {

    private final UserDetailsRepository userDetailsRepository;
    private final ContactRepository contactRepository;
    private final UserService userService;
    private final ContactFormRepository contactFormRepository;
    private final UserRepository userRepository;


    public HomeController(UserDetailsRepository userDetailsRepository, ContactRepository contactRepository, UserService userService, ContactFormRepository contactFormRepository, UserRepository userRepository) {
        this.userDetailsRepository = userDetailsRepository;
        this.contactRepository = contactRepository;
        this.userService = userService;
        this.contactFormRepository = contactFormRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/home")
    public String main() {
        return "/home/main";
    }

    @GetMapping("/login")
    public String login() {
        return "/home/login";
    }

    @GetMapping("/contact")
    public String getContact(Model model) {
        List<Contact> contact = contactRepository.findAll();
        model.addAttribute("address", contact.stream().map(Contact::getAddress).collect(Collectors.joining(";")));
        model.addAttribute("phone",contact.stream().map(el -> el.getPhoneNumber()).map(el -> el.toString()).collect(Collectors.joining("; ")));
        model.addAttribute("email", contact.stream().map(Contact::getEmail).collect(Collectors.joining("; ")));
        model.addAttribute("contactForm", new ContactForm());
        return "/home/contact";
    }
/**
 * add information send successfully
 */
    @PostMapping("/contact")
    public String postContact(@Valid ContactForm contactForm, BindingResult bindingResult,
                        Model model, RedirectAttributes redirectAttributes ) {
        if(bindingResult.hasErrors()) {
            Contact contact = contactRepository.findAll().get(0);
            model.addAttribute("address", contact.getAddress());
            model.addAttribute("phone",contact.getPhoneNumber());
            model.addAttribute("email", contact.getEmail());
            model.addAttribute("messageContact", contactForm);
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "/home/contact";
        }
        contactFormRepository.save(contactForm);
        redirectAttributes.addFlashAttribute("message","Wiadomość wysłana");
        return "redirect:/gowithme/contact";
    }


    @GetMapping("/registration")
    public String getRegistration(ModelMap model) {
        model.addAttribute("registrationWrapper", new RegistrationDTO());
        return "home/registration";
    }
/**
    *Leater must make create token and verification
 */
    @PostMapping("/registration")
    public String postRegistration(@Valid RegistrationDTO wrapper, BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes, Model model) {
        if(!wrapper.getPassword().equals(wrapper.getRepeatPassword())) {
            model.addAttribute("registrationWrapper", wrapper);
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "home/registration";
        }

        if(bindingResult.hasErrors()){
            model.addAttribute("registrationWrapper", wrapper);
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "home/registration";
        }
        userService.saveUser(User.builder()
                .email(wrapper.getEmail())
                .password(wrapper.getPassword())
                .build());
       // wrapper.getUserDetails() .setUser(userRepository.findByEmail(wrapper.getUser().getEmail()));
        userDetailsRepository.save(wrapper.getUserDetails());
        redirectAttributes.addFlashAttribute("message","Rejestracja przebiegła pomyślnie");
        return "redirect:/gowithme/login";
    }


}
