package breezingbolt.http.controllers;

import breezingbolt.entities.User;
import breezingbolt.http.principals.UserPrincipal;
import breezingbolt.http.repository.UserRepository;
import breezingbolt.http.services.AuthService;
import breezingbolt.utils.AppLogger;
import breezingbolt.utils.RedirectHandler;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Optional;

@Aspect
@Component
public class UserController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    public ModelAndView getProfilePage() {
        try {
            return new ModelAndView("/profile/profile", HttpStatus.OK);
        } catch (Exception e) {
            AppLogger.error(Arrays.toString(e.getStackTrace()));
            return RedirectHandler.redirectWithError("/serverError", e.getLocalizedMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ModelAndView updateCurrentUser(User user) {
        try {
            Optional<UserPrincipal> currentUser = authService.getCurrentUser();
            long id = currentUser.get().getId();
            User cUser = userRepository.findById(id).get();
            cUser.setUsername(user.getUsername());
            cUser.setFirst_name(user.getFirst_name());
            cUser.setLast_name(user.getLast_name());
            cUser.setEmail(user.getEmail());
            if (!user.getPassword().equals("") && !user.getPassword().startsWith("$2a"))
                cUser.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            userRepository.save(cUser);
        } catch (Exception e) {
            AppLogger.error(Arrays.toString(e.getStackTrace()));
            return RedirectHandler.redirectWithError("/profile/profile", e.getLocalizedMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return this.getProfilePage();
    }

    public ModelAndView deleteCurrentUser(){
        try {
            Optional<UserPrincipal> currentUser = authService.getCurrentUser();
            long id = currentUser.get().getId();
            User cUser = userRepository.findById(id).get();
            userRepository.delete(cUser);
        } catch (Exception e) {
            AppLogger.error(Arrays.toString(e.getStackTrace()));
            return RedirectHandler.redirectWithError("/profile/profile", e.getLocalizedMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ModelAndView("/auth/login", HttpStatus.OK);
    }
}
