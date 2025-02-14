package vn.com.saytruyen.user_service.config.security;

import io.github.buianhtai1205.saytruyen_common_service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import vn.com.saytruyen.user_service.model.User;
import vn.com.saytruyen.user_service.repository.UserRepository;

import java.util.Optional;

/**
 * The type User details custom service.
 */
@Component
public class UserDetailsCustomService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserAndRoleToLogin(username);
        return user.map(UserDetailsCustom::new)
                .orElseThrow(() -> new ResourceNotFoundException("User with username not found: " + username));
    }
}
