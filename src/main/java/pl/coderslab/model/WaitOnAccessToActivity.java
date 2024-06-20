package pl.coderslab.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class WaitOnAccessToActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private ActivitiesPlan activityPlan;
    @ManyToOne
    private UserDetails userDetails;
    private LocalDateTime requestDate;
}
