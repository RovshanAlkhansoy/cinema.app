package demo.cinema.app.repository;

import demo.cinema.app.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String userName);

}
