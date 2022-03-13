package breezingbolt.entities;

import lombok.*;

import javax.persistence.*;

@Entity @Getter @Setter @RequiredArgsConstructor @NoArgsConstructor @EqualsAndHashCode
@Table(name= "users")
public class User {
    @Id @GeneratedValue
    private long id;

    @NonNull @ManyToOne @JoinColumn(name="role_id", nullable=false)
    private Role role;

    @NonNull
    private String username;
    @NonNull
    private String first_name;
    @NonNull
    private String last_name;
    @NonNull
    private String email;
    @NonNull
    private String password;
    @NonNull
    private boolean is_active;
}
