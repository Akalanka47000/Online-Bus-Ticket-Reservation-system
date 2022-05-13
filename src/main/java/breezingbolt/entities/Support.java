package breezingbolt.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name= "schedules")
public class Support {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull @ManyToOne @JoinColumn(name="created_by", nullable=false)
    private User created_by;

    @NonNull
    @NotNull(message = "Description box cannot be empty")
    private char description;

    @OneToMany(mappedBy= "bookedSchedule")
    private List<Booking> bookings;
}
