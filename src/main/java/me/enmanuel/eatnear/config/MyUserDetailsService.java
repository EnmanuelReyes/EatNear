package me.enmanuel.eatnear.config;

import me.enmanuel.eatnear.entity.User;
import me.enmanuel.eatnear.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User: Enmanuel Reyes
 * Date: 31-Jan-17
 * Time: 9:53 AM
 */
@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {
    //get user from the database, via Hibernate
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);
        return user;
    }
}
