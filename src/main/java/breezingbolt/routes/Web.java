package breezingbolt.routes;

import breezingbolt.http.controllers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Web {

    @Autowired
    private HomeController homeController;

    @Autowired
    private ScheduleController scheduleController;

    @Autowired
    private BookingController bookingController;

    @Autowired
    private ManagementController managementController;

    @Autowired
    private UserController userController;

    @GetMapping("/")
    public ModelAndView home() {
        return homeController.getHomePage();
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "auth/signup";
    }

    @GetMapping("/schedule")
    public ModelAndView schedule() {
        return scheduleController.getSchedulePage();
    }

    @GetMapping("/booking")
    public ModelAndView booking() {
        return bookingController.getBookingPage();
    }

    @GetMapping("/management")
    public ModelAndView management() {
        return managementController.getManagementPage();
    }

    @GetMapping("/profile")
    public ModelAndView profile() {
        return userController.getProfilePage();
    }
}
