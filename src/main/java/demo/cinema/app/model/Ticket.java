package demo.cinema.app.model;

import static demo.cinema.app.model.Ticket.TABLE_NAME;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = TABLE_NAME)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {

    public static final String TABLE_NAME = "TICKETS";

    @Id
    @GeneratedValue //As PostgreSql is using generation type should be AUTO (default)
    @Column(name = "TICKET_ID", updatable = false, nullable = false, unique = true)
    private Long ticketId;

    private double ticketPrice;

    @Column(name = "BOOKING_DATE_TIME", updatable = false)
    @CreationTimestamp
    private LocalDateTime bookingDateTime;

    @Column(name = "REVERSED_DATE_TIME", updatable = false)
    @UpdateTimestamp
    private LocalDateTime reversedDateTime;

    @ManyToOne
    @JoinColumn(name = "SESSION_ID", referencedColumnName = "sessionId")
    private Session session;

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "userId")
    private User user;

}
