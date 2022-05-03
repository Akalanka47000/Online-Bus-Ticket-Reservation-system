package breezingbolt.http.controllers;

import breezingbolt.entities.City;
import breezingbolt.http.repository.CityRepository;
import breezingbolt.utils.ExceptionHandler;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import java.util.HashMap;
import java.util.List;

@Aspect
@Component
public class HomeController {

    @Autowired
    private CityRepository cityRepository;

    public ModelAndView getHomePage() {
        return ExceptionHandler.handle(() -> new ModelAndView("index", this.homePageInjections(), HttpStatus.OK), "serverError");
    }

    private HashMap homePageInjections() {
        List<City> cities = cityRepository.findAll();
        return new HashMap() {
            {
                put("cityList", cities);
            }
        };
    }
}
