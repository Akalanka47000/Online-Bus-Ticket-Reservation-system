package breezingbolt.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity @Getter @Setter @RequiredArgsConstructor @NoArgsConstructor @EqualsAndHashCode
@Table(name= "users")
public class User {
    @Id @GeneratedValue
    private long id;

    @NonNull @NotNull @ManyToOne @JoinColumn(name="role_id", nullable=false)
    private Role role;

    @NonNull @NotNull @Column(unique=true)
    private String username;
    @NonNull @NotNull
    private String first_name;
    @NonNull @NotNull
    private String last_name;
    @NonNull @NotNull @Column(unique=true)
    private String email;
    @NonNull @NotNull
    private String password;
    @NonNull @Column(columnDefinition = "boolean default true")
    private boolean is_active;
}
