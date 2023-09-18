package demo.cinema.app.repository;

import demo.cinema.app.model.Session;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SessionRepository extends JpaRepository<Session, Long> {

    @Query("SELECT s FROM Session s LEFT JOIN FETCH s.movie m WHERE m.id = :movieId")
    List<Session> findByMovie(@Param("movieId") Long movieId);

    @Query("SELECT s FROM Session s LEFT JOIN FETCH s.movie m WHERE s.showtime >= :from AND s.showtime <= :to")
    List<Session> findByDate(@Param("dateStart") Date from, @Param("dateEnd") Date to);

    @Query("SELECT s FROM Session s LEFT JOIN FETCH s.hall h WHERE h.id = :hallId")
    List<Session> findByHall(@Param("hallId") Long hallId);

    @Query("SELECT s FROM Session s LEFT JOIN FETCH s.movie m WHERE m.id = :movieId AND s.showtime >= :from AND s" +
            ".showtime <= :to")
    List<Session> findByMovieAndDate(@Param("movieId") Long movieId, @Param("dateStart") Date from,
                                     @Param("dateEnd") Date to);

    @Query("SELECT s FROM Session s LEFT JOIN FETCH s.movie m LEFT JOIN FETCH s.hall h WHERE m.id = :movieId AND h.id = :hallId")
    List<Session> findByMovieAndHall(@Param("movieId") Long movieId, @Param("hallId") Long hallId);

}

