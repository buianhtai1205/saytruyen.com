package vn.com.saytruyen.user_service.config.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import vn.com.saytruyen.user_service.model.User;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type User details custom.
 */
public class UserDetailsCustom implements UserDetails {

    @Getter
    private final Long id;

    private final String username;

    private final String password;

    private final List<GrantedAuthority> authorities;

    /**
     * Instantiates a new User details custom.
     *
     * @param user the user
     */
    public UserDetailsCustom(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.authorities = user.getUserRoles().stream()
                .map(userRole -> {
                    String roleName = "ROLE_" + userRole.getRole().getRoleName();
                    return new SimpleGrantedAuthority(roleName);
                }).collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
