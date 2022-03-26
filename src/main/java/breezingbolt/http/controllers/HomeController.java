package breezingbolt.http.controllers;

import breezingbolt.entities.City;
import breezingbolt.http.repository.CityRepository;
import breezingbolt.utils.AppLogger;
import breezingbolt.utils.ErrorHandler;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Aspect
@Component
public class HomeController {

    @Autowired
    private CityRepository cityRepository;

    public ModelAndView getHomePage() {
        try {
            List<City> cities = cityRepository.findAll();
            return new ModelAndView("index", new HashMap() {{
                put("cityList", cities );
            }}, HttpStatus.OK);
        } catch (Exception e) {
            AppLogger.error(Arrays.toString(e.getStackTrace()));
            return ErrorHandler.redirect("serverError", e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
