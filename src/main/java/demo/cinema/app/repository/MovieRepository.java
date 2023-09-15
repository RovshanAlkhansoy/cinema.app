package demo.cinema.app.repository;

import demo.cinema.app.model.Movie;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByTitleIgnoreCaseContainingOrGenreIgnoreCaseContaining(String title, String genre);

}
