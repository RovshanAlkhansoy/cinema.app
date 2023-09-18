package demo.cinema.app.model;

import static demo.cinema.app.model.Ticket.TABLE_NAME;

import demo.cinema.app.enums.TicketStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Date;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, unique = true)
    private Long id;

    @Column(name = "TICKET_PRICE")
    @Positive(message = "Ticket price must be positive")
    private BigDecimal ticketPrice;

    @Column
    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus = TicketStatus.AVAILABLE;

    @Column(name = "BOOKING_DATE_TIME", updatable = false)
    @CreationTimestamp
    private Date bookingDateTime;

    @Column(name = "REVERSED_DATE_TIME", updatable = false)
    @UpdateTimestamp
    private Date reversedDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SESSION_ID", referencedColumnName = "sessionId")
    private Session session;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", referencedColumnName = "userId")
    private User user;

}
