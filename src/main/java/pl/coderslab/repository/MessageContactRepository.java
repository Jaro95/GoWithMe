package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.model.MessageContact;

public interface MessageContactRepository extends JpaRepository<MessageContact, Long> {
}
