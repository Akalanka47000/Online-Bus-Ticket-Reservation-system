package breezingbolt.http.repository;

import breezingbolt.entities.Booking;
import breezingbolt.entities.Schedule;
import breezingbolt.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository  extends JpaRepository<Booking, Long>  {
    List<Booking> findAllByBookedScheduleAndUser(Schedule schedule, User user);
    List<Booking> findAllByUser(User user);
}
