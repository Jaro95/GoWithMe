package pl.coderslab.service;

import pl.coderslab.dto.RegistrationDTO;
import pl.coderslab.model.User;

public interface UserService {

    User findByEmail(String email);

    void saveUser(RegistrationDTO user);

    void saveAdmin(User user);

    void saveGod(User user);
}
