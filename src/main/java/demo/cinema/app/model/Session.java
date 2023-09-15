package demo.cinema.app.model;

import static demo.cinema.app.model.Session.TABLE_NAME;

import demo.cinema.app.enums.SessionType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
    @Column(name = "SESSION_ID", updatable = false, nullable = false, unique = true)
    private Long sessionId;

    private int availableSeatsCount;

    @Temporal(TemporalType.TIMESTAMP)
    private Date showtime;

    @Column(name = "SESSION_TYPE")
    private SessionType sessionType;

    @ManyToOne
    @JoinColumn(name = "HALL_ID", referencedColumnName = "hallId")
    private Hall hall;

    @ManyToOne
    @JoinColumn(name = "MOVIE_ID", referencedColumnName = "sessionId")
    private Movie movie;

}
