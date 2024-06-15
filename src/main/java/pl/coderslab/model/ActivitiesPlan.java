package pl.coderslab.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ActivitiesPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    @ManyToOne
    private Category category;
    @NotBlank
    @Size(max=300)
    private String description;
    @NotBlank
    private String city;
    @NotBlank
    @Size(max = 100)
    private String location;
    @OneToMany
    @JoinTable(name = "activites_joined_people")
    private List<UserDetails> userDetails;
    @OneToOne
    private UserDetails user;
}
