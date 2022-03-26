package breezingbolt.entities;

import lombok.*;

import javax.persistence.*;

@Entity @Getter @Setter @AllArgsConstructor @RequiredArgsConstructor @NoArgsConstructor @EqualsAndHashCode
@Table(name= "cities")
public class City {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    private String name;
}
