package pl.coderslab.Service;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.model.User;
import pl.coderslab.model.UserDetails;

@Getter
@Setter
public class RegistrationWrapper {
    private User user;
    private UserDetails userDetails;
}
