package breezingbolt.http.controllers;

import breezingbolt.entities.User;
import breezingbolt.http.services.AuthService;
import breezingbolt.utils.AppLogger;
import breezingbolt.utils.ErrorHandler;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import java.util.Arrays;

@Aspect
@Component
public class AuthController {

    @Autowired
    private AuthService authService;

    public ModelAndView signUp(User user) {
        try {
            if(user.getRole().getId() == 1) return ErrorHandler.redirect("auth/signup","You cannot register as an admin from here",HttpStatus.BAD_REQUEST);
            User newUser = authService.signUp(user);
            if (newUser == null) return ErrorHandler.redirect("auth/signup","Failed to add user",HttpStatus.UNPROCESSABLE_ENTITY );
        } catch (Exception e) {
            AppLogger.error(Arrays.toString(e.getStackTrace()));
            return ErrorHandler.redirect("auth/signup", e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ModelAndView("index", HttpStatus.OK);
    }
}
