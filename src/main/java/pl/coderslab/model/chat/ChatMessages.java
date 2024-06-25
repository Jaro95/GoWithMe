package pl.coderslab.model.chat;

import lombok.*;
import pl.coderslab.model.User;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class ChatMessages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    private User userChat;
    @ManyToMany
    private List<Messages> messages;
}
