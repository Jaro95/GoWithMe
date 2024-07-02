package pl.coderslab.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import pl.coderslab.dto.MessagesDTO;
import pl.coderslab.model.UserDetails;
import pl.coderslab.model.chat.ChatMessages;
import pl.coderslab.model.chat.Messages;
import pl.coderslab.repository.ChatMessagesRepository;
import pl.coderslab.repository.MessagesRepository;
import pl.coderslab.repository.UserDetailsRepository;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {

    private List<WebSocketSession> sessions = new ArrayList<>();
    private final ObjectMapper objectMapper;

    @Autowired
    private MessagesRepository messagesRepository;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private ChatMessagesRepository chatMessagesRepository;

    @Autowired
    public ChatWebSocketHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws IOException {
       MessagesDTO messagesDTO = objectMapper.readValue(textMessage.getPayload(), MessagesDTO.class);
       // System.out.println("tutaj masz wiadomość: " + message.toString());
       // messageRepository.save(message);
        messagesRepository.save(Messages.builder()
                .senderMessage(userDetailsRepository.findById(messagesDTO.getSenderMessage().getId()).get())
                .content(messagesDTO.getContent())
                .sendTime(LocalDateTime.now())
                .chat(chatMessagesRepository.findByUserChatId(messagesDTO.getReceiverMessage().getId()))
                .build());
        for (WebSocketSession webSocketSession : sessions) {
            if (webSocketSession.isOpen()) {
                webSocketSession.sendMessage(textMessage);
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
    }


}