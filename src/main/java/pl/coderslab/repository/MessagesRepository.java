package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.model.UserDetails;
import pl.coderslab.model.chat.ChatMessages;
import pl.coderslab.model.chat.Messages;
import pl.coderslab.service.CurrentUser;

import java.util.List;
import java.util.Set;

@Repository
public interface MessagesRepository extends JpaRepository<Messages, Long> {
    @Query("select m.senderMessage from Messages m where m.chat = ?1 ")
    Set<UserDetails> allUserMessages(ChatMessages chatMessages);
    Messages findFirstBySenderMessageOrderBySendTimeDesc(UserDetails userDetails);
    List<Messages> findByChat(ChatMessages chatMessages);
    @Query("select m from Messages m where m.chat.userChat = ?1 and m.senderMessage = ?2 ")
    List<Messages> allConversationWhenOtherUserSender(UserDetails currentUser, UserDetails otherUserSender);
    @Query("select m from Messages m where m.chat.userChat = ?1 and m.senderMessage = ?2 ")
    List<Messages> allConversationWhenCurrentUserSender(UserDetails otherUserSender,UserDetails currentUser);
}
