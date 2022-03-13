package breezingbolt.database.seeders;

import breezingbolt.entities.Role;
import breezingbolt.entities.User;
import breezingbolt.http.repository.RoleRepository;
import breezingbolt.http.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserSeeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        seed();
    }

    public void seed() {
       if(userRepository.findAll().size()==0){
           List<User> users = new ArrayList<>();
           Optional<Role> adminRole = roleRepository.findById(1L);
           users.add(new User(adminRole.get(),"Akalanka47", "Akalanka", "Perera", "akalankaperera128@gmail.com", "123", true));
           users.forEach((user)->{
               userRepository.save(user);
           });
       }
    }
}
