package pl.coderslab.service;

import org.springframework.stereotype.Service;
import pl.coderslab.model.Category;
import pl.coderslab.model.Notification;
import pl.coderslab.model.UserDetails;
import pl.coderslab.repository.NotificationRepository;

import java.time.LocalDateTime;

@Service
public class NotificationService {


    public void addNotificationRejectActivity(NotificationRepository notificationRepository,UserDetails forUserDetails,
                                              UserDetails userSendRequest, String message , Category category){
        notificationRepository.save(Notification.builder()
                .userDetails(forUserDetails)
                .name(userSendRequest.getFirstName() + " " + userSendRequest.getLastName()
                        + message + category.getName())
                .createDateTime(LocalDateTime.now())
                .display(false)
                .build());
    }
}
