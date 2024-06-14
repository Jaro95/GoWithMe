package pl.coderslab.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 60)
    @NotBlank
    @Email
    @Size(max = 50)
    private String email;
    @NotBlank
    @Size(min = 5)
    private String password;
    @NotNull
    private int enabled;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Role> roles;
    @OneToOne
    private UserDetails userDetails;
}
