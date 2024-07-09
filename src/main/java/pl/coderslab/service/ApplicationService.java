package pl.coderslab.service;

import org.springframework.stereotype.Service;
import pl.coderslab.model.Category;
import pl.coderslab.model.Notification;
import pl.coderslab.model.UserDetails;
import pl.coderslab.model.chat.ChatMessages;
import pl.coderslab.repository.MessagesRepository;
import pl.coderslab.repository.NotificationRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ApplicationService {


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

    public List<UserDetails> getPaginatedList(List<UserDetails> dataList, int pageNumber, int pageSize) {
        int fromIndex = (pageNumber - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, dataList.size());

        if (fromIndex >= dataList.size() || fromIndex < 0) {
            return Collections.emptyList(); // Zwraca pustą listę jeśli indeks jest poza zakresem
        }
        return dataList.subList(fromIndex, toIndex);
    }
}
