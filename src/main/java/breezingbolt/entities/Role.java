package breezingbolt.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity @Getter @Setter @AllArgsConstructor @RequiredArgsConstructor @NoArgsConstructor @EqualsAndHashCode
@Table(name= "roles")
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull @Column(unique=true)
    private String name;

    @OneToMany(mappedBy="role")
    private List<User> assigned_users;

}
