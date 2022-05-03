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

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Aspect
@Component
public class ScheduleController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private UserRepository userRepository;

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
            scheduleToUpdate.setOrigin_city(schedule.getOrigin_city());
            scheduleToUpdate.setDestination_city(schedule.getDestination_city());
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

    private HashMap schedulePageInjections() {
        List<Schedule> schedule = scheduleRepository.findAll();
        List<City> cities = cityRepository.findAll();
        return new HashMap() {
            {
                put("schedule", schedule);
                put("cityList", cities);
            }
        };
    }
}
