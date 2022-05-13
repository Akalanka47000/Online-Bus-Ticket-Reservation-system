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
public class SupportController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private SupportRepository supportRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HomeController homeController;

    public ModelAndView getSupportPage() {
        return ExceptionHandler.handle(
                () -> new ModelAndView("/support/support", supportPageInjections(), HttpStatus.OK), "/serverError",
                this::schedulePageInjections);
    }

    public ModelAndView addSupport(Support support) {
        return ExceptionHandler.handle(() -> {
            Optional<UserPrincipal> currentUser = AuthService.getCurrentUser();
            long id = currentUser.get().getId();
            User cUser = userRepository.findById(id).get();
            support.setCreated_by(cUser);
            supportRepository.save(support);
            return this.getSupportPage();
        }, "/support/support", this::supportPageInjections);
    }

    public ModelAndView updateSupport(Support support) {
        return ExceptionHandler.handle(() -> {
            Support supportToUpdate = supportRepository.findById(support.getId()).get();
            supportToUpdate.setTicketID(support.getTicketID());
            supportToUpdate.setCreatedBy(support.getCreatedBy());
            supportToUpdate.setDescription(support.getDescription());
            supportRepository.save(supportToUpdate);
            return this.getSupportPage();
        }, "/support/support", this::supportPageInjections);
    }

    public ModelAndView deleteSupport(long id) {
        return ExceptionHandler.handle(() -> {
            supportRepository.deleteById(id);
            return this.getSupportPage();
        }, "/schedule/schedule", this::supportPageInjections);
    }

    public ModelAndView getAvailability(long origin_city_id, long destination_city_id) {
        Optional<Support> support = supportRepository.findByOriginCityAndDestinationCity(cityRepository.findById(origin_city_id).get(), cityRepository.findById(destination_city_id).get());
        Boolean availability;
        if (support.isPresent()) availability = true;
        else availability = false;
        HashMap availabilityMap = new HashMap() {
            {
                put("routeAvailable", availability.toString());
            }
        };
        availabilityMap.putAll(homeController.homePageInjections());
        return ExceptionHandler.handle(() -> new ModelAndView("/index", availabilityMap , HttpStatus.OK), "/serverError");
    }

    public HashMap supportPageInjections() {
        List<Support> support = scheduleRepository.findAll();
        List<City> cities = cityRepository.findAll();

        Collections.sort(support, (o1, o2) -> {
            try {
                return new SimpleDateFormat("hh:mm").parse(o1.getTime()).compareTo(new SimpleDateFormat("hh:mm").parse(o2.getTime()));
            } catch (ParseException e) {
                return 0;
            }
        });

        return new HashMap() {
            {
                put("support", support);
            }
        };
    }
}
