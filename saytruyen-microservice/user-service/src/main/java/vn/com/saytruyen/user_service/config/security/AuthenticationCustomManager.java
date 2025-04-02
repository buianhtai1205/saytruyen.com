package vn.com.saytruyen.user_service.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import vn.com.saytruyen.user_service.constant.UserServiceMessage;

/**
 * The type Authentication custom manager.
 */
@Component
public class AuthenticationCustomManager implements AuthenticationManager {

    private final MessageSource messageSource;
    @Autowired
    private UserDetailsCustomService userDetailsCustomService;

    /**
     * Instantiates a new Authentication custom manager.
     *
     * @param messageSource the message source
     */
    public AuthenticationCustomManager(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final UserDetails userDetail = userDetailsCustomService.loadUserByUsername(authentication.getName());
        if (!new BCryptPasswordEncoder().matches(authentication.getCredentials().toString(), userDetail.getPassword())) {
            throw new BadCredentialsException(
                    messageSource.getMessage(
                            UserServiceMessage.UT_E000002,
                            null,
                            LocaleContextHolder.getLocale()
                    )
            );
        }
        return new UsernamePasswordAuthenticationToken(userDetail.getUsername(), userDetail.getPassword(), userDetail.getAuthorities());
    }
}
