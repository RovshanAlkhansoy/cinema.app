package demo.cinema.app.model;

import static demo.cinema.app.model.Movie.TABLE_NAME;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = TABLE_NAME)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {

    public static final String TABLE_NAME = "MOVIES";

    @Id
    @GeneratedValue //As PostgreSql using generation type should be AUTO from default
    @Column(updatable = false, nullable = false, unique = true)
    private long movieId;

    private String title;
    private String genre;
    private int duration;

    @Temporal(TemporalType.DATE)
    private Date releaseDate;

    private String director;
    private String rating;

}
