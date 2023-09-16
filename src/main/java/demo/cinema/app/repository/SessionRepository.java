package demo.cinema.app.repository;

import demo.cinema.app.model.Session;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {

    List<Session> findByMovieId(Long movieId);

    List<Session> findByHallId(Long hallId);

    Optional<Session> findByIdAndMovieId(Long sessionId, Long movieId);

}

