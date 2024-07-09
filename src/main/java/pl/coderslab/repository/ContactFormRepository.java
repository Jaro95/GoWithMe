package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.model.ContactForm;

public interface ContactFormRepository extends JpaRepository<ContactForm, Long> {
}
