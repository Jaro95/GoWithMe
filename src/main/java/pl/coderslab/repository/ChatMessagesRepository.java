package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.model.User;
import pl.coderslab.model.chat.ChatMessages;

public interface ChatMessagesRepository extends JpaRepository<ChatMessages, Long> {
    ChatMessages findByUserChat(User user);
}
