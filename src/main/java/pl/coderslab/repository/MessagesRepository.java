package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.model.chat.Messages;

public interface MessagesRepository extends JpaRepository<Messages, Long> {
}
