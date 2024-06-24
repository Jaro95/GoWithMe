package pl.coderslab.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.model.Notification;
import pl.coderslab.model.UserDetails;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
    List<Notification> findAllByUserDetailsOrderByCreateDateTimeDesc(UserDetails userDetails);
    Page<Notification> findAllByUserDetailsIdOrderByCreateDateTimeDesc(long id, Pageable pageable);
}
