package pl.coderslab.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.model.ActivitiesPlan;
import pl.coderslab.model.Category;
import pl.coderslab.model.Notification;
import pl.coderslab.model.UserDetails;
import pl.coderslab.repository.NotificationRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private NotificationRepository notificationRepository;

    public void addNotificationActivity(UserDetails forUserDetails, ActivitiesPlan activitiesPlan, String message){
        notificationRepository.save(Notification.builder()
                .userDetails(forUserDetails)
                .name(activitiesPlan.getUser().getFirstName() + " " + activitiesPlan.getUser().getLastName()
                        + message + activitiesPlan.getCategory().getName())
                .createDateTime(LocalDateTime.now())
                .display(false)
                .build());
    }

    public void addNotificationRejectActivity(UserDetails forUserDetails , UserDetails userSendRequest, String message , Category category){
        notificationRepository.save(Notification.builder()
                .userDetails(forUserDetails)
                .name(userSendRequest.getFirstName() + " " + userSendRequest.getLastName()
                        + message + category.getName())
                .createDateTime(LocalDateTime.now())
                .display(false)
                .build());
    }
}
