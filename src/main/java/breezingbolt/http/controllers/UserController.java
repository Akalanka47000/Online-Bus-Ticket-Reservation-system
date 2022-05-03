package breezingbolt.http.controllers;

import breezingbolt.entities.User;
import breezingbolt.http.principals.UserPrincipal;
import breezingbolt.http.repository.UserRepository;
import breezingbolt.http.services.AuthService;
import breezingbolt.utils.ExceptionHandler;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Aspect
@Component
public class UserController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    public ModelAndView getProfilePage() {
        return ExceptionHandler.handle(() -> new ModelAndView("/profile/profile", HttpStatus.OK), "/serverError");
    }

    public ModelAndView updateCurrentUser(User user) {
        return ExceptionHandler.handle(() -> {
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
            UserPrincipal principal = new UserPrincipal(cUser);
            Authentication authentication = new UsernamePasswordAuthenticationToken(principal, principal.getPassword(), principal.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return this.getProfilePage();
        }, "/profile/profile");
    }

    public ModelAndView deleteCurrentUser(){
        return ExceptionHandler.handle(() -> {
            Optional<UserPrincipal> currentUser = authService.getCurrentUser();
            long id = currentUser.get().getId();
            User cUser = userRepository.findById(id).get();
            userRepository.delete(cUser);
            return new ModelAndView("/auth/login", HttpStatus.OK);
        }, "/profile/profile");
    }
}
