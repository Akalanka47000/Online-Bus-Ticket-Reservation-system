package breezingbolt.http.controllers;

import breezingbolt.entities.User;
import breezingbolt.http.services.AuthService;
import breezingbolt.utils.AppLogger;
import breezingbolt.utils.RedirectHandler;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Aspect
@Component
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private HomeController homeController;

    public ModelAndView signUp(User user) {
        try {
            if(user.getRole().getId() == 1) return RedirectHandler.redirectWithError("auth/signup","You cannot register as an admin from here",HttpStatus.BAD_REQUEST);
            User newUser = authService.signUp(user);
            if (newUser == null) return RedirectHandler.redirectWithError("auth/signup","Failed to add user",HttpStatus.UNPROCESSABLE_ENTITY );
        } catch (Exception e) {
            AppLogger.error(Arrays.toString(e.getStackTrace()));
            return RedirectHandler.redirectWithError("auth/signup", e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return RedirectHandler.redirect("auth/login", "Account created successfully", HttpStatus.OK);
    }

    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response){
        try {
            authService.logout(request,response);
            return homeController.getHomePage();
        } catch (Exception e) {
            AppLogger.error(Arrays.toString(e.getStackTrace()));
            return RedirectHandler.redirectWithError("serverError", e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
