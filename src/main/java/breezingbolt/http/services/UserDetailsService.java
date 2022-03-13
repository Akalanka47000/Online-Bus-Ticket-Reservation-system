package breezingbolt.http.services;

import breezingbolt.entities.User;
import breezingbolt.http.principals.UserPrincipal;
import breezingbolt.http.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String value) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(value);

        if(user.isPresent())
            return new UserPrincipal(user.get());

        user = userRepository.findByEmail(value);

        if(user.isPresent())
            return new UserPrincipal(user.get());

        throw new UsernameNotFoundException("A user with the given username does not exist in the system");
    }
}
