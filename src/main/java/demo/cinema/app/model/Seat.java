package demo.cinema.app.model;

import static demo.cinema.app.model.Seat.TABLE_NAME;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
public class Seat {

    public static final String TABLE_NAME = "SEATS";

    @Id
    @GeneratedValue //As PostgreSql using generation type should be AUTO from default
    @Column(updatable = false, nullable = false, unique = true)
    private Long seatId;

    private String seatNumber;
    private String availabilityStatus;

    @ManyToOne
    @JoinColumn(name = "hall_id", referencedColumnName = "hallId")
    private Hall hall;


}
