package demo.cinema.app.repository;

import demo.cinema.app.model.Movie;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByTitleIgnoreCaseContainingOrGenreIgnoreCaseContaining(String title, String genre);

    @Query("SELECT DISTINCT m FROM Movie m LEFT JOIN FETCH m.sessions")
    List<Movie> findAllMoviesWithSessions();

}
