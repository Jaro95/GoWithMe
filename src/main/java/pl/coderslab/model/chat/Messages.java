package pl.coderslab.model.chat;

import lombok.*;
import pl.coderslab.model.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
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
    @Size(max = 1000)
    private String content;
    private LocalDateTime sendTime;
    @ManyToOne
    private ChatMessages chat;
}
