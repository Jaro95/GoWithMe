package pl.coderslab.Service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.dto.RegistrationDTO;
import pl.coderslab.model.Role;
import pl.coderslab.model.User;
import pl.coderslab.model.UserDetails;
import pl.coderslab.repository.RoleRepository;
import pl.coderslab.repository.UserDetailsRepository;
import pl.coderslab.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserDetailsRepository userDetailsRepository;
    private final EmailService emailService;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           BCryptPasswordEncoder passwordEncoder, UserDetailsRepository userDetailsRepository, EmailService emailService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userDetailsRepository = userDetailsRepository;
        this.emailService = emailService;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(RegistrationDTO user) {
        Role userRole = roleRepository.findByName("ROLE_USER");
        userRepository.save(User.builder()
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .enabled(false)
                .roles(new HashSet<>(Arrays.asList(userRole)))
                .createdAccount(LocalDateTime.now())
                .token(UUID.randomUUID().toString())
                .build());
        userDetailsRepository.save(UserDetails.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .city(user.getCity())
                .user(userRepository.findByEmail(user.getEmail())).build());
       // emailService.sendVerificationEmail(user.getEmail(),userRepository.findByEmail(user.getEmail()).getToken());
    }

    @Override
    public void saveAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        Role userRole = roleRepository.findByName("ROLE_ADMIN");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    @Override
    public void saveGod(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        Role userRole = roleRepository.findByName("ROLE_SUPER_ADMIN");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    public void changeEmail(String email, User user) {
        user.setEmail(email);
        userRepository.save(user);
    }
    public void changePassword(String password, User user) {
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

}
