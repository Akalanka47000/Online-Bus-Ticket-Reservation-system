package breezingbolt.http.controllers;

import breezingbolt.entities.City;
import breezingbolt.entities.Schedule;
import breezingbolt.entities.User;
import breezingbolt.http.principals.UserPrincipal;
import breezingbolt.http.repository.CityRepository;
import breezingbolt.http.repository.ScheduleRepository;
import breezingbolt.http.repository.UserRepository;
import breezingbolt.http.services.AuthService;
import breezingbolt.utils.ExceptionHandler;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Aspect
@Component
public class ScheduleController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HomeController homeController;

    public ModelAndView getSchedulePage() {
        return ExceptionHandler.handle(
                () -> new ModelAndView("/schedule/schedule", schedulePageInjections(), HttpStatus.OK), "/serverError",
                this::schedulePageInjections);
    }

    public ModelAndView addSchedule(Schedule schedule) {
        return ExceptionHandler.handle(() -> {
            Optional<UserPrincipal> currentUser = AuthService.getCurrentUser();
            long id = currentUser.get().getId();
            User cUser = userRepository.findById(id).get();
            schedule.setCreated_by(cUser);
            scheduleRepository.save(schedule);
            return this.getSchedulePage();
        }, "/schedule/schedule", this::schedulePageInjections);
    }

    public ModelAndView updateSchedule(Schedule schedule) {
        return ExceptionHandler.handle(() -> {
            Schedule scheduleToUpdate = scheduleRepository.findById(schedule.getId()).get();
            scheduleToUpdate.setOriginCity(schedule.getOriginCity());
            scheduleToUpdate.setDestinationCity(schedule.getDestinationCity());
            scheduleToUpdate.setTime(schedule.getTime());
            scheduleToUpdate.setBus_capacity(schedule.getBus_capacity());
            scheduleRepository.save(scheduleToUpdate);
            return this.getSchedulePage();
        }, "/schedule/schedule", this::schedulePageInjections);
    }

    public ModelAndView deleteSchedule(long id) {
        return ExceptionHandler.handle(() -> {
            scheduleRepository.deleteById(id);
            return this.getSchedulePage();
        }, "/schedule/schedule", this::schedulePageInjections);
    }

    public ModelAndView getAvailability(long origin_city_id, long destination_city_id) {
        Optional<Schedule> schedule = scheduleRepository.findByOriginCityAndDestinationCity(cityRepository.findById(origin_city_id).get(), cityRepository.findById(destination_city_id).get());
        Boolean availability;
        if (schedule.isPresent()) availability = true;
        else availability = false;
        HashMap availabilityMap = new HashMap() {
            {
                put("routeAvailable", availability.toString());
            }
        };
        availabilityMap.putAll(homeController.homePageInjections());
        return ExceptionHandler.handle(() -> new ModelAndView("/index", availabilityMap , HttpStatus.OK), "/serverError");
    }

    public HashMap schedulePageInjections() {
        List<Schedule> schedule = scheduleRepository.findAll();
        List<City> cities = cityRepository.findAll();

        Collections.sort(schedule, (o1, o2) -> {
            try {
                return new SimpleDateFormat("hh:mm").parse(o1.getTime()).compareTo(new SimpleDateFormat("hh:mm").parse(o2.getTime()));
            } catch (ParseException e) {
                return 0;
            }
        });

        return new HashMap() {
            {
                put("schedule", schedule);
                put("cityList", cities);
            }
        };
    }
}
