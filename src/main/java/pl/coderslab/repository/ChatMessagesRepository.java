package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.model.User;
import pl.coderslab.model.UserDetails;
import pl.coderslab.model.chat.ChatMessages;
import pl.coderslab.model.chat.Messages;

import java.util.List;

public interface ChatMessagesRepository extends JpaRepository<ChatMessages, Long> {
    ChatMessages findByUserChat(UserDetails user);
    ChatMessages findByUserChatId(long id);
}
