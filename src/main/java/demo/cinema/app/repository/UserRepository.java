package demo.cinema.app.repository;

import demo.cinema.app.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUserName(String userName);

    @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.tickets t WHERE u.userId = :userId")
    User getUserWithTickets(@Param("userId") Long userId);

}
