package breezingbolt.http.repository;

import breezingbolt.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository  extends JpaRepository<Booking, Long>  {
}
