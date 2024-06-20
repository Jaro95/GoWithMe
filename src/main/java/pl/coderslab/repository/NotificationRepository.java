package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
}
