package pl.coderslab.scheduled;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.coderslab.model.User;
import pl.coderslab.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UnconfirmedUserCleaner {

    @Autowired
    private UserRepository userRepository;

    @Scheduled(fixedRate = 86400000) //86400000 1000->1s
    public void cleanUnconfirmedUsers() {
        LocalDateTime timeBeforeUserDelete = LocalDateTime.now().minusDays(1);
        List<User> unconfirmedUsers = userRepository.unconfirmedUsers("verificated",timeBeforeUserDelete);
        System.out.println("sprawdzamy");
        unconfirmedUsers.forEach(System.out::println);
        //userRepository.deleteAll(unconfirmedUsers);
    }
}
