package demo.cinema.app.model;

import static demo.cinema.app.model.Hall.TABLE_NAME;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
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
public class Hall {

    public static final String TABLE_NAME = "HALLS";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, unique = true)
    private Long id;

    @Column(name = "HALL_NAME")
    private String hallName;

    @Column(name = "CAPACITY")
    @Max(value = 30, message = "Capacity cannot exceed 30")
    @Positive(message = "Capacity must be positive")
    private int capacity = 30;

}
