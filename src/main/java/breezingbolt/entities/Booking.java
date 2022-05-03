package breezingbolt.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity @Getter @Setter @AllArgsConstructor @RequiredArgsConstructor @NoArgsConstructor @EqualsAndHashCode
@Table(name= "bookings")
public class Booking {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull @ManyToOne @JoinColumn(name="schedule_id", nullable=false)
    @NotNull(message = "Schedule cannot be empty")
    private Schedule booked_schedule;

    @NonNull @ManyToOne @JoinColumn(name="user_id", nullable=false)
    @NotNull(message = "User cannot be empty")
    private User user;
}
