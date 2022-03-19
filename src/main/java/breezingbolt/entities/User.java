package breezingbolt.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity @Getter @Setter @RequiredArgsConstructor @NoArgsConstructor @EqualsAndHashCode
@Table(name= "users")
public class User {
    @Id @GeneratedValue
    private long id;

    @NonNull @ManyToOne @JoinColumn(name="role_id", nullable=false)
    @NotNull(message = "Role cannot be empty")
    private Role role;

    @NonNull @Column(unique=true)
    @NotNull(message = "Username cannot be empty")
    private String username;

    @NonNull
    @NotNull(message = "First name cannot be empty")
    private String first_name;

    @NonNull
    @NotNull(message = "Last name cannot be empty")
    private String last_name;

    @NonNull @Column(unique=true)
    @NotNull(message = "Email cannot be empty")
    private String email;

    @NonNull
    @NotNull(message = "Password cannot be empty")
    private String password;

    @NonNull @Column(columnDefinition = "boolean default true")
    private boolean is_active;
}
