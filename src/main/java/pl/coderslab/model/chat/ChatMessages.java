package pl.coderslab.model.chat;

import lombok.*;
import pl.coderslab.model.UserDetails;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@ToString
public class ChatMessages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    private UserDetails userChat;
    @ManyToMany
    private List<Messages> messages;
}
