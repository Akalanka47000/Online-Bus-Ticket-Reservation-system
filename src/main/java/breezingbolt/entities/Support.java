package breezingbolt.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity @Getter @Setter @AllArgsConstructor @RequiredArgsConstructor @NoArgsConstructor @EqualsAndHashCode
@Table(name= "support")
public class Support {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @NotNull(message = "Title cannot be empty")
    private String title;

    @NonNull
    @NotNull(message = "Description box cannot be empty")
    private String body;

    @NonNull @ManyToOne @JoinColumn(name="created_by", nullable=false)
    private User created_by;
}
