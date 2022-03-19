package breezingbolt.routes;

import breezingbolt.entities.User;
import breezingbolt.http.controllers.AuthController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;

@Controller
public class api {

    @Autowired
    AuthController authController;

    @PostMapping("/signup")
    public ModelAndView signUp(@Valid @RequestBody User payload){
        return authController.signUp(payload);
    }
}
