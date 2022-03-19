package breezingbolt.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity @Getter @Setter @RequiredArgsConstructor @NoArgsConstructor @EqualsAndHashCode
@Table(name= "roles")
public class Role {
    @Id @GeneratedValue
    private long id;

    @NonNull @Column(unique=true)
    private String name;

    @OneToMany(mappedBy="role")
    private List<User> assigned_users;

}
