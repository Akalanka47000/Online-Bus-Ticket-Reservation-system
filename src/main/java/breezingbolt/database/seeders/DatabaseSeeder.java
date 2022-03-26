package breezingbolt.database.seeders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    CitySeeder citySeeder;

    @Autowired
    RoleSeeder roleSeeder;

    @Autowired
    UserSeeder userSeeder;

    @Override
    public void run(String... args) throws Exception {
        citySeeder.seed();
        roleSeeder.seed();
        userSeeder.seed();
    }
}
