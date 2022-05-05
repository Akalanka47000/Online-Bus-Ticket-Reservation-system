package breezingbolt.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity @Getter @Setter @AllArgsConstructor @RequiredArgsConstructor @NoArgsConstructor @EqualsAndHashCode
@Table(name= "schedules")
public class Schedule {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull @ManyToOne @JoinColumn(name="origin_city_id", nullable=false)
    @NotNull(message = "Origin City cannot be empty")
    private City origin_city;

    @NonNull @ManyToOne @JoinColumn(name="destination_city_id", nullable=false)
    @NotNull(message = "Destination City cannot be empty")
    private City destination_city;

    @NonNull
    @NotNull(message = "Time cannot be empty")
    private String time;

    @NonNull
    @NotNull(message = "Bus capacity cannot be empty")
    private int bus_capacity;

    @NonNull @ManyToOne @JoinColumn(name="created_by", nullable=false)
    private User created_by;

    @OneToMany(mappedBy= "bookedSchedule")
    private List<Booking> bookings;
}
