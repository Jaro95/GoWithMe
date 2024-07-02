package pl.coderslab.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MessagesDTO {
    private UserIdDTO senderMessage;
    private UserIdDTO receiverMessage;
    private String content;
    private LocalDateTime timestamp;


}
