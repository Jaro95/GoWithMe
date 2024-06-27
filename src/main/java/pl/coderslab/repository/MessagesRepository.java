package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.model.UserDetails;
import pl.coderslab.model.chat.ChatMessages;
import pl.coderslab.model.chat.Messages;

import java.util.List;
import java.util.Set;

public interface MessagesRepository extends JpaRepository<Messages, Long> {
    @Query("select m.senderMessage from Messages m where m.chat = ?1 ")
    Set<UserDetails> allUserMessages(ChatMessages chatMessages);
    Messages findFirstBySenderMessageOrderBySendTimeDesc(UserDetails userDetails);
    List<Messages> findByChat(ChatMessages chatMessages);
    @Query("select m from Messages m where m.chat = ?1 and m.senderMessage.id = ?2 ")
    List<Messages> allConversationWithUser(ChatMessages chatMessages,long id);
}
