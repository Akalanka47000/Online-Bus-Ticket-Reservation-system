package breezingbolt.routes;

import breezingbolt.entities.User;
import breezingbolt.http.controllers.AuthController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class Api {

    @Autowired
    private AuthController authController;

    @PostMapping("/signup")
    public ModelAndView signUp(@Valid @RequestBody User payload){
        return authController.signUp(payload);
    }

    @PostMapping("/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response){
        return authController.logout(request, response);
    }
}
