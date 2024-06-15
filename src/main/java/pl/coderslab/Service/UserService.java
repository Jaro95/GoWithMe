package pl.coderslab.Service;

import pl.coderslab.model.User;

public interface UserService {

    User findByEmail(String email);

    void saveUser(User user);

    void saveAdmin(User user);

    void saveGod(User user);
}
