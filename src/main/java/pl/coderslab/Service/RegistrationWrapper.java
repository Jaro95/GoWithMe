package pl.coderslab.Service;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.model.City;
import pl.coderslab.model.User;
import pl.coderslab.model.UserDetails;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RegistrationWrapper {
    private User user;
    private UserDetails userDetails;
    private City city;
    @NotBlank
    private String repeatPassword;
}
