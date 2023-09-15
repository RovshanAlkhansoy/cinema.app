package demo.cinema.app.model;

import static demo.cinema.app.model.Movie.TABLE_NAME;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
public class Movie {

    public static final String TABLE_NAME = "MOVIES";

    @Id
    @GeneratedValue //As PostgreSql is using generation type should be AUTO (default)
    @Column(updatable = false, nullable = false, unique = true)
    private long movieId;

    private String title;
    private String genre;
    private int duration;

    @Temporal(TemporalType.DATE)
    private Date releaseDate;

    private String rating;

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    private List<Session> sessions = new ArrayList<>();

}
