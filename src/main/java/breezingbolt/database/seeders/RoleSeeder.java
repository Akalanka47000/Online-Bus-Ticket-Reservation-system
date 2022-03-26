package breezingbolt.database.seeders;

import breezingbolt.entities.Role;
import breezingbolt.http.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class RoleSeeder {

    @Autowired
    private RoleRepository roleRepository;

    public void seed() {
        if(roleRepository.findAll().size()==0){
            List<Role> roles = new ArrayList<>();
            roles.add(new Role("SuperAdmin"));
            roles.add(new Role("BusinessAdmin"));
            roles.add(new Role("User"));
            roles.forEach((role)->{
                roleRepository.save(role);
            });
        }
    }
}
