package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.model.User;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    User findByToken(String token);

    @Query("from User u where u.token != ?1 and u.createdAccount < ?2")
    List<User> unconfirmedUsers(String token, LocalDateTime localDateTime);
}
