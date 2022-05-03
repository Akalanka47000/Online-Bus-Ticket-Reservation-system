package breezingbolt.http.controllers;

import breezingbolt.entities.User;
import breezingbolt.http.services.AuthService;
import breezingbolt.utils.ExceptionHandler;
import breezingbolt.utils.RedirectHandler;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Aspect
@Component
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private HomeController homeController;

    public ModelAndView signUp(User user) {
        return ExceptionHandler.handle(() -> {
            if (user.getRole().getId() == 1)
                return RedirectHandler.redirectWithError("auth/signup", "You cannot register as an admin from here",
                        HttpStatus.BAD_REQUEST);
            User newUser = authService.signUp(user);
            if (newUser == null)
                return RedirectHandler.redirectWithError("auth/signup", "Failed to add user",
                        HttpStatus.UNPROCESSABLE_ENTITY);
            return RedirectHandler.redirect("auth/login", "Account created successfully", HttpStatus.OK);
        }, "auth/signup");

    }

    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
        return ExceptionHandler.handle(() -> {
            authService.logout(request, response);
            return homeController.getHomePage();
        }, "serverError");
    }
}
