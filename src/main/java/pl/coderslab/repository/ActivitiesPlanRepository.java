package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.model.ActivitiesPlan;

import java.util.List;

public interface ActivitiesPlanRepository extends JpaRepository<ActivitiesPlan, Long> {
    List<ActivitiesPlan> findByUserId(long id);
}
