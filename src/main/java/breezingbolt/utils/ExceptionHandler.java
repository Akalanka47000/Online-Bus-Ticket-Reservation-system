package breezingbolt.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.Callable;

public class ExceptionHandler {
    public static ModelAndView handle(Callable function, String page, Callable injections) {
        try {
            try {
                return (ModelAndView) function.call();
            } catch (Exception e) {
                AppLogger.error(Arrays.toString(e.getStackTrace()));
                return RedirectHandler.redirectWithError(page, e.getLocalizedMessage(),
                        HttpStatus.INTERNAL_SERVER_ERROR, (HashMap) injections.call());
            }
        }catch (Exception e) {
            AppLogger.error(Arrays.toString(e.getStackTrace()));
            return RedirectHandler.redirectWithError("/serverError", e.getLocalizedMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static ModelAndView handle(Callable function, String page) {
        try {
            try {
                return (ModelAndView) function.call();
            } catch (Exception e) {
                AppLogger.error(Arrays.toString(e.getStackTrace()));
                return RedirectHandler.redirectWithError(page, e.getLocalizedMessage(),
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e) {
            AppLogger.error(Arrays.toString(e.getStackTrace()));
            return RedirectHandler.redirectWithError("/serverError", e.getLocalizedMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
