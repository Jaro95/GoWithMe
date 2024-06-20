package pl.coderslab.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@ToString
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    @Size(max = 50)
    private String firstName;
    @NotBlank
    @Size(max = 50)
    private String lastName;
    @NotBlank
    @Size(max=100)
    private String city;
    @Min(0)
    private int age;
    @Size(max=300)
    private String description;
    @ManyToMany
    private List<ActivitiesPlan> activitiesPlan;
    @ManyToMany
    private List<Notification> notificationsList;
    @OneToOne
    private User user;

}
