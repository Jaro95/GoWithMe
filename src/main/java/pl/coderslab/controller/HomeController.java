package pl.coderslab.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.service.CurrentUser;
import pl.coderslab.dto.RegistrationDTO;
import pl.coderslab.service.UserService;
import pl.coderslab.model.Contact;
import pl.coderslab.model.ContactForm;
import pl.coderslab.model.User;
import pl.coderslab.repository.ContactRepository;
import pl.coderslab.repository.ContactFormRepository;
import pl.coderslab.repository.UserRepository;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/gowithme")
public class HomeController {


    private final ContactRepository contactRepository;
    private final UserService userService;
    private final ContactFormRepository contactFormRepository;
    private final UserRepository userRepository;


    public HomeController(ContactRepository contactRepository, UserService userService, ContactFormRepository contactFormRepository, UserRepository userRepository) {
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
    public String getLogin(@RequestParam(required = false) String error, Model model) {
        model.addAttribute("wrongPassword", error);
        return "/home/login";
    }

    @GetMapping("/validate")
    public String validateUser(@AuthenticationPrincipal CurrentUser currentUser, RedirectAttributes redirectAttributes) {
        if (!currentUser.getUser().isEnabled()) {
            redirectAttributes.addFlashAttribute("messageEnabled", "Konto nie zostało aktywowane");
            return "redirect:/gowithme/login";
        }
        return "redirect:/gowithme/app";
    }

    @GetMapping("/verification")
    public String validateUser(@RequestParam(required = false) String token, RedirectAttributes redirectAttributes) {
        User user = userRepository.findByToken(token);
        if (user == null) {
            redirectAttributes.addFlashAttribute("messageEnabled", "Token nieprawidłowy lub jego ważność wygasła");
            return "redirect:/gowithme/login";
        }
        user.setEnabled(true);
        user.setToken("verified");
        userRepository.save(user);
        redirectAttributes.addFlashAttribute("message", "Konto zostało aktywowane");
        return "redirect:/gowithme/login";
    }

    @GetMapping("/contact")
    public String getContact(Model model) {
        List<Contact> contact = contactRepository.findAll();
        model.addAttribute("address", contact.stream().map(Contact::getAddress).collect(Collectors.joining(";")));
        model.addAttribute("phone", contact.stream().map(el -> el.getPhoneNumber()).map(el -> el.toString()).collect(Collectors.joining("; ")));
        model.addAttribute("email", contact.stream().map(Contact::getEmail).collect(Collectors.joining("; ")));
        model.addAttribute("contactForm", new ContactForm());
        return "/home/contact";
    }


    @PostMapping("/contact")
    public String postContact(@Valid ContactForm contactForm, BindingResult bindingResult,
                              Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            Contact contact = contactRepository.findAll().get(0);
            model.addAttribute("address", contact.getAddress());
            model.addAttribute("phone", contact.getPhoneNumber());
            model.addAttribute("email", contact.getEmail());
            model.addAttribute("messageContact", contactForm);
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "/home/contact";
        }
        contactForm.setCreated(LocalDateTime.now());
        contactFormRepository.save(contactForm);
        redirectAttributes.addFlashAttribute("message", "Wiadomość wysłana");
        return "redirect:/gowithme/contact";
    }


    @GetMapping("/registration")
    public String getRegistration(ModelMap model) {
        model.addAttribute("registrationDTO", new RegistrationDTO());
        return "home/registration";
    }

    /**
     * Leater must make create token and verification
     */
    @PostMapping("/registration")
    public String postRegistration(@Valid RegistrationDTO wrapper, BindingResult bindingResult,
                                   Model model, RedirectAttributes redirectAttributes) {

        if (userRepository.findByEmail(wrapper.getEmail()) != null) {
            model.addAttribute("usedEmail", "Podany email jest zajęty");
            model.addAttribute("registrationDTO", wrapper);
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "home/registration";
        }

        if (!wrapper.getPassword().equals(wrapper.getRepeatPassword())) {
            model.addAttribute("wrongPassword", "Hasla nie sa takie same");
            model.addAttribute("registrationDTO", wrapper);
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "home/registration";
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("registrationDTO", wrapper);
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "home/registration";
        }
        userService.saveUser(wrapper);
        redirectAttributes.addFlashAttribute("message", "Rejestracja przebiegła pomyślnie");
        return "redirect:/gowithme/login";
    }


}
