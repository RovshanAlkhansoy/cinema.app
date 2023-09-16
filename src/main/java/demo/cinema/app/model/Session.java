package demo.cinema.app.model;

import static demo.cinema.app.model.Session.TABLE_NAME;

import demo.cinema.app.enums.SessionType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = TABLE_NAME)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Session {

    public static final String TABLE_NAME = "SESSIONS";

    @Id
    @GeneratedValue //As PostgreSql is using generation type should be AUTO (default)
    @Column(updatable = false, nullable = false, unique = true)
    private Long id;

    @Column(name = "AVAILABLE_SEATS_COUNT")
    @Positive(message = "Available seats count must be positive")
    private int availableSeatsCount;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "SHOWTIME")
    private Date showtime;

    @Column(name = "SESSION_TYPE")
    @Enumerated(EnumType.STRING)
    private SessionType sessionType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HALL_ID", referencedColumnName = "id")
    private Hall hall;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MOVIE_ID", referencedColumnName = "id")
    private Movie movie;

}
