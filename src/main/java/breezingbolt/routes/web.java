package breezingbolt.routes;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class web {
    @GetMapping("/")
    public String home(HttpServletRequest req) {
        boolean loggedIn = req.getSession().getAttribute("loggedIn") != null;
        return loggedIn? "index" : "auth/login";
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }
}
