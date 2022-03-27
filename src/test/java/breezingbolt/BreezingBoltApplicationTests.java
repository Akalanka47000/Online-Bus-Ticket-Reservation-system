package breezingbolt;

import breezingbolt.http.controllers.HomeController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BreezingBoltApplicationTests {

    @Autowired
    HomeController homeController;

    @Test
    void contextLoads() {
        ModelAndView home = homeController.getHomePage();
        assertEquals(HttpStatus.OK, home.getStatus());
    }

}
