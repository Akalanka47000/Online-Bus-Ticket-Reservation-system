package breezingbolt.entities;

import lombok.*;

import javax.persistence.*;

@Entity @Getter @Setter @RequiredArgsConstructor @NoArgsConstructor @EqualsAndHashCode
@Table(name= "cities")
public class City {
    @Id @GeneratedValue
    private long id;

    @NonNull
    private String name;
}
