package breezingbolt.http.controllers;

import breezingbolt.entities.Role;
import breezingbolt.entities.User;
import breezingbolt.http.principals.UserPrincipal;
import breezingbolt.http.repository.RoleRepository;
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
        try {
            List<User> users = userRepository.findAllByRole_Name("SuperAdmin");
            return new ModelAndView("/management/user_management", new HashMap() {
                {
                    put("admins", users);
                }
            }, HttpStatus.OK);
        } catch (Exception e) {
            AppLogger.error(Arrays.toString(e.getStackTrace()));
            return RedirectHandler.redirectWithError("/serverError", e.getLocalizedMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ModelAndView addUser(User user) {
        List<User> users = userRepository.findAllByRole_Name("SuperAdmin");
        try {
            Optional<Role> adminRole = roleRepository.findByName("SuperAdmin");
            user.setRole(adminRole.get());
            User newUser = authService.signUp(user);
            if (newUser == null)
                return RedirectHandler.redirectWithError("/management/user_management", "Failed to add admin",
                        HttpStatus.UNPROCESSABLE_ENTITY, new HashMap() {
                            {
                                put("admins", users);
                            }
                        });
        } catch (Exception e) {
            AppLogger.error(Arrays.toString(e.getStackTrace()));
            return RedirectHandler.redirectWithError("/management/user_management", e.getLocalizedMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR, new HashMap() {
                        {
                            put("admins", users);
                        }
                    });
        }
        return this.getManagementPage();
    }

    public ModelAndView updateUser(User user) {
        List<User> users = userRepository.findAllByRole_Name("SuperAdmin");
        try {
            User currentUser = userRepository.findById(user.getId()).get();
            currentUser.setFirst_name(user.getFirst_name());
            currentUser.setLast_name(user.getLast_name());
            currentUser.setUsername(user.getUsername());
            currentUser.setEmail(user.getEmail());
            if (!user.getPassword().equals(""))
                currentUser.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            userRepository.save(currentUser);
        } catch (Exception e) {
            AppLogger.error(Arrays.toString(e.getStackTrace()));
            return RedirectHandler.redirectWithError("/management/user_management", e.getLocalizedMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR, new HashMap() {
                        {
                            put("admins", users);
                        }
                    });
        }
        return this.getManagementPage();
    }

    public ModelAndView deleteUser(long id) {
        List<User> users = userRepository.findAllByRole_Name("SuperAdmin");
        try {
            long currentUserId = AuthService.getCurrentUser().get().getId();
            if (id == currentUserId)
                return RedirectHandler.redirectWithError("/management/user_management",
                        "You cannot delete your own account", HttpStatus.UNPROCESSABLE_ENTITY, new HashMap() {
                            {
                                put("admins", users);
                            }
                        });
            userRepository.deleteById(id);
        } catch (Exception e) {
            AppLogger.error(Arrays.toString(e.getStackTrace()));
            return RedirectHandler.redirectWithError("/management/user_management", e.getLocalizedMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR, new HashMap() {
                        {
                            put("admins", users);
                        }
                    });
        }
        return this.getManagementPage();
    }
}
