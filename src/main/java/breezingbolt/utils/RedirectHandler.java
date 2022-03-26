package breezingbolt.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;
import java.util.HashMap;

public class RedirectHandler {
    public static ModelAndView redirect(String path, String message, HttpStatus status){
        return new ModelAndView(path, new HashMap<String, String>() {{
            put("message",message);
        }},status );
    }
    public static ModelAndView redirectWithError(String path, String error, HttpStatus status){
        return new ModelAndView(path, new HashMap<String, String>() {{
            put("errors",error);
        }},status );
    }
}
