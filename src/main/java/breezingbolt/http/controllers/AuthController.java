package breezingbolt.http.controllers;

import breezingbolt.entities.User;
import breezingbolt.http.services.AuthService;
import breezingbolt.utils.AppLogger;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import java.util.Arrays;
import java.util.HashMap;

@Aspect
@Component
public class AuthController {

    @Autowired
    private AuthService authService;

    public ModelAndView signUp(User user) {
        ModelAndView modelAndView = new ModelAndView("index", HttpStatus.OK);
        try {
            User newUser =  authService.signUp(user);
            if (newUser==null) {
                modelAndView = new ModelAndView("auth/signup", new HashMap<String, String>() {{
                    put("errors","Failed to add user");
                }}, HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } catch (Exception e) {
            AppLogger.error(Arrays.toString(e.getStackTrace()));
            modelAndView = new ModelAndView("auth/signup", new HashMap<String, String>() {{
                put("errors",e.getLocalizedMessage());
            }}, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return modelAndView;
    }
}
