package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.model.ActivitiesPlan;

import java.util.List;

public interface ActivitiesPlanRepository extends JpaRepository<ActivitiesPlan, Long> {
    List<ActivitiesPlan> findByUserId(long id);
    List<ActivitiesPlan> findByCity(String city);
}
