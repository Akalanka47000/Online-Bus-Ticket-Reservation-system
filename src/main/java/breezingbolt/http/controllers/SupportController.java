package breezingbolt.http.controllers;

import breezingbolt.entities.Support;
import breezingbolt.entities.User;
import breezingbolt.http.principals.UserPrincipal;
import breezingbolt.http.repository.CityRepository;
import breezingbolt.http.repository.ScheduleRepository;
import breezingbolt.http.repository.SupportRepository;
import breezingbolt.http.repository.UserRepository;
import breezingbolt.http.services.AuthService;
import breezingbolt.utils.ExceptionHandler;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

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
                this::supportPageInjections);
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
            supportToUpdate.setTitle(support.getTitle());
            supportToUpdate.setBody(support.getBody());
            supportRepository.save(supportToUpdate);
            return this.getSupportPage();
        }, "/support/support", this::supportPageInjections);
    }

    public ModelAndView deleteSupport(long id) {
        return ExceptionHandler.handle(() -> {
            supportRepository.deleteById(id);
            return this.getSupportPage();
        }, "/support/support", this::supportPageInjections);
    }

    public HashMap supportPageInjections() {
        List<Support> support = supportRepository.findAll();
        return new HashMap() {
            {
                put("support", support);
            }
        };
    }
}
