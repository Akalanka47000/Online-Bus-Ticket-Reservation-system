package breezingbolt.http.controllers;

import breezingbolt.entities.Role;
import breezingbolt.entities.User;
import breezingbolt.http.repository.RoleRepository;
import breezingbolt.http.repository.UserRepository;
import breezingbolt.http.services.AuthService;
import breezingbolt.utils.ExceptionHandler;
import breezingbolt.utils.RedirectHandler;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Aspect
@Component
public class ManagementController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthService authService;

    public ModelAndView getManagementPage() {
        return ExceptionHandler.handle(() -> new ModelAndView("/management/user_management", this.managementPageInjections(), HttpStatus.OK), "/serverError");
    }

    public ModelAndView addUser(User user) {
        return ExceptionHandler.handle(() -> {
            Optional<Role> adminRole = roleRepository.findByName("SuperAdmin");
            user.setRole(adminRole.get());
            User newUser = authService.signUp(user);
            if (newUser == null)
                return RedirectHandler.redirectWithError("/management/user_management", "Failed to add admin",
                        HttpStatus.UNPROCESSABLE_ENTITY, this.managementPageInjections());
            return this.getManagementPage();
        }, "/management/user_management", this::managementPageInjections);
    }

    public ModelAndView updateUser(User user) {
        return ExceptionHandler.handle(() -> {
            User currentUser = userRepository.findById(user.getId()).get();
            currentUser.setFirst_name(user.getFirst_name());
            currentUser.setLast_name(user.getLast_name());
            currentUser.setUsername(user.getUsername());
            currentUser.setEmail(user.getEmail());
            if (!user.getPassword().equals(""))
                currentUser.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            userRepository.save(currentUser);
            return this.getManagementPage();
        }, "/management/user_management", this::managementPageInjections);
    }

    public ModelAndView deleteUser(long id) {
        return ExceptionHandler.handle(() -> {
            long currentUserId = AuthService.getCurrentUser().get().getId();
            if (id == currentUserId)
                return RedirectHandler.redirectWithError("/management/user_management",
                        "You cannot delete your own account", HttpStatus.UNPROCESSABLE_ENTITY,
                        this.managementPageInjections());
            userRepository.deleteById(id);
            return this.getManagementPage();
        }, "/management/user_management", this::managementPageInjections);
    }

    private HashMap managementPageInjections() {
        List<User> users = userRepository.findAllByRole_Name("SuperAdmin");
        return new HashMap() {
            {
                put("admins", users);
            }
        };
    }
}
