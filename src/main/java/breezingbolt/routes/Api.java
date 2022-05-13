package breezingbolt.routes;

import breezingbolt.entities.Booking;
import breezingbolt.entities.Schedule;
import breezingbolt.entities.Support;
import breezingbolt.entities.User;
import breezingbolt.http.controllers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;

@Controller
public class Api {

    @Autowired
    private AuthController authController;

    @Autowired
    private ManagementController managementController;

    @Autowired
    private ScheduleController scheduleController;

    @Autowired
    private ScheduleController ticketController;

    @Autowired
    private BookingController bookingController;

    @Autowired
    private SupportController supportController;

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

    @PostMapping("/support/add")
    public ModelAndView addSupport(@Valid @RequestBody Support payload){
        return supportController.addSupport(payload);
    }

    @PostMapping("/support/update")
    public ModelAndView updateSupport(@Valid @RequestBody Support payload){
        return supportController.updateSupport(payload);
    }

    @PostMapping("/support/delete/{id}")
    public ModelAndView deleteSupport(@PathVariable("id") long ticketId){
        return supportController.deleteSupport(ticketId);
    }

    @PostMapping("/schedule/availability")
    public ModelAndView getAvailability(@RequestBody HashMap payload){
        return scheduleController.getAvailability(Long.parseLong((String) payload.get("origin_city_id")), Long.parseLong((String) payload.get("destination_city_id")));
    }

    @PostMapping("/booking/add")
    public ModelAndView addBooking(@Valid @RequestBody Booking payload){
        return bookingController.addBooking(payload);
    }

    @PostMapping("/booking/delete/{id}")
    public ModelAndView deleteBooking(@PathVariable("id") long bookingId){
        return bookingController.deleteBooking(bookingId);
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
