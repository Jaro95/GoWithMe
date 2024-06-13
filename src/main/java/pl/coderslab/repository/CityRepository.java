package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.model.City;

public interface CityRepository extends JpaRepository<City, Long> {
}
