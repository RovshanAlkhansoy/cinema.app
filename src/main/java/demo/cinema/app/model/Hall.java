package demo.cinema.app.model;

import static demo.cinema.app.model.Hall.TABLE_NAME;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
public class Hall {

    public static final String TABLE_NAME = "HALLS";

    @Id
    @GeneratedValue //As PostgreSql using generation type should be AUTO from default
    @Column(name = "HALL_ID", updatable = false, nullable = false, unique = true)
    private String hallId;

    private String name;

    public static final int MAX_CAPACITY = 30;

    private String hallType;

}
