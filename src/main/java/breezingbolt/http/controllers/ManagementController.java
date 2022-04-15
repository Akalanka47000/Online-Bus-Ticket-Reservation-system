package breezingbolt.http.controllers;

import breezingbolt.entities.User;
import breezingbolt.http.repository.UserRepository;
import breezingbolt.utils.AppLogger;
import breezingbolt.utils.RedirectHandler;
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
public class ManagementController {

    @Autowired
    private UserRepository userRepository;

    public ModelAndView getManagementPage() {
        try {
            List<User> users = userRepository.findAllByRole_Name("SuperAdmin");
            return new ModelAndView("management/management", new HashMap() {{
                put("admins", users );
            }}, HttpStatus.OK);
        } catch (Exception e) {
            AppLogger.error(Arrays.toString(e.getStackTrace()));
            return RedirectHandler.redirectWithError("serverError", e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
