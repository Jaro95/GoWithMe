package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.model.ActivitiesPlan;

public interface ActivitiesPlanRepository extends JpaRepository<ActivitiesPlan, Long> {
}
