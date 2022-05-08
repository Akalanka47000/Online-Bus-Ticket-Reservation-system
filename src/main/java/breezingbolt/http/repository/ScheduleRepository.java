package breezingbolt.http.repository;

import breezingbolt.entities.City;
import breezingbolt.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
 public Optional<Schedule> findByOriginCityAndDestinationCity(City originCity, City destinationCity);
}
