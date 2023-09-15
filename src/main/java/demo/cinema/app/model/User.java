package demo.cinema.app.model;

import static demo.cinema.app.model.User.TABLE_NAME;

import demo.cinema.app.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = TABLE_NAME)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {

    public static final String TABLE_NAME = "USERS";

    @Id
    @GeneratedValue //As PostgreSql is using generation type should be AUTO (default)
    @Column(updatable = false, nullable = false, unique = true)
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LASTNAME")
    private String lastName;

    @Column(name = "FATHER_NAME")
    private String fatherName;

    @Column(name = "USERNAME")
    private String userName;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "BALANCE")
    @Min(value = 0, message = "Balance must be non-negative")
    private BigDecimal balance;

    @Column(name = "CREATED_AT")
    private Date createdAt;

    @OneToMany(mappedBy = "user")
    private transient List<Ticket> tickets = new ArrayList<>();

    @Column(name = "USER_ROLE")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
