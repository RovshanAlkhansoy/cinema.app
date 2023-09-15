package demo.cinema.app.model;

import static demo.cinema.app.model.Hall.TABLE_NAME;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
    @GeneratedValue //As PostgreSql is using generation type should be AUTO (default)
    @Column(updatable = false, nullable = false, unique = true)
    private String id;

    @Column(name = "HALL_NAME")
    private String hallName;

    @Column(name = "CAPACITY")
    @Max(value = 30, message = "Capacity cannot exceed 30")
    @Min(value = 0, message = "Capacity must be non-negative")
    private int capacity = 30;

}
