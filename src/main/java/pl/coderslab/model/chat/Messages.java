package pl.coderslab.model.chat;

import lombok.*;
import pl.coderslab.model.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@ToString
public class Messages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private UserDetails senderMessage;
    private String content;
    private LocalDateTime sendTime;
    @ManyToOne
    private ChatMessages chat;
}
