package breezingbolt.routes;

import breezingbolt.http.controllers.HomeController;
import breezingbolt.http.controllers.ManagementController;
import breezingbolt.http.controllers.ScheduleController;
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
    private ManagementController managementController;

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

    @GetMapping("/management")
    public ModelAndView management() {
        return managementController.getManagementPage();
    }
}
