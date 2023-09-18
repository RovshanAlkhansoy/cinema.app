package demo.cinema.app.repository;

import demo.cinema.app.model.Ticket;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query("SELECT t FROM Ticket t JOIN FETCH t.session s WHERE t.id = :ticketId")
    Ticket findTicketWithSession(@Param("ticketId") Long ticketId);

    BigDecimal findTicketPriceBySessionId(Long sessionId);

    @Query("SELECT t FROM Ticket t " +
            "WHERE t.session.id = :sessionId " +
            "AND t.user.id = :userId " +
            "AND t.ticketStatus = 'BOOKED'")
    List<Ticket> findTicketsToReverse(@Param("sessionId") Long sessionId, @Param("userId") Long userId);


}
