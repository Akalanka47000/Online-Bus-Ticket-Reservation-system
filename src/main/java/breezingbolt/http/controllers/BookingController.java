package breezingbolt.http.controllers;

import breezingbolt.entities.Booking;
import breezingbolt.entities.User;
import breezingbolt.http.principals.UserPrincipal;
import breezingbolt.http.repository.BookingRepository;
import breezingbolt.http.repository.UserRepository;
import breezingbolt.http.services.AuthService;
import breezingbolt.utils.ExceptionHandler;
import breezingbolt.utils.RedirectHandler;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Aspect
@Component
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ScheduleController scheduleController;

    public ModelAndView getBookingPage() {
        return ExceptionHandler.handle(
                () -> new ModelAndView("/booking/booking", bookingPageInjections(), HttpStatus.OK), "/serverError");
    }

    public ModelAndView addBooking(Booking booking) {
        return ExceptionHandler.handle(() -> {
            Optional<UserPrincipal> currentUser = AuthService.getCurrentUser();
            long id = currentUser.get().getId();
            User cUser = userRepository.findById(id).get();
            List<Booking> check = bookingRepository.findAllByBookedScheduleAndUser(booking.getBookedSchedule(), cUser);
            if (check.size() > 0 )
                return RedirectHandler.redirectWithError("/schedule/schedule", "Booking already exists",
                        HttpStatus.UNPROCESSABLE_ENTITY, scheduleController.schedulePageInjections());
            booking.setUser(cUser);
            bookingRepository.save(booking);
            return this.getBookingPage();
        }, "/booking/booking", this::bookingPageInjections);
    }

    public ModelAndView deleteBooking(long id) {
        return ExceptionHandler.handle(() -> {
            bookingRepository.deleteById(id);
            return this.getBookingPage();
        }, "/booking/booking", this::bookingPageInjections);
    }

    private HashMap bookingPageInjections() {
        Optional<UserPrincipal> currentUser = AuthService.getCurrentUser();
        long id = currentUser.get().getId();
        User cUser = userRepository.findById(id).get();
        List<Booking> bookings = bookingRepository.findAllByUser(cUser);
        return new HashMap() {
            {
                put("booking", bookings);
            }
        };
    }
}
