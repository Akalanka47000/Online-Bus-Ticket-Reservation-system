package breezingbolt.http.controllers;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

public class AuthController {
    @ResponseBody
    public static String login(HttpServletRequest req, String email, String password, Model m){
        System.out.println(email);
        System.out.println(password);
        m.addAttribute("res" , "success");
        req.getSession().setAttribute("loggedIn", true);
        return "index";
    }
}
