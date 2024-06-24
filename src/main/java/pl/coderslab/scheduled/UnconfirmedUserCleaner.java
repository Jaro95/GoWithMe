package pl.coderslab.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.coderslab.model.User;
import pl.coderslab.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class UnconfirmedUserCleaner {


    private UserRepository userRepository;

    @Scheduled(fixedRate = 86400000)
    public void cleanUnconfirmedUsers() {
        LocalDateTime timeBeforeUserDelete = LocalDateTime.now().minusDays(1);
        List<User> unconfirmedUsers = userRepository.unconfirmedUsers("verificacted",timeBeforeUserDelete);
        userRepository.deleteAll(unconfirmedUsers);
    }
}
