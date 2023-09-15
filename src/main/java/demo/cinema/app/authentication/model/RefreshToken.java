package demo.cinema.app.authentication.model;

import static demo.cinema.app.authentication.model.RefreshToken.TABLE_NAME;

import demo.cinema.app.model.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.Instant;
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
public class RefreshToken {

    public static final String TABLE_NAME = "REFRESH_TOKENS";

    @Id
    private Long id;

    private String token;

    private Instant expiryDate;

    @OneToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "userId")
    private User user;

}
