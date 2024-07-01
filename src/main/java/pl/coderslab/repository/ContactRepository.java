package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    Contact findByAddress(String address);
}
