package breezingbolt.routes;

import breezingbolt.entities.Schedule;
import breezingbolt.entities.User;
import breezingbolt.http.controllers.AuthController;
import breezingbolt.http.controllers.ManagementController;
import breezingbolt.http.controllers.ScheduleController;
import breezingbolt.http.controllers.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class Api {

    @Autowired
    private AuthController authController;

    @Autowired
    private ManagementController managementController;

    @Autowired
    private ScheduleController scheduleController;

    @Autowired
    private UserController userController;

    @PostMapping("/signup")
    public ModelAndView signUp(@Valid @RequestBody User payload){
        return authController.signUp(payload);
    }

    @PostMapping("/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response){
        return authController.logout(request, response);
    }

    @PostMapping("/auth/user/update")
    public ModelAndView updateCurrentUser(@Valid @RequestBody User payload){
        return userController.updateCurrentUser(payload);
    }

    @PostMapping("/auth/user/delete")
    public ModelAndView deleteCurrentUser(){
        return userController.deleteCurrentUser();
    }

    @PostMapping("/schedule/add")
    public ModelAndView addSchedule(@Valid @RequestBody Schedule payload){
        return scheduleController.addSchedule(payload);
    }

    @PostMapping("/schedule/update")
    public ModelAndView updateSchedule(@Valid @RequestBody Schedule payload){
        return scheduleController.updateSchedule(payload);
    }

    @PostMapping("/schedule/delete/{id}")
    public ModelAndView deleteSchedule(@PathVariable("id") long scheduleId){
        return scheduleController.deleteSchedule(scheduleId);
    }

    @PostMapping("/admin/user/add")
    public ModelAndView addAdmin(@Valid @RequestBody User payload){
        return managementController.addUser(payload);
    }

    @PostMapping("/admin/user/update")
    public ModelAndView updateAdmin(@Valid @RequestBody User payload){
        return managementController.updateUser(payload);
    }

    @PostMapping("/admin/user/delete/{id}")
    public ModelAndView deleteAdmin(@PathVariable("id") long userId){
        return managementController.deleteUser(userId);
    }
}
