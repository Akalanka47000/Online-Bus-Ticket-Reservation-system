package breezingbolt.database.seeders;

import breezingbolt.entities.Role;
import breezingbolt.entities.User;
import breezingbolt.http.repository.RoleRepository;
import breezingbolt.http.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserSeeder {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public void seed() {
       if(userRepository.findAll().size()==0){
           List<User> users = new ArrayList<>();
           Optional<Role> adminRole = roleRepository.findByName("SuperAdmin");
           users.add(new User(adminRole.get(),"Akalanka47", "Akalanka", "Perera", "akalankaperera128@gmail.com", BCrypt.hashpw("123456", BCrypt.gensalt()), true));
           users.forEach((user)->{
               userRepository.save(user);
           });
       }
    }
}
