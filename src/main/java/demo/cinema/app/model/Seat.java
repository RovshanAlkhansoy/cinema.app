package demo.cinema.app.model;

import static demo.cinema.app.model.Seat.TABLE_NAME;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;
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
public class Seat {

    public static final String TABLE_NAME = "SEATS";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, unique = true)
    private Long id;

    @Column(name = "SEAT_NUMBER")
    @Positive(message = "Seat number count must be positive")
    private String seatNumber;

    @Column(name = "IS_SEAT_AVAILABLE")
    private boolean isSeatAvailable;

    @ManyToOne
    @JoinColumn(name = "HALL_ID", referencedColumnName = "hallId")
    private Hall hall;

}
